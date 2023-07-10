package com.bce.core.object.abilitys;

import com.bce.core.object.Assign;
import com.bce.core.var.Damage;

/**何も起きません。通常攻撃に付与される特性です*/
public class NullAbillity extends Ability{
    public static final int ability_id = 0;

    @Override
    public void invoke(int stage_chara_id, Assign assign) {}

    @Override
    public Damage dealtDamage(Damage damage) {
        damage.setAbilityId(ability_id);
        return damage;
    }

    @Override
    public Damage receiveDamage(Damage damage) {
        damage.setAbilityId(ability_id);
        return damage;
    }

    @Override
    public int getId() {
        return ability_id;
    }
}
