package com.mi.global.shop.widget.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import com.mi.global.shop.R;

public class FestivalLoadingLayout extends LoadingLayout {
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
    public void releaseToRefreshImpl() {
    }

    public FestivalLoadingLayout(Context context, TypedArray typedArray, int i) {
        super(context, typedArray, 0);
    }

    /* access modifiers changed from: protected */
    public int getDefaultDrawableResId() {
        return R.drawable.shop_mi_rabbit_1;
    }

    /* access modifiers changed from: protected */
    public void refreshingImpl() {
        getGif_bg().setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void resetImpl() {
        getGif_bg().setVisibility(8);
    }
}
