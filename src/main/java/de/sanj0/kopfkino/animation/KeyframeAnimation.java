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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Animates transitions between specified keyframes.
 */
public class KeyframeAnimation {
    private TransitionFunction transition;
    private List<Keyframe> frames;
    private int currentTime;
    private int duration;
    private int frameIndex = 0;
    private boolean loop;

    public KeyframeAnimation(final TransitionFunction transition, final List<Keyframe> frames) {
        this.transition = transition;
        this.frames = frames;
        computeDuration();
    }

    public KeyframeAnimation(final TransitionFunction transition) {
        this.transition = transition;
        this.frames = new ArrayList<>();
    }

    private void computeDuration() {
        duration = frames.isEmpty() ? 0 : frames.get(frames.size() - 1).getTime();
    }

    public float nextValue() {
        final Keyframe frame = frames.get(frameIndex);
        final Keyframe prevFrame = frameIndex > 0 ? frames.get(frameIndex - 1) : null;
        final float prevValue = prevFrame == null ? 0 : prevFrame.getValue();
        final float prevTimecode = prevFrame == null ? 0 : prevFrame.getTime();
        if (currentTime >= frame.getTime()) {
            if (frameIndex == frames.size() - 1) {
                if (loop) {
                    reset();
                    return nextValue();
                } else {
                    return frames.get(frameIndex).getValue();
                }
            } else {
                frameIndex++;
                return nextValue();
            }
        } else {
            currentTime++;
            return prevValue + (frame.getValue() - prevValue) * transition.apply((currentTime - 1 - prevTimecode) / (frame.getTime() - prevTimecode));
        }
    }

    public boolean ended() {
        return currentTime >= duration;
    }

    /**
     * Sorts the list of keyframes by their time so that
     * they are in order in respect to chronology.
     * <p>When keyframes were added in order (first to last) using the
     * append methods, a call to this method will be semantically redundant.
     */
    public void sort() {
        frames.sort(Comparator.comparingInt(Keyframe::getTime));
    }

    public void reset() {
        currentTime = 0;
        frameIndex = 0;
    }

    public void appendFrame(final Keyframe frame) {
        frames.add(frame);
        duration = frame.getTime();
    }

    public void appendFrame(final int t, final float v) {
        appendFrame(new Keyframe(t, v));
    }

    public void removeFrame(final Keyframe frame) {
        frames.remove(frame);
        computeDuration();
    }

    public void removeFrame(final int timecode) {
        frames.removeIf(f -> f.getTime() == timecode);
        computeDuration();
    }

    public void removeFrame(final float value) {
        frames.removeIf(f -> f.getValue() == value);
        computeDuration();
    }

    /**
     * Gets {@link #transition}.
     *
     * @return the value of {@link #transition}
     */
    public TransitionFunction getTransition() {
        return transition;
    }

    /**
     * Sets {@link #transition}.
     *
     * @param transition the new value of {@link #transition}
     */
    public void setTransition(final TransitionFunction transition) {
        this.transition = transition;
    }

    /**
     * Gets {@link #currentTime}.
     *
     * @return the value of {@link #currentTime}
     */
    public int getCurrentTime() {
        return currentTime;
    }

    /**
     * Sets {@link #currentTime}.
     *
     * @param currentTime the new value of {@link #currentTime}
     */
    public void setCurrentTime(final int currentTime) {
        if (currentTime > duration && loop) {
            this.currentTime = currentTime % duration;
            return;
        }
        this.currentTime = currentTime;
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

    /**
     * Gets {@link #duration}.
     *
     * @return the value of {@link #duration}
     */
    public int getDuration() {
        return duration;
    }
}
