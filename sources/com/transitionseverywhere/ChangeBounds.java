package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.utils.AnimatorUtils;
import com.transitionseverywhere.utils.PointFProperty;
import com.transitionseverywhere.utils.RectEvaluator;
import com.transitionseverywhere.utils.ViewGroupUtils;
import com.transitionseverywhere.utils.ViewOverlayUtils;
import com.transitionseverywhere.utils.ViewUtils;
import java.util.Map;

@TargetApi(14)
public class ChangeBounds extends Transition {
    private static final String M = "android:changeBounds:parent";
    private static final String N = "android:changeBounds:windowX";
    private static final String O = "android:changeBounds:windowY";
    private static final String[] P = {d, e, M, N, O};
    private static final PointFProperty<Drawable> Q;
    private static final PointFProperty<ViewBounds> R;
    private static final PointFProperty<ViewBounds> S;
    private static final PointFProperty<View> T;
    private static final PointFProperty<View> U;
    private static final PointFProperty<View> V;
    private static final String W = "ChangeBounds";
    private static RectEvaluator X = null;
    private static final String d = "android:changeBounds:bounds";
    private static final String e = "android:changeBounds:clip";

    /* renamed from: a  reason: collision with root package name */
    int[] f9433a = new int[2];
    boolean b = false;
    boolean c = false;

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            Q = new PointFProperty<Drawable>() {

                /* renamed from: a  reason: collision with root package name */
                private Rect f9434a = new Rect();

                /* renamed from: a */
                public void set(Drawable drawable, PointF pointF) {
                    drawable.copyBounds(this.f9434a);
                    this.f9434a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
                    drawable.setBounds(this.f9434a);
                }

                /* renamed from: a */
                public PointF get(Drawable drawable) {
                    drawable.copyBounds(this.f9434a);
                    return new PointF((float) this.f9434a.left, (float) this.f9434a.top);
                }
            };
            R = new PointFProperty<ViewBounds>() {
                /* renamed from: a */
                public void set(ViewBounds viewBounds, PointF pointF) {
                    viewBounds.a(pointF);
                }
            };
            S = new PointFProperty<ViewBounds>() {
                /* renamed from: a */
                public void set(ViewBounds viewBounds, PointF pointF) {
                    viewBounds.b(pointF);
                }
            };
            T = new PointFProperty<View>() {
                /* renamed from: a */
                public void set(View view, PointF pointF) {
                    ViewUtils.a(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
                }
            };
            U = new PointFProperty<View>() {
                /* renamed from: a */
                public void set(View view, PointF pointF) {
                    ViewUtils.a(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
                }
            };
            V = new PointFProperty<View>() {
                /* renamed from: a */
                public void set(View view, PointF pointF) {
                    int round = Math.round(pointF.x);
                    int round2 = Math.round(pointF.y);
                    ViewUtils.a(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
                }
            };
            return;
        }
        Q = null;
        R = null;
        S = null;
        T = null;
        U = null;
        V = null;
    }

    public ChangeBounds() {
    }

    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ChangeBounds);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.ChangeBounds_resizeClip, false);
        obtainStyledAttributes.recycle();
        a(z);
    }

    public String[] a() {
        return P;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public boolean b() {
        return this.b;
    }

    public void b(boolean z) {
        this.c = z;
    }

    private void d(TransitionValues transitionValues) {
        View view = transitionValues.f9482a;
        if (ViewUtils.a(view, false) || view.getWidth() != 0 || view.getHeight() != 0) {
            transitionValues.b.put(d, new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            transitionValues.b.put(M, transitionValues.f9482a.getParent());
            if (this.c) {
                transitionValues.f9482a.getLocationInWindow(this.f9433a);
                transitionValues.b.put(N, Integer.valueOf(this.f9433a[0]));
                transitionValues.b.put(O, Integer.valueOf(this.f9433a[1]));
            }
            if (this.b) {
                transitionValues.b.put(e, ViewUtils.b(view));
            }
        }
    }

    public void a(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public void b(TransitionValues transitionValues) {
        d(transitionValues);
    }

    private boolean a(View view, View view2) {
        if (!this.c) {
            return true;
        }
        TransitionValues d2 = d(view, true);
        if (d2 == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == d2.f9482a) {
            return true;
        }
        return false;
    }

    public Animator a(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        boolean z;
        View view;
        Animator animator;
        int i2;
        int i3;
        Animator animator2;
        int i4;
        int i5;
        int i6;
        View view2;
        ObjectAnimator objectAnimator;
        ViewGroup viewGroup2 = viewGroup;
        TransitionValues transitionValues3 = transitionValues;
        TransitionValues transitionValues4 = transitionValues2;
        if (transitionValues3 == null || transitionValues4 == null) {
            return null;
        }
        if (X == null) {
            X = new RectEvaluator();
        }
        Map<String, Object> map = transitionValues3.b;
        Map<String, Object> map2 = transitionValues4.b;
        ViewGroup viewGroup3 = (ViewGroup) map.get(M);
        ViewGroup viewGroup4 = (ViewGroup) map2.get(M);
        if (viewGroup3 == null || viewGroup4 == null) {
            return null;
        }
        View view3 = transitionValues4.f9482a;
        if (a(viewGroup3, viewGroup4)) {
            Rect rect = (Rect) transitionValues3.b.get(d);
            Rect rect2 = (Rect) transitionValues4.b.get(d);
            int i7 = rect.left;
            int i8 = rect2.left;
            int i9 = rect.top;
            int i10 = rect2.top;
            int i11 = rect.right;
            int i12 = rect2.right;
            int i13 = rect.bottom;
            int i14 = rect2.bottom;
            int i15 = i11 - i7;
            int i16 = i13 - i9;
            int i17 = i12 - i8;
            int i18 = i14 - i10;
            View view4 = view3;
            Rect rect3 = (Rect) transitionValues3.b.get(e);
            Rect rect4 = (Rect) transitionValues4.b.get(e);
            if ((i15 == 0 || i16 == 0) && (i17 == 0 || i18 == 0)) {
                i = 0;
            } else {
                i = (i7 == i8 && i9 == i10) ? 0 : 1;
                if (!(i11 == i12 && i13 == i14)) {
                    i++;
                }
            }
            if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
                i++;
            }
            if (i > 0) {
                int i19 = i14;
                if (!this.b || (rect3 == null && rect4 == null)) {
                    int i20 = i15;
                    int i21 = i12;
                    int i22 = i10;
                    int i23 = i8;
                    View view5 = view4;
                    int i24 = i19;
                    ViewUtils.a(view5, i7, i9, i11, i13);
                    if (i != 2) {
                        view = view5;
                        int i25 = i22;
                        int i26 = i23;
                        z = true;
                        if (i7 == i26 && i9 == i25) {
                            animator = AnimatorUtils.a(view, T, s(), (float) i11, (float) i13, (float) i21, (float) i24);
                        } else {
                            animator = AnimatorUtils.a(view, U, s(), (float) i7, (float) i9, (float) i26, (float) i25);
                        }
                    } else if (i20 == i17 && i16 == i18) {
                        view = view5;
                        z = true;
                        animator = AnimatorUtils.a(view5, V, s(), (float) i7, (float) i9, (float) i23, (float) i22);
                    } else {
                        view = view5;
                        z = true;
                        ViewBounds viewBounds = new ViewBounds(view);
                        Animator a2 = AnimatorUtils.a(viewBounds, R, s(), (float) i7, (float) i9, (float) i23, (float) i22);
                        Animator a3 = AnimatorUtils.a(viewBounds, S, s(), (float) i11, (float) i13, (float) i21, (float) i24);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(new Animator[]{a2, a3});
                        animatorSet.addListener(viewBounds);
                        animator = animatorSet;
                    }
                } else {
                    View view6 = view4;
                    ViewUtils.a(view6, i7, i9, Math.max(i15, i17) + i7, Math.max(i16, i18) + i9);
                    if (i7 == i8 && i9 == i10) {
                        i5 = i15;
                        i4 = i12;
                        i3 = i10;
                        i2 = i8;
                        animator2 = null;
                    } else {
                        float f = (float) i7;
                        float f2 = (float) i9;
                        i5 = i15;
                        float f3 = f;
                        i4 = i12;
                        i3 = i10;
                        i2 = i8;
                        animator2 = AnimatorUtils.a(view6, V, s(), f3, f2, (float) i8, (float) i10);
                    }
                    if (rect3 == null) {
                        i6 = 0;
                        rect3 = new Rect(0, 0, i5, i16);
                    } else {
                        i6 = 0;
                    }
                    Rect rect5 = rect4 == null ? new Rect(i6, i6, i17, i18) : rect4;
                    if (!rect3.equals(rect5)) {
                        ViewUtils.a(view6, rect3);
                        Property<View, Rect> property = ChangeClipBounds.f9439a;
                        RectEvaluator rectEvaluator = X;
                        Rect[] rectArr = new Rect[2];
                        rectArr[i6] = rect3;
                        rectArr[1] = rect5;
                        objectAnimator = ObjectAnimator.ofObject(view6, property, rectEvaluator, rectArr);
                        int i27 = i4;
                        final View view7 = view6;
                        final Rect rect6 = rect4;
                        View view8 = view6;
                        final int i28 = i2;
                        final int i29 = i3;
                        final int i30 = i27;
                        view2 = view8;
                        final int i31 = i19;
                        objectAnimator.addListener(new AnimatorListenerAdapter() {
                            private boolean h;

                            public void onAnimationCancel(Animator animator) {
                                this.h = true;
                            }

                            public void onAnimationEnd(Animator animator) {
                                if (!this.h) {
                                    ViewUtils.a(view7, rect6);
                                    ViewUtils.a(view7, i28, i29, i30, i31);
                                }
                            }
                        });
                    } else {
                        view2 = view6;
                        objectAnimator = null;
                    }
                    animator = TransitionUtils.a(animator2, objectAnimator);
                    view = view2;
                    z = true;
                }
                if (view.getParent() instanceof ViewGroup) {
                    final ViewGroup viewGroup5 = (ViewGroup) view.getParent();
                    ViewGroupUtils.a(viewGroup5, z);
                    a((Transition.TransitionListener) new Transition.TransitionListenerAdapter() {

                        /* renamed from: a  reason: collision with root package name */
                        boolean f9436a = false;

                        public void a(Transition transition) {
                            ViewGroupUtils.a(viewGroup5, false);
                            this.f9436a = true;
                        }

                        public void b(Transition transition) {
                            if (!this.f9436a) {
                                ViewGroupUtils.a(viewGroup5, false);
                            }
                        }

                        public void c(Transition transition) {
                            ViewGroupUtils.a(viewGroup5, false);
                        }

                        public void d(Transition transition) {
                            ViewGroupUtils.a(viewGroup5, true);
                        }
                    });
                }
                return animator;
            }
            return null;
        }
        final View view9 = view3;
        viewGroup2.getLocationInWindow(this.f9433a);
        int intValue = ((Integer) transitionValues3.b.get(N)).intValue() - this.f9433a[0];
        int intValue2 = ((Integer) transitionValues3.b.get(O)).intValue() - this.f9433a[1];
        int intValue3 = ((Integer) transitionValues4.b.get(N)).intValue() - this.f9433a[0];
        int intValue4 = ((Integer) transitionValues4.b.get(O)).intValue() - this.f9433a[1];
        if (intValue == intValue3 && intValue2 == intValue4) {
            return null;
        }
        int width = view9.getWidth();
        int height = view9.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        view9.draw(new Canvas(createBitmap));
        BitmapDrawable bitmapDrawable = new BitmapDrawable(viewGroup.getContext().getResources(), createBitmap);
        bitmapDrawable.setBounds(intValue, intValue2, width + intValue, height + intValue2);
        Animator a4 = AnimatorUtils.a(bitmapDrawable, Q, s(), (float) intValue, (float) intValue2, (float) intValue3, (float) intValue4);
        if (a4 != null) {
            final float alpha = view9.getAlpha();
            view9.setAlpha(0.0f);
            ViewOverlayUtils.a(viewGroup2, bitmapDrawable);
            final ViewGroup viewGroup6 = viewGroup;
            final BitmapDrawable bitmapDrawable2 = bitmapDrawable;
            a4.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ViewOverlayUtils.b(viewGroup6, bitmapDrawable2);
                    view9.setAlpha(alpha);
                }
            });
        }
        return a4;
    }

    private static class ViewBounds extends AnimatorListenerAdapter {

        /* renamed from: a  reason: collision with root package name */
        private int f9438a;
        private int b;
        private int c;
        private int d;
        private boolean e;
        private boolean f;
        private View g;

        public ViewBounds(View view) {
            this.g = view;
        }

        public void a(PointF pointF) {
            this.f9438a = Math.round(pointF.x);
            this.b = Math.round(pointF.y);
            this.e = true;
            if (this.f) {
                a();
            }
        }

        public void b(PointF pointF) {
            this.c = Math.round(pointF.x);
            this.d = Math.round(pointF.y);
            this.f = true;
            if (this.e) {
                a();
            }
        }

        private void a() {
            ViewUtils.a(this.g, this.f9438a, this.b, this.c, this.d);
            this.e = false;
            this.f = false;
        }
    }
}
