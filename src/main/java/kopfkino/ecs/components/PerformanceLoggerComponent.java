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

package kopfkino.ecs.components;

import kopfkino.Entity;
import kopfkino.ExternalResources;
import kopfkino.Time;
import kopfkino.collision.Collision;
import kopfkino.ecs.EntityComponent;
import kopfkino.KopfkinoGraphics;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Add this component to an Entity in oder to log
 * the performance of the engine to a file.
 */
public class PerformanceLoggerComponent extends EntityComponent {
    private StringBuilder data = new StringBuilder(25);
    private final int maxBytes;
    private final long logPeriod;
    private int bytesWritten = 0;
    private int ticks = 0;
    private boolean done = false;
    private final Path path = ExternalResources.getFile("perf.txt").toPath();

    public PerformanceLoggerComponent(final Entity subject, final int maxBytes, final long logPeriod) {
        super(subject);
        this.maxBytes = maxBytes;
        this.logPeriod = logPeriod;
        try {
            Files.createFile(path);
        } catch (IOException e) {
            try {
                Files.write(path, new byte[0]);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void fixedUpdate() {
        if (!done) {
            ticks++;
            if (ticks >= logPeriod) {
                data.append("rn:").append(Time.getRenderTime()).append("\nfu:").append(Time.getFixedUpdateTime()).append("\nct:").append(Time.getCollisionDetectionTime()).append("\n");
                try {
                    Files.write(path, data.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bytesWritten += data.length();
                data.setLength(0);
                if (bytesWritten >= maxBytes) {
                    done = true;
                }
                ticks = 0;
            }
        }
    }

    @Override
    public void collision(final Collision collision) {

    }

    @Override
    public void collisionStart(final Collision collision) {

    }

    @Override
    public void collisionEnd(final Entity partner) {

    }

    @Override
    public void updateAfter() {

    }

    @Override
    public void renderAfter(final KopfkinoGraphics graphics) {

    }
}
