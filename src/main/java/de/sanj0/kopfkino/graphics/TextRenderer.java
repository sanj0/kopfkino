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

package de.sanj0.kopfkino.graphics;

import de.sanj0.kopfkino.BoundingBox;
import de.sanj0.kopfkino.Dimensions;
import de.sanj0.kopfkino.KopfkinoGraphics;
import de.sanj0.kopfkino.Vector2f;

/**
 * Renders an Entity as text.
 */
public class TextRenderer extends EntityRenderer {
    private String text;
    private KopfkinoGraphics.TextAnchor anchor;

    public TextRenderer(final String text, final Vector2f positionOffset, final RenderConfig renderConfig) {
        super(positionOffset, new Dimensions(-1, -1), renderConfig);
        this.text = text;
        this.anchor = KopfkinoGraphics.TextAnchor.CENTRE;
    }

    public TextRenderer(final String text, final RenderConfig renderConfig) {
        super(renderConfig);
        this.text = text;
        this.anchor = KopfkinoGraphics.TextAnchor.CENTRE;
    }

    @Override
    protected final void renderEntity(final KopfkinoGraphics graphics, final BoundingBox bb) {
        graphics.drawString(this.text, bb.getPosition(), this.anchor);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setAnchor(KopfkinoGraphics.TextAnchor anchor) {
        this.anchor = anchor;
    }

    public KopfkinoGraphics.TextAnchor getAnchor() {
        return this.anchor;
    }
}
