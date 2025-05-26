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

package kopfkino;

import java.io.File;

/**
 * Manages resources outside the application bundle, i. e.
 * somewhere on the file system.
 * <p>This should only be used for data that changes, e. g. preferences or saved game states.
 */
public class ExternalResources {

    private static File root = null;

    static void init(final String gameName) {
        root = new File(System.getProperty("user.home"), "." + gameName);
        if (!root.exists()) {
            root.mkdirs();
        } else if (root.isFile()) {
            throw new IllegalStateException(root.getAbsolutePath() + " as external resource root already exists and is not a directory");
        }
    }

    public static File getFile(final String name) {
        return new File(root, name);
    }
}
