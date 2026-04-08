package com.changhong.opendb.app.utils;

/**
 * @author Luo Tiansheng
 * @since 2026/4/3
 */
public class Causes
{
        public static String message(Throwable e)
        {
                int cnt = 0;

                while (e.getCause() != null) {
                        e = e.getCause();
                        cnt++;
                }

                return e.getMessage();
        }
}
