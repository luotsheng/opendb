package valkyrie.app.widgets;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * 状态栏
 *
 * @author Luo Tiansheng
 * @since 2026/5/18
 */
public class VkStatusBar extends BorderPane
{
        private static final VkStatusBar INSTANCE = new VkStatusBar();

        private VkStatusBar()
        {
                getStyleClass().add("vk-status-bar");
                updateMessage("SELECT 1");
        }

        public static VkStatusBar getInstance()
        {
                return INSTANCE;
        }

        public void updateMessage(String message)
        {
                setCenter(new Label(message));
        }
}
