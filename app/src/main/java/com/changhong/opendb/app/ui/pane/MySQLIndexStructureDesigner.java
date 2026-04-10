package com.changhong.opendb.app.ui.pane;

import com.changhong.opendb.app.driver.TableIndexMetaData;
import com.changhong.opendb.app.driver.TableMetaData;
import com.changhong.opendb.app.driver.executor.SQLExecutor;

import java.util.Collection;

/**
 * @author Luo Tiansheng
 * @since 2026/4/10
 */
public class MySQLIndexStructureDesigner extends Designer<TableIndexMetaData>
{
        public MySQLIndexStructureDesigner(TableMetaData tableMetaData, SQLExecutor executor, String name)
        {
                super(tableMetaData, executor, name);
        }

        @Override
        public void onReload(Collection<TableIndexMetaData> values)
        {

        }

        @Override
        public void onCommitEdit(TableIndexMetaData oldVal, TableIndexMetaData newVal)
        {

        }

        @Override
        public void applySave()
        {

        }

        @Override
        public void applyPlus(TableIndexMetaData newObject)
        {
                /* DO NOTHING... */
        }

        @Override
        public void applyMinus(Collection<TableIndexMetaData> selectionItems)
        {

        }
}
