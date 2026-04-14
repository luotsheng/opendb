package com.changhong.openvdb.app.event.workbench;

import com.changhong.openvdb.app.assets.Assets;
import com.changhong.openvdb.app.pane.TableDesignerPane;
import com.changhong.openvdb.driver.api.Driver;
import com.changhong.openvdb.driver.api.Session;
import com.changhong.openvdb.driver.api.Table;
import javafx.scene.Node;
import javafx.scene.control.Tab;

import static com.changhong.utils.string.StaticLibrary.strfmt;

/**
 * 打开设计表面板事件
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
public class OpenTableDesignerPaneEvent extends OpenTabEvent
{
        private final String conn;
        private final Session session;
        private final Driver driver;
        private final Table table;

        public OpenTableDesignerPaneEvent(String conn, Session session, Driver driver, Table table)
        {
                this.conn = conn;
                this.session = session;
                this.driver = driver;
                this.table = table;
        }

        @Override
        public String tabId()
        {
                return strfmt("%s@%s(%s)", table.getName(), session.scope(), conn);
        }

        @Override
        public Node createPane(Tab tab)
        {
                TableDesignerPane pane = new TableDesignerPane(tab, session, driver, table);
                tab.setGraphic(Assets.use("struct1"));
                return pane;
        }
}
