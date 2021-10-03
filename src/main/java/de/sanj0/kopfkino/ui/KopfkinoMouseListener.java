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

import de.sanj0.kopfkino.Input;
import de.sanj0.kopfkino.Vector2f;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KopfkinoMouseListener implements MouseListener, MouseMotionListener {

    public static Vector2f cursorPosition = Vector2f.zero();

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        Input.PRESSED_MOUSE_BTNS.add(e.getButton());
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
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

    private void updateCursor(final MouseEvent e) {
        cursorPosition = new Vector2f(e.getX(), e.getY());
    }
}
