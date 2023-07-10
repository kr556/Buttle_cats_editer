package com.bce.core.object.abilitys;

import com.bce.core.object.Assign;
import com.bce.core.var.Damage;

public class Strong extends Ability {
    public static final int ability_id = 1;

    @Override
    public void invoke(int stage_chara_id, Assign assign) {

    }

    @Override
    public Damage dealtDamage(Damage damage) {
        damage.setDamege((int) (damage.getDamege() * 1.5));
        damage.setAbilityId(ability_id);
        return damage;
    }

    @Override
    public Damage receiveDamage(Damage damage) {
        damage.setDamege((int) (damage.getDamege() / 2.5));
        damage.setAbilityId(ability_id);
        return damage;
    }

    @Override
    public int getId() {
        return ability_id;
    }
}
