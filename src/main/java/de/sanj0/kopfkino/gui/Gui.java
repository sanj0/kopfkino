package de.sanj0.kopfkino.gui;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;
import de.sanj0.kopfkino.graphics.Renderable;
import de.sanj0.kopfkino.utils.Colors;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Gui implements Renderable {
    private List<Component> components;
    private Component focus = null;
    private boolean mouseDownOnComponent = false;

    public Gui(List<Component> components) {
        this.components = components;
    }

    public Gui() {
        this.components = new ArrayList<>();
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).render(graphics);
        }
    }

    public boolean onMouseDown(Vector2f cursorPos) {
        BoundingBox cursor = new BoundingBox(cursorPos, Dimensions.one());
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (component.getBounds().contains(cursor)) {
                focus = component;
                mouseDownOnComponent = true;
                component.onMouseDown(cursorPos);
                return true;
            }
        }
        focus = null;
        mouseDownOnComponent = false;
        return false;
    }
    public boolean onMouseUp(Vector2f cursorPos) {
        if (mouseDownOnComponent) {
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
        if (focus != null) {
            focus.onKeyDown(e);
            return true;
        }
        return false;
    }
    public boolean onKeyUp(KeyEvent e) {
        if (focus != null) {
            focus.onKeyUp(e);
            return true;
        }
        return false;
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
}
