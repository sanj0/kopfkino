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

import kopfkino.Game;
import kopfkino.Input;
import kopfkino.gui.Gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KopfkinoKeyListener implements KeyListener {
    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        Gui gui = Game.getInstance().getCurrentScene().getGui();
        if (gui != null) {
            if (gui.onKeyDown(e)) {
                return;
            }
        }
        Input.PRESSED_KEYS.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        Gui gui = Game.getInstance().getCurrentScene().getGui();
        if (gui != null) {
            if (gui.onKeyUp(e)) {
                return;
            }
        }
        Input.PRESSED_KEYS.remove(e.getKeyCode());
    }
}
