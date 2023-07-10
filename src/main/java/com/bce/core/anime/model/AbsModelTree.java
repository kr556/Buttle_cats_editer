package com.bce.core.anime.model;

import com.bce.core.anime.model.image.AbsImage;
import com.bce.core.anime.model.image.Folder;
import com.bce.core.anime.model.image.FolderImage;
import com.bce.core.anime.model.image.TreeEndImage;
import com.bce.core.var.Vec2DLine;
import com.bce.core.var.Vec2DPoint;
import com.bce.core.var.Vec2Double;
import com.bce.core.var.Vec2IPoint;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * アニメーションのツリー構造を格納するクラスです。{@link ArrayList}を継承してるので自身に自身と同じ型を格納できます。
 * @param <IMAGE> 描画する画像
 */
public abstract class AbsModelTree<IMAGE extends AbsImage>
        extends ArrayList<AbsModelTree<? extends AbsImage>>
        implements List<AbsModelTree<? extends AbsImage>>{

    protected int id;
    private int layer;      // 大きい方が優先されます
    /**
     * 自身が所属しているツリー構造をidで格納してます。indexが小さいほうが親モデルです。自身は含まれません。
     */
    protected List<ModelID> structure;
    private String name;
    protected IMAGE image;
    /**
     * 所属しているツリーを返します。
     */
    protected ModelStream tree_top;
    private ModelStream parent;

//=====================<ここから相対的なパラメータ>=====================//
    private double theta;
    private Vec2DPoint pivot;
    private Vec2DPoint point;
    private Vec2DLine scale,
            share;
//============================<ここまで>============================//

    /**親のツリーまでの変形を蓄積し、自身の変形を適用した変形*/
    private AffineTransform affine;

//======================<ここから画像フィルター>======================//
    /**
     * 0 -> invalid<br>
     * 1 -> glow
     */
    private int filter;
    private double opacity;
//============================<ここまで>===========================//

    {
        filter = 0;
        opacity = 1.0;
        layer = 0;
    }

    /**ツリーの頂点*/
    protected AbsModelTree() {
        this.id = 0;
        this.image = (IMAGE) new Folder();
        this.parent = null;
        structure.add(() -> parent.get().id);
        this.tree_top = () -> this;

        affine = new AffineTransform();
    }

    protected AbsModelTree(ModelStream parent, ModelStream tree_top, AbsImage image) {
        this.id = parent.get().size();
        this.image = (IMAGE) image;
        this.parent =  parent;
        structure.add(() -> parent.get().id);
        this.tree_top = tree_top;

        initTransform();
    }

    protected void initTransform() {
        updateTransform();
    }

    private void updateTransform() {
        if (Objects.nonNull(parent)) affine = this.parent.get().affine;
        affine.translate(point.getX(), point.getY());

        affine.rotate(theta, pivot.getX(), pivot.getY());
        affine.scale(scale.getX(), scale.getY());
        affine.shear(share.getX(), share.getY());
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public abstract IMAGE getImage();

    /**
     * このモデルのcenterを起点として回転します。
     * @param theta [rad]
     */
    public void rotate(double theta) {
        affine.rotate(theta);
    }

    /**
     * このモデルの不透明度を設定します。
     * @param a 1が最大で不透過。0が最小で透明。
     */
    public void alpha(double a) {
        opacity = a;
    }

    /**
     * 平行移動します
     * @param tx_ty ピクセルごとの座標です
     */
    public void translate(Vec2IPoint tx_ty) {
        affine.translate(tx_ty.getX(), tx_ty.getY());
    }

    /**
     * 平行移動します
     * @param x ピクセルごとのx座標です
     * @param y ピクセルごとのy座標です
     */
    public void translate(int x, int y) {
        affine.translate(x, y);
    }

    /**
     * 拡大縮小の設定をします
     * @param scale 画像に対する倍率。1が等倍
     */
    public void scale(Vec2DLine scale) {
        affine.scale(scale.getX(), scale.getY());
        this.scale = scale;
    }

    /**
     * 拡大縮小の設定をします
     * @param scalex 画像のx座標に対する倍率。1が等倍
     * @param scaley 画像のy座標に対する倍率。1が等倍
     */
    public void scale(double scalex, double scaley) {
        affine.scale(scalex, scaley);
    }

    /**
     * アフィン変換の中心を設定します
     * @param x ピクセルごとのx座標
     * @param y ピクセルごとのy座標
     */
    public void setAnchor(int x, int y) {
        this.pivot = new Vec2DPoint(x, y);
    }

    /**
     * アフィン変換の中心を設定します
     * @param anchor ピクセルごとの座標です
     */
    public void setAnchor(Vec2DPoint anchor) {
        this.pivot = anchor;
    }

    /**
     * モデルの座標を設定します
     * @param point ピクセルごとの画像です
     */
    public void setPoint(Vec2DPoint point) {
        this.point = point;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        point.getX();
    }

    public void setY(int y) {
        point.getY();
    }
    
    /**
     * アフィン変換の結果をツリー構造に基づいて絶対的なアフィン変換を取得します。
     */
    public AffineTransform getTransform() {
        updateTransform();

        return affine;
    }

    public String getName() {
        if (name == null) return "null";

        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean add(AbsModelTree<? extends AbsImage> model) {
        image = size() == 0 ?
                (IMAGE) new TreeEndImage(image.getBufferedImage()) : image;

        return super.add(model);
    }

    /**ツリーを返すためだけのインターフェースです。*/
    public interface ModelStream {
        AbsModelTree<? extends AbsImage> get();
    }

    /** {@link AbsModelTree#structure}にid格納を格納するためのだけのインターフェースです。それ以外には使わないでください.*/
    public interface ModelID {
        int getID();
    }

}
