package com.mi.global.shop.widget;

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
    private static final int[] e = {16843284};

    /* renamed from: a  reason: collision with root package name */
    private Paint f7167a;
    private Drawable b;
    private int c;
    private int d;

    public RecycleViewDivider(Context context, int i) {
        this.c = 2;
        if (i == 1 || i == 0) {
            this.d = i;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(e);
            this.b = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalArgumentException("请输入正确的参数！");
    }

    public RecycleViewDivider(Context context, int i, int i2) {
        this(context, i);
        this.b = ContextCompat.getDrawable(context, i2);
        this.c = this.b.getIntrinsicHeight();
    }

    public RecycleViewDivider(Context context, int i, int i2, int i3) {
        this(context, i);
        this.c = i2;
        this.f7167a = new Paint(1);
        this.f7167a.setColor(i3);
        this.f7167a.setStyle(Paint.Style.FILL);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        rect.set(0, 0, 0, this.c);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
        if (this.d == 1) {
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
            int i2 = this.c + bottom;
            if (this.b != null) {
                this.b.setBounds(paddingLeft, bottom, measuredWidth, i2);
                this.b.draw(canvas);
            }
            if (this.f7167a != null) {
                canvas.drawRect((float) paddingLeft, (float) bottom, (float) measuredWidth, (float) i2, this.f7167a);
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
            int i2 = this.c + right;
            if (this.b != null) {
                this.b.setBounds(right, paddingTop, i2, measuredHeight);
                this.b.draw(canvas);
            }
            if (this.f7167a != null) {
                canvas.drawRect((float) right, (float) paddingTop, (float) i2, (float) measuredHeight, this.f7167a);
            }
        }
    }
}
