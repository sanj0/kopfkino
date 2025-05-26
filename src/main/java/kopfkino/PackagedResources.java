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

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Load resources from within the maven resources directory
 */
public class PackagedResources {
    private static final ClassLoader CLASS_LOADER = PackagedResources.class.getClassLoader();

    private PackagedResources() {
    }

    /**
     * Loads the resource located at the given path as a {@link BufferedImage}.
     *
     * @param path the name of the resource
     *
     * @return a {@link BufferedImage} loaded from the given path
     */
    public static BufferedImage loadImage(final String path) {
        checkPath(path);
        try (final InputStream in = CLASS_LOADER.getResourceAsStream(path)) {
            if (in == null)
                throw new IOException("unable to open resource " + path);
            return ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads the resource located at the given path as a {@link Clip}.
     *
     * @param path the name of the resource
     *
     * @return a {@link Clip} loaded from the given path
     */
    public static Clip loadAudio(final String path) {
        checkPath(path);
        try (final InputStream in = CLASS_LOADER.getResourceAsStream(path)) {
            if (in == null)
                throw new IOException("unable to open resource " + path);
            try (final AudioInputStream audioIn = AudioSystem.getAudioInputStream(in)) {
                final Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                return clip;
            }
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void checkPath(final String path) {
        if (path.startsWith("/")) {
            throw new IllegalArgumentException("path to packaged resource must not start with '\\'");
        }
    }
}
