package com.changhong.opendb.ui.widgets;

import com.changhong.opendb.resource.ResourceManager;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;

/**
 * @author Luo Tiansheng
 * @since 2026/3/27
 */
public class VFX
{
        public static <S> TableView<S> newTableView()
        {
                TableView<S> table = new TableView<>();
                table.getStyleClass().add("vfx-table-view");
                return table;
        }

        public static <S, T> TableColumn<S, T> newTableColumn(String name)
        {
                return new TableColumn<>(name);
        }

        public static Button newIconButton(String tip, String icon)
        {
                Button button = new Button();

                button.getStyleClass().add("vfx-icon-button");
                button.setTooltip(new Tooltip(tip));
                button.setGraphic(ResourceManager.use(icon));

                return button;
        }
}
