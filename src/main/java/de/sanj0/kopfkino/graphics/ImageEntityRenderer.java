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

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Vector2f;

import java.awt.image.BufferedImage;

public class ImageEntityRenderer extends EntityRenderer {
    private BufferedImage image;

    public ImageEntityRenderer(final Entity subject, final Vector2f positionOffset, final Dimensions sizeOffset, final RenderConfig renderConfig, final BufferedImage image) {
        super(subject, positionOffset, sizeOffset, renderConfig);
        this.image = image;
    }

    public ImageEntityRenderer(final Entity subject, final RenderConfig renderConfig, final BufferedImage image) {
        super(subject, renderConfig);
        this.image = image;
    }

    public ImageEntityRenderer(final Entity subject, final BufferedImage image) {
        super(subject);
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
