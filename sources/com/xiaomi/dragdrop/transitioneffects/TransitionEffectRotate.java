package com.xiaomi.dragdrop.transitioneffects;

import android.view.View;
import android.view.ViewGroup;

public class TransitionEffectRotate extends TransitionEffect {
    float f;

    public float a() {
        return 1.3f;
    }

    public int b() {
        return 330;
    }

    public TransitionEffectRotate(float f2) {
        this.f = f2;
    }

    public void a(float f2, float f3, float f4, float f5, View view, ViewGroup viewGroup) {
        if (f2 == 0.0f || Math.abs(f2) > 1.0f) {
            a(view);
            return;
        }
        if (this.e == null) {
            b(view);
        }
        view.setPivotX(((float) view.getMeasuredWidth()) / 2.0f);
        view.setPivotY(((float) view.getMeasuredHeight()) / 2.0f);
        view.setRotationX(this.f * f2);
        view.setCameraDistance(d);
    }

    public void a(View view, ViewGroup viewGroup) {
        super.a(view);
    }
}
