package com.bce.core.anime.model;

import com.bce.core.anime.model.image.AbsImage;
import com.bce.core.anime.model.image.TreeEndImage;

import java.awt.image.BufferedImage;

public class ImageTree extends AbsModelTree<AbsImage> {

    public ImageTree(AbsImage image, AbsModelTree<AbsImage> parent) {
        super(parent.size(), parent.getId(), () -> image);
    }

    public ImageTree(BufferedImage bf, AbsModelTree<AbsImage> parent) {
        super(parent.size(), parent.getId(), () -> new TreeEndImage(bf));
    }

    @Override
    public AbsImage getImage() {
        return image.get();
    }
}
