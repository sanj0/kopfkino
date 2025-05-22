package de.***REMOVED***.kopfkino.gui;

import de.***REMOVED***.kopfkino.BoundingBox;
import de.***REMOVED***.kopfkino.graphics.KopfkinoGraphics;

public class Label extends Component {
    private String text;
    public Label(BoundingBox bounds, String text) {
        super(bounds);
        this.text = text;
    }

    @Override
    public boolean keepsFocus() {
        return false;
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (getFont() != null) {
            graphics.setFont(getFont());
        } else {
            graphics.setFont(graphics.getFont().deriveFont(getBounds().getHeight() / 1.5f));
        }
        graphics.setColor(getForegroundColor());
        graphics.drawString(text, getBounds().getCentre(), KopfkinoGraphics.TextAnchor.CENTRE);
        graphics.outlineRect(getBounds());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
