package com.bce.core.object.abilitys;

import com.bce.core.object.Assign;
import com.bce.core.var.Damage;

/**
 * 新しい特性を実装する際はこのクラスをオーバーライドしてください。オーバーライドする際は必ず,
 * <pre>{@code public static final int ability_id = //任意の定数}</pre>
 * を実装してください。なお、任意の定数は絶対に他の定数とかぶらないようにお願いします。
 */
public abstract class Ability {
    private String name;

    /**
     * 特性を発生させるメソッドです。
     * @param stage_chara_id 特性を発生させたキャラクターのステージ上でのid
     * @param assign キャラの所属
     */
    public abstract void invoke(int stage_chara_id, Assign assign);

    /**
     * 特性によって与えられるダメージを算出します。
     * @param damage 変化前のダメージ.
     * @return 特性によって変化したダメージを返します。
     */
    public abstract Damage dealtDamage(Damage damage) ;

    /**
     * 特性によって受けるダメージを算出します。
     * @param damage 変化前のダメージ。
     * @return 特性によって変化したダメージを返します
     */
    public abstract Damage receiveDamage(Damage damage);

    /**特性のidを返します*/
    public abstract int getId() ;
}
