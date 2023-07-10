package com.bce.core.object.atk;

import com.bce.core.object.abilitys.Ability;
import com.bce.core.var.Vec2IPoint;

/**
 * 攻撃が発生する場所と時間、ダメージ、それに準じる特性を格納してます。
 */
public class AttackObject {

    protected int damage;
    /**この攻撃が発動するフレーム*/
    protected long invoke_frame;
    /**この攻撃が発動する場所*/
    protected Vec2IPoint invoke_point;
    /**この攻撃で発生するすべての特殊効果*/
    protected Ability[] ability;

    public AttackObject(int damage, int invoke_min, int invoke_max, long invoke_frame, Ability ... ability) {
        this.damage = damage;
        invoke_point = new Vec2IPoint(invoke_min, invoke_max);
        this.ability = ability;
    }

    public AttackObject(int damage, Vec2IPoint invoke_point, long invoke_frame, Ability ... ability) {
        this.damage = damage;
        this.invoke_point = invoke_point;
        this.ability = ability;
    }

    public int getDamage() {
        return damage;
    }

    public long getInvokeFrame() {
        return invoke_frame;
    }

    public Vec2IPoint getInvokePoint() {
        return invoke_point;
    }

    public Ability[] getAbilitys() {
        return ability;
    }
}
