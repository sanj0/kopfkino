package de.sanj0.kopfkino;

public class Layouts {
    public static BoundingBox centre(BoundingBox container, Dimensions size) {
        return BoundingBox.inTheCentreOf(size, container);
    }
    public static BoundingBox centre(Dimensions size) {
        return centre(Game.getDisplayBounds(), size);
    }

    public static BoundingBox centreOffset(BoundingBox container, Dimensions size, Vector2f offset) {
        BoundingBox box = centre(container, size);
        box.getPosition().add(offset);
        return box;
    }

    public static BoundingBox centreOffset(Dimensions size, Vector2f offset) {
        BoundingBox box = centre(size);
        box.getPosition().add(offset);
        return box;
    }
}
