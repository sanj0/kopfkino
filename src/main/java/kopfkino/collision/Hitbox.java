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

package kopfkino.collision;

import kopfkino.BoundingBox;
import kopfkino.Entity;

/**
 * A hitbox it either a circle or a {@link BoundingBox}.
 * <p>It is used to determine whether two {@link Entity}s
 * collide or not.
 */
public interface Hitbox {
    /**
     * Returns the bounding box containing the hitbox.
     *
     * @return the bounding box containing the hitbox
     */
    BoundingBox getBoundingBox();
}
