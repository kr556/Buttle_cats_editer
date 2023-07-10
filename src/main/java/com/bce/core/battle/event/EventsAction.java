package com.bce.core.battle.event;

/**
 * ステージ上のキャラクターに対して発動する動作をまとめたものです。
 */
public class EventsAction {
    private EventsAction() {}

    /**
     * ふっとばし1フレーム分の処理です。
     * @param invoke_chara_id 特性が発動したキャラクター
     * @param recive_chara_id 特性の受けになるキャラクター
     */
    @Deprecated // 実装完了したらアノテーション消してね
    public static void blow(int invoke_chara_id, int ... recive_chara_id) {

    }

    /**
     * 遅くする1フレーム分の処理
     * @param invoke_chara_id
     * @param recive_chara_id
     */
    @Deprecated // 実装完了したらアノテーション消してね
    public static void srow(int invoke_chara_id, int ... recive_chara_id) {}
}
