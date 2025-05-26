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

public class OvalEntityRenderer extends PrimitiveEntityRenderer {
    public OvalEntityRenderer(final Vector2f positionOffset, final Dimensions sizeOffset, final RenderConfig renderConfig) {
        super(positionOffset, sizeOffset, renderConfig);
    }

    public OvalEntityRenderer(final RenderConfig renderConfig) {
        super(renderConfig);
    }

    public OvalEntityRenderer() {
        super();
    }

    @Override
    protected void renderShape(final KopfkinoGraphics graphics, final BoundingBox bb, final boolean fill) {
        if (fill) {
            graphics.drawOval(bb);
        } else {
            graphics.outlineOval(bb);
        }
    }
}
