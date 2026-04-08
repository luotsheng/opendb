package com.changhong.opendb.app.core.event;

import com.changhong.opendb.app.utils.Causes;

/**
 * 异常捕获事件
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
public class ThrowableEvent extends Event
{
        public final String message;

        public ThrowableEvent(Throwable e)
        {
                this.message = Causes.message(e);
        }

}
