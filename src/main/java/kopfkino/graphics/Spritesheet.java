/*
 *    Copyright 2022 ***REMOVED*** ***REMOVED***
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

package kopfkino.graphics;

import kopfkino.Dimensions;
import kopfkino.animation.SpriteAnimation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * A spritesheet is a single image holing multiple autonomous images.
 */
public class Spritesheet {
    private final BufferedImage image;
    private final Dimensions spriteSize;

    public Spritesheet(final BufferedImage image, final Dimensions spriteSize) {
        this.image = image;
        this.spriteSize = spriteSize;
    }

    public BufferedImage spriteAt(final int x, final int y) {
        return image.getSubimage((int) spriteSize.getWidth() * x, (int) spriteSize.getHeight() * y,
                (int) spriteSize.getWidth(), (int) spriteSize.getHeight());
    }

    public BufferedImage sprite(final int x, final int y, final int w, final int h) {
        return image.getSubimage(x, y, w, h);
    }

    /**
     * Returns a new {@link SpriteAnimation} with all sprites described by the
     * given array of coordinates. The first entry in the vararg-array is the x
     * coordinate of the first frame, the second entry its y coordinate and so
     * on.
     *
     * @param loop    should the animation loop by default?
     * @param sprites an array of coordinates of the sprite frames, where the
     *                first two are x and y of the first sprite, respectively,
     *                and so on
     *
     * @return a new {@link SpriteAnimation} with all the given frames
     */
    public SpriteAnimation animation(final boolean loop, final int... sprites) {
        if (sprites.length % 2 != 0) {
            throw new IllegalArgumentException("number of singular coordinates must be even");
        }
        final List<BufferedImage> frames = new ArrayList<>(sprites.length / 2);
        for (int i = 0; i < sprites.length; i += 2) {
            frames.add(spriteAt(sprites[i], sprites[i + 1]));
        }

        return new SpriteAnimation(frames, loop);
    }
}
