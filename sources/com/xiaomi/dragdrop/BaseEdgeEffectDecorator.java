package com.xiaomi.dragdrop;

import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.widget.RecyclerView;

abstract class BaseEdgeEffectDecorator extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    protected static final int f10093a = 0;
    protected static final int b = 1;
    protected static final int c = 2;
    protected static final int d = 3;
    private RecyclerView e;
    private EdgeEffectCompat f;
    private EdgeEffectCompat g;
    private boolean h;
    private int i;
    private int j;

    /* access modifiers changed from: protected */
    public abstract int a(int i2);

    public BaseEdgeEffectDecorator(RecyclerView recyclerView) {
        this.e = recyclerView;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        boolean z = false;
        if (this.f != null) {
            z = false | a(canvas, recyclerView, this.i, this.f);
        }
        if (this.g != null) {
            z |= a(canvas, recyclerView, this.j, this.g);
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(recyclerView);
        }
    }

    private static boolean a(Canvas canvas, RecyclerView recyclerView, int i2, EdgeEffectCompat edgeEffectCompat) {
        if (edgeEffectCompat.isFinished()) {
            return false;
        }
        int save = canvas.save();
        boolean c2 = c(recyclerView);
        switch (i2) {
            case 0:
                canvas.rotate(-90.0f);
                if (!c2) {
                    canvas.translate((float) (-recyclerView.getHeight()), 0.0f);
                    break;
                } else {
                    canvas.translate((float) ((-recyclerView.getHeight()) + recyclerView.getPaddingTop()), (float) recyclerView.getPaddingLeft());
                    break;
                }
            case 1:
                if (c2) {
                    canvas.translate((float) recyclerView.getPaddingLeft(), (float) recyclerView.getPaddingTop());
                    break;
                }
                break;
            case 2:
                canvas.rotate(90.0f);
                if (!c2) {
                    canvas.translate(0.0f, (float) (-recyclerView.getWidth()));
                    break;
                } else {
                    canvas.translate((float) recyclerView.getPaddingTop(), (float) ((-recyclerView.getWidth()) + recyclerView.getPaddingRight()));
                    break;
                }
            case 3:
                canvas.rotate(180.0f);
                if (!c2) {
                    canvas.translate((float) (-recyclerView.getWidth()), (float) (-recyclerView.getHeight()));
                    break;
                } else {
                    canvas.translate((float) ((-recyclerView.getWidth()) + recyclerView.getPaddingRight()), (float) ((-recyclerView.getHeight()) + recyclerView.getPaddingBottom()));
                    break;
                }
        }
        boolean draw = edgeEffectCompat.draw(canvas);
        canvas.restoreToCount(save);
        return draw;
    }

    public void a() {
        if (!this.h) {
            this.i = a(0);
            this.j = a(1);
            this.e.addItemDecoration(this);
            this.h = true;
        }
    }

    public void b() {
        if (this.h) {
            this.e.removeItemDecoration(this);
        }
        c();
        this.e = null;
        this.h = false;
    }

    public void a(float f2) {
        a(this.e);
        if (this.f.onPull(f2, 0.5f)) {
            ViewCompat.postInvalidateOnAnimation(this.e);
        }
    }

    public void b(float f2) {
        b(this.e);
        if (this.g.onPull(f2, 0.5f)) {
            ViewCompat.postInvalidateOnAnimation(this.e);
        }
    }

    public void c() {
        boolean z = false;
        if (this.f != null) {
            z = false | this.f.onRelease();
        }
        if (this.g != null) {
            z |= this.g.onRelease();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this.e);
        }
    }

    private void a(RecyclerView recyclerView) {
        if (this.f == null) {
            this.f = new EdgeEffectCompat(recyclerView.getContext());
        }
        a(recyclerView, this.f, this.i);
    }

    private void b(RecyclerView recyclerView) {
        if (this.g == null) {
            this.g = new EdgeEffectCompat(recyclerView.getContext());
        }
        a(recyclerView, this.g, this.j);
    }

    private static void a(RecyclerView recyclerView, EdgeEffectCompat edgeEffectCompat, int i2) {
        int measuredWidth = recyclerView.getMeasuredWidth();
        int measuredHeight = recyclerView.getMeasuredHeight();
        if (c(recyclerView)) {
            measuredWidth -= recyclerView.getPaddingLeft() + recyclerView.getPaddingRight();
            measuredHeight -= recyclerView.getPaddingTop() + recyclerView.getPaddingBottom();
        }
        int max = Math.max(0, measuredWidth);
        int max2 = Math.max(0, measuredHeight);
        if (!(i2 == 0 || i2 == 2)) {
            int i3 = max;
            max = max2;
            max2 = i3;
        }
        edgeEffectCompat.setSize(max2, max);
    }

    private static boolean c(RecyclerView recyclerView) {
        return recyclerView.getLayoutManager().getClipToPadding();
    }

    public void d() {
        if (this.h) {
            this.e.removeItemDecoration(this);
            this.e.addItemDecoration(this);
        }
    }
}
