package com.bce.core.object.charas;

import com.bce.core.object.Assign;
import com.bce.core.object.Character;
import com.bce.core.object.Range;
import com.bce.core.object.Types;
import com.bce.core.object.atk.AttackNode;

/**
 * 未召喚キャラ用のクラス。召喚しないでください
 */
public class DummyCharacter extends Character {
    public static DummyCharacter create() {
        return (DummyCharacter) createCharacter(new DummyCharacter()).form()
                .setHp(() -> -1)
                .setAttack(() -> new AttackNode())
                .setRange(() -> new Range(0,0))
                .setType(() -> new Types(Types.TYPE_.NONE))
                .setAssign(() -> Assign.NONE)
                .setSpeed(() -> 0)
                .setName(() -> "null")
                .toCharacter();
    }

    /**
     * 未召喚キャラ用のクラス。召喚しないでください
     */
    private DummyCharacter() {
        super(-1, Assign.NONE);
    }

    @Override
    public void hit(int damage) {

    }

    @Override
    public void die() {

    }

    @Override
    public int getDamage(int atk_count) {
        return 0;
    }

    @Override
    public int getFinalDamage(int atk_count) {
        return 0;
    }
}
