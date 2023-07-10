package com.bce.core.gui.swing.gamerender;

import com.bce.core.var.gui.MouseMat4;
import com.bce.core.var.Vec2DLine;
import sframeparts.swing.SJLabel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import static com.bce.core.gui.swing.GamePanel.*;
import static com.bce.core.gui.swing.gamerender.RenderCanvas.mag;
import static com.bce.core.gui.swing.gamerender.RenderCanvas.renderer;

/**ゲーム中のマウスの操作を検知するためのクラスです*/
public class MouseStreamLabel extends SJLabel implements MouseListener, MouseWheelListener {
    private MouseMat4 spd_vec = new MouseMat4(0d, 0d, 0d, 0d);
    private Vec2DLine fp = new Vec2DLine(0d, 0d);

    public MouseStreamLabel() {
        setBackground(Color.blue);
        setVisible(true);
        addMouseWheelListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        first.set(e.getPoint());
        fp.set(e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        now.set(e.getPoint());
        int x = renderer.getX() + (int)(e.getX() - fp.getX()) > 0 || (renderer.getX() + (int)(e.getX() - fp.getX())) < 100 - renderer.stg.getBackground().getWidth() ?
                renderer.getX() : (renderer.getX() + (int)(e.getX() - fp.getX()));
        int y = renderer.getY();

        renderer.setLocation(x, y);

        fp.set(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        end.set(e.getPoint());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        RenderCanvas.mag = mag + e.getPreciseWheelRotation() / 1.5;
        if (mag > 10) mag = 10;
        else if(mag < 1) mag = 1;
    }
}
