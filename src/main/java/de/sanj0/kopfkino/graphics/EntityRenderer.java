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
import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Vector2f;

public abstract class EntityRenderer implements Renderable {
    private final Entity subject;
    private Vector2f positionOffset;
    private Dimensions sizeOffset;
    private RenderConfig renderConfig;

    public EntityRenderer(final Entity subject, final Vector2f positionOffset,
                          final Dimensions sizeOffset, final RenderConfig renderConfig) {
        this.subject = subject;
        this.positionOffset = positionOffset;
        this.sizeOffset = sizeOffset;
        this.renderConfig = renderConfig;
    }

    public EntityRenderer(final Entity subject, final RenderConfig renderConfig) {
        this(subject, Vector2f.zero(), Dimensions.zero(), renderConfig);
    }

    public EntityRenderer(final Entity subject) {
        this(subject, RenderConfig.createDefault());
    }

    protected abstract void renderEntity(final KopfkinoGraphics graphics, final BoundingBox bb);

    @Override
    public final void render(final KopfkinoGraphics graphics) {
        graphics.applyConfig(getRenderConfig());
        renderEntity(graphics, computeBoundingBox());
    }

    public BoundingBox computeBoundingBox() {
        return new BoundingBox(subject.getPosition().plus(positionOffset), subject.getSize().plus(sizeOffset));
    }

    /**
     * Gets {@link #subject}.
     *
     * @return the value of {@link #subject}
     */
    public Entity getSubject() {
        return subject;
    }

    /**
     * Gets {@link #positionOffset}.
     *
     * @return the value of {@link #positionOffset}
     */
    public Vector2f getPositionOffset() {
        return positionOffset;
    }

    /**
     * Sets {@link #positionOffset}.
     *
     * @param positionOffset the new value of {@link #positionOffset}
     */
    public void setPositionOffset(final Vector2f positionOffset) {
        this.positionOffset = positionOffset;
    }

    /**
     * Gets {@link #sizeOffset}.
     *
     * @return the value of {@link #sizeOffset}
     */
    public Dimensions getSizeOffset() {
        return sizeOffset;
    }

    /**
     * Sets {@link #sizeOffset}.
     *
     * @param sizeOffset the new value of {@link #sizeOffset}
     */
    public void setSizeOffset(final Dimensions sizeOffset) {
        this.sizeOffset = sizeOffset;
    }

    /**
     * Gets {@link #renderConfig}.
     *
     * @return the value of {@link #renderConfig}
     */
    public RenderConfig getRenderConfig() {
        return renderConfig;
    }

    /**
     * Sets {@link #renderConfig}.
     *
     * @param renderConfig the new value of {@link #renderConfig}
     */
    public void setRenderConfig(final RenderConfig renderConfig) {
        this.renderConfig = renderConfig;
    }
}
