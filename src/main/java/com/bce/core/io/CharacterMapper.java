package com.bce.core.io;

import com.bce.core.object.Character;
import com.bce.core.object.CharacterForm;
import com.bce.core.object.Range;
import com.bce.core.object.Types;
import com.bce.core.object.atk.AttackNode;
import com.bce.core.object.charas.DummyCharacter;
import com.fasterxml.jackson.databind.JsonNode;

import static com.bce.core.object.Assign.*;

/**
 * キャラクターを読み込みます。
 */
public class CharacterMapper {
    private JsonReader jr;
    private Chara cr;

    public CharacterMapper(JsonNode jn) {
        jr = new JsonReader(jn);
    }

    public CharacterMapper(JsonNode jn, Character character) {
        jr = new JsonReader(jn);
        cr = () -> character;
    }

    /**
     * キャラクターステータスファイルからAttakNodeを読み込むのは少し複雑な処理になるので可読性の観点から分けてます。
     * @return JsonNodeから読み込んだAttackNodeを返します。
     */
    @Deprecated //実装完了したら消してね
    private AttackNode toAttackNode() {
        return null;
    }

    public CharacterForm.FormEnd read() {
        JsonNode tjn = jr.readTree("type");
        Types.TYPE_[] tts = new Types.TYPE_[tjn.size()];

        for (int i = 0; i < tts.length; i++) {
            tts[i] = switch (tjn.get(i).textValue()) {
                case "IS_CAT"       -> Types.TYPE_.IS_CAT;
                case "NONE"         -> Types.TYPE_.NONE;
                case "WHITE"        -> Types.TYPE_.WHITE;
                case "RED"          -> Types.TYPE_.RED;
                case "FLOAT"        -> Types.TYPE_.FLOAT;
                case "BLACK"        -> Types.TYPE_.BLACK;
                case "METAL"        -> Types.TYPE_.METAL;
                case "ANGEL"        -> Types.TYPE_.ANGEL;
                case "ALIEN"        -> Types.TYPE_.ALIEN;
                case "ZOMBIE"       -> Types.TYPE_.ZOMBIE;
                case "AKU"          -> Types.TYPE_.AKU;
                case "RELIC"        -> Types.TYPE_.RELIC;
                case "EVA_ANGEL"    -> Types.TYPE_.EVA_ANGEL;
                case "WITCH"        -> Types.TYPE_.WITCH;
                case "SUB_BEHEMOTH" -> Types.TYPE_.SUB_BEHEMOTH;
                case "SUB_COLOSSAL" -> Types.TYPE_.SUB_COLOSSAL;
                default -> throw new IllegalStateException("type " + tjn.get(i).textValue() + " does not exist");
            };
        }

        Types tps = new Types(tts);

        //jr.readTree();
        return Character.createCharacter(DummyCharacter.create()).form()
                .setHp(() ->  jr.readTree("hp").asInt())
                .setAttack(this::toAttackNode)
                .setRange(() -> new Range(jr.readTree("range/size").asInt(), jr.readTree("range/sensing").asInt()))
                .setType(() -> tps)
                .setAssign(() -> tps.get().length == 0 ? NONE : tps.get()[0] == Types.TYPE_.IS_CAT ? CAT : ENEMY)
                .setSpeed(() -> jr.readTree("speed").asInt())
                .setName(() -> jr.readTree("name").textValue());
    }

    private interface Chara {
        Character get();
    }
}
