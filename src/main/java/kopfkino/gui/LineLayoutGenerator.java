package kopfkino.gui;

import kopfkino.Align;
import kopfkino.BoundingBox;
import kopfkino.Vector2f;

import java.util.*;

/**
 * Generates a static line layout for {@link Component}s in a {@link Gui}.
 * A static layout is one that doesn't change after the initial setup.
 * As a Kopfkino always has the same resolution, this works perfectly
 * fine and circumvents expensive recurring computation.
 * <br>
 * Generating a layout consists of (chained) calls to methods of an
 * instance {@code llg} of this class. Adding a Component to the layout
 * requires calling {@link #add(Component)}, {@link #addGroup(List, Component...)}
 * or {@link #addGroup(float, Component...)}. When a Component is added to
 * the layout, it's position is set according to the current state of {@code llg}.
 * After that, the state of {@code llg} automatically changes according to the
 * layout. The layout is line-oriented and works as follows.
 * <br>
 * At any given time, an instance {@code llg} has a cursor at a location
 * on screen. Whenever a new Component is added, it will be at
 * that location. Apart from automatically moving when a Component is added,
 * the cursor can be manipulated in different ways:
 * <ul>
 *     <li>{@link #gap(float)} inserts a horizontal gap. The cursor is advanced by the given amount on the x-axis,</li>
 *     <li>{@link #newLine(float, Align.AlignX)} starts a new line with the given offset on the y-axis. This resets
 *     the cursor according to the given alignment. See the method documentation for details.</li>
 * </ul>
 * The state of {@code llg} also has an instance of {@link Align}. It's x and y
 * component determine the placements of Components relative to the cursor.
 * After adding all objects, {@link #finalAlign(Align.AlignY, float)} can be used to align
 * all added objects along the y-axis, to e.g. have the lot centered.
 * <br>
 * <h2>Example</h2>
 * The following code snippet is an example usage of this class to generate a
 * simple HUD for a game. It has two progress bars (for health, energy, ...)
 * in the top right corner, a location name at the top centre and a checkbox with
 * a label just below that (because why not).
 * <pre>
 * {@code
 * import de.sanj0.kopfkino.gui.*;
 * import static de.sanj0.kopfkino.Prelude.*;
 * public class HudScene extends Scene {
 *     public HudScene() {
 *         Gui gui = new Gui();
 *         setGui(gui);
 *         ProgressBar healthBar = new ProgressBar(dimensions(200, 50), 0.75f);
 *         ProgressBar energyBar = new ProgressBar(dimensions(200, 50), 0.5f);
 *         Label locationLabel = new Label("Frankfurt am Main");
 *         CheckBox checkBox = new CheckBox(dimensions(25, 25), false, System.out::println);
 *         Label checkBoxLabel = new Label("Toggle something ...");
 *
 *         // Create a new instance that uses the whole game screen as its bounds to determine
 *         // left, right and centre and an initial x-axis-align of LEFT.
 *         LineLayoutGenerator llg = new LineLayoutGenerator(Game.getDisplayBounds(), Align.AlignX.LEFT);
 *         llg.
 *             gap(25). // Add a gap so that the health bar isn't directly at the edge
 *             add(healthBar). // set the health bar's position at the current cursor
 *             resetCursor(Align.AlignX.CENTRE). // reset the cursor to the centre of the screen
 *             add(locationLabel). // set the position of the centre of the location label to the current cursor
 *             newLine(75, Align.AlignX.LEFT). // reset the cursor to the left and shift it down by 75 pixels
 *             gap(25). // add the same gap as before
 *             add(energyBar). // set the energy bar's position. It will be directly below the health bar
 *             resetCursor(Align.AlignX.CENTRE). // reset the cursor to the centre of the screen
 *             addGroup(25, checkBox, checkBoxLabel). // centre the check box and its label together
 *             finalAlign(Align.AlignY.TOP, 25); // align all previously added components so that the highest
 *                                                  ones are 25 pixels below the top edge
 *
 *         // Lastly, the components need to be added to the Gui.
 *         // Because calls to add[Group] only sets their positions.
 *         gui.add(healthBar);
 *         gui.add(energyBar);
 *         gui.add(locationLabel);
 *         gui.add(checkBox);
 *         gui.add(checkBoxLabel);
 *     }
 * }
 * }
 * </pre>
 * <h2>Help</h2>
 * When something doesn't seem to work properly, the following can be considered:
 * <ul>
 *     <li>Enabling debug drawing of the boundaries of all Gui-Components via {@link Gui#setDrawBounds(boolean)},</li>
 *     <li>Keeping in mind that every call to and of the {@code add} and {@code addGroup} functions sets
 *     the cursors x position to the right edge of the last Component (or the left edge when the current x-align is RIGHT),</li>
 *     <li>To have multiple elements be centered together as a whole, subsequent calls to {@link #add(Component)}won't work.
 *     {@link #addGroup(float, Component...)} or {@link #addGroup(List, Component...)} are needed.</li>
 * </ul>
 */
