package com.changhong.opendb.driver;

import com.changhong.opendb.core.event.EventBus;
import com.changhong.opendb.driver.datasource.VirtualDataSource;
import com.changhong.opendb.utils.Catcher;
import com.changhong.opendb.utils.ResultSetUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.changhong.opendb.utils.StringUtils.strfmt;

/**
 * @author Luo Tiansheng
 * @since 2026/3/30
 */
@SuppressWarnings({
        "SqlSourceToSinkFlow",
        "SqlDialectInspection",
        "SqlNoDataSourceInspection"
})
public class MySQLJdbcTemplate extends JdbcTemplate
{
        public MySQLJdbcTemplate(String name, VirtualDataSource ds)
        {
                super(name, ds);
        }

        @Override
        public List<String> databases()
        {
                String sql = "SHOW DATABASES;";

                try (Connection connection = ds.getConnection();
                     Statement statement = connection.createStatement()) {
                        List<String> ret = new ArrayList<>();
                        ResultSet resultSet = statement.executeQuery(sql);

                        while (resultSet.next())
                                ret.add(resultSet.getString(1));

                        return ret;
                } catch (SQLException e) {
                        EventBus.publish(e);
                }

                return List.of();
        }

        @Override
        public List<TableMetadata> tables(String db)
        {
                String sql = strfmt("""
                    SELECT
                    	`TABLE_NAME` AS `name`,
                    	`CREATE_TIME` AS `createTime`,
                    	`UPDATE_TIME` AS `updateTime`,
                    	`ENGINE` AS `engine`,
                    	ROUND((DATA_LENGTH + INDEX_LENGTH) / 1024, 2) AS `size`,
                    	`TABLE_ROWS` AS `rows`,
                    	`TABLE_COMMENT` AS `comment`
                    FROM
                    	information_schema.TABLES
                    WHERE
                    	TABLE_SCHEMA = '%s'
                """, db);

                try (Connection connection = ds.getConnection();
                     Statement statement = ds.use(connection, db)) {
                        ResultSet rs = statement.executeQuery(sql);
                        return ResultSetUtils.rs2jlist(rs, TableMetadata.class);
                } catch (SQLException e) {
                        EventBus.publish(e);
                }

                return List.of();
        }

        @Override
        public void deleteTable(String db, String name) throws SQLException
        {
                try (Connection connection = ds.getConnection();
                     Statement statement = ds.use(connection, db)) {
                        statement.execute(strfmt("DROP TABLE `%s`;", name));
                }
        }

        public QueryResultSet select(Long id, String db, String[] sql)
                throws SQLException
        {
                QueryResultSet qrs = new QueryResultSet();

                try (Connection connection = ds.getConnection();
                     Statement statement = ds.use(connection, db)) {

                        queue.put(id, statement);

                        for (int i = 0; i < sql.length - 1; i++)
                                statement.execute(sql[i]);

                        ResultSet rs = statement.executeQuery(sql[sql.length - 1]);
                        queue.remove(id);
                        ResultSetUtils.rs2qrs(rs, qrs);
                }

                return qrs;
        }

        public QueryResultSet select(String db, TableMetadata table, int start, int size)
                throws SQLException
        {
                return select(db, table.getName(), start, size);
        }

        public QueryResultSet select(String db, String table, int start, int size)
                throws SQLException
        {
                QueryResultSet qrs = new QueryResultSet();

                String sql = strfmt("SELECT * FROM %s LIMIT %d OFFSET %d;", table, size, start);

                try (Connection connection = ds.getConnection();
                     Statement statement = ds.use(connection, db);
                     ResultSet rs = statement.executeQuery(sql)) {
                        ResultSetUtils.rs2qrs(rs, qrs);
                }

                return qrs;
        }

        @Override
        public void cancel(Long id)
        {
                Catcher.tryCall(() -> {
                        if (queue.containsKey(id))
                                queue.get(id).cancel();
                });
        }
}
