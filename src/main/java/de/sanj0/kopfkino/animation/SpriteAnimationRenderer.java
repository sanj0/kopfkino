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

package de.sanj0.kopfkino.animation;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Time;
import de.sanj0.kopfkino.graphics.EntityRenderer;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;

/**
 * An {@link EntityRenderer} that renders a {@link SpriteAnimation} with a fixed
 * frame rate.
 */
public class SpriteAnimationRenderer extends EntityRenderer {
    /**
     * The animation to render.
     */
    private SpriteAnimation animation;
    /**
     * The frame rate of the animation.
     */
    private int fps;
    /**
     * Internally used to count the number of passed milliseconds.
     */
    private long msBuf;

    public SpriteAnimationRenderer(final SpriteAnimation animation, final int fps) {
        this.animation = animation;
        this.fps = fps;
    }

    @Override
    protected void renderEntity(final KopfkinoGraphics graphics, final BoundingBox bb) {
        msBuf += Time.getRenderTime();
        if (msBuf >= 1000 / fps) {
            msBuf = msBuf - 1000 / fps;
            animation.next();
        }
        graphics.drawImage(animation.currentFrame(), bb);
    }

    /**
     * Gets {@link #animation}.
     *
     * @return the value of {@link #animation}
     */
    public SpriteAnimation getAnimation() {
        return animation;
    }

    /**
     * Sets {@link #animation}.
     *
     * @param animation the new value of {@link #animation}
     */
    public void setAnimation(final SpriteAnimation animation) {
        this.animation = animation;
    }

    /**
     * Gets {@link #fps}.
     *
     * @return the value of {@link #fps}
     */
    public int getFps() {
        return fps;
    }

    /**
     * Sets {@link #fps}.
     *
     * @param fps the new value of {@link #fps}
     */
    public void setFps(final int fps) {
        this.fps = fps;
    }
}
