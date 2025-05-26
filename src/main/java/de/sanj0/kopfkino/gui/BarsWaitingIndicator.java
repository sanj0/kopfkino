package de.sanj0.kopfkino.gui;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.KopfkinoGraphics;

/**
 * A waiting indicator that shows some bars in a sin-wave.
 */
public class BarsWaitingIndicator extends Component {
    private int nBars = 10;
    private float iota = 0.0f;
    private float delta = 0.05f;

    public BarsWaitingIndicator(BoundingBox bounds) {
        super(bounds);
    }

    public BarsWaitingIndicator(Dimensions size) {
        super(new BoundingBox(Vector2f.zero(), size));
    }

    @Override
    public boolean keepsFocus() {
        return false;
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        iota += delta;
        float barWidth = getBounds().getWidth() / nBars;
        float baseY = getBounds().getY() + getBounds().getHeight() * 0.5f;
        graphics.setColor(getBackgroundColor());
        for (int i = 0; i < nBars; i++) {
            float phase = (float) Math.sin(iota + (float) i/nBars * 6f);
            float height = getBounds().getHeight() * 0.5f * phase;
            float x = getBounds().getX() + barWidth * i;
            float y;
            if (height < 0) {
                y = baseY  + height;
                height = -height;
            } else {
                y = baseY;
            }
            graphics.drawRect(new BoundingBox(x, y, barWidth, height));
        }
        graphics.setColor(getForegroundColor());
        graphics.drawLine(new Vector2f(getBounds().getX(), baseY), new Vector2f(getBounds().getMaxX(), baseY));
    }
}
