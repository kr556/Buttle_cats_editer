import com.bce.core.battle.Battle;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import sframeparts.swing.SJFrame;
import sframeparts.swing.SJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Hoge {
    static BufferedImage bf;

    static {
        try {
            bf = ImageIO.read(new File("temp/dummy.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Battle.start();

        SJFrame f = new SJFrame()
                .setSJBounds(10,10,500,500)
                .setSJDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
                .setSJVisible(true);

        f.add(new Draw()
                .setSJBounds(0,0,500,500)
                .setSJBackground(Color.WHITE)
                .setSJVisible(true)
                .setSJLayout(null));
    }

    static class Draw extends SJPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;

            AffineTransform af = new AffineTransform();

            //g2.drawRect(0,0,100,100);
            //g2.drawImage(bf, af, null);
            g2.drawImage(bf, af,  null);

            Battle.loop();
            repaint();
        }
    }
}
