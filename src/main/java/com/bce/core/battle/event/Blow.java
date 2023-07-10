package com.bce.core.battle.event;

public class Blow extends AbsEvent {
    private int frame = 0;

    /**
     * @param frame 発動時間
     */
    public Blow(int frame) {
        super();
    }

    @Override
    public void invoke() {}

    @Override
    public void loop() {}

    @Override
    public void fin() {}
}
