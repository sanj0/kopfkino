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

import java.util.Objects;

/**
 * Super class of {@link Vector2f} to encapsulate math functionality.
 * <p>All operations are supported in two ways:
 * <ol>
 *     <li>
 *         functions that change the state of <code>this</code>
 *         <br>for example {@link #add(BiFloatTuple)}, {@link #multiply(BiFloatTuple)}
 *     </li>
 *     <li>
 *         functions that create a new instance and don't change the state of <code>this</code>
 *         <br>for example {@link #plus(BiFloatTuple)}, {@link #divBy(BiFloatTuple)}
 *     </li>
 * </ol>
 * Where a speaking name (e.g. {@link #times(BiFloatTuple)}) for the non-state-change
 * function isn't possible, the past version of the normal function is used (e.g. {@link #absed()})
 */
public abstract class BiFloatTuple<T> {
    protected float a;
    protected float b;

    protected BiFloatTuple(final float a, final float b) {
        this.a = a;
        this.b = b;
    }

    protected abstract T createInstance(final float a, final float b);

    public T set(final BiFloatTuple v) {
        a = v.a;
        b = v.b;
        return (T) this;
    }

    /**
     * Multiplies b.x with this.x and b.y with this.y and returns {@code this}.
     *
     * @param o another tuple
     *
     * @return {@code this}
     */
    public T multiply(final BiFloatTuple o) {
        a *= o.a;
        b *= o.b;
        return (T) this;
    }

    /**
     * Returns a new tuple with a and b being the product of the xs and ys of
     * this and the given tuple, respectively.
     * <p>Doesn't change this, nor the given tuple.
     *
     * @param o another tuple
     *
     * @return a BiFloatTuple whose components are the product of the respective
     * components of {@link this} and {@code b}.
     */
    public T times(final BiFloatTuple o) {
        return createInstance(a * o.a, b * o.b);
    }

    /**
     * Divides this.x by b.x and this.y by b.y and returns {@code this}.
     *
     * @param o another tuple
     *
     * @return {@code this}
     */
    public T divide(final BiFloatTuple o) {
        a /= o.a;
        b /= o.b;
        return (T) this;
    }

    /**
     * Returns a new tuple with a and b being the quotient of the xs and ys of
     * this and the given tuple, respectively.
     * <p>Doesn't change this, nor the given tuple.
     * <p>
     * param o another tuple
     *
     * @return a BiFloatTuple whose components are the quotient of the
     * respective components of {@link this} and {@code b}.
     */
    public T divBy(final BiFloatTuple o) {
        return createInstance(a / o.a, b / o.b);
    }

    /**
     * Adds b.x to this.x and b.y to this.y and returns {@code this}.
     * <p>
     * param o another tuple
     *
     * @return {@code this}
     */
    public T add(final BiFloatTuple o) {
        a += o.a;
        b += o.b;
        return (T) this;
    }

    /**
     * Returns a new tuple with a and b being the sum of the xs and ys of this
     * and the given tuple, respectively.
     * <p>Doesn't change this, nor the given tuple.
     * <p>
     * param o another tuple
     *
     * @return a BiFloatTuple whose components are the sum of the respective
     * components of {@link this} and {@code b}.
     */
    public T plus(final BiFloatTuple o) {
        return createInstance(a + o.a, b + o.b);
    }

    /**
     * Subtracts b.x from this.x and b.y from this.y and returns {@code this}.
     * <p>
     * param o another tuple
     *
     * @return {@code this}
     */
    public T subtract(final BiFloatTuple o) {
        a -= o.a;
        b -= o.b;
        return (T) this;
    }

    /**
     * Returns a new tuple with a and b being the difference between the xs and
     * ys of this and the given tuple, respectively.
     * <p>Doesn't change this, nor the given tuple.
     * <p>
     * param o another tuple
     *
     * @return a BiFloatTuple whose components are the difference between the
     * respective components of {@link this} and {@code b}.
     */
    public T minus(final BiFloatTuple o) {
        return createInstance(a - o.a, b - o.b);
    }

    /**
     * Absolutes the values of this.x and this.y and return {@code this}.
     *
     * @return {@code this}
     */
    public T abs() {
        a = Math.abs(a);
        b = Math.abs(b);
        return (T) this;
    }

    /**
     * Creates a new tuple with the absolute values of this.x and this.y as x
     * and y, respectively.
     * <p>Doesn't change {@code this}
     *
     * @return a new tuple which is the absolute version of {@link this}
     */
    public T absed() {
        return createInstance(Math.abs(a), Math.abs(b));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiFloatTuple<?> that = (BiFloatTuple<?>) o;
        return Float.compare(that.a, a) == 0 && Float.compare(that.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
