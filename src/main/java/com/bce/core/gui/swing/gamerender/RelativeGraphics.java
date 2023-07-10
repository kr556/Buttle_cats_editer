package com.bce.core.gui.swing.gamerender;

import com.bce.core.var.*;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.Objects;

public class RelativeGraphics {
    private Graphics2D g2;
    private Rectangle viewPort;
    private OPoint o_point;
    private PlotMode plotMode;
    private transient Vec2DLine _retV2l;
    private transient Vec2DPoint _retV2p;
    private transient Vec2PointVec2Line _retV2PpV2l;

    /**
     * 相対座標で表したベクトルを絶対座標にします
     * @param parent g2が表示されてる親コンポーネント
     * @param x x座標
     * @param y y座標
     * @param xComp x成分
     * @param yComp y成分
     * @return 変形されたベクトルが返されます。<br>
     */
    public static Vec2PointVec2Line toAbs(Rectangle parent, double x, double y, double xComp, double yComp) {
        double _x = parent.getX(),
                _y = parent.getY(),
                _w = parent.getWidth(),
                _h = parent.getHeight();
        var l2 = new Vec2DLine(_w * xComp , _h * yComp);
        var p2 = new Vec2DPoint(_w * x, _h * y);
        return new Vec2PointVec2Line(p2, l2);
    }

    /**
     * 相対座標で表したベクトルを絶対座標にします
     * @param parent g2が表示されてる親コンポーネント
     * @param x x座標
     * @param y y座標
     * @return 変形されたベクトルが返されます。<br>
     */
    public static Vec2DPoint toAbsPoint(Rectangle parent, double x, double y) {
        double _w = parent.getWidth(),
                _h = parent.getHeight();
        return new Vec2DPoint(_w * x, _h * y);
    }

    private Vec2DPoint pointConvertPlotMode(double x, double y) {
        return toAbsPoint(x, y);
    }
    
    /**
     * グラフのx軸、y軸の方向に応じて座標を決めます。
     * @param v2point 相対的な座標。
     * @return {@link RelativeGraphics#plotMode}に基づいて座標を変換します。
     */
    private Vec2DPoint pointConvertPlotMode(Vec2DPoint v2point) {
        return switch (plotMode) {
            case X_RIGHT_Y_UP -> new Vec2DPoint(v2point.getX(), viewPort.getHeight() - v2point.getY());
            case X_LEFT_Y_UP -> new Vec2DPoint(-v2point.getX(), viewPort.getHeight() - v2point.getY());
            case X_RIGHT_Y_DOWN -> new Vec2DPoint( v2point.getX(), v2point.getY());
            case X_LEFT_Y_DOWN -> new Vec2DPoint(-v2point.getX(), v2point.getY());
        };
    }

    /**
     * グラフのx軸、y軸の方向に応じて座標を決めます。
     * @param v2line 相対的な座標。
     * @return {@link RelativeGraphics#plotMode}に基づいて座標を変換します。
     */
    private Vec2DLine pointConvertPlotMode(Vec2DLine v2line) {
        switch (plotMode) {
            case X_RIGHT_Y_UP -> _retV2l.set(   v2line.getX(), -v2line.getY());
            case X_LEFT_Y_UP -> _retV2l.set(   -v2line.getX(), -v2line.getY());
            case X_RIGHT_Y_DOWN -> _retV2l.set( v2line.getX(),  v2line.getY());
            case X_LEFT_Y_DOWN -> _retV2l.set( -v2line.getX(),  v2line.getY());
        }
        if (plotMode == null) {
            throw new NullPointerException(RelativeGraphics.class + " viewPort setting is \"null\"");
        }
        return _retV2l;
    }

    /**
     * グラフのx軸、y軸の方向に応じて座標を決めます。
     * @param v2p2 絶対的な座標。
     * @return {@link RelativeGraphics#plotMode}に基づいて座標を変換します。
     */
    private Vec2PointVec2Line pointConvertPlotMode(Vec2PointVec2Line v2p2, Image src, ImageObserver ob) {
        final double w_0 = src.getWidth(ob),
                h_0 = src.getHeight(ob);
        _retV2p = v2p2.getVec2Point();
        _retV2l = v2p2.getVec2Line();
        Vec2DPoint vp = pointConvertPlotMode(_retV2p);

        switch (plotMode) {
            case X_RIGHT_Y_UP -> _retV2p.setY(_retV2l.getY() - h_0 + _retV2p.getY());
            case X_LEFT_Y_UP -> {
                _retV2p.setX(_retV2l.getX() - w_0 - _retV2p.getX());
                _retV2p.setY(_retV2l.getY() - h_0 + _retV2p.getY());
            }
            case X_RIGHT_Y_DOWN -> {}
            case X_LEFT_Y_DOWN -> _retV2p.setX(_retV2l.getX() - w_0 + _retV2p.getX());
        }

        _retV2PpV2l.set(pointConvertPlotMode(_retV2p), v2p2.getVec2Line());

        if (plotMode == null) {
            throw new NullPointerException(RelativeGraphics.class + " viewPort setting is \"null\"");
        }
        return _retV2PpV2l;
    }

