package com.xiaomi.dragdrop;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;

class SwapTargetItemOperator extends BaseDraggableItemDecorator {
    private static final String c = "SwapTargetItemOperator";
    private RecyclerView.ViewHolder d;
    private Interpolator e;
    private int f;
    private int g;
    private final Rect h = new Rect();
    private final Rect i = new Rect();
    private final Rect j = new Rect();
    private boolean k;
    private float l;
    private float m;
    private DraggingItemInfo n;
    private ItemDraggableRange o;
    private boolean p;

    public SwapTargetItemOperator(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange, DraggingItemInfo draggingItemInfo) {
        super(recyclerView, viewHolder);
        this.n = draggingItemInfo;
        this.o = itemDraggableRange;
        CustomRecyclerViewUtils.a(this.f10091a.getLayoutManager(), this.b.itemView, this.j);
    }

    private static float a(float f2, float f3) {
        float f4 = (f2 * 0.7f) + (0.3f * f3);
        return Math.abs(f4 - f3) < 0.01f ? f3 : f4;
    }

    public void b(Interpolator interpolator) {
        this.e = interpolator;
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        if (this.d != viewHolder) {
            if (this.d != null) {
                ViewCompat.animate(this.d.itemView).translationX(0.0f).translationY(0.0f).setDuration(10).start();
            }
            this.d = viewHolder;
            this.p = true;
        }
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        RecyclerView.ViewHolder viewHolder = this.b;
        RecyclerView.ViewHolder viewHolder2 = this.d;
        if (viewHolder != null && viewHolder2 != null && viewHolder.getItemId() == this.n.c) {
            this.l = a(viewHolder, viewHolder2);
            if (this.p) {
                this.p = false;
                this.m = this.l;
            } else {
                this.m = a(this.m, this.l);
            }
            a(viewHolder, viewHolder2, this.m);
        }
    }

    private float a(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        View view = viewHolder2.itemView;
        int layoutPosition = viewHolder.getLayoutPosition();
        int layoutPosition2 = viewHolder2.getLayoutPosition();
        CustomRecyclerViewUtils.a(this.f10091a.getLayoutManager(), view, this.h);
        CustomRecyclerViewUtils.a(view, this.i);
        Rect rect = this.i;
        Rect rect2 = this.h;
        int height = view.getHeight() + rect.top + rect.bottom + rect2.top + rect2.bottom;
        int width = view.getWidth() + rect.left + rect.right + rect2.left + rect2.right;
        float left = width != 0 ? ((float) (viewHolder.itemView.getLeft() - this.f)) / ((float) width) : 0.0f;
        float top = height != 0 ? ((float) (viewHolder.itemView.getTop() - this.g)) / ((float) height) : 0.0f;
        int e2 = CustomRecyclerViewUtils.e(this.f10091a);
        if (e2 != 1) {
            top = e2 == 0 ? layoutPosition > layoutPosition2 ? left : left + 1.0f : 0.0f;
        } else if (layoutPosition <= layoutPosition2) {
            top += 1.0f;
        }
        return Math.min(Math.max(top, 0.0f), 1.0f);
    }

    private void a(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, float f2) {
        View view = viewHolder2.itemView;
        int layoutPosition = viewHolder.getLayoutPosition();
        int layoutPosition2 = viewHolder2.getLayoutPosition();
        Rect rect = this.n.h;
        Rect rect2 = this.j;
        int i2 = this.n.b + rect.top + rect.bottom + rect2.top + rect2.bottom;
        int i3 = this.n.f10101a + rect.left + rect.right + rect2.left + rect2.right;
        if (this.e != null) {
            f2 = this.e.getInterpolation(f2);
        }
        switch (CustomRecyclerViewUtils.e(this.f10091a)) {
            case 0:
                if (layoutPosition > layoutPosition2) {
                    ViewCompat.setTranslationX(view, f2 * ((float) i3));
                    return;
                } else {
                    ViewCompat.setTranslationX(view, (f2 - 1.0f) * ((float) i3));
                    return;
                }
            case 1:
                if (layoutPosition > layoutPosition2) {
                    ViewCompat.setTranslationY(view, f2 * ((float) i2));
                    return;
                } else {
                    ViewCompat.setTranslationY(view, (f2 - 1.0f) * ((float) i2));
                    return;
                }
            default:
                return;
        }
    }

    public void a() {
        if (!this.k) {
            this.f10091a.addItemDecoration(this, 0);
            this.k = true;
        }
    }

    public void a(boolean z) {
        if (this.k) {
            this.f10091a.removeItemDecoration(this);
        }
        RecyclerView.ItemAnimator itemAnimator = this.f10091a.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        this.f10091a.stopScroll();
        if (this.d != null) {
            a(this.b, this.d, this.m);
            a(this.d.itemView, z);
            this.d = null;
        }
        this.o = null;
        this.b = null;
        this.f = 0;
        this.g = 0;
        this.m = 0.0f;
        this.l = 0.0f;
        this.k = false;
        this.n = null;
    }

    public void a(int i2, int i3) {
        this.f = i2;
        this.g = i3;
    }
}
