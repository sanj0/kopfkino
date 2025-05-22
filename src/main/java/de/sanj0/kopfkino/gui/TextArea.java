package de.sanj0.kopfkino.gui;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TextArea extends Component {
    private StringBuilder text;
    private int cursorPosition = 0;
    private Vector2f lastClickedPosition = null;

    public TextArea(BoundingBox bounds, String text) {
        super(bounds);
        this.text = new StringBuilder(text);
    }

    @Override
    public boolean keepsFocus() {
        return true;
    }

    @Override
    public void onKeyDown(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                if (cursorPosition != 0) {
                    cursorPosition -= 1;
                    text.deleteCharAt(cursorPosition);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (cursorPosition > 0) {
                    cursorPosition -= 1;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (cursorPosition < text.length()) {
                    cursorPosition += 1;
                }
                break;
            case KeyEvent.VK_UP:
                cursorPosition = text.length();
                break;
            case KeyEvent.VK_DOWN:
                cursorPosition = 0;
                break;
            default:
                if (!e.isActionKey() && e.getKeyCode() != KeyEvent.VK_SHIFT && e.getKeyCode() != KeyEvent.VK_ALT && e.getKeyCode() != KeyEvent.VK_ALT_GRAPH) {
                    text.insert(cursorPosition, e.getKeyChar());
                    cursorPosition += 1;
                }
                break;
        }
    }

    @Override
    public void onMouseDown(Vector2f cursorPos) {
        lastClickedPosition = cursorPos;
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        if (getFont() != null) {
            graphics.setFont(getFont());
        } else {
            graphics.setFont(graphics.getFont().deriveFont(getBounds().getHeight() / 1.5f));
        }
        graphics.setColor(getBackgroundColor());
        graphics.outlineRect(getBounds());

        graphics.setColor(getForegroundColor());
        Vector2f textOrigin = getBounds().getPosition().plus(new Vector2f(3, 0));
        graphics.drawString(text, textOrigin, KopfkinoGraphics.TextAnchor.TOP_LEFT_CORNER);

        if (hasFocus()) {
            graphics.setStroke(new BasicStroke(2));
            float textWidth = graphics.getGraphics2D().getFontMetrics().stringWidth(text.substring(0, cursorPosition));
            graphics.drawLine(
                new Vector2f(
                    getBounds().getX() + textWidth + 3,
                    getBounds().getY() + 3
                ),
                new Vector2f(
                    getBounds().getX() + textWidth + 3,
                    getBounds().getY() + getBounds().getHeight() - 3
                )
            );
        }

        if (lastClickedPosition != null) {
            int relativeX = Math.round(lastClickedPosition.getX() - textOrigin.getX());
            // todo: this could be more efficient with a binary search but linear search may outperform binary search
            // at small scale
            for (int i = 0; i < text.length(); i++) {
                if (graphics.getGraphics2D().getFontMetrics().stringWidth(text.substring(0, i)) >= relativeX) {
                    cursorPosition = i;
                    break;
                } else if (i == text.length() - 1) {
                    cursorPosition = text.length();
                }
            }
            lastClickedPosition = null;
        }
    }
}
