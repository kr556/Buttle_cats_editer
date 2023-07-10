package com.bce.core.object;

import com.bce.core.io.CharacterMapper;
import com.bce.core.object.atk.AttackNode;
import com.bce.core.object.charas.DummyCharacter;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * キャラクターを生成するためのクラスです。
 */
public class CharacterForm {
    Character chara;

    static CharacterForm suport(Character cha) {
        return new CharacterForm(cha);
    }

    static FormEnd suport(Character c, JsonNode jn) {
        // ファイルからオブジェクトを読み込む処理。後々jsonじゃなくて別の方法になるかも
        return new CharacterMapper(jn).read();
    }

    private CharacterForm(Character ch) {
        if (ch == null) {
            chara = DummyCharacter.create();
            return;
        }

        chara = ch;
    }

    public FormHp form() {
        return new FormHp();
    }

    public FormName formEnd() {
        return new FormName();
    }

    public final class FormHp {
        FormHp() {}

        /**
         * 元のキャラクターのHP変えたくないときに使います
         * @return アタックを設定するクラス
         */
        public FormAtk setHp() {
            return new FormAtk();
        }

        /**
         * HPを設定します
         * @param hp キャラクターのHP
         * @return アタックを設定するクラス
         */
        public FormAtk setHp(FormValue<Integer> hp) {
            chara.setHp(hp.get());
            return new FormAtk();
        }
        
        /**
         * 元のキャラクターのHPを元に設定できます
         * @param hp 引数は元のキャラクターのHPです
         * @return アタックを設定するクラス
         */
        public FormAtk setHp(FormValueArg<Integer> hp) {
            chara.setHp(hp.get(chara.getHp()));
            return new FormAtk();
        }
    }

    public final class FormAtk {
        FormAtk() {}

        /**
         * 元のキャラクターのAttackを変えたくないときに使います。
         * @return 射程と当たり判定を設定するクラス
         */
        public FormRange setAttack() {
            return new FormRange();
        }

        /**
         * Attackを設定します
         * @param atk キャラクターのAttackです
         * @return 射程と当たり判定を設定するクラス
         */
        public FormRange setAttack(FormValue<AttackNode> atk) {
            chara.setAttack(atk.get());
            return new FormRange();
        }

        /**
         * 元のキャラクターのAttackを元に設定できます
         * @param atk 引数は元のキャラクターのattackです
         * @return 射程と当たり判定を設定するクラス
         */
        public FormRange setAttack(FormValueArg<AttackNode> atk) {
            chara.setAttack(atk.get(chara.getAttack()));
            return new FormRange();
        }
    }

    public final class FormRange {
        FormRange() {}


        /**
         * 元のキャラクターのRangeを変えたくないときに使います。
         * @return 終端クラス
         */
        public FormType setRange() {
            return new FormType();
        }

        /**
         * Rangeを設定します
         * @param range キャラクターのRangeです
         * @return 終端クラス
         */
        public FormType setRange(FormValue<Range> range) {
            chara.setRange(range.get());
            return new FormType();
        }

        /**
         * 元のキャラクターのRangeを元に設定できます
         * @param range 引数は元のキャラクターのRangeです
         * @return 属性を設定するクラス
         */
        public FormEnd setRange(FormValueArg<Range> range) {
            chara.setRange(range.get(chara.getRange()));
            return new FormEnd();
        }
    }

    public final class FormType {
        FormType() {}
        /**
         * 元のキャラクターの属性を変えたくないときに使います。
         * @return 終端クラス
         */
        public FormAssign setType() {
            return new FormAssign();
        }

        /**
         * 属性を設定します
         * @param typs キャラクターの属性です
         * @return 属性を設定するクラス
         */
        public FormAssign setType(FormValue<Types> typs) {
            chara.setTypes(typs.get());
            return new FormAssign();
        }

        /**
         * 元のキャラクターの属性を元に設定できます
         * @param typs 引数は元のキャラクターのTypeです
         * @return 属性を設定するクラス
         */
        public FormAssign setType(FormValueArg<Types> typs) {
            chara.setTypes(typs.get(chara.getTypes()));
            return new FormAssign();
        }
    }

    public final class FormAssign {
        FormAssign() {}
        /**
         * 元のキャラクターの所属を変えたくないときに使います。
         *
         * @return 終端クラス
         */
        public FormSpeed setAssign() {
            return new FormSpeed();
        }

        /**
         * 所属を設定します
         * @param assign キャラクターの所属です
         * @return 終端クラス
         */
        public FormSpeed setAssign(FormValue<Assign> assign) {
            chara.setAssign(assign.get());
            return new FormSpeed();
        }

        /**
         * 元のキャラクターの所属を元に設定できます
         * @param assign 引数は元のキャラクターの所属です
         * @return 属性を設定するクラス
         */
        public FormSpeed setAssign(FormValueArg<Assign> assign) {
            chara.setAssign(assign.get(chara.assign()));
            return new FormSpeed();
        }
    }

    public final class FormSpeed {
        FormSpeed() {}

        /**
         * 元のキャラクターのSpeedを変えたくないときに使います。
         * @return 終端クラス
         */
        public FormName setSpeed() {
            return new FormName();
        }

        /**
         * Speedを設定します
         * @param speed キャラクターのSpeedです
         * @return 終端クラス
         */
        public FormName setSpeed(FormValue<Integer> speed) {
            chara.setSpeed(speed.get());
            return new FormName();
        }

        /**
         * 元のキャラクターのSpeedを元に設定できます
         * @param speed 引数は元のキャラクターのSpeedです
         * @return 属性を設定するクラス
         */
        public FormName setSpeed(FormValueArg<Integer> speed) {
            chara.setSpeed(speed.get(chara.getSpeed()));
            return new FormName();
        }
    }

    public final class FormName extends FormEnd {
        FormName() {}

        /**
         * 元のキャラクターの名前を変えたくないときに使います。
         * @return 終端クラス
         */
        public FormEnd setName() {
            return new FormEnd();
        }

        /**
         * Speedを設定します
         * @param name キャラクターのSpeedです
         * @return 終端クラス
         */
        public FormEnd setName(FormValue<String> name) {
            chara.setName(name.get());
            return new FormEnd();
        }

        /**
         * 元のキャラクターのSpeedを元に設定できます
         * @param name 引数は元のキャラクターのSpeedです
         * @return 属性を設定するクラス
         */
        public FormEnd setName(FormValueArg<String> name) {
            chara.setName(name.get(chara.getName()));
            return new FormEnd();
        }
    }

    public class FormEnd {
        FormEnd() {}

        /**
         * キャラクターに変換します
         * @return フィールドが変更されたキャラクターです
         */
        public Character toCharacter() {
            return chara;
        }

        /**
         * もう一度設定を最初からやり直します
         * @return 設定するためのクラス
         */
        public CharacterForm getForm() {
            return suport(chara);
        }
    }

    public interface FormValueArg<T> {
        T get(T value);
    }

    public interface FormValue<T> {
        T get();
    }
}
