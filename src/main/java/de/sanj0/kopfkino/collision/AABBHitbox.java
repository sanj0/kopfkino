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

package de.sanj0.kopfkino.collision;

import de.sanj0.kopfkino.BoundingBox;

import java.util.function.Supplier;

public class AABBHitbox implements Hitbox {
    private Supplier<BoundingBox> boundingBoxSupplier;

    public AABBHitbox(final Supplier<BoundingBox> boundingBoxSupplier) {
        this.boundingBoxSupplier = boundingBoxSupplier;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(boundingBoxSupplier.get());
    }

    /**
     * Gets {@link #boundingBoxSupplier}.
     *
     * @return the value of {@link #boundingBoxSupplier}
     */
    public Supplier<BoundingBox> getBoundingBoxSupplier() {
        return boundingBoxSupplier;
    }

    /**
     * Sets {@link #boundingBoxSupplier}.
     *
     * @param boundingBoxSupplier the new value of {@link #boundingBoxSupplier}
     */
    public void setBoundingBoxSupplier(final Supplier<BoundingBox> boundingBoxSupplier) {
        this.boundingBoxSupplier = boundingBoxSupplier;
    }
}
