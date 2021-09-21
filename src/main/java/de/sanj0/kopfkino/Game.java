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

import de.sanj0.kopfkino.engine.FixedUpdateLoop;
import de.sanj0.kopfkino.engine.RenderLoop;
import de.sanj0.kopfkino.ui.KopfkinoWindow;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {

    private static Game instance = null;

    private final int resolutionW;
    private final int resolutionH;
    private long fixedUpdatePeriod;
    private final String name;
    private final Color backgroundColor;

    private KopfkinoWindow window;
    private ScheduledExecutorService executorService;

    private Game(final int resolutionW, final int resolutionH, final String name, final Color backgroundColor) {
        this.resolutionW = resolutionW;
        this.resolutionH = resolutionH;
        this.name = name;
        this.backgroundColor = backgroundColor;
    }

    public static void init(final int resolutionW, final int resolutionH,
                            final String name) {
        if (instance != null) {
            System.err.println("game already initialised");
            System.exit(1);
        }
        instance = new Game(resolutionW, resolutionH, name, Color.BLACK);
    }

    public static void start(final long fixedUpdatePeriod, final int cappedFPS) {
        instance.fixedUpdatePeriod = fixedUpdatePeriod;
        instance.window = new KopfkinoWindow(instance.resolutionW, instance.resolutionH, instance.name);
        instance.window.setVisible(true);

        instance.executorService = Executors.newScheduledThreadPool(2);
        instance.executorService.scheduleAtFixedRate(new FixedUpdateLoop(), instance.fixedUpdatePeriod, instance.fixedUpdatePeriod, TimeUnit.MILLISECONDS);
        instance.executorService.scheduleAtFixedRate(new RenderLoop(), 0, cappedFPS, TimeUnit.MILLISECONDS);
    }

    public static void exit() {
        instance.executorService.shutdownNow();
        System.exit(0);
    }

    public static Game getInstance() {
        return instance;
    }

    /**
     * Gets {@link #resolutionW}.
     *
     * @return the value of {@link #resolutionW}
     */
    public int getResolutionW() {
        return resolutionW;
    }

    /**
     * Gets {@link #resolutionH}.
     *
     * @return the value of {@link #resolutionH}
     */
    public int getResolutionH() {
        return resolutionH;
    }

    /**
     * Gets {@link #fixedUpdatePeriod}.
     *
     * @return the value of {@link #fixedUpdatePeriod}
     */
    public long getFixedUpdatePeriod() {
        return fixedUpdatePeriod;
    }

    /**
     * Gets {@link #name}.
     *
     * @return the value of {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Gets {@link #backgroundColor}.
     *
     * @return the value of {@link #backgroundColor}
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Gets {@link #window}.
     *
     * @return the value of {@link #window}
     */
    public KopfkinoWindow getWindow() {
        return window;
    }
}
