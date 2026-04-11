package com.changhong.driver.mysql;

import com.changhong.collection.Lists;
import com.changhong.driver.api.*;
import com.changhong.driver.api.Driver;
import com.changhong.driver.exception.SQLRuntimeException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

import static com.changhong.string.StringStaticize.strwfmt;

/**
 * MySQL 驱动层实现
 *
 * @author Luo Tiansheng
 * @since 2026/4/11
 */
@SuppressWarnings("SqlSourceToSinkFlow")
public class MySQLDriver extends Driver implements SQLExecutor
{
        public MySQLDriver(DataSource dataSource)
        {
                super(dataSource);
        }

        @Override
        public List<Table> getTables(Session session)
        {
                List<Table> tables = Lists.newArrayList();

                executeQuery(session, statement -> {
                        String sql = strwfmt("""
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
                        """, session.schema());

                        try (var rs = statement.executeQuery(sql)) {
                                while (rs.next()) {
                                        tables.add(new Table(
                                                rs.getString("name"),
                                                rs.getString("createTime"),
                                                rs.getString("updateTime"),
                                                rs.getString("engine"),
                                                rs.getFloat("size"),
                                                rs.getInt("rows"),
                                                rs.getString("comment")
                                        ));
                                }
                        } catch (SQLException e) {
                                throw new SQLRuntimeException(e);
                        }
                });

                return tables;
        }

        @Override
        public DataGrid execute(long jobId, SQL job)
        {
                return null;
        }

        @Override
        public void cancel(long jobId)
        {

        }
}
