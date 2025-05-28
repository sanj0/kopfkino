package kopfkino.gui;

import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.KopfkinoGraphics;
import kopfkino.Vector2f;

/**
 * Just a block.
 */
public class Block extends Component {
    private float cornerRadius = 0;

    public Block(BoundingBox bounds) {
        super(bounds);
    }

    public Block(Dimensions size) {
        super(new BoundingBox(Vector2f.zero(), size));
    }

    @Override
    public boolean keepsFocus() {
        return false;
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        graphics.drawRoundRect(getBounds(), cornerRadius, cornerRadius);
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}
