package com.changhong.opendb.bus.event;

import com.changhong.opendb.bus.Event;

/**
 * 异常捕获事件
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
public class CatcherEvent extends Event
{
        public final String message;

        public CatcherEvent(String message)
        {
                super(null);
                this.message = message;
        }
}
