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

import java.util.Objects;

/**
 * An axis aligned bounding box with a {@link #position} and a {@link #size}.
 */
public class BoundingBox {

    private Vector2f position;
    private Dimensions size;

    public BoundingBox(final Vector2f position, final Dimensions size) {
        this.position = position;
        this.size = size;
    }

    public BoundingBox(final BoundingBox box) {
        this(new Vector2f(box.position), new Dimensions(box.size));
    }

    public BoundingBox(final float x, final float y, final float w, final float h) {
        this(new Vector2f(x, y), new Dimensions(w, h));
    }

    public BoundingBox(final Vector2f pos, final float w, final float h) {
        this(pos, new Dimensions(w, h));
    }

    public BoundingBox(final float x, final float y, final Dimensions size) {
        this(new Vector2f(x, y), size);
    }

    /**
     * Returns true if the given bounding box and this
     * intersect.
     *
     * @param b another bounding box
     * @return do this and the given bounding box intersect?
     */
    public boolean intersects(final BoundingBox b) {
        if (getWidth() <= 0 || getHeight() <= 0 || b.getWidth() <= 0 || b.getHeight() <= 0)
            return false;
        return b.getX() + b.getWidth() > getX() &&
                b.getY() + b.getHeight() > getY() &&
                b.getX() < getX() + getWidth() &&
                b.getY() < getY() + getHeight();
    }

    /**
     * Does this bounding box fully contain the given one?
     *
     * @param b another bounding box
     * @return does this bounding box fully contain the given one?
     */
    public boolean contains(final BoundingBox b) {
        if (getWidth() <= 0 || getHeight() <= 0 || b.getWidth() <= 0 || b.getHeight() <= 0)
            return false;
        return b.getX() >= getX() &&
                b.getY() >= getY() &&
                b.getMaxX() <= getMaxX() &&
                b.getMaxY() <= getMaxY();
    }

    public Vector2f getCentre() {
        return new Vector2f(position.plus(size.divBy(Dimensions.two())));
    }

    public void centreAround(final Vector2f centre) {
        position = centre.minus(size.divBy(Dimensions.two()));
    }

    @Override
    public String toString() {
        return "BoundingBox{" +
                "position=" + position +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoundingBox that = (BoundingBox) o;
        return Objects.equals(position, that.position) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, size);
    }

    /**
     * Gets {@link #position}.
     *
     * @return the value of {@link #position}
     */
    public Vector2f getPosition() {
        return position;
    }

    /**
     * Sets {@link #position}.
     *
     * @param position the new value of {@link #position}
     */
    public void setPosition(final Vector2f position) {
        this.position = position;
    }

    /**
     * Gets {@link #size}.
     *
     * @return the value of {@link #size}
     */
    public Dimensions getSize() {
        return size;
    }

    /**
     * Sets {@link #size}.
     *
     * @param size the new value of {@link #size}
     */
    public void setSize(final Dimensions size) {
        this.size = size;
    }

    /**
     * Gets x.
     *
     * @return the value of x
     */
    public float getX() {
        return position.getX();
    }

    public float getMaxX() {
        return getX() + getWidth();
    }

    /**
     * Sets x.
     *
     * @param x the new value of x
     */
    public void setX(final float x) {
        position.setX(x);
    }

    /**
     * Gets y.
     *
     * @return the value of y
     */
    public float getY() {
        return position.getY();
    }

    public float getMaxY() {
        return getY() + getHeight();
    }

    /**
     * Sets y.
     *
     * @param y the new value of y
     */
    public void setY(final float y) {
        position.setY(y);
    }

    /**
     * Gets width.
     *
     * @return the value of width
     */
    public float getWidth() {
        return size.getWidth();
    }

    /**
     * Sets width.
     *
     * @param width the new value of width
     */
    public void setWidth(final float width) {
        size.setWidth(width);
    }

    /**
     * Gets height.
     *
     * @return the value of height
     */
    public float getHeight() {
        return size.getHeight();
    }

    /**
     * Sets height.
     *
     * @param height the new value of height
     */
    public void setHeight(final float height) {
        size.setHeight(height);
    }
}
