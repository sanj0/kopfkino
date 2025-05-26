package kopfkino.gui;

import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.Vector2f;
import kopfkino.KopfkinoGraphics;

import java.util.function.Consumer;

/**
 * A simple checkbox inside a rounded rectangle.
 */
public class CheckBox extends AbstractCheckBox {
    private float cornerRadius = 5;

    public CheckBox(BoundingBox bounds, boolean active, Consumer<Boolean> onToggle) {
        super(bounds, active, onToggle);
    }

    public CheckBox(Dimensions size, boolean active, Consumer<Boolean> onToggle) {
        this(new BoundingBox(Vector2f.zero(), size), active, onToggle);
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (isActive()) {
            graphics.setColor(getBackgroundColor());
            graphics.drawRoundRect(getBounds(), cornerRadius, cornerRadius);
        }
        graphics.setColor(getForegroundColor());
        graphics.outlineRoundRect(getBounds(), cornerRadius, cornerRadius);
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}
