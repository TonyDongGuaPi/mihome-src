package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(14)
public class ChangeScroll extends Transition {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9441a = "android:changeScroll:x";
    private static final String b = "android:changeScroll:y";

    public ChangeScroll() {
    }

    public ChangeScroll(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public void b(TransitionValues transitionValues) {
        d(transitionValues);
    }

    private void d(TransitionValues transitionValues) {
        transitionValues.b.put(f9441a, Integer.valueOf(transitionValues.f9482a.getScrollX()));
        transitionValues.b.put(b, Integer.valueOf(transitionValues.f9482a.getScrollY()));
    }

    public Animator a(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2 = null;
        if (transitionValues == null || transitionValues2 == null || Build.VERSION.SDK_INT < 14) {
            return null;
        }
        View view = transitionValues2.f9482a;
        int intValue = ((Integer) transitionValues.b.get(f9441a)).intValue();
        int intValue2 = ((Integer) transitionValues2.b.get(f9441a)).intValue();
        int intValue3 = ((Integer) transitionValues.b.get(b)).intValue();
        int intValue4 = ((Integer) transitionValues2.b.get(b)).intValue();
        if (intValue != intValue2) {
            view.setScrollX(intValue);
            objectAnimator = ObjectAnimator.ofInt(view, "scrollX", new int[]{intValue, intValue2});
        } else {
            objectAnimator = null;
        }
        if (intValue3 != intValue4) {
            view.setScrollY(intValue3);
            objectAnimator2 = ObjectAnimator.ofInt(view, "scrollY", new int[]{intValue3, intValue4});
        }
        return TransitionUtils.a(objectAnimator, objectAnimator2);
    }
}
