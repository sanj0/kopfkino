/*
 *    Copyright 2021 ***REMOVED*** ***REMOVED***
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package kopfkino;

import kopfkino.graphics.RenderConfig;

import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * A convenient {@link java.awt.Graphics2D} delegate.
 */
public class KopfkinoGraphics {
    public static final Font DEFAULT_FONT = new Font(null, Font.PLAIN, 20);

    private final Graphics2D g2d;

    public KopfkinoGraphics(final Graphics2D g2d, final RenderConfig initialRenderConfig) {
        this.g2d = g2d;
        applyConfig(initialRenderConfig);
    }

    public KopfkinoGraphics(final Graphics2D g2d) {
        this(g2d, RenderConfig.createDefault());
    }

    public KopfkinoGraphics copy() {
        return new KopfkinoGraphics((Graphics2D) g2d.create());
    }

    /**
     * Draws a circle of given diameter around the given position.
     *
     * @param point    the point to be drawn
     * @param diameter the diameter of the circle around the given point
     */
    public void drawPoint(final Vector2f point, final float diameter) {
        g2d.fillOval(Math.round(point.getX() - diameter * .5f),
                Math.round(point.getY() - diameter * .5f),
                Math.round(diameter), Math.round(diameter));
    }

    public void drawRect(final BoundingBox box) {
        g2d.fillRect(Math.round(box.getX()), Math.round(box.getY()), Math.round(box.getWidth()), Math.round(box.getHeight()));
    }

    public void outlineRect(final BoundingBox box) {
        g2d.drawRect(Math.round(box.getX()), Math.round(box.getY()), Math.round(box.getWidth()), Math.round(box.getHeight()));
    }

    public void drawOval(final BoundingBox box) {
        g2d.fillOval(Math.round(box.getX()), Math.round(box.getY()), Math.round(box.getWidth()), Math.round(box.getHeight()));
    }

    public void outlineOval(final BoundingBox box) {
        g2d.drawOval(Math.round(box.getX()), Math.round(box.getY()), Math.round(box.getWidth()), Math.round(box.getHeight()));
    }

    public void drawPolygon(final Vector2f... points) {
        renderPolygon(true, points);
    }

    public void outlinePolygon(final Vector2f... points) {
        renderPolygon(false, points);
    }

    private void renderPolygon(final boolean fill, final Vector2f... points) {
        final int nPoints = points.length;
        final int[] xPoints = new int[nPoints];
        final int[] yPoints = new int[nPoints];

        for (int i = 0; i < nPoints; i++) {
            xPoints[i] = Math.round(points[i].getX());
            yPoints[i] = Math.round(points[i].getY());
        }

        if (fill) {
            g2d.fillPolygon(xPoints, yPoints, nPoints);
        } else {
            g2d.drawPolygon(xPoints, yPoints, nPoints);
        }
    }

    public void drawImage(final BufferedImage image, final Vector2f position) {
        g2d.drawImage(image, Math.round(position.getX()), Math.round(position.getY()), null);
    }

    public void drawImage(final BufferedImage image, final BoundingBox box) {
        g2d.drawImage(image, Math.round(box.getX()), Math.round(box.getY()), Math.round(box.getWidth()), Math.round(box.getHeight()), null);
    }

    public void drawRoundRect(final BoundingBox box, final float arcW, final float arcH) {
        g2d.fillRoundRect(Math.round(box.getX()), Math.round(box.getY()), Math.round(box.getWidth()), Math.round(box.getHeight()), Math.round(arcW), Math.round(arcH));
    }

    public void outlineRoundRect(final BoundingBox box, final float arcW, final float arcH) {
        g2d.drawRoundRect(Math.round(box.getX()), Math.round(box.getY()), Math.round(box.getWidth()), Math.round(box.getHeight()), Math.round(arcW), Math.round(arcH));
    }

    public void drawLine(final Vector2f p0, final Vector2f p1) {
        g2d.drawLine(Math.round(p0.getX()), Math.round(p0.getY()), Math.round(p1.getX()), Math.round(p1.getY()));
    }

