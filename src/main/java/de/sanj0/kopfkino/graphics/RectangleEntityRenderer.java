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

/**
 * Renders a rectangle.
 * <p>Rounded corners are controlled using {@link #arcWidth} and {@link
 * #arcHeight}; default value is 0 for both.
 */
public class RectangleEntityRenderer extends PrimitiveEntityRenderer {

    private float arcWidth = 0;
    private float arcHeight = 0;

    public RectangleEntityRenderer(final Entity subject, final Vector2f positionOffset, final Dimensions sizeOffset, final RenderConfig renderConfig) {
        super(subject, positionOffset, sizeOffset, renderConfig);
    }

    public RectangleEntityRenderer(final Entity subject, final RenderConfig renderConfig) {
        super(subject, renderConfig);
    }

    public RectangleEntityRenderer(final Entity subject) {
        super(subject);
    }

    public RectangleEntityRenderer withArc(final float arcWidth, final float arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        return this;
    }

    @Override
    protected void renderShape(final KopfkinoGraphics graphics, final BoundingBox bb, final boolean fill) {
        if (fill) {
            graphics.drawRoundRect(bb, arcWidth, arcHeight);
        } else {
            graphics.outlineRoundRect(bb, arcWidth, arcHeight);
        }
    }

    /**
     * Gets {@link #arcWidth}.
     *
     * @return the value of {@link #arcWidth}
     */
    public float getArcWidth() {
        return arcWidth;
    }

    /**
     * Sets {@link #arcWidth}.
     *
     * @param arcWidth the new value of {@link #arcWidth}
     */
    public void setArcWidth(final float arcWidth) {
        this.arcWidth = arcWidth;
    }

    /**
     * Gets {@link #arcHeight}.
     *
     * @return the value of {@link #arcHeight}
     */
    public float getArcHeight() {
        return arcHeight;
    }

    /**
     * Sets {@link #arcHeight}.
     *
     * @param arcHeight the new value of {@link #arcHeight}
     */
    public void setArcHeight(final float arcHeight) {
        this.arcHeight = arcHeight;
    }
}
