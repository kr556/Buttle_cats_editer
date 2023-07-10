package com.bce.core.var;

import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

public class Vec2DPoint extends Vec2Double {
    /**内積*/
    public static double innerProduct(Vec2DLine a, Vec2DLine b, double angle) {
        return a.scalar() * b.scalar() * cos(angle);
    }

    public Vec2DPoint(double d1, double d2) {
        super(d1, d2);
    }

    public double getX() {
        return e1;
    }

    public double getY() {
        return e2;
    }

    public double scalar() {
        return sqrt(getX() * getX() + getY() * getY());
    }

    public void setX(double x) {
        e1 = x;
    }

    public void setY(double y) {
        e2 = y;
    }

    public void set(Vec2DPoint v2) {
        set(v2.getX(), v2.getY());
    }

    public void set(double x, double y) {
        e1 = x;
        e2 = y;
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
    public Vec2DPoint add(Vec2<? extends Number> var) {
        set(var.e1.doubleValue() + getX(), var.e2.doubleValue() + getY());
        return this;
    }

    /**和です。*/
    public Vec2DPoint add(Number var) {
        set(var.doubleValue() + getX(), var.doubleValue() + getY());
        return this;
    }
}
