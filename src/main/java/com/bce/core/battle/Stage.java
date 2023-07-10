package com.bce.core.battle;

import com.bce.core.exception.IllegalAssignException;
import com.bce.core.object.Assign;
import com.bce.core.object.castle.Castle;
import com.bce.core.object.Character;
import com.bce.core.object.charas.DummyCharacter;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.bce.core.object.Assign.*;
import static com.bce.core.object.Assign.CAT;

public class Stage {

    /**
     * stage/stage_name.jsonからステージ名を格納します。ここのステージ名を元にstageからステージを読み込みます。
     */
    public static List<String> stg_name = new ArrayList<>();

    public static NewStageValue newStage = Stage::new;

    /**
     * 幅
     */
    private int whidth;

    /**
     * 出撃制限
     */
    private int limit_cat, limit_enemy;

    private String name;

    /**
     * 城
     */
    private Castle castle_cat, castle_enemy;

    /**
     * 背景
     */
    private BufferedImage background;

    /**
     * ステージ上のキャラ<br>
     *
     * @cat 味方ネコ(にゃんこ軍団)<br>
     * @enemy 敵(わんこ軍団)
     */
    private CharacterHashMap<Integer, Character> charas = new CharacterHashMap<>();

    public static StageForm createStage(JsonNode jn) {
        return StageForm.suport(jn);
    }

    public static StageForm createStage(NewStageValue stage) {
        return StageForm.suport(stage.get());
    }

    /**
     * setterは、ステージが作られたときしか使用されず、また構造上外部からアクセスされることがほぼないので{@link StageForm}に分けてます。
     *これによってコンストラクタから作ったインスタンスは同じパッケージ内にある場合を除いて変更できないので非推奨となっています。新しいキャラクターを作る
     *場合は、{@link Stage#createStage}を使用し、<pre>{@code Stage stg = Stage.createStage(newStage)...}</pre>のように書いてキャラクターフォームを取得し、set処理を行ってください。<br>
     * なお、{@link Stage#createStage(JsonNode)}にステージ情報を格納したJsonNodeを渡すとインスタンスが復元されます。
     */
    @Deprecated(since = "java:17, BCE:0.0.0")
    public Stage() {}

