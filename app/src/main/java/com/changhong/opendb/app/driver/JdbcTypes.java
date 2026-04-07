package com.changhong.opendb.app.driver;

import java.sql.Types;

/**
 * Jdbc Type 工具类
 *
 * @author Luo Tiansheng
 * @since 2026/4/7
 */
public class JdbcTypes
{
        public static boolean isTime(int type)
        {
                return switch (type) {
                        case Types.DATE,
                             Types.TIME,
                             Types.TIMESTAMP,
                             Types.TIME_WITH_TIMEZONE,
                             Types.TIMESTAMP_WITH_TIMEZONE -> true;
                        default -> false;
                };
        }
}
