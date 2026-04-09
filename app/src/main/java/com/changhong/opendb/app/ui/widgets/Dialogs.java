package com.changhong.opendb.app.ui.widgets;

import com.changhong.opendb.app.utils.Causes;
import javafx.application.Platform;

/**
 * @author Luo Tiansheng
 * @since 2026/3/25
 */
public class Dialogs
{
        public interface DialogCallback {
                void apply() throws Throwable;
        }

        public static void tryCall(DialogCallback callback)
        {
                try {
                        callback.apply();
                } catch (Throwable e) {
                        openError(e);
                }
        }

        public static void openError(String message)
        {
                Platform.runLater(() -> ErrorDialog.showDialog(message));
        }

        /**
         * 弹出异常提示框
         */
        public static void openError(Throwable e)
        {
                openError(Causes.message(e));
        }

}
