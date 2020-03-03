package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.TextView;

public class TopTab extends AppCompatTextView implements Checkable {
    private static int LENGTH_STANDARD = Integer.MAX_VALUE;
    private static final String LENGTH_STANDARD_OF_1_CHAR = "度";
    private static final String LENGTH_STANDARD_OF_4_CHAR = "长长长长长";
    private boolean isChecked;
    private Paint.FontMetrics mFontMetrics;

    public TopTab(Context context) {
        this(context, (AttributeSet) null);
    }

    public TopTab(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TopTab(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isChecked = false;
        setGravity(17);
        setEllipsize(TextUtils.TruncateAt.END);
        setSingleLine(true);
        TextPaint paint = getPaint();
        if (paint != null) {
            paint.setFakeBoldText(true);
        }
    }

    public float getTextAscent() {
        return getFontMetrics().ascent - ((float) getBaseline());
    }

    public float getTextDescent() {
        return getFontMetrics().descent - ((float) getBaseline());
    }

    public float getTextTop() {
        return getFontMetrics().top - ((float) getBaseline());
    }

    public float getTextBottom() {
        return getFontMetrics().bottom - ((float) getBaseline());
    }

    private Paint.FontMetrics getFontMetrics() {
        if (this.mFontMetrics == null) {
            this.mFontMetrics = getPaint().getFontMetrics();
        }
        return this.mFontMetrics;
    }

    private boolean shouldAdjustSelfWidth(String str) {
        int measureTitleWidth = measureTitleWidth(str, getTextSize());
        if (LENGTH_STANDARD == Integer.MAX_VALUE) {
            LENGTH_STANDARD = measureTitleWidth(LENGTH_STANDARD_OF_4_CHAR, getTextSize());
        }
        return measureTitleWidth > LENGTH_STANDARD;
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (charSequence != null && shouldAdjustSelfWidth(charSequence.toString())) {
            charSequence = TextUtils.ellipsize(charSequence, getPaint(), (float) LENGTH_STANDARD, TextUtils.TruncateAt.END);
        }
        super.setText(charSequence, bufferType);
    }

    private int measureTitleWidth(String str, float f) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(f);
        return (int) (textPaint.measureText(str) + ((float) getPaddingLeft()) + ((float) getPaddingRight()));
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        if (this.isChecked != z) {
            this.isChecked = z;
            refreshDrawableState();
        }
    }

    public void toggle() {
        setChecked(!this.isChecked);
    }
}
