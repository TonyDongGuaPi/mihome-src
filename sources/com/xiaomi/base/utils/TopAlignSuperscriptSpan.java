package com.xiaomi.base.utils;

import android.text.TextPaint;
import android.text.style.SuperscriptSpan;

public class TopAlignSuperscriptSpan extends SuperscriptSpan {

    /* renamed from: a  reason: collision with root package name */
    protected float f10046a = 1.5f;
    protected float b = 0.0f;

    TopAlignSuperscriptSpan() {
    }

    public TopAlignSuperscriptSpan(float f) {
        double d = (double) f;
        if (d > 0.0d && d < 1.0d) {
            this.b = f;
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        float ascent = textPaint.ascent();
        textPaint.setTextSize(textPaint.getTextSize() / this.f10046a);
        float f = textPaint.getFontMetrics().ascent;
        textPaint.baselineShift += (int) ((ascent - (this.b * ascent)) - (f - (this.b * f)));
    }

    public void updateMeasureState(TextPaint textPaint) {
        updateDrawState(textPaint);
    }
}
