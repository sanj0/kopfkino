package kopfkino.gui;

import de.sanj0.kopfkino.*;
import kopfkino.*;
import kopfkino.utils.MathUtils;

public class Slider extends Component {
    private float value;
    private float thickness = 4;
    private boolean dragging = false;

    public Slider(BoundingBox bounds, float value) {
        super(bounds);
        this.value = value;
    }

    public Slider(Dimensions size, float value) {
        this(new BoundingBox(Vector2f.zero(), size), value);
    }

    @Override
    public boolean keepsFocus() {
        return false;
    }

    @Override
    public void onMouseDown(Vector2f cursorPos) {
        BoundingBox cursor = new BoundingBox(cursorPos, Dimensions.one());
        if (genLineBounds().contains(cursor) || genSliderBounds().contains(cursor)) {
            dragging = true;
        }
    }

    @Override
    public void onMouseUp(Vector2f cursorPos) {
        dragging = false;
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (dragging) {
            valueFromCursor(Input.cursor());
        }
        graphics.setColor(getBackgroundColor());
        graphics.drawRect(genLineBounds());
        graphics.setColor(getForegroundColor());
        graphics.drawRect(genSliderBounds());
    }

    private void valueFromCursor(BoundingBox cursor) {
        value = MathUtils.clamp((cursor.getX() - getBounds().getX()) / getBounds().getWidth(), 0, 1);
        if (getOnAction() != null) {
            getOnAction().accept(this);
        }
    }

    private BoundingBox genLineBounds() {
        return BoundingBox.relativeTo(getBounds(), Align.centre(), new Dimensions(getBounds().getWidth(), thickness));
    }

    private BoundingBox genSliderBounds() {
        float sliderX = getBounds().getX() + getBounds().getWidth() * value;
        return new BoundingBox(sliderX - thickness, getBounds().getY(), thickness * 2f, getBounds().getHeight());
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}
