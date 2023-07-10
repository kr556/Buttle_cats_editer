package com.bce.core.anime.model;

/**新しいツリーモデルを作る際に使います。*/
public class ModelForm {
    private AbsModelTree.ModelStream tree;

    public static ModelForm createTree() {
        FoloderTree tr = new FoloderTree();
        ModelForm re = new ModelForm();
        re.tree = () -> tr;

        return re;
    }
}
