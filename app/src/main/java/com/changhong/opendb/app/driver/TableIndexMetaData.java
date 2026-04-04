package com.changhong.opendb.app.driver;

import com.changhong.collection.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author Luo Tiansheng
 * @since 2026/4/4
 */
@Data
public class TableIndexMetaData
{
        /**
         * 索引名
         */
        private String name;

        /**
         * 字段文本
         */
        private String columnsText;

        /**
         * 索引类型
         */
        private String type;

        /**
         * 是否可见
         */
        private boolean visible;

        /**
         * 索引列
         */
        private List<TableIndexColumn> columnMetaDatas = Lists.newArrayList();

        public void generateColumnText()
        {
                StringBuilder builder = new StringBuilder();

                for (TableIndexColumn meta : columnMetaDatas) {
                        builder.append("`").append(meta.getName()).append("`");
                        if (meta.getPrefixLength() != null)
                                builder.append("(").append(meta.getPrefixLength()).append(")");
                        builder.append(", ");
                }

                builder.deleteCharAt(builder.length() - 2);
                columnsText = builder.toString();
        }
}
