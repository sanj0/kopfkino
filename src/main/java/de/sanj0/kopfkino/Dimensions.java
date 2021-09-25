/*
 *    Copyright 2021 Malte Dostal
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

/**
 * Width and height.
 */
public class Dimensions extends BiFloatTuple<Dimensions> {
    public Dimensions(final float width, final float height) {
        super(width, height);
    }

    public Dimensions(final Dimensions d) {
        this(d.a, d.b);
    }

    /**
     * Returns a new instance with x and y equal to the given float.
     *
     * @param num a number
     *
     * @return a {@link Dimensions} with x and y equal to the given float
     */
    public static Dimensions num(final float num) {
        return new Dimensions(num, num);
    }

    /**
     * Returns a new dimensions with x and y equal to 0.
     *
     * @return a new dimensions with x and y equal to 0
     */
    public static Dimensions zero() {
        return new Dimensions(0, 0);
    }

    /**
     * Returns a new dimensions with x and y equal to 1.
     *
     * @return a new dimensions with x and y equal to 1
     */
    public static Dimensions one() {
        return new Dimensions(1, 1);
    }

    /**
     * Returns a new dimensions with x and y equal to 2.
     *
     * @return a new dimensions with x and y equal to 2
     */
    public static Dimensions two() {
        return new Dimensions(2, 2);
    }

    /**
     * Returns a new dimensions with x and y equal to -1.
     *
     * @return a new dimensions with x and y equal to -1
     */
    public static Dimensions negOne() {
        return new Dimensions(-1, -1);
    }

    @Override
    public Dimensions createInstance(final float a, final float b) {
        return new Dimensions(a, b);
    }

    @Override
    public String toString() {
        return "Dimensions{" +
                "width=" + a +
                ", height=" + b +
                '}';
    }

    public Vector2f toVector2f() {
        return new Vector2f(a, b);
    }

    /**
     * Gets width.
     *
     * @return the value of width
     */
    public float getWidth() {
        return a;
    }

    /**
     * Sets width.
     *
     * @param width the new value of width
     */
    public void setWidth(final float width) {
        this.a = width;
    }

    /**
     * Gets height.
     *
     * @return the value of height
     */
    public float getHeight() {
        return b;
    }

    /**
     * Sets height.
     *
     * @param height the new value of height
     */
    public void setHeight(final float height) {
        this.b = height;
    }
}
