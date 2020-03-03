package com.xiaomi.smarthome.newui.widget.topnavi.layoutmanager;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.UIUtil;

public class DividerDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20940a = "no_divider";
    private final Paint b = new Paint(1);

    public DividerDecoration(int i) {
        this.b.setColor(-1710619);
        this.b.setStrokeWidth((float) i);
        this.b.setStyle(Paint.Style.STROKE);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int a2 = UIUtil.a(recyclerView.getContext(), 12.0d);
        if (((LinearLayoutManager) recyclerView.getLayoutManager()).getChildCount() != 0) {
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View childAt = recyclerView.getChildAt(i);
                canvas.drawLine((float) a2, (float) childAt.getBottom(), (float) (recyclerView.getWidth() - a2), (float) childAt.getBottom(), this.b);
            }
        }
    }
}
