package com.changhong.opendb.app.driver;

import com.changhong.collection.Maps;
import com.changhong.utils.Captor;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;

import java.util.List;
import java.util.Map;

import static com.changhong.string.StringStaticize.strieq;

/**
 * SQL 工具类
 *
 * @author Luo Tiansheng
 * @since 2026/4/7
 */
public class SQLUtils
{
        /**
         * 从 DDL 中解析字段默认值
         */
        public static Map<String, ColumnDefaultSpec> parseColumnDefaultSpec(String ddl)
        {
                Map<String, ColumnDefaultSpec> ret = Maps.newHashMap();

                Captor.call(() -> {
                        var createTable = (CreateTable) CCJSqlParserUtil.parse(ddl);

                        List<ColumnDefinition> definitions = createTable.getColumnDefinitions();

                        for (ColumnDefinition definition : definitions) {
                                boolean isDefault = false;

                                List<String> specs = definition.getColumnSpecs();

                                for (String spec : specs) {
                                        if (isDefault) {
                                                var columnDefaultSpec = newColumnDefaultSpec(definition, spec);
                                                ret.put(columnDefaultSpec.getName(), columnDefaultSpec);
                                                break;
                                        }

                                        if (strieq(spec, "DEFAULT"))
                                                isDefault = true;
                                }
                        }
                });

                return ret;
        }

        private static ColumnDefaultSpec newColumnDefaultSpec(ColumnDefinition definition, String spec)
        {
                String name = definition.getColumnName();

                if (name.startsWith("`") && name.endsWith("`")) {
                        name = name.substring(1);
                        name = name.substring(0, name.length() - 1);
                }

                var columnDefaultSpec = new ColumnDefaultSpec();
                columnDefaultSpec.setName(name);
                columnDefaultSpec.setDefaultValue(
                        strieq(spec, "null") ? null : spec
                );

                return columnDefaultSpec;
        }
}
