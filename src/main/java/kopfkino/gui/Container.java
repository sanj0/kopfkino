package kopfkino.gui;

import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.KopfkinoGraphics;
import kopfkino.Vector2f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * A loose container for {@code Component}s. Loose means that this doesn't »contain« any components in the literal sense.
 * A container merely holds a list of components and renders them wherever they are.
 */
public abstract class Container extends Component {
    private List<Component> children;

    public Container(BoundingBox bounds, List<Component> children) {
        super(bounds);
        this.children = children;
    }

    public Container(BoundingBox bounds) {
        this(bounds, new ArrayList<>());
    }

    public Container(Dimensions size, List<Component> children) {
        this(new BoundingBox(Vector2f.zero(), size), children);
    }

    public Container(Dimensions size) {
        this(new BoundingBox(Vector2f.zero(), size), new ArrayList<>());
    }

    @Override
    public boolean keepsFocus() {
        return false; // a container should never have the focus anyway.
    }

    @Override
    public Component getFocus(BoundingBox cursor) {
        for (int i = 0; i < children.size(); i++) {
            Component component = children.get(i);
            if (component.getBounds().contains(cursor))
                return component.getFocus(cursor);
        }
        return null;
    }

    @Override
    public abstract void render(KopfkinoGraphics graphics);

    protected void renderChildren(KopfkinoGraphics graphics) {
        for (int i = 0; i < children.size(); i++) {
            Component child = children.get(i);
            if (child.isVisible()) child.render(graphics.copy());
        }
    }

    public List<Component> getChildren() {
        return children;
    }

    public void setChildren(List<Component> children) {
        this.children = children;
    }

    public int size() {
        return children.size();
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }

    public boolean contains(Object o) {
        return children.contains(o);
    }

    public boolean add(Component component) {
        return children.add(component);
    }

    public boolean remove(Object o) {
        return children.remove(o);
    }

    public boolean addAll(Collection<? extends Component> collection) {
        return children.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends Component> collection) {
        return children.addAll(i, collection);
    }

    public void sort(Comparator<? super Component> c) {
        children.sort(c);
    }

    public void clear() {
        children.clear();
    }

    public Component get(int i) {
        return children.get(i);
    }

    public Component set(int i, Component component) {
        return children.set(i, component);
    }

    public void add(int i, Component component) {
        children.add(i, component);
    }

    public Component remove(int i) {
        return children.remove(i);
    }

    public int indexOf(Object o) {
        return children.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return children.lastIndexOf(o);
    }
}
