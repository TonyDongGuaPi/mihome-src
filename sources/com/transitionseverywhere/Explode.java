package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

@TargetApi(14)
public class Explode extends Visibility {
    private static final String O = "android:explode:screenBounds";

    /* renamed from: a  reason: collision with root package name */
    private static final TimeInterpolator f9457a = new DecelerateInterpolator();
    private static final TimeInterpolator b = new AccelerateInterpolator();
    private static final String c = "Explode";
    private int[] P = new int[2];

    public Explode() {
        a((TransitionPropagation) new CircularPropagation());
    }

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a((TransitionPropagation) new CircularPropagation());
    }

    private void e(TransitionValues transitionValues) {
        View view = transitionValues.f9482a;
        view.getLocationOnScreen(this.P);
        int i = this.P[0];
        int i2 = this.P[1];
        transitionValues.b.put(O, new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2));
    }

    public void a(TransitionValues transitionValues) {
        super.a(transitionValues);
        e(transitionValues);
    }

    public void b(TransitionValues transitionValues) {
        super.b(transitionValues);
        e(transitionValues);
    }

    public Animator a(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues2.b.get(O);
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        a((View) viewGroup, rect, this.P);
        return TranslationAnimationCreator.a(view, transitionValues2, rect.left, rect.top, translationX + ((float) this.P[0]), translationY + ((float) this.P[1]), translationX, translationY, f9457a, this);
    }

    public Animator b(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        float f;
        float f2;
        if (transitionValues == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues.b.get(O);
        int i = rect.left;
        int i2 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) transitionValues.f9482a.getTag(R.id.transitionPosition);
        if (iArr != null) {
            f2 = ((float) (iArr[0] - rect.left)) + translationX;
            f = ((float) (iArr[1] - rect.top)) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
        } else {
            f2 = translationX;
            f = translationY;
        }
        a((View) viewGroup, rect, this.P);
        return TranslationAnimationCreator.a(view, transitionValues, i, i2, translationX, translationY, f2 + ((float) this.P[0]), f + ((float) this.P[1]), b, this);
    }

    private void a(View view, Rect rect, int[] iArr) {
        int i;
        int i2;
        double d;
        double d2;
        View view2 = view;
        view2.getLocationOnScreen(this.P);
        int i3 = this.P[0];
        int i4 = this.P[1];
        Rect r = r();
        if (r == null) {
            i2 = (view.getWidth() / 2) + i3 + Math.round(view.getTranslationX());
            i = (view.getHeight() / 2) + i4 + Math.round(view.getTranslationY());
        } else {
            int centerX = r.centerX();
            i = r.centerY();
            i2 = centerX;
        }
        double centerX2 = (double) (rect.centerX() - i2);
        double centerY = (double) (rect.centerY() - i);
        if (centerX2 == 0.0d && centerY == 0.0d) {
            d2 = (Math.random() * 2.0d) - 1.0d;
            d = (Math.random() * 2.0d) - 1.0d;
        } else {
            double d3 = centerY;
            d2 = centerX2;
            d = d3;
        }
        double hypot = Math.hypot(d2, d);
        double a2 = a(view2, i2 - i3, i - i4);
        iArr[0] = (int) Math.round((d2 / hypot) * a2);
        iArr[1] = (int) Math.round(a2 * (d / hypot));
    }

    private static double a(View view, int i, int i2) {
        return Math.hypot((double) Math.max(i, view.getWidth() - i), (double) Math.max(i2, view.getHeight() - i2));
    }
}
