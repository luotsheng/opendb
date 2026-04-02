package com.changhong.opendb.driver;

import com.changhong.opendb.driver.datasource.VirtualDataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Jdbc 模板
 *
 * @author Luo Tiansheng
 * @since 2026/3/30
 */
@SuppressWarnings({
        "SqlDialectInspection",
        "SqlNoDataSourceInspection"
})
public abstract class JdbcTemplate
{
        private final String name;
        protected final VirtualDataSource ds;

        /**
         * 任务队列
         */
        protected final Map<Long, Statement> queue = new ConcurrentHashMap<>();

        /**
         * 创建 Jdbc 目录
         *
         * @param name 连接名称
         * @param ds   数据源
         */
        public JdbcTemplate(String name, VirtualDataSource ds)
        {
                this.name = name;
                this.ds = ds;
        }

        /**
         * @return 返回 Jdbc 连接名称
         */
        public String name()
        {
                return name;
        }

        public abstract List<String> databases();

        public abstract List<TableMetadata> tables(String db);

        public abstract void deleteTable(String db, String name) throws SQLException;

        public abstract boolean execute(Long id, String db, String[] sql)
                throws SQLException;

        public abstract QueryResultSet select(Long id, String db, String[] sql)
                throws SQLException;

        public abstract QueryResultSet select(String db, TableMetadata table, int start, int size)
                throws SQLException;

        public abstract QueryResultSet select(String db, String table, int start, int size)
                throws SQLException;

        public abstract void cancel(Long id);
}
