package com.xiaomi.smarthome.voice;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import com.xiaomi.smarthome.framework.log.LogUtil;

public class MyFabBehavior extends CoordinatorLayout.Behavior<View> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22779a = "MyFabBehavior";
    private static final Interpolator b = new FastOutSlowInInterpolator();
    private float c;
    /* access modifiers changed from: private */
    public boolean d;

    public MyFabBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i) {
        if (view.getVisibility() == 0 && this.c == 0.0f) {
            this.c = ((float) coordinatorLayout.getHeight()) - view.getY();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("onStartNestedScroll   viewY ");
        int i2 = i & 2;
        sb.append(i2 != 0);
        sb.append("   coordinatorLayout.getHeight()  ");
        sb.append(coordinatorLayout.getHeight());
        sb.append("   child.getY()  ");
        sb.append(view.getY());
        LogUtil.a(f22779a, sb.toString());
        return i2 != 0;
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr) {
        LogUtil.a(f22779a, "onNestedPreScroll   dy: " + i2 + "     child.getVisibility()     " + view.getVisibility() + "   isAnimate:   " + this.d);
        if (i2 >= 0 && !this.d && view.getVisibility() == 0) {
            a(view);
        } else if (i2 < 0 && !this.d && view.getVisibility() == 4) {
            b(view);
        }
    }

    /* access modifiers changed from: private */
    public void a(final View view) {
        LogUtil.a(f22779a, "hide");
        ViewPropertyAnimator duration = view.animate().translationY(this.c).setInterpolator(b).setDuration(200);
        duration.setListener(new Animator.AnimatorListener() {
            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                boolean unused = MyFabBehavior.this.d = true;
            }

            public void onAnimationEnd(Animator animator) {
                view.setVisibility(4);
                boolean unused = MyFabBehavior.this.d = false;
            }

            public void onAnimationCancel(Animator animator) {
                MyFabBehavior.this.b(view);
            }
        });
        duration.start();
    }

    /* access modifiers changed from: private */
    public void b(final View view) {
        LogUtil.a(f22779a, "show");
        ViewPropertyAnimator duration = view.animate().translationY(0.0f).setInterpolator(b).setDuration(200);
        duration.setListener(new Animator.AnimatorListener() {
            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                view.setVisibility(0);
                boolean unused = MyFabBehavior.this.d = true;
            }

            public void onAnimationEnd(Animator animator) {
                boolean unused = MyFabBehavior.this.d = false;
            }

            public void onAnimationCancel(Animator animator) {
                MyFabBehavior.this.a(view);
            }
        });
        duration.start();
    }
}
