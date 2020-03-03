package com.mi.global.shop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import com.mi.global.shop.util.FontUtil;

public class CustomButtonView extends Button {
    private static final String TAG = "CustomTextView";

    public CustomButtonView(Context context) {
        super(context);
        FontUtil.a(this, context);
    }

    public CustomButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        FontUtil.a(this, context);
    }

    public CustomButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        FontUtil.a(this, context);
    }
}
