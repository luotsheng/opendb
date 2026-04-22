package valkyrie.app.widgets;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Luo Tiansheng
 * @since 2026/4/02
 */
@NoArgsConstructor
@AllArgsConstructor
public class VkCodeAreaCreateInfo
{
        public boolean enableCopy = true;
        public boolean enablePaste = true;
        public VkCodeArea.ShowingMenuListener showingMenuListener;
        public VkCodeArea.ConfigureContextMenuHandler contextMenuHandler;
}
