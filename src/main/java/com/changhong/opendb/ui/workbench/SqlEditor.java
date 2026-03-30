package com.changhong.opendb.ui.workbench;

import com.changhong.opendb.model.ODBNStatus;
import com.changhong.opendb.ui.navigator.node.ODBNConnection;
import com.changhong.opendb.ui.navigator.node.ODBNDatabase;
import com.changhong.opendb.ui.widgets.VFX;
import com.changhong.opendb.ui.widgets.VSeparator;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

/**
 * @author Luo Tiansheng
 * @since 2026/3/29
 */
public class SqlEditor extends BorderPane
{
        private final ToolBar toolBar;
        private final TextArea textArea;

        public SqlEditor()
        {
                toolBar = new ToolBar();
                textArea = new TextArea();

                setupToolbar();
                setupTextArea();

                setTop(toolBar);
                setCenter(textArea);
        }

        public void setupToolbar()
        {
                ODBNStatus instance = ODBNStatus.getInstance();
                ODBNConnection selectedConnection = instance.getSelectedConnection();

                Button run = VFX.newIconButton("运行 SQL", "run0");
                run.setText("运行");

                ComboBox<ODBNConnection> connection = newConnectionComboBox();
                ComboBox<ODBNDatabase> database = newDatabaseComboBox();

                // setup combo
                connection.getItems().addAll(instance.getConnections());

                if (selectedConnection != null) {
                        connection.getSelectionModel().select(selectedConnection);
                        database.getItems().addAll(selectedConnection.getDatabases());
                        database.getSelectionModel().select(selectedConnection.getSelectedDatabase());
                }

                toolBar.getItems().addAll(
                        connection,
                        database,
                        new VSeparator(),
                        run);

        }

        public void setupTextArea()
        {
                /* DO NOTHING */
        }

        private static ComboBox<ODBNDatabase> newDatabaseComboBox()
        {
                ComboBox<ODBNDatabase> database = new ComboBox<>();
                database.setPrefWidth(200);
                database.setConverter(new StringConverter<>()
                {
                        @Override
                        public String toString(ODBNDatabase database)
                        {
                                return database.getName();
                        }

                        @Override
                        public ODBNDatabase fromString(String s)
                        {
                                return null;
                        }
                });
                return database;
        }

        private static ComboBox<ODBNConnection> newConnectionComboBox()
        {
                ComboBox<ODBNConnection> connection = new ComboBox<>();
                connection.setPrefWidth(200);
                connection.setConverter(new StringConverter<>()
                {
                        @Override
                        public String toString(ODBNConnection connection)
                        {
                                return connection.getName();
                        }

                        @Override
                        public ODBNConnection fromString(String s)
                        {
                                return null;
                        }
                });
                return connection;
        }
}
