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

import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Game;
import de.sanj0.kopfkino.collision.Collider;
import de.sanj0.kopfkino.collision.Collision;

import java.util.List;
import java.util.Map;

public class CollisionLoop implements Runnable {
    @Override
    public void run() {
        try {
            final List<Entity> entities = Game.getInstance().getCurrentScene().getEntities();
            final int numEntities = entities.size();
            for (int i = 0; i < numEntities - 1; i++) {
                final Entity a = entities.get(i);
                for (int ii = i + 1; ii < numEntities; ii++) {
                    final Entity b = entities.get(ii);
                    final Map<Entity, Collision> result = Collider.detectStatic(a, b);
                    if (result == null) {
                        if (a.getIntersectingEntities().contains(b)) {
                            a.collisionEnd(b);
                            a.getIntersectingEntities().remove(b);
                        }
                        if (b.getIntersectingEntities().contains(a)) {
                            b.collisionEnd(a);
                            b.getIntersectingEntities().remove(a);
                        }
                        continue;
                    }

                    if (!a.getIntersectingEntities().contains(b)) {
                        a.collisionStart(result.get(a));
                        a.getIntersectingEntities().add(b);
                    }
                    a.collision(result.get(a));
                    if (!b.getIntersectingEntities().contains(a)) {
                        b.collisionStart(result.get(b));
                        b.getIntersectingEntities().add(a);
                    }
                    b.collision(result.get(b));
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
