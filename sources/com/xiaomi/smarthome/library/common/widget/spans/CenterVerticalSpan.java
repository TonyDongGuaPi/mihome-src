package com.xiaomi.smarthome.library.common.widget.spans;

import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class CenterVerticalSpan extends MetricAffectingSpan {

    /* renamed from: a  reason: collision with root package name */
    private final int f19076a;

    public CenterVerticalSpan(int i) {
        this.f19076a = i;
    }

    public void updateMeasureState(@NonNull TextPaint textPaint) {
        textPaint.baselineShift += a(textPaint);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.baselineShift += a(textPaint);
    }

    private int a(TextPaint textPaint) {
        return ((int) ((textPaint.ascent() + textPaint.descent()) / 2.0f)) + this.f19076a;
    }
}
