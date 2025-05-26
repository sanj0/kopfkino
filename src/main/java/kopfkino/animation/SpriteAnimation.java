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

package kopfkino.animation;

import kopfkino.graphics.Spritesheet;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * An animation composed of one or more image frames that are "played" in
 * order.
 *
 * @see Spritesheet#animation(boolean, int...)
 */
public class SpriteAnimation {
    private List<BufferedImage> frames;
    private int head = 0;
    private boolean loop;

    public SpriteAnimation(final List<BufferedImage> frames, final boolean loop) {
        this.frames = frames;
        this.loop = loop;
    }

    public void reset() {
        head = 0;
    }

    public void next() {
        head++;
        if (head > frames.size() - 1) {
            if (loop) head = 0;
            else head = frames.size() - 1;
        }
    }

    public BufferedImage currentFrame() {
        return frames.get(head);
    }

    /**
     * Gets {@link #frames}.
     *
     * @return the value of {@link #frames}
     */
    public List<BufferedImage> getFrames() {
        return frames;
    }

    /**
     * Sets {@link #frames}.
     *
     * @param frames the new value of {@link #frames}
     */
    public void setFrames(final List<BufferedImage> frames) {
        this.frames = frames;
    }

    /**
     * Gets {@link #head}.
     *
     * @return the value of {@link #head}
     */
    public int getHead() {
        return head;
    }

    /**
     * Sets {@link #head}.
     *
     * @param head the new value of {@link #head}
     */
    public void setHead(final int head) {
        this.head = head;
    }

    /**
     * Gets {@link #loop}.
     *
     * @return the value of {@link #loop}
     */
    public boolean isLoop() {
        return loop;
    }

    /**
     * Sets {@link #loop}.
     *
     * @param loop the new value of {@link #loop}
     */
    public void setLoop(final boolean loop) {
        this.loop = loop;
    }
}
