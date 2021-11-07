package de.sanj0.kopfkino.physics;

import de.sanj0.kopfkino.Vector2f;

/**
 * A rigid physics body.
 * <p>naming conventions are purposefully awful as to comply with common physics
 * namings
 */
public class Rigidbody {
    private float m;
    private Vector2f F = Vector2f.zero();
    private Vector2f v = Vector2f.zero();

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
}
