package com.mi.global.bbs.view.praise;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BgView extends View {
    private int height = 0;
    private int width = 0;

    public BgView(Context context) {
        super(context);
    }

    public BgView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BgView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.width != 0 && this.height != 0) {
            setMeasuredDimension(this.width, this.height);
        }
    }
}
