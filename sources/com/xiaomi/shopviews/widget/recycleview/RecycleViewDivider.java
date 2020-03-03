package com.xiaomi.shopviews.widget.recycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecycleViewDivider extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public static final int f13343a = 10000;
    private static final int[] f = {16843284};
    private Paint b;
    private Drawable c;
    private int d;
    private int e;

    public RecycleViewDivider(Context context, int i) {
        this.d = 2;
        this.e = i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f);
        this.c = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    public RecycleViewDivider(Context context, int i, int i2) {
        this(context, i);
        this.c = ContextCompat.getDrawable(context, i2);
        this.d = this.c.getIntrinsicHeight();
    }

    public RecycleViewDivider(Context context, int i, int i2, int i3) {
        this(context, i);
        this.d = i2;
        this.b = new Paint(1);
        this.b.setColor(i3);
        this.b.setStyle(Paint.Style.FILL);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        rect.set(0, 0, 0, this.d);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
        if (this.e == 10000) {
            b(canvas, recyclerView);
            a(canvas, recyclerView);
        } else if (this.e == 1) {
            b(canvas, recyclerView);
        } else {
            a(canvas, recyclerView);
        }
    }

    private void a(Canvas canvas, RecyclerView recyclerView) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int measuredWidth = recyclerView.getMeasuredWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int bottom = childAt.getBottom() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            int i2 = this.d + bottom;
            if (this.c != null) {
                this.c.setBounds(paddingLeft, bottom, measuredWidth, i2);
                this.c.draw(canvas);
            }
            if (this.b != null) {
                canvas.drawRect((float) paddingLeft, (float) bottom, (float) measuredWidth, (float) i2, this.b);
            }
        }
    }

    private void b(Canvas canvas, RecyclerView recyclerView) {
        int paddingTop = recyclerView.getPaddingTop();
        int measuredHeight = recyclerView.getMeasuredHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int right = childAt.getRight() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).rightMargin;
            int i2 = this.d + right;
            if (this.c != null) {
                this.c.setBounds(right, paddingTop, i2, measuredHeight);
                this.c.draw(canvas);
            }
            if (this.b != null) {
                canvas.drawRect((float) right, (float) paddingTop, (float) i2, (float) measuredHeight, this.b);
            }
        }
    }
}
