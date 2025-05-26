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
import kopfkino.utils.ImageUtils;

import java.awt.image.BufferedImage;

/**
 * A bake-able primitive renderer.
 */
public abstract class PrimitiveEntityRenderer extends EntityRenderer {
    private boolean fill = true;
    private BufferedImage bakedImage = null;

    public PrimitiveEntityRenderer(final Vector2f positionOffset, final Dimensions sizeOffset, final RenderConfig renderConfig) {
        super(positionOffset, sizeOffset, renderConfig);
    }

    public PrimitiveEntityRenderer(final RenderConfig renderConfig) {
        super(renderConfig);
    }

    public PrimitiveEntityRenderer() {
        super();
    }

    protected abstract void renderShape(final KopfkinoGraphics graphics, final BoundingBox bb, final boolean fill);

    @Override
    protected final void renderEntity(final KopfkinoGraphics graphics, final BoundingBox bb) {
        if (bakedImage == null) {
            renderShape(graphics, bb, fill);
        } else {
            graphics.drawImage(bakedImage, bb);
        }
    }

    public void bake() {
        final Dimensions size = getSubject().getSize().plus(getSizeOffset());
        bakedImage = ImageUtils.createCompatibleImage(size);
        ImageUtils.renderToImage(bakedImage, g -> renderShape(g, new BoundingBox(Vector2f.zero(), size), fill), getRenderConfig());
    }

    public void unBake() {
        bakedImage = null;
    }

    /**
     * Gets {@link #fill}.
     *
     * @return the value of {@link #fill}
     */
    public boolean isFill() {
        return fill;
    }

    /**
     * Sets {@link #fill}.
     *
     * @param fill the new value of {@link #fill}
     */
    public void setFill(final boolean fill) {
        this.fill = fill;
    }
}
