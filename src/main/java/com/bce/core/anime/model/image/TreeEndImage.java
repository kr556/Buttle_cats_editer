package com.bce.core.anime.model.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class TreeEndImage extends AbsImage {
    public TreeEndImage(BufferedImage bf) {
        super(bf);
    }

    public FolderImage toFolderImage() {
        return new FolderImage(getBufferedImage());
    }

    @Override
    public Graphics2D getGraphics() {
        return (Graphics2D) bufferedImage.getGraphics();
    }

    @Override
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    @Override
    public boolean treeEnd() {
        return true;
    }
}
