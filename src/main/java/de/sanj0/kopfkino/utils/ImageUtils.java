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

import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;
import de.sanj0.kopfkino.graphics.RenderConfig;
import de.sanj0.kopfkino.graphics.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {
    private static final GraphicsConfiguration GC =
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    private ImageUtils() {
    }

    public static BufferedImage createCompatibleImage(final Dimensions size) {
        return GC.createCompatibleImage(Math.round(size.getWidth()), Math.round(size.getHeight()), Transparency.TRANSLUCENT);
    }

    public static void renderToImage(final BufferedImage image, final Renderable renderable,
                                     final RenderConfig renderConfig) {
        final KopfkinoGraphics graphics = new KopfkinoGraphics(image.createGraphics(), renderConfig);
        renderable.render(graphics);
        graphics.dispose();
    }
}
