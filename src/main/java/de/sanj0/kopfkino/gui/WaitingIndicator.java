package de.sanj0.kopfkino.gui;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.KopfkinoGraphics;
import de.sanj0.kopfkino.utils.ColorUtils;

import java.awt.geom.AffineTransform;

public class WaitingIndicator extends Component {
    private float angle = 0;
    private float angleDelta = 0.03f;
    public WaitingIndicator(BoundingBox bounds) {
        super(bounds);
    }

    public WaitingIndicator(Dimensions size) {
        super(new BoundingBox(Vector2f.zero(), size));
    }

    @Override
    public boolean keepsFocus() {
        return false;
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        AffineTransform prevTransform = graphics.getGraphics2D().getTransform();
        angle += angleDelta;
        float blendRatio = (float) Math.abs(Math.sin(angle * 2) % 1);
        graphics.rotateAround(getBounds().getCentre(), angle);
        graphics.setColor(ColorUtils.blend(getBackgroundColor(), getForegroundColor(), blendRatio));
        graphics.drawRect(getBounds());

        graphics.rotateAround(getBounds().getCentre(), -angle * 2);
        graphics.setColor(ColorUtils.blend(getBackgroundColor(), getForegroundColor(), 1 - blendRatio));
        graphics.outlineRect(getBounds());

        graphics.getGraphics2D().setTransform(prevTransform);
    }
}
