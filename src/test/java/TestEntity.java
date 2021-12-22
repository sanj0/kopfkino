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

import de.sanj0.kopfkino.*;
import de.sanj0.kopfkino.collision.CircleHitbox;
import de.sanj0.kopfkino.collision.Collision;
import de.sanj0.kopfkino.graphics.ImageEntityRenderer;

public class TestEntity extends Entity {
    public TestEntity(final BoundingBox boundingBox) {
        super(boundingBox, new ImageEntityRenderer(PackagedResources.loadImage("img/LiamNeeson.jpg")));
    }

    @Override
    public void fixedUpdate() {
        getRigidbody().getV().add(Input.input().toVector().times(2));
    }

    @Override
    public void collision(final Collision collision) {
        if (collision.getPartner().getHitbox() instanceof CircleHitbox) {
            getRigidbody().getF().add(collision.getCollisionNormal().times(-100));
        }
    }

    @Override
    public void collisionStart(final Collision collision) {
    }

    @Override
    public void collisionEnd(final Entity partner) {
    }
}
