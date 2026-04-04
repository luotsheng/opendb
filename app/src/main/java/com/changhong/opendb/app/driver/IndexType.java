package com.changhong.opendb.app.driver;

/**
 * 索引类型
 *
 * @author Luo Tiansheng
 * @since 2026/4/4
 */
public enum IndexType
{
        /**
         * B-Tree 索引
         * MySQL 默认索引类型
         * 支持范围查询、排序、前缀匹配
         */
        BTREE,

        /**
         * 哈希索引
         * 只支持等值查询 (=)
         * 不支持范围查询
         * 主要用于 MEMORY 引擎
         */
        HASH,

        /**
         * 全文索引
         * 用于全文搜索
         * 支持 MATCH ... AGAINST 语法
         */
        FULLTEXT,

        /**
         * R-Tree 索引
         * 用于空间数据类型
         * 支持 GIS 空间查询
         */
        RTREE
}
