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

package kopfkino.ui;

import kopfkino.Dimensions;
import kopfkino.Game;
import kopfkino.Vector2f;
import kopfkino.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class KopfkinoCanvas extends JPanel {
    private BufferedImage render = ImageUtils.createCompatibleImage(Dimensions.resolution());
    private Vector2f contentOffset = Vector2f.zero();
    private Vector2f contentScale = Vector2f.one();

    public KopfkinoCanvas(final int w, final int h) {
        super(true);
        setSize(w, h);
        setIgnoreRepaint(true);
        final KopfkinoMouseListener mouseListener = new KopfkinoMouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D panelGraphics = (Graphics2D) g;
        final BufferedImage render = Game.getInstance().getCurrentScene().getCamera().render(Game.getInstance().getCurrentScene());
        this.render = render;
        panelGraphics.setColor(Game.getInstance().getBackgroundColor());
        panelGraphics.fillRect(0, 0, getWidth(), getHeight());
        if (Game.getInstance().getScaleMethod() == Game.ScaleMethod.LETTER_BOX) {
            final float contentScale = Math.min((float) getWidth() / Game.resolutionWidth(),
                    (float) getHeight() / Game.resolutionHeight());
            final int contentWidth = (int) (Game.resolutionWidth() * contentScale);
            final int contentHeight = (int) (Game.resolutionHeight() * contentScale);
            this.contentScale = Vector2f.num(contentScale);
            contentOffset = new Vector2f(getWidth() / 2f - contentWidth / 2f,
                    Math.max(getHeight() / 2f - contentHeight / 2f, 0));
            panelGraphics.drawImage(render, (int) contentOffset.getX(),
                    (int) contentOffset.getY(),
                    contentWidth, contentHeight, null);
        } else {
            panelGraphics.drawImage(render, 0, 0, getWidth(), getHeight(), null);
        }
        panelGraphics.dispose();
    }

    /**
     * Gets {@link #render}.
     *
     * @return the value of {@link #render}
     */
    public BufferedImage getRender() {
        return render;
    }

    /**
     * Gets {@link #contentOffset}.
     *
     * @return the value of {@link #contentOffset}
     */
    public Vector2f getContentOffset() {
        return contentOffset;
    }

    /**
     * Gets {@link #contentScale}.
     *
     * @return the value of {@link #contentScale}
     */
    public Vector2f getContentScale() {
        return contentScale;
    }
}
