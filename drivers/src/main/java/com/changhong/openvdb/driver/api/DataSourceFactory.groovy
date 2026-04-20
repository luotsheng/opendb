package com.changhong.openvdb.driver.api

import com.changhong.openvdb.driver.redis.RedisDataSource

/**
 * @author Luo Tiansheng
 * @since 2026/4/20
 */
class DataSourceFactory {

        static CloseableDataSource getDataSource(ConnectionConfig config) {
                switch (config.type) {
                        case [DbType.mysql, DbType.dm]:
                                return new PooledDataSource(config)
                        case [DbType.redis]:
                                return new RedisDataSource(config)
                }
                null
        }

}
