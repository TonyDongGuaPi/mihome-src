package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.resource.c;

public final class as extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private c f9778a = null;
    private ImageView b = null;
    private ImageView c = null;

    public as(Context context) {
        super(context);
        this.f9778a = c.a(context);
        setBackgroundColor(0);
        setOrientation(1);
        this.c = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, a.C);
        layoutParams.gravity = 80;
        addView(this.c, layoutParams);
        Drawable a2 = this.f9778a.a(1001, -1, -1);
        if (this.b != null) {
            this.b.setBackgroundDrawable(a2);
        }
    }
}
