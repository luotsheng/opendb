package valkyrie.app.event.workbench;

import valkyrie.app.event.bus.Event;

/**
 * 根据所属对象关闭在工作区的面板
 *
 * @author Luo Tiansheng
 * @since 2026/4/14
 */
public class CloseWorkbenchTabEvent extends Event
{
        private final Object owner;

        public CloseWorkbenchTabEvent(Object owner)
        {
                this.owner = owner;
        }

        /**
         * 所属对象（表示是谁打开的面板）
         */
        public Object owner()
        {
                return owner;
        }
}
