package com.changhong.opendb.core.event;

import com.changhong.opendb.model.ConnectionInfo;

/**
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
public class NewQueryScriptEvent extends Event
{
        public ConnectionInfo info;

        public NewQueryScriptEvent(ConnectionInfo info)
        {
                this.info = info;
        }
}
