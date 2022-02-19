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

package de.sanj0.kopfkino.ui;

import de.sanj0.kopfkino.Game;

import javax.swing.*;
import java.awt.*;

public class KopfkinoCanvas extends JPanel {
    public KopfkinoCanvas(final int w, final int h) {
        super(true);
        setSize(w, h);
        setIgnoreRepaint(true);
        final KopfkinoMouseListener mouseListener = new KopfkinoMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D panelGraphics = (Graphics2D) g;
        panelGraphics.drawImage(Game.getInstance().getCurrentScene().getCamera().render(Game.getInstance().getCurrentScene()), 0, 0, null);
        panelGraphics.dispose();
    }
}
