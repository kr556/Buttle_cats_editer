package com.bce.core.battle.event;

import com.bce.core.anime.AbsEffect;

public abstract class AbsEvent {
    public int frame = 0;

    public AbsEffect eff;

    public AbsEvent(int ... character_ic) {}

    /**
     * このイベントが発動したときの処理
     */
    public abstract void invoke();

    /**
     * このイベントが1フレーム毎に行う処理
     */
    public abstract void loop();

    /**
     * このイベントが終了したときの処理
     */
    public abstract void fin();
}
