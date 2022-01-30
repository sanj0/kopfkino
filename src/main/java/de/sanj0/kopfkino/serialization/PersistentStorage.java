/*
 *    Copyright 2022 Malte Dostal
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

package de.sanj0.kopfkino.serialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Persistent storage provided via serialization.
 * <p>The data is stored in a (per default) hidden dot-file
 * on the file system as clear text. Both reading and writing using {@link
 * SerializationManager} uses {@link Object#toString()} except for {@link
 * java.util.List} instances--with them, toString is used on every Entry
 * separately.
 */
public final class PersistentStorage {

    /**
     * Stores the persistent data.
     * <p>The data is loaded at startup and saved when the game exits normally.
     */
    protected static final Map<String, Object> DATA = new ConcurrentHashMap<>();

    /**
     * Fields that will be stored upon quitting the game normally.
     */
    protected static final List<PersistentField<?>> PERSISTENT_FIELDS = new ArrayList<>();

    private PersistentStorage() {
    }

    /**
     * Retrieves the value associated with the given key from the persistent
     * storage or returns the given default value if none is present.
     *
     * @param key          the key of the desired persistent value
     * @param defaultValue the default value to return if no value is present in
     *                     the storage
     * @param <T>          the type of the value to return--casting is done
     *                     either way
     *
     * @return the value associated with the given key from the storage or, if
     * none is present, the given default value
     * @see Map#getOrDefault(Object, Object)
     */
    public static <T> T orDefault(final String key, final T defaultValue) {
        return (T) DATA.getOrDefault(key, defaultValue);
    }

    /**
     * Puts the given key-value pair into the storage.
     * <p>If there already was a value associated with the key, this method
     * return the previous value and null if otherwise.
     *
     * @param key the key of the value to be put into storage
     * @param val the value to be put into storage
     * @param <T> the type of the given value
     *
     * @return the value previously associated with the given key and null if
     * there is none
     * @see Map#put(Object, Object)
     */
    public static <T> Object put(final String key, final T val) {
        return DATA.put(key, val);
    }
}
