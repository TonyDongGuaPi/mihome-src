package com.mi.global.bbs.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mi.global.bbs.R;

@RequiresApi(api = 19)
public class StartAnimatable extends Transition {
    /* access modifiers changed from: private */
    public final Animatable animatable;

    public void captureEndValues(TransitionValues transitionValues) {
    }

    public void captureStartValues(TransitionValues transitionValues) {
    }

    public StartAnimatable(Animatable animatable2) {
        if (animatable2 instanceof Drawable) {
            this.animatable = animatable2;
            return;
        }
        throw new IllegalArgumentException("Non-Drawable resource provided.");
    }

    @RequiresApi(api = 21)
    public StartAnimatable(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StartAnimatable);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.StartAnimatable_android_src);
        obtainStyledAttributes.recycle();
        if (drawable instanceof Animatable) {
            this.animatable = (Animatable) drawable;
            return;
        }
        throw new IllegalArgumentException("Non-Animatable resource provided.");
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (this.animatable == null || transitionValues2 == null || !(transitionValues2.view instanceof ImageView)) {
            return null;
        }
        ((ImageView) transitionValues2.view).setImageDrawable((Drawable) this.animatable);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 1});
        ofInt.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                StartAnimatable.this.animatable.start();
            }
        });
        return ofInt;
    }
}
