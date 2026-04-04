package com.changhong.opendb.app.driver;

import lombok.Data;

/**
 * 索引列数据
 *
 * @author Luo Tiansheng
 * @since 2026/4/4
 */
@Data
public class TableIndexColumn
{
        /**
         * 列名称
         */
        private String name;

        /**
         * 列顺序
         */
        private Integer order;

        /**
         * 前缀长度
         */
        private Integer prefixLength;
}
