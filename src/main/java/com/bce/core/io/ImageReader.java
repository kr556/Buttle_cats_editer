package com.bce.core.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageReader {
    private ImageReader() {}

    public static BufferedImage read(File f) {
        BufferedImage re;
        try {
            re = ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException("image not fount / " + f);
        }
        return re;
    }

    public static BufferedImage read(String fileName) {
        BufferedImage re;
        InputStream ip = ImageReader.class.getClassLoader().getResourceAsStream(fileName);
        try {
            re = ImageIO.read(ip);
        } catch (IOException e) {
            throw new RuntimeException("image not fount / " + fileName);
        }
        return re;
    }

    public static byte[][][] readByte(String fileName) {
        BufferedImage bf;
        InputStream ip = ImageReader.class.getClassLoader().getResourceAsStream(fileName);

        try {
            bf = ImageIO.read(ip);
        } catch (IOException e) {
            throw new RuntimeException("image not fount / " + fileName);
        }

        byte[][][] re = new byte[bf.getHeight()][bf.getWidth()][4];

        try {
            for (int y = 0; y < bf.getHeight(); y++) {
                for (int x = 0; x < bf.getWidth(); x++) {
                    int a = (bf.getRGB(x,y) >> 24) & 0xff,
                            r = (bf.getRGB(x, y) >> 16) & 0xff,
                            g = (bf.getRGB(x,y) >> 8) & 0xff,
                            b = (bf.getRGB(x,y)) & 0xff;

                    re[y][x][0] = (byte) r;
                    re[y][x][1] = (byte) g;
                    re[y][x][2] = (byte) b;
                    re[y][x][3] = (byte) a;
                }
            }
        } catch (Exception e) {
            re = new byte[][][]{
                    {{-0x80},{-0x80},{-0x80},{0x7f}},   {{0x7f},{0x7f},{0x7f},{0x7f}},
                    {{0x7f}},{{0x7f},{0x7f},{0x7f}},    {{-0x80},{-0x80},{-0x80},{0x7f}}
            };
            throw new RuntimeException("");
        }

        return re;
    }
}
