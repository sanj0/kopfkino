package kopfkino;

public class Align {
    private AlignX alignX;
    private AlignY alignY;

    public Align(AlignX alignX, AlignY alignY) {
        this.alignX = alignX;
        this.alignY = alignY;
    }

    public static Align centre() {
        return new Align(AlignX.CENTRE, AlignY.CENTRE);
    }

    public Vector2f relativeTo(Vector2f anchor, Dimensions size) {
        float x = 0;
        float y = 0;
        switch (alignX) {
            case LEFT:
                x = anchor.getX();
                break;
            case RIGHT:
                x = anchor.getX() - size.getWidth();
                break;
            case CENTRE:
                x = anchor.getX() - size.getWidth() * 0.5f;
                break;
        }
        switch (alignY) {
            case TOP:
                y = anchor.getY();
                break;
            case BOTTOM:
                y = anchor.getY() - size.getHeight();
                break;
            case CENTRE:
                y = anchor.getY() - size.getHeight() * 0.5f;
                break;
        }
        return new Vector2f(x, y);
    }

    public AlignX getAlignX() {
        return alignX;
    }

    public void setAlignX(AlignX alignX) {
        this.alignX = alignX;
    }

    public AlignY getAlignY() {
        return alignY;
    }

    public void setAlignY(AlignY alignY) {
        this.alignY = alignY;
    }

    public enum AlignX {
        LEFT,
        RIGHT,
        CENTRE
    }

    public enum AlignY {
        TOP,
        BOTTOM,
        CENTRE
    }
}
