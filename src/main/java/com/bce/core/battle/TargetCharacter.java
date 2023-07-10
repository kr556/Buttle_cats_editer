package com.bce.core.battle;

import com.bce.core.object.Assign;

public record TargetCharacter(Assign assign, int id) {
    public int getIdOnStage() {
        return id;
    }

    public Assign getAssign() {
        return assign;
    }
}
