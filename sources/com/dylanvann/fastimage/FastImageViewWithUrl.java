package com.dylanvann.fastimage;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;
import com.bumptech.glide.load.model.GlideUrl;
import com.xiaomi.youpin.log.LogUtils;

class FastImageViewWithUrl extends ImageView {
    public GlideUrl glideUrl;

    public FastImageViewWithUrl(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.postCatchedException(new IllegalArgumentException("draw failed with url: " + this.glideUrl.b()));
        }
    }
}
