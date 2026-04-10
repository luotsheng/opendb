package com.changhong.opendb.app.ui.pane;

import com.changhong.opendb.app.driver.ColumnMetaData;
import com.changhong.opendb.app.driver.TableMetaData;
import com.changhong.opendb.app.driver.executor.SQLExecutor;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Luo Tiansheng
 * @since 2026/4/10
 */
public class MySQLTableStructureDesigner extends Designer<ColumnMetaData>
{
        private final Set<ColumnMetaData> columnMetaDataUpdateBuffer = new HashSet<>();
        private final Set<ColumnMetaData> primaryUpdateBuffer = new LinkedHashSet<>();

        public MySQLTableStructureDesigner(TableMetaData tableMetaData, SQLExecutor executor, String name)
        {
                super(tableMetaData, executor, name);
        }

        @Override
        public void onReload(Collection<ColumnMetaData> values)
        {
                primaryUpdateBuffer.clear();

                for (ColumnMetaData columnMetaData : values)
                        if (columnMetaData.isPrimary())
                                primaryUpdateBuffer.add(columnMetaData);
        }

        @Override
        public void onCommitEdit(ColumnMetaData oldVal, ColumnMetaData newVal)
        {
                /* 检测到主键变动 */
                if (oldVal.isPrimary() != newVal.isPrimary()) {
                        if (newVal.isPrimary()) {
                                primaryUpdateBuffer.add(newVal);
                        } else {
                                primaryUpdateBuffer.remove(newVal);
                        }

                        return;
                }

                /* 变更记录 */
                columnMetaDataUpdateBuffer.add(newVal);
        }

        @Override
        public void applySave()
        {
                executor.alterChange(tableMetaData, columnMetaDataUpdateBuffer);
                executor.alterPrimaryKey(tableMetaData, primaryUpdateBuffer);
                primaryUpdateBuffer.clear();
                columnMetaDataUpdateBuffer.clear();
        }

        @Override
        public void applyPlus(ColumnMetaData newObject)
        {
                /* DO NOTHING... */
        }

        @Override
        public void applyMinus(Collection<ColumnMetaData> selectionItems)
        {
                executor.deleteColumns(tableMetaData, selectionItems);
        }
}
