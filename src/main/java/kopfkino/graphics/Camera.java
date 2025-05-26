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

import kopfkino.Dimensions;
import kopfkino.Vector2f;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * A camera is a movable, scalable and otherwise transformable projector that
 * renders a {@link Renderable} onto a {@link BufferedImage}.
 */
public interface Camera {
    /**
     * Renders the given Renderable onto a {@link BufferedImage} and returns
     * it.
     * <p>
     * The image returned by this method will always have the exact {@link
     * Dimensions} of {@link #getSize()}.
     *
     * @param subject the Renderable to render
     *
     * @return a visual representation of the given Renderable as this camera
     * sees it
     */
    BufferedImage render(Renderable subject);

    /**
     * Returns the position of this camera as set and described by {@link
     * #setPosition(Vector2f)}.
     *
     * @return the current position of this camera
     */
    Vector2f getPosition();

    /**
     * Sets the position of this camera.
     * <p>
     * The position of this camera is relative to the normal user space and
     * directly influences the output of this camera produced with {@link
     * #render(Renderable)}.
     *
     * @param position the new position of this camera
     */
    void setPosition(Vector2f position);

    /**
     * Returns the size of this camera as set and described in {@link
     * #setSize(Dimensions)}.
     *
     * @return the current size of this camera
     */
    Dimensions getSize();

    /**
     * Sets the size of this camera.
     * <p>
     * The size of the camera directly determines the size of its output
     * produced with {@link #render(Renderable)}.
     *
     * @param size the new size of this camera
     */
    void setSize(Dimensions size);

    /**
     * Returns the current resolution of this camera as set and described in
     * {@link #setResolution(Dimensions)}.
     *
     * @return the current resolution of this camera
     */
    Dimensions getResolution();

    /**
     * Sets the resolution of this camera.
     * <p>
     * The resolution describes with how many pixel the camera can render. <br>
     * This does not affect the size of the image produced with {@link
     * #render(Renderable)}.
     *
     * @param resolution the new resolution of this camera
     */
    void setResolution(Dimensions resolution);

    /**
     * Returns the scale as set and described in {@link #setScale(float)}.
     *
     * @return the current scale of this camera
     */
    float getScale();

    /**
     * Sets the current scale of the camera.
     * <p>
     * A scale of <code>1f</code> is default and does not change the image, a
     * scale of
     * <code>0f</code> would make the camera see
     * nothing and a scale of <code>2f</code> would make everything twice as
     * big.
     * <p>
     * This scale does not affect the size of the image produced by {@link
     * #render(Renderable)}.
     *
     * @param scale the new scale
     */
    void setScale(float scale);

    /**
     * Returns an instance of {@link AffineTransform} that directly represents
     * the values of this camera in <br> {@link #getScale() scale} <br> {@link
     * #getPosition() position}
     *
     * @return a {@link AffineTransform} that directly represents the state of
     * this camera
     */
    default AffineTransform getAffineTransform() {
        final AffineTransform transform = new AffineTransform();
        transform.translate(getResolution().getWidth() - getSize().getWidth(), getResolution().getHeight() - getSize().getHeight());
        transform.scale(getScale(), getScale());
        transform.translate(-getPosition().getX(), -getPosition().getY());

        return transform;
    }

    /**
     * Returns a transformed version of the given vector using this camera's
     * current transform.
     *
     * @param absolutePosition a position
     *
     * @return the given vector as transformed by this camera
     */
    default Vector2f transformPoint(final Vector2f absolutePosition) {
        final AffineTransform transform = getAffineTransform();
        try {
            transform.invert();
        } catch (final NoninvertibleTransformException e) {
            e.printStackTrace();
        }
        final Point2D srcPoint = new Point2D.Float(absolutePosition.getX(), absolutePosition.getY());
        final Point2D dstPoint = new Point2D.Float();
        transform.transform(srcPoint, dstPoint);

        return new Vector2f((float) dstPoint.getX(), (float) dstPoint.getY());
    }
}
