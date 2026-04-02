package com.changhong.opendb.ui.workbench;

import javafx.scene.layout.BorderPane;
import org.fxmisc.richtext.CodeArea;

/**
 * @author Luo Tiansheng
 * @since 2026/4/2
 */
public class SqlMessagePane extends BorderPane
{
        private final CodeArea codeArea = new CodeArea();

        public SqlMessagePane()
        {
                codeArea.setEditable(false);
                setCenter(codeArea);
        }

        public void appendInfo(String text)
        {
                codeArea.appendText("[  OK  ] " + text + "\n");
        }

        public void appendError(String text)
        {
                codeArea.appendText("[ ERROR ]" + text + "\n");
        }
}
