package com.bce.core.gui.swing.gamerender;

/**{@link RelativeGraphics#setPlotMode}で描画する座標の原点の位置を設定する際に使うパラメータです。*/
public enum OPoint {
    O_CENTER((byte)         0b0000_0001),
    O_TOP_LEFT((byte)       0b0000_0010),
    O_UNDER_LEFT((byte)     0b0000_0100),
    O_SELF((byte)           0b0000_1000);

    private final byte b;

    OPoint(byte b) {
        this.b = b;
    }

    int getByte() {
        return b;
    }
}
