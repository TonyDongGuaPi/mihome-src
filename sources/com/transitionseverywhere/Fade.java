package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.utils.AnimatorUtils;

@TargetApi(14)
public class Fade extends Visibility {

    /* renamed from: a  reason: collision with root package name */
    static final String f9458a = "fade:alpha";
    public static final int b = 1;
    public static final int c = 2;

    public Fade() {
    }

    public Fade(int i) {
        b(i);
    }

    public Fade(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Fade);
        int i = obtainStyledAttributes.getInt(R.styleable.Fade_fadingMode, c());
        obtainStyledAttributes.recycle();
        b(i);
    }

    public void a(TransitionValues transitionValues) {
        super.a(transitionValues);
        if (transitionValues.f9482a != null) {
            transitionValues.b.put(f9458a, Float.valueOf(transitionValues.f9482a.getAlpha()));
        }
    }

    private Animator a(final View view, float f, float f2, TransitionValues transitionValues) {
        final float alpha = view.getAlpha();
        float f3 = f * alpha;
        float f4 = f2 * alpha;
        if (transitionValues != null && transitionValues.b.containsKey(f9458a)) {
            float floatValue = ((Float) transitionValues.b.get(f9458a)).floatValue();
            if (floatValue != alpha) {
                f3 = floatValue;
            }
        }
        view.setAlpha(f3);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{f4});
        ofFloat.addListener(new FadeAnimatorListener(view, alpha));
        a((Transition.TransitionListener) new Transition.TransitionListenerAdapter() {
            public void b(Transition transition) {
                view.setAlpha(alpha);
            }
        });
        return ofFloat;
    }

    public Animator a(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return a(view, 0.0f, 1.0f, transitionValues);
    }

    public Animator b(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return a(view, 1.0f, 0.0f, transitionValues);
    }

    private static class FadeAnimatorListener extends AnimatorListenerAdapter {

        /* renamed from: a  reason: collision with root package name */
        private final View f9460a;
        private float b;
        private boolean c = false;

        public FadeAnimatorListener(View view, float f) {
            this.f9460a = view;
            this.b = f;
        }

        public void onAnimationStart(Animator animator) {
            if (AnimatorUtils.a(this.f9460a) && this.f9460a.getLayerType() == 0) {
                this.c = true;
                this.f9460a.setLayerType(2, (Paint) null);
            }
        }

        public void onAnimationEnd(Animator animator) {
            this.f9460a.setAlpha(this.b);
            if (this.c) {
                this.f9460a.setLayerType(0, (Paint) null);
            }
        }
    }
}
