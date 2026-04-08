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

        public static boolean isSupportLength(int jdbcType) {
                return switch (jdbcType) {
                        // 字符串类型 - 必须指定长度
                        case Types.CHAR, Types.VARCHAR, Types.NCHAR, Types.NVARCHAR, Types.BINARY, Types.VARBINARY ->
                                true;

                        // 精确数值 - 必须指定精度和小数位（长度指的是precision）
                        case Types.DECIMAL, Types.NUMERIC -> true;

                        // 整数类型 - 显示宽度已废弃，不建议指定长度
                        case Types.TINYINT, Types.SMALLINT, Types.INTEGER, Types.BIGINT -> false;

                        // 浮点类型 - 可指定但不推荐
                        case Types.FLOAT, Types.DOUBLE, Types.REAL -> false;

                        // 日期时间 - 可指定微秒精度（0-6）
                        case Types.DATE -> false;  // DATE 不支持精度
                        case Types.TIME, Types.TIMESTAMP -> true;   // TIME 和 TIMESTAMP 支持微秒精度

                        // 大文本/大二进制 - 不能指定长度
                        case Types.LONGVARCHAR, Types.LONGNVARCHAR, Types.LONGVARBINARY, Types.CLOB, Types.NCLOB,
                             Types.BLOB -> false;

                        // 其他类型 - 不支持长度
                        default -> false;
                };
        }
}
