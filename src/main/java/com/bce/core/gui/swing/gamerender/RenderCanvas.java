package com.bce.core.gui.swing.gamerender;

import com.bce.core.battle.Stage;
import sframeparts.swing.SJLabel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderCanvas extends Canvas {
    public Stage stg;
    /**倍率*/
    public static double mag = 1d;

    public static RenderCanvas renderer;

    public RenderCanvas(Stage s) {
        stg = s;
    }

    /**1ループだけの描画の処理です。絶対にここで{@link #repaint()}を使わないでください*/
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D back_img = (Graphics2D) g,
                string_draw,
                chara;

        back_img.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        back_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 背景の描画|>
        Color cd, ch;

        BufferedImage bf;
        if (stg.getBackground().getHeight() == 512) {
            bf = new BufferedImage(768, 512, BufferedImage.TYPE_INT_RGB);
            Graphics2D ig2 = bf.createGraphics();
            ig2.drawImage(stg.getBackground(), 0, 0, 1024, 512, null);
            cd = new Color(bf.getRGB(0,0));
            ch = new Color(bf.getRGB(0,511));
        } else {
            bf = new BufferedImage(768, 1024, BufferedImage.TYPE_INT_RGB);
            Graphics2D ig2 = bf.createGraphics();
            ig2.drawImage(stg.getBackground(),0, 0, 768, 1024, null);
            cd = new Color(bf.getRGB(0,0));
            ch = new Color(bf.getRGB(0,1023));
        }

        back_img.setColor(cd);
        back_img.fillRect(0,0, getWidth(), getHeight() / 2);
        back_img.setColor(ch);
        back_img.fillRect(0, 512, getWidth(), getHeight() / 2);


        BufferedImage i_bf = new BufferedImage((int) (bf.getWidth() * mag), (int) (bf.getHeight() * mag), BufferedImage.TYPE_INT_RGB);

        i_bf.createGraphics().drawImage(bf.getScaledInstance(i_bf.getWidth(), i_bf.getHeight(),BufferedImage.TYPE_INT_RGB), 0, 0, null);

        for (int i = 0; i < stg.getWidth() + i_bf.getWidth(); i += i_bf.getWidth() ) {
            back_img.drawImage(i_bf, i, 200, null);
        }

        //g2.drawImage(stg.getCastleC);
        back_img.setColor(Color.RED);
        back_img.drawRect(120,300, 128,256);
        back_img.setColor(Color.YELLOW);
        back_img.drawRect(1200,300, 128,256);
        //  <|
    }
}
