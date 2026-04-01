package com.changhong.opendb.driver;

import com.changhong.opendb.driver.datasource.VirtualDataSource;
import lombok.Getter;

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

        /**
         * 获取当前数据源中的所有数据库名称列表
         *
         * @return 数据库名称列表，按字母顺序排序；若无数据库则返回空列表
         */
        public abstract List<String> databases();

        /**
         * 获取指定数据库中的所有表信息
         *
         * @param db 数据库名称，不能为 null 或空字符串
         * @return 表信息列表，包含表名、类型、注释等元数据；若无表则返回空列表
         *
         * @throws IllegalArgumentException 如果数据库名称无效或不存在
         */
        public abstract List<TableInfo> tables(String db);

        /**
         * 执行自定义 SQL 查询语句序列（支持多条语句，最后一条必须为查询语句）
         *
         * @param id  查询任务标识符，用于取消任务；可为 null（表示不可取消）
         * @param db  数据库名称，不能为 null 或空字符串
         * @param sql SQL 语句数组，前 n-1 条可为更新/DDL 语句，最后一条必须为 SELECT 语句
         * @return 查询结果集，包含列元数据及数据行
         *
         * @throws SQLException             当 SQL 语法错误、执行失败或查询被取消时抛出
         * @throws IllegalArgumentException 如果 sql 数组为空或最后一条不是 SELECT 语句
         */
        public abstract QueryResultSet select(Long id, String db, String[] sql)
                throws SQLException;

        /**
         * 分页查询指定表的全部数据（基于表元数据）
         *
         * @param db    数据库名称，不能为 null 或空字符串
         * @param table 表元数据对象，包含表名、列信息等
         * @param start 起始行索引（从 0 开始）
         * @param size  每页返回的行数（必须大于 0）
         * @return 分页查询结果集，包含指定范围的数据行
         *
         * @throws SQLException             当查询执行失败时抛出
         * @throws IllegalArgumentException 如果 start < 0 或 size <= 0
         */
        public abstract QueryResultSet select(String db, TableInfo table, int start, int size)
                throws SQLException;

        /**
         * 分页查询指定表的全部数据（基于表名）
         *
         * @param db    数据库名称，不能为 null 或空字符串
         * @param table 表名称，不能为 null 或空字符串
         * @param start 起始行索引（从 0 开始）
         * @param size  每页返回的行数（必须大于 0）
         * @return 分页查询结果集，包含指定范围的数据行
         *
         * @throws SQLException             当查询执行失败时抛出
         * @throws IllegalArgumentException 如果表名无效、start < 0 或 size <= 0
         */
        public abstract QueryResultSet select(String db, String table, int start, int size)
                throws SQLException;

        /**
         * 取消指定标识符的正在执行的查询任务
         *
         * @param id 查询任务标识符，对应 {@link #select(Long, String, String[])} 中的 id 参数
         * @throws IllegalStateException 如果任务不存在或已完成
         */
        public abstract void cancel(Long id);
}
