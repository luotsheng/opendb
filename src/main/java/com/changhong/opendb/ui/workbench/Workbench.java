package com.changhong.opendb.ui.workbench;

import com.changhong.opendb.core.event.*;
import com.changhong.opendb.model.ConnectionInfo;
import com.changhong.opendb.ui.widgets.VFX;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.changhong.opendb.utils.StringUtils.strfmt;

/**
 * @author Luo Tiansheng
 * @since 2026/3/25
 */
@SuppressWarnings("FieldCanBeLocal")
public class Workbench extends VBox implements EventListener
{
        private final TabPane tabPane = VFX.newTabPane();
        private final Tab detailTab = new Tab("详情");
        private final List<SqlEditor> editors = new ArrayList<>();
        private final Map<String, Tab> queryResultTab = new HashMap<>();

        public Workbench()
        {
                setStyle("-fx-background-color: #ffffff;");
                getChildren().add(tabPane);
                VBox.setVgrow(tabPane, Priority.ALWAYS);
                setupDetailTab();

                // 订阅事件
                EventBus.subscribe(OpenWorkbenchPaneEvent.class, this);
                EventBus.subscribe(CloseWorkbenchPaneEvent.class, this);
                EventBus.subscribe(NewQueryScriptEvent.class, this);
                EventBus.subscribe(NewQueryResultSetPaneEvent.class, this);
                EventBus.subscribe(RemoveSqlEditorTabEvent.class, this);
        }

        private void setupDetailTab()
        {
                detailTab.setClosable(false);
                tabPane.getTabs().add(detailTab);
        }

        static int idx = 0;

        private static String queryName(ConnectionInfo info)
        {
                return strfmt("查询脚本@%s_%d.sql", info == null ? "[ N/A ]" : info.getName(), (idx++));
        }

        @Override
        public void onEvent(Event event)
        {
                switch (event) {
                        case OpenWorkbenchPaneEvent e      -> handleOpenWorkbenchPaneEvent(e);
                        case CloseWorkbenchPaneEvent e     -> handleCloseWorkbenchPaneEvent(e);
                        case RemoveSqlEditorTabEvent e     -> handleRemoveSqlEditorTabEvent(e);
                        case NewQueryScriptEvent e         -> handleNewQueryScriptEvent(e);
                        case NewQueryResultSetPaneEvent e  -> handleNewQueryResultSetPaneEvent(e);
                        default -> {}
                }
        }

        private void handleOpenWorkbenchPaneEvent(OpenWorkbenchPaneEvent event)
        {
                detailTab.setContent(event.getPane());
        }

        @SuppressWarnings("unused")
        private void handleCloseWorkbenchPaneEvent(CloseWorkbenchPaneEvent event)
        {
                if (detailTab.getContent() == event.getPane())
                        detailTab.setContent(null);
        }

        private void handleRemoveSqlEditorTabEvent(RemoveSqlEditorTabEvent event)
        {
                for (SqlEditor editor : editors) {
                        if (editor.sqlFileEquals(event.sqlFile))
                                tabPane.getTabs().remove(editor.getOwnerTab());
                }
        }

        private void handleNewQueryScriptEvent(NewQueryScriptEvent event)
        {
                Tab queryTab = new Tab();
                ConnectionInfo info = event.connectionInfo;
                SqlEditor sqlEditor;

                if (event.queryInfo != null) {
                        sqlEditor = new SqlEditor(event.queryInfo, queryTab);
                } else {
                        sqlEditor = new SqlEditor(queryName(info), queryTab);
                }

                queryTab.setContent(sqlEditor);
                editors.add(sqlEditor);
                tabPane.getTabs().add(queryTab);
                tabPane.getSelectionModel().select(queryTab);
        }

        private void handleNewQueryResultSetPaneEvent(NewQueryResultSetPaneEvent event)
        {
                PreviewTableDataPane pane = new PreviewTableDataPane(
                        event.jdbcTemplate,
                        event.database,
                        event.info
                );

                String id = strfmt("%s@%s (%s)",
                        event.info.getName(),
                        event.database,
                        event.jdbcTemplate.getConnectionName());

                if (queryResultTab.containsKey(id)) {
                        Tab tab = queryResultTab.get(id);
                        tabPane.getSelectionModel().select(tab);
                        return;
                }

                Tab tab = new Tab(id);
                tab.setContent(pane);
                tab.setOnCloseRequest(closeEvent -> {
                        Tab closeTab = (Tab) closeEvent.getTarget();
                        queryResultTab.remove(closeTab.getText());
                });

                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
                queryResultTab.put(id, tab);
        }
}
