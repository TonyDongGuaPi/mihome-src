package com.xiaomi.smarthome.newui.widget.topnavi.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class FlowLayoutManager extends RecyclerView.LayoutManager {
    private static final String e = "FlowLayoutManager";

    /* renamed from: a  reason: collision with root package name */
    final FlowLayoutManager f20941a = this;
    protected int b;
    protected int c;
    protected int d = 0;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j = 0;
    private Row k = new Row();
    private List<Row> l = new ArrayList();
    private SparseArray<Rect> m = new SparseArray<>();

    public boolean canScrollVertically() {
        return true;
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.l.size();
    }

    public FlowLayoutManager() {
        setAutoMeasureEnabled(true);
    }

    public class Item {

        /* renamed from: a  reason: collision with root package name */
        int f20942a;
        View b;
        Rect c;

        public void a(Rect rect) {
            this.c = rect;
        }

        public Item(int i, View view, Rect rect) {
            this.f20942a = i;
            this.b = view;
            this.c = rect;
        }
    }

    public class Row {

        /* renamed from: a  reason: collision with root package name */
        float f20943a;
        float b;
        List<Item> c = new ArrayList();

        public Row() {
        }

        public void a(float f) {
            this.f20943a = f;
        }

        public void b(float f) {
            this.b = f;
        }

        public void a(Item item) {
            this.c.add(item);
        }
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(e, "onLayoutChildren");
        this.d = 0;
        int i2 = this.g;
        this.k = new Row();
        this.l.clear();
        this.m.clear();
        removeAllViews();
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            this.j = 0;
        } else if (getChildCount() != 0 || !state.isPreLayout()) {
            detachAndScrapAttachedViews(recycler);
            if (getChildCount() == 0) {
                this.b = getWidth();
                this.c = getHeight();
                this.f = getPaddingLeft();
                this.h = getPaddingRight();
                this.g = getPaddingTop();
                this.i = (this.b - this.f) - this.h;
            }
            int i3 = i2;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < getItemCount(); i6++) {
                Log.d(e, "index:" + i6);
                View viewForPosition = recycler.getViewForPosition(i6);
                if (8 != viewForPosition.getVisibility()) {
                    measureChildWithMargins(viewForPosition, 0, 0);
                    int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
                    int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
                    int i7 = i4 + decoratedMeasuredWidth;
                    if (i7 <= this.i) {
                        int i8 = this.f + i4;
                        Rect rect = this.m.get(i6);
                        if (rect == null) {
                            rect = new Rect();
                        }
                        rect.set(i8, i3, decoratedMeasuredWidth + i8, i3 + decoratedMeasuredHeight);
                        this.m.put(i6, rect);
                        i5 = Math.max(i5, decoratedMeasuredHeight);
                        this.k.a(new Item(decoratedMeasuredHeight, viewForPosition, rect));
                        this.k.a((float) i3);
                        this.k.b((float) i5);
                        decoratedMeasuredWidth = i7;
                    } else {
                        d();
                        i3 += i5;
                        this.d += i5;
                        int i9 = this.f;
                        Rect rect2 = this.m.get(i6);
                        if (rect2 == null) {
                            rect2 = new Rect();
                        }
                        rect2.set(i9, i3, i9 + decoratedMeasuredWidth, i3 + decoratedMeasuredHeight);
                        this.m.put(i6, rect2);
                        this.k.a(new Item(decoratedMeasuredHeight, viewForPosition, rect2));
                        this.k.a((float) i3);
                        this.k.b((float) decoratedMeasuredHeight);
                        i5 = decoratedMeasuredHeight;
                    }
                    if (i6 == getItemCount() - 1) {
                        d();
                        this.d += i5;
                    }
                    i4 = decoratedMeasuredWidth;
                }
            }
            this.d = Math.max(this.d, e());
            Log.d(e, "onLayoutChildren totalHeight:" + this.d);
            a(recycler, state);
        }
    }

    private void a(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!state.isPreLayout()) {
            Rect rect = new Rect(getPaddingLeft(), getPaddingTop() + this.j, getWidth() - getPaddingRight(), this.j + (getHeight() - getPaddingBottom()));
            for (int i2 = 0; i2 < this.l.size(); i2++) {
                Row row = this.l.get(i2);
                float f2 = row.f20943a;
                float f3 = row.b + f2;
                if (f2 >= ((float) rect.bottom) || ((float) rect.top) >= f3) {
                    List<Item> list = row.c;
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        removeAndRecycleView(list.get(i3).b, recycler);
                    }
                } else {
                    List<Item> list2 = row.c;
                    for (int i4 = 0; i4 < list2.size(); i4++) {
                        View view = list2.get(i4).b;
                        measureChildWithMargins(view, 0, 0);
                        addView(view);
                        Rect rect2 = list2.get(i4).c;
                        layoutDecoratedWithMargins(view, rect2.left, rect2.top - this.j, rect2.right, rect2.bottom - this.j);
                    }
                }
            }
        }
    }

    private void d() {
        List<Item> list = this.k.c;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Item item = list.get(i2);
            View view = item.b;
            int position = getPosition(view);
            if (((float) this.m.get(position).top) < this.k.f20943a + ((this.k.b - ((float) list.get(i2).f20942a)) / 2.0f)) {
                Rect rect = this.m.get(position);
                if (rect == null) {
                    rect = new Rect();
                }
                rect.set(this.m.get(position).left, (int) (this.k.f20943a + ((this.k.b - ((float) list.get(i2).f20942a)) / 2.0f)), this.m.get(position).right, (int) (this.k.f20943a + ((this.k.b - ((float) list.get(i2).f20942a)) / 2.0f) + ((float) getDecoratedMeasuredHeight(view))));
                this.m.put(position, rect);
                item.a(rect);
                list.set(i2, item);
            }
        }
        this.k.c = list;
        this.l.add(this.k);
        this.k = new Row();
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d("TAG", "totalHeight:" + this.d);
        if (this.j + i2 < 0) {
            i2 = -this.j;
        } else if (this.j + i2 > this.d - e()) {
            i2 = (this.d - e()) - this.j;
        }
        this.j += i2;
        offsetChildrenVertical(-i2);
        a(recycler, state);
        return i2;
    }

    private int e() {
        return (this.f20941a.getHeight() - this.f20941a.getPaddingBottom()) - this.f20941a.getPaddingTop();
    }

    public int c() {
        return (this.f20941a.getWidth() - this.f20941a.getPaddingLeft()) - this.f20941a.getPaddingRight();
    }
}
