package com.changhong.opendb.ui.navigator.node;

import com.changhong.opendb.core.event.EventBus;
import com.changhong.opendb.core.event.NewQueryScriptEvent;
import com.changhong.opendb.model.QueryInfo;
import com.changhong.opendb.resource.ResourceManager;
import javafx.scene.input.MouseEvent;

/**
 * @author Luo Tiansheng
 * @since 2026/3/25
 */
@SuppressWarnings("FieldCanBeLocal")
public class ODBNQuery extends ODBNode
{
        private final QueryInfo queryInfo;

        public ODBNQuery(QueryInfo queryInfo)
        {
                super(queryInfo.getName());
                setGraphic(ResourceManager.use("sql"));
                this.queryInfo = queryInfo;
        }

        @Override
        public void onMouseDoubleClickEvent(MouseEvent event)
        {
                EventBus.publish(new NewQueryScriptEvent(queryInfo));
        }
}