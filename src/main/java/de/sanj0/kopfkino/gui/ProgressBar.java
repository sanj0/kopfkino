package de.sanj0.kopfkino.gui;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.KopfkinoGraphics;

public class ProgressBar extends Component {
    private float value;
    private float cornerRadius = 10;

    public ProgressBar(BoundingBox bounds, float value) {
        super(bounds);
        this.value = value;
    }

    public ProgressBar(Dimensions size, float value) {
        super(new BoundingBox(Vector2f.zero(), size));
        this.value = value;
    }

    @Override
    public boolean keepsFocus() {
        return false;
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        graphics.setColor(getBackgroundColor());
        graphics.drawRoundRect(
            new BoundingBox(
                getBounds().getPosition(),
                new Dimensions(getBounds().getWidth() * value, getBounds().getHeight())
            ),
            cornerRadius,
            cornerRadius
        );

        graphics.setColor(getForegroundColor());
        graphics.outlineRoundRect(getBounds(), cornerRadius, cornerRadius);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}
