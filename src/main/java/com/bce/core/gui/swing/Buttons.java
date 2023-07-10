package com.bce.core.gui.swing;

import com.bce.core.gui.swing.buttons.BEAbsButton;
import com.bce.core.gui.swing.buttons.ButtonGoGame;
import com.bce.core.gui.swing.buttons.ButtonGoHome;

enum Buttons {
    BTN_GOTO_GAME_PANEL(new ButtonGoGame()),
    BTN_GOTO_HOME_PANEL(new ButtonGoHome());

    private final BEAbsButton jb;

    Buttons(BEAbsButton jb) {this.jb = jb;}

    public BEAbsButton button() {
        return jb;
    }
}
