/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.StringBuilder;

/** A text label, with optional word wrapping.
 * <p>
 * Unlike most scene2d.ui widgets, label can be scaled and rotated using the actor's scale, rotation, and origin. This only
 * affects drawing, other scene2d.ui widgets will still use the unscaled and unrotated bounds of the label. Note that a scaled or
 * rotated label causes a SpriteBatch flush when it is drawn, so should be used relatively sparingly.
 * <p>
 * The preferred size of the label is determined by the actual text bounds, unless {@link #setWrap(boolean) word wrap} is enabled.
 * @author Nathan Sweet */
public class Label extends Widget {
	private LabelStyle style;
	private final TextBounds bounds = new TextBounds();
	private final StringBuilder text = new StringBuilder();
	private BitmapFontCache cache;
	private int labelAlign = Align.left;
	private HAlignment lineAlign = HAlignment.LEFT;
	private boolean wrap;
	private float lastPrefHeight;
	private boolean sizeInvalid = true;
	private float fontScaleX = 1, fontScaleY = 1;

	public Label (CharSequence text, Skin skin) {
		this(text, skin.get(LabelStyle.class));
	}

	public Label (CharSequence text, Skin skin, String styleName) {
		this(text, skin.get(styleName, LabelStyle.class));
	}

	/** Creates a label, using a {@link LabelStyle} that has a BitmapFont with the specified name from the skin and the specified
	 * color. */
	public Label (CharSequence text, Skin skin, String fontName, Color color) {
		this(text, new LabelStyle(skin.getFont(fontName), color));
	}

	/** Creates a label, using a {@link LabelStyle} that has a BitmapFont with the specified name and the specified color from the
	 * skin. */
	public Label (CharSequence text, Skin skin, String fontName, String colorName) {
		this(text, new LabelStyle(skin.getFont(fontName), skin.getColor(colorName)));
	}

	public Label (CharSequence text, LabelStyle style) {
		if (text != null) this.text.append(text);
		setStyle(style);
		setWidth(getPrefWidth());
		setHeight(getPrefHeight());
	}

	public void setStyle (LabelStyle style) {
		if (style == null) throw new IllegalArgumentException("style cannot be null.");
		if (style.font == null) throw new IllegalArgumentException("Missing LabelStyle font.");
		this.style = style;
		cache = new BitmapFontCache(style.font, style.font.usesIntegerPositions());
		invalidateHierarchy();
	}

	/** Returns the label's style. Modifying the returned style may not have an effect until {@link #setStyle(LabelStyle)} is
	 * called. */
	public LabelStyle getStyle () {
		return style;
	}

	/** @param newText May be null. */
	public void setText (CharSequence newText) {
		if (newText instanceof StringBuilder) {
			if (text.equals(newText)) return;
			text.setLength(0);
			text.append((StringBuilder)newText);
		} else {
			if (newText == null) newText = "";
			if (textEquals(newText)) return;
			text.setLength(0);
			text.append(newText);
		}
		invalidateHierarchy();
	}

	private boolean textEquals (CharSequence other) {
		int length = text.length;
		char[] chars = text.chars;
		if (length != other.length()) return false;
		for (int i = 0; i < length; i++)
			if (chars[i] != other.charAt(i)) return false;
		return true;
	}

	public CharSequence getText () {
		return text;
	}

	public void invalidate () {
		super.invalidate();
		sizeInvalid = true;
	}

	private void computeSize () {
		sizeInvalid = false;
		if (wrap)
			bounds.set(cache.getFont().getWrappedBounds(text, getWidth()));
		else
			bounds.set(cache.getFont().getMultiLineBounds(text));
		bounds.width *= fontScaleX;
		bounds.height *= fontScaleY;
	}

