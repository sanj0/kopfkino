package kopfkino.gui;

import kopfkino.*;
import kopfkino.graphics.Renderable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Gui implements Renderable {
    private List<Component> components;
    private Component focus = null;
    private boolean mouseDownOnComponent = false;
    private boolean drawBounds = false;
    private boolean outlineFocus = false;
    private boolean visible = true;

    public Gui(List<Component> components) {
        this.components = components;
    }

    public Gui() {
        this.components = new ArrayList<>();
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (!visible) return;
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (!component.isVisible()) continue;
            KopfkinoGraphics g = graphics.copy();
            g.setStroke(new BasicStroke(3));
            component.render(g);
            if (drawBounds) graphics.outlineRect(component.getBounds());
        }
        if (outlineFocus && focus != null) {
            graphics.setColor(Colors.RED);
            graphics.outlineRect(focus.getBounds());
        }
    }

    public boolean onMouseDown(Vector2f cursorPos) {
        if (!visible) return false;
        BoundingBox cursor = new BoundingBox(cursorPos, Dimensions.one());
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (!component.isVisible()) continue;
            Component focusedComponent;
            if (component.getBounds().contains(cursor) && (focusedComponent = component.getFocus(cursor)) != null) {
                focus = focusedComponent;
                mouseDownOnComponent = true;
                focusedComponent.onMouseDown(cursorPos);
                return true;
            }
        }
        focus = null;
        mouseDownOnComponent = false;
        return false;
    }

    public boolean onMouseUp(Vector2f cursorPos) {
        if (!visible) return false;
        if (mouseDownOnComponent) {
            if (!focus.isVisible()) {
                focus = null;
                return false;
            }
            mouseDownOnComponent = false;
            focus.onMouseUp(cursorPos);
            if (!focus.keepsFocus()) {
                focus = null;
            }
            return true;
        }
        return false;
    }

    public boolean onKeyDown(KeyEvent e) {
        if (!visible) return false;
        if (focus != null && focus.isVisible()) {
            focus.onKeyDown(e);
            return true;
        }
        return false;
    }

    public boolean onKeyUp(KeyEvent e) {
        if (!visible) return false;
        if (focus != null && focus.isVisible()) {
            focus.onKeyUp(e);
            return true;
        }
        return false;
    }

    public void onScroll(MouseWheelEvent e) {
        if (!visible) return;
        for (int i = 0; i < size(); i++) {
            Component component = get(i);
            if (!component.isVisible()) continue;
            if (component.getBounds().contains(Input.cursor()))
                component.onScroll(e);
        }
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public Component getFocus() {
        return focus;
    }

    public void setFocus(Component focus) {
        this.focus = focus;
    }

    public boolean isDrawBounds() {
        return drawBounds;
    }

    public void setDrawBounds(boolean drawBounds) {
        this.drawBounds = drawBounds;
    }

    public int size() {
        return components.size();
    }

    public boolean isEmpty() {
        return components.isEmpty();
    }

    public boolean contains(Object o) {
        return components.contains(o);
    }

    public Iterator<Component> iterator() {
        return components.iterator();
    }

    public boolean add(Component component) {
        return components.add(component);
    }

    public boolean remove(Object o) {
        return components.remove(o);
    }

    public boolean addAll(Collection<? extends Component> collection) {
        return components.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends Component> collection) {
        return components.addAll(i, collection);
    }

    public boolean removeAll(Collection<?> collection) {
        return components.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return components.retainAll(collection);
    }

    public void clear() {
        components.clear();
    }

    public void add(int i, Component component) {
        components.add(i, component);
    }

    public Component set(int i, Component component) {
        return components.set(i, component);
    }

    public Component get(int i) {
        return components.get(i);
    }

    public Component remove(int i) {
        return components.remove(i);
    }

    public int indexOf(Object o) {
        return components.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return components.lastIndexOf(o);
    }

    public boolean isOutlineFocus() {
        return outlineFocus;
    }

    public void setOutlineFocus(boolean outlineFocus) {
        this.outlineFocus = outlineFocus;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
