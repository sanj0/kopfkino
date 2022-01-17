/*
 *    Copyright 2022 ***REMOVED*** ***REMOVED***
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

package de.sanj0.kopfkino.ecs.components;

import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Game;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.collision.Collision;
import de.sanj0.kopfkino.ecs.EntityComponent;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;

/**
 * Every fixed update, centers the camera on its subject.
 * <p>{@link #offset} is added to the calculated center position.
 * <p>Example:
 * <pre>{@code
 * myEntity.addComponent(new CenterCameraOnEntityComponent(myEntity, Vector2f.zero()));
 * }</pre>
 */
public class CenterCameraOnEntityComponent extends EntityComponent {

    /**
     * Used internally for centering the Entity.
     */
    private final Vector2f halfResolution;
    /**
     * The offset to be added to the final position of the camera.
     * <p>Set to {@code Vector2f.zero()} for true centering.
     */
    private Vector2f offset;

    public CenterCameraOnEntityComponent(final Entity subject, final Vector2f offset) {
        super(subject);
        this.halfResolution = new Vector2f(Game.getInstance().getResolutionW(),
                Game.getInstance().getResolutionH()).times(Vector2f.num(.5f));
        this.offset = offset;
    }

    @Override
    public void fixedUpdate() {
        Game.getCamera().setPosition(getSubject().getBoundingBox().getCentre()
                .minus(halfResolution.divBy(Game.getCamera().getScale())).plus(offset));
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

    @Override
    public void updateAfter() {
    }

    @Override
    public void renderAfter(final KopfkinoGraphics graphics) {
    }

    /**
     * Gets {@link #offset}.
     *
     * @return the value of {@link #offset}
     */
    public Vector2f getOffset() {
        return offset;
    }

    /**
     * Sets {@link #offset}.
     *
     * @param offset the new value of {@link #offset}
     */
    public void setOffset(final Vector2f offset) {
        this.offset = offset;
    }
}
