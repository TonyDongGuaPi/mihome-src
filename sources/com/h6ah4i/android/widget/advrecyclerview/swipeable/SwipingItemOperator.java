package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;

class SwipingItemOperator {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5729a = "SwipingItemOperator";
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final float e = 0.15f;
    private static final int f = 48;
    private static final Interpolator g = new RubberBandInterpolator(e);
    private RecyclerViewSwipeManager h;
    private RecyclerView.ViewHolder i;
    private View j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o = this.j.getWidth();
    private final int p = this.j.getHeight();
    private float q = a(this.o);
    private float r = a(this.p);
    private int s;
    private int t;
    private float u;
    private int v;
    private int w;
    private final boolean x;

    private static float a(int i2) {
        if (i2 != 0) {
            return 1.0f / ((float) i2);
        }
        return 0.0f;
    }

    public SwipingItemOperator(RecyclerViewSwipeManager recyclerViewSwipeManager, RecyclerView.ViewHolder viewHolder, int i2, boolean z) {
        this.h = recyclerViewSwipeManager;
        this.i = viewHolder;
        this.k = SwipeReactionUtils.a(i2);
        this.l = SwipeReactionUtils.b(i2);
        this.m = SwipeReactionUtils.c(i2);
        this.n = SwipeReactionUtils.d(i2);
        this.x = z;
        this.j = ((SwipeableItemViewHolder) viewHolder).k();
    }

    public void a() {
        int i2 = (int) (this.i.itemView.getResources().getDisplayMetrics().density * 48.0f);
        int max = Math.max(0, this.o - i2);
        int max2 = Math.max(0, this.p - i2);
        this.v = b(this.h.c(this.i), -max, max);
        this.w = b(this.h.d(this.i), -max2, max2);
    }

    public void b() {
        this.h = null;
        this.i = null;
        this.s = 0;
        this.t = 0;
        this.o = 0;
        this.q = 0.0f;
        this.r = 0.0f;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.u = 0.0f;
        this.v = 0;
        this.w = 0;
        this.j = null;
    }

    public void a(int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        if (this.s != i3 || this.t != i4) {
            this.s = i3;
            this.t = i4;
            if (this.x) {
                i5 = this.s;
                i6 = this.v;
            } else {
                i5 = this.t;
                i6 = this.w;
            }
            int i8 = i5 + i6;
            int i9 = this.x ? this.o : this.p;
            float f2 = this.x ? this.q : this.r;
            if (this.x) {
                i7 = i8 > 0 ? this.m : this.k;
            } else if (i8 > 0) {
                i7 = this.n;
            } else {
                i7 = this.l;
            }
            float f3 = 0.0f;
            switch (i7) {
                case 1:
                    f3 = Math.signum((float) i8) * g.getInterpolation(((float) Math.min(Math.abs(i8), i9)) * f2);
                    break;
                case 2:
                    f3 = Math.min(Math.max(((float) i8) * f2, -1.0f), 1.0f);
                    break;
            }
            this.h.a(this.i, i2, this.u, f3, this.x, false, true);
            this.u = f3;
        }
    }

    private static int b(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }
}
