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
 * <p>All operations are supported in two ways:
 * <ol>
 *     <li>
 *         functions that change the state of <code>this</code>
 *         <br>for example {@link #add(Vector2f)}, {@link #multiply(Vector2f)}
 *     </li>
 *     <li>
 *         functions that create a new instance and don't change the state of <code>this</code>
 *         <br>for example {@link #plus(Vector2f)}, {@link #divBy(Vector2f)}
 *     </li>
 * </ol>
 * Where a speaking name (e.g. {@link #times(Vector2f)}) for the non-state-change
 * function isn't possible, the past version of the normal function is used (e.g. {@link #absed()})
 */
public class Vector2f {

    private float x;
    private float y;

    public Vector2f(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(final Vector2f v) {
        this(v.x, v.y);
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

    /**
     * Multiplies b.x with this.x and b.y with this.y and returns {@code this}.
     *
     * @param b another vector
     *
     * @return {@code this}
     */
    public Vector2f multiply(final Vector2f b) {
        x *= b.x;
        y *= b.y;
        return this;
    }

    /**
     * Returns a new vector with x and y being the product of the xs and ys of
     * this and the given vector, respectively.
     * <p>Doesn't change this, nor the given Vector.
     *
     * @param b another Vector
     *
     * @return a vector2f whose components are the product of the respective
     * components of {@link this} and {@code b}.
     */
    public Vector2f times(final Vector2f b) {
        return new Vector2f(x + b.x, y * b.y);
    }

    /**
     * Divides this.x by b.x and this.y by b.y and returns {@code this}.
     *
     * @param b another vector
     *
     * @return {@code this}
     */
    public Vector2f divide(final Vector2f b) {
        x /= b.x;
        y /= b.y;
        return this;
    }

    /**
     * Returns a new vector with x and y being the quotient of the xs and ys of
     * this and the given vector, respectively.
     * <p>Doesn't change this, nor the given Vector.
     *
     * @param b another Vector
     *
     * @return a vector2f whose components are the quotient of the respective
     * components of {@link this} and {@code b}.
     */
    public Vector2f divBy(final Vector2f b) {
        return new Vector2f(x / b.x, y / b.y);
    }

    /**
     * Adds b.x to this.x and b.y to this.y and returns {@code this}.
     *
     * @param b another vector
     *
     * @return {@code this}
     */
    public Vector2f add(final Vector2f b) {
        x += b.x;
        y += b.y;
        return this;
    }

    /**
     * Returns a new vector with x and y being the sum of the xs and ys of this
     * and the given vector, respectively.
     * <p>Doesn't change this, nor the given Vector.
     *
     * @param b another Vector
     *
     * @return a vector2f whose components are the sum of the respective
     * components of {@link this} and {@code b}.
     */
    public Vector2f plus(final Vector2f b) {
        return new Vector2f(x + b.x, y + b.y);
    }

    /**
     * Subtracts b.x from this.x and b.y from this.y and returns {@code this}.
     *
     * @param b another vector
     *
     * @return {@code this}
     */
    public Vector2f subtract(final Vector2f b) {
        x -= b.x;
        y -= b.y;
        return this;
    }

    /**
     * Returns a new vector with x and y being the difference between the xs and
     * ys of this and the given vector, respectively.
     * <p>Doesn't change this, nor the given Vector.
     *
     * @param b another Vector
     *
     * @return a vector2f whose components are the difference between the
     * respective components of {@link this} and {@code b}.
     */
    public Vector2f minus(final Vector2f b) {
        return new Vector2f(x - b.x, y - b.y);
    }

    /**
     * Absolutes the values of this.x and this.y and return {@code this}.
     *
     * @return {@code this}
     */
    public Vector2f abs() {
        x = Math.abs(x);
        y = Math.abs(y);
        return this;
    }

    /**
     * Creates a new vector with the absolute values of this.x and this.y as x
     * and y, respectively.
     * <p>Doesn't change {@code this}
     *
     * @return a new vector which is the absolute version of {@link this}
     */
    public Vector2f absed() {
        return new Vector2f(Math.abs(x), Math.abs(y));
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
    public float dotProduct(final Vector2f b) {
        return x * b.x + y * b.y;
    }

    /**
     * Returns the magnitude of this
     * vector using {@link Math#hypot(double, double)}.
     *
     * @return the magnitude of this vector
     */
    public float magnitude() {
        return (float) Math.hypot(x, y);
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
        return new Vector2f(x / mag, y / mag);
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
        return new Vector2f(x / ratio, y / ratio);
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
        return Float.compare(vector2f.x, x) == 0 && Float.compare(vector2f.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Gets {@link #x}.
     *
     * @return the value of {@link #x}
     */
    public float getX() {
        return x;
    }

    /**
     * Sets {@link #x}.
     *
     * @param x the new value of {@link #x}
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Sets x and y to be equal to x and y of the given vector, respectively.
     *
     * @param v a vector
     */
    public void set(final Vector2f v) {
        x = v.x;
        y = v.y;
    }

    /**
     * Gets {@link #y}.
     *
     * @return the value of {@link #y}
     */
    public float getY() {
        return y;
    }

    /**
     * Sets {@link #y}.
     *
     * @param y the new value of {@link #y}
     */
    public void setY(final float y) {
        this.y = y;
    }
}
