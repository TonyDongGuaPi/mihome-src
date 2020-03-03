package com.xiaomi.yp_ui.widget.zoomable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.view.animation.DecelerateInterpolator;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;

public class AnimatedZoomableControllerSupport extends AbstractAnimatedZoomableController {
    private static final Class<?> f = AnimatedZoomableControllerSupport.class;
    private final ValueAnimator g = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});

    public static AnimatedZoomableControllerSupport i() {
        return new AnimatedZoomableControllerSupport(TransformGestureDetector.a());
    }

    public AnimatedZoomableControllerSupport(TransformGestureDetector transformGestureDetector) {
        super(transformGestureDetector);
        this.g.setInterpolator(new DecelerateInterpolator());
    }

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
                AnimatedZoomableControllerSupport.this.a(AnimatedZoomableControllerSupport.this.f(), ((Float) valueAnimator.getAnimatedValue()).floatValue());
                AnimatedZoomableControllerSupport.super.b(AnimatedZoomableControllerSupport.this.f());
            }
        });
        this.g.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                FLog.v(AnimatedZoomableControllerSupport.this.h(), "setTransformAnimated: animation cancelled");
                a();
            }

            public void onAnimationEnd(Animator animator) {
                FLog.v(AnimatedZoomableControllerSupport.this.h(), "setTransformAnimated: animation finished");
                a();
            }

            private void a() {
                if (runnable != null) {
                    runnable.run();
                }
                AnimatedZoomableControllerSupport.this.a(false);
                AnimatedZoomableControllerSupport.this.v().c();
            }
        });
        this.g.start();
    }

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
