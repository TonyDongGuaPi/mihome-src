package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(14)
public class Rotate extends Transition {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9466a = "android:rotate:rotation";

    public void a(TransitionValues transitionValues) {
        transitionValues.b.put(f9466a, Float.valueOf(transitionValues.f9482a.getRotation()));
    }

    public void b(TransitionValues transitionValues) {
        transitionValues.b.put(f9466a, Float.valueOf(transitionValues.f9482a.getRotation()));
    }

    public Animator a(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        View view = transitionValues2.f9482a;
        float floatValue = ((Float) transitionValues.b.get(f9466a)).floatValue();
        float floatValue2 = ((Float) transitionValues2.b.get(f9466a)).floatValue();
        if (floatValue == floatValue2) {
            return null;
        }
        view.setRotation(floatValue);
        return ObjectAnimator.ofFloat(view, View.ROTATION, new float[]{floatValue, floatValue2});
    }
}
