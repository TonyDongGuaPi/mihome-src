package com.xiaomi.youpin;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class CenterDrawableTextView extends TextView {
    @TargetApi(21)
    public CenterDrawableTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public CenterDrawableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CenterDrawableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CenterDrawableTextView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        CenterDrawableHelper.a(this, canvas);
        super.onDraw(canvas);
    }
}
