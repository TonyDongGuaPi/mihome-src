package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;

class DraggingItemDecorator extends BaseDraggableItemDecorator {
    private static final String c = "DraggingItemDecorator";
    private int d;
    private int e;
    private Bitmap f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private NinePatchDrawable m;
    private final Rect n = new Rect();
    private boolean o;
    private boolean p;
    private ItemDraggableRange q;
    private int r;
    private DraggingItemInfo s;

    public DraggingItemDecorator(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange) {
        super(recyclerView, viewHolder);
        this.q = itemDraggableRange;
    }

    private static int a(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }

    private static View a(RecyclerView recyclerView, ItemDraggableRange itemDraggableRange, int i2, int i3) {
        int layoutPosition;
        if (i2 == -1 || i3 == -1) {
            return null;
        }
        int childCount = recyclerView.getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = recyclerView.getChildAt(i4);
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
            if (childViewHolder != null && (layoutPosition = childViewHolder.getLayoutPosition()) >= i2 && layoutPosition <= i3 && itemDraggableRange.a(layoutPosition)) {
                return childAt;
            }
        }
        return null;
    }

    private static View b(RecyclerView recyclerView, ItemDraggableRange itemDraggableRange, int i2, int i3) {
        int layoutPosition;
        if (i2 == -1 || i3 == -1) {
            return null;
        }
        for (int childCount = recyclerView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = recyclerView.getChildAt(childCount);
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
            if (childViewHolder != null && (layoutPosition = childViewHolder.getLayoutPosition()) >= i2 && layoutPosition <= i3 && itemDraggableRange.a(layoutPosition)) {
                return childAt;
            }
        }
        return null;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.f != null) {
            canvas.drawBitmap(this.f, (float) (this.d - this.n.left), (float) (this.e - this.n.top), (Paint) null);
        }
    }

    public void a(MotionEvent motionEvent, DraggingItemInfo draggingItemInfo) {
        if (!this.o) {
            View view = this.b.itemView;
            this.s = draggingItemInfo;
            this.f = a(view, this.m);
            this.g = this.f5698a.getPaddingLeft();
            this.i = this.f5698a.getPaddingTop();
            this.r = CustomRecyclerViewUtils.e(this.f5698a);
            view.setVisibility(4);
            a(motionEvent, true);
            this.f5698a.addItemDecoration(this);
            this.o = true;
        }
    }

    public void a(boolean z) {
        if (this.o) {
            this.f5698a.removeItemDecoration(this);
        }
        RecyclerView.ItemAnimator itemAnimator = this.f5698a.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        this.f5698a.stopScroll();
        a((float) this.d, this.e);
        if (this.b != null) {
            a(this.b.itemView, z);
        }
        if (this.b != null) {
            this.b.itemView.setVisibility(0);
        }
        this.b = null;
        if (this.f != null) {
            this.f.recycle();
            this.f = null;
        }
        this.q = null;
        this.d = 0;
        this.e = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.o = false;
    }

    public boolean a(MotionEvent motionEvent, boolean z) {
        this.k = (int) (motionEvent.getX() + 0.5f);
        this.l = (int) (motionEvent.getY() + 0.5f);
        return b(z);
    }

    public boolean b(boolean z) {
        int i2 = this.d;
        int i3 = this.e;
        n();
        boolean z2 = (i2 == this.d && i3 == this.e) ? false : true;
        if (z2 || z) {
            a((float) this.d, this.e);
            ViewCompat.postInvalidateOnAnimation(this.f5698a);
        }
        return z2;
    }

    public void a(NinePatchDrawable ninePatchDrawable) {
        this.m = ninePatchDrawable;
        if (this.m != null) {
            this.m.getPadding(this.n);
        }
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.e - this.s.e;
    }

    public int d() {
        return this.d - this.s.d;
    }

    private void n() {
        RecyclerView recyclerView = this.f5698a;
        if (recyclerView.getChildCount() > 0) {
            this.g = 0;
            this.h = recyclerView.getWidth() - this.s.f5703a;
            this.i = 0;
            this.j = recyclerView.getHeight() - this.s.b;
            switch (this.r) {
                case 0:
                    this.i += recyclerView.getPaddingTop();
                    this.j -= recyclerView.getPaddingBottom();
                    break;
                case 1:
                    this.g += recyclerView.getPaddingLeft();
                    this.h -= recyclerView.getPaddingRight();
                    break;
            }
            this.h = Math.max(this.g, this.h);
            this.j = Math.max(this.i, this.j);
            if (!this.p) {
                int a2 = CustomRecyclerViewUtils.a(recyclerView, true);
                int b = CustomRecyclerViewUtils.b(recyclerView, true);
                View a3 = a(recyclerView, this.q, a2, b);
                View b2 = b(recyclerView, this.q, a2, b);
                switch (this.r) {
                    case 0:
                        if (a3 != null) {
                            this.g = Math.min(this.g, a3.getLeft());
                        }
                        if (b2 != null) {
                            this.h = Math.min(this.h, Math.max(0, b2.getRight() - this.s.f5703a));
                            break;
                        }
                        break;
                    case 1:
                        if (a3 != null) {
                            this.i = Math.min(this.j, a3.getTop());
                        }
                        if (b2 != null) {
                            this.j = Math.min(this.j, Math.max(0, b2.getBottom() - this.s.b));
                            break;
                        }
                        break;
                }
            }
        } else {
            int paddingLeft = recyclerView.getPaddingLeft();
            this.g = paddingLeft;
            this.h = paddingLeft;
            int paddingTop = recyclerView.getPaddingTop();
            this.i = paddingTop;
            this.j = paddingTop;
        }
        this.d = this.k - this.s.f;
        this.e = this.l - this.s.g;
        this.d = a(this.d, this.g, this.h);
        this.e = a(this.e, this.i, this.j);
    }

    private static int a(int i2, int i3) {
        if (i2 == -1) {
            return -1;
        }
        return (i2 / i3) * i3;
    }

    public boolean e() {
        return this.e == this.i;
    }

    public boolean f() {
        return this.e == this.j;
    }

    public boolean g() {
        return this.d == this.g;
    }

    public boolean h() {
        return this.d == this.h;
    }

    private Bitmap a(View view, NinePatchDrawable ninePatchDrawable) {
        int width = view.getWidth() + this.n.left + this.n.right;
        int height = view.getHeight() + this.n.top + this.n.bottom;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        if (ninePatchDrawable != null) {
            ninePatchDrawable.setBounds(0, 0, width, height);
            ninePatchDrawable.draw(canvas);
        }
        int save = canvas.save(3);
        canvas.clipRect(this.n.left, this.n.top, width - this.n.right, height - this.n.bottom);
        canvas.translate((float) this.n.left, (float) this.n.top);
        view.draw(canvas);
        canvas.restoreToCount(save);
        return createBitmap;
    }

    private void a(float f2, int i2) {
        if (this.b != null) {
            a(this.f5698a, this.b, f2 - ((float) this.b.itemView.getLeft()), (float) (i2 - this.b.itemView.getTop()));
        }
    }

    public void c(boolean z) {
        if (this.p != z) {
            this.p = z;
        }
    }

    public int i() {
        return this.e;
    }

    public int j() {
        return this.e + this.s.b;
    }

    public int k() {
        return this.d;
    }

    public int l() {
        return this.d + this.s.f5703a;
    }

    public void m() {
        if (this.b != null) {
            ViewCompat.setTranslationX(this.b.itemView, 0.0f);
            ViewCompat.setTranslationY(this.b.itemView, 0.0f);
            this.b.itemView.setVisibility(0);
        }
        this.b = null;
    }

    public void a(RecyclerView.ViewHolder viewHolder) {
        if (this.b == null) {
            this.b = viewHolder;
            viewHolder.itemView.setVisibility(4);
            return;
        }
        throw new IllegalStateException("A new view holder is attempt to be assigned before invalidating the older one");
    }
}
