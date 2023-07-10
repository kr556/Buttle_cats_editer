package com.bce.core.object;

import com.bce.core.anime.AbsAnimationNode;
import com.bce.core.object.atk.AttackNode;
import com.bce.core.object.charas.DummyCharacter;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;

/**
 * キャラクターのクラスです。適宜オーバーライドして敵にするなり味方にするなりしてください。なお、このクラス、またはこのクラスのサブクラスの
 * のインスタンスを生成するにはcreateCharacterに一度インスタンス化したものを渡し、編集してから{@link CharacterForm}のtoCharacter()
 * を通してインスタンスを受けとってください。これはキャラクターを運用する際の特性上フィールドを編集するのは最初だけでほとんど事足りることや、
 * 可読性の観点からパラメーターを調整するメソッドがこのクラスに存在してないためです。
 */
public abstract class Character implements Serializable {

    /**
     * キャラクターのパラメーターを編集します。
     * @param ch 書き換えられる元のキャラ。これ自身は書き換えられません。
     * @return ここではキャラクターのパラメーターを設定するためのクラスを返します
     */
    public static CharacterForm createCharacter(Character ch) {
        return CharacterForm.suport(ch);
    }

    /**
     * 引数のキャラクターをjsonNodeを元にインスタンスを復元します
     * @param character 書き換えられる元のキャラ。これ自身は書き換えられません。
     * @param jn キャラクターの情報を収録したjsonNode
     * @return ここではキャラクターのパラメーターを設定するためのクラスを返します
     */
    public static CharacterForm.FormEnd createCharacter(Character character, JsonNode jn) {
        return CharacterForm.suport(character, jn);
    }

    /**
     * jsonNodeを元にインスタンスを復元します
     * @param jn キャラクターの情報を収録したjsonNode
     * @return ここではキャラクターのパラメーターを設定するためのクラスを返します
     */
    public static CharacterForm.FormEnd createCharacter(JsonNode jn) {
        return Character.createCharacter(DummyCharacter.create(), jn);
    }

    private int hp;
    private int speed;
    private AttackNode attack;
    private Types types;
    private Range ran;
    private boolean metal;
    private Assign assign;
    private String name;
    private AbsAnimationNode animationNode;

    protected transient int id_on_stage;

    protected transient int position;
    protected transient boolean life;
    protected transient FrameObject frame;
    protected transient CharacterState state;

    public Character(int character_id, Assign assign, Types ... types) {
        this.assign = assign;
        this.id_on_stage = character_id;

        this.types = types.length >= 1 ? types[0]
        // types.lengthが0の場合
                : switch (assign) {
            case CAT -> new Types(Types.TYPE_.IS_CAT);
            case ENEMY, NONE -> new Types(Types.TYPE_.NONE);
        };
        init();
    }

    public void init() {
        life = false;
        frame = new FrameObject(CharacterState.WALKING);
        state = CharacterState.WALKING;
        position = 0;
    }

    /**
     * @param damage 一回の攻撃でこのキャラが受けたダメージ。
     */
    public abstract void hit(int damage);

    /**
     * このキャラのフレームを進めます。
     */
    public void frameAdvance() {
    }

    /**
     * 行軍
     */
    public void marth(int speed) {
        position += speed;
    }

    /**
     * 死亡イベント
     */
    public abstract void die();

    /**
     * キャラのステージ上でのIDを設定します。0 ~ 49の範囲にある場合、勝手にステージ上に居ると判定されます。
     */
    public void setIdOnStage(int character_id) {
        this.id_on_stage = character_id;
    }

    /**
     * @return ステージ上のIDを返します<br>
     * @0~49     -通常の敵<br>
     * @50       -城<br>
     * @51~100   -鉄壁砲などの特殊キャラ
     */
    public int getIdOnStage() {
        return id_on_stage;
    }

    /**
     * @return 素の攻撃力
     */
    public abstract int getDamage(int atk_count);

    /**
     * @return 宝や超ダメなどの係数を全て適用した攻撃力。
     */
    public abstract int getFinalDamage(int atk_count);

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public Range getRange() {
        return ran;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public Types getTypes() {
        return types;
    }

    AttackNode getAttack() {
        return attack;
    }

    /**
     * @return どっち側の軍に所属しているか
     */
    public Assign assign() {
        return assign;
    }


    void setHp(int hp) {
        this.hp = hp;
    }

    void setAttack(AttackNode attack) {
        this.attack = attack;
    }

    void setAssign(Assign assign) {
        this.assign = assign;
    }

    void setRange(Range ran) {
        this.ran = ran;
    }

    void setTypes(Types types) {
        this.types = types;
    }


    void setSpeed(int speed) {
        this.speed = speed;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name +
                " |> [hp: " + hp + ", speed: " + speed + ", types: " + types + ", ran: " + ran + ", assign: " + assign + "]" +
                "\n      attack -> \n" + attack.toString().replace("\n", "      \n") +
                "\n---------------------[stage status]---------------------" +
                "\n   stageID:  " + id_on_stage +
                "\n   position: " + position +
                "\n   life:     " + life +
                "\n   frame:    " + frame +
                "\n   state:    " + state +
                "\n--------------------------------------------------------";
    }
}
