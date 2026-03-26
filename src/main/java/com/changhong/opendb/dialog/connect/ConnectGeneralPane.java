package com.changhong.opendb.dialog.connect;

import com.changhong.opendb.dialog.connect.layout.ConnectGridPane;
import com.changhong.opendb.widgets.PropertyGridPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
class ConnectGeneralPane extends PropertyGridPane
{
        private final TextField connectName = new TextField("本地数据库");
        private final TextField hostAddress = new TextField("127.0.0.1");
        private final TextField port = new TextField("3306");
        private final TextField username = new TextField("root");
        private final PasswordField password = new PasswordField();
        private final CheckBox savePassword = new CheckBox("保存密码");

        public ConnectGeneralPane()
        {
                super();
                setupPaneLayout();
        }

        private void setupPaneLayout()
        {
                addRow("连接名称：", connectName);
                addRow(null, new Label()); /* separator */
                addRow("主机地址：", hostAddress);
                addRow("端口号：", port);
                addRow("用户名：", username);
                addRow("密码：", password);
                addRow(null, new Label()); /* separator */
                addRow(null, savePassword);
        }

}
