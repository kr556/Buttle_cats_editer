package com.bce.core.var;

public class Vec3<T> {
    protected T d_1, d_2, d_3;

    public Vec3(T d1, T d2, T d3) {
        d_1 = d1;
        d_2 = d2;
        d_3 = d3;
    }

    public Vec3<T> action(LinerFunction<T> l1, LinerFunction<T> l2, LinerFunction<T> l3) {
        d_1 = l1.result(d_1);
        d_2 = l2.result(d_2);
        d_3 = l3.result(d_3);
        return this;
    }

    public T get1() {
        return d_1;
    }

    public T get2() {
        return d_2;
    }

    public T get3() {
        return d_3;
    }
}
