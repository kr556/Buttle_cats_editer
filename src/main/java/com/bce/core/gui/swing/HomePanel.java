package com.bce.core.gui.swing;

import sframeparts.swing.SJLabel;

import static com.bce.core.gui.DrawAssets.*;
import static com.bce.core.gui.swing.Buttons.BTN_GOTO_GAME_PANEL;

import javax.swing.*;

import java.util.HashMap;

public class HomePanel extends JPanel {
    public HashMap<Buttons, JButton> hshBtn = new HashMap<>();

    public HomePanel() {
        setBounds(0,0, width, height);
        setVisible(true);
        setLayout(null);
        setBackground(theme);

        hshBtn.put(BTN_GOTO_GAME_PANEL, BTN_GOTO_GAME_PANEL.button());

        BTN_GOTO_GAME_PANEL.button().setBounds(10,10,200, 30);

        JLabel jl = new SJLabel().setSJBounds(getBounds())
                .setSJBackground(theme)
                .setSJVisible(true)
                .setSJLayout(null);
        add(jl.add(hshBtn.get(BTN_GOTO_GAME_PANEL)));
    }
}
