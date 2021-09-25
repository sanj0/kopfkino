/*
 *    Copyright 2021 ***REMOVED*** ***REMOVED***
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package de.sanj0.kopfkino;

import java.util.Objects;

/**
 * A vector defined by two float: x and y.
 * <p>Most math operations are defined by {@link BiFloatTuple}.
 */
public class Vector2f extends BiFloatTuple<Vector2f> {
    public Vector2f(final float x, final float y) {
        super(x, y);
    }

    @Override
    public Vector2f createInstance(final float a, final float b) {
        return new Vector2f(a, b);
    }

    public Vector2f(final Vector2f v) {
        this(v.getX(), v.getY());
    }

    /**
     * Returns a new instance with x and y equal to the given float.
     *
     * @param num a number
     *
     * @return a {@link Vector2f} with x and y equal to the given float
     */
    public static Vector2f num(final float num) {
        return new Vector2f(num, num);
    }

    /**
     * Returns a new vector with x and y equal to 0.
     *
     * @return a new vector with x and y equal to 0
     */
    public static Vector2f zero() {
        return new Vector2f(0, 0);
    }

    /**
     * Returns a new vector with x and y equal to 1.
     *
     * @return a new vector with x and y equal to 1
     */
    public static Vector2f one() {
        return new Vector2f(1, 1);
    }

    /**
     * Returns a new vector with x and y equal to 2.
     *
     * @return a new vector with x and y equal to 2
     */
    public static Vector2f two() {
        return new Vector2f(2, 2);
    }

    /**
     * Returns a new vector with x and y equal to -1.
     *
     * @return a new vector with x and y equal to -1
     */
    public static Vector2f negOne() {
        return new Vector2f(-1, -1);
    }

    public Dimensions toDimensions() {
        return new Dimensions(a, b);
    }

    /**
     * Returns the dot product of this and the
     * given vector.
     * <p>With this = (x1, y1) and b = (x2, y2),
     * this function returns the following:
     * <br>{@code x1 * x2 + y1 + y2}
     *
     * @return the dot product of this and the given vector
     */
    public float dotProduct(final Vector2f o) {
        return a * o.a + b * o.b;
    }

    /**
     * Returns the magnitude of this
     * vector using {@link Math#hypot(double, double)}.
     *
     * @return the magnitude of this vector
     */
    public float magnitude() {
        return (float) Math.hypot(a, b);
    }

    /**
     * Returns a new vector that is the unit vector
     * of this vector. That is, a vector with the same
     * ratio between x and y components but with a magnitude
     * of 1.
     * <p>Doesn't change this vector
     *
     * @return a new vector, that is the normalised unit vector of {@code this}
     */
    public Vector2f normalise() {
        final float mag = magnitude();
        return new Vector2f(a / mag, b / mag);
    }

    /**
     * Normalises this vector by copying the components of
     * its unit vector retrieved using {@link #normalise()}
     * and returns {@code this}.
     *
     * @return {@code this}
     */
    public Vector2f normalised() {
        set(normalise());
        return this;
    }

    /**
     * Returns a new vector where x / y is equal to
     * x / y of the current vector, but the magnitude
     * is equal to the given one.
     * <p>Doesn't change this vector.
     *
     * @param newMag a new magnitude for a new vector
     * @return a new vector with the given magnitude but the x / y ratio of {@code this}
     */
    public Vector2f withMagnitude(final float newMag) {
        final float ratio = newMag / magnitude();
        return new Vector2f(a * ratio, b * ratio);
    }

    /**
     * Copyes the components of the vector retrieved by
     * calling {@link #withMagnitude(float)} in order to
     * set the current vector's magnitude to the given one
     * and returns {@code this}.
     *
     * @param newMag a new magnitude for this vector
     * @return {@code this}
     */
    public Vector2f setMagnitude(final float newMag) {
        set(withMagnitude(newMag));
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return Float.compare(vector2f.a, a) == 0 && Float.compare(vector2f.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + a +
                ", y=" + b +
                '}';
    }

    /**
     * Gets x.
     *
     * @return the value of x
     */
    public float getX() {
        return a;
    }

    /**
     * Sets x.
     *
     * @param x the new value of x
     */
    public void setX(final float x) {
        a = x;
    }

    /**
     * Sets x and y to be equal to x and y of the given vector, respectively.
     *
     * @param v a vector
     */
    public void set(final Vector2f v) {
        a = v.a;
        b = v.b;
    }

    /**
     * Gets y.
     *
     * @return the value of y
     */
    public float getY() {
        return b;
    }

    /**
     * Sets y.
     *
     * @param y the new value of y
     */
    public void setY(final float y) {
        b = y;
    }
}
