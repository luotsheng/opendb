package valkyrie.app.widgets;

import javafx.geometry.Bounds;
import javafx.scene.control.ListView;
import javafx.stage.Popup;
import org.fxmisc.richtext.CodeArea;
import valkyrie.app.workbench.SqlKeyWordDefine;

import java.util.List;

import static valkyrie.utils.string.StaticLibrary.lowercase;

/**
 * 代码提示类
 *
 * @author Luo Tiansheng
 * @since 2026/4/22
 */
public class VkCodeCompletionPopup
{
        private final ListView<String> listView = new ListView<>();
        private final List<String> keywords = List.of(SqlKeyWordDefine.KEYWORDS);
        private final Popup popup = new Popup();

        public void hide()
        {
                popup.hide();
        }

        public void show(CodeArea area, String word)
        {
                Bounds bounds = area.getCaretBounds().orElse(null);

                if (bounds == null)
                        return;

                List<String> result = keywords.stream()
                        .filter(k -> k.startsWith(lowercase(word)))
                        .toList();

                listView.getItems().setAll(result);
                popup.show(area, bounds.getMaxX(), bounds.getMaxY());
        }

}
