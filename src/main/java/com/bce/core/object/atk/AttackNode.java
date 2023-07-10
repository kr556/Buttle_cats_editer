package com.bce.core.object.atk;

import com.bce.core.object.Assign;
import com.bce.core.battle.Battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 一キャラクターの攻撃を全て一つにしたものです、フレームごとに攻撃を決めます。
 */
public class AttackNode extends ArrayList<AttackObject> implements List<AttackObject> {
    /**攻撃の起点*/
    private int absolutelyInvokePoint;

    public AttackNode(AttackObject...attacks) {
        super(new ArrayList<>(Arrays.asList(attacks)));
    }

    /**
     * 攻撃の際に、攻撃の起点を設定します。
     * @param stage_chara_id この攻撃を発動したキャラクターのステージ上でのID
     * @param assign この攻撃を発動したキャラの所属。
     */
    public void invoke(int stage_chara_id, Assign assign) {
        absolutelyInvokePoint = Battle.stage.getAbsolutePosition(stage_chara_id, assign);
        for (AttackObject atk : this) {
            atk.invoke_point.action(
                    min -> min + absolutelyInvokePoint,
                    max -> max + absolutelyInvokePoint);
        }

    }

    /**
     * 正常な文字列を返すのが困難なた
     * @return
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        int i = 1;
        for (AttackObject ao : this) {
            ret.append("step.").append(i++).append(" : ").append(ao);
        }

        return ret.toString();
    }

}
