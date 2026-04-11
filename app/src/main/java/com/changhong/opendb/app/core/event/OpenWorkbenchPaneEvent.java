package com.changhong.opendb.app.core.event;

import com.changhong.opendb.app.ui.pane.BrowserPane;
import lombok.Getter;

/**
 * 在工作区打开面板事件
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
public class OpenWorkbenchPaneEvent extends Event
{
        @Getter
        private BrowserPane pane;

        public OpenWorkbenchPaneEvent(BrowserPane pane)
        {
                this.pane = pane;
        }
}
