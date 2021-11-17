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

package de.sanj0.kopfkino.engine;

import de.sanj0.kopfkino.Directions;
import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Game;
import de.sanj0.kopfkino.collision.Collider;

import java.util.List;

public class CollisionLoop implements Runnable {
    @Override
    public void run() {
        try {
            final List<Entity> entities = Game.getInstance().getCurrentScene().getEntities();
            final int numEntities = entities.size();
            for (int i = 0; i < numEntities - 1; i++) {
                final Entity a = entities.get(i);
                final Directions blockedDirections = new Directions();
                for (int ii = i + 1; ii < numEntities; ii++) {
                    final Entity b = entities.get(ii);
                    final Collider.Collisions result = Collider.detectStatic(a, b);
                    if (result == null) {
                        if (a.getIntersectingEntities().contains(b)) {
                            a.collisionEnd(b);
                            a.getIntersectingEntities().remove(b);
                            b.collisionEnd(a);
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
                        a.getIntersectingEntities().add(b);
                        b.collisionStart(result.getB());
                        b.getIntersectingEntities().add(a);
                    }
                    a.collision(result.getA());
                    b.collision(result.getB());
                }
                a.setBlockedDirections(blockedDirections);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
