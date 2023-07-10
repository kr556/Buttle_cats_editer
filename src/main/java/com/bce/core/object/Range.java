package com.bce.core.object;

/**
 * 当たり判定と感知射程を格納してます。
 */
public class Range {
    private int ran,
            entity_size;

    public Range(int range, int size) {
        ran = range;
        entity_size = size;
    }

    public Range(int range) {
        ran = range;
        entity_size = -320;
    }

    public int getRange() {
        return ran;
    }

    public int getSize() {
        return entity_size;
    }
}
