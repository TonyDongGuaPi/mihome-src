package com.xiaomi.miot.store.component.image;

import android.content.Context;
import android.graphics.Canvas;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.views.image.GlobalImageLoadListener;
import com.xiaomi.youpin.log.LogUtils;
import javax.annotation.Nullable;

public class ReactImageView extends com.facebook.react.views.image.ReactImageView {
    private ReadableArray mSourceString;

    public ReactImageView(Context context, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable GlobalImageLoadListener globalImageLoadListener, @Nullable Object obj) {
        super(context, abstractDraweeControllerBuilder, globalImageLoadListener, obj);
    }

    public void setSource(@Nullable ReadableArray readableArray) {
        super.setSource(readableArray);
        if (readableArray != null) {
            this.mSourceString = readableArray;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.postCatchedException(new RuntimeException("source: " + this.mSourceString));
        }
    }
}
