package kopfkino.gui;

import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.Vector2f;
import kopfkino.KopfkinoGraphics;

import java.util.function.Consumer;

/**
 * An abstract button that implements general button behaviour.
 * Extend to implement a custom button or use {@link Button} for a simple standard button.
 */
public abstract class AbstractButton extends Component {
    private Consumer<Vector2f> onClick;

    public AbstractButton(BoundingBox bounds, Consumer<Vector2f> onClick) {
        super(bounds);
        this.onClick = onClick;
    }

    public AbstractButton(Dimensions size, Consumer<Vector2f> onClick) {
        this(new BoundingBox(Vector2f.zero(), size), onClick);
    }

    /**
     * A button doesn't keep its focus.
     * @return false
     */
    @Override
    public boolean keepsFocus() {
        return false;
    }

    /**
     * If the given cursor position is inside the bounds of this button, fire {@link #getOnClick() on click}
     * and {@link #getOnAction() on action}.
     * @param cursorPos
     */
    @Override
    public void onMouseUp(Vector2f cursorPos) {
        if (getBounds().contains(new BoundingBox(cursorPos, Dimensions.one()))) {
            if (onClick != null) {
                onClick.accept(cursorPos);
            }
            if (getOnAction() != null) {
                getOnAction().accept(this);
            }
        }
    }

    @Override
    public abstract void render(KopfkinoGraphics graphics);

    public Consumer<Vector2f> getOnClick() {
        return onClick;
    }

    public void setOnClick(Consumer<Vector2f> onClick) {
        this.onClick = onClick;
    }
}
