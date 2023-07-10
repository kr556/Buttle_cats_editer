package com.bce.core.battle;

import com.fasterxml.jackson.databind.JsonNode;

public class StageForm {
    Stage stg;

    static StageForm suport(Stage stage) {
        return new StageForm(stage);
    }

    static StageForm suport(JsonNode jn) {
        return new StageForm(jn);
    }

    StageForm(Stage stage) {
        stg = stage;
    }

    StageForm(JsonNode jn) {
        stg = new StageIO(jn).read();
    }

    public FormEnd formEnd() {
        return new FormEnd();
    }

    public FormWidth form() {
        return new FormWidth();
    }

    public class FormWidth {

    }

    public class FormEnd {
        public FormEnd() {}

        public Stage toStage() {
            return stg;
        }
    }
}
