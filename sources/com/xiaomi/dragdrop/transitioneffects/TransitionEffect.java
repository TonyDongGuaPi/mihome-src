package com.xiaomi.dragdrop.transitioneffects;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public abstract class TransitionEffect {

    /* renamed from: a  reason: collision with root package name */
    public static final float f10126a = 1.3f;
    public static final int b = 300;
    public static final float c;
    public static float d;
    public TransitionEffect e = null;

    public abstract float a();

    public abstract void a(float f, float f2, float f3, float f4, View view, ViewGroup viewGroup);

    public abstract void a(View view, ViewGroup viewGroup);

    public abstract int b();

    static {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        c = displayMetrics.density * 1280.0f;
        double pow = Math.pow(2.0d, (double) displayMetrics.density);
        double d2 = (double) displayMetrics.widthPixels;
        Double.isNaN(d2);
        double d3 = pow * d2;
        double d4 = (double) displayMetrics.density;
        Double.isNaN(d4);
        d = (float) (((d3 / d4) / 320.0d) * 1280.0d);
    }

    /* access modifiers changed from: protected */
    public void a(View view) {
        if (this.e == null || this.e == this) {
            b(view);
        } else {
            this.e.a(view);
        }
    }

    /* access modifiers changed from: protected */
    public void b(View view) {
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setAlpha(1.0f);
    }

    /* access modifiers changed from: protected */
    public void a(ViewGroup viewGroup) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            a(viewGroup.getChildAt(childCount));
        }
    }
}
