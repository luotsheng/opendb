package com.changhong.opendb.app;

import atlantafx.base.theme.CupertinoLight;
import com.changhong.opendb.ui.layout.MainLayout;
import com.changhong.opendb.ui.widgets.ErrorDialog;
import com.changhong.opendb.ui.widgets.VFX;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luo Tiansheng
 * @since 2026/3/23
 */
public class Launcher extends Application
{
        private static final Class<Launcher> aClass = Launcher.class;
        private static final List<LauncherRunnable> runnables = new ArrayList<>();

        public interface LauncherRunnable {
                void run(Stage stage, Scene scene);
        }

        public static void runLater(LauncherRunnable runnable)
        {
                runnables.add(runnable);
        }

        private static void addVFXStylesheet(Scene scene, String path)
        {
                URL url = aClass.getResource(path);
                ObservableList<String> stylesheets = scene.getStylesheets();

                if (url != null)
                        stylesheets.add(url.toExternalForm());

        }

        static void initialize()
        {
                ErrorDialog.initializeListener();
        }

        static void initializeVFX(Scene scene)
        {
                addVFXStylesheet(scene, "/css/vfx-table-view.css");
                addVFXStylesheet(scene, "/css/vfx-icon-button.css");
                addVFXStylesheet(scene, "/css/vfx-code-area.css");
        }

        @Override
        public void start(Stage stage)
        {
                initialize();
                Application.setUserAgentStylesheet(new CupertinoLight().getUserAgentStylesheet());
                Scene scene = new Scene(new MainLayout(), 1200, 800);
                initializeVFX(scene);
                stage.setTitle("数据库可视化工具");
                stage.setScene(scene);
                stage.setMaximized(true);

                runnables.forEach(runnable -> runnable.run(stage, scene));

                stage.show();
        }

        public static void main(String[] args)
        {
                launch();
        }
}
