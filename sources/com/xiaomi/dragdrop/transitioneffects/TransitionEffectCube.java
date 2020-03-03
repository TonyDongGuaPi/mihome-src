package com.xiaomi.dragdrop.transitioneffects;

import android.view.View;
import android.view.ViewGroup;

public class TransitionEffectCube extends TransitionEffect {
    public float a() {
        return 0.0f;
    }

    public int b() {
        return 330;
    }

    public void a(float f, float f2, float f3, float f4, View view, ViewGroup viewGroup) {
        if (f == 0.0f || Math.abs(f) > 1.0f) {
            a(view);
            return;
        }
        if (this.e == null) {
            b(view);
        }
        float measuredWidth = (float) view.getMeasuredWidth();
        float measuredHeight = ((float) view.getMeasuredHeight()) / 2.0f;
        float scaleY = view.getScaleY();
        float translationY = view.getTranslationY();
        float pivotY = view.getPivotY();
        view.setAlpha(1.0f);
        view.setTranslationY(translationY + ((pivotY - measuredHeight) * (1.0f - scaleY)));
        if (f < 0.0f) {
            measuredWidth = 0.0f;
        }
        view.setPivotX(measuredWidth);
        view.setPivotY(measuredHeight);
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(f * -90.0f);
        view.setCameraDistance(d);
    }

    public void a(View view, ViewGroup viewGroup) {
        super.a(view);
    }
}
