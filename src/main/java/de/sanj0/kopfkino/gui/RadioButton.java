package de.sanj0.kopfkino.gui;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.KopfkinoGraphics;

import java.util.List;
import java.util.function.Consumer;

public class RadioButton extends Component {
    private boolean active;
    private List<RadioButton> group;

    public RadioButton(BoundingBox bounds, boolean active, Consumer<Component> onAction, List<RadioButton> group) {
        super(bounds);
        this.active = active;
        this.group = group;
        this.setOnAction(onAction);
    }

    public RadioButton(Dimensions size, boolean active, Consumer<Component> onAction, List<RadioButton> group) {
        this(new BoundingBox(Vector2f.zero(), size), active, onAction, group);
    }

    @Override
    public void onMouseUp(Vector2f cursorPos) {
        if (getBounds().contains(new BoundingBox(cursorPos, Dimensions.one())) && !active) {
            active = true;
            for (int i = 0; i < group.size(); i++) {
                RadioButton other = group.get(i);
                if (other != this) {
                    group.get(i).setActive(false);
                }
            }
            if (getOnAction() != null) {
                getOnAction().accept(this);
            }
        }
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (active) {
            graphics.setColor(getBackgroundColor());
            graphics.drawOval(getBounds());
        }
        graphics.setColor(getForegroundColor());
        graphics.outlineOval(getBounds());
    }

    @Override
    public boolean keepsFocus() {
        return false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RadioButton> getGroup() {
        return group;
    }

    public void setGroup(List<RadioButton> group) {
        this.group = group;
    }
}
