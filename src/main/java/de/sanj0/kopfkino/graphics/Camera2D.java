/*
 *    Copyright 2021 Malte Dostal
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
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The default {@link Camera} implementation that allows for translation and
 * scaling of the output image.
 */
public class Camera2D implements Camera {
    private final BoundingBox boundingBox;
    private Dimensions resolution;
    private float scale;
    private Object finalInterpolation = RenderingHints.VALUE_INTERPOLATION_BILINEAR;

    public Camera2D(final BoundingBox boundingBox, final Dimensions resolution, final float scale) {
        this.boundingBox = boundingBox;
        this.resolution = resolution;
        this.scale = scale;
    }

    @Override
    public BufferedImage render(final Renderable subject) {
        // scaled to the actual size later on
        final BufferedImage image = ImageUtils.createCompatibleImage(resolution);
        final KopfkinoGraphics graphics = new KopfkinoGraphics(image.createGraphics());
        graphics.setClip(new BoundingBox(0, 0, resolution));
        graphics.getGraphics2D().setTransform(getAffineTransform());
        subject.render(graphics);
        return ImageUtils.resize(image, resolution, finalInterpolation);
    }

    @Override
    public Vector2f getPosition() {
        return boundingBox.getPosition();
    }

    @Override
    public void setPosition(final Vector2f position) {
        boundingBox.setPosition(position);
    }

    @Override
    public Dimensions getSize() {
        return boundingBox.getSize();
    }

    @Override
    public void setSize(final Dimensions size) {
        boundingBox.setSize(size);
    }

    @Override
    public Dimensions getResolution() {
        return resolution;
    }

    @Override
    public void setResolution(final Dimensions resolution) {
        this.resolution = resolution;
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(final float scale) {
        this.scale = scale;
    }

    /**
     * Gets {@link #finalInterpolation}.
     *
     * @return the value of {@link #finalInterpolation}
     */
    public Object getFinalInterpolation() {
        return finalInterpolation;
    }

    /**
     * Sets {@link #finalInterpolation}.
     *
     * @param finalInterpolation the new value of {@link #finalInterpolation}
     */
    public void setFinalInterpolation(final Object finalInterpolation) {
        this.finalInterpolation = finalInterpolation;
    }
}
