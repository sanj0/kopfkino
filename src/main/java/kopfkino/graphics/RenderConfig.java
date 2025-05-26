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

package kopfkino.graphics;

import kopfkino.KopfkinoGraphics;

import java.awt.*;

/**
 * Configures how graphics are rendered using various parameters.
 */
public class RenderConfig {
    /**
     * Hints used for rendering, critical for control of quality and speed.
     */
    private RenderingHints renderingHints;
    /**
     * The color used for rendering primitives.
     */
    private Color color;
    /**
     * The stroke used for rendering primitives.
     */
    private Stroke stroke;
    /**
     * The paint used to fill primitives.
     */
    private Paint paint;
    /**
     * The fonts used to render text.
     */
    private Font font;
    /**
     * The composite used for rendering.
     */
    private Composite composite;

    private RenderConfig(final RenderingHints renderingHints, final Color color,
                         final Stroke stroke, final Paint paint, final Font font,
                         final Composite composite) {
        this.renderingHints = renderingHints;
        this.color = color;
        this.stroke = stroke;
        this.paint = paint;
        this.font = font;
        this.composite = composite;
    }

    /**
     * Returns a builder for this class overloaded with default values for every
     * field.
     *
     * @return a default builder for this class
     */
    public static RenderConfigBuilder builder() {
        return new RenderConfigBuilder();
    }

    /**
     * Creates and returns a new instance that has only its {@link #color}
     * changed from the default, to the given one.
     *
     * @param c a color
     * @return a new instance that only has the color differ from the default
     */
    public static RenderConfig forColor(final Color c) {
        return new RenderConfigBuilder().withColor(c).build();
    }

    /**
     * Convenience-method for {@code RenderConfig.builder().build()}.
     *
     * @return a new default RenderConfig
     */
    public static RenderConfig createDefault() {
        return RenderConfig.builder().build();
    }

    /**
     * Applies this render configuration with all it's values to the given
     * graphics instance for subsequent rendering according to this config.
     *
     * @param g a graphics instance to be configured accordingly
     */
    public void apply(final Graphics2D g) {
        g.setRenderingHints(renderingHints);
        g.setColor(color);
        g.setStroke(stroke);
        g.setPaint(paint);
        g.setFont(font);
        g.setComposite(composite);
    }

    /**
     * Gets {@link #renderingHints}.
     *
     * @return the value of {@link #renderingHints}
     */
    public RenderingHints getRenderingHints() {
        return renderingHints;
    }

    /**
     * Sets {@link #renderingHints}.
     *
     * @param renderingHints the new value of {@link #renderingHints}
     */
    public void setRenderingHints(final RenderingHints renderingHints) {
        this.renderingHints = renderingHints;
    }

    /**
     * Gets {@link #color}.
     *
     * @return the value of {@link #color}
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets {@link #color}.
     *
     * @param color the new value of {@link #color}
     */
    public void setColor(final Color color) {
        this.color = color;
    }

    /**
     * Gets {@link #stroke}.
     *
     * @return the value of {@link #stroke}
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * Sets {@link #stroke}.
     *
     * @param stroke the new value of {@link #stroke}
     */
    public void setStroke(final Stroke stroke) {
        this.stroke = stroke;
    }

    /**
     * Gets {@link #paint}.
     *
     * @return the value of {@link #paint}
     */
    public Paint getPaint() {
        return paint;
    }

    /**
     * Sets {@link #paint}.
     *
     * @param paint the new value of {@link #paint}
     */
    public void setPaint(final Paint paint) {
        this.paint = paint;
    }

    /**
     * Gets {@link #font}.
     *
     * @return the value of {@link #font}
     */
    public Font getFont() {
        return font;
    }

    /**
     * Sets {@link #font}.
     *
     * @param font the new value of {@link #font}
     */
    public void setFont(final Font font) {
        this.font = font;
    }

    /**
     * Gets {@link #composite}.
     *
     * @return the value of {@link #composite}
     */
    public Composite getComposite() {
        return composite;
    }

    /**
     * Sets {@link #composite}.
     *
     * @param composite the new value of {@link #composite}
     */
    public void setComposite(final Composite composite) {
        this.composite = composite;
    }

    public static class RenderConfigBuilder {
        private RenderingHints renderingHints = DefaultRenderingHints.getHints();
        private Color color = Color.BLACK;
        private Stroke stroke = new BasicStroke();
        private Paint paint = null;
        private Font font = KopfkinoGraphics.DEFAULT_FONT;
        private Composite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);

        /**
         * Sets {@link RenderConfig#renderingHints}.
         *
         * @param renderingHints new rendering hints
         *
         * @return {@code this}
         */
        public RenderConfigBuilder withRenderingHints(final RenderingHints renderingHints) {
            this.renderingHints = renderingHints;
            return this;
        }

        /**
         * Puts the given key/value pair into {@link RenderConfig#renderingHints}.
         *
         * @param key   a hint key
         * @param value the hint's value
         *
         * @return {@code this}
         */
        public RenderConfigBuilder putRenderingHint(final Object key, final Object value) {
            renderingHints.put(key, value);
            return this;
        }

        /**
         * Sets {@link RenderConfig#color}.
         *
         * @param color new color
         *
         * @return {@code this}
         */
        public RenderConfigBuilder withColor(final Color color) {
            this.color = color;
            return this;
        }

        /**
         * Sets {@link RenderConfig#stroke}.
         *
         * @param stroke new stroke
         *
         * @return {@code this}
         */
        public RenderConfigBuilder withStroke(final Stroke stroke) {
            this.stroke = stroke;
            return this;
        }

        /**
         * Sets {@link RenderConfig#paint}.
         *
         * @param paint new paint
         *
         * @return {@code this}
         */
        public RenderConfigBuilder withPaint(final Paint paint) {
            this.paint = paint;
            return this;
        }

        /**
         * Sets {@link RenderConfig#font}.
         *
         * @param font new font
         *
         * @return {@code this}
         */
        public RenderConfigBuilder withFont(final Font font) {
            this.font = font;
            return this;
        }

        /**
         * Sets {@link RenderConfig#composite}.
         *
         * @param composite new composite
         *
         * @return {@code this}
         */
        public RenderConfigBuilder withComposite(final Composite composite) {
            this.composite = composite;
            return this;
        }

        /**
         * Returns a new instance of {@link RenderConfig} obtained using all
         * before set values or their respective default.
         *
         * @return a new instance of {@link RenderConfig} according to this
         * builder
         */
        public RenderConfig build() {
            return new RenderConfig(renderingHints, color, stroke, paint, font, composite);
        }
    }
}
