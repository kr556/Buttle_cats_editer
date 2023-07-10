package com.bce.core.anime.model.image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * アニメーションで使用できる画像です。
 */
public abstract sealed class AbsImage permits Folder, FolderImage, TreeEndImage {
    protected BufferedImage bufferedImage;

    protected AbsImage(BufferedImage bf) {
        bufferedImage = bf;
    }

    public int getWidth() {
        return bufferedImage.getWidth();
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public abstract Graphics2D getGraphics();

    public abstract BufferedImage getBufferedImage();

    public abstract boolean treeEnd();
}
