package com.bce.core.gui.swing.gamerender;

/**どの方向に座標のプラスが向かうか指定します。*/
public enum PlotMode {
    X_RIGHT_Y_UP((byte)     0b0101_0000),
    X_LEFT_Y_UP((byte)      0b1001_0000),
    X_RIGHT_Y_DOWN((byte)   0b0110_0000),
    X_LEFT_Y_DOWN((byte)    0b1010_0000);

    private final byte b;

    PlotMode(byte b) {
        this.b = b;
    }

    byte getByte() {
        return b;
    }
}