    /**
     * Renders a multiline text. <br> The lines are separated by {@code \n}. The
     * text is left-aligned, as every new line will start at the same x
     * position.
     *
     * @param text the text to render
     * @param x    the x position of the baseline of the first character
     * @param y    the y position of the baseline of the first character
     */
    public void drawMultilineString(final Object text, final float x, final float y) {
        final int lineHeight = g2d.getFontMetrics().getHeight();
        final String[] lines = text.toString().split("\n");
        final int xRounded = Math.round(x);
        int yRounded = Math.round(y);
        for (final String line : lines)
            g2d.drawString(line, xRounded, yRounded += lineHeight);
    }

    public void drawString(final Object o, final Vector2f position, final TextAnchor anchor) {
        switch (anchor) {
            case TOP_LEFT_CORNER:
                drawString(o, position, new Align(Align.AlignX.LEFT, Align.AlignY.TOP));
                break;
            case BOTTOM_LEFT_CORNER:
                drawString(o, position, new Align(Align.AlignX.LEFT, Align.AlignY.BOTTOM));
                break;
            case TOP_RIGHT_CORNER:
                drawString(o, position, new Align(Align.AlignX.RIGHT, Align.AlignY.TOP));
                break;
            case BOTTOM_RIGHT_CORNER:
                drawString(o, position, new Align(Align.AlignX.RIGHT, Align.AlignY.BOTTOM));
                break;
            case CENTRE:
                drawString(o, position, new Align(Align.AlignX.CENTRE, Align.AlignY.CENTRE));
                break;
        }
    }

    public void drawString(final Object o, final Vector2f position, Align align) {
        final String string = o.toString();
        final FontMetrics fontMetrics = g2d.getFontMetrics();
        final LineMetrics lineMetrics = fontMetrics.getLineMetrics(string, g2d);
        Dimensions size = new Dimensions(fontMetrics.stringWidth(string), 0);
        Vector2f pos = align.relativeTo(position, size);
        g2d.drawString(o.toString(), pos.getX(), pos.getY() + lineMetrics.getDescent());
    }

    public void rotateAround(Vector2f anchor, double theta) {
        g2d.rotate(theta, anchor.getX(), anchor.getY());
    }

    public void applyConfig(final RenderConfig config) {
        config.apply(g2d);
    }

    public void setComposite(final Composite comp) {
        g2d.setComposite(comp);
    }

    public void setPaint(final Paint paint) {
        g2d.setPaint(paint);
    }

    public void setStroke(final Stroke s) {
        g2d.setStroke(s);
    }

    public void setRenderingHint(final RenderingHints.Key hintKey, final Object hintValue) {
        g2d.setRenderingHint(hintKey, hintValue);
    }

    public void setRenderingHints(final Map<?, ?> hints) {
        g2d.setRenderingHints(hints);
    }

    public Color getColor() {
        return g2d.getColor();
    }

    public void setColor(final Color c) {
        g2d.setColor(c);
    }

    public Font getFont() {
        return g2d.getFont();
    }

    public void setFont(final Font font) {
        g2d.setFont(font);
    }

    /**
     * Delegate of {@link Graphics2D#setClip(int, int, int, int)}
     *
     * @param box the new clip
     */
    public void setClip(final BoundingBox box) {
        g2d.setClip(Math.round(box.getX()), Math.round(box.getY()), Math.round(box.getWidth()), Math.round(box.getHeight()));
    }

    /**
     * Delegate of {@link Graphics2D#dispose()}
     */
    public void dispose() {
        g2d.dispose();
    }

    /**
     * Gets {@link #g2d}.
     *
     * @return the value of {@link #g2d}
     */
    public Graphics2D getGraphics2D() {
        return g2d;
    }

    public enum TextAnchor {
        TOP_LEFT_CORNER,
        BOTTOM_LEFT_CORNER,
        TOP_RIGHT_CORNER,
        BOTTOM_RIGHT_CORNER,
        CENTRE
    }
}
