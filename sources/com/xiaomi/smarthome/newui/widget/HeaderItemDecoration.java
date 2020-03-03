package com.xiaomi.smarthome.newui.widget;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HeaderItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private StickyHeaderInterface f20867a;
    private int b;
    private View c;

    public interface StickyHeaderInterface {
        int a(int i);

        void a(View view, int i);

        int b(int i);

        boolean c(int i);
    }

    public void a(StickyHeaderInterface stickyHeaderInterface) {
        this.f20867a = stickyHeaderInterface;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition;
        super.onDrawOver(canvas, recyclerView, state);
        View childAt = recyclerView.getChildAt(0);
        if (childAt != null && (childAdapterPosition = recyclerView.getChildAdapterPosition(childAt)) != -1) {
            View a2 = a(childAdapterPosition, recyclerView);
            a((ViewGroup) recyclerView, a2);
            View a3 = a(recyclerView, a2.getBottom());
            if (a3 != null) {
                if (this.f20867a.c(recyclerView.getChildAdapterPosition(a3))) {
                    a(canvas, a2, a3);
                } else {
                    a(canvas, a2);
                }
            }
        }
    }

    private View a(int i, RecyclerView recyclerView) {
        int a2 = this.f20867a.a(i);
        int b2 = this.f20867a.b(a2);
        if (this.c == null) {
            this.c = LayoutInflater.from(recyclerView.getContext()).inflate(b2, recyclerView, false);
        }
        this.f20867a.a(this.c, a2);
        return this.c;
    }

    private void a(Canvas canvas, View view) {
        canvas.save();
        canvas.translate(0.0f, 0.0f);
        view.draw(canvas);
        canvas.restore();
    }

    private void a(Canvas canvas, View view, View view2) {
        canvas.save();
        canvas.translate(0.0f, (float) (view2.getTop() - view.getHeight()));
        view.draw(canvas);
        canvas.restore();
    }

    private View a(RecyclerView recyclerView, int i) {
        for (int i2 = 0; i2 < recyclerView.getChildCount(); i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (childAt.getBottom() > i && childAt.getTop() <= i) {
                return childAt;
            }
        }
        return null;
    }

    private void a(ViewGroup viewGroup, View view) {
        view.measure(ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(viewGroup.getWidth(), 1073741824), viewGroup.getPaddingLeft() + viewGroup.getPaddingRight(), view.getLayoutParams().width), ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(viewGroup.getHeight(), 0), viewGroup.getPaddingTop() + viewGroup.getPaddingBottom(), view.getLayoutParams().height));
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        this.b = measuredHeight;
        view.layout(0, 0, measuredWidth, measuredHeight);
    }
}
