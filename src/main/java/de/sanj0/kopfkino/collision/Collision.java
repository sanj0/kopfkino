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

package de.sanj0.kopfkino.collision;

import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Vector2f;

public class Collision {
    private final Entity partner;
    /**
     * The normal of this collision.
     */
    private final Vector2f collisionNormal;

    public Collision(final Entity partner, final Vector2f collisionNormal) {
        this.partner = partner;
        this.collisionNormal = collisionNormal;
    }

    /**
     * Gets {@link #partner}.
     *
     * @return the value of {@link #partner}
     */
    public Entity getPartner() {
        return partner;
    }

    /**
     * Gets {@link #collisionNormal}.
     *
     * @return the value of {@link #collisionNormal}
     */
    public Vector2f getCollisionNormal() {
        return collisionNormal;
    }
}
