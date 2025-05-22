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

import de.sanj0.kopfkino.Game;
import de.sanj0.kopfkino.Input;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.gui.Gui;
import de.sanj0.kopfkino.utils.MathUtils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KopfkinoMouseListener implements MouseListener, MouseMotionListener {
    public static Vector2f cursorPosition = Vector2f.zero();

    private final KopfkinoCanvas container;

    public KopfkinoMouseListener(final KopfkinoCanvas container) {
        this.container = container;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        Gui gui = Game.getInstance().getCurrentScene().getGui();
        if (gui != null) {
            if (gui.onMouseDown(getCursor(e))) {
                return;
            }
        }
        Input.PRESSED_MOUSE_BTNS.add(e.getButton());
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        Gui gui = Game.getInstance().getCurrentScene().getGui();
        if (gui != null) {
            if (gui.onMouseUp(getCursor(e))) {
                return;
            }
        }
        Input.PRESSED_MOUSE_BTNS.remove(e.getButton());
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        updateCursor(e);
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        updateCursor(e);
    }

    private Vector2f getCursor(final MouseEvent e) {
        final Vector2f cursor = new Vector2f(e.getX(), e.getY()).subtract(container.getContentOffset()).divide(container.getContentScale());
        cursor.setX(MathUtils.clamp(cursor.getX(), 0, Game.resolutionWidth()));
        cursor.setY(MathUtils.clamp(cursor.getY(), 0, Game.resolutionHeight()));
        return cursor;
    }

    private void updateCursor(final MouseEvent e) {
        cursorPosition = getCursor(e);
    }
}
