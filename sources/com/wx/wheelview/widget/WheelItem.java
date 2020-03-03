package com.wx.wheelview.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.wheelview.util.WheelUtils;

public class WheelItem extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f9879a;
    private TextView b;

    public WheelItem(Context context) {
        super(context);
        a();
    }

    public WheelItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public WheelItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, WheelUtils.a(getContext(), 45.0f));
        linearLayout.setOrientation(0);
        linearLayout.setPadding(20, 20, 20, 20);
        linearLayout.setGravity(17);
        addView(linearLayout, layoutParams);
        this.f9879a = new ImageView(getContext());
        this.f9879a.setTag(100);
        this.f9879a.setVisibility(8);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.rightMargin = 20;
        linearLayout.addView(this.f9879a, layoutParams2);
        this.b = new TextView(getContext());
        this.b.setTag(101);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        this.b.setSingleLine();
        this.b.setIncludeFontPadding(false);
        this.b.setGravity(17);
        this.b.setTextColor(-16777216);
        linearLayout.addView(this.b, new FrameLayout.LayoutParams(-1, -1));
    }

    public void setText(CharSequence charSequence) {
        this.b.setText(charSequence);
    }

    public void setImage(int i) {
        this.f9879a.setVisibility(0);
        this.f9879a.setImageResource(i);
    }
}
