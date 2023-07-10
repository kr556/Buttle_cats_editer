package com.bce.core.gui.swing.gamerender;

import com.bce.core.battle.Battle;
import com.bce.core.gui.DrawAssets;
import sframeparts.swing.SJLabel;

import java.awt.*;

/**ゲームのフレームや召喚ボタンなどのGUIを描画するためのクラスです*/
public class GameUILabel extends SJLabel {
    public static GameUILabel gameUI = new GameUILabel();

    public GameUILabel() {
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g_str = (Graphics2D) g;
        String time_frame = String.format("%.2f", Battle.frame / 30d) + "[s] frame:" + Battle.frame;

        g_str.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g_str.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g_str.setColor(Color.DARK_GRAY);
        g_str.setFont(new Font(DrawAssets.number_font.getName(), Font.PLAIN, 14));
        g_str.drawString(time_frame,30,20);

        g_str.setColor(Color.WHITE);
        g_str.setFont(new Font(DrawAssets.number_font.getName(), Font.BOLD, 14));
        g_str.drawString(time_frame,30,20);
    }
}
