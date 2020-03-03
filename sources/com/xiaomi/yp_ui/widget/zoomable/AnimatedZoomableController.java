package com.xiaomi.yp_ui.widget.zoomable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.view.animation.DecelerateInterpolator;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;

public class AnimatedZoomableController extends AbstractAnimatedZoomableController {
    private static final Class<?> f = AnimatedZoomableController.class;
    private final ValueAnimator g = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});

    public static AnimatedZoomableController i() {
        return new AnimatedZoomableController(TransformGestureDetector.a());
    }

    @SuppressLint({"NewApi"})
    public AnimatedZoomableController(TransformGestureDetector transformGestureDetector) {
        super(transformGestureDetector);
        this.g.setInterpolator(new DecelerateInterpolator());
    }

    @SuppressLint({"NewApi"})
    public void b(Matrix matrix, long j, @Nullable final Runnable runnable) {
        FLog.v(h(), "setTransformAnimated: duration %d ms", (Object) Long.valueOf(j));
        g();
        Preconditions.checkArgument(j > 0);
        Preconditions.checkState(!c());
        a(true);
        this.g.setDuration(j);
        u().getValues(d());
        matrix.getValues(e());
        this.g.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimatedZoomableController.this.a(AnimatedZoomableController.this.f(), ((Float) valueAnimator.getAnimatedValue()).floatValue());
                AnimatedZoomableController.super.b(AnimatedZoomableController.this.f());
            }
        });
        this.g.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                FLog.v(AnimatedZoomableController.this.h(), "setTransformAnimated: animation cancelled");
                a();
            }

            public void onAnimationEnd(Animator animator) {
                FLog.v(AnimatedZoomableController.this.h(), "setTransformAnimated: animation finished");
                a();
            }

            private void a() {
                if (runnable != null) {
                    runnable.run();
                }
                AnimatedZoomableController.this.a(false);
                AnimatedZoomableController.this.v().c();
            }
        });
        this.g.start();
    }

    @SuppressLint({"NewApi"})
    public void g() {
        if (c()) {
            FLog.v(h(), "stopAnimation");
            this.g.cancel();
            this.g.removeAllUpdateListeners();
            this.g.removeAllListeners();
        }
    }

    /* access modifiers changed from: protected */
    public Class<?> h() {
        return f;
    }
}
