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

package kopfkino.serialization;

import kopfkino.BoundingBox;
import kopfkino.Dimensions;
import kopfkino.Vector2f;

import java.util.function.Function;

/**
 * A field that is automatically stored persistently using {@link
 * PersistentStorage} and optionally loaded from there too.
 * <p>Especially useful for {@link Vector2f}, {@link
 * Dimensions}
 * and {@link BoundingBox} as the reading and writing is done
 * out of the box.
 * <p>Other automatic types include:
 * <br>String, int, float, boolean
 *
 * @param <T> the value type
 */
public class PersistentField<T> {
    private String key;
    private T val = null;
    private final Function<T, String> writeFunction;

    protected PersistentField(final String key, final T val, final Function<T, String> writeFunction) {
        this.key = key;
        this.val = val;
        this.writeFunction = writeFunction;
        PersistentStorage.PERSISTENT_FIELDS.add(this);
    }

    public static PersistentField<Float> loadFloat(final String key, final float defaultValue) {
        return new PersistentField<>(key, Float.parseFloat(PersistentStorage.orDefault(key, String.valueOf(defaultValue))),
                String::valueOf);
    }

    public static PersistentField<String> loadString(final String key, final String defaultValue) {
        return new PersistentField<>(key, PersistentStorage.orDefault(key, defaultValue),
                Function.identity());
    }

    public static PersistentField<Boolean> loadBool(final String key, final boolean defaultValue) {
        return new PersistentField<>(key, Boolean.valueOf(PersistentStorage.orDefault(key, String.valueOf(defaultValue))),
                String::valueOf);
    }

    public static PersistentField<Integer> loadInt(final String key, final int defaultValue) {
        return new PersistentField<>(key, Integer.parseInt(PersistentStorage.orDefault(key, String.valueOf(defaultValue))),
                String::valueOf);
    }

    public static PersistentField<Vector2f> loadVec2f(final String key, final Vector2f defaultValue) {
        return new PersistentField<>(key, Vector2f.parseVector2f(PersistentStorage.orDefault(key, Vector2f.writeVector2f(defaultValue))),
                Vector2f::writeVector2f);
    }

    public static PersistentField<Dimensions> loadDimensions(final String key, final Vector2f defaultValue) {
        return new PersistentField<>(key, Dimensions.parseDimensions(PersistentStorage.orDefault(key, Vector2f.writeVector2f(defaultValue))),
                Dimensions::writeDimensions);
    }

    /**
     * Writes {@link #val#toString()} or a text representation of val, as
     * presented by {@link #writeFunction}
     *
     * @return the text representation of {@link #val}
     */
    protected String write() {
        return writeFunction.apply(val);
    }

    /**
     * Gets {@link #key}.
     *
     * @return the value of {@link #key}
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets {@link #key}.
     *
     * @param key the new value of {@link #key}
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Gets {@link #val}.
     *
     * @return the value of {@link #val}
     */
    public T get() {
        return val;
    }

    /**
     * Sets {@link #val}.
     *
     * @param val the new value of {@link #val}
     */
    public void set(final T val) {
        this.val = val;
    }
}
