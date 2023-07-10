package com.bce.core.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**jsonファイルを読むための基本的なクラスです*/
public class JsonReader {
    private JsonNode jn;

    private static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.matches("\\d+");
    }

    public static int colordicToInt(JsonNode jn) {
        return Integer.parseInt(jn.textValue().replace("#", ""), 16);
    }

    public JsonReader(JsonNode jn) {
        this.jn = jn;
    }

    public JsonReader(File f) throws IOException {
        ObjectMapper om = new ObjectMapper();
        jn = om.readTree(f);
    }

    /**
     * resouceからjsonを読むときに使います。
     */
    public JsonReader(String fileName) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
        jn = new ObjectMapper().readTree(in);
    }

    /**
     * /で区切ってツリーを読みます。
     */
    public JsonNode readTree(String s) {
        String[] tr = s.split("/");
        JsonNode j = jn;
        if (s.equals("")) return jn;
        for (String t : tr) {
            if (isNumeric(s)) {
                j = j.get(Integer.parseInt(t));
            } else {
                j = j.get(t);
            }
        }
        return j;
    }

    /**
     * ツリーの位置を任意の箇所に置き換えます。
     */
    public void setNode(String s) {
        String[] tr = s.split("/");
        for (String t : tr) {
            if (isNumeric(s)) {
                jn = jn.get(Integer.parseInt(t));
            } else {
                jn = jn.get(t);
            }
        }
    }
}