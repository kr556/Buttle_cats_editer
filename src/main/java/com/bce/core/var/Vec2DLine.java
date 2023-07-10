package com.bce.core.var;

import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

/**マウスの座標のベクトルです*/
public class Vec2DLine extends Vec2Double {
    public Vec2DLine(double d1, double d2) {
        super(d1, d2);
    }

    public void set(Point p) {
        setX(p.getX());
        setY(p.getY());
    }

    public void set(Vec2DLine v2) {
        set(v2.getX(), getY());
    }

    public void set(double d1, double d2) {
        setX(d1);
        setY(d2);
    }

    public void setX(double x) {
        e1 = x;
    }

    public void setY(double y) {
        e2 = y;
    }

    public double getX() {
        return e1;
    }

    public double getY() {
        return e2;
    }

    public double innerProduct(Vec2DLine a, Vec2DLine b, double angle) {
        return a.scalar() * b.scalar() * cos(angle);
    }

    public double scalar() {
        return sqrt(getX() * getX() + getY() * getY());
    }

    /**
     * 積です。
     * @param x x方向の成分をx倍します
     * @param y y方向の成分をy倍します
     */
    public void area(double x, double  y) {
        e1 *= x;
        e2 *= y;
    }

    /**和です。*/
    public Vec2DLine add(Vec2<? extends Number> var) {
        set(var.e1.doubleValue() + getX(), var.e2.doubleValue() + getY());
        return this;
    }

    /**和です。*/
    public Vec2DLine add(Number var) {
        set(var.doubleValue() + getX(), var.doubleValue() + getY());
        return this;
    }
}
