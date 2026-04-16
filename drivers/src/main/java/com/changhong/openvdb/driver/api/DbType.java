package com.changhong.openvdb.driver.api;

import lombok.Getter;

import static com.changhong.utils.string.StaticLibrary.lowercase;

/**
 * @author Luo Tiansheng
 * @since 2026/4/11
 */
public enum DbType
{
        mysql("MySQL"),
        dm("达梦数据库"),
        ;

        @Getter
        private final String alias;

        DbType(String alias)
        {
                this.alias = alias;
        }

        public static DbType of(String type)
        {
                return valueOf(lowercase(type));
        }
}
