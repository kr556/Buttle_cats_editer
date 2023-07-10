package com.bce;

import com.bce.core.GameBoot;
import com.bce.core.gui.DrawAssets;
import com.bce.core.io.FileReader;
import com.bce.core.io.ImageReader;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .registerFont(Font.createFont(Font.PLAIN, new FileReader().read("gui/font/FiraCode-Medium.ttf")));
            DrawAssets.number_font = new Font("Fira Code Medium", Font.PLAIN, 14);
            DrawAssets.string_font = DrawAssets.number_font;
        } catch (FontFormatException | IOException e) {
            DrawAssets.string_font = new Font(null, Font.PLAIN, 14);
            throw new RuntimeException("not found font file / フォントファイルが見つかりません");
        }

        //GameBoot.mainBoot(GameBoot.TYPE_LWJGL);
        GameBoot.mainBoot(GameBoot.TYPE_SWING);
    }
}
