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

package kopfkino.graphics;

import kopfkino.BoundingBox;
import kopfkino.Entity;
import kopfkino.KopfkinoGraphics;

/**
 * An {@link EntityRenderer} that does absolutely nothing.
 * <p>Useful for invisible {@link Entity}s or
 * for when only the methods {@link Entity#renderAfter(KopfkinoGraphics)}
 * and / or {@link Entity#renderBefore(KopfkinoGraphics)} are needed
 * for rendering the Entity.
 */
public class EmptyEntityRenderer extends EntityRenderer {
    @Override
    protected void renderEntity(final KopfkinoGraphics graphics, final BoundingBox bb) {
        // empty, as promised
    }
}
