package com.xiaomi.smarthome.framework.page.rndebug;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class SlideRecyclerView extends RecyclerView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16967a = "SlideRecyclerView";
    private static final int b = -1;
    private static final int c = -1;
    private static final int d = 600;
    private VelocityTracker e;
    private int f;
    private Rect g;
    private Scroller h;
    private float i;
    private float j;
    private float k;
    private boolean l;
    private ViewGroup m;
    private int n;
    private int o;

    public SlideRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlideRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlideRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f = ViewConfiguration.get(context).getScaledTouchSlop();
        this.h = new Scroller(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0061, code lost:
        if (java.lang.Math.abs(r0 - r7.j) > java.lang.Math.abs(((float) r1) - r7.k)) goto L_0x0063;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            float r0 = r8.getX()
            int r0 = (int) r0
            float r1 = r8.getY()
            int r1 = (int) r1
            r7.a(r8)
            int r2 = r8.getAction()
            r3 = 1
            switch(r2) {
                case 0: goto L_0x006a;
                case 1: goto L_0x0066;
                case 2: goto L_0x0017;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x00c9
        L_0x0017:
            android.view.VelocityTracker r2 = r7.e
            r4 = 1000(0x3e8, float:1.401E-42)
            r2.computeCurrentVelocity(r4)
            android.view.VelocityTracker r2 = r7.e
            float r2 = r2.getXVelocity()
            android.view.VelocityTracker r4 = r7.e
            float r4 = r4.getYVelocity()
            float r5 = java.lang.Math.abs(r2)
            r6 = 1142292480(0x44160000, float:600.0)
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L_0x0040
            float r2 = java.lang.Math.abs(r2)
            float r4 = java.lang.Math.abs(r4)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 > 0) goto L_0x0063
        L_0x0040:
            float r0 = (float) r0
            float r2 = r7.j
            float r2 = r0 - r2
            float r2 = java.lang.Math.abs(r2)
            int r4 = r7.f
            float r4 = (float) r4
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 < 0) goto L_0x00c9
            float r2 = r7.j
            float r0 = r0 - r2
            float r0 = java.lang.Math.abs(r0)
            float r1 = (float) r1
            float r2 = r7.k
            float r1 = r1 - r2
            float r1 = java.lang.Math.abs(r1)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x00c9
        L_0x0063:
            r7.l = r3
            return r3
        L_0x0066:
            r7.a()
            goto L_0x00c9
        L_0x006a:
            android.widget.Scroller r2 = r7.h
            boolean r2 = r2.isFinished()
            if (r2 != 0) goto L_0x0077
            android.widget.Scroller r2 = r7.h
            r2.abortAnimation()
        L_0x0077:
            float r2 = (float) r0
            r7.i = r2
            r7.j = r2
            float r2 = (float) r1
            r7.k = r2
            int r0 = r7.pointToPosition(r0, r1)
            r7.n = r0
            int r0 = r7.n
            r1 = -1
            if (r0 == r1) goto L_0x00c9
            android.view.ViewGroup r0 = r7.m
            int r2 = r7.n
            android.support.v7.widget.RecyclerView$LayoutManager r4 = r7.getLayoutManager()
            android.support.v7.widget.LinearLayoutManager r4 = (android.support.v7.widget.LinearLayoutManager) r4
            int r4 = r4.findFirstVisibleItemPosition()
            int r2 = r2 - r4
            android.view.View r2 = r7.getChildAt(r2)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r7.m = r2
            if (r0 == 0) goto L_0x00b1
            android.view.ViewGroup r2 = r7.m
            if (r2 == r0) goto L_0x00b1
            int r2 = r0.getScrollX()
            if (r2 == 0) goto L_0x00b1
            r2 = 0
            r0.scrollTo(r2, r2)
        L_0x00b1:
            android.view.ViewGroup r0 = r7.m
            int r0 = r0.getChildCount()
            r2 = 2
            if (r0 != r2) goto L_0x00c7
            android.view.ViewGroup r0 = r7.m
            android.view.View r0 = r0.getChildAt(r3)
            int r0 = r0.getWidth()
            r7.o = r0
            goto L_0x00c9
        L_0x00c7:
            r7.o = r1
        L_0x00c9:
            boolean r8 = super.onInterceptTouchEvent(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.page.rndebug.SlideRecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.l || this.n == -1) {
            closeMenu();
            a();
            return super.onTouchEvent(motionEvent);
        }
        float x = motionEvent.getX();
        a(motionEvent);
        switch (motionEvent.getAction()) {
            case 1:
                if (this.o != -1) {
                    int scrollX = this.m.getScrollX();
                    this.e.computeCurrentVelocity(1000);
                    if (this.e.getXVelocity() < -600.0f) {
                        this.h.startScroll(scrollX, 0, this.o - scrollX, 0, Math.abs(this.o - scrollX));
                    } else if (this.e.getXVelocity() >= 600.0f) {
                        this.h.startScroll(scrollX, 0, -scrollX, 0, Math.abs(scrollX));
                    } else if (scrollX >= this.o / 2) {
                        this.h.startScroll(scrollX, 0, this.o - scrollX, 0, Math.abs(this.o - scrollX));
                    } else {
                        this.h.startScroll(scrollX, 0, -scrollX, 0, Math.abs(scrollX));
                    }
                    invalidate();
                }
                this.o = -1;
                this.l = false;
                this.n = -1;
                a();
                return true;
            case 2:
                if (this.o == -1) {
                    return true;
                }
                float f2 = this.i - x;
                if (((float) this.m.getScrollX()) + f2 <= ((float) this.o) && ((float) this.m.getScrollX()) + f2 > 0.0f) {
                    this.m.scrollBy((int) f2, 0);
                }
                this.i = x;
                return true;
            default:
                return true;
        }
    }

    private void a() {
        if (this.e != null) {
            this.e.clear();
            this.e.recycle();
            this.e = null;
        }
    }

    private void a(MotionEvent motionEvent) {
        if (this.e == null) {
            this.e = VelocityTracker.obtain();
        }
        this.e.addMovement(motionEvent);
    }

    public int pointToPosition(int i2, int i3) {
        int findFirstVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        Rect rect = this.g;
        if (rect == null) {
            this.g = new Rect();
            rect = this.g;
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() == 0) {
                childAt.getHitRect(rect);
                if (rect.contains(i2, i3)) {
                    return findFirstVisibleItemPosition + childCount;
                }
            }
        }
        return -1;
    }

    public void computeScroll() {
        if (this.h.computeScrollOffset()) {
            this.m.scrollTo(this.h.getCurrX(), this.h.getCurrY());
            invalidate();
        }
    }

    public void closeMenu() {
        if (this.m != null && this.m.getScrollX() != 0) {
            this.m.scrollTo(0, 0);
        }
    }
}
