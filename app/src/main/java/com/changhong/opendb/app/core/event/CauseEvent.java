package com.changhong.opendb.app.core.event;

/**
 * 异常捕获事件
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
public class CauseEvent extends Event
{
        public final String message;

        public CauseEvent(Throwable e)
        {
                this.message = findCauseMessage(e);
        }

        private static String findCauseMessage(Throwable e) {
                Throwable root = e;

                while (root.getCause() != null) {
                        root = root.getCause();
                }

                return root.getMessage();
        }

}
