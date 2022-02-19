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

/**
 * A value at a given time offset.
 */
public class Keyframe {
    private int time;
    private float value;

    public Keyframe(final int time, final float value) {
        this.time = time;
        this.value = value;
    }

    /**
     * Gets {@link #time}.
     *
     * @return the value of {@link #time}
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets {@link #time}.
     *
     * @param time the new value of {@link #time}
     */
    public void setTime(final int time) {
        this.time = time;
    }

    /**
     * Gets {@link #value}.
     *
     * @return the value of {@link #value}
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets {@link #value}.
     *
     * @param value the new value of {@link #value}
     */
    public void setValue(final float value) {
        this.value = value;
    }
}
