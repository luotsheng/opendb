package com.changhong.opendb.app.ui.pane;

import com.changhong.opendb.app.driver.TableIndexMetaData;
import com.changhong.opendb.app.driver.TableMetaData;
import com.changhong.opendb.app.driver.executor.SQLExecutor;
import javafx.scene.control.Tab;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 表设计接口
 * <p>
 * 该接口主要针对不同按钮实现，避免所有面板都写在一个类中，导致
 * 耦合度较高的情况。
 *
 * @author Luo Tiansheng
 * @since 2026/3/27
 */
@Getter
public abstract class Designer<T>
{
        private final Tab tab;

        protected final TableMetaData tableMetaData;
        protected final SQLExecutor executor;

        private final Map<Integer, TableIndexMetaData> tableIndexMetaDataUpdateBuffer = new HashMap<>();

        public Designer(TableMetaData tableMetaData, SQLExecutor executor, String name)
        {
                this.tableMetaData = tableMetaData;
                this.executor = executor;
                this.tab = new Tab(name);
        }

        /**
         * 当页面刷新时调用
         */
        public abstract void onReload(Collection<T> values);

        /**
         * 编辑事件回调
         */
        public abstract void onCommitEdit(T oldVal, T newVal);

        /**
         * 当用户点击保存按钮
         */
        public abstract void applySave();

        /**
         * 当用户点击加号按钮
         */
        public abstract void applyPlus(T newObject);

        /**
         * 当用户点击减号按钮
         */
        public abstract void applyMinus(Collection<T> selectionItems);
}
