package com.bce.core.var.gui;

import com.bce.core.var.Vec2PointVec2Line;
import com.bce.core.var.Vec2DLine;

/**マウスの最初の座標と最後の座標を格納する行列*/
public class MouseMat4 extends Vec2PointVec2Line {
    /**
     *
     * @param fx マウスの最初のx座標
     * @param fy マウスの最初のy座標
     * @param ex マウスの最後のx座標
     * @param ey マウスの最後のy座標
     */
    public MouseMat4(double fx, double fy, double ex, double ey) {
        super(fx, fy, ex, ey);
    }

    /**
     * @param frst マウスの最初の座標
     * @param finl マウスの最後の座標
     */
    public MouseMat4(Vec2DLine frst, Vec2DLine finl) {
        super(frst.getX(), frst.getY(), finl.getX(), finl.getY());
    }

    public Vec2DLine movement() {
        return new Vec2DLine(getFinal().getX() - getFirst().getX(), getFinal().getY() - getFirst().getY());
    }


    public void set(Vec2DLine m0, Vec2DLine m1) {
        set(m0.getX(), m0.getY(), m1.getX(), m0.getY());
    }

    public Vec2DLine getFirst() {
        return new Vec2DLine(getElement(0),getElement(1));
    }

    public Vec2DLine getFinal() {
        return new Vec2DLine(getElement(2),getElement(3));
    }
}
