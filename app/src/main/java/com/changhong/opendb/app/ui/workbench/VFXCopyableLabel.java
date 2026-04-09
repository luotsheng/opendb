package com.changhong.opendb.app.ui.workbench;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

/**
 * @author Luo Tiansheng
 * @since 2026/4/9
 */
public class VFXCopyableLabel extends TextField
{
        public VFXCopyableLabel()
        {
                this(null);
        }

        public VFXCopyableLabel(String text)
        {
                super(text);

                setStyle("""
                        -fx-background-color: transparent;
                        -fx-border-color: transparent;
                        -fx-padding: 0;
                        """
                );

                setEditable(false);
                setCursor(Cursor.TEXT);
                setAlignment(Pos.CENTER);
                setPrefHeight(Region.USE_COMPUTED_SIZE);

                Platform.runLater(this::deselect);
        }
}
