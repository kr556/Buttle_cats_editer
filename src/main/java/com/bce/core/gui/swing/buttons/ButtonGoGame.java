package com.bce.core.gui.swing.buttons;

import com.bce.core.battle.Battle;
import com.bce.core.gui.DrawAssets;
import com.bce.core.gui.swing.GamePanel;
import com.bce.core.gui.swing.HomePanel;
import com.bce.core.gui.swing.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.bce.core.gui.DrawAssets.theme;
import static com.bce.core.gui.swing.MainFrame.*;

public class ButtonGoGame extends BEAbsButton implements ActionListener {
    public ButtonGoGame() {
        super();
        setBackground(theme);
        setBorder(new LineBorder(new Color(0xffffff - theme.getRGB()),1));
        setForeground(new Color(0xffffff - theme.getRGB()));
        setText("Battle start!");
        setActionCommand(getText());

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        now.setVisible(false);
        now.removeAll();
        GamePanel.gamePanel = new GamePanel();
        now.add(GamePanel.gamePanel);
        now.setVisible(true);

        Battle.start();
    }
}
