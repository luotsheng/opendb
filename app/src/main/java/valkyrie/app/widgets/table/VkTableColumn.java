package valkyrie.app.widgets.table;

import javafx.scene.control.TableColumn;

/**
 * @author Luo Tiansheng
 * @since 2026/4/6
 */
public class VkTableColumn<S, T> extends TableColumn<S, T>
{
        public VkTableColumn(String name)
        {
                this(name, false);
        }

        public VkTableColumn(String name, boolean editable)
        {
                super(name);
                setEditable(editable);
        }
}
