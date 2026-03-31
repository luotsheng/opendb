package com.changhong.opendb.ui.workbench;

import com.changhong.opendb.driver.QueryResultSet;
import com.changhong.opendb.ui.widgets.VFX;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.util.List;

/**
 * @author Luo Tiansheng
 * @since 2026/3/30
 */
@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
public class ResultSetViewPane extends BorderPane
{
        private TableView<List<String>> tableView = VFX.newTableView();

        public ResultSetViewPane()
        {
                setCenter(tableView);
        }

        public void refresh(QueryResultSet qrs)
        {
                tableView.getColumns().clear();
                tableView.getItems().clear();

                for (int i = 0; i < qrs.getColumns().size(); i++)
                {
                        int index = i;

                        String colText = qrs.getColumns().get(i);

                        TableColumn<List<String>, String> col =
                                new TableColumn<>(colText);

                        col.setPrefWidth(calcColWidth(colText, qrs.getRows(), i));
                        col.setMaxWidth(1000);
                        col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(index)));

                        tableView.getColumns().add(col);
                }

                tableView.setItems(
                        FXCollections.observableArrayList(qrs.getRows())
                );
        }

        private static int calcColWidth(String colText, List<List<String>> values, int index)
        {
                int V = 12, MAX = 200;
                int CM = colText.length();
                int CW = CM * V;

                for (List<String> value : values) {
                        String cellValue = value.get(index);

                        if (cellValue == null || cellValue.isEmpty())
                                continue;

                        if (cellValue.length() > CM)
                                CM = cellValue.length();
                }

                int FW = Math.max(CM * V, 64);

                return Math.min(Math.max(CW, FW), MAX); /* px */
        }

}