public class LineLayoutGenerator {
    private BoundingBox bounds;
    private Vector2f cursor;
    private Align align;
    private List<Component> allComponents;

    public LineLayoutGenerator(BoundingBox bounds, Align.AlignX initLineAlignX) {
        this.bounds = bounds;
        this.align = new Align(initLineAlignX, Align.AlignY.CENTRE);
        cursor = new Vector2f(computeXForAlign(initLineAlignX), bounds.getY());
        allComponents = new ArrayList<>();
    }

    private float computeXForAlign(Align.AlignX align) {
        return align == Align.AlignX.RIGHT ?
            bounds.getMaxX() : align == Align.AlignX.LEFT ?
            bounds.getX() : bounds.getCentre().getX();
    }

    public LineLayoutGenerator add(Component component) {
        component.getBounds().setPosition(align.relativeTo(cursor, component.getBounds().getSize()));
        if (align.getAlignX() == Align.AlignX.RIGHT) {
            cursor.setX(component.getBounds().getX());
        } else {
            cursor.setX(component.getBounds().getMaxX());
        }
        allComponents.add(component);
        return this;
    }

    public LineLayoutGenerator addGroup(float gaps, Component... components) {
        if (components.length == 0) return this;
        return addGroup(Collections.nCopies(components.length - 1, gaps), components);
    }

    public LineLayoutGenerator addGroup(List<Float> gaps, Component... components) {
        if (components.length == 0) return this;
        if (components.length == 1) return add(components[0]);
        if (align.getAlignX() != Align.AlignX.CENTRE) {
            for (int i = 0; i < components.length; i++) {
                add(components[i]);
                if (i != components.length - 1) gap(gaps.get(i));
            }
            return this;
        }
        float totalWidth = Arrays.stream(components).map(c -> c.getBounds().getWidth()).reduce(Float::sum).get()
            + gaps.stream().reduce(Float::sum).get();
        cursor.setX(cursor.getX() - (totalWidth * 0.5f));
        alignX(Align.AlignX.LEFT);
        for (int i = 0; i < components.length; i++) {
            add(components[i]);
            if (i != components.length - 1) gap(gaps.get(i));
        }
        return alignX(Align.AlignX.CENTRE);
    }

    public LineLayoutGenerator gap(float amount) {
        if (align.getAlignX() == Align.AlignX.RIGHT) {
            cursor.setX(cursor.getX() - amount);
        } else {
            cursor.setX(cursor.getX() + amount);
        }
        return this;
    }

    public LineLayoutGenerator newLine(float vskip, Align.AlignX initAlign) {
        cursor.setY(cursor.getY() + vskip);
        cursor.setX(computeXForAlign(initAlign));
        return alignX(initAlign);
    }

    public LineLayoutGenerator resetCursor(Align.AlignX alignX) {
        return newLine(0, alignX);
    }

    public LineLayoutGenerator alignX(Align.AlignX alignX) {
        align.setAlignX(alignX);
        return this;
    }

    public LineLayoutGenerator alignY(Align.AlignY alignY) {
        align.setAlignY(alignY);
        return this;
    }

    public void finalAlign(Align.AlignY totalAlignY, float offset) {
        if (allComponents.isEmpty()) return;
        float minY = allComponents.stream().map(c -> c.getBounds().getY()).min(Float::compare).get();
        float maxY = allComponents.stream().map(c -> c.getBounds().getMaxY()).max(Float::compare).get();
        float moveAmount = 0;
        switch (totalAlignY) {
            case CENTRE:
                moveAmount =  bounds.getCentre().getY() - (minY + maxY) / 2f;
                break;
            case TOP:
                moveAmount = bounds.getY() - minY;
                break;
            case BOTTOM:
                moveAmount = bounds.getMaxY() - maxY;
                break;
        }
        for (Component component : allComponents) {
            component.getBounds().setY(component.getBounds().getY() + moveAmount + offset);
        }
    }
}
