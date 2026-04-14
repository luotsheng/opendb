package com.changhong.openvdb.app.event;

import com.changhong.openvdb.app.event.bus.Event;
import javafx.scene.Node;
import lombok.Getter;

/**
 * 在工作区打开面板事件
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
@Getter
public class OpenWorkbenchPaneEvent extends Event
{
        private final Node pane;

        public OpenWorkbenchPaneEvent(Node pane)
        {
                this.pane = pane;
        }
}
