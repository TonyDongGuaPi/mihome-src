package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class w extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private String f9737a = null;
    private x b = null;

    private w(Context context, String str, String str2, Drawable drawable) {
        super(context);
        setOrientation(0);
        this.f9737a = str2;
        this.b = x.a(context, drawable);
        this.b.a((CharSequence) Html.fromHtml(String.format("<u>%s</u>", new Object[]{str})));
        this.b.a(h.a(-13601621, -15909519));
        addView(this.b);
    }

    public static final w a(Context context, JSONObject jSONObject, Drawable drawable) {
        if (jSONObject != null) {
            return new w(context, j.a(jSONObject, "label"), j.a(jSONObject, "href"), drawable);
        }
        return null;
    }

    public final String a() {
        return this.f9737a;
    }

    public final void a(View.OnClickListener onClickListener) {
        if (this.b != null) {
            this.b.setOnClickListener(onClickListener);
        }
    }
}