	public void layout () {
		if (sizeInvalid) computeSize();

		if (wrap) {
			float prefHeight = getPrefHeight();
			if (prefHeight != lastPrefHeight) {
				lastPrefHeight = prefHeight;
				invalidateHierarchy();
			}
		}

		BitmapFont font = cache.getFont();
		float oldScaleX = font.getScaleX();
		float oldScaleY = font.getScaleY();
		if (fontScaleX != 1 || fontScaleY != 1) font.setScale(fontScaleX, fontScaleY);

		float height = getHeight();

		float y;
		if ((labelAlign & Align.top) != 0) {
			y = cache.getFont().isFlipped() ? 0 : height - bounds.height;
			y += style.font.getDescent();
		} else if ((labelAlign & Align.bottom) != 0) {
			y = cache.getFont().isFlipped() ? height - bounds.height : 0;
			y -= style.font.getDescent();
		} else
			y = (int)((height - bounds.height) / 2);
		if (!cache.getFont().isFlipped()) y += bounds.height;

		float x;
		if ((labelAlign & Align.left) != 0)
			x = 0;
		else if ((labelAlign & Align.right) != 0) {
			x = getWidth() - bounds.width;
		} else
			x = (int)((getWidth() - bounds.width) / 2);

		if (wrap)
			cache.setWrappedText(text, x, y, bounds.width, lineAlign);
		else
			cache.setMultiLineText(text, x, y, bounds.width, lineAlign);

		if (fontScaleX != 1 || fontScaleY != 1) font.setScale(oldScaleX, oldScaleY);
	}

	public void draw (SpriteBatch batch, float parentAlpha) {
		validate();
		Color color = getColor();
		if (style.background != null) {
			batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			style.background.draw(batch, getX(), getY(), getWidth(), getHeight());
		}
		cache.setColor(style.fontColor == null ? color : Color.tmp.set(color).mul(style.fontColor));
		cache.setPosition(getX(), getY());
		cache.draw(batch, color.a * parentAlpha);
	}

	public float getPrefWidth () {
		if (wrap) return 0;
		if (sizeInvalid) computeSize();
		return bounds.width;
	}

	public float getPrefHeight () {
		if (sizeInvalid) computeSize();
		return bounds.height - style.font.getDescent() * 2;
	}

	public TextBounds getTextBounds () {
		if (sizeInvalid) computeSize();
		return bounds;
	}

	/** If false, the text will only wrap where it contains newlines (\n). The preferred size of the label will be the text bounds.
	 * If true, the text will word wrap using the width of the label. The preferred width of the label will be 0, it is expected
	 * that the something external will set the width of the label. Default is false. */
	public void setWrap (boolean wrap) {
		this.wrap = wrap;
		invalidateHierarchy();
	}

	/** @param wrapAlign Aligns each line of text horizontally and all the text vertically.
	 * @see Align */
	public void setAlignment (int wrapAlign) {
		setAlignment(wrapAlign, wrapAlign);
	}

	/** @param labelAlign Aligns all the text with the label widget.
	 * @param lineAlign Aligns each line of text (left, right, or center).
	 * @see Align */
	public void setAlignment (int labelAlign, int lineAlign) {
		this.labelAlign = labelAlign;

		if ((lineAlign & Align.left) != 0)
			this.lineAlign = HAlignment.LEFT;
		else if ((lineAlign & Align.right) != 0)
			this.lineAlign = HAlignment.RIGHT;
		else
			this.lineAlign = HAlignment.CENTER;

		invalidate();
	}

	public void setFontScale (float fontScale) {
		this.fontScaleX = fontScale;
		this.fontScaleY = fontScale;
		invalidateHierarchy();
	}

	public void setFontScale (float fontScaleX, float fontScaleY) {
		this.fontScaleX = fontScaleX;
		this.fontScaleY = fontScaleY;
		invalidateHierarchy();
	}

	public float getFontScaleX () {
		return fontScaleX;
	}

	public void setFontScaleX (float fontScaleX) {
		this.fontScaleX = fontScaleX;
		invalidateHierarchy();
	}

	public float getFontScaleY () {
		return fontScaleY;
	}

	public void setFontScaleY (float fontScaleY) {
		this.fontScaleY = fontScaleY;
		invalidateHierarchy();
	}

	/** The style for a label, see {@link Label}.
	 * @author Nathan Sweet */
	static public class LabelStyle {
		public BitmapFont font;
		/** Optional. */
		public Color fontColor;
		/** Optional. */
		public Drawable background;

		public LabelStyle () {
		}

		public LabelStyle (BitmapFont font, Color fontColor) {
			this.font = font;
			this.fontColor = fontColor;
		}

		public LabelStyle (LabelStyle style) {
			this.font = style.font;
			if (style.fontColor != null) this.fontColor = new Color(style.fontColor);
		}
	}
}
