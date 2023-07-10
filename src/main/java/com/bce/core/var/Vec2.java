package com.bce.core.var;

import java.util.Objects;

public abstract class Vec2<E extends Number> {
    protected E e1, e2;

    public Vec2(Vec2<? extends Number> vec) {
        e1 = (E) vec.e1;
        e2 = (E) vec.e2;
    }

    public Vec2(E d1, E d2) {
        e1 = d1;
        e2 = d2;
    }

    public Vec2<E> action(LinerFunction<E> l1, LinerFunction<E> l2) {
        e1 = l1.result(e1);
        e2 = l2.result(e2);
        return this;
    }

    public Vec2<Integer> intVec2() {
        return new Vec2Int(e1.intValue(), e2.intValue());
    }

    public Vec2<Long> longVec2() {
        return new Vec2Long(e1.longValue(), e2.longValue());
    }

    public Vec2<Float> floarVec2() {
        return new Vec2Float(e1.floatValue(), e2.floatValue());
    }

    public Vec2<Double> doubleVec2() {
        return new Vec2Double(e1.doubleValue(), e2.doubleValue());
    }

    @Override
    public String toString() {
        return "[" + e1 + ", " + e2 + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vec2<?> vec2 = (Vec2<?>) o;
        return Objects.equals(e1, vec2.e1) && Objects.equals(e2, vec2.e2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e1, e2);
    }
}
