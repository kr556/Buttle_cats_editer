package com.bce.core.var;

public class Vec2Int extends Vec2<Integer> {
    public Vec2Int(Vec2<? extends Number> vec) {
        super(vec);
    }

    public Vec2Int(int d1, int d2) {
        super(d1, d2);
    }
}
