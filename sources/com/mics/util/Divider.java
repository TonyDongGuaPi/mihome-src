package com.mics.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class Divider extends RecyclerView.ItemDecoration {
    private static final int[] f = {16843284};

    /* renamed from: a  reason: collision with root package name */
    private boolean f7773a;
    private Paint b;
    private Drawable c;
    private int d;
    private int e;

    public Divider(Context context, int i) {
        this.d = 1;
        if (i == 1 || i == 0) {
            this.e = i;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f);
            this.c = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalArgumentException("请输入正确的参数！");
    }

    public Divider(Context context, int i, int i2) {
        this(context, i);
        this.c = ContextCompat.getDrawable(context, i2);
        this.d = this.c.getIntrinsicHeight();
    }

    public Divider(Context context, int i, int i2, int i3) {
        this(context, i, i2, i3, false);
    }

    public Divider(Context context, int i, int i2, int i3, boolean z) {
        this(context, i);
        this.d = i2;
        this.b = new Paint(1);
        this.b.setColor(i3);
        this.b.setStyle(Paint.Style.FILL);
        this.f7773a = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (this.e == 0) {
            if (recyclerView.getChildAdapterPosition(view) == 0) {
                rect.set(0, this.f7773a ? this.d : 0, 0, this.d);
            } else {
                rect.set(0, 0, 0, this.d);
            }
        } else if (recyclerView.getChildAdapterPosition(view) == 0) {
            rect.set(this.f7773a ? this.d : 0, 0, this.d, 0);
        } else {
            rect.set(0, 0, this.d, 0);
        }
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
        if (this.e == 1) {
            b(canvas, recyclerView);
        } else {
            a(canvas, recyclerView);
        }
    }

    private void a(Canvas canvas, RecyclerView recyclerView) {
        Canvas canvas2 = canvas;
        int paddingLeft = recyclerView.getPaddingLeft();
        int measuredWidth = recyclerView.getMeasuredWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int bottom = childAt.getBottom() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            int i2 = bottom + this.d;
            if (this.f7773a && i == 0) {
                if (this.c != null) {
                    this.c.setBounds(paddingLeft, childAt.getTop() - this.d, measuredWidth, childAt.getTop());
                    this.c.draw(canvas2);
                }
                if (this.b != null) {
                    canvas.drawRect((float) paddingLeft, (float) (childAt.getTop() - this.d), (float) measuredWidth, (float) childAt.getTop(), this.b);
                }
            }
            if (this.c != null) {
                this.c.setBounds(paddingLeft, bottom, measuredWidth, i2);
                this.c.draw(canvas2);
            }
            if (this.b != null) {
                canvas.drawRect((float) paddingLeft, (float) bottom, (float) measuredWidth, (float) i2, this.b);
            }
        }
    }

    private void b(Canvas canvas, RecyclerView recyclerView) {
        Canvas canvas2 = canvas;
        int paddingTop = recyclerView.getPaddingTop();
        int measuredHeight = recyclerView.getMeasuredHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int right = childAt.getRight() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).rightMargin;
            int i2 = right + this.d;
            if (this.f7773a && i == 0) {
                if (this.c != null) {
                    this.c.setBounds(childAt.getLeft() - this.d, paddingTop, childAt.getLeft(), measuredHeight);
                    this.c.draw(canvas2);
                }
                if (this.b != null) {
                    canvas.drawRect((float) (childAt.getLeft() - this.d), (float) paddingTop, (float) childAt.getLeft(), (float) measuredHeight, this.b);
                }
            }
            if (this.c != null) {
                this.c.setBounds(right, paddingTop, i2, measuredHeight);
                this.c.draw(canvas2);
            }
            if (this.b != null) {
                canvas.drawRect((float) right, (float) paddingTop, (float) i2, (float) measuredHeight, this.b);
            }
        }
    }
}
