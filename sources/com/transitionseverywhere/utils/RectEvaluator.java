package com.transitionseverywhere.utils;

import android.animation.TypeEvaluator;
import android.annotation.TargetApi;
import android.graphics.Rect;

@TargetApi(14)
public class RectEvaluator implements TypeEvaluator<Rect> {

    /* renamed from: a  reason: collision with root package name */
    private Rect f9501a;

    public RectEvaluator() {
    }

    public RectEvaluator(Rect rect) {
        this.f9501a = rect;
    }

    /* renamed from: a */
    public Rect evaluate(float f, Rect rect, Rect rect2) {
        int i = rect.left + ((int) (((float) (rect2.left - rect.left)) * f));
        int i2 = rect.top + ((int) (((float) (rect2.top - rect.top)) * f));
        int i3 = rect.right + ((int) (((float) (rect2.right - rect.right)) * f));
        int i4 = rect.bottom + ((int) (((float) (rect2.bottom - rect.bottom)) * f));
        if (this.f9501a == null) {
            return new Rect(i, i2, i3, i4);
        }
        this.f9501a.set(i, i2, i3, i4);
        return this.f9501a;
    }
}