    public RelativeGraphics(Graphics g, Rectangle parent) {
        g2 = (Graphics2D) g;
        viewPort = parent;
        plotMode = PlotMode.X_RIGHT_Y_DOWN;
        o_point = OPoint.O_TOP_LEFT;
        _retV2p = new Vec2DPoint(0,0);
        _retV2l = new Vec2DLine(0,0);
        _retV2PpV2l = new Vec2PointVec2Line(0,0,0,0);
    }

    public RelativeGraphics(Graphics2D g2,Rectangle parent) {
        this.g2 = g2;
        viewPort = parent;
        plotMode = PlotMode.X_RIGHT_Y_DOWN;
        o_point = OPoint.O_TOP_LEFT;
        _retV2p = new Vec2DPoint(0,0);
        _retV2l = new Vec2DLine(0,0);
        _retV2PpV2l = new Vec2PointVec2Line(0,0,0,0);
    }

    public void setViewPort(int x, int y, int w, int h) {
        viewPort = new Rectangle(x, y, w, h);
    }

    public void setViewPort(Rectangle bound) {
        viewPort = bound;
    }

    public void setOPoint(OPoint o) {
        o_point = o;
    }

    public void setPlotMode(PlotMode p) {
        plotMode = p;
    }

    public OPoint getVeiwMode() {
        return o_point;
    }

    public PlotMode getPlotMode() {
        return plotMode;
    }

    public void draw(Shape s) {
        g2.draw(s);
    }


