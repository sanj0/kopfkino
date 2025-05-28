package kopfkino.gui;

import kopfkino.Align;
import kopfkino.BoundingBox;
import kopfkino.Vector2f;
import kopfkino.KopfkinoGraphics;

public class Label extends AbstractLabel {
    public Label(BoundingBox bounds, String text, float minWidth, Align.AlignX alignX) {
        super(bounds, text, minWidth, alignX);
    }

    public Label(float fontSize, String text, Align.AlignX alignX) {
        super(fontSize, text, alignX);
    }

    public Label(float fontSize, String text) {
        super(fontSize, text);
    }

    public Label(String text) {
        super(text);
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (getFont() != null) {
            graphics.setFont(getFont());
        }
        graphics.setColor(getForegroundColor());
        graphics.clipRect(getBounds());
        Align align = getAlign();
        float x = align.getAlignX() == Align.AlignX.LEFT ? getBounds().getX()
            : align.getAlignX() == Align.AlignX.RIGHT ? getBounds().getMaxX()
            : getBounds().getX() +getBounds().getWidth() * 0.5f;
        float y = align.getAlignY() == Align.AlignY.TOP ? getBounds().getY()
            : align.getAlignY() == Align.AlignY.BOTTOM ? getBounds().getMaxY()
            : getBounds().getY() + getBounds().getHeight() * 0.5f;
        graphics.drawString(getText(), new Vector2f(x, y), align);
    }
}
