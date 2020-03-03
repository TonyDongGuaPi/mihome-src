package com.xiaomi.smarthome.library.common.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class AdjustImageView extends ImageView {
    public AdjustImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int a2 = DisplayUtils.a((Activity) getContext()).x - (DisplayUtils.a(17.0f) * 2);
        setMeasuredDimension(a2, (int) (((float) a2) * 0.409f));
    }
}
