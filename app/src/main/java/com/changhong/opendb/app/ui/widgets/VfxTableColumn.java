package com.changhong.opendb.app.ui.widgets;

import javafx.scene.control.TableColumn;

/**
 * @author Luo Tiansheng
 * @since 2026/4/6
 */
public class VfxTableColumn<S, T> extends TableColumn<S, T>
{
        public VfxTableColumn(String name)
        {
                this(name, false);
        }

        public VfxTableColumn(String name, boolean editable)
        {
                super(name);
                setEditable(editable);
        }
}
