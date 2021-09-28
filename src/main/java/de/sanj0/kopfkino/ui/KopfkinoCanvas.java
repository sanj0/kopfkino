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

import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.Game;
import de.sanj0.kopfkino.graphics.KopfkinoGraphics;
import de.sanj0.kopfkino.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class KopfkinoCanvas extends JPanel {

    private final BufferedImage canvas;

    public KopfkinoCanvas(final int w, final int h) {
        super(true);
        setSize(w, h);
        setIgnoreRepaint(true);
        canvas = ImageUtils.createCompatibleImage(new Dimensions(Game.getInstance().getResolutionW(), Game.getInstance().getResolutionH()));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D panelGraphics = (Graphics2D) g;
        final Graphics2D g2d = canvas.createGraphics();
        g2d.setBackground((Game.getInstance().getBackgroundColor()));
        g2d.clearRect(0, 0, getWidth(), getHeight());

        final KopfkinoGraphics graphics = new KopfkinoGraphics(g2d);

        panelGraphics.drawImage(canvas, 0, 0, null);
        panelGraphics.dispose();
        g2d.dispose();
    }
}
