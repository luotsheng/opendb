package valkyrie.app.widgets;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * @author Luo Tiansheng
 * @since 2026/4/9
 */
public class VkComboBox<T> extends ComboBox<T>
{
        public VkComboBox()
        {
        }

        public VkComboBox(ObservableList<T> items)
        {
                super(items);
        }

        public VkComboBox<T> copyComboBox()
        {
                VkComboBox<T> dst = new VkComboBox<>();

                dst.getItems().addAll(this.getItems());
                dst.getSelectionModel().select(
                        this.getSelectionModel().getSelectedIndex());

                return dst;
        }
}
