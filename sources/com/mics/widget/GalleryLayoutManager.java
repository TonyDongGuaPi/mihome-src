package com.mics.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.mics.core.MiCS;

public class GalleryLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7810a = 0;
    public static final int b = 1;
    private static final int g = -1;
    private static final int h = 1;
    int c = -1;
    View d;
    RecyclerView e;
    /* access modifiers changed from: private */
    public final String f = getClass().getSimpleName();
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private State l;
    /* access modifiers changed from: private */
    public PagerSnapHelper m = new PagerSnapHelper();
    private InnerScrollListener n = new InnerScrollListener();
    /* access modifiers changed from: private */
    public boolean o = false;
    private int p = 0;
    private OrientationHelper q;
    private OrientationHelper r;
    private ItemTransformer s;
    /* access modifiers changed from: private */
    public OnItemSelectedListener t;

    public interface ItemTransformer {
        void a(GalleryLayoutManager galleryLayoutManager, View view, float f);
    }

    public interface OnItemSelectedListener {
        void a(RecyclerView recyclerView, View view, int i);
    }

    public GalleryLayoutManager(int i2) {
        this.p = i2;
    }

    public int a() {
        return this.p;
    }

    public int b() {
        return this.c;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.p == 1) {
            return new LayoutParams(-1, -2);
        }
        return new LayoutParams(-2, -1);
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            e();
            detachAndScrapAttachedViews(recycler);
        } else if (!state.isPreLayout()) {
            if (state.getItemCount() == 0 || state.didStructureChange()) {
                if (getChildCount() == 0 || state.didStructureChange()) {
                    e();
                }
                this.k = Math.min(Math.max(0, this.k), getItemCount() - 1);
                detachAndScrapAttachedViews(recycler);
                a(recycler, state, 0);
            }
        }
    }

    private void e() {
        if (MiCS.e()) {
            Log.d(this.f, "reset: ");
        }
        if (this.l != null) {
            this.l.f7813a.clear();
        }
        if (this.c != -1) {
            this.k = this.c;
        }
        this.k = Math.min(Math.max(0, this.k), getItemCount() - 1);
        this.i = this.k;
        this.j = this.k;
        this.c = -1;
        if (this.d != null) {
            this.d.setSelected(false);
            this.d = null;
        }
    }

    private void a(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (this.p == 0) {
            a(recycler, state);
        } else {
            b(recycler, state);
        }
        if (MiCS.e()) {
            String str = this.f;
            Log.d(str, "firstFillCover finish:first: " + this.i + ",last:" + this.j);
        }
        if (this.s != null) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                this.s.a(this, childAt, a(childAt, (float) i2));
            }
        }
        this.n.onScrolled(this.e, 0, 0);
    }

    private void a(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int startAfterPadding = d().getStartAfterPadding();
        int endAfterPadding = d().getEndAfterPadding();
        int i2 = this.k;
        Rect rect = new Rect();
        int g2 = g();
        View viewForPosition = recycler.getViewForPosition(this.k);
        addView(viewForPosition, 0);
        measureChildWithMargins(viewForPosition, 0, 0);
        int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
        int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
        int paddingTop = (int) (((float) getPaddingTop()) + (((float) (g2 - decoratedMeasuredHeight)) / 2.0f));
        int paddingLeft = (int) (((float) getPaddingLeft()) + (((float) (f() - decoratedMeasuredWidth)) / 2.0f));
        rect.set(paddingLeft, paddingTop, decoratedMeasuredWidth + paddingLeft, decoratedMeasuredHeight + paddingTop);
        layoutDecorated(viewForPosition, rect.left, rect.top, rect.right, rect.bottom);
        if (c().f7813a.get(i2) == null) {
            c().f7813a.put(i2, rect);
        } else {
            c().f7813a.get(i2).set(rect);
        }
        this.j = i2;
        this.i = i2;
        int decoratedLeft = getDecoratedLeft(viewForPosition);
        int decoratedRight = getDecoratedRight(viewForPosition);
        a(recycler, this.k - 1, decoratedLeft, startAfterPadding);
        b(recycler, this.k + 1, decoratedRight, endAfterPadding);
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i3) {
        super.onItemsRemoved(recyclerView, i2, i3);
    }

    private void b(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int startAfterPadding = d().getStartAfterPadding();
        int endAfterPadding = d().getEndAfterPadding();
        int i2 = this.k;
        Rect rect = new Rect();
        int f2 = f();
        View viewForPosition = recycler.getViewForPosition(this.k);
        addView(viewForPosition, 0);
        measureChildWithMargins(viewForPosition, 0, 0);
        int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
        int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
        int paddingLeft = (int) (((float) getPaddingLeft()) + (((float) (f2 - decoratedMeasuredWidth)) / 2.0f));
        int paddingTop = (int) (((float) getPaddingTop()) + (((float) (g() - decoratedMeasuredHeight)) / 2.0f));
        rect.set(paddingLeft, paddingTop, decoratedMeasuredWidth + paddingLeft, decoratedMeasuredHeight + paddingTop);
        layoutDecorated(viewForPosition, rect.left, rect.top, rect.right, rect.bottom);
        if (c().f7813a.get(i2) == null) {
            c().f7813a.put(i2, rect);
        } else {
            c().f7813a.get(i2).set(rect);
        }
        this.j = i2;
        this.i = i2;
        int decoratedTop = getDecoratedTop(viewForPosition);
        int decoratedBottom = getDecoratedBottom(viewForPosition);
        c(recycler, this.k - 1, decoratedTop, startAfterPadding);
        d(recycler, this.k + 1, decoratedBottom, endAfterPadding);
    }

    private void a(RecyclerView.Recycler recycler, int i2, int i3, int i4) {
        Rect rect = new Rect();
        int g2 = g();
        while (i2 >= 0 && i3 > i4) {
            View viewForPosition = recycler.getViewForPosition(i2);
            addView(viewForPosition, 0);
            measureChildWithMargins(viewForPosition, 0, 0);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
            int paddingTop = (int) (((float) getPaddingTop()) + (((float) (g2 - decoratedMeasuredHeight)) / 2.0f));
            rect.set(i3 - decoratedMeasuredWidth, paddingTop, i3, decoratedMeasuredHeight + paddingTop);
            layoutDecorated(viewForPosition, rect.left, rect.top, rect.right, rect.bottom);
            i3 = rect.left;
            this.i = i2;
            if (c().f7813a.get(i2) == null) {
                c().f7813a.put(i2, rect);
            } else {
                c().f7813a.get(i2).set(rect);
            }
            i2--;
        }
    }

    private void b(RecyclerView.Recycler recycler, int i2, int i3, int i4) {
        Rect rect = new Rect();
        int g2 = g();
        while (i2 < getItemCount() && i3 < i4) {
            View viewForPosition = recycler.getViewForPosition(i2);
            addView(viewForPosition);
            measureChildWithMargins(viewForPosition, 0, 0);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
            int paddingTop = (int) (((float) getPaddingTop()) + (((float) (g2 - decoratedMeasuredHeight)) / 2.0f));
            rect.set(i3, paddingTop, decoratedMeasuredWidth + i3, decoratedMeasuredHeight + paddingTop);
            layoutDecorated(viewForPosition, rect.left, rect.top, rect.right, rect.bottom);
            i3 = rect.right;
            this.j = i2;
            if (c().f7813a.get(i2) == null) {
                c().f7813a.put(i2, rect);
            } else {
                c().f7813a.get(i2).set(rect);
            }
            i2++;
        }
    }

    private void c(RecyclerView.Recycler recycler, int i2, int i3, int i4) {
        Rect rect = new Rect();
        int f2 = f();
        while (i2 >= 0 && i3 > i4) {
            View viewForPosition = recycler.getViewForPosition(i2);
            addView(viewForPosition, 0);
            measureChildWithMargins(viewForPosition, 0, 0);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
            int paddingLeft = (int) (((float) getPaddingLeft()) + (((float) (f2 - decoratedMeasuredWidth)) / 2.0f));
            rect.set(paddingLeft, i3 - decoratedMeasuredHeight, decoratedMeasuredWidth + paddingLeft, i3);
            layoutDecorated(viewForPosition, rect.left, rect.top, rect.right, rect.bottom);
            i3 = rect.top;
            this.i = i2;
            if (c().f7813a.get(i2) == null) {
                c().f7813a.put(i2, rect);
            } else {
                c().f7813a.get(i2).set(rect);
            }
            i2--;
        }
    }

    private void d(RecyclerView.Recycler recycler, int i2, int i3, int i4) {
        Rect rect = new Rect();
        int f2 = f();
        while (i2 < getItemCount() && i3 < i4) {
            View viewForPosition = recycler.getViewForPosition(i2);
            addView(viewForPosition);
            measureChildWithMargins(viewForPosition, 0, 0);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
            int paddingLeft = (int) (((float) getPaddingLeft()) + (((float) (f2 - decoratedMeasuredWidth)) / 2.0f));
            rect.set(paddingLeft, i3, decoratedMeasuredWidth + paddingLeft, decoratedMeasuredHeight + i3);
            layoutDecorated(viewForPosition, rect.left, rect.top, rect.right, rect.bottom);
            i3 = rect.bottom;
            this.j = i2;
            if (c().f7813a.get(i2) == null) {
                c().f7813a.put(i2, rect);
            } else {
                c().f7813a.get(i2).set(rect);
            }
            i2++;
        }
    }

    private void b(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (getItemCount() != 0) {
            if (this.p == 0) {
                d(recycler, state, i2);
            } else {
                c(recycler, state, i2);
            }
            if (this.s != null) {
                for (int i3 = 0; i3 < getChildCount(); i3++) {
                    View childAt = getChildAt(i3);
                    this.s.a(this, childAt, a(childAt, (float) i2));
                }
            }
        }
    }

    private float a(View view, float f2) {
        int b2 = b(view, f2);
        int width = this.p == 0 ? view.getWidth() : view.getHeight();
        if (MiCS.e()) {
            String str = this.f;
            Log.d(str, "calculateToCenterFraction: distance:" + b2 + ",childLength:" + width);
        }
        return Math.max(-1.0f, Math.min(1.0f, (((float) b2) * 1.0f) / ((float) width)));
    }

    private int b(View view, float f2) {
        OrientationHelper d2 = d();
        int endAfterPadding = ((d2.getEndAfterPadding() - d2.getStartAfterPadding()) / 2) + d2.getStartAfterPadding();
        if (this.p == 0) {
            return (int) (((((float) (view.getWidth() / 2)) - f2) + ((float) view.getLeft())) - ((float) endAfterPadding));
        }
        return (int) (((((float) (view.getHeight() / 2)) - f2) + ((float) view.getTop())) - ((float) endAfterPadding));
    }

    private void c(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        int i3;
        int i4;
        RecyclerView.Recycler recycler2 = recycler;
        int i5 = i2;
        if (MiCS.e()) {
            Log.d(this.f, "fillWithVertical: dy:" + i5);
        }
        int startAfterPadding = d().getStartAfterPadding();
        int endAfterPadding = d().getEndAfterPadding();
        int i6 = 0;
        if (getChildCount() > 0) {
            if (i5 < 0) {
                for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                    View childAt = getChildAt(childCount);
                    if (getDecoratedTop(childAt) - i5 <= endAfterPadding) {
                        break;
                    }
                    if (MiCS.e()) {
                        Log.v(this.f, "fillWithVertical: removeAndRecycleView:" + getPosition(childAt));
                    }
                    removeAndRecycleView(childAt, recycler2);
                    this.j--;
                }
            } else {
                int i7 = 0;
                int i8 = 0;
                while (true) {
                    if (i7 >= getChildCount()) {
                        break;
                    }
                    View childAt2 = getChildAt(i7 + i8);
                    if (getDecoratedBottom(childAt2) - i5 < startAfterPadding) {
                        if (MiCS.e()) {
                            Log.v(this.f, "fillWithVertical: removeAndRecycleView:" + getPosition(childAt2) + ",bottom:" + getDecoratedBottom(childAt2));
                        }
                        removeAndRecycleView(childAt2, recycler2);
                        this.i++;
                        i8--;
                        i7++;
                    } else if (MiCS.e()) {
                        Log.d(this.f, "fillWithVertical: break:" + getPosition(childAt2) + ",bottom:" + getDecoratedBottom(childAt2));
                    }
                }
            }
        }
        int i9 = this.i;
        int f2 = f();
        int i10 = -1;
        if (i5 >= 0) {
            if (getChildCount() != 0) {
                View childAt3 = getChildAt(getChildCount() - 1);
                i4 = getDecoratedBottom(childAt3);
                i3 = getPosition(childAt3) + 1;
            } else {
                i3 = i9;
                i4 = -1;
            }
            int i11 = i3;
            while (i11 < getItemCount() && i4 < endAfterPadding + i5) {
                Rect rect = c().f7813a.get(i11);
                View viewForPosition = recycler2.getViewForPosition(i11);
                addView(viewForPosition);
                if (rect == null) {
                    rect = new Rect();
                    c().f7813a.put(i11, rect);
                }
                Rect rect2 = rect;
                measureChildWithMargins(viewForPosition, i6, i6);
                int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
                int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
                int paddingLeft = (int) (((float) getPaddingLeft()) + (((float) (f2 - decoratedMeasuredWidth)) / 2.0f));
                if (i4 == -1 && i3 == 0) {
                    int paddingTop = (int) (((float) getPaddingTop()) + (((float) (g() - decoratedMeasuredHeight)) / 2.0f));
                    rect2.set(paddingLeft, paddingTop, decoratedMeasuredWidth + paddingLeft, decoratedMeasuredHeight + paddingTop);
                } else {
                    rect2.set(paddingLeft, i4, decoratedMeasuredWidth + paddingLeft, decoratedMeasuredHeight + i4);
                }
                int i12 = rect2.left;
                int i13 = rect2.top;
                layoutDecorated(viewForPosition, i12, i13, rect2.right, rect2.bottom);
                i4 = rect2.bottom;
                this.j = i11;
                if (MiCS.e()) {
                    Log.d(this.f, "fillWithVertical: add view:" + i11 + ",startOffset:" + i4 + ",mLastVisiblePos:" + this.j + ",bottomEdge" + endAfterPadding);
                }
                i11++;
                i6 = 0;
            }
            return;
        }
        if (getChildCount() > 0) {
            View childAt4 = getChildAt(0);
            i10 = getDecoratedTop(childAt4);
            i9 = getPosition(childAt4) - 1;
        }
        for (int i14 = i9; i14 >= 0 && i10 > startAfterPadding + i5; i14--) {
            Rect rect3 = c().f7813a.get(i14);
            View viewForPosition2 = recycler2.getViewForPosition(i14);
            addView(viewForPosition2, 0);
            if (rect3 == null) {
                rect3 = new Rect();
                c().f7813a.put(i14, rect3);
            }
            Rect rect4 = rect3;
            measureChildWithMargins(viewForPosition2, 0, 0);
            int decoratedMeasuredWidth2 = getDecoratedMeasuredWidth(viewForPosition2);
            int decoratedMeasuredHeight2 = getDecoratedMeasuredHeight(viewForPosition2);
            int paddingLeft2 = (int) (((float) getPaddingLeft()) + (((float) (f2 - decoratedMeasuredWidth2)) / 2.0f));
            rect4.set(paddingLeft2, i10 - decoratedMeasuredHeight2, decoratedMeasuredWidth2 + paddingLeft2, i10);
            layoutDecorated(viewForPosition2, rect4.left, rect4.top, rect4.right, rect4.bottom);
            i10 = rect4.top;
            this.i = i14;
        }
    }

    private void d(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        int i3;
        int i4;
        RecyclerView.Recycler recycler2 = recycler;
        int i5 = i2;
        int startAfterPadding = d().getStartAfterPadding();
        int endAfterPadding = d().getEndAfterPadding();
        if (MiCS.e()) {
            Log.v(this.f, "fillWithHorizontal() called with: dx = [" + i5 + "],leftEdge:" + startAfterPadding + ",rightEdge:" + endAfterPadding);
        }
        int i6 = 0;
        if (getChildCount() > 0) {
            if (i5 >= 0) {
                int i7 = 0;
                for (int i8 = 0; i8 < getChildCount(); i8++) {
                    View childAt = getChildAt(i8 + i7);
                    if (getDecoratedRight(childAt) - i5 >= startAfterPadding) {
                        break;
                    }
                    removeAndRecycleView(childAt, recycler2);
                    this.i++;
                    i7--;
                    if (MiCS.e()) {
                        Log.v(this.f, "fillWithHorizontal:removeAndRecycleView:" + getPosition(childAt) + " mFirstVisiblePosition change to:" + this.i);
                    }
                }
            } else {
                for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                    View childAt2 = getChildAt(childCount);
                    if (getDecoratedLeft(childAt2) - i5 > endAfterPadding) {
                        removeAndRecycleView(childAt2, recycler2);
                        this.j--;
                        if (MiCS.e()) {
                            Log.v(this.f, "fillWithHorizontal:removeAndRecycleView:" + getPosition(childAt2) + "mLastVisiblePos change to:" + this.j);
                        }
                    }
                }
            }
        }
        int i9 = this.i;
        int g2 = g();
        int i10 = -1;
        if (i5 >= 0) {
            if (getChildCount() != 0) {
                View childAt3 = getChildAt(getChildCount() - 1);
                int position = getPosition(childAt3) + 1;
                i4 = getDecoratedRight(childAt3);
                if (MiCS.e()) {
                    Log.d(this.f, "fillWithHorizontal:to right startPosition:" + position + ",startOffset:" + i4 + ",rightEdge:" + endAfterPadding);
                }
                i3 = position;
            } else {
                i3 = i9;
                i4 = -1;
            }
            int i11 = i3;
            while (i11 < getItemCount() && i4 < endAfterPadding + i5) {
                Rect rect = c().f7813a.get(i11);
                View viewForPosition = recycler2.getViewForPosition(i11);
                addView(viewForPosition);
                if (rect == null) {
                    rect = new Rect();
                    c().f7813a.put(i11, rect);
                }
                Rect rect2 = rect;
                measureChildWithMargins(viewForPosition, i6, i6);
                int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
                int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
                int paddingTop = (int) (((float) getPaddingTop()) + (((float) (g2 - decoratedMeasuredHeight)) / 2.0f));
                if (i4 == -1 && i3 == 0) {
                    int paddingLeft = (int) (((float) getPaddingLeft()) + (((float) (f() - decoratedMeasuredWidth)) / 2.0f));
                    rect2.set(paddingLeft, paddingTop, decoratedMeasuredWidth + paddingLeft, decoratedMeasuredHeight + paddingTop);
                } else {
                    rect2.set(i4, paddingTop, decoratedMeasuredWidth + i4, decoratedMeasuredHeight + paddingTop);
                }
                int i12 = rect2.left;
                int i13 = rect2.top;
                layoutDecorated(viewForPosition, i12, i13, rect2.right, rect2.bottom);
                i4 = rect2.right;
                this.j = i11;
                if (MiCS.e()) {
                    Log.d(this.f, "fillWithHorizontal,layout:mLastVisiblePos: " + this.j);
                }
                i11++;
                i6 = 0;
            }
            return;
        }
        if (getChildCount() > 0) {
            View childAt4 = getChildAt(0);
            int position2 = getPosition(childAt4) - 1;
            i10 = getDecoratedLeft(childAt4);
            if (MiCS.e()) {
                Log.d(this.f, "fillWithHorizontal:to left startPosition:" + position2 + ",startOffset:" + i10 + ",leftEdge:" + startAfterPadding + ",child count:" + getChildCount());
            }
            i9 = position2;
        }
        for (int i14 = i9; i14 >= 0 && i10 > startAfterPadding + i5; i14--) {
            Rect rect3 = c().f7813a.get(i14);
            View viewForPosition2 = recycler2.getViewForPosition(i14);
            addView(viewForPosition2, 0);
            if (rect3 == null) {
                rect3 = new Rect();
                c().f7813a.put(i14, rect3);
            }
            Rect rect4 = rect3;
            measureChildWithMargins(viewForPosition2, 0, 0);
            int decoratedMeasuredWidth2 = getDecoratedMeasuredWidth(viewForPosition2);
            int decoratedMeasuredHeight2 = getDecoratedMeasuredHeight(viewForPosition2);
            int paddingTop2 = (int) (((float) getPaddingTop()) + (((float) (g2 - decoratedMeasuredHeight2)) / 2.0f));
            rect4.set(i10 - decoratedMeasuredWidth2, paddingTop2, i10, decoratedMeasuredHeight2 + paddingTop2);
            layoutDecorated(viewForPosition2, rect4.left, rect4.top, rect4.right, rect4.bottom);
            i10 = rect4.left;
            this.i = i14;
        }
    }

    private int f() {
        return (getWidth() - getPaddingRight()) - getPaddingLeft();
    }

    private int g() {
        return (getHeight() - getPaddingBottom()) - getPaddingTop();
    }

    public State c() {
        if (this.l == null) {
            this.l = new State();
        }
        return this.l;
    }

    private int a(int i2) {
        if (getChildCount() != 0 && i2 >= this.i) {
            return 1;
        }
        return -1;
    }

    public PointF computeScrollVectorForPosition(int i2) {
        int a2 = a(i2);
        PointF pointF = new PointF();
        if (a2 == 0) {
            return null;
        }
        if (this.p == 0) {
            pointF.x = (float) a2;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = (float) a2;
        }
        return pointF;
    }

    class State {

        /* renamed from: a  reason: collision with root package name */
        SparseArray<Rect> f7813a = new SparseArray<>();
        int b = 0;

        public State() {
        }
    }

    public boolean canScrollHorizontally() {
        return this.p == 0;
    }

    public boolean canScrollVertically() {
        return this.p == 1;
    }

    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        int i3 = -i2;
        int endAfterPadding = ((d().getEndAfterPadding() - d().getStartAfterPadding()) / 2) + d().getStartAfterPadding();
        if (i2 > 0) {
            if (getPosition(getChildAt(getChildCount() - 1)) == getItemCount() - 1) {
                View childAt = getChildAt(getChildCount() - 1);
                i3 = -Math.max(0, Math.min(i2, (((childAt.getRight() - childAt.getLeft()) / 2) + childAt.getLeft()) - endAfterPadding));
            }
        } else if (this.i == 0) {
            View childAt2 = getChildAt(0);
            i3 = -Math.min(0, Math.max(i2, (((childAt2.getRight() - childAt2.getLeft()) / 2) + childAt2.getLeft()) - endAfterPadding));
        }
        if (MiCS.e()) {
            String str = this.f;
            Log.d(str, "scrollHorizontallyBy: dx:" + i2 + ",fixed:" + i3);
        }
        int i4 = -i3;
        c().b = i4;
        b(recycler, state, i4);
        offsetChildrenHorizontal(i3);
        return i4;
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        int i3 = -i2;
        int endAfterPadding = ((d().getEndAfterPadding() - d().getStartAfterPadding()) / 2) + d().getStartAfterPadding();
        if (i2 > 0) {
            if (getPosition(getChildAt(getChildCount() - 1)) == getItemCount() - 1) {
                View childAt = getChildAt(getChildCount() - 1);
                i3 = -Math.max(0, Math.min(i2, (((getDecoratedBottom(childAt) - getDecoratedTop(childAt)) / 2) + getDecoratedTop(childAt)) - endAfterPadding));
            }
        } else if (this.i == 0) {
            View childAt2 = getChildAt(0);
            i3 = -Math.min(0, Math.max(i2, (((getDecoratedBottom(childAt2) - getDecoratedTop(childAt2)) / 2) + getDecoratedTop(childAt2)) - endAfterPadding));
        }
        if (MiCS.e()) {
            String str = this.f;
            Log.d(str, "scrollVerticallyBy: dy:" + i2 + ",fixed:" + i3);
        }
        int i4 = -i3;
        c().b = i4;
        b(recycler, state, i4);
        offsetChildrenVertical(i3);
        return i4;
    }

    public OrientationHelper d() {
        if (this.p == 0) {
            if (this.q == null) {
                this.q = OrientationHelper.createHorizontalHelper(this);
            }
            return this.q;
        }
        if (this.r == null) {
            this.r = OrientationHelper.createVerticalHelper(this);
        }
        return this.r;
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public void a(ItemTransformer itemTransformer) {
        this.s = itemTransformer;
    }

    public void a(OnItemSelectedListener onItemSelectedListener) {
        this.t = onItemSelectedListener;
    }

    public void a(RecyclerView recyclerView) {
        a(recyclerView, -1);
    }

    public void a(RecyclerView recyclerView, int i2) {
        if (recyclerView != null) {
            this.e = recyclerView;
            this.k = Math.max(0, i2);
            recyclerView.setLayoutManager(this);
            this.m.attachToRecyclerView(recyclerView);
            recyclerView.addOnScrollListener(this.n);
            return;
        }
        throw new IllegalArgumentException("The attach RecycleView must not null!!");
    }

    public void a(boolean z) {
        this.o = z;
    }

    private class InnerScrollListener extends RecyclerView.OnScrollListener {

        /* renamed from: a  reason: collision with root package name */
        int f7812a;
        boolean b;

        private InnerScrollListener() {
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            int position;
            super.onScrolled(recyclerView, i, i2);
            View findSnapView = GalleryLayoutManager.this.m.findSnapView(recyclerView.getLayoutManager());
            if (!(findSnapView == null || (position = recyclerView.getLayoutManager().getPosition(findSnapView)) == GalleryLayoutManager.this.c)) {
                if (GalleryLayoutManager.this.d != null) {
                    GalleryLayoutManager.this.d.setSelected(false);
                }
                GalleryLayoutManager.this.d = findSnapView;
                GalleryLayoutManager.this.d.setSelected(true);
                GalleryLayoutManager.this.c = position;
                if (!GalleryLayoutManager.this.o && this.f7812a != 0) {
                    if (MiCS.e()) {
                        Log.v(GalleryLayoutManager.this.f, "ignore selection change callback when fling ");
                    }
                    this.b = true;
                    return;
                } else if (GalleryLayoutManager.this.t != null) {
                    GalleryLayoutManager.this.t.a(recyclerView, findSnapView, GalleryLayoutManager.this.c);
                }
            }
            if (MiCS.e()) {
                String c2 = GalleryLayoutManager.this.f;
                Log.v(c2, "onScrolled: dx:" + i + ",dy:" + i2);
            }
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            this.f7812a = i;
            if (MiCS.e()) {
                String c2 = GalleryLayoutManager.this.f;
                Log.v(c2, "onScrollStateChanged: " + i);
            }
            if (this.f7812a == 0) {
                View findSnapView = GalleryLayoutManager.this.m.findSnapView(recyclerView.getLayoutManager());
                if (findSnapView != null) {
                    int position = recyclerView.getLayoutManager().getPosition(findSnapView);
                    if (position != GalleryLayoutManager.this.c) {
                        if (GalleryLayoutManager.this.d != null) {
                            GalleryLayoutManager.this.d.setSelected(false);
                        }
                        GalleryLayoutManager.this.d = findSnapView;
                        GalleryLayoutManager.this.d.setSelected(true);
                        GalleryLayoutManager.this.c = position;
                        if (GalleryLayoutManager.this.t != null) {
                            GalleryLayoutManager.this.t.a(recyclerView, findSnapView, GalleryLayoutManager.this.c);
                        }
                    } else if (!GalleryLayoutManager.this.o && GalleryLayoutManager.this.t != null && this.b) {
                        this.b = false;
                        GalleryLayoutManager.this.t.a(recyclerView, findSnapView, GalleryLayoutManager.this.c);
                    }
                } else {
                    Log.e(GalleryLayoutManager.this.f, "onScrollStateChanged: snap null");
                }
            }
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        GallerySmoothScroller gallerySmoothScroller = new GallerySmoothScroller(recyclerView.getContext());
        gallerySmoothScroller.setTargetPosition(i2);
        startSmoothScroll(gallerySmoothScroller);
    }

    private class GallerySmoothScroller extends LinearSmoothScroller {
        public GallerySmoothScroller(Context context) {
            super(context);
        }

        public int a(View view) {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
                return 0;
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int decoratedLeft = layoutManager.getDecoratedLeft(view) - layoutParams.leftMargin;
            int decoratedRight = layoutManager.getDecoratedRight(view) + layoutParams.rightMargin;
            return ((int) (((float) ((layoutManager.getWidth() - layoutManager.getPaddingRight()) - layoutManager.getPaddingLeft())) / 2.0f)) - (decoratedLeft + ((int) (((float) (decoratedRight - decoratedLeft)) / 2.0f)));
        }

        public int b(View view) {
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager == null || !layoutManager.canScrollVertically()) {
                return 0;
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int decoratedTop = layoutManager.getDecoratedTop(view) - layoutParams.topMargin;
            int decoratedBottom = layoutManager.getDecoratedBottom(view) + layoutParams.bottomMargin;
            return ((int) (((float) ((layoutManager.getHeight() - layoutManager.getPaddingBottom()) - layoutManager.getPaddingTop())) / 2.0f)) - (decoratedTop + ((int) (((float) (decoratedBottom - decoratedTop)) / 2.0f)));
        }

        /* access modifiers changed from: protected */
        public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            int a2 = a(view);
            int b = b(view);
            int calculateTimeForDeceleration = calculateTimeForDeceleration((int) Math.sqrt((double) ((a2 * a2) + (b * b))));
            if (calculateTimeForDeceleration > 0) {
                action.update(-a2, -b, calculateTimeForDeceleration, this.mDecelerateInterpolator);
            }
        }
    }
}
