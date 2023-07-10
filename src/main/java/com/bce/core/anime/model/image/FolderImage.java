package com.bce.core.anime.model.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class FolderImage extends AbsImage {
    public FolderImage(BufferedImage bf) {
        super(bf);
    }

    public TreeEndImage toFolderImage() {
        return new TreeEndImage(getBufferedImage());
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
        return false;
    }
}
