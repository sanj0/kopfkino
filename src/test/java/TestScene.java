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

import de.sanj0.kopfkino.*;
import de.sanj0.kopfkino.collision.CircleHitbox;
import de.sanj0.kopfkino.collision.Collision;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;
import de.sanj0.kopfkino.graphics.OvalEntityRenderer;
import de.sanj0.kopfkino.physics.World;
import de.sanj0.kopfkino.scene.Scene;
import de.sanj0.kopfkino.utils.Colors;

import java.awt.*;

public class TestScene extends Scene {
    public TestScene() {
        getPhysicsWorld().setFriction(0.008f);
        getPhysicsWorld().setStoppingThreshold(.1f);
        add(new TestEntity(new BoundingBox(160, 90, 100, 100)));
        add(new Entity(new BoundingBox(0, 0, 50, 50), new OvalEntityRenderer()) {
            {setAffectedByPhysics(false);}
            Vector2f lastCollisionVector = Vector2f.zero();
            @Override
            public void renderAfter(final KopfkinoGraphics graphics) {
                graphics.setColor(Colors.ACTIVE_GREEN);
                graphics.drawPoint(getBoundingBox().getCentre().plus(lastCollisionVector.times(new Vector2f(-100, -100))), 25);
            }

            @Override
            public void fixedUpdate() {
                setPosition(Input.cursorPosition().minus(getSize().divBy(Dimensions.two())));
            }

            @Override
            public void collisionStart(final Collision collision) {
                getRenderer().getRenderConfig().setColor(Color.RED);
            }

            @Override
            public void collisionEnd(final Entity partner) {
                getRenderer().getRenderConfig().setColor(Color.BLACK);
            }

            @Override
            public void collision(final Collision collision) {
                lastCollisionVector = collision.getCollisionNormal();
            }
        });
        entitySnapshot().get(1).setHitbox(new CircleHitbox(entitySnapshot().get(1).getBoundingBox()::getCentre, 25f));
        add(new Entity(new BoundingBox(-500, 1030, 3000, 50)){{
                setAffectedByPhysics(false);}});
        add(new Entity(new BoundingBox(-500, 0, 3000, 50)){{
            setAffectedByPhysics(false);}});
        add(new Entity(new BoundingBox(0, 0, 50, 3000)){{
            setAffectedByPhysics(false);}});
        add(new Entity(new BoundingBox(1870, 0, 50, 3000)){{
            setAffectedByPhysics(false);}});
        add(new KeyframeAnimationShowcase());
    }
}
