package valkyrie.app.pane;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import static valkyrie.utils.string.StaticLibrary.fmt;

/**
 * @author Luo Tiansheng
 * @since 2026/4/2
 */
public class SqlMessagePane extends VirtualizedScrollPane<CodeArea>
{
        private final CodeArea codeArea;

        public SqlMessagePane()
        {
                super(new CodeArea());

                codeArea = getContent();
                codeArea.setEditable(false);

                setupContextMenu();
        }

        private void setupContextMenu()
        {

        }

        private void clearAll()
        {
                codeArea.replaceText("");
        }

        public void appendExecute(String text)
        {
                appendText("> Execute");
                appendText(text);
        }

        public void appendExecuteQuery(String text)
        {
                appendText("> Query");
                appendText(text);
        }

        public void appendExecuteUpdate(String text)
        {
                appendText("> Update");
                appendText(text);
        }

        public void appendRow(int value)
        {
                appendText("Row: " + value);
        }

        public void appendCost(long cost)
        {
                appendText("Time: " + cost + "ms");
        }

        public void appendError(String message)
        {
                appendText("Error: " + message.replaceAll("\n", ""));
        }

        private void appendText(String text)
        {
                text = text.strip();
                codeArea.appendText(text + "\n");
                codeArea.moveTo(codeArea.getLength());
                codeArea.requestFollowCaret();
        }

}
