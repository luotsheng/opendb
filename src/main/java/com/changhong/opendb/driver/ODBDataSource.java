package com.changhong.opendb.driver;

import com.changhong.opendb.core.event.EventBus;
import com.changhong.opendb.core.event.ExceptionEvent;
import com.changhong.opendb.model.ConnectionInfo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Luo Tiansheng
 * @since 2026/3/27
 */
public abstract class ODBDataSource implements DataSource, AutoCloseable
{
        private final HikariDataSource ds;

        public ODBDataSource(ConnectionInfo info)
        {
                HikariConfig conf = new HikariConfig();

                conf.setJdbcUrl(info.getJdbcUrl());
                conf.setUsername(info.getUsername());
                conf.setPassword(info.getPassword());

                conf.setMaximumPoolSize(16);
                conf.setMinimumIdle(1);
                conf.setConnectionTimeout(15000);

                ds = new HikariDataSource(conf);
        }

        /**
         * 获取数据库列表
         */
        public abstract List<String> getDatabases();

        /**
         * 获取表
         */
        public abstract List<String> getTables(String database);

        @Override
        public Connection getConnection() throws SQLException
        {
                return ds.getConnection();
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException
        {
                return ds.getConnection();
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException
        {
                return ds.getLogWriter();
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException
        {
                ds.setLogWriter(out);
        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException
        {
                ds.setLoginTimeout(seconds);
        }

        @Override
        public int getLoginTimeout() throws SQLException
        {
                return ds.getLoginTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException
        {
                return ds.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException
        {
                return ds.isWrapperFor(iface);
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException
        {
                return ds.getParentLogger();
        }

        @Override
        public void close() throws Exception
        {
                ds.close();
        }
}
