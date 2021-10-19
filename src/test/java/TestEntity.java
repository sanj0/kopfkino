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
import de.sanj0.kopfkino.graphics.ImageEntityRenderer;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TestEntity extends Entity {
    public TestEntity(final BoundingBox boundingBox) {
        super(boundingBox, new ImageEntityRenderer(null, PackagedResources.loadImage("img/LiamNeeson.jpg")));
    }

    @Override
    public void renderAfter(final KopfkinoGraphics graphics) {
        if (Input.mouseDown(MouseEvent.BUTTON1)) {
            graphics.setColor(Color.RED);
            Game.getInstance().getCurrentScene().getCamera().getPosition().add(new Vector2f(1, 0));
        } else if (Input.mouseDown(MouseEvent.BUTTON3)) {
            graphics.setColor(Color.BLUE);
            Game.getInstance().getCurrentScene().getCamera().getPosition().add(new Vector2f(-1, 0));
        } else {
            graphics.setColor(Color.GREEN);
        }

        graphics.drawPoint(Input.cursorPosition(), 50);
    }

    @Override
    public void fixedUpdate() {
        getPosition().add(Input.direction().times(Vector2f.num(.5f)));
    }
}
