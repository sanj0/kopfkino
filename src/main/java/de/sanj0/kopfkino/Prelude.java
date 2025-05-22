package de.sanj0.kopfkino;

/**
 * Very useful static methods to be statically mass-imported for maximum convenience.
 */
public class Prelude {
    /**
     * Convenience method to instantiate a new vector.
     * To be statically imported for max comfort.
     * @param x the x component of the new vector
     * @param y the y component of the new vector
     * @return new instance with the given x and y values
     */
    public static Vector2f vec(float x, float y) {
        return new Vector2f(x, y);
    }

    /**
     * Convenience method to instantiate a new vector with a y value of 0.
     * To be statically imported for max comfort.
     * @param x the x component of the new vector
     * @return new instance with the given x and a y value of 0
     */
    public static Vector2f vecX(float x) {
        return new Vector2f(x, 0);
    }

    /**
     * Convenience method to instantiate a new vector with an x value of 0.
     * To be statically imported for max comfort.
     * @param y the y component of the new vector
     * @return new instance with the given y and a x value of 0
     */
    public static Vector2f vecY(float y) {
        return new Vector2f(0, y);
    }

    /**
     * Convenience method to instantiate new dimensions. To be statically imported for max comfort.
     * @param x the x component of the new vector
     * @param y the y component of the new vector
     * @return new instance with the given x and y values
     */
    public static Dimensions dimensions(float x, float y) {
        return new Dimensions(x, y);
    }
}
