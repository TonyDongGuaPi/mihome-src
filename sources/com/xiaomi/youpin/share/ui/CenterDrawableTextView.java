package com.xiaomi.youpin.share.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CenterDrawableTextView extends AppCompatTextView {
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
