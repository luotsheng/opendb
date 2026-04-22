package com.changhong.openvdb.app.event.workbench;

import com.changhong.openvdb.app.assets.Assets;
import com.changhong.openvdb.app.explorer.UICatalogNode;
import com.changhong.openvdb.app.explorer.UIConnectionNode;
import com.changhong.openvdb.app.workbench.ScriptEditor;
import com.changhong.openvdb.core.model.ScriptFile;
import javafx.scene.Node;
import javafx.scene.control.Tab;

import static com.changhong.utils.string.StaticLibrary.fmt;
import static com.changhong.utils.string.StaticLibrary.strnempty;

/**
 * 打开脚本编辑器
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
public class OpenScriptEditorPaneEvent extends OpenTabEvent
{
        private final UICatalogNode catalog;
        private final UIConnectionNode connection;
        private final ScriptFile scriptFile;

        public OpenScriptEditorPaneEvent(UICatalogNode owner, UIConnectionNode connection)
        {
                this(owner, connection, null);
        }

        public OpenScriptEditorPaneEvent(UICatalogNode owner, UIConnectionNode connection, ScriptFile scriptFile)
        {
                super(owner);
                this.catalog = owner;
                this.connection = connection;
                this.scriptFile = scriptFile;
        }

        @Override
        public String tabId()
        {
                if (scriptFile != null)
                        return fmt("%s@%s(%s)", scriptFile.getName(), catalog.getName(), connection.getName());
                return null;
        }

        @Override
        public Node createPane(Tab tab)
        {
                tab.setGraphic(Assets.use("sql"));

                tab.setOnCloseRequest(e -> {
                        if (tab.getContent() instanceof ScriptEditor editor)
                                editor.close();
                });

                ScriptEditor scriptEditor = new ScriptEditor(connection, scriptFile, tab);

                /* tabId 不为空时，重新设置 Tab 标题 */
                if (strnempty(tabId()))
                        tab.setText(tabId());

                return scriptEditor;
        }
}
