package com.xiaomi.smarthome.camera.view.recycle;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.mijia.app.AppConfig;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int i) {
        this.space = (int) (((float) i) * AppConfig.d);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.right = this.space;
        rect.bottom = this.space;
        rect.top = 0;
        rect.left = 0;
    }
}
