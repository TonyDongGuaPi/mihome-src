package com.xiaomi.smarthome.library.common.widget.spans;

import android.text.TextPaint;
import android.text.style.SuperscriptSpan;

public class TopAlignSuperscriptSpan extends SuperscriptSpan {

    /* renamed from: a  reason: collision with root package name */
    protected int f19077a = 2;
    protected float b = 0.0f;

    TopAlignSuperscriptSpan() {
    }

    TopAlignSuperscriptSpan(float f) {
        double d = (double) f;
        if (d > 0.0d && d < 1.0d) {
            this.b = f;
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        float ascent = textPaint.ascent();
        textPaint.setTextSize(textPaint.getTextSize() / ((float) this.f19077a));
        float f = textPaint.getFontMetrics().ascent;
        textPaint.baselineShift = (int) (((float) textPaint.baselineShift) + ((ascent - (this.b * ascent)) - (f - (this.b * f))));
    }

    public void updateMeasureState(TextPaint textPaint) {
        updateDrawState(textPaint);
    }
}
