package com.changhong.opendb.app.ui.widgets.table;

import javafx.scene.control.TableColumn;

/**
 * @author Luo Tiansheng
 * @since 2026/4/6
 */
public class VFXTableColumnFactory<S>
{
        public interface EditCommitEventCallback<S>
        {
                void onCommit(TableColumn.CellEditEvent<S, ?> event);
        }

        private final EditCommitEventCallback<S> callback;

        public VFXTableColumnFactory(EditCommitEventCallback<S> callback)
        {
                this.callback = callback;
        }

        public <T> VFXTableColumn<S, T> createEditableColumn(String name)
        {
                VFXTableColumn<S, T> c = new VFXTableColumn<>(name, true);

                c.setOnEditCommit(callback::onCommit);

                return c;
        }
}
