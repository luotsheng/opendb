package com.changhong.openvdb.app.event.workbench;

import com.changhong.openvdb.app.assets.Assets;
import com.changhong.openvdb.app.explorer.UIConnectionNode;
import com.changhong.openvdb.app.workbench.ScriptEditor;
import com.changhong.openvdb.core.model.ScriptFile;
import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 * 打开脚本编辑器
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
public class OpenScriptEditorPaneEvent extends OpenTabEvent
{
        private final UIConnectionNode connection;
        private final ScriptFile scriptFile;

        public OpenScriptEditorPaneEvent(Object owner, UIConnectionNode connection)
        {
                this(owner, connection, null);
        }

        public OpenScriptEditorPaneEvent(Object owner, UIConnectionNode connection, ScriptFile scriptFile)
        {
                super(owner);
                this.connection = connection;
                this.scriptFile = scriptFile;
        }

        @Override
        public String tabId()
        {
                // 由于脚本编辑器需要控制 Tab 标题，以及标记未保存状态，
                // 所以打开事件不指定 Tab id，由脚本编辑器内部自己管理。
                return "";
        }

        @Override
        public Node createPane(Tab tab)
        {
                tab.setGraphic(Assets.use("sql"));

                tab.setOnCloseRequest(e -> {
                        if (tab.getContent() instanceof ScriptEditor editor)
                                editor.close();
                });

                return new ScriptEditor(connection, scriptFile, tab);
        }
}
