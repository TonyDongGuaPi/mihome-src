package com.mi.global.shop.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.mi.global.shop.util.FontUtil;

public class CustomTextView extends AppCompatTextView {
    private static final String TAG = "CustomTextView";

    public CustomTextView(Context context) {
        super(context);
        FontUtil.a(this, context);
    }

    public CustomTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        FontUtil.a(this, context);
    }

    public CustomTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        FontUtil.a(this, context);
    }
}
