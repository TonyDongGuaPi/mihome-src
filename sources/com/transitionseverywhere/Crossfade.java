package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.utils.RectEvaluator;
import com.transitionseverywhere.utils.ViewGroupOverlayUtils;
import java.util.Map;

@TargetApi(14)
public class Crossfade extends Transition {
    private static final String M = "Crossfade";
    private static final String N = "android:crossfade:bitmap";
    private static final String O = "android:crossfade:drawable";
    private static final String P = "android:crossfade:bounds";
    private static RectEvaluator Q = null;

    /* renamed from: a  reason: collision with root package name */
    public static final int f9454a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 0;
    public static final int e = 1;
    /* access modifiers changed from: private */
    public int R = 1;
    private int S = 1;

    public Crossfade a(int i) {
        if (i >= 0 && i <= 2) {
            this.R = i;
        }
        return this;
    }

    public int b() {
        return this.R;
    }

    public Crossfade b(int i) {
        if (i >= 0 && i <= 1) {
            this.S = i;
        }
        return this;
    }

    public int c() {
        return this.S;
    }

    public Animator a(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ObjectAnimator ofInt;
        TransitionValues transitionValues3 = transitionValues;
        TransitionValues transitionValues4 = transitionValues2;
        ObjectAnimator objectAnimator = null;
        if (transitionValues3 == null || transitionValues4 == null || Build.VERSION.SDK_INT < 14) {
            return null;
        }
        if (Q == null) {
            Q = new RectEvaluator();
        }
        boolean z = this.R != 1;
        final View view = transitionValues4.f9482a;
        Map<String, Object> map = transitionValues3.b;
        Map<String, Object> map2 = transitionValues4.b;
        Rect rect = (Rect) map.get(P);
        Rect rect2 = (Rect) map2.get(P);
        Bitmap bitmap = (Bitmap) map.get(N);
        Bitmap bitmap2 = (Bitmap) map2.get(N);
        final BitmapDrawable bitmapDrawable = (BitmapDrawable) map.get(O);
        BitmapDrawable bitmapDrawable2 = (BitmapDrawable) map2.get(O);
        if (bitmapDrawable == null || bitmapDrawable2 == null || bitmap.sameAs(bitmap2)) {
            return null;
        }
        ViewGroupOverlayUtils.a(z, view, this.R, bitmapDrawable, bitmapDrawable2);
        if (this.R == 2) {
            ofInt = ObjectAnimator.ofInt(bitmapDrawable, "alpha", new int[]{255, 0, 0});
        } else {
            ofInt = ObjectAnimator.ofInt(bitmapDrawable, "alpha", new int[]{0});
        }
        ObjectAnimator objectAnimator2 = ofInt;
        objectAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.invalidate(bitmapDrawable.getBounds());
            }
        });
        if (this.R == 2) {
            objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 0.0f, 1.0f});
        } else if (this.R == 0) {
            objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 1.0f});
        }
        ObjectAnimator objectAnimator3 = objectAnimator;
        final boolean z2 = z;
        final View view2 = view;
        final BitmapDrawable bitmapDrawable3 = bitmapDrawable;
        AnonymousClass2 r11 = r0;
        final BitmapDrawable bitmapDrawable4 = bitmapDrawable2;
        AnonymousClass2 r0 = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ViewGroupOverlayUtils.b(z2, view2, Crossfade.this.R, bitmapDrawable3, bitmapDrawable4);
            }
        };
        objectAnimator2.addListener(r11);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{objectAnimator2});
        if (objectAnimator3 != null) {
            animatorSet.playTogether(new Animator[]{objectAnimator3});
        }
        if (this.S == 1 && !rect.equals(rect2)) {
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofObject(bitmapDrawable, "bounds", Q, new Object[]{rect, rect2})});
            if (this.S == 1) {
                animatorSet.playTogether(new Animator[]{ObjectAnimator.ofObject(bitmapDrawable2, "bounds", Q, new Object[]{rect, rect2})});
            }
        }
        return animatorSet;
    }

    private void d(TransitionValues transitionValues) {
        if (Build.VERSION.SDK_INT >= 14) {
            View view = transitionValues.f9482a;
            Rect rect = new Rect(0, 0, view.getWidth(), view.getHeight());
            if (this.R != 1) {
                rect.offset(view.getLeft(), view.getTop());
            }
            transitionValues.b.put(P, rect);
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            if (view instanceof TextureView) {
                createBitmap = ((TextureView) view).getBitmap();
            } else {
                view.draw(new Canvas(createBitmap));
            }
            transitionValues.b.put(N, createBitmap);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(view.getResources(), createBitmap);
            bitmapDrawable.setBounds(rect);
            transitionValues.b.put(O, bitmapDrawable);
        }
    }

    public void a(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public void b(TransitionValues transitionValues) {
        d(transitionValues);
    }
}
