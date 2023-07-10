package com.bce.core.anime.model;

import com.bce.core.anime.model.image.AbsImage;
import com.bce.core.anime.model.image.FolderImage;
import com.bce.core.anime.model.image.TreeEndImage;

import java.awt.image.BufferedImage;

public class ImageTree extends AbsModelTree<AbsImage> {

    public ImageTree(ModelStream parent, AbsImage image) {
        super(parent, parent.get().tree_top, image);
    }

    public ImageTree(ModelStream  parent, BufferedImage image) {
        super(parent, parent.get().tree_top, new FolderImage(image));
    }

    @Override
    public AbsImage getImage() {
        return image;
    }
}
