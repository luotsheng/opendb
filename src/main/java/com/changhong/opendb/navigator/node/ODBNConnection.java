package com.changhong.opendb.navigator.node;

import com.changhong.opendb.core.event.EventBus;
import com.changhong.opendb.driver.MySQLDataSource;
import com.changhong.opendb.driver.ODBDataSource;
import com.changhong.opendb.model.ConnectionInfo;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

import java.util.List;

/**
 * @author Luo Tiansheng
 * @since 2026/3/25
 */
@SuppressWarnings("FieldCanBeLocal")
public class ODBNConnection extends ODBNode
{
        private final ConnectionInfo info;
        private boolean openFlag = false;
        private ODBDataSource ds;

        public ODBNConnection(ConnectionInfo info)
        {
                super(info.getName());
                this.info = info;
        }

        @Override
        public void onMouseDoubleClickEvent(MouseEvent event)
        {
                if (openFlag)
                        return;

                try {
                        ds = new MySQLDataSource(info);
                        openFlag = true;
                        setupDatabases(ds.getDatabases());
                } catch (Exception e) {
                        EventBus.publish(e);
                }
        }

        private void setupDatabases(List<String> databases)
        {
                for (String database : databases)
                        getChildren().add(new TreeItem<>(database));
        }

        @Override
        public String getName()
        {
                return info.getName();
        }
}
