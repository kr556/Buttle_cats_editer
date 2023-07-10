package com.bce.core.object;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * 属性を格納します。<br>
 * 使用できる属性をenumとして提供します、
 */
public class Types {
    public enum TYPE_ {
        /**
         * 無属性<br>
         * もしも無属性の場合、すべての属性が無効になります。
         */
        NONE("NONE"),
        /**
         * 白<br>
         * もしも白の場合、{@link TYPE_#SUB_BEHEMOTH}と{@link TYPE_#SUB_COLOSSAL}以外の属性が無効となりますが、無属性の場合
         * に限り白ではなく無属性として扱われます。
         */
        WHITE("WHITE"),
        /**赤*/
        RED("red"),
        /**浮き*/
        FLOAT("float"),
        /**黒*/
        BLACK("black"),
        /**メタル*/
        METAL("metal"),
        /**天使*/
        ANGEL("angel"),
        /**エイリアン*/
        ALIEN("alien"),
        /**ゾンビ*/
        ZOMBIE("zombie"),
        /**悪魔*/
        AKU("aku"),
        /**古代*/
        RELIC("relic"),
        /**使徒*/
        EVA_ANGEL("eva-angel"),
        /**魔女*/
        WITCH("witch"),
        /**超獣*/
        SUB_BEHEMOTH("(behemoth)"),
        /**超生命体*/
        SUB_COLOSSAL("(colossal)"),
        /**
         * この場合、このキャラは味方キャラとなるので一切の属性を持ちません
         */
        IS_CAT("this character is cat");

        final String s;

        TYPE_(String name) {
            s = name;
        }

        String getName() {
            return s;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    private List<TYPE_> types;
    private boolean metal;

    public Types(@NotNull TYPE_ ... types) {
        if (types.length == 0) this.types = new ArrayList<>();

        try {
            this.types = new ArrayList<>(new HashSet<>(Arrays.asList(types))).stream()
                    .filter(typ -> !this.types.contains(TYPE_.IS_CAT) || typ == TYPE_.IS_CAT)
                    .filter(typ -> !this.types.contains(TYPE_.NONE) || typ == TYPE_.NONE)
                    .filter(typ -> !this.types.contains(TYPE_.WHITE) || typ == TYPE_.WHITE || typ == TYPE_.SUB_BEHEMOTH || typ == TYPE_.SUB_COLOSSAL)
                    .filter(typ -> !this.types.contains(TYPE_.ANGEL) || typ != TYPE_.FLOAT)
                    .toList();
        } catch (NullPointerException e) {
            this.types = new ArrayList<>();
        }
    }

    public TYPE_[] get() {
        return types.toArray(new TYPE_[0]);
    }

    public boolean isMetal() {
        return types.contains(TYPE_.METAL);
    }

    @Override
    public String toString() {
        return "Types : " + types;
    }
}
