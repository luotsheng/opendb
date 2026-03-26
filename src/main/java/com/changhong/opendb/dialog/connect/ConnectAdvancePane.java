package com.changhong.opendb.dialog.connect;

import com.changhong.opendb.widgets.PropertyGridPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
class ConnectAdvancePane extends PropertyGridPane
{
        private final TextField jdbcUrl = new TextField("jdbc:mysql://");
        private final ComboBox<String> timezone = new ComboBox<>();
        private final CheckBox useSSL = new CheckBox("使用 SSL");

        private static final String[] TIMEZONES = new String[]{
                "UTC",
                "Asia/Shanghai",
                "Asia/Hong_Kong",
                "Asia/Singapore",
                "Asia/Seoul",
                "Asia/Bangkok",
                "Asia/Dubai",
                "Europe/London",
                "Europe/Berlin",
                "Europe/Paris",
                "America/New_York",
                "America/Los_Angeles",
                "America/Chicago",
                "Australia/Sydney",
                "Asia/Tokyo",
        };

        public ConnectAdvancePane()
        {
                super();
                setupTimezone();
                setupPaneLayout();
        }

        private void setupTimezone()
        {
                timezone.setMaxWidth(Double.MAX_VALUE);
                timezone.getItems().addAll(TIMEZONES);
                timezone.getSelectionModel().select(1);
        }

        private void setupPaneLayout()
        {
                addRow("JDBC URL：", jdbcUrl);
                addRow("时区：", timezone);
                addRow(null, useSSL);
        }
}
