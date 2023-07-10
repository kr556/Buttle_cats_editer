package com.bce.core.anime.model;

import com.bce.core.anime.model.image.AbsImage;
import com.bce.core.anime.model.image.Folder;

public class FoloderTree extends AbsModelTree<Folder> {

    FoloderTree() {
        super();
    }

    public FoloderTree(ModelStream parent) {
        super(parent, parent.get().tree_top, new Folder());
    }

    @Override
    public Folder getImage() {
        return null;
    }
}
