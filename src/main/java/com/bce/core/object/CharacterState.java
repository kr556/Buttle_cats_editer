package com.bce.core.object;

public enum CharacterState {
    WALKING("walking"),
    STOP("stop"),
    ATTACK("attack"),
    KB("knock back");

    private final String str;

    CharacterState(String s) {
        str = s;
    }

    @Override
    public String toString() {
        return str;
    }
}
