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

import de.sanj0.kopfkino.graphics.EntityRenderer;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;
import de.sanj0.kopfkino.graphics.RectangleEntityRenderer;
import de.sanj0.kopfkino.graphics.Renderable;

/**
 * An entity in a game.
 */
public class Entity implements Renderable {
    private BoundingBox boundingBox;
    private EntityRenderer renderer;

    public Entity(final BoundingBox boundingBox, final EntityRenderer renderer) {
        this.boundingBox = boundingBox;
        this.renderer = renderer;
        renderer.setSubject(this);
    }

    public Entity(final BoundingBox boundingBox) {
        this(boundingBox, new RectangleEntityRenderer().withArc(5, 5));
    }

    /**
     * Renders this Entity and calls the update methods {@link #updateBefore()}
     * and {@link #updateAfter()}.
     */
    @Override
    public final void render(final KopfkinoGraphics graphics) {
        updateBefore();
        renderBefore(graphics);
        renderer.render(graphics);
        renderAfter(graphics);
        updateAfter();
    }

    /**
     * Is called every fixed update tick.
     * <p>Base implementation empty.
     */
    public void fixedUpdate() {
    }

    /**
     * Is called every time before this entity is rendered.
     * <p>Base implementation empty.
     */
    public void updateBefore() {
    }

    /**
     * Is called every time after this entity is rendered.
     * <p>Base implementation empty.
     */
    public void updateAfter() {
    }

    /**
     * Render graphics before this entity's {@link #renderer}.
     * <p>Base implementation empty.
     *
     * @param graphics graphics to render to
     */
    public void renderBefore(final KopfkinoGraphics graphics) {
    }

    /**
     * Render graphics after this entity's {@link #renderer}.
     * <p>Base implementation empty.
     *
     * @param graphics graphics to render to
     */
    public void renderAfter(final KopfkinoGraphics graphics) {
    }

    /**
     * Gets {@link #boundingBox}.
     *
     * @return the value of {@link #boundingBox}
     */
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    /**
     * Sets {@link #boundingBox}.
     *
     * @param boundingBox the new value of {@link #boundingBox}
     */
    public void setBoundingBox(final BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * Gets {@link #renderer}.
     *
     * @return the value of {@link #renderer}
     */
    public EntityRenderer getRenderer() {
        return renderer;
    }

    /**
     * Sets {@link #renderer}.
     *
     * @param renderer the new value of {@link #renderer}
     */
    public void setRenderer(final EntityRenderer renderer) {
        renderer.setSubject(this);
        this.renderer = renderer;
    }

    /**
     * Gets the bounding box's position.
     *
     * @return the position of the bounding box
     */
    public Vector2f getPosition() {
        return boundingBox.getPosition();
    }

    /**
     * Sets the bounding box's position.
     *
     * @param position a position
     */
    public void setPosition(final Vector2f position) {
        boundingBox.setPosition(position);
    }

    public Dimensions getSize() {
        return boundingBox.getSize();
    }

    public void setSize(final Dimensions size) {
        boundingBox.setSize(size);
    }

    /**
     * Gets x.
     *
     * @return the value of x
     */
    public float getX() {
        return boundingBox.getX();
    }

    /**
     * Sets x.
     *
     * @param x the new value of x
     */
    public void setX(final float x) {
        boundingBox.setX(x);
    }

    /**
     * Gets y.
     *
     * @return the value of y
     */
    public float getY() {
        return boundingBox.getY();
    }

    /**
     * Sets y.
     *
     * @param y the new value of y
     */
    public void setY(final float y) {
        boundingBox.setY(y);
    }

    /**
     * Gets width.
     *
     * @return the value of width
     */
    public float getWidth() {
        return boundingBox.getWidth();
    }

    /**
     * Sets width.
     *
     * @param width the new value of width
     */
    public void setWidth(final float width) {
        boundingBox.setWidth(width);
    }

    /**
     * Gets height.
     *
     * @return the value of height
     */
    public float getHeight() {
        return boundingBox.getHeight();
    }

    /**
     * Sets height.
     *
     * @param height the new value of height
     */
    public void setHeight(final float height) {
        boundingBox.setHeight(height);
    }
}
