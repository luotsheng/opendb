package com.changhong.openvdb.app.event.workbench;

import com.changhong.openvdb.app.event.bus.Event;
import javafx.scene.Node;
import lombok.Getter;

/**
 * 在工作区导航页 Tab 关闭面板事件
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
@Getter
public class CloseNavigationPaneEvent extends Event
{
        private final Object owner;

        public CloseNavigationPaneEvent(Object owner)
        {
                this.owner = owner;
        }
}
