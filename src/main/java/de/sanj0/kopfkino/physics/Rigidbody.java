package de.sanj0.kopfkino.physics;

import de.sanj0.kopfkino.Vector2f;

/**
 * A rigid physics body.
 * <p>naming conventions are purposefully aweful as to comply with common pyhsics namings
 */
public class Rigidbody {
    private float m;
    private Vector2f F;
    private Vector2f v;

    public Rigidbody(float m) {
        this.m = m;
    }

    public float getM() {
        return m;
    }

    public void setM(float m) {
        this.m = m;
    }

    public Vector2f getF() {
        return F;
    }

    public void setF(Vector2f f) {
        F = f;
    }

    public Vector2f getV() {
        return v;
    }

    public void setV(Vector2f v) {
        this.v = v;
    }
}
