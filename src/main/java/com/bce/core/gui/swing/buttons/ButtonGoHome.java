package com.bce.core.gui.swing.buttons;

import com.bce.core.battle.Battle;
import com.bce.core.gui.swing.HomePanel;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.bce.core.gui.DrawAssets.theme;
import static com.bce.core.gui.swing.MainFrame.now;

public class ButtonGoHome extends BEAbsButton implements ActionListener {
    public ButtonGoHome() {
        setBackground(theme);
        setBorder(new LineBorder(new Color(0xffffff - theme.getRGB()),1));
        setForeground(new Color(0xffffff - theme.getRGB()));
        setText("back");
        setActionCommand(getText());

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        now.setVisible(false);
        now.removeAll();
        now.add(new HomePanel());
        now.setVisible(true);
        Battle.end();
    }
}
