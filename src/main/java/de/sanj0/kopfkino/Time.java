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

package de.sanj0.kopfkino;

/**
 * Static time utilities.
 */
public class Time {
    /**
     * The amount of milliseconds between each fixed update.
     */
    protected static int fixedUpdateRate;

    /**
     * The amount of milliseconds it took to render the last frame.
     */
    protected static long renderTime;

    /**
     * The amount of milliseconds it actually took to perform the last fixed update.
     */
    protected static long fixedUpdateTime;

    /**
     * The amount of milliseconds it took to go through collision
     * detection the last time.
     */
    protected static long collisionDetectionTime;

    /**
     * Gets {@link #fixedUpdateRate}.
     *
     * @return the value of {@link #fixedUpdateRate}
     */
    public static int getFixedUpdateRate() {
        return fixedUpdateRate;
    }

    /**
     * Gets {@link #renderTime}.
     *
     * @return the value of {@link #renderTime}
     */
    public static long getRenderTime() {
        return renderTime;
    }

    /**
     * Returns the current fps.
     *
     * @return the current frames per second
     */
    public static float fps() {
        return 1000f / renderTime;
    }

    /**
     * Gets {@link #fixedUpdateTime}.
     *
     * @return the value of {@link #fixedUpdateTime}
     */
    public static long getFixedUpdateTime() {
        return fixedUpdateTime;
    }

    /**
     * Gets {@link #collisionDetectionTime}.
     *
     * @return the value of {@link #collisionDetectionTime}
     */
    public static long getCollisionDetectionTime() {
        return collisionDetectionTime;
    }
}
