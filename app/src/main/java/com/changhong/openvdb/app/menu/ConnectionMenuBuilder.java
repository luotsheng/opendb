package com.changhong.openvdb.app.menu;

import com.changhong.openvdb.app.assets.Assets;
import com.changhong.openvdb.app.dialog.connection.CreateOrEditConnectionDialog;
import com.changhong.openvdb.driver.api.DbType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * @author Luo Tiansheng
 * @since 2026/3/25
 */
public class ConnectionMenuBuilder
{
        public static Menu buildMenu() {
                Menu newConnectionMenu = new Menu("新建连接");

                MenuItem mysqlItem = new MenuItem(DbType.mysql.getAlias());
                mysqlItem.setGraphic(Assets.use("mysql"));
                mysqlItem.setOnAction(e -> openConnectionDialog(DbType.mysql));

                MenuItem dmItem = new MenuItem(DbType.dm.getAlias());
                dmItem.setGraphic(Assets.use("dm2"));
                dmItem.setOnAction(e -> openConnectionDialog(DbType.dm));

                MenuItem redisItem = new MenuItem(DbType.redis.getAlias());
                redisItem.setGraphic(Assets.use("redis"));
                redisItem.setOnAction(e -> openConnectionDialog(DbType.redis));

                newConnectionMenu.getItems().addAll(mysqlItem, dmItem, redisItem);

                return newConnectionMenu;
        }

        public static ContextMenu buildContextMenu() {
                ContextMenu contextMenu = new ContextMenu();
                contextMenu.getItems().addAll(buildMenu().getItems());
                return contextMenu;
        }

        @SuppressWarnings("unused")
        private static void openConnectionDialog(DbType dbType) {
                new CreateOrEditConnectionDialog(dbType).showAndWait();
        }
}
