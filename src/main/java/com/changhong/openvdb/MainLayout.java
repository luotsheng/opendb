package com.changhong.openvdb;

import javafx.scene.control.SplitPane;

/**
 * @author Luo Tiansheng
 * @since 2026/3/25
 */
@SuppressWarnings("FieldCanBeLocal")
public class MainLayout extends SplitPane {
    public MainLayout() {
        this.getItems().addAll(
                new Navigator(),
                new Workbench()
        );
        this.setDividerPositions(0.3);
    }
}
