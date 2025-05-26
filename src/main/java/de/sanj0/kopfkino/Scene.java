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

import de.sanj0.kopfkino.collision.Collider;
import de.sanj0.kopfkino.ecs.EntityComponent;
import de.sanj0.kopfkino.graphics.*;
import de.sanj0.kopfkino.gui.Gui;
import de.sanj0.kopfkino.physics.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Defines what the game currently shows and "plays with".
 */
public class Scene implements Renderable {
    /**
     * A potential gui that gets drawn on top of the entites.
     * May be null in case of no gui.
     */
    private Gui gui = null;
    private final List<Entity> entities = new ArrayList<>();
    private final World physicsWorld;
    private Camera camera;

    public Scene() {
        camera = new Camera2D(new BoundingBox(0, 0, Game.getInstance().getResolutionW(), Game.getInstance().getResolutionH()),
                new Dimensions(Game.getInstance().getResolutionW(), Game.getInstance().getResolutionH()), 1.0f);
        physicsWorld = new World(this, Vector2f.zero(), World.DEFAULT_FRICTION);
    }

    @Override
    public void render(final KopfkinoGraphics graphics) {
        graphics.setRenderingHints(DefaultRenderingHints.getHints());
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(graphics.copy());
        }
        if (gui != null) {
            gui.render(graphics);
        }
    }

    /**
     * Is called every fixed tick and updates all entites and their components accordingly.
     * May be overwritten (calling super!) in order to add Scene-wide functionality.
     */
    public void fixedUpdate() {
        for (int i = 0; i < entities.size(); i++) {
            final Entity e = entities.get(i);
            e.fixedUpdate();
            final List<EntityComponent> components = e.getComponents();
            for (int ii = 0; ii < components.size(); ii++) {
                components.get(ii).fixedUpdate();
            }
        }
        physicsWorld.update();
    }

    public void collisionDetection() {
        for (int i = 0; i < entities.size() - 1; i++) {
            final Entity a = entities.get(i);
            final Directions blockedDirections = new Directions();
            for (int ii = i + 1; ii < entities.size(); ii++) {
                final Entity b = entities.get(ii);
                final Collider.Collisions result = Collider.detectStatic(a, b);
                if (result == null) {
                    if (a.getIntersectingEntities().contains(b)) {
                        a.collisionEnd(b);
                        a.foreachComponent(c -> c.collisionEnd(b));
                        a.getIntersectingEntities().remove(b);
                        b.collisionEnd(a);
                        b.foreachComponent(c -> c.collisionEnd(a));
                        b.getIntersectingEntities().remove(a);
                    }
                    continue;
                }
                // collision happened
                final Directions.Direction dir = a.getBoundingBox().collisionDirection(b.getBoundingBox());
                if (dir != null) {
                    blockedDirections.add(a.getBoundingBox().collisionDirection(b.getBoundingBox()));
                }
                if (!a.getIntersectingEntities().contains(b)) {
                    Game.getInstance().getCurrentScene().getPhysicsWorld().handleCollision(a, result.getA());
                    a.collisionStart(result.getA());
                    a.foreachComponent(c -> c.collisionStart(result.getA()));
                    a.getIntersectingEntities().add(b);
                    b.collisionStart(result.getB());
                    b.foreachComponent(c -> c.collisionStart(result.getB()));
                    b.getIntersectingEntities().add(a);
                }
                a.collision(result.getA());
                a.foreachComponent(c -> c.collision(result.getA()));
                b.collision(result.getB());
                b.foreachComponent(c -> c.collision(result.getB()));
            }
            a.setBlockedDirections(blockedDirections);
        }
    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    /**
     * Gets {@link #physicsWorld}.
     *
     * @return the value of {@link #physicsWorld}
     */
    public World getPhysicsWorld() {
        return physicsWorld;
    }

    /**
     * Gets {@link #camera}.
     *
     * @return the value of {@link #camera}
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Sets {@link #camera}.
     *
     * @param camera the new value of {@link #camera}
     */
    public void setCamera(final Camera camera) {
        this.camera = camera;
    }

    /**
     * Returns the number of elements in this scene.  If this scene contains
     * more than {@code Integer.MAX_VALUE} elements, returns {@code
     * Integer.MAX_VALUE}.
     *
     * @return the number of elements in this scene
     */
    public int size() {
        return entities.size();
    }

    /**
     * Returns {@code true} if this scene contains no elements.
     *
     * @return {@code true} if this scene contains no elements
     */
    public boolean isEmpty() {
        return entities.isEmpty();
    }

    /**
     * Returns {@code true} if this scene contains the specified element. More
     * formally, returns {@code true} if and only if this scene contains at
     * least one element {@code e} such that {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this scene is to be tested
     *
     * @return {@code true} if this scene contains the specified element
     */
    public boolean contains(final Object o) {
        return entities.contains(o);
    }

    /**
     * Appends the specified element to the end of this scene (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this scene.  In particular, some scenes will
     * refuse to add null elements, and others will impose restrictions on the
     * type of elements that may be added.  List classes should clearly specify
     * in their documentation any restrictions on what elements may be added.
     *
     * @param entity element to be appended to this scene
     *
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(final Entity entity) {
        return entities.add(entity);
    }

    /**
     * Removes the first occurrence of the specified element from this scene, if
     * it is present (optional operation).  If this scene does not contain the
     * element, it is unchanged.  More formally, removes the element with the
     * lowest index {@code i} such that {@code Objects.equals(o, get(i))} (if
     * such an element exists).  Returns {@code true} if this scene contained
     * the specified element (or equivalently, if this scene changed as a result
     * of the call).
     *
     * @param o element to be removed from this scene, if present
     *
     * @return {@code true} if this scene contained the specified element
     */
    public boolean remove(final Object o) {
        return entities.remove(o);
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this scene, in the order that they are returned by the specified
     * collection's iterator (optional operation).  The behavior of this
     * operation is undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this scene, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this scene
     *
     * @return {@code true} if this scene changed as a result of the call
     */
    public boolean addAll(final Collection<? extends Entity> c) {
        return entities.addAll(c);
    }

    /**
     * Inserts all of the elements in the specified collection into this scene
     * at the specified position (optional operation).  Shifts the element
     * currently at that position (if any) and any subsequent elements to the
     * right (increases their indices).  The new elements will appear in this
     * scene in the order that they are returned by the specified collection's
     * iterator.  The behavior of this operation is undefined if the specified
     * collection is modified while the operation is in progress.  (Note that
     * this will occur if the specified collection is this scene, and it's
     * nonempty.)
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this scene
     *
     * @return {@code true} if this scene changed as a result of the call
     */
    public boolean addAll(final int index, final Collection<? extends Entity> c) {
        return entities.addAll(index, c);
    }

    /**
     * Sorts this scene according to the order induced by the specified {@link
     * Comparator}.  The sort is <i>stable</i>: this method must not reorder
     * equal elements.
     *
     * <p>All elements in this scene must be <i>mutually comparable</i> using
     * the specified comparator (that is, {@code c.compare(e1, e2)} must not
     * throw a {@code ClassCastException} for any elements {@code e1} and {@code
     * e2} in the scene).
     *
     * <p>If the specified comparator is {@code null} then all elements in this
     * scene must implement the {@link Comparable} interface and the elements'
     * {@linkplain Comparable natural ordering} should be used.
     *
     * @param c the {@code Comparator} used to compare scene elements. A {@code
     *          null} value indicates that the elements' {@linkplain Comparable
     *          natural ordering} should be used
     */
    public void sort(final Comparator<? super Entity> c) {
        entities.sort(c);
    }

    /**
     * Removes all of the elements from this scene (optional operation). The
     * scene will be empty after this call returns.
     */
    public void clear() {
        entities.clear();
    }

    /**
     * Returns the element at the specified position in this scene.
     *
     * @param index index of the element to return
     *
     * @return the element at the specified position in this scene
     * @throws IndexOutOfBoundsException if the index is out of range ({@code
     *                                   index < 0 || index >= size()})
     */
    public Entity get(final int index) {
        return entities.get(index);
    }

    /**
     * Replaces the element at the specified position in this scene with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     *
     * @return the element previously at the specified position
     */
    public Entity set(final int index, final Entity element) {
        return entities.set(index, element);
    }

    /**
     * Inserts the specified element at the specified position in this scene
     * (optional operation).  Shifts the element currently at that position (if
     * any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public void add(final int index, final Entity element) {
        entities.add(index, element);
    }

    /**
     * Removes the element at the specified position in this scene (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * scene.
     *
     * @param index the index of the element to be removed
     *
     * @return the element previously at the specified position
     */
    public Entity remove(final int index) {
        return entities.remove(index);
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this scene, or -1 if this scene does not contain the element. More
     * formally, returns the lowest index {@code i} such that {@code
     * Objects.equals(o, get(i))}, or -1 if there is no such index.
     *
     * @param o element to search for
     *
     * @return the index of the first occurrence of the specified element in
     * this scene, or -1 if this scene does not contain the element
     */
    public int indexOf(final Object o) {
        return entities.indexOf(o);
    }

    /**
     * Removes all of the elements of this collection that satisfy the given
     * predicate.  Errors or runtime exceptions thrown during iteration or by
     * the predicate are relayed to the caller.
     *
     * @param filter a predicate which returns {@code true} for elements to be
     *               removed
     *
     * @return {@code true} if any elements were removed
     */
    public boolean removeIf(final Predicate<? super Entity> filter) {
        return entities.removeIf(filter);
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     *
     * @param action The action to be performed for each element
     */
    public void forEach(final Consumer<? super Entity> action) {
        // make it weird for thread safety by tolerance of
        // potential one frame semantic lags
        for (int i = 0; i < entities.size(); i++) {
            final Entity e = entities.get(i);
            if (e == null) continue;
            action.accept(e);
        }
    }

    /**
     * Gets a snapshot copy of {@link #entities}.
     * <p>No direct reference to the {@link Entity} list is
     * disclosed due to thread safety.
     *
     * @return a snapshot copy of {@link #entities}
     */
    public List<Entity> entitySnapshot() {
        return new ArrayList<>(entities);
    }
}
