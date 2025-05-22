package de.***REMOVED***.kopfkino.gui;

import de.***REMOVED***.kopfkino.BoundingBox;
import de.***REMOVED***.kopfkino.Dimensions;
import de.***REMOVED***.kopfkino.Vector2f;
import de.***REMOVED***.kopfkino.graphics.KopfkinoGraphics;

import java.util.function.Consumer;

public class Button extends Component {
    private String text;
    private Consumer<Vector2f> onClick;
    public Button(BoundingBox bounds, String text, Consumer<Vector2f> onClick) {
        super(bounds);
        this.text = text;
        this.onClick = onClick;
    }

    @Override
    public boolean keepsFocus() {
        return false;
    }

    @Override
    public void onMouseUp(Vector2f cursorPos) {
        setBackgroundColor(getBackgroundColor().brighter());
        if (getBounds().contains(new BoundingBox(cursorPos, Dimensions.one()))) {
            onClick.accept(cursorPos);
        }
    }

    @Override
    public void onMouseDown(Vector2f cursorPos) {
         setBackgroundColor(getBackgroundColor().darker());
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (getFont() != null) {
            graphics.setFont(getFont());
        } else {
            graphics.setFont(graphics.getFont().deriveFont(getBounds().getHeight() * 0.5f));
        }
        graphics.setColor(getBackgroundColor());
        graphics.drawRect(getBounds());
        graphics.setColor(getForegroundColor());
        graphics.drawString(text, getBounds().getCentre(), KopfkinoGraphics.TextAnchor.CENTRE);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
