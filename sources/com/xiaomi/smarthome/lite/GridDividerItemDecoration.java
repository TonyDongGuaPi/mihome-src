package com.xiaomi.smarthome.lite;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private Drawable f19349a;
    private Drawable b;
    private int c;

    public GridDividerItemDecoration(Drawable drawable, Drawable drawable2, int i) {
        this.f19349a = drawable;
        this.b = drawable2;
        this.c = i;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        a(canvas, recyclerView);
        b(canvas, recyclerView);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        boolean z = false;
        if (!(recyclerView.getChildAdapterPosition(view) % this.c == 0)) {
            rect.left = this.f19349a.getIntrinsicWidth();
        }
        if (recyclerView.getChildAdapterPosition(view) < this.c) {
            z = true;
        }
        if (!z) {
            rect.top = this.b.getIntrinsicHeight();
        }
    }

    private void a(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        if (childCount > 0) {
            int i = 0;
            Object tag = recyclerView.getChildAt(0).getTag();
            if (tag != null) {
                try {
                    i = "header".equals((String) tag);
                } catch (Exception unused) {
                }
            }
            while (i < childCount) {
                View childAt = recyclerView.getChildAt(i);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                int right = childAt.getRight();
                this.f19349a.setBounds(right, top, this.f19349a.getIntrinsicWidth() + right, bottom);
                this.f19349a.draw(canvas);
                i++;
            }
        }
    }

    private void b(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        if (childCount > 0) {
            int i = 0;
            Object tag = recyclerView.getChildAt(0).getTag();
            if (tag != null) {
                try {
                    i = "header".equals((String) tag);
                } catch (Exception unused) {
                }
            }
            while (i < childCount) {
                View childAt = recyclerView.getChildAt(i);
                int bottom = childAt.getBottom();
                this.b.setBounds(childAt.getLeft(), bottom, childAt.getRight(), this.b.getIntrinsicHeight() + bottom);
                this.b.draw(canvas);
                i++;
            }
        }
    }
}
