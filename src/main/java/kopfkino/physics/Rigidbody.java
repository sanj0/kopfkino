package kopfkino.physics;

import kopfkino.Entity;
import kopfkino.Vector2f;

/**
 * A rigid physics body.
 * <p>naming conventions are purposefully awful as to comply with common
 * physics
 * namings
 */
public class Rigidbody {
    /**
     * The mass of this body.
     * Per default, the constructor of {@link Entity}
     * sets this to the product of its width and height.
     */
    private float m;
    /**
     * The force applied to this body in the next update.
     * Immediately after having been applied, the force is reset to 0, 0
     */
    private Vector2f F = Vector2f.zero();
    /**
     * The current velocity of this body.
     */
    private Vector2f v = Vector2f.zero();
    /**
     * Bounciness between 0 and 1 (usually).
     * 0 means that on collision, movement is simply stopped
     * while 1 means that on collision, movement is "reversed"
     * <p>Default value: <code>.3f</code>
     */
    private float bounciness = .3f;

    public Rigidbody(final float m) {
        this.m = m;
    }

    public float getM() {
        return m;
    }

    public void setM(final float m) {
        this.m = m;
    }

    public Vector2f getF() {
        return F;
    }

    public void setF(final Vector2f f) {
        F = f;
    }

    public Vector2f getV() {
        return v;
    }

    public void setV(final Vector2f v) {
        this.v = v;
    }

    /**
     * Gets {@link #bounciness}.
     *
     * @return the value of {@link #bounciness}
     */
    public float getBounciness() {
        return bounciness;
    }

    /**
     * Sets {@link #bounciness}.
     *
     * @param bounciness the new value of {@link #bounciness}
     */
    public void setBounciness(final float bounciness) {
        this.bounciness = bounciness;
    }
}
