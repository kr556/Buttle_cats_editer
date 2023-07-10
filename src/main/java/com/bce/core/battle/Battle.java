package com.bce.core.battle;

import com.bce.core.object.Assign;
import com.bce.core.object.Character;

public class Battle {
    /**
     * 1ゲームに1ステージとします。
     */
    public static Stage stage;

    /**
     * ゲーム開始からのフレーム数。0以上なら戦闘中
     */
    public static long frame = -1;

    /**
     * 今が戦闘中か
     */
    public static boolean battleNow = false;

    public static Timer timer = new Timer();

//==============================in game============================== |>

    /**
     * ゲーム開始時に呼び出す
     */
    public static void start() {
        battleNow = true;
        frame = 0;
    }


    /**
     * ゲーム終了時に呼び出す
     */
    public static void end() {
        frame = 0;
        battleNow = false;
    }

    /**
     * ここにはいループ１サイクルに実行する処理を書きます。
     */
    @Deprecated() // 実装完了したらアノテーション消してね
    public static void loop() {
        // first |>
        Thread t = new Thread(() -> timer.count(33));
        t.start();  // <|

        // finaly |>
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }           // <|
    }

    /**
     * ゲームのフレームを進めます
     */
    public synchronized static void frameAdvance() {
        frame++;
    }

    /**
     * キャラの召喚
     */
    public synchronized static void summon(Character chara) {
        stage.summon(chara);
    }

    /**
     * キャラクターIDで指定したキャラが攻撃したキャラの座標を検出して攻撃します。
     * @param chara_id_on_stg ステージ上のキャラクターID<br>
     */
    public synchronized static void attack(int chara_id_on_stg, Assign assign) {
        if (assign.equals(Assign.CAT)) {
            var all = stage.getCharas(assign);
        }
    }

//==============================in game============================== <|
}