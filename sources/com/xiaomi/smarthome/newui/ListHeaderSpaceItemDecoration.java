package com.xiaomi.smarthome.newui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class ListHeaderSpaceItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    private final int f20316a = DisplayUtils.a(7.0f);
    private Paint b = new Paint();

    public ListHeaderSpaceItemDecoration() {
        this.b.setColor(SHApplication.getAppContext().getResources().getColor(R.color.std_list_space_bg));
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (!(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            rect.contains(0, 0, 0, 0);
        } else if (recyclerView.getChildAdapterPosition(view) == 0) {
            rect.top = this.f20316a;
        } else {
            rect.top = 0;
        }
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }
}
