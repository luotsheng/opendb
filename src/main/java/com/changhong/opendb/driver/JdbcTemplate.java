package com.changhong.opendb.driver;

import com.changhong.opendb.driver.datasource.DataSourceProxy;
import com.changhong.opendb.ui.navigator.node.ODBNDatabase;
import com.changhong.opendb.utils.Catcher;
import lombok.Getter;
import org.w3c.dom.CDATASection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.changhong.opendb.utils.StringUtils.strfmt;

/**
 * @author Luo Tiansheng
 * @since 2026/3/30
 */
@SuppressWarnings("SqlSourceToSinkFlow")
public class JdbcTemplate
{
        @Getter
        private final String connectionName;
        private final DataSourceProxy ds;

        public JdbcTemplate(String connectionName, DataSourceProxy ds)
        {
                this.connectionName = connectionName;
                this.ds = ds;
        }

        /**
         * 获取数据库列表
         */
        public List<String> getDatabases()
        {
                return ds.getDatabases();
        }

        /**
         * 获取表
         */
        public List<TableInfo> getTables(String database)
        {
                return ds.getTables(database);
        }

        @SuppressWarnings("SqlSourceToSinkFlow")
        public QueryResultSet selectByPage(String database, String table, int start, int size)
        {
                QueryResultSet qrs = new QueryResultSet();

                String sql = strfmt("SELECT * FROM %s LIMIT %d OFFSET %d;", table, size, start);

                try (Connection connection = ds.getConnection();
                     Statement statement = ds.use(connection, database);
                     ResultSet rs = statement.executeQuery(sql)) {
                        rs2qrs(rs, qrs);
                } catch (Throwable e) {
                        Catcher.ithrow(e);
                }

                return qrs;
        }

        public QueryResultSet select(String db, String[] sql)
        {
                QueryResultSet qrs = new QueryResultSet();

                try (Connection connection = ds.getConnection();
                     Statement statement = ds.use(connection, db)) {

                        for (int i = 0; i < sql.length - 1; i++)
                                statement.execute(sql[i]);

                        ResultSet rs = statement.executeQuery(sql[sql.length - 1]);
                        rs2qrs(rs, qrs);
                } catch (Throwable e) {
                        Catcher.ithrow(e);
                }

                return qrs;
        }

        private static void rs2qrs(ResultSet rs, QueryResultSet qrs) throws SQLException
        {
                ResultSetMetaData meta = rs.getMetaData();
                int colCount = meta.getColumnCount();

                for (int i = 1; i <= colCount; i++)
                        qrs.getColumns().add(meta.getColumnLabel(i));

                while (rs.next()) {
                        List<String> row = new ArrayList<>();
                        for (int i = 1; i <= colCount; i++) {
                                Object val = rs.getObject(i);
                                row.add(val != null ? val.toString() : null);
                        }
                        qrs.getRows().add(row);
                }
        }
}
