package com.bce.core.var;

public class Vec2PointVec2Line extends Mat4<Double> {
    public Vec2PointVec2Line(double x, double y, double w, double h) {
        set(x, y, w, h);
    }

    public Vec2PointVec2Line(Vec2<Double> point, Vec2<Double> vecter) {
        set(point.e1, point.e2, vecter.e1, vecter.e2);
    }

    public void set(Vec2<Double> p, Vec2<Double> l) {
        set(p.e1, p.e2, l.e1, l.e2);
    }

    public double getX() {
        return getElement(0);
    }

    public double getY() {
        return getElement(1);
    }

    public double getWigth() {
        return getElement(2);
    }

    public double getHeight() {
        return getElement(3);
    }

    public Vec2DPoint getVec2Point() {
        return new Vec2DPoint(getX(),getY());
    }

    public Vec2DLine getVec2Line() {
        return new Vec2DLine(getWigth(), getHeight());
    }
}