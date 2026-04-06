package com.changhong.opendb.app.ui.widgets.table;

import lombok.Setter;

/**
 * @author Luo Tiansheng
 * @since 2026/4/6
 */
public class VFXTableColumnFactory<S>
{
        public interface EditCommitEventListener
        {
                void onCommit(int row, int col, Object newVal);
        }

        @Setter
        private EditCommitEventListener onEditCommitEventListener;

        public VFXTableColumnFactory()
        {
        }

        public <T> VFXTableColumn<S, T> createEditableColumn(String name)
        {
                VFXTableColumn<S, T> c = new VFXTableColumn<>(name, true);

                c.setOnEditCommit(event -> {
                        if (onEditCommitEventListener != null) {
                                onEditCommitEventListener.onCommit(
                                        event.getTablePosition().getRow(),
                                        event.getTablePosition().getColumn(),
                                        event.getNewValue()
                                );
                        }
                });

                return c;
        }
}
