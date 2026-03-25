package com.changhong.openvdb;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * @author Luo Tiansheng
 * @since 2026/3/25
 */
public class Workbench extends VBox {

    public Workbench() {
        setStyle("-fx-background-color: #ffffff;");
        getChildren().add(new Label("Workbench"));
    }

}
