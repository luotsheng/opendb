package com.changhong.openvdb.driver.dm;

import static com.changhong.utils.string.StaticLibrary.uppercase;

/**
 * @author Luo Tiansheng
 * @since 2026/4/17
 */
public enum DMIndexType
{
        NORMAL,
        UNIQUE,
        BITMAP,
        ;

        public static DMIndexType of(String type)
        {
                return valueOf(uppercase(type));
        }

        public boolean equals(String name)
        {
                return of(name) == this;
        }

}
