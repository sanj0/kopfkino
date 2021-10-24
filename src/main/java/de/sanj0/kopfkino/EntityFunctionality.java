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

import de.sanj0.kopfkino.collision.Collision;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;

/**
 * Encapsulated entity functionality.
 */
public interface EntityFunctionality {
    /**
     * Is called every fixed update tick.
     * <p>Base implementation empty.
     */
    void fixedUpdate();

    /**
     * Is called whenever this entity collides with another.
     *
     * @param collision the collision that occurred
     */
    void collision(final Collision collision);

    /**
     * A collision that wasn't there in the loop before.
     *
     * @param collision the collision that wasn't there the loop before
     */
    void collisionStart(final Collision collision);

    /**
     * Called when a collision from the previous loop no longer exists.
     *
     * @param partner the lost partner entity
     */
    void collisionEnd(final Entity partner);

    /**
     * Is called every time before this entity is rendered.
     * <p>Base implementation empty.
     */
    void updateBefore();

    /**
     * Is called every time after this entity is rendered.
     * <p>Base implementation empty.
     */
    void updateAfter();

    /**
     * Render graphics before this entity's {@code renderer}.
     * <p>Base implementation empty.
     *
     * @param graphics graphics to render to
     */
    void renderBefore(final KopfkinoGraphics graphics);

    /**
     * Render graphics after this entity's {@code renderer}.
     * <p>Base implementation empty.
     *
     * @param graphics graphics to render to
     */
    void renderAfter(final KopfkinoGraphics graphics);
}
