package com.xiaomi.jr.mipay.safekeyboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KeyboardBarView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private TextView f10974a;

    public KeyboardBarView(Context context) {
        this(context, (AttributeSet) null);
    }

    public KeyboardBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public KeyboardBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.jr_mipay_safe_keyboard_view_keyboard_bar, this, true);
        this.f10974a = (TextView) findViewById(R.id.title_textview);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Jr_Mipay_SafeKeyboard);
        String string = obtainStyledAttributes.getString(R.styleable.Jr_Mipay_SafeKeyboard_keyboardBarTitle);
        obtainStyledAttributes.recycle();
        if (string != null) {
            setTitle(string);
        }
    }

    public void setTitle(String str) {
        this.f10974a.setText(str);
    }
}
