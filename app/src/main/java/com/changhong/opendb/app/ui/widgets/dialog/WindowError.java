package com.changhong.opendb.app.ui.widgets.dialog;

import com.changhong.opendb.app.VFXApplication;
import com.changhong.opendb.app.ui.workbench.VFXCopyableLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Toolkit;

import static com.changhong.string.StringStaticize.strwfmt;

/**
 * @author Luo Tiansheng
 * @since 2026/3/26
 */
@SuppressWarnings("DuplicatedCode")
class WindowError
{
        public static void showDialog(String fmt, Object... args) {
                Toolkit.getDefaultToolkit().beep();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(true);
                stage.setTitle(VFXApplication.TITLE);

                String text = strwfmt(fmt, args);

                VFXCopyableLabel label = new VFXCopyableLabel(text);

                Button ok = new Button("确认");
                ok.setDefaultButton(true);
                ok.setOnAction(e -> stage.close());

                HBox hbox = new HBox(ok);
                hbox.setPadding(new Insets(10));
                hbox.setAlignment(Pos.CENTER_RIGHT);
                hbox.setSpacing(10);

                BorderPane root = new BorderPane();
                root.setCenter(label);
                root.setBottom(hbox);

                Dimension size = new Dimension(text);

                Scene scene = new Scene(root, size.width, size.height);

                stage.setScene(scene);
                stage.sizeToScene();
                stage.showAndWait();
        }
}
