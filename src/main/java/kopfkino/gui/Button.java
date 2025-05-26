package kopfkino.gui;

import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.Vector2f;
import kopfkino.KopfkinoGraphics;

import java.util.function.Consumer;

/**
 * A simple button. Renders text inside a rounded rectangle and get brighter when pressed down.
 */
public class Button extends AbstractButton {
    private String text;
    private float cornerRadius = 10;

    public Button(BoundingBox bounds, String text, Consumer<Vector2f> onClick) {
        super(bounds, onClick);
        this.text = text;
    }

    public Button(Dimensions size, String text, Consumer<Vector2f> onClick) {
        this(new BoundingBox(Vector2f.zero(), size), text, onClick);
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (getFont() != null) {
            graphics.setFont(getFont());
        } else {
            graphics.setFont(graphics.getFont().deriveFont(getBounds().getHeight() * 0.5f));
        }
        graphics.setColor(getBackgroundColor());
        graphics.drawRoundRect(getBounds(), cornerRadius, cornerRadius);
        graphics.setColor(getForegroundColor());
        graphics.drawString(text, getBounds().getCentre(), KopfkinoGraphics.TextAnchor.CENTRE);
    }

    @Override
    public void onMouseUp(Vector2f cursorPos) {
        setBackgroundColor(getBackgroundColor().brighter());
        super.onMouseUp(cursorPos);
    }

    @Override
    public void onMouseDown(Vector2f cursorPos) {
        setBackgroundColor(getBackgroundColor().darker());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}
