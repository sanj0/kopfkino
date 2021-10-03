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

package de.sanj0.kopfkino.utils;

import de.sanj0.kopfkino.Directions;
import de.sanj0.kopfkino.Vector2f;

import java.util.Map;

/**
 * A small collection of directional Vectors.
 */
public class DirectionalVectors {
    private static final Map<Directions, Vector2f> PRE_BAKED = Map.of(
            new Directions(Directions.Direction.UP), new Vector2f(0, -1),
            new Directions(Directions.Direction.DOWN), new Vector2f(0, 1),
            new Directions(Directions.Direction.LEFT), new Vector2f(-1, 0),
            new Directions(Directions.Direction.RIGHT), new Vector2f(1, 0),
            new Directions(Directions.Direction.UP, Directions.Direction.RIGHT), new Vector2f(1, -1).normalise(),
            new Directions(Directions.Direction.DOWN, Directions.Direction.RIGHT), new Vector2f(1, 1).normalise(),
            new Directions(Directions.Direction.UP, Directions.Direction.LEFT), new Vector2f(-1, -1).normalise(),
            new Directions(Directions.Direction.DOWN, Directions.Direction.LEFT), new Vector2f(-1, 1).normalise()
    );

    public static Vector2f getDirectionalVector(final Directions directions) {
        final Vector2f baked = PRE_BAKED.get(directions);
        if (baked != null) return baked;
        else return Vector2f.zero();
    }
}