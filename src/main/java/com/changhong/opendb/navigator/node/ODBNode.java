package com.changhong.opendb.navigator.node;

import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

/**
 * @author Luo Tiansheng
 * @since 2026/3/25
 */

public abstract class ODBNode extends TreeItem<String>
{
        public ODBNode(String name)
        {
                super(name);
        }

        /**
         * 鼠标双击事件
         */
        public abstract void onMouseDoubleClickEvent(MouseEvent event);

        /**
         * 节点名称
         */
        public abstract String getName();

}
