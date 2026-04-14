package com.changhong.openvdb.app.event.bus;

import lombok.Getter;

import java.util.Date;

/**
 * 事件类
 *
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
@Getter
public abstract class Event
{
        protected boolean consume = false;

        private final Date createTime = new Date();

        /**
         * 消费事件
         */
        public void consume()
        {
                this.consume = true;
        }

        /**
         * 事件名称
         */
        public String name()
        {
                return this.getClass().getSimpleName();
        }
}
