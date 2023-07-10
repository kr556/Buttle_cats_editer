package com.bce.core.var;

import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

public class Vec2IPoint extends Vec2Int {
    /**内積*/
    public static double innerProduct(Vec2DLine a, Vec2DLine b, double angle) {
        return a.scalar() * b.scalar() * cos(angle);
    }

    public Vec2IPoint(int d1, int d2) {
        super(d1, d2);
    }

    public int getX() {
        return e1;
    }

    public int getY() {
        return e2;
    }

    public double scalar() {
        return sqrt(getX() * getX() + getY() * getY());
    }

    public void setX(int x) {
        e1 = x;
    }

    public void setY(int y) {
        e2 = y;
    }

    public void set(Vec2IPoint v2) {
        set(v2.getX(), v2.getY());
    }

    public void set(int x, int y) {
        e1 = x;
        e2 = y;
    }

    /**
     * 積です。
     * @param x x方向の成分をx倍します
     * @param y y方向の成分をy倍します
     */
    public void area(int x, int  y) {
        e1 *= x;
        e2 *= y;
    }

    /**
     * 和です。
     */
    public Vec2IPoint add(Vec2<? extends Number> var) {
        set(var.e1.intValue() + getX(), var.e2.intValue() + getY());
        return this;
    }

    /**
     * 和です。
     */
    public Vec2IPoint add(Number var) {
        set(var.intValue() + getX(), var.intValue() + getY());
        return this;
    }
}
