package kopfkino.gui;

import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.KopfkinoGraphics;
import kopfkino.Vector2f;
import kopfkino.utils.ColorUtils;
import kopfkino.utils.MathUtils;

import java.awt.event.MouseWheelEvent;
import java.util.List;

public class ScrollPane extends Container {
    private float scroll = 0;
    private float minScroll = Float.MAX_VALUE;
    private float maxScroll = Float.MIN_VALUE;
    private boolean drawScrollBar = true;

    public ScrollPane(BoundingBox bounds, List<Component> children) {
        super(bounds, children);
    }

    public ScrollPane(BoundingBox bounds) {
        super(bounds);
    }

    public ScrollPane(Dimensions size, List<Component> children) {
        super(size, children);
    }

    public ScrollPane(Dimensions size) {
        super(size);
    }

    @Override
    public void onScroll(MouseWheelEvent e) {
        scroll += (float) e.getPreciseWheelRotation() * 10f;
    }

    @Override
    public Component getFocus(BoundingBox cursor) {
        for (int i = 0; i < size(); i++) {
            Component component = get(i);
            BoundingBox effectiveBounds = new BoundingBox(
                new Vector2f(
                    component.getBounds().getX(),
                    component.getBounds().getY() - scroll),
                component.getBounds().getSize()
            );
            if (effectiveBounds.contains(cursor))
                return component.getFocus(cursor);
        }
        return null;
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        graphics.setColor(getBackgroundColor());
        graphics.drawRect(getBounds());
        computeScroll();
        graphics.clipRect(getBounds());
        graphics.getGraphics2D().translate(0, scroll);
        renderChildren(graphics.copy());
        graphics.getGraphics2D().translate(0, -scroll);
        if (drawScrollBar) {
            Dimensions scrollBarSize = new Dimensions(12, getBounds().getHeight());
            Vector2f scrollBarPos = new Vector2f(getBounds().getMaxX() - scrollBarSize.getWidth(), getBounds().getY());
            graphics.setColor(ColorUtils.withAlpha(getForegroundColor(), 0.5f));
            graphics.drawRect(new BoundingBox(scrollBarPos, scrollBarSize));
            graphics.setColor(getForegroundColor());
            graphics.drawPoint(scrollBarPos.plus(new Vector2f(scrollBarSize.getWidth() * 0.5f, (1 - (scroll - minScroll) / (maxScroll - minScroll)) * scrollBarSize.getHeight())), scrollBarSize.getWidth());
        }
    }

    private void computeScroll() {
        minScroll = Float.MAX_VALUE;
        maxScroll = Float.MIN_VALUE;
        for (int i = 0; i < size(); i++) {
            Component child = get(i);
            minScroll = Float.min(minScroll, getBounds().getMaxY()- child.getBounds().getMaxY());
            maxScroll = Float.max(maxScroll,  getBounds().getY() - child.getBounds().getY());
        }
        scroll = MathUtils.clamp(scroll, minScroll, maxScroll);
    }
}
