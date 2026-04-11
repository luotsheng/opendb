package com.changhong.driver.api;

import com.changhong.driver.api.sql.SQL;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Luo Tiansheng
 * @since 2026/4/11
 */
@Getter
@Setter
public class DataGrid
{
        /**
         * 单表且带主键可编辑
         */
        private boolean editable;

        private SQL sql;

        private Session session;

        private List<Column> columns;

        private List<GridRow> rows;
}
