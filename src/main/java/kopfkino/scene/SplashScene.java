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

package kopfkino.scene;

import de.sanj0.kopfkino.*;
import kopfkino.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * A splash screen scene that may be shown before the game when the according parameter in {@link Game#start(int, Scene, int, int)}
 * is greater than 0.
 */
public class SplashScene extends Scene {
    public static final int DEFAULT_DURATION = 3000;

    private final Scene next;
    private final int duration;
    private final BufferedImage image;
    private final Dimensions size;
    private int ticks;

    public SplashScene(final Scene next, final int duration, final BufferedImage image) {
        this.next = next;
        this.duration = duration;
        this.image = image;
        final float imgWidth = Math.min(Game.getInstance().getResolutionW() * .25f, image.getWidth());
        size = new Dimensions(imgWidth, imgWidth / image.getWidth() * image.getHeight());
    }

    @Override
    public void render(final KopfkinoGraphics graphics) {
        graphics.drawRect(new BoundingBox(0, 0, new Dimensions(Game.getInstance().getResolutionW(), Game.getInstance().getResolutionH())));
        if ((float) ticks / duration < .85f) {
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(ticks / (duration * .5f), 1)));
        } else {
            final float subProgress = (ticks - .85f * duration) / (duration * .15f);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - subProgress));
        }
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.drawImage(image, new BoundingBox(Game.getInstance().getResolutionW() * .5f - size.getWidth() * .5f,
                Game.getInstance().getResolutionH() * .5f - size.getHeight() * .5f, size));
    }

    @Override
    public void fixedUpdate() {
        Game.getCamera().setPosition(Vector2f.zero());
        ticks++;
        if (ticks >= duration || Input.mouseDown(1) || Input.keyDown(KeyEvent.VK_SPACE)) {
            Game.setScene(next);
        }
    }
}
