package com.mi.global.shop.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import com.mi.global.shop.util.FontUtil;

public class CustomEditTextView extends AppCompatEditText {
    private static final String TAG = "CustomTextView";

    public CustomEditTextView(Context context) {
        super(context);
        FontUtil.a(this, context);
    }

    public CustomEditTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        FontUtil.a(this, context);
    }

    public CustomEditTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        FontUtil.a(this, context);
    }
}