    public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
        return g2.drawImage(img, xform, obs);
    }

    public void drawImage(BufferedImage img, BufferedImageOp op, double x, double y) {
        var p = toAbsPoint(x, y);
        p = pointConvertPlotMode(p);
        g2.drawImage(img, op, (int) p.getX(), (int) p.getY());
    }


    public boolean drawImage(Image img, double x, double y, ImageObserver observer) {
        var p = toAbsPoint(x, y);
        p = pointConvertPlotMode(p);
        return g2.drawImage(img, (int) p.getX(), (int) p.getY(), observer);
    }


    public boolean drawImage(Image img, double x, double y, double w, double h, ImageObserver observer) {
        var p = toAbs(x, y, w, h);
        p = pointConvertPlotMode(p, img, observer);
        return g2.drawImage(img, (int) p.getX(), (int) p.getY(), (int) p.getWigth(), (int) p.getHeight(), observer);
    }


    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        return g2.drawImage(img, x, y, bgcolor, observer);
    }


    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        return g2.drawImage(img, x, y, width, height, bgcolor, observer);
    }


    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
        return g2.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
    }


    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
        return g2.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
    }

    public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
        g2.drawRenderedImage(img, xform);
    }


    public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
        g2.drawRenderableImage(img, xform);
    }


    public void drawString(String str, int x, int y) {
        g2.drawString(str, x, y);
    }


    public void drawString(String str, float x, float y) {
        g2.drawString(str, x, y);
    }


    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        g2.drawString(iterator, x, y);
    }



    public void dispose() {
        g2.dispose();
    }


    public void drawString(AttributedCharacterIterator iterator, float x, float y) {
        g2.drawString(iterator, x, y);
    }


    public void drawGlyphVector(GlyphVector g, float x, float y) {
        g2.drawGlyphVector(g, x, y);
    }


    public void fill(Shape s) {
        g2.fill(s);
    }


    public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
        return g2.hit(rect, s, onStroke);
    }


    public GraphicsConfiguration getDeviceConfiguration() {
        return g2.getDeviceConfiguration();
    }


    public void setComposite(Composite comp) {
        g2.setComposite(comp);
    }


    public void setPaint(Paint paint) {
        g2.setPaint(paint);
    }


    public void setStroke(Stroke s) {
        g2.setStroke(s);
    }


    public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
        g2.setRenderingHint(hintKey, hintValue);
    }

    public Object getRenderingHint(RenderingHints.Key hintKey) {
        return g2.getRenderingHint(hintKey);
    }


    public void setRenderingHints(Map<?, ?> hints) {
        g2.setRenderingHints(hints);
    }


    public void addRenderingHints(Map<?, ?> hints) {
        g2.addRenderingHints(hints);
    }


    public RenderingHints getRenderingHints() {
        return g2.getRenderingHints();
    }


    public Graphics create() {
        return g2.create();
    }

    public void translate(int x, int y) {
        g2.translate(x, y);
    }

    public Color getColor() {
        return g2.getColor();
    }

    public void setColor(Color c) {
        g2.setColor(c);
    }

    public void setPaintMode() {
        g2.setPaintMode();
    }

    public void setXORMode(Color c1) {
        g2.setXORMode(c1);
    }

    public Font getFont() {
        return g2.getFont();
    }


    public void setFont(Font font) {
        g2.setFont(font);
    }


    public FontMetrics getFontMetrics(Font f) {
        return g2.getFontMetrics(f);
    }

    public Rectangle getClipBounds() {
        return g2.getClipBounds();
    }

    public void clipRect(int x, int y, int width, int height) {
        g2.clipRect(x, y, width, height);
    }


    public void setClip(int x, int y, int width, int height) {
        g2.setClip(x, y, width, height);
    }


    public Shape getClip() {
        return g2.getClip();
    }


    public void setClip(Shape clip) {
        g2.setClip(clip);
    }

    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        g2.copyArea(x, y, width, height, dx, dy);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        g2.drawLine(x1, y1, x2, y2);
    }

    public void fillRect(int x, int y, int width, int height) {
        g2.fillRect(x, y, width, height);
    }

    public void clearRect(int x, int y, int width, int height) {
        g2.clearRect(x, y, width, height);
    }

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g2.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        g2.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void drawOval(int x, int y, int width, int height) {
        g2.drawOval(x, y, width, height);
    }

    public void fillOval(int x, int y, int width, int height) {
        g2.fillOval(x, y, width, height);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g2.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        g2.fillArc(x, y, width, height, startAngle, arcAngle);
    }

    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        g2.drawPolyline(xPoints, yPoints, nPoints);
    }

    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        g2.drawPolygon(xPoints, yPoints, nPoints);
    }

    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        g2.fillPolygon(xPoints, yPoints, nPoints);
    }

    public void translate(double tx, double ty) {
        g2.translate(tx, ty);
    }

    public void rotate(double theta) {
        g2.rotate(theta);
    }

    public void rotate(double theta, double x, double y) {
        g2.rotate(theta, x, y);
    }

    public void scale(double sx, double sy) {
        g2.scale(sx, sy);
    }

    public void shear(double shx, double shy) {
        g2.shear(shx, shy);
    }

    public void transform(AffineTransform Tx) {
        g2.transform(Tx);
    }

    public void setTransform(AffineTransform Tx) {
        g2.setTransform(Tx);
    }

    public AffineTransform getTransform() {
        return g2.getTransform();
    }

    public Paint getPaint() {
        return g2.getPaint();
    }

    public Composite getComposite() {
        return g2.getComposite();
    }

    public void setBackground(Color color) {
        g2.setBackground(color);
    }

    public Color getBackground() {
        return g2.getBackground();
    }

    public Stroke getStroke() {
        return g2.getStroke();
    }

    public void clip(Shape s) {
        g2.clip(s);
    }

    public FontRenderContext getFontRenderContext() {
        return g2.getFontRenderContext();
    }

    /**
     * 相対座標で表したベクトルを絶対座標にします
     * @param x x座標
     * @param y y座標
     * @param xComp x成分
     * @param yComp y成分
     * @return 変形されたベクトルが返されます。<br>
     */
    public Vec2PointVec2Line toAbs(double x, double y, double xComp, double yComp) {
        double _x = viewPort.getX(),
                _y = viewPort.getY(),
                _w = viewPort.getWidth(),
                _h = viewPort.getHeight();
        _retV2p.set(_w * x , _h * y);
        _retV2l.set(_w * xComp, _h * yComp);
        _retV2PpV2l.set(_retV2p, _retV2l);
        return _retV2PpV2l;
    }

    /**
     * 相対座標で表したベクトルを絶対座標にします
     * @param x x座標
     * @param y y座標
     * @return 変形されたベクトルが返されます。<br>
     */
    public Vec2DPoint toAbsPoint(double x, double y) {
        double _w = viewPort.getWidth(),
                _h = viewPort.getHeight();
        _retV2p.set(_w * x, _h * y);
        return _retV2p;
    }

    /**
     * 相対座標で表したベクトルを絶対座標にします
     * @param x x座標
     * @param y y座標
     * @return 変形されたベクトルが返されます。<br>
     */
    public Vec2DLine toAbsLine(double x, double y) {
        double _w = viewPort.getWidth(),
                _h = viewPort.getHeight();
        _retV2l.set(_w * x, _h * y);
        return _retV2l;
    }

    /**
     * 相対座標で表したベクトルを絶対座標にします
     * @param v2 変形したいベクトル
     * @return 変形されたベクトルが返されます。<br>
     */
    public Vec2DPoint toAbs(Vec2DPoint v2) {
        double _w = viewPort.getWidth(),
                _h = viewPort.getHeight();
        _retV2p.set(_w * v2.getX(), _h * v2.getY());
        return _retV2p;
    }

    @Override
    public String toString() {
        return _retV2PpV2l.toString() + "\n" + _retV2p + " : " + _retV2l;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RelativeGraphics that = (RelativeGraphics) o;
        return Objects.equals(g2, that.g2) && Objects.equals(viewPort, that.viewPort) && o_point == that.o_point && plotMode == that.plotMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(g2, viewPort, o_point, plotMode);
    }
}
