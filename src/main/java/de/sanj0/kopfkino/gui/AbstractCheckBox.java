package de.sanj0.kopfkino.gui;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.KopfkinoGraphics;

import java.util.function.Consumer;

/**
 * An abstract toggleable checkbox. Extend with custom rendering or use {@link AbstractCheckBox} as a simple implementation.
 */
public abstract class AbstractCheckBox extends Component {
    private boolean active;
    private Consumer<Boolean> onToggle;

    public AbstractCheckBox(BoundingBox bounds, boolean active, Consumer<Boolean> onToggle) {
        super(bounds);
        this.active = active;
        this.onToggle = onToggle;
    }

    public AbstractCheckBox(Dimensions size, boolean active, Consumer<Boolean> onToggle) {
        this(new BoundingBox(Vector2f.zero(), size), active, onToggle);
    }

    /**
     * Doesn't keep focus.
     * @return false
     */
    @Override
    public boolean keepsFocus() {
        return false;
    }

    @Override
    public void onMouseUp(Vector2f cursorPos) {
        if (getBounds().contains(new BoundingBox(cursorPos, Dimensions.one()))) {
            active = !active;
            if (onToggle != null) {
                onToggle.accept(active);
            }
            if (getOnAction() != null) {
                getOnAction().accept(this);
            }
        }
    }

    @Override
    public abstract void render(KopfkinoGraphics graphics);

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Consumer<Boolean> getOnToggle() {
        return onToggle;
    }

    public void setOnToggle(Consumer<Boolean> onToggle) {
        this.onToggle = onToggle;
    }
}
