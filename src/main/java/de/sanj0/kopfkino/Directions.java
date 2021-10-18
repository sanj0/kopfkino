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

package de.sanj0.kopfkino;

import de.sanj0.kopfkino.utils.DirectionalVectors;

import java.util.*;

public class Directions {
    private final Set<Direction> directionSet;

    public Directions(final Direction... directions) {
        this.directionSet = EnumSet.noneOf(Direction.class);
        directionSet.addAll(Arrays.asList(directions));
    }

    public Vector2f toVector() {
        return DirectionalVectors.getDirectionalVector(this);
    }

    public boolean removeAll(final Collection<Direction> c) {
        return directionSet.removeAll(c);
    }

    public int size() {
        return directionSet.size();
    }

    public boolean isEmpty() {
        return directionSet.isEmpty();
    }

    public boolean contains(final Direction o) {
        return directionSet.contains(o);
    }

    public boolean add(final Direction direction) {
        return directionSet.add(direction);
    }

    public boolean remove(final Direction o) {
        return directionSet.remove(o);
    }

    public boolean addAll(final Collection<? extends Direction> c) {
        return directionSet.addAll(c);
    }

    /**
     * Gets {@link #directionSet}.
     *
     * @return the value of {@link #directionSet}
     */
    public Set<Direction> getDirectionSet() {
        return directionSet;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directions that = (Directions) o;
        return directionSet.containsAll(that.directionSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directionSet);
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
