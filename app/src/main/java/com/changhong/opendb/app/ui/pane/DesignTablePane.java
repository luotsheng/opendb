package com.changhong.opendb.app.ui.pane;

import atlantafx.base.util.IntegerStringConverter;
import com.changhong.opendb.app.driver.ColumnMetaData;
import com.changhong.opendb.app.driver.TableMetaData;
import com.changhong.opendb.app.resource.Assets;
import com.changhong.opendb.app.ui.widgets.VStringEditingTableCell;
import com.changhong.opendb.app.ui.widgets.VCheckBoxTableCell;
import com.changhong.opendb.app.ui.widgets.VFX;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

import java.util.List;

/**
 * @author Luo Tiansheng
 * @since 2026/4/3
 */
@SuppressWarnings({"FieldCanBeLocal", "unchecked"})
public class DesignTablePane extends DetailPane
{
        private final TableMetaData tableMetaData;
        private final List<ColumnMetaData> columnMetaDatas;
        private final TableView<ColumnMetaData> structureView = VFX.newTableView();
        private final ToolBar toolBar = new ToolBar();
        private final TabPane tabPane = new TabPane();

        public DesignTablePane(TableMetaData tableMetaData, List<ColumnMetaData> columnMetaDatas)
        {
                this.tableMetaData = tableMetaData;
                this.columnMetaDatas = columnMetaDatas;

                setupToolBar();
                setupStructureView();

                Tab tableStruct = new Tab("表结构");
                tableStruct.setClosable(false);
                tableStruct.setGraphic(Assets.use("struct1"));

                BorderPane borderPane = new BorderPane();
                borderPane.setCenter(structureView);
                tableStruct.setContent(borderPane);

                Tab indexStruct = new Tab("索引");
                indexStruct.setClosable(false);
                indexStruct.setGraphic(Assets.use("index0"));

                tabPane.getTabs().addAll(
                        tableStruct,
                        indexStruct
                );

                setTop(toolBar);
                setCenter(tabPane);
        }

        private void setupToolBar()
        {
                Button save = VFX.newIconButton("保存", "storage");
                save.setOnAction(e -> applySave());

                Button plus = VFX.newIconButton("新增行", "plus");
                plus.setOnAction(e -> applyPlus());

                Button minus = VFX.newIconButton("删除行", "minus");
                minus.setOnAction(e -> applyMinus());

                toolBar.getItems().addAll(
                        save,
                        plus,
                        minus
                );
        }

        private void applySave()
        {

        }

        private void applyPlus()
        {
                structureView.getItems().add(new ColumnMetaData());
                structureView.refresh();
        }

        private void applyMinus()
        {
        }

        private void setupStructureView()
        {
                structureView.setEditable(true);
                structureView.getSelectionModel().setCellSelectionEnabled(true);
                structureView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                // 列
                TableColumn<ColumnMetaData, String> name = VFX.newEditableTableColumn("名称");
                TableColumn<ColumnMetaData, String> type = VFX.newEditableTableColumn("类型");
                TableColumn<ColumnMetaData, Integer> length = VFX.newEditableTableColumn("长度");
                TableColumn<ColumnMetaData, Integer> scale = VFX.newEditableTableColumn("小数位");
                TableColumn<ColumnMetaData, String> defaultValue = VFX.newEditableTableColumn("默认值");
                TableColumn<ColumnMetaData, Boolean> nullable = VFX.newEditableTableColumn("是否允许 NULL");
                TableColumn<ColumnMetaData, Boolean> primary = VFX.newEditableTableColumn("主键");
                TableColumn<ColumnMetaData, Boolean> autoIncrement = VFX.newEditableTableColumn("是否自增");
                TableColumn<ColumnMetaData, String> comment = VFX.newEditableTableColumn("注释");

                name.setCellValueFactory(new PropertyValueFactory<>("name"));
                type.setCellValueFactory(new PropertyValueFactory<>("type"));
                length.setCellValueFactory(new PropertyValueFactory<>("length"));
                scale.setCellValueFactory(new PropertyValueFactory<>("scale"));
                defaultValue.setCellValueFactory(new PropertyValueFactory<>("defaultValue"));
                nullable.setCellValueFactory(new PropertyValueFactory<>("nullable"));
                primary.setCellValueFactory(new PropertyValueFactory<>("primary"));
                autoIncrement.setCellValueFactory(new PropertyValueFactory<>("autoIncrement"));
                comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

                name.setCellFactory(c -> new VStringEditingTableCell<>());
                type.setCellFactory(c -> new VStringEditingTableCell<>());
                defaultValue.setCellFactory(c -> new VStringEditingTableCell<>());
                length.setCellFactory(c -> new TextFieldTableCell<>(new IntegerStringConverter()));
                scale.setCellFactory(c -> new TextFieldTableCell<>(new IntegerStringConverter()));
                comment.setCellFactory(c -> new VStringEditingTableCell<>());

                nullable.setCellFactory(c -> new VCheckBoxTableCell<>());
                primary.setCellFactory(c -> new VCheckBoxTableCell<>());
                autoIncrement.setCellFactory(c -> new VCheckBoxTableCell<>());

                // 初始化宽度
                name.setPrefWidth(150);
                type.setPrefWidth(100);
                length.setPrefWidth(100);
                scale.setPrefWidth(100);
                defaultValue.setPrefWidth(200);
                nullable.setPrefWidth(120);
                primary.setPrefWidth(100);
                autoIncrement.setPrefWidth(100);
                comment.setPrefWidth(200);

                structureView.getColumns().addAll(
                        name,
                        type,
                        length,
                        scale,
                        nullable,
                        primary,
                        autoIncrement,
                        defaultValue,
                        comment
                );

                structureView.getItems().addAll(FXCollections.observableArrayList(columnMetaDatas));
        }
}
