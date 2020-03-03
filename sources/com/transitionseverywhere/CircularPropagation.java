package com.transitionseverywhere;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.view.ViewGroup;

@TargetApi(14)
public class CircularPropagation extends VisibilityPropagation {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9453a = "CircularPropagation";
    private float b = 3.0f;

    public void a(float f) {
        if (f != 0.0f) {
            this.b = f;
            return;
        }
        throw new IllegalArgumentException("propagationSpeed may not be 0");
    }

    public long a(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        int i2;
        int i3;
        if (transitionValues == null && transitionValues2 == null) {
            return 0;
        }
        if (transitionValues2 == null || b(transitionValues) == 0) {
            i = -1;
        } else {
            transitionValues = transitionValues2;
            i = 1;
        }
        int c = c(transitionValues);
        int d = d(transitionValues);
        Rect r = transition.r();
        if (r != null) {
            i3 = r.centerX();
            i2 = r.centerY();
        } else {
            int[] iArr = new int[2];
            viewGroup.getLocationOnScreen(iArr);
            int round = Math.round(((float) (iArr[0] + (viewGroup.getWidth() / 2))) + viewGroup.getTranslationX());
            i2 = Math.round(((float) (iArr[1] + (viewGroup.getHeight() / 2))) + viewGroup.getTranslationY());
            i3 = round;
        }
        double a2 = a((float) c, (float) d, (float) i3, (float) i2) / a(0.0f, 0.0f, (float) viewGroup.getWidth(), (float) viewGroup.getHeight());
        long e = transition.e();
        if (e < 0) {
            e = 300;
        }
        double d2 = (double) (((float) (e * ((long) i))) / this.b);
        Double.isNaN(d2);
        return Math.round(d2 * a2);
    }

    private static double a(float f, float f2, float f3, float f4) {
        return Math.hypot((double) (f3 - f), (double) (f4 - f2));
    }
}
