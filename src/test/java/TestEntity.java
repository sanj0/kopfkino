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

import kopfkino.*;
import kopfkino.animation.SpriteAnimation;
import kopfkino.animation.SpriteAnimationRenderer;
import kopfkino.collision.CircleHitbox;
import kopfkino.collision.Collision;
import kopfkino.graphics.EmptyEntityRenderer;
import kopfkino.graphics.Spritesheet;
import kopfkino.utils.ImageUtils;

public class TestEntity extends Entity {
    private Vector2f position = new Vector2f(100, 100);
    private SpriteAnimation animation;

    public TestEntity(final BoundingBox boundingBox) {
        super(boundingBox, new EmptyEntityRenderer());
        setHitbox(new CircleHitbox(getBoundingBox()::getCentre, getWidth() / 2f));
        setPosition(position);
        animation = new Spritesheet(ImageUtils.renderImage(new Dimensions(300, 100), g -> {
            g.setColor(Colors.CADET_BLUE);
            g.drawOval(new BoundingBox(0, 0, 100, 100));
            g.setColor(Colors.DEEP_PINK);
            g.drawPolygon(new Vector2f(100, 100), new Vector2f(150, 0), new Vector2f(200, 100));
            g.setColor(Colors.CHOCOLATE_BROWN);
            g.drawRect(new BoundingBox(200, 0, 100, 100));
        }), new Dimensions(100, 100)).animation(true, 0, 0, 1, 0, 2, 0);
        setRenderer(new SpriteAnimationRenderer(animation, 5));
    }

    @Override
    public void fixedUpdate() {
        position.set(getPosition());
        getRigidbody().getV().add(Input.input().toVector().times(2));
    }

    @Override
    public void collision(final Collision collision) {
        final int f = -9000;
        if (collision.getPartner().getHitbox() instanceof CircleHitbox) {
            getRigidbody().getF().add(collision.getCollisionVector().times(f));
        } else {
            getRigidbody().getV().add(collision.getCollisionNormal().times(f / 10000f));
        }
    }
}
