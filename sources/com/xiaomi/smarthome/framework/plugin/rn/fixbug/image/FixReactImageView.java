package com.xiaomi.smarthome.framework.plugin.rn.fixbug.image;

import android.content.Context;
import android.graphics.Canvas;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.views.image.GlobalImageLoadListener;
import com.facebook.react.views.image.ReactImageView;
import javax.annotation.Nullable;

public class FixReactImageView extends ReactImageView {
    boolean mFiltered = false;

    public FixReactImageView(Context context, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable GlobalImageLoadListener globalImageLoadListener, @Nullable Object obj) {
        super(context, abstractDraweeControllerBuilder, globalImageLoadListener, obj);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mFiltered) {
            getDrawable().setFilterBitmap(false);
            this.mFiltered = false;
        }
        super.onDraw(canvas);
    }
}
