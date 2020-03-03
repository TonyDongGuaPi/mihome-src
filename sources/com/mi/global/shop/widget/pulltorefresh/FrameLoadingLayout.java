package com.mi.global.shop.widget.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import com.mi.global.shop.R;

public class FrameLoadingLayout extends LoadingLayout {
    /* access modifiers changed from: protected */
    public void onLoadingDrawableSet(Drawable drawable) {
    }

    /* access modifiers changed from: protected */
    public void onPullImpl(float f) {
    }

    /* access modifiers changed from: protected */
    public void pullToRefreshImpl() {
    }

    /* access modifiers changed from: protected */
    public void refreshingImpl() {
    }

    /* access modifiers changed from: protected */
    public void releaseToRefreshImpl() {
    }

    /* access modifiers changed from: protected */
    public void resetImpl() {
    }

    public FrameLoadingLayout(Context context, TypedArray typedArray, boolean z) {
        super(context, typedArray, z);
    }

    /* access modifiers changed from: protected */
    public int getDefaultDrawableResId() {
        return R.drawable.shop_mi_rabbit;
    }
}
