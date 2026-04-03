package com.changhong.opendb.driver;

import com.changhong.opendb.driver.executor.SQLExecutor;
import lombok.Data;

import java.util.List;

/**
 * @author Luo Tiansheng
 * @since 2026/3/30
 */
@Data
public class ShittyMutableDataGrid
{
        private List<ColumnMetaData> columns;
        private List<Row> rows;
        private boolean editable = false;
        private boolean addable = false;

        private final SQL origin;
        private final SQLExecutor executor;

        public ShittyMutableDataGrid(SQL origin, SQLExecutor executor)
        {
                this.origin = origin;
                this.executor = executor;
        }

        public void refresh()
        {
                if (executor != null && origin != null) {
                        ShittyMutableDataGrid refreshQRS = executor.execute(origin);
                        this.columns = refreshQRS.columns;
                        this.rows = refreshQRS.rows;
                }
        }

        public void addEmptyRow()
        {
                rows.addLast(new Row(columns.size()));
        }
}
