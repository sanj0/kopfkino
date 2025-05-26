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

package kopfkino.utils;

import kopfkino.Directions;
import kopfkino.Vector2f;

import java.util.HashMap;
import java.util.Map;

/**
 * Converts {@link Directions} into a vector.
 */
public class DirectionalVectors {

    public static final Vector2f UP = new Vector2f(0, -1);
    public static final Vector2f DOWN = new Vector2f(0, 1);
    public static final Vector2f RIGHT = new Vector2f(1, 0);
    public static final Vector2f LEFT = new Vector2f(-1, 0);
    private static final Map<Directions, Vector2f> PRE_BAKED = new HashMap<>(8) {{
        put(new Directions(Directions.Direction.UP), UP);
        put(new Directions(Directions.Direction.DOWN), DOWN);
        put(new Directions(Directions.Direction.LEFT), LEFT);
        put(new Directions(Directions.Direction.RIGHT), RIGHT);
        put(new Directions(Directions.Direction.UP, Directions.Direction.RIGHT), new Vector2f(1, -1).normalise());
        put(new Directions(Directions.Direction.DOWN, Directions.Direction.RIGHT), new Vector2f(1, 1).normalise());
        put(new Directions(Directions.Direction.UP, Directions.Direction.LEFT), new Vector2f(-1, -1).normalise());
        put(new Directions(Directions.Direction.DOWN, Directions.Direction.LEFT), new Vector2f(-1, 1).normalise());
    }};

    private DirectionalVectors() {
    }

    public static Vector2f getDirectionalVector(final Directions directions) {
        final Vector2f baked = PRE_BAKED.get(directions);
        if (baked != null) return baked;
        else return Vector2f.zero();
    }
}
