package com.bce.core;

import com.bce.core.battle.Stage;
import com.bce.core.io.JsonReader;
import com.bce.core.gui.swing.MainFrame;
import com.fasterxml.jackson.databind.JsonNode;

import static com.bce.core.gui.DrawAssets.*;

import java.awt.*;
import java.io.IOException;

public class GameBoot {

    public static int TYPE_LWJGL = 0,TYPE_SWING = 1;

    private GameBoot() {}

    public static final void mainBoot(int drawingType) {
        readFile();
        if (drawingType == TYPE_SWING) {        // javax.swingで描画
            MainFrame.createFrame();
            MainFrame.run();
        } else if (drawingType == TYPE_LWJGL) { // lwjgl(OpenGL)で描画
//            MainGLWindow mw = new MainGLWindow();
//            mw.run();
        }
    }

    /**
     * コンポーネントに必要なアセットを読み込みます。
     */
    @Deprecated // 実装完了したらアノテーションは消してください
    public static final void readFile() {
        JsonReader jr;
        try {
            jr = new JsonReader("gui/db.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        width= jr.readTree("width").intValue();
        height = jr.readTree("height").intValue();
        theme = new Color(JsonReader.colordicToInt(jr.readTree("back-ground")));
    }

}
