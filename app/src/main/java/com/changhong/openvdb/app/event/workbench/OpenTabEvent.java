package com.changhong.openvdb.app.event.workbench;

import com.changhong.openvdb.app.event.bus.Event;
import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 * 在工作区打开新的标签页
 *
 * @author Luo Tiansheng
 * @since 2026/4/14
 */
public abstract class OpenTabEvent extends Event
{
        /**
         * Tab id
         */
        public abstract String tabId();
        /**
         * 创建对应面板
         */
        public abstract Node createPane(Tab tab);
}
