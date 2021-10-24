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

package de.sanj0.kopfkino.collision;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Vector2f;

import java.util.function.Supplier;

public class CircleHitbox implements Hitbox {
    private Supplier<Vector2f> centreSupplier;
    private Supplier<Float> radiusSupplier;

    public CircleHitbox(final Supplier<Vector2f> centreSupplier, final Supplier<Float> radiusSupplier) {
        this.centreSupplier = centreSupplier;
        this.radiusSupplier = radiusSupplier;
    }

    public CircleHitbox(final Supplier<Vector2f> centreSupplier, final float radius) {
        this(centreSupplier, () -> radius);
    }

    @Override
    public BoundingBox getBoundingBox() {
        final float radius = radiusSupplier.get();
        return new BoundingBox(centreSupplier.get().minus(Vector2f.num(radius)), Dimensions.num(radius * 2f));
    }

    /**
     * Gets {@link #centreSupplier}.
     *
     * @return the value of {@link #centreSupplier}
     */
    public Supplier<Vector2f> getCentreSupplier() {
        return centreSupplier;
    }

    /**
     * Sets {@link #centreSupplier}.
     *
     * @param centreSupplier the new value of {@link #centreSupplier}
     */
    public void setCentreSupplier(final Supplier<Vector2f> centreSupplier) {
        this.centreSupplier = centreSupplier;
    }

    /**
     * Gets {@link #radiusSupplier}.
     *
     * @return the value of {@link #radiusSupplier}
     */
    public Supplier<Float> getRadiusSupplier() {
        return radiusSupplier;
    }

    /**
     * Sets {@link #radiusSupplier}.
     *
     * @param radiusSupplier the new value of {@link #radiusSupplier}
     */
    public void setRadiusSupplier(final Supplier<Float> radiusSupplier) {
        this.radiusSupplier = radiusSupplier;
    }
}
