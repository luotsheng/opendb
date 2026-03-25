package com.changhong.openvdb;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * @author Luo Tiansheng
 * @since 2026/3/25
 */
@SuppressWarnings("FieldCanBeLocal")
public class Navigator extends VBox {

    private final TabPane tabPane = new TabPane();

    public Navigator() {
        setPadding(new Insets(0));
        setStyle("-fx-background-color: #f0f0f0;");

        tabPane.setStyle("""
            -fx-background-color: transparent;
            -fx-border-color: transparent;
            -fx-border-width: 0;
            -fx-background-insets: 0;
            -fx-padding: 0;
        """);

        tabPane.setPadding(new Insets(0));

        Tab navigatorTab = new Tab("连接管理");
        navigatorTab.setClosable(false);

        Tab projectTab = new Tab("项目管理");
        projectTab.setClosable(false);

        tabPane.getTabs().addAll(navigatorTab, projectTab);

        getChildren().add(tabPane);
        setVgrow(tabPane, Priority.ALWAYS);
    }

}
