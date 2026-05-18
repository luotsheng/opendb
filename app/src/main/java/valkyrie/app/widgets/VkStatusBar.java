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
                updateMessage("(STARTING)");
        }

        public static VkStatusBar getInstance()
        {
                return INSTANCE;
        }

        public void updateMessage(String message)
        {
                message = message.replaceAll("\n", "");

                if (message.length() > 128)
                        message = message.substring(0, 128) + "...";

                setCenter(new Label(message));
        }
}
