package com.changhong.opendb.utils;

import com.changhong.opendb.driver.QueryResultSet;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结果集工具
 *
 * @author Luo Tiansheng
 * @since 2026/3/30
 */
public class ResultSetUtils
{
        /**
         * 结果集转 Java 集合
         */
        public static <T> List<T> rs2jlist(ResultSet rs, Class<T> aClass)
        {
                try {
                        List<Map<String, Object>> rows = new ArrayList<>();

                        ResultSetMetaData metaData = rs.getMetaData();
                        int columnCount = metaData.getColumnCount();

                        while (rs.next()) {
                                Map<String, Object> row = new HashMap<>();

                                for (int i = 1; i < columnCount + 1; i++) {
                                        Object object = rs.getObject(i);

                                        if (object instanceof LocalDateTime localDateTime) {
                                                java.util.Date date =
                                                        Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                                                row.put(metaData.getColumnLabel(i), date);
                                        } else {
                                                row.put(metaData.getColumnLabel(i), object);
                                        }

                                }

                                rows.add(row);
                        }

                        String jsonArray = JSONUtils.toJSONString(rows);

                        return JSONUtils.toJavaList(jsonArray, aClass);
                } catch (Exception e) {
                        Catcher.ithrow(e);
                        return null;
                }
        }

        /**
         * 结果集转 QueryResultSet 对象
         */
        public static void rs2qrs(ResultSet rs, QueryResultSet qrs) throws SQLException
        {
                ResultSetMetaData meta = rs.getMetaData();
                int colCount = meta.getColumnCount();

                for (int i = 1; i <= colCount; i++)
                        qrs.getColumns().add(meta.getColumnLabel(i));

                while (rs.next()) {
                        List<String> row = new ArrayList<>();
                        for (int i = 1; i <= colCount; i++) {
                                Object val = rs.getObject(i);
                                row.add(val != null ? val.toString() : null);
                        }
                        qrs.getRows().add(row);
                }
        }
}
