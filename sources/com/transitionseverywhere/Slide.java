package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.transitionseverywhere.utils.ViewUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TargetApi(14)
public class Slide extends Visibility {
    private static final CalculateSlide P = new CalculateSlideHorizontal() {
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationX() - ((float) viewGroup.getWidth());
        }
    };
    private static final CalculateSlide Q = new CalculateSlideHorizontal() {
        public float a(ViewGroup viewGroup, View view) {
            if (ViewUtils.g(viewGroup)) {
                return view.getTranslationX() + ((float) viewGroup.getWidth());
            }
            return view.getTranslationX() - ((float) viewGroup.getWidth());
        }
    };
    private static final CalculateSlide R = new CalculateSlideVertical() {
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationY() - ((float) viewGroup.getHeight());
        }
    };
    private static final CalculateSlide S = new CalculateSlideHorizontal() {
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationX() + ((float) viewGroup.getWidth());
        }
    };
    private static final CalculateSlide T = new CalculateSlideHorizontal() {
        public float a(ViewGroup viewGroup, View view) {
            if (ViewUtils.g(viewGroup)) {
                return view.getTranslationX() - ((float) viewGroup.getWidth());
            }
            return view.getTranslationX() + ((float) viewGroup.getWidth());
        }
    };
    private static final CalculateSlide U = new CalculateSlideVertical() {
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationY() + ((float) viewGroup.getHeight());
        }
    };

    /* renamed from: a  reason: collision with root package name */
    protected static final TimeInterpolator f9469a = new DecelerateInterpolator();
    protected static final TimeInterpolator b = new AccelerateInterpolator();
    private int O = 80;
    protected CalculateSlide c = U;

    @Retention(RetentionPolicy.SOURCE)
    public @interface GravityFlag {
    }

    protected interface CalculateSlide {
        float a(ViewGroup viewGroup, View view);

        float b(ViewGroup viewGroup, View view);
    }

    protected static abstract class CalculateSlideHorizontal implements CalculateSlide {
        protected CalculateSlideHorizontal() {
        }

        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationY();
        }
    }

    protected static abstract class CalculateSlideVertical implements CalculateSlide {
        protected CalculateSlideVertical() {
        }

        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationX();
        }
    }

    public Slide() {
        a(80);
    }

    public Slide(int i) {
        a(i);
    }

    public Slide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Slide);
        int i = obtainStyledAttributes.getInt(R.styleable.Slide_slideEdge, 80);
        obtainStyledAttributes.recycle();
        a(i);
    }

    @SuppressLint({"RtlHardcoded"})
    public void a(int i) {
        if (i == 3) {
            this.c = P;
        } else if (i == 5) {
            this.c = S;
        } else if (i == 48) {
            this.c = R;
        } else if (i == 80) {
            this.c = U;
        } else if (i == 8388611) {
            this.c = Q;
        } else if (i == 8388613) {
            this.c = T;
        } else {
            throw new IllegalArgumentException("Invalid slide direction");
        }
        this.O = i;
        SidePropagation sidePropagation = new SidePropagation();
        sidePropagation.a(i);
        a((TransitionPropagation) sidePropagation);
    }

    public int b() {
        return this.O;
    }

    public Animator a(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues2.b.get("android:visibility:screenLocation");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        return TranslationAnimationCreator.a(view, transitionValues2, iArr[0], iArr[1], this.c.a(viewGroup, view), this.c.b(viewGroup, view), translationX, translationY, f9469a, this);
    }

    public Animator b(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues.b.get("android:visibility:screenLocation");
        return TranslationAnimationCreator.a(view, transitionValues, iArr[0], iArr[1], view.getTranslationX(), view.getTranslationY(), this.c.a(viewGroup, view), this.c.b(viewGroup, view), b, this);
    }
}
