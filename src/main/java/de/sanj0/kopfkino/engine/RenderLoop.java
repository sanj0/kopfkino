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

package de.sanj0.kopfkino.engine;

import de.sanj0.kopfkino.Game;

public class RenderLoop implements Runnable {
    @Override
    public void run() {
        try {
            Game.getInstance().getWindow().getCanvas().repaint();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
