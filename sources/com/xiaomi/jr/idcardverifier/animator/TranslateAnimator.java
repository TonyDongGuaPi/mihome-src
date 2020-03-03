package com.xiaomi.jr.idcardverifier.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class TranslateAnimator {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10866a = 500;
    private float b;
    private TranslateAnimatorListener c;
    private ObjectAnimator d;
    private ObjectAnimator e;
    private AnimatorSet f;

    public interface TranslateAnimatorListener {
        void a();

        void b();
    }

    public TranslateAnimator(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getSize(point);
        this.b = (float) point.x;
    }

    public TranslateAnimator(Context context, TranslateAnimatorListener translateAnimatorListener) {
        this(context);
        this.c = translateAnimatorListener;
    }

    public void a(TranslateAnimatorListener translateAnimatorListener) {
        this.c = translateAnimatorListener;
    }

    public void a(View view) {
        if (this.d == null) {
            this.d = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{this.b, 0.0f}).setDuration(500);
            this.d.setInterpolator(new DecelerateInterpolator());
        }
        this.d.removeAllUpdateListeners();
        this.d.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                TranslateAnimator.this.b(valueAnimator);
            }
        });
        if (this.e == null) {
            this.e = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, -this.b}).setDuration(500);
            this.e.setInterpolator(new AccelerateInterpolator());
        }
        this.e.removeAllUpdateListeners();
        this.e.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                TranslateAnimator.this.a(valueAnimator);
            }
        });
        if (this.f == null) {
            this.f = new AnimatorSet();
            this.f.playSequentially(new Animator[]{this.e, this.d});
        }
        this.f.start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(ValueAnimator valueAnimator) {
        if (this.c != null && ((Float) valueAnimator.getAnimatedValue()).floatValue() == 0.0f) {
            this.c.b();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ValueAnimator valueAnimator) {
        if (this.c != null && ((Float) valueAnimator.getAnimatedValue()).floatValue() == (-this.b)) {
            this.c.a();
        }
    }

    public void a() {
        this.c = null;
        if (this.f != null) {
            this.f.cancel();
        }
    }
}
