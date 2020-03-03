package com.xiaomi.smarthome.camera.activity.sdcard;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.mijia.app.AppConfig;

public class SDCardItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    SDCardItemDecoration() {
        this.space = 0;
        this.space = (int) (AppConfig.d * 2.0f);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i;
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i2 = this.space * 2;
        int i3 = this.space * 2;
        if (childAdapterPosition >= 0) {
            int i4 = childAdapterPosition + 1;
            if (i4 <= 3) {
                i2 = this.space * 7;
            }
            int i5 = i4 % 3;
            int i6 = 0;
            if (i5 == 1) {
                i6 = this.space * 7;
                i = this.space;
            } else if (i5 == 2) {
                i = this.space;
            } else {
                i = this.space * 7;
            }
            rect.set(i6, i2, i, i3);
        }
    }
}
