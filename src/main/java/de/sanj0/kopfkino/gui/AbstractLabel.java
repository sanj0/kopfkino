package de.sanj0.kopfkino.gui;

import de.sanj0.kopfkino.Align;
import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.KopfkinoGraphics;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public abstract class AbstractLabel extends Component {
    private String text;
    private float minWidth;
    private Align align;

    public AbstractLabel(BoundingBox bounds, String text, float minWidth, Align.AlignX alignX) {
        super(bounds);
        this.text = text;
        this.align = new Align(alignX, Align.AlignY.CENTRE);
        this.minWidth = minWidth;
        updateSize();
    }

    public AbstractLabel(float fontSize, String text, Align.AlignX alignX) {
        this(new BoundingBox(Vector2f.zero(), Dimensions.zero()), text, -1, alignX);
        setFont(KopfkinoGraphics.DEFAULT_FONT.deriveFont(fontSize));
        updateSize();
    }

    public AbstractLabel(float fontSize, String text) {
        this(fontSize, text, Align.AlignX.CENTRE);
    }

    public AbstractLabel(String text) {
        this(KopfkinoGraphics.DEFAULT_FONT.getSize(), text, Align.AlignX.CENTRE);
    }

    @Override
    public boolean keepsFocus() {
        return false;
    }

    @Override
    public abstract void render(KopfkinoGraphics graphics);

    private void updateSize() {
        Font font = getFont() == null ? KopfkinoGraphics.DEFAULT_FONT : getFont();
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        Rectangle2D bounds = font.getStringBounds(getText(), frc);
        getBounds().setWidth(Math.max((float) bounds.getWidth(), minWidth));
        getBounds().setHeight((float) bounds.getHeight());
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        updateSize();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        updateSize();
    }

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public float getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(float minWidth) {
        this.minWidth = minWidth;
    }
}
