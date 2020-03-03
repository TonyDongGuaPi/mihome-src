package com.mi.global.bbs.view.Editor;

import android.content.Context;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;

public class FontButton extends FontTextView {
    public FontButton(Context context) {
        super(context);
    }

    public FontButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyle);
    }

    public FontButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CharSequence getAccessibilityClassName() {
        return FontButton.class.getName();
    }
}
