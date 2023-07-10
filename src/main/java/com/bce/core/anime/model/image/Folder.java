package com.bce.core.anime.model.image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * フォルダーに格納する用の画像です。表示されることは絶対にありません。
 */
public final class Folder extends AbsImage {
    private static BufferedImage nullBuffer;

    static {
        nullBuffer = new BufferedImage(0, 0, BufferedImage.TYPE_BYTE_BINARY);
    }

    public Folder() {
        super(nullBuffer);
    }

    /**このクラスがビットマップを持った画像を保持することはないので非推奨です。*/
    @Deprecated(since = "java:17, BCE:0.0.0")
    @Override
    public Graphics2D getGraphics() {
        return (Graphics2D) bufferedImage.getGraphics();
    }

    /**このクラスがビットマップを持った画像を保持することはないので非推奨です。*/
    @Deprecated(since = "java:17, BCE:0.0.0")
    @Override
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    @Override
    public boolean treeEnd() {
        return false;
    }

}
