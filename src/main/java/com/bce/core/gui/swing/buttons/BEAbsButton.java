package com.bce.core.gui.swing.buttons;

import com.bce.core.gui.DrawAssets;
import sframeparts.swing.SJButton;

import javax.swing.*;

public abstract class BEAbsButton extends SJButton {
    public BEAbsButton() {
        setFont(DrawAssets.string_font);
    }
}
