package com.changhong.opendb.driver;

import com.changhong.opendb.core.event.EventBus;
import com.changhong.opendb.model.ConnectionInfo;
import com.changhong.opendb.utils.Catcher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luo Tiansheng
 * @since 2026/3/27
 */
@SuppressWarnings("SqlNoDataSourceInspection")
public class MySQLDataSource extends ODBDataSource
{
        public MySQLDataSource(ConnectionInfo info)
        {
                super(info);
        }

        @Override
        public List<String> getDatabases()
        {
                String sql = "SHOW DATABASES;";

                try (Connection connection = getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(sql)) {
                        List<String> ret = new ArrayList<>();

                        while (resultSet.next())
                                ret.add(resultSet.getString(1));

                        return ret;
                } catch (SQLException e) {
                        EventBus.publish(e);
                }

                return List.of();
        }

        @Override
        public List<String> getTables(String database)
        {
                return List.of();
        }
}
