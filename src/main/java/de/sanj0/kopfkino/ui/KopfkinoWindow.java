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

package de.sanj0.kopfkino.ui;

import javax.swing.*;
import java.awt.*;

/**
 * The window the game is rendered in.
 */
public class KopfkinoWindow extends JFrame {

    private final KopfkinoCanvas canvas;

    public KopfkinoWindow(final int w, final int h, final String gameName) throws HeadlessException {
        super(gameName + " (kopfkino engine)");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(null);
        pack();
        setSize(w, getHeight() - getContentPane().getHeight() + h);
        setLocationRelativeTo(null);

        canvas = new KopfkinoCanvas(w, h);
        add(canvas);
        addKeyListener(new KopfkinoKeyListener());
        addWindowListener(new KopfkinoWindowListener());
    }

    /**
     * Gets {@link #canvas}.
     *
     * @return the value of {@link #canvas}
     */
    public KopfkinoCanvas getCanvas() {
        return canvas;
    }
}
