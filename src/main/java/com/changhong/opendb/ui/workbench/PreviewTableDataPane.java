package com.changhong.opendb.ui.workbench;

import com.changhong.opendb.driver.JdbcTemplate;
import com.changhong.opendb.driver.QueryResultSet;
import com.changhong.opendb.driver.TableInfo;
import com.changhong.opendb.resource.Assets;
import com.changhong.opendb.utils.Catcher;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

/**
 * @author Luo Tiansheng
 * @since 2026/3/30
 */
@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
public class PreviewTableDataPane extends BorderPane
{
        private int start = 0;
        private int size = 1024;
        private Node oldGraphic;

        private final Tab ownerTab;
        private final JdbcTemplate jdbcTemplate;
        private final String database;
        private final TableInfo tableInfo;
        private final ResultSetViewPane resultSetViewPane;

        public PreviewTableDataPane(Tab ownerTab,
                                    JdbcTemplate jdbcTemplate,
                                    String database,
                                    TableInfo tableInfo)
        {
                this.ownerTab = ownerTab;
                this.jdbcTemplate = jdbcTemplate;
                this.database = database;
                this.tableInfo = tableInfo;
                this.resultSetViewPane = new ResultSetViewPane();

                setCenter(resultSetViewPane);
        }

        private void setLoadingIndicator()
        {
                oldGraphic = ownerTab.getGraphic();
                ownerTab.setGraphic(Assets.newProgressIndicator());
        }

        private void removeLoadingIndicator()
        {
                ownerTab.setGraphic(oldGraphic);
        }


        public void asyncUpdate()
        {
                setLoadingIndicator();

                new Thread(() -> {
                        try {
                                QueryResultSet rs = jdbcTemplate.select(
                                        database,
                                        tableInfo,
                                        start,
                                        size);
                                Platform.runLater(() -> resultSetViewPane.refresh(rs));
                        } catch (Exception e) {
                                Catcher.ithrow(e);
                        } finally {
                                Platform.runLater(this::removeLoadingIndicator);
                        }
                }).start();
        }
}
