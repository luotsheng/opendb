package valkyrie.app.widgets;

import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 * @author Luo Tiansheng
 * @since 2026/4/15
 */
public class VkTextField extends TextField
{
        public VkTextField()
        {
                super();
        }

        public VkTextField(String text)
        {
                super(text);
        }

        @Override
        public void requestFocus()
        {
                if (!isFocused())
                       Platform.runLater(super::requestFocus);
        }
}
