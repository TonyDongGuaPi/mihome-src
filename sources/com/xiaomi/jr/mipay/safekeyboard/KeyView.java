package com.xiaomi.jr.mipay.safekeyboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KeyView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private TextView f10973a;
    private ImageView b;

    public KeyView(Context context) {
        this(context, (AttributeSet) null);
    }

    public KeyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public KeyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.jr_mipay_safe_keyboard_view_key, this, true);
        this.f10973a = (TextView) findViewById(R.id.label_textview);
        this.b = (ImageView) findViewById(R.id.icon_imageview);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Jr_Mipay_SafeKeyboard_Key);
        String string = obtainStyledAttributes.getString(R.styleable.Jr_Mipay_SafeKeyboard_Key_keyLabel);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.Jr_Mipay_SafeKeyboard_Key_keyIcon);
        int color = obtainStyledAttributes.getColor(R.styleable.Jr_Mipay_SafeKeyboard_keyLabelColor, -16777216);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Jr_Mipay_SafeKeyboard_keyLabelSize, 60);
        obtainStyledAttributes.recycle();
        if (string != null) {
            setLabel(string, color, dimensionPixelSize);
        }
        if (drawable != null) {
            setIcon(drawable);
        }
    }

    public void setLabel(String str, int i, int i2) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("<br>", "\n").replace("<br/>", "\n").replace("<br />", "\n");
        }
        this.b.setVisibility(8);
        this.f10973a.setVisibility(0);
        this.f10973a.setText(str);
        this.f10973a.setTextColor(i2);
        this.f10973a.setTextSize(0, (float) i);
    }

    public void setIcon(Drawable drawable) {
        this.f10973a.setVisibility(8);
        this.b.setVisibility(0);
        this.b.setImageDrawable(drawable);
    }
}
