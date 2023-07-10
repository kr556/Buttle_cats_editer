package com.bce.core.battle;

import com.bce.core.io.ImageReader;
import com.bce.core.io.JsonReader;
import com.bce.core.object.castle.Castle;
import com.bce.core.object.charas.DummyCharacter;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

import static com.bce.core.object.Assign.CAT;
import static com.bce.core.object.Assign.ENEMY;

public class StageIO extends JsonReader {
    /**
     * ステージパラメータを格納したJsonNodeをステージに変換するインスタンスを生成します。
     * @param jn ステージパラメータ
     */
    public StageIO(JsonNode jn) {
        super(jn);
    }

    /**
     * ステージファイルをJsonNodeとして読み込めるようにします。これを格納したJsonNodeをステージに変換するインスタンスを生成します。
     * @param f ファイルパス
     * @throws IOException ファイルが存在しない、またはアクセスできない場合
     */
    public StageIO(File f) throws IOException {
        super(f);
    }

    /**
     * パッケージ、またはresource内のステージファイルをJsonNodeとして読み込めるようにします。これを格納したJsonNodeをステージに変換するインスタンスを生成します。
     * @param fileName たとえば、hoge.jar内のresource/stage/hoge.jsonを読み込む場合、stage/hoge.jsonを渡します
     * @throws IOException ファイルが存在しない、またはアクセスできない場合
     */
    public StageIO(String fileName) throws IOException {
        super(fileName);
    }

    /**
     * 格納しているJsonNodeからステージのインスタンスを復元します。
     * @return stage name(file path)から読み込みます。
     */
    @SuppressWarnings({"", "deprecation"})
    public Stage read() {
        Stage st = new Stage();

        // name
        // ステージ幅
        // 味方と敵の出撃制限
        // 味方と敵の城の名前
        // バックグラウンドのファイル名
        st.setName(readTree("size").textValue());
        st.setWhidth(readTree("size").asInt());
        st.setLimit(CAT, readTree("limit-c").asInt());
        st.setLimit(ENEMY, readTree("limit-e").asInt());
        st.setCastle(
                new Castle(CAT, readTree("castle-c").textValue()),
                new Castle(ENEMY , readTree("castle-e").textValue()));
        st.setBackground(ImageReader.read("stage/back-ground/" + readTree("back-ground").textValue() + ".png"));
        for (int i = 0; i < st.getLimitC(); i++) {
            st.getCharas(CAT).put( i, DummyCharacter.create());
        }
        for (int i = 0; i < st.getLimitE(); i++) {
            st.getCharas(ENEMY).put( i, DummyCharacter.create());
        }
        return st;
    }
}
