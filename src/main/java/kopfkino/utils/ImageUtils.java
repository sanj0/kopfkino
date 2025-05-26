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

package kopfkino.utils;

import kopfkino.Dimensions;
import kopfkino.KopfkinoGraphics;
import kopfkino.graphics.RenderConfig;
import kopfkino.graphics.Renderable;

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

    /**
     * Creates a new compatible image with the given size and calls the given
     * {@link Renderable}'s {@code render()} method with an instance of {@link
     * KopfkinoGraphics} derived from the created image's graphics.
     *
     * @param size       the size of the new image
     * @param renderable a Renderable
     *
     * @return a new BufferedImage with the given Renderable drawn onto
     * @see #createCompatibleImage(Dimensions)
     */
    public static BufferedImage renderImage(final Dimensions size, final Renderable renderable) {
        final BufferedImage img = createCompatibleImage(size);
        renderable.render(new KopfkinoGraphics(img.createGraphics()));
        return img;
    }

    public static BufferedImage resize(final BufferedImage src, final Dimensions newSize, final Object interpolation) {
        final BufferedImage dst = createCompatibleImage(newSize);
        final Graphics2D graphics2D = dst.createGraphics();

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolation);
        graphics2D.drawImage(src, 0, 0, dst.getWidth(), dst.getHeight(), null);
        graphics2D.dispose();

        return dst;
    }
}
