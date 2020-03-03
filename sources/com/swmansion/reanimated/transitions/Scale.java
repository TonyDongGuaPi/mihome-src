package com.swmansion.reanimated.transitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(api = 26)
public class Scale extends Visibility {

    /* renamed from: a  reason: collision with root package name */
    static final String f8938a = "scale:scaleX";
    static final String b = "scale:scaleY";

    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        transitionValues.values.put(f8938a, Float.valueOf(transitionValues.view.getScaleX()));
        transitionValues.values.put(b, Float.valueOf(transitionValues.view.getScaleY()));
    }

    public Scale a(float f) {
        if (f >= 0.0f) {
            return this;
        }
        throw new IllegalArgumentException("disappearedScale cannot be negative!");
    }

    private Animator a(final View view, float f, float f2, TransitionValues transitionValues) {
        final float scaleX = view.getScaleX();
        final float scaleY = view.getScaleY();
        float f3 = scaleX * f;
        float f4 = scaleX * f2;
        float f5 = f * scaleY;
        float f6 = f2 * scaleY;
        if (transitionValues != null) {
            Float f7 = (Float) transitionValues.values.get(f8938a);
            Float f8 = (Float) transitionValues.values.get(b);
            if (!(f7 == null || f7.floatValue() == scaleX)) {
                f3 = f7.floatValue();
            }
            if (!(f8 == null || f8.floatValue() == scaleY)) {
                f5 = f8.floatValue();
            }
        }
        view.setScaleX(f3);
        view.setScaleY(f5);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{f3, f4}), ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{f5, f6})});
        addListener(new TransitionListenerAdapter() {
            public void onTransitionEnd(Transition transition) {
                view.setScaleX(scaleX);
                view.setScaleY(scaleY);
                transition.removeListener(this);
            }
        });
        return animatorSet;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return a(view, 0.0f, 1.0f, transitionValues);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return a(view, 1.0f, 0.0f, transitionValues);
    }
}
