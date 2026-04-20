package com.changhong.openvdb.driver.redis

import com.changhong.openvdb.driver.api.Column
import com.changhong.openvdb.driver.api.DbType
import com.changhong.openvdb.driver.api.Dialect
import com.changhong.openvdb.driver.api.Driver
import com.changhong.openvdb.driver.api.Index
import com.changhong.openvdb.driver.api.Session
import com.changhong.openvdb.driver.api.Table
import redis.clients.jedis.Jedis

import javax.sql.DataSource

/**
 * Redis 驱动层实现
 *
 * @author Luo Tiansheng
 * @since 2026/4/20
 */
class RedisDriver extends Driver {

        private Jedis jedis

        /**
         * 构造一个新的驱动实例。
         *
         * @param dataSource 数据源，用于获取数据库连接（不能为 {@code null}）
         * @throws NullPointerException 如果 {@code dataSource} 为 {@code null}
         */
        RedisDriver(DataSource dataSource) {
                super(dataSource)
                this.jedis = ((RedisDataSource) dataSource).jedis
        }

        @Override
        List<String> getCatalogs() {
                List<String> catalogs = []
                def count = jedis.configGet("databases").get("databases") as Integer
                for (int i = 0; i < count; i++)
                        catalogs << String.valueOf(i)
                return catalogs
        }

        @Override
        DbType getType() {
                DbType.redis
        }

        @Override
        protected Dialect createDialect() {
                return null
        }

        @Override
        String showCreateTable(Session session, String table) {
                return null
        }

        @Override
        List<Table> getTables(Session session) {
                return null
        }

        @Override
        List<Index> getIndexes(Session session, String table) {
                return null
        }

        @Override
        Set<String> getIndexTypes() {
                return null
        }

        @Override
        void dropTable(Session session, String table) {

        }

        @Override
        void dropColumns(Session session, String table, Collection<Column> columns) {

        }

        @Override
        void dropIndexKeys(Session session, String table, Collection<Index> selectionItems) {

        }

        @Override
        void dropPrimaryKey(Session session, String table) {

        }

        @Override
        void addPrimaryKey(Session session, String table, Collection<Column> primaryKeys) {

        }

        @Override
        void alterIndexKeys(Session session, String table, Collection<Index> indexes) {

        }

        @Override
        void alterChange(Session session, String table, Collection<Column> columns) {

        }

        @Override
        void alterVisible(Session session, String table, Collection<Index> indexes) {

        }

}
