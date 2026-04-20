package com.changhong.openvdb.driver.redis

import com.changhong.openvdb.driver.api.CloseableDataSource
import com.changhong.openvdb.driver.api.ConnectionConfig
import lombok.Getter
import redis.clients.jedis.Jedis

import java.sql.Connection
import java.sql.SQLException
import java.sql.SQLFeatureNotSupportedException
import java.util.logging.Logger

import static com.changhong.utils.string.StaticLibrary.strnempty

/**
 * @author Luo Tiansheng
 * @since 2026/4/20
 */
class RedisDataSource implements CloseableDataSource {

        private final Jedis jedis

        RedisDataSource(ConnectionConfig config) {
                jedis = new Jedis(config.getHost(), Integer.valueOf(config.getPort()))

                if (strnempty(config.password))
                        jedis.auth(config.password)

                jedis.ping()
        }

        @Override
        void close() throws Exception {
                jedis.close()
        }

        Jedis getJedis() {
                return jedis
        }

        /////////////////////////////////////////////////////////////////////////////
        ///                                JDBC                                   ///
        /////////////////////////////////////////////////////////////////////////////

        @Override
        Connection getConnection() throws SQLException {
                throw new UnsupportedOperationException()
        }

        @Override
        Connection getConnection(String username, String password) throws SQLException {
                throw new UnsupportedOperationException()
        }

        @Override
        PrintWriter getLogWriter() throws SQLException {
                throw new UnsupportedOperationException()
        }

        @Override
        void setLogWriter(PrintWriter out) throws SQLException {
                throw new UnsupportedOperationException()
        }

        @Override
        void setLoginTimeout(int seconds) throws SQLException {
                throw new UnsupportedOperationException()
        }

        @Override
        int getLoginTimeout() throws SQLException {
                throw new UnsupportedOperationException()
        }

        @Override
        def <T> T unwrap(Class<T> iface) throws SQLException {
                throw new UnsupportedOperationException()
        }

        @Override
        boolean isWrapperFor(Class<?> iface) throws SQLException {
                throw new UnsupportedOperationException()
        }

        @Override
        Logger getParentLogger() throws SQLFeatureNotSupportedException {
                throw new UnsupportedOperationException()
        }
}
