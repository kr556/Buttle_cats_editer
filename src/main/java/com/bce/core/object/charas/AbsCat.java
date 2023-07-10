package com.bce.core.object.charas;

import com.bce.core.object.Assign;
import com.bce.core.object.Character;

public abstract class AbsCat extends Character {
    /**
     * 味方キャラ用のコンストラクターです。キャラクターのパラメーターが設定されない可能性があるので非推奨です。{@link Character#createCharacter}
     * を使用することをおすすめします。
     * @param character_id ステージ上のキャラクターのIDです。
     * @param assign どちらの軍に所属しているか
     */
    @Deprecated(since = "java.17, BCE.0.0.0")
    public AbsCat(int character_id, Assign assign) {
        super(character_id, assign);
    }
}