    /**
     * @return キャラのステージ上でのID。IDが埋まってる場合-1を返します
     */
    public int createCharacterID(Assign a) {
        var cs = getCharas(a);
        for (int i = 0; i < cs.size(); i++) {
            if (cs.get(i).getIdOnStage() != i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * キャラを召喚します。
     */
    public void summon(Character chara) {
        int id = createCharacterID(chara.assign());
        chara.setIdOnStage(id);

        if (chara.assign() == CAT) {
            this.charas.put(CAT, id, chara);
        } else if (chara.assign() == ENEMY) {
            this.charas.put(ENEMY, id, chara);
        }
    }

    public int getWidth() {
        return whidth;
    }

    public String getName() {
        return name;
    }

    public int getLimitC() {
        return limit_cat;
    }

    public int getLimitE() {
        return limit_enemy;
    }

    public Castle getCastleC() {
        return castle_cat;
    }

    public Castle getCastleE() {
        return castle_enemy;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public Character getChara(int character_id, Assign assign) {
        return assign == CAT ? charas.get(CAT, character_id) : charas.get(ENEMY, character_id);
    }

    /**
     * ステージ上に召喚、または召喚の可能背があるキャラを返します。
     *
     * @param assign にゃんこ軍か否か
     * @return キャラを返します。
     */
    public HashMap<Integer, Character> getCharas(Assign assign) {
        return switch (assign) {
            case CAT -> charas.cat;
            case ENEMY -> charas.enemy;
            case NONE -> charas.none;
        };
    }

    /**
     * ステージ上のキャラクターの数を取得します。通常、{@link DummyCharacter}は召喚されてないキャラクター扱いなのでカウントされません。
     * @param assign 取得したい軍
     */
    public int getCharacterCount(Assign assign) {
        return new ArrayList<>(charas.gets(assign).values()).stream()
                .filter(c -> c.getIdOnStage() != -1).toList().size();
    }

    /**
     * キャラクターのステージ上での絶対的な座標。
     *
     * @param character_id ステージ上でのキャラクターのID
     * @param assign       所属
     */
    public int getAbsolutePosition(int character_id, Assign assign) {
        return getAbsolute(getChara(character_id, assign).getPosition(), assign);
    }

    /**
     * 渡された相対的な座標をステージの大きさに基づいて絶対座標に変換します
     *
     * @param relative 相対的な座標
     * @param assign   引数の相対座標はどちらの軍から見た座標か
     * @return ステージの右端を0としての座標。
     */
    public int getAbsolute(int relative, Assign assign) {
        return switch (assign) {
            case CAT -> 0;
            case ENEMY -> 0;
            case NONE -> throw new IllegalAssignException("cant get position \"NONE\"");
        };
    }

    /**
     * 特定の範囲の中にいるキャラクターの識別子を取得します。minがmaxより大きい場合、min ~ maxの中以外のキャラクターが返されます。
     *
     * @param assign 取得するキャラの所属。例えば、味方が攻撃する場合は敵のキャラを取得するので{@link Assign#ENEMY}が入ります。
     * @param min    範囲の最小値。相対的な座標
     * @param max    範囲の最大値。相対的な座標
     * @return 対象キャラクターの所属とステージ上のIDを返します.
     */
    public TargetCharacter[] getTarget(Assign assign, int min, int max) {
        int abs_min = getAbsolute(min, assign),
                abs_max = getAbsolute(max, assign);

        return (TargetCharacter[])  new ArrayList<>(getCharas(assign).values()).stream()
                .filter(c -> min <= max ?
                        getAbsolutePosition(c.getIdOnStage(), CAT) <= abs_max &&
                                getAbsolutePosition(c.getIdOnStage(), CAT) >= abs_min :
                        getAbsolutePosition(c.getIdOnStage(), CAT) > abs_max ||
                                getAbsolutePosition(c.getIdOnStage(), CAT) < abs_min)
                .toList()
                .toArray();
    }

    void setName(String name) {
        this.name = name;
    }

    void setWhidth(int whidth) {
        this.whidth = whidth;
    }

    void setCastle(Castle castle_cat, Castle castle_enemy) {
        this.castle_cat = castle_cat;
        this.castle_enemy = castle_enemy;
    }

    void setBackground(BufferedImage background) {
        this.background = background;
    }

    void setLimit(Assign assign, int limit) {
        switch (assign) {
            case CAT -> limit_cat = limit;
            case ENEMY -> limit_enemy = limit;
            case NONE -> throw new IllegalAssignException();
        }
    }

    @Override
    public String toString() {
        return ("stage: " + name +
                "\n   wdith:  " + whidth +
                "\n   castle:   cat = " + castle_cat  + ",     enemy = " + castle_enemy +
                "\n   characters:" +
                "\n=======================================< cats >=======================================|" +
                "\n   " + charas.toIDString(CAT).replace("\n", "\n   ") +
                "\n======================================< enemys >======================================|" +
                "\n   " + charas.toIDString(ENEMY).replace("\n", "\n   ") +
                "\n======================================< nones >=======================================|" +
                "\n   " + charas.toIDString(NONE).replace("\n", "\n   "))
                        .replace("\n", "\n┃");
    }

    private static class CharacterHashMap<KEY, C extends Character> {
        HashMap<KEY, C> cat = new HashMap<>(),
                enemy = new HashMap<>(),
                none = new HashMap<>();

        public C get(Assign assign, KEY key) {
            return switch (assign) {
                case CAT -> cat.get(key);
                case ENEMY -> enemy.get(key);
                case NONE -> none.get(key);
            };
        }

        public void put(Assign assign, KEY key, C character) {
            switch (assign) {
                case CAT -> cat.put(key, character);
                case ENEMY -> enemy.put(key, character);
                case NONE -> none.put(key, character);
            }
        }

        private HashMap<KEY, C> gets(Assign assign) {
            return switch (assign) {
                case CAT -> cat;
                case ENEMY -> enemy;
                case NONE -> none;
            };
        }

        public String toIDString(Assign assign) {
            StringBuilder sb = new StringBuilder();
            for (Character c : gets(assign).values()) {
                if (c.getIdOnStage() != -1) {
                    sb.append(c.getIdOnStage());
                    sb.append("> [");
                    sb.append(", name:");
                    sb.append(c.getName());
                    sb.append("]\n");
                }
            }
            return sb.toString();
        }
    }

    public interface NewStageValue{
        Stage get();
    }
}
