package com.bce.core.gui.swing;

import static com.bce.core.gui.DrawAssets.*;

import com.bce.core.assets.TempAssets;
import com.bce.core.battle.Battle;
import com.bce.core.battle.Stage;

import com.bce.core.battle.StageIO;
import com.bce.core.gui.swing.buttons.ButtonGoHome;
import com.bce.core.gui.swing.gamerender.MouseStreamLabel;
import com.bce.core.gui.swing.gamerender.RenderCanvas;
import com.bce.core.gui.swing.gamerender.GameUILabel;
import com.bce.core.var.Vec2DLine;
import sframeparts.swing.SJButton;
import sframeparts.swing.SJLabel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class GamePanel extends JPanel {
    public static Vec2DLine first = new Vec2DLine(0d,0d),
            now = new Vec2DLine(0d, 0d),
            end = new Vec2DLine(0d, 0d);

    public static GamePanel gamePanel;

    private HashMap<Buttons, SJButton> hashBtn = new HashMap<>();
    private SJLabel pl, ren, sta;
    private Stage stg;

    public GamePanel() {
        try {
            Stage s = new StageIO("stage/" + TempAssets.now_selecting_stageName + ".json").read();
            stg = Stage.createStage(() -> s)
                    .formEnd()
                    .toStage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setBounds(0,0, width, height);
        setLayout(null);
        setBackground(theme);
        setVisible(true);

        SJLabel jl = new SJLabel()
                .setSJBounds(getBounds())
                .setSJBackground(theme)
                .setSJLayout(null);
        pl = new SJLabel()
                .setSJBounds((int) ((double)getWidth() * 0.1),(int) ((double)getHeight() * 0.1), (int) ((double)getWidth() * 0.8), (int) ((double)getHeight() * 0.8))
                .setSJBackground(Color.WHITE)
                .setSJOpaque(true);

        add(jl);
        jl.add(pl);

        GameUILabel.gameUI = new GameUILabel();
        RenderCanvas.renderer = new RenderCanvas(stg);

        RenderCanvas.renderer.setBounds(getBounds());

        pl.add(new MouseStreamLabel().setSJBounds(getBounds()));
        pl.add(GameUILabel.gameUI.setSJBounds(getBounds()));
        pl.add(RenderCanvas.renderer);

        jl.add(new ButtonGoHome().setSJBounds(10,10,200,30));

        new GameLoop().newLoop();
    }

    private class GameLoop implements Runnable {
        public void newLoop() {
            new Thread(this).start();
        }

        @Override
        public void run() {
            while (Battle.battleNow) {
                Battle.loop();
                Battle.frameAdvance();
                repaint();
            }
        }
    }
}
