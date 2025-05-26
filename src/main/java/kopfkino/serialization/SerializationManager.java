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

package kopfkino.serialization;

import de.sanj0.sanjo.SJClass;
import de.sanj0.sanjo.SJValue;
import de.sanj0.sanjo.SanjoFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map.Entry;

public class SerializationManager {

    private SerializationManager() {
    }

    /**
     * Writes the contents of {@link PersistentStorage#DATA} into the given file
     * for it to later be {@link #read(String)} again.
     *
     * @param path path to the file to write to
     */
    public static void write(final String path) throws IOException {
        final SJClass root = SJClass.defaultClass();
        for (final Entry<String, Object> entry : PersistentStorage.DATA.entrySet()) {
            root.addValue(entry.getKey(), entry.getValue());
        }

        // writes the PersistentFields and in the process, overwrites previous
        // , most likely wrong, writes from the DATA map (because the initial read
        // value of each field still exists within that storage
        for (final PersistentField<?> field : PersistentStorage.PERSISTENT_FIELDS) {
            root.addValue(field.getKey(), field.write());
        }

        Files.write(new File(path).toPath(), root.write().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Reads the sanjo data from the the given path and stores the parsed
     * objects (refer to sanjo docs) in {@link PersistentStorage#DATA}.
     *
     * @param path the file to read from
     */
    public static void read(final String path) throws IOException {
        final SanjoFile f = new SanjoFile(path);
        final SJClass root = f.parser().parse();
        for (final Entry<String, SJValue> val : root.getValues().entrySet()) {
            if (PersistentStorage.put(val.getKey(), val.getValue().getValue()) != null) {
                System.out.println("warning: upon reading serialized data, '" +
                        val.getKey() + "', which was read from " + path + ", was already in" +
                        " the storage and thus overrode existing data.");
            }
        }
    }
}
