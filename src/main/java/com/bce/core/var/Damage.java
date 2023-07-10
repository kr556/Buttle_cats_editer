package com.bce.core.var;

/**
 * 攻撃力などの表現をするためのラッパークラスです
 */
public class Damage {
    private int damege;
    /**このダメージを生成した特性のID。このIDをもとにして攻撃無効などの判定をします*/
    private int ability_id;

    public Damage(int damege, int ability_id) {
        this.damege = damege;
        this.ability_id = ability_id;
    }

    public int getDamege() {
        return damege;
    }

    public int ailityID() {
        return ability_id;
    }

    public void setAbilityId(int ability_id) {
        this.ability_id = ability_id;
    }

    public void setDamege(int damege) {
        this.damege = damege;
    }
}
