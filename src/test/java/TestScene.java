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

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Input;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;
import de.sanj0.kopfkino.scene.Scene;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TestScene extends Scene {
    public TestScene() {
        add(new Entity(new BoundingBox(160, 90, 25, 25)) {
            @Override
            public void renderAfter(final KopfkinoGraphics graphics) {
                if (Input.mouseDown(MouseEvent.BUTTON1)) {
                    graphics.setColor(Color.RED);
                } else if (Input.mouseDown(MouseEvent.BUTTON3)) {
                    graphics.setColor(Color.BLUE);
                } else {
                    graphics.setColor(Color.GREEN);
                }

                graphics.drawPoint(Input.cursorPosition(), 50);
            }

            @Override
            public void fixedUpdate() {
                getPosition().add(Input.direction().times(Vector2f.num(.5f)));
            }
        });
    }
}
