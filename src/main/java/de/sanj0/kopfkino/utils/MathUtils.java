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

package de.sanj0.kopfkino.utils;

import java.util.List;
import java.security.SecureRandom;

/**
 * Static math utility functions.
 */
public class MathUtils {

    /**
     * The <code>Random</code> instance used for all utils that need one.
     */
    private static final SecureRandom RNG = new SecureRandom();

    private MathUtils() {
    }

    public static<T> T pickRandom(List<T> xs) {
        return xs.get(randomInt(0, xs.size() - 1));
    }

    /**
     * Returns the square of the given number using {@link Math#pow(double,
     * double)}.
     *
     * @param d a number
     *
     * @return the square of the given number
     */
    public static double square(final double d) {
        return Math.pow(d, 2);
    }

    /**
     * Returns the given value inside the given range. So for the given value
     * {@code v} and the range {@code min} to {@code max}, this method would
     * return <p> {@code v} if it lies between {@code min} ... {@code max}
     * <br> {@code min} if {@code v} is smaller
     * than {@code min} <br> and {@code max} if {@code v} is greater than {@code
     * max}
     *
     * @param value a value
     * @param min   the smaller end of the range
     * @param max   the greater end of the range
     *
     * @return the given value clamped to the given range
     */
    public static float clamp(final float value, final float min, final float max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Generates a random int between the given two.
     *
     * @param min the smallest number possible
     * @param max the biggest number possible
     *
     * @return a new random int between the given bounds
     */
    public static int randomInt(final float min, final float max) {
        final int r = RNG.nextInt((Math.round(max) - Math.round(min)) + 1);
        return Math.round(r + min);
    }


}
