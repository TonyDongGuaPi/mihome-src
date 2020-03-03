package com.xiaomi.dragdrop;

import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;

abstract class BaseDraggableItemDecorator extends RecyclerView.ItemDecoration {
    private static final int c = 2;
    private static final int d = 20;

    /* renamed from: a  reason: collision with root package name */
    protected final RecyclerView f10091a;
    protected RecyclerView.ViewHolder b;
    private int e = 200;
    private final int f;
    private Interpolator g;

    public BaseDraggableItemDecorator(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        this.f10091a = recyclerView;
        this.b = viewHolder;
        this.f = (int) ((recyclerView.getResources().getDisplayMetrics().density * 2.0f) + 0.5f);
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(Interpolator interpolator) {
        this.g = interpolator;
    }

    /* access modifiers changed from: protected */
    public void a(View view, boolean z) {
        int translationX = (int) ViewCompat.getTranslationX(view);
        int translationY = (int) ViewCompat.getTranslationY(view);
        int width = view.getWidth() / 2;
        int height = view.getHeight() / 2;
        float abs = width > 0 ? Math.abs(((float) translationX) / ((float) width)) : 0.0f;
        float abs2 = height > 0 ? Math.abs(((float) translationY) / ((float) height)) : 0.0f;
        float min = 1.0f - Math.min(abs, 1.0f);
        float min2 = 1.0f - Math.min(abs2, 1.0f);
        int max = Math.max((int) ((((float) this.e) * (1.0f - (min * min))) + 0.5f), (int) ((((float) this.e) * (1.0f - (min2 * min2))) + 0.5f));
        int max2 = Math.max(Math.abs(translationX), Math.abs(translationY));
        if (!a() || !z || max <= 20 || max2 <= this.f) {
            ViewCompat.setTranslationX(view, 0.0f);
            ViewCompat.setTranslationY(view, 0.0f);
            return;
        }
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(view);
        animate.cancel();
        animate.setDuration((long) max);
        animate.setInterpolator(this.g);
        animate.translationX(0.0f);
        animate.translationY(0.0f);
        animate.setListener(new ViewPropertyAnimatorListener() {
            public void onAnimationCancel(View view) {
            }

            public void onAnimationStart(View view) {
            }

            public void onAnimationEnd(View view) {
                animate.setListener((ViewPropertyAnimatorListener) null);
                ViewCompat.setTranslationX(view, 0.0f);
                ViewCompat.setTranslationY(view, 0.0f);
                if (view.getParent() instanceof RecyclerView) {
                    ViewCompat.postInvalidateOnAnimation((RecyclerView) view.getParent());
                }
            }
        });
        animate.start();
    }

    protected static void a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.endAnimation(viewHolder);
        }
        ViewCompat.setTranslationX(viewHolder.itemView, f2);
        ViewCompat.setTranslationY(viewHolder.itemView, f3);
    }

    private static boolean a() {
        return Build.VERSION.SDK_INT >= 11;
    }
}
