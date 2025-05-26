package kopfkino.gui;

import de.sanj0.kopfkino.*;
import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.KopfkinoGraphics;

public class TextArea extends AbstractTextArea {
    private float cornerRadius = 10;

    public TextArea(BoundingBox bounds, String text) {
        super(bounds, text);
    }

    public TextArea(Dimensions size, String text) {
        super(size, text);
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (getFont() != null) {
            graphics.setFont(getFont());
        } else {
            graphics.setFont(graphics.getFont().deriveFont(getBounds().getHeight() / 1.5f));
        }
        graphics.setColor(getBackgroundColor());
        graphics.outlineRoundRect(getBounds(), cornerRadius, cornerRadius);
        renderText(graphics);
        renderCaret(graphics);
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}
