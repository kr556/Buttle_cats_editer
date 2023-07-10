package com.bce.core.object;

import static com.bce.core.object.CharacterState.*;

/**
 * キャラクターのフレームを格納します。現在どの状態のフレームかも格納してます。
 */
public class FrameObject {
    private long walking,
            stop,
            attack,
            kb;

    FrameObject(CharacterState state) {
        FrameInit(state);
    }

    public void FrameInit(CharacterState state) {
        walking = 0;
        stop = 0;
        attack = 0;
        kb = 0;
    }

    public long getFrame(CharacterState state) {
        return switch (state) {
            case WALKING -> walking;
            case STOP -> stop;
            case ATTACK -> attack;
            case KB -> kb;
        };
    }

    void setFrame(CharacterState state, long frame) {
        switch (state) {
            case WALKING -> walking = frame;
            case STOP -> stop = frame;
            case ATTACK -> attack = frame;
            case KB -> kb = frame;
        }
    }

    void frameAdvance(CharacterState state) {
        switch (state) {
            case WALKING -> walking++;
            case STOP -> stop++;
            case ATTACK -> attack++;
            case KB -> kb++;
        }
    }

    @Override
    public String toString() {
        return "[" +
                "walking: " + walking +
                ", stop: " + stop +
                ", attack: " + attack +
                ", kb: " + kb +
                ']';
    }
}
