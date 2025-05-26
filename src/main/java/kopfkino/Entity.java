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

package kopfkino;

import kopfkino.collision.AABBHitbox;
import kopfkino.collision.Collision;
import kopfkino.collision.Hitbox;
import kopfkino.ecs.EntityComponent;
import kopfkino.graphics.EntityRenderer;
import kopfkino.graphics.RectangleEntityRenderer;
import kopfkino.graphics.Renderable;
import kopfkino.physics.Rigidbody;

import java.util.*;
import java.util.function.Consumer;

/**
 * An entity in a game.
 */
public class Entity implements EntityFunctionality, Renderable {
    private BoundingBox boundingBox;
    private EntityRenderer renderer;
    private Hitbox hitbox;
    private Rigidbody rigidbody;
    private boolean affectedByPhysics = true;

    private Set<Entity> intersectingEntities = Collections.synchronizedSet(new HashSet<>());
    private Directions blockedDirections = new Directions();

    private final List<EntityComponent> components = new ArrayList<>(5);

    public Entity(final BoundingBox boundingBox, final EntityRenderer renderer) {
        this.boundingBox = boundingBox;
        this.renderer = renderer;
        this.hitbox = new AABBHitbox(this::getBoundingBox);
        renderer.setSubject(this);
        rigidbody = new Rigidbody(getWidth() * getHeight());
    }

    public Entity(final BoundingBox boundingBox) {
        this(boundingBox, new RectangleEntityRenderer().withArc(5, 5));
    }

    public final void removeFromScene() {
        Game.currentScene().remove(this);
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
        for (int i = 0; i < components.size(); i++) {
            final EntityComponent ec = components.get(i);
            ec.renderAfter(graphics);
            ec.updateAfter();
        }
        renderAfter(graphics);
        updateAfter();
    }

    /**
     * Is called every fixed update tick.
     * <p>
     * Base implementation empty.
     */
    @Override
    public void fixedUpdate() {
    }

    @Override
    public void collision(final Collision collision) {
    }

    @Override
    public void collisionStart(final Collision collision) {
    }

    @Override
    public void collisionEnd(final Entity partner) {
    }

    /**
     * Is called every time before this entity is rendered.
     * <p>
     * Base implementation empty.
     */
    @Override
    public void updateBefore() {
    }

    /**
     * Is called every time after this entity is rendered.
     * <p>
     * Base implementation empty.
     */
    @Override
    public void updateAfter() {
    }

    /**
     * Render graphics before this entity's {@link #renderer}.
     * <p>
     * Base implementation empty.
     *
     * @param graphics graphics to render to
     */
    @Override
    public void renderBefore(final KopfkinoGraphics graphics) {
    }

    /**
     * Render graphics after this entity's {@link #renderer}.
     * <p>
     * Base implementation empty.
     *
     * @param graphics graphics to render to
     */
    @Override
    public void renderAfter(final KopfkinoGraphics graphics) {
    }

    public void foreachComponent(final Consumer<EntityComponent> action) {
        for (int i = 0; i < components.size(); i++) {
            action.accept(components.get(i));
        }
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
     * Gets {@link #hitbox}.
     *
     * @return the value of {@link #hitbox}
     */
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Sets {@link #hitbox}.
     *
     * @param hitbox the new value of {@link #hitbox}
     */
    public void setHitbox(final Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * Gets {@link #rigidbody}.
     *
     * @return the value of {@link #rigidbody}
     */
    public Rigidbody getRigidbody() {
        return rigidbody;
    }

    /**
     * Sets {@link #rigidbody}.
     *
     * @param rigidbody the new value of {@link #rigidbody}
     */
    public void setRigidbody(final Rigidbody rigidbody) {
        this.rigidbody = rigidbody;
    }

    /**
     * Gets {@link #affectedByPhysics}.
     *
     * @return the value of {@link #affectedByPhysics}
     */
    public boolean isAffectedByPhysics() {
        return affectedByPhysics;
    }

    /**
     * Sets {@link #affectedByPhysics}.
     *
     * @param affectedByPhysics the new value of {@link #affectedByPhysics}
     */
    public void setAffectedByPhysics(final boolean affectedByPhysics) {
        this.affectedByPhysics = affectedByPhysics;
    }

    /**
     * Gets {@link #intersectingEntities}.
     *
     * @return the value of {@link #intersectingEntities}
     */
    public Set<Entity> getIntersectingEntities() {
        return intersectingEntities;
    }

    /**
     * Sets {@link #intersectingEntities}.
     *
     * @param intersectingEntities the new value of {@link #intersectingEntities}
     */
    public void setIntersectingEntities(final Set<Entity> intersectingEntities) {
        this.intersectingEntities = intersectingEntities;
    }

    /**
     * Gets {@link #blockedDirections}.
     *
     * @return the value of {@link #blockedDirections}
     */
    public Directions getBlockedDirections() {
        return blockedDirections;
    }

    /**
     * Sets {@link #blockedDirections}.
     *
     * @param blockedDirections the new value of {@link #blockedDirections}
     */
    public void setBlockedDirections(final Directions blockedDirections) {
        this.blockedDirections = blockedDirections;
    }

    /**
     * Gets {@link #components}.
     *
     * @return the value of {@link #components}
     */
    public List<EntityComponent> getComponents() {
        return components;
    }

    public boolean addComponent(final EntityComponent entityComponent) {
        return components.add(entityComponent);
    }

    public boolean removeComponent(final EntityComponent c) {
        return components.remove(c);
    }

    public void clearComponents() {
        components.clear();
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

    @Override
    public boolean equals(final Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Entity{" +
                "boundingBox=" + boundingBox +
                '}';
    }
}
