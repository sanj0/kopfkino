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

package kopfkino.graphics;

import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.KopfkinoGraphics;
import kopfkino.Vector2f;

import java.awt.image.BufferedImage;

public class ImageEntityRenderer extends EntityRenderer {
    private BufferedImage image;

    public ImageEntityRenderer(final Vector2f positionOffset, final Dimensions sizeOffset, final RenderConfig renderConfig, final BufferedImage image) {
        super(positionOffset, sizeOffset, renderConfig);
        this.image = image;
    }

    public ImageEntityRenderer(final RenderConfig renderConfig, final BufferedImage image) {
        super(renderConfig);
        this.image = image;
    }

    public ImageEntityRenderer(final BufferedImage image) {
        super();
        this.image = image;
    }

    @Override
    protected void renderEntity(final KopfkinoGraphics graphics, final BoundingBox bb) {
        graphics.drawImage(image, bb);
    }

    /**
     * Gets {@link #image}.
     *
     * @return the value of {@link #image}
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets {@link #image}.
     *
     * @param image the new value of {@link #image}
     */
    public void setImage(final BufferedImage image) {
        this.image = image;
    }
}
