/*
 *    Copyright 2022 ***REMOVED*** ***REMOVED***
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

package de.sanj0.kopfkino.ecs;

import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.EntityFunctionality;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;

/**
 * A component for an {@link de.sanj0.kopfkino.Entity}.
 */
public abstract class EntityComponent implements EntityFunctionality {
    protected Entity subject;

    protected EntityComponent(final Entity subject) {
        this.subject = subject;
    }

    @Override
    public final void updateBefore() {
        // not supported for Components due to performance
    }

    @Override
    public final void renderBefore(final KopfkinoGraphics graphics) {
        // not supported for Component due to performance
    }

    /**
     * Gets {@link #subject}.
     *
     * @return the value of {@link #subject}
     */
    public Entity getSubject() {
        return subject;
    }
}
