package com.bce.core.object.abilitys;

/**
 * 特性を提供するstaticメソッドの集合です。
 */
public final class Abilitys {
    public static Ability getAbility(int ability_id) {
        return switch (ability_id) {
            case NullAbillity.ability_id -> new NullAbillity();
            case Strong.ability_id -> new Strong();
            default -> throw new RuntimeException("ability_id:" + ability_id + " is not found");
        };
    }
}
