package com.bce.core.object.charas;

import com.bce.core.object.Assign;
import com.bce.core.object.Character;
import com.bce.core.object.Types;

public abstract class AbsEnemy extends Character {

    /**
     * 敵キャラ用のコンストラクターです。キャラクターのパラメーターが設定されない可能性があるので非推奨です。{@link Character#createCharacter}
     * を使用することをおすすめします。
     * @param character_id ステージ上のキャラクターのIDです。
     */
    @Deprecated(since = "java.17, BCE.0.0.0")
    private AbsEnemy(int character_id, Types types) {
        super(character_id, Assign.ENEMY, types);
    }
}
