package com.bce.core.gui.swing;

import javax.swing.*;

import java.awt.*;

import static com.bce.core.gui.DrawAssets.*;

public class MainFrame extends JFrame {
    public static JPanel now;

    private static MainFrame mainFrame;

    public static void createFrame() {
        mainFrame = new MainFrame();
    }

    public static JFrame getFrame() {
        return mainFrame;
    }

    public static void run() {
        mainFrame.setVisible(true);
    }

    private MainFrame() {
        setBounds(10,10, width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("battle cats editer");

        now = new HomePanel();

        add(now);
    }
}
