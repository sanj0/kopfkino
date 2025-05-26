package kopfkino.graphics;

import kopfkino.Dimensions;
import kopfkino.Game;
import kopfkino.KopfkinoGraphics;
import kopfkino.Vector2f;

import java.awt.*;

public class Grid implements Renderable {
    private Dimensions cellsSize;
    private boolean labels;
    private Stroke stroke;
    private Color color;

    public Grid(Dimensions cellsSize, boolean labels, Stroke stroke, Color color) {
        this.cellsSize = cellsSize;
        this.labels = labels;
        this.stroke = stroke;
        this.color = color;
    }

    @Override
    public void render(KopfkinoGraphics graphics) {
        graphics.setStroke(stroke);
        graphics.setColor(color);
        Dimensions display = Game.getResolution();
        for (float x = 0; x < display.getWidth(); x += cellsSize.getWidth()) {
            graphics.drawLine(new Vector2f(x, 0), new Vector2f(x, display.getHeight()));
        }
        for (float y = 0; y < display.getHeight(); y += cellsSize.getHeight()) {
            graphics.drawLine(new Vector2f(0, y), new Vector2f(display.getWidth(), y));
        }
    }

    public Dimensions getCellsSize() {
        return cellsSize;
    }

    public void setCellsSize(Dimensions cellsSize) {
        this.cellsSize = cellsSize;
    }

    public boolean isLabels() {
        return labels;
    }

    public void setLabels(boolean labels) {
        this.labels = labels;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
