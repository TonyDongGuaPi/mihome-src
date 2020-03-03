package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.graphics.PointF;
import android.os.Build;
import android.view.View;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.utils.AnimatorUtils;
import com.transitionseverywhere.utils.PointFProperty;

@TargetApi(11)
public class TranslationAnimationCreator {

    /* renamed from: a  reason: collision with root package name */
    public static final PointFProperty<View> f9484a;

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            f9484a = new PointFProperty<View>() {
                /* renamed from: a */
                public void set(View view, PointF pointF) {
                    view.setTranslationX(pointF.x);
                    view.setTranslationY(pointF.y);
                }
            };
        } else {
            f9484a = null;
        }
    }

    public static Animator a(View view, TransitionValues transitionValues, int i, int i2, float f, float f2, float f3, float f4, TimeInterpolator timeInterpolator, Transition transition) {
        float f5;
        float f6;
        View view2 = view;
        TransitionValues transitionValues2 = transitionValues;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) transitionValues2.f9482a.getTag(R.id.transitionPosition);
        if (iArr != null) {
            float f7 = ((float) (iArr[0] - i)) + translationX;
            f5 = ((float) (iArr[1] - i2)) + translationY;
            f6 = f7;
        } else {
            f6 = f;
            f5 = f2;
        }
        int round = i + Math.round(f6 - translationX);
        int round2 = i2 + Math.round(f5 - translationY);
        view.setTranslationX(f6);
        view.setTranslationY(f5);
        Animator a2 = AnimatorUtils.a(view, f9484a, f6, f5, f3, f4);
        if (a2 != null) {
            TransitionPositionListener transitionPositionListener = new TransitionPositionListener(view, transitionValues2.f9482a, round, round2, translationX, translationY);
            transition.a((Transition.TransitionListener) transitionPositionListener);
            a2.addListener(transitionPositionListener);
            AnimatorUtils.a(a2, transitionPositionListener);
            a2.setInterpolator(timeInterpolator);
        }
        return a2;
    }

    private static class TransitionPositionListener extends AnimatorListenerAdapter implements Transition.TransitionListener {

        /* renamed from: a  reason: collision with root package name */
        private final View f9485a;
        private final View b;
        private final int c;
        private final int d;
        private int[] e;
        private float f;
        private float g;
        private final float h;
        private final float i;

        public void a(Transition transition) {
        }

        public void c(Transition transition) {
        }

        public void d(Transition transition) {
        }

        public void e(Transition transition) {
        }

        public void onAnimationEnd(Animator animator) {
        }

        private TransitionPositionListener(View view, View view2, int i2, int i3, float f2, float f3) {
            this.b = view;
            this.f9485a = view2;
            this.c = i2 - Math.round(this.b.getTranslationX());
            this.d = i3 - Math.round(this.b.getTranslationY());
            this.h = f2;
            this.i = f3;
            this.e = (int[]) this.f9485a.getTag(R.id.transitionPosition);
            if (this.e != null) {
                this.f9485a.setTag(R.id.transitionPosition, (Object) null);
            }
        }

        public void onAnimationCancel(Animator animator) {
            if (this.e == null) {
                this.e = new int[2];
            }
            this.e[0] = Math.round(((float) this.c) + this.b.getTranslationX());
            this.e[1] = Math.round(((float) this.d) + this.b.getTranslationY());
            this.f9485a.setTag(R.id.transitionPosition, this.e);
        }

        public void onAnimationPause(Animator animator) {
            this.f = this.b.getTranslationX();
            this.g = this.b.getTranslationY();
            this.b.setTranslationX(this.h);
            this.b.setTranslationY(this.i);
        }

        public void onAnimationResume(Animator animator) {
            this.b.setTranslationX(this.f);
            this.b.setTranslationY(this.g);
        }

        public void b(Transition transition) {
            this.b.setTranslationX(this.h);
            this.b.setTranslationY(this.i);
        }
    }
}
