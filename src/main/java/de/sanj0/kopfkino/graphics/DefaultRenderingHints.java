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

package de.sanj0.kopfkino.graphics;

import java.awt.*;
import java.util.Map;

import static java.awt.RenderingHints.*;

/**
 * Used to configure the graphics of the game by providing {@link #hints default
 * rendering hints} that are being used by the default config of {@link
 * RenderConfig}.
 * <p>
 * {@code RenderConfig.builder().build().getRenderingHints()} will return a copy
 * of the {@link #hints default rendering hints}.
 */
public class DefaultRenderingHints {

    /**
     * A preset of all important keys set to focus on quality, compromising on
     * performance.
     */
    public static final RenderingHints PRESET_HQ = new RenderingHints(
            Map.of(KEY_RENDERING, VALUE_RENDER_QUALITY, KEY_ANTIALIASING,
                    VALUE_ANTIALIAS_ON, KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_QUALITY,
                    KEY_DITHERING, VALUE_DITHER_ENABLE, KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC,
                    KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON));
    /**
     * A preset of all important keys set to focus on performance, compromising
     * on quality.
     */
    public static final RenderingHints PRESET_LQ = new RenderingHints(
            Map.of(KEY_RENDERING, VALUE_RENDER_SPEED, KEY_ANTIALIASING,
                    VALUE_ANTIALIAS_OFF, KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_SPEED,
                    KEY_DITHERING, VALUE_DITHER_DISABLE, KEY_INTERPOLATION, VALUE_INTERPOLATION_BILINEAR,
                    KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_OFF));
    /**
     * The default rendering hints used by {@link RenderConfig}.
     * <p>A copy may be obtained using {@link #getHints()}
     * <br>May be set using {@link #set(RenderingHints)}
     * <br>May be edited using {@link #put(Key, Object)}
     */
    private static RenderingHints hints = new RenderingHints(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

    private DefaultRenderingHints() {
    }

    /**
     * Returns a copy of the {@link #hints default hints}.
     *
     * @return a copy of the {@link #hints default hints}
     */
    public static RenderingHints getHints() {
        return (RenderingHints) hints.clone();
    }

    /**
     * Puts the given key/value pair into the {@link #hints default hints}.
     *
     * @param key   a hint key
     * @param value the hint's value
     */
    public static void put(final RenderingHints.Key key, final Object value) {
        hints.put(key, value);
    }

    /**
     * Sets the {@link #hints default hints}.
     *
     * @param hints new {@link #hints default hints}
     */
    public static void set(final RenderingHints hints) {
        DefaultRenderingHints.hints = hints;
    }
}
