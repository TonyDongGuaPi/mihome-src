package com.transitionseverywhere.extra;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.R;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionUtils;
import com.transitionseverywhere.TransitionValues;
import com.transitionseverywhere.Visibility;

@TargetApi(14)
public class Scale extends Visibility {

    /* renamed from: a  reason: collision with root package name */
    static final String f9491a = "scale:scaleX";
    static final String b = "scale:scaleY";
    private float c = 0.0f;

    public Scale() {
    }

    public Scale(float f) {
        a(f);
    }

    public void a(TransitionValues transitionValues) {
        super.a(transitionValues);
        if (transitionValues.f9482a != null) {
            transitionValues.b.put(f9491a, Float.valueOf(transitionValues.f9482a.getScaleX()));
            transitionValues.b.put(b, Float.valueOf(transitionValues.f9482a.getScaleY()));
        }
    }

    public Scale a(float f) {
        if (f >= 0.0f) {
            this.c = f;
            return this;
        }
        throw new IllegalArgumentException("disappearedScale cannot be negative!");
    }

    public Scale(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Scale);
        a(obtainStyledAttributes.getFloat(R.styleable.Scale_disappearedScale, this.c));
        obtainStyledAttributes.recycle();
    }

    @Nullable
    private Animator a(final View view, float f, float f2, TransitionValues transitionValues) {
        final float scaleX = view.getScaleX();
        final float scaleY = view.getScaleY();
        float f3 = scaleX * f;
        float f4 = scaleX * f2;
        float f5 = f * scaleY;
        float f6 = f2 * scaleY;
        if (transitionValues != null) {
            Float f7 = (Float) transitionValues.b.get(f9491a);
            Float f8 = (Float) transitionValues.b.get(b);
            if (!(f7 == null || f7.floatValue() == scaleX)) {
                f3 = f7.floatValue();
            }
            if (!(f8 == null || f8.floatValue() == scaleY)) {
                f5 = f8.floatValue();
            }
        }
        view.setScaleX(f3);
        view.setScaleY(f5);
        Animator a2 = TransitionUtils.a(ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{f3, f4}), ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{f5, f6}));
        a((Transition.TransitionListener) new Transition.TransitionListenerAdapter() {
            public void b(Transition transition) {
                view.setScaleX(scaleX);
                view.setScaleY(scaleY);
            }
        });
        return a2;
    }

    public Animator a(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return a(view, this.c, 1.0f, transitionValues);
    }

    public Animator b(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return a(view, 1.0f, this.c, transitionValues);
    }
}
