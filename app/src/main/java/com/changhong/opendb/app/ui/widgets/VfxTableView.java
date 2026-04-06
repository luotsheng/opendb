package com.changhong.opendb.app.ui.widgets;


import javafx.scene.control.TableView;

/**
 * @author Luo Tiansheng
 * @since 2026/4/6
 */
public class VfxTableView<S> extends TableView<S>
{
        public VfxTableView()
        {
                getStyleClass().add("vfx-table-view");
                setFixedCellSize(26);
        }
}
