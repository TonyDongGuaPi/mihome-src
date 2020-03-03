package com.scwang.smartrefresh.layout.impl;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.Space;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import com.scwang.smartrefresh.layout.api.RefreshContent;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.listener.CoordinatorLayoutListener;
import com.scwang.smartrefresh.layout.util.DesignUtil;
import com.scwang.smartrefresh.layout.util.ScrollBoundaryUtil;
import com.scwang.smartrefresh.layout.util.SmartUtil;
import java.util.Collections;
import java.util.LinkedList;

public class RefreshContentWrapper implements ValueAnimator.AnimatorUpdateListener, RefreshContent, CoordinatorLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    protected View f8796a;
    protected View b;
    protected View c;
    protected View d;
    protected View e;
    protected int f = 0;
    protected boolean g = true;
    protected boolean h = true;
    protected ScrollBoundaryDeciderAdapter i = new ScrollBoundaryDeciderAdapter();

    public RefreshContentWrapper(@NonNull View view) {
        this.c = view;
        this.b = view;
        this.f8796a = view;
    }

    /* access modifiers changed from: protected */
    public void a(View view, RefreshKernel refreshKernel) {
        boolean isInEditMode = this.f8796a.isInEditMode();
        View view2 = null;
        while (true) {
            if (view2 != null && (!(view2 instanceof NestedScrollingParent) || (view2 instanceof NestedScrollingChild))) {
                break;
            }
            view = a(view, view2 == null);
            if (view == view2) {
                break;
            }
            if (!isInEditMode) {
                DesignUtil.a(view, refreshKernel, (CoordinatorLayoutListener) this);
            }
            view2 = view;
        }
        if (view2 != null) {
            this.c = view2;
        }
    }

    public void a(boolean z, boolean z2) {
        this.g = z;
        this.h = z2;
    }

    /* access modifiers changed from: protected */
    public View a(View view, boolean z) {
        LinkedList linkedList = new LinkedList(Collections.singletonList(view));
        View view2 = null;
        while (!linkedList.isEmpty() && view2 == null) {
            View view3 = (View) linkedList.poll();
            if (view3 != null) {
                if ((z || view3 != view) && SmartUtil.c(view3)) {
                    view2 = view3;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                        linkedList.add(viewGroup.getChildAt(i2));
                    }
                }
            }
        }
        return view2 == null ? view : view2;
    }

    /* access modifiers changed from: protected */
    public View a(View view, PointF pointF, View view2) {
        if ((view instanceof ViewGroup) && pointF != null) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            PointF pointF2 = new PointF();
            while (childCount > 0) {
                View childAt = viewGroup.getChildAt(childCount - 1);
                if (!ScrollBoundaryUtil.a(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    childCount--;
                } else if (!(childAt instanceof ViewPager) && SmartUtil.c(childAt)) {
                    return childAt;
                } else {
                    pointF.offset(pointF2.x, pointF2.y);
                    View a2 = a(childAt, pointF, view2);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return a2;
                }
            }
        }
        return view2;
    }

    @NonNull
    public View a() {
        return this.f8796a;
    }

    @NonNull
    public View b() {
        return this.c;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r6, int r7, int r8) {
        /*
            r5 = this;
            r0 = 1
            r1 = -1
            r2 = 0
            r3 = 0
            if (r7 == r1) goto L_0x0021
            android.view.View r4 = r5.b
            android.view.View r7 = r4.findViewById(r7)
            if (r7 == 0) goto L_0x0021
            if (r6 <= 0) goto L_0x0016
            float r4 = (float) r6
            r7.setTranslationY(r4)
            r7 = 1
            goto L_0x0022
        L_0x0016:
            float r4 = r7.getTranslationY()
            int r4 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r4 <= 0) goto L_0x0021
            r7.setTranslationY(r3)
        L_0x0021:
            r7 = 0
        L_0x0022:
            if (r8 == r1) goto L_0x003f
            android.view.View r1 = r5.b
            android.view.View r8 = r1.findViewById(r8)
            if (r8 == 0) goto L_0x003f
            if (r6 >= 0) goto L_0x0034
            float r7 = (float) r6
            r8.setTranslationY(r7)
            r7 = 1
            goto L_0x003f
        L_0x0034:
            float r0 = r8.getTranslationY()
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x003f
            r8.setTranslationY(r3)
        L_0x003f:
            if (r7 != 0) goto L_0x0048
            android.view.View r7 = r5.b
            float r8 = (float) r6
            r7.setTranslationY(r8)
            goto L_0x004d
        L_0x0048:
            android.view.View r7 = r5.b
            r7.setTranslationY(r3)
        L_0x004d:
            android.view.View r7 = r5.d
            if (r7 == 0) goto L_0x005b
            android.view.View r7 = r5.d
            int r8 = java.lang.Math.max(r2, r6)
            float r8 = (float) r8
            r7.setTranslationY(r8)
        L_0x005b:
            android.view.View r7 = r5.e
            if (r7 == 0) goto L_0x0069
            android.view.View r7 = r5.e
            int r6 = java.lang.Math.min(r2, r6)
            float r6 = (float) r6
            r7.setTranslationY(r6)
        L_0x0069:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.impl.RefreshContentWrapper.a(int, int, int):void");
    }

    public boolean c() {
        return this.g && this.i.a(this.f8796a);
    }

    public boolean d() {
        return this.h && this.i.b(this.f8796a);
    }

    public void a(MotionEvent motionEvent) {
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        pointF.offset((float) (-this.f8796a.getLeft()), (float) (-this.f8796a.getTop()));
        if (this.c != this.f8796a) {
            this.c = a(this.f8796a, pointF, this.c);
        }
        if (this.c == this.f8796a) {
            this.i.f8797a = null;
        } else {
            this.i.f8797a = pointF;
        }
    }

    public void a(RefreshKernel refreshKernel, View view, View view2) {
        a(this.f8796a, refreshKernel);
        if (view != null || view2 != null) {
            this.d = view;
            this.e = view2;
            FrameLayout frameLayout = new FrameLayout(this.f8796a.getContext());
            refreshKernel.a().getLayout().removeView(this.f8796a);
            ViewGroup.LayoutParams layoutParams = this.f8796a.getLayoutParams();
            frameLayout.addView(this.f8796a, -1, -1);
            refreshKernel.a().getLayout().addView(frameLayout, layoutParams);
            this.f8796a = frameLayout;
            if (view != null) {
                view.setClickable(true);
                ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                int indexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeView(view);
                layoutParams2.height = SmartUtil.a(view);
                viewGroup.addView(new Space(this.f8796a.getContext()), indexOfChild, layoutParams2);
                frameLayout.addView(view);
            }
            if (view2 != null) {
                view2.setClickable(true);
                ViewGroup.LayoutParams layoutParams3 = view2.getLayoutParams();
                ViewGroup viewGroup2 = (ViewGroup) view2.getParent();
                int indexOfChild2 = viewGroup2.indexOfChild(view2);
                viewGroup2.removeView(view2);
                FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(layoutParams3);
                layoutParams3.height = SmartUtil.a(view2);
                viewGroup2.addView(new Space(this.f8796a.getContext()), indexOfChild2, layoutParams3);
                layoutParams4.gravity = 80;
                frameLayout.addView(view2, layoutParams4);
            }
        }
    }

    public void a(ScrollBoundaryDecider scrollBoundaryDecider) {
        if (scrollBoundaryDecider instanceof ScrollBoundaryDeciderAdapter) {
            this.i = (ScrollBoundaryDeciderAdapter) scrollBoundaryDecider;
        } else {
            this.i.b = scrollBoundaryDecider;
        }
    }

    public void a(boolean z) {
        this.i.c = z;
    }

    public ValueAnimator.AnimatorUpdateListener a(int i2) {
        if (this.c == null || i2 == 0) {
            return null;
        }
        if ((i2 >= 0 || !ScrollBoundaryUtil.b(this.c)) && (i2 <= 0 || !ScrollBoundaryUtil.a(this.c))) {
            return null;
        }
        this.f = i2;
        return this;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        try {
            if (this.c instanceof AbsListView) {
                SmartUtil.a((AbsListView) this.c, intValue - this.f);
            } else {
                this.c.scrollBy(0, intValue - this.f);
            }
        } catch (Throwable unused) {
        }
        this.f = intValue;
    }
}
