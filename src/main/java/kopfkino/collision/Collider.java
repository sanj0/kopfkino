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

package kopfkino.collision;

import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.Entity;
import kopfkino.Vector2f;
import kopfkino.utils.MathUtils;

/**
 * Static functions to detect collisions
 */
public class Collider {
    // detects a static collision.
    // returns null if no collision was detected and a map containing collision
    // events for both entities.
    public static Collisions detectStatic(final Entity a, final Entity b) {
        final Hitbox aBox = a.getHitbox();
        final Hitbox bBox = b.getHitbox();

        if (aBox instanceof AABBHitbox) {
            if (bBox instanceof AABBHitbox) {
                if (!AABBtoAABB((AABBHitbox) aBox, (AABBHitbox) bBox))
                    return null;
            } else {
                if (!AABBtoCircle((AABBHitbox) aBox, (CircleHitbox) bBox))
                    return null;
            }
        } else {
            if (bBox instanceof AABBHitbox) {
                if (!AABBtoCircle((AABBHitbox) bBox, (CircleHitbox) aBox))
                    return null;
            } else {
                if (!circleToCircle((CircleHitbox) aBox, (CircleHitbox) bBox))
                    return null;
            }
        }
        final Vector2f collisionVectorAtoB = bBox.getBoundingBox().getCentre().minus(aBox.getBoundingBox().getCentre()).normalise();
        final Vector2f collisionNormal = aBox.getBoundingBox().collisionNormal(bBox.getBoundingBox());
        return new Collisions(new Collision(b, collisionNormal, collisionVectorAtoB), new Collision(a, collisionNormal.times(Vector2f.negOne()), collisionVectorAtoB.times(Vector2f.negOne())));
    }

    private static boolean AABBtoAABB(final AABBHitbox a, final AABBHitbox b) {
        return a.getBoundingBox().intersects(b.getBoundingBox());
    }

    // inspired by https://learnopengl.com/In-Practice/2D-Game/Collisions/Collision-detection
    private static boolean AABBtoCircle(final AABBHitbox a, final CircleHitbox b) {
        final BoundingBox aabb = a.getBoundingBox();
        final Vector2f aabbCentre = aabb.getCentre();
        final Dimensions aabbSizeDivTwo = aabb.getSize().divBy(Dimensions.two());
        final Vector2f centreDiff = b.getCentreSupplier().get().minus(aabbCentre);
        final Vector2f centreDiffClamped = new Vector2f(MathUtils.clamp(centreDiff.getX(), -aabbSizeDivTwo.getWidth(), aabbSizeDivTwo.getWidth()),
                MathUtils.clamp(centreDiff.getY(), -aabbSizeDivTwo.getHeight(), aabbSizeDivTwo.getHeight()));
        // retrieve vector between center circle and closest point AABB and check if length <= radius
        final Vector2f difference = aabbCentre.plus(centreDiffClamped).minus(b.getCentreSupplier().get());
        return difference.magnitude() < b.getRadiusSupplier().get();
    }

    private static boolean circleToCircle(final CircleHitbox a, CircleHitbox b) {
        return a.getCentreSupplier().get().minus(b.getCentreSupplier().get()).magnitude() <= a.getRadiusSupplier().get() + b.getRadiusSupplier().get();
    }

    public static class Collisions {
        private final Collision a;
        private final Collision b;

        public Collisions(final Collision a, final Collision b) {
            this.a = a;
            this.b = b;
        }

        /**
         * Gets {@link #a}.
         *
         * @return the value of {@link #a}
         */
        public Collision getA() {
            return a;
        }

        /**
         * Gets {@link #b}.
         *
         * @return the value of {@link #b}
         */
        public Collision getB() {
            return b;
        }
    }
}
