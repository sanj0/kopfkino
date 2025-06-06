package kopfkino.gui;

import kopfkino.BoundingBox;
import kopfkino.Game;
import kopfkino.Vector2f;
import kopfkino.graphics.Renderable;
import kopfkino.Colors;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.util.function.Consumer;

public abstract class Component implements Renderable {
    private BoundingBox bounds;
    private Color backgroundColor = Colors.TEAL_BLUE_COLOR;
    private Color foregroundColor = Colors.BLACK;
    private Font font = null;
    private Consumer<Component> onAction;
    private boolean visible = true;

    public Component(BoundingBox bounds) {
        this.bounds = bounds;
    }

    /**
     * Does this component keeps its focus after the mouse button is released?
     * Buttons don't while text inputs do.
     * @return keep the focus after mouse button is released?
     */
    public abstract boolean keepsFocus();

    /**
     * When the cursor is inside the bounds of this component upon clickign, which component should be focused?
     * Should return {@code this} for normal components and only a different component if {@code this} is a container.
     * @param cursor the cursor that clicked
     * @return the component that should be focused
     */
    public Component getFocus(BoundingBox cursor) {
        return this;
    }

    public void onMouseDown(Vector2f cursorPos) {
    }

    public void onMouseUp(Vector2f cursorPos) {
    }

    public void onKeyDown(KeyEvent e) {
    }

    public void onKeyUp(KeyEvent e) {
    }

    public void onScroll(MouseWheelEvent e) {
    }

    public boolean hasFocus() {
        Gui gui = Game.getInstance().getCurrentScene().getGui();
        if (gui == null) {
            return false;
        }
        return gui.getFocus() == this;
    }

    public BoundingBox getBounds() {
        return bounds;
    }

    public void setBounds(BoundingBox bounds) {
        this.bounds = bounds;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Consumer<Component> getOnAction() {
        return onAction;
    }

    public void setOnAction(Consumer<Component> onAction) {
        this.onAction = onAction;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
