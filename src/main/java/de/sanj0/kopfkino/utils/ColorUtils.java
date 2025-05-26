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

import java.awt.*;

/**
 * Utility methods for colors.
 */
public class ColorUtils {

    private ColorUtils() {
    }

    /**
     * Parses the given string to a color.
     * <p>supported formats:
     * <br>#html
     * <br>r, g, b(, a)
     * <p>returns null if s is not in the correct format
     *
     * @param s a string that represents a color
     *
     * @return the Color parsed from the given string or null
     */
    public static Color parseColor(final String s) {
        if (s.startsWith("#")) {
            return Color.decode(s);
        } else {
            final String[] components = s.split(",");
            final int alpha = components.length == 4 ? Integer.parseInt(components[3].trim()) : 255;
            if (components.length > 4 || components.length < 3) {
                return null;
            }
            return new Color(Integer.parseInt(components[0].trim()),
                    Integer.parseInt(components[1].trim()), Integer.parseInt(components[2].trim()), alpha);
        }
    }

    /**
     * Blends the two given colors by the given ratio.
     * <p>The ratio is between 0 and 1, inclusively.
     * <br>0 would return a copy of the first color, 1 a copy of the second.
     * <br>Everything in between mixes the two just as one would expect.
     *
     * @param color1 the first color
     * @param color2 the second color
     * @param ratio  the blend ratio between 0 and 1, inclusively.
     *
     * @return a blend between the two colors by the given ratio
     */
    public static Color blend(final Color color1, final Color color2, float ratio) {
        if (ratio > 1 || ratio < 0) {
            throw new IllegalArgumentException("blend ratio must be => 0 and =< 1\n\t is " + ratio);
        }

        final float invRatio = 1 - ratio;
        return new Color((int) (color1.getRed() * invRatio + ratio * color2.getRed()),
                (int) (color1.getGreen() * invRatio + ratio * color2.getGreen()),
                (int) (color1.getBlue() * invRatio + ratio * color2.getBlue()),
                (int) (color1.getAlpha() * invRatio + ratio * color2.getAlpha()));
    }

    /**
     * Returns the given color with the given alpha value. The value goes from 0
     * to 255, 0 meaning complete transparency, 255 meaning full visibility.
     *
     * @param color the {@link Color} to return with adjusted alpha
     * @param alpha the new alpha value for the color
     *
     * @return the given color with the given alpha value
     */
    public static Color withAlpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    /**
     * Returns the given color with the given alpha value. The value goes from
     * 0f to 1f, 0f meaning complete transparency, 1f meaning full visibility.
     *
     * @param color the {@link Color} to return with adjusted alpha
     * @param alpha the new alpha value for the color
     *
     * @return the given color with the given alpha value
     */
    public static Color withAlpha(final Color color, final float alpha) {
        return withAlpha(color, (int) (alpha * 255f));
    }
}
