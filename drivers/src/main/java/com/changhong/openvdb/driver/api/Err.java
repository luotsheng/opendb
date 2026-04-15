package com.changhong.openvdb.driver.api;

import com.changhong.openvdb.driver.api.exception.DriverException;
import lombok.Getter;

/**
 * 通用错误码接口。
 * <p>
 * 定义系统或数据库操作中常见的返回状态码，用于标识操作结果或异常类型。
 *
 * @author Luo Tiansheng
 * @since 2026/4/15
 */
@Getter
public class Err
{
        public static final Err OK = new Err(1000, "success");
        public static final Err KEY_NOT_FOUND = new Err(1001, "key not found");

        private final int code;
        private final String message;
        private final DriverException cause;

        private Err(int code, String message)
        {
                this(code, message, null);
        }

        private Err(int code, String message, DriverException cause)
        {
                this.code = code;
                this.message = message;
                this.cause = cause;
        }

        public static Err withCause(DriverException cause)
        {
                return new Err(500, cause.getMessage(), cause);
        }
}
