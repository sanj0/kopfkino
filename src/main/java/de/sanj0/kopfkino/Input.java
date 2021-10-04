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

import de.sanj0.kopfkino.ui.KopfkinoMouseListener;
import de.sanj0.kopfkino.utils.DirectionalVectors;

import java.util.HashSet;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

public class Input {
    public static final Set<Integer> PRESSED_KEYS = new HashSet<>(5);
    public static final Set<Integer> PRESSED_MOUSE_BTNS = new HashSet<>(3);

    private Input() {
    }

    public static boolean keyDown(final int keycode) {
        return PRESSED_KEYS.contains(keycode);
    }

    public static boolean mouseDown(final int button) {
        return PRESSED_MOUSE_BTNS.contains(button);
    }

    public static boolean mouseDown() {
        return !PRESSED_MOUSE_BTNS.isEmpty();
    }

    public static Vector2f cursorPosition() {
        return Game.getInstance().getCurrentScene().getCamera().transformPoint(new Vector2f(KopfkinoMouseListener.cursorPosition));
    }

    public static Vector2f absoluteCursorPosition() {
        return new Vector2f(KopfkinoMouseListener.cursorPosition);
    }

    public static BoundingBox cursor() {
        return new BoundingBox(cursorPosition(), Dimensions.one());
    }

    public static BoundingBox absoluteCursor() {
        return new BoundingBox(absoluteCursorPosition(), Dimensions.one());
    }

    /**
     * Returns the directions from arrow and WASD input.
     *
     * @return directions from arrow and WASD input
     */
    public static Directions input() {
        final Directions directions = new Directions();
        if (up()) directions.add(Directions.Direction.UP);
        if (down()) directions.add(Directions.Direction.DOWN);
        if (left()) directions.add(Directions.Direction.LEFT);
        if (right()) directions.add(Directions.Direction.RIGHT);
        return directions;
    }

    /**
     * Returns a unit vector derived from up / down, left / right input via
     * arrow key and WASD.
     *
     * @return a unit vector that represents up / down, left / right input from
     * arrow keys and WASD
     */
    public static Vector2f direction() {
        return DirectionalVectors.getDirectionalVector(input());
    }

    /**
     * Is right arrow or D down?
     *
     * @return true if right arrow of A is down
     */
    public static boolean right() {
        return keyDown(VK_RIGHT) || keyDown(VK_D);
    }

    /**
     * Is left arrow or A down?
     *
     * @return true if right arrow of A is down
     */
    public static boolean left() {
        return keyDown(VK_LEFT) || keyDown(VK_A);
    }

    /**
     * Is up arrow or W down?
     *
     * @return true if up arrow of W is down
     */
    public static boolean up() {
        return keyDown(VK_UP) || keyDown(VK_W);
    }

    /**
     * Is down arrow or S down?
     *
     * @return true if up arrow of W is down
     */
    public static boolean down() {
        return keyDown(VK_DOWN) || keyDown(VK_S);
    }
}
