package com.xiaomi.yp_pic_pick.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.youpin.log.LogUtils;

public class YPSimpleDraweeView extends SimpleDraweeView {
    String mSourceUrl;

    public YPSimpleDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
    }

    public YPSimpleDraweeView(Context context) {
        super(context);
    }

    public YPSimpleDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public YPSimpleDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public YPSimpleDraweeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setSourceUrl(String str) {
        this.mSourceUrl = str;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.postCatchedException(new RuntimeException(e.getMessage() + " source: " + this.mSourceUrl));
        }
    }
}
