package kopfkino.gui;

import kopfkino.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class AbstractTextArea extends Component {
    private StringBuilder text;
    private int caretPosition = 0;
    private Vector2f lastClickedPosition = null;
    private float scroll = 0;

    public AbstractTextArea(BoundingBox bounds, String text) {
        super(bounds);
        this.text = new StringBuilder(text);
    }

    public AbstractTextArea(Dimensions size, String text) {
        this(new BoundingBox(Vector2f.zero(), size), text);
    }

    @Override
    public boolean keepsFocus() {
        return true;
    }

    @Override
    public void onKeyDown(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                if (caretPosition != 0) {
                    caretPosition -= 1;
                    text.deleteCharAt(caretPosition);
                }
                break;
            case KeyEvent.VK_DELETE:
                if (caretPosition < text.length()) {
                    text.deleteCharAt(caretPosition);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (caretPosition > 0) {
                    caretPosition -= 1;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (caretPosition < text.length()) {
                    caretPosition += 1;
                }
                break;
            case KeyEvent.VK_UP:
                caretPosition = text.length();
                break;
            case KeyEvent.VK_DOWN:
                caretPosition = 0;
                break;
            case KeyEvent.VK_ENTER:
                Game.getInstance().getCurrentScene().getGui().setFocus(null);
                break;
            default:
                if (!e.isActionKey() && e.getKeyCode() != KeyEvent.VK_SHIFT && e.getKeyCode() != KeyEvent.VK_ALT && e.getKeyCode() != KeyEvent.VK_ALT_GRAPH) {
                    text.insert(caretPosition, e.getKeyChar());
                    caretPosition += 1;
                }
                break;
        }
        if (getOnAction() != null) {
            getOnAction().accept(this);
        }
    }

    @Override
    public void onMouseDown(Vector2f cursorPos) {
        lastClickedPosition = cursorPos;
    }

    protected void renderCaret(KopfkinoGraphics graphics) {
        graphics.setColor(getForegroundColor());
        graphics.getGraphics2D().translate(-scroll, 0);
        if (hasFocus()) {
            graphics.setStroke(new BasicStroke(2));
            float caretX = graphics.getGraphics2D().getFontMetrics().stringWidth(text.substring(0, caretPosition)) + getBounds().getX() + 3;
            if (caretX > getBounds().getMaxX()) {
                scroll = caretX - getBounds().getMaxX() + 6;
            } else {
                scroll = 0;
            }
            graphics.drawLine(
                new Vector2f(
                    caretX,
                    getBounds().getY() + 3
                ),
                new Vector2f(
                    caretX,
                    getBounds().getY() + getBounds().getHeight() - 3
                )
            );
        }
        graphics.getGraphics2D().translate(scroll, 0);
    }

    protected void renderText(KopfkinoGraphics graphics) {
        graphics.setColor(getForegroundColor());
        Vector2f textOrigin = new Vector2f(getBounds().getX() + 3, getBounds().getY() + (getBounds().getHeight() * 0.5f));
        graphics.setClip(getBounds());
        graphics.getGraphics2D().translate(-scroll, 0);
        graphics.drawString(text, textOrigin, new Align(Align.AlignX.LEFT, Align.AlignY.CENTRE));

        if (lastClickedPosition != null) {
            int relativeX = Math.round(lastClickedPosition.getX() - textOrigin.getX());
            // todo: this could be more efficient with a binary search but linear search may outperform binary search
            // at small scale
            for (int i = 0; i < text.length(); i++) {
                if (graphics.getGraphics2D().getFontMetrics().stringWidth(text.substring(0, i)) >= relativeX) {
                    caretPosition = i;
                    break;
                } else if (i == text.length() - 1) {
                    caretPosition = text.length();
                }
            }
            lastClickedPosition = null;
        }
        graphics.getGraphics2D().translate(scroll, 0);
    }

    @Override
    public abstract void render(KopfkinoGraphics graphics);

    public StringBuilder getText() {
        return text;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }
}
