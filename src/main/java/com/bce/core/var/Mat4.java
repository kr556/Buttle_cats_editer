package com.bce.core.var;

import java.util.Arrays;

/**色んなことに使える2*2の便利な行列です*/
class Mat4<T extends Number> {
    protected Number[] ds = new Number[4];

    public void action(LinerFunction<T> f00, LinerFunction<T> f01,
                       LinerFunction<T> f10, LinerFunction<T> f11) {
        if (f00 != null) ds[0] = f00.result((T)ds[0]);
        if (f10 != null) ds[1] = f00.result((T)ds[1]);
        if (f01 != null) ds[2] = f00.result((T)ds[2]);
        if (f11 != null) ds[3] = f00.result((T)ds[3]);
    }

    public void set(T d00, T d01,
                    T d10, T d11) {
        ds[0] = d00.doubleValue();
        ds[1] = d01.doubleValue();
        ds[2] = d10.doubleValue();
        ds[3] = d11.doubleValue();
    }

    /**
     * index is<br>
     * [  1, 2  ]<br>
     * [  3, 4  ]<br>
     */
    public T getElement(int index) {
        return (T)ds[index];
    }

    @Override
    public String toString() {
        return (Arrays.toString(ds))
                .replace("[", "{")
                .replace("]", "}");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mat4<T> mat4 = (Mat4<T>) o;
        return Arrays.equals(ds, mat4.ds);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ds);
    }
}
