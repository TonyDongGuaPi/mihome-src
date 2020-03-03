package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import org.json.JSONArray;
import org.json.JSONObject;

public final class i extends AbstractMethod {
    /* access modifiers changed from: private */
    public JSONObject g;
    /* access modifiers changed from: private */
    public JSONObject h;
    private a i;
    private TextView j;
    private TextView k;
    private RelativeLayout l;

    public i(Context context) {
        super(context);
    }

    private static JSONArray e(String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("label", "");
            jSONObject.put("name", "user_name");
            if (!a(str)) {
                jSONObject.put("value", str);
            }
            jSONObject.put("regexp", "[.@_A-Za-z0-9]{1,64}");
            jSONObject.put("type", "user_name");
            jSONObject.put("tip", "");
            jSONObject.put("placeholder", c.bD.bw);
            jSONArray.put(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("label", "");
            jSONObject2.put("name", "password");
            jSONObject2.put("type", "password");
            jSONObject2.put("placeholder", c.bD.bx);
            jSONArray.put(jSONObject2);
        } catch (Exception unused) {
        }
        return jSONArray;
    }

    public final i a(JSONObject jSONObject) {
        this.g = jSONObject;
        if (this.j != null) {
            String a2 = a(this.g, "label");
            if (!TextUtils.isEmpty(a2)) {
                this.j.setText(Html.fromHtml(a2));
                if (this.l != null) {
                    this.l.setVisibility(0);
                }
            }
        }
        return this;
    }

    public final void a(RelativeLayout relativeLayout) {
        TextView textView = new TextView(this.b);
        textView.setText(this.c);
        textView.setTextColor(-13421773);
        textView.setTextSize(b.k);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        layoutParams.leftMargin = g.a(this.b, 10.0f);
        relativeLayout.addView(textView, layoutParams);
    }

    public final int b() {
        return l.c.intValue();
    }

    public final i b(String str) {
        this.c = str;
        return this;
    }

    public final i b(JSONObject jSONObject) {
        this.h = jSONObject;
        if (this.k != null) {
            String a2 = a(this.h, "label");
            if (!TextUtils.isEmpty(a2)) {
                this.k.setText(Html.fromHtml(a2));
                if (this.l != null) {
                    this.l.setVisibility(0);
                }
            }
        }
        return this;
    }

    public final void b(RelativeLayout relativeLayout) {
        this.i = new a(this.b, e(PreferenceUtils.c(this.b)), this, "");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
        relativeLayout.addView(this.i, layoutParams);
    }

    public final a.C0077a c() {
        if (this.i != null) {
            return this.i.b();
        }
        return null;
    }

    public final void c(RelativeLayout relativeLayout) {
        String a2 = a(this.g, "label");
        this.j = new TextView(this.b);
        a(this.j);
        if (!TextUtils.isEmpty(a2)) {
            this.j.setText(Html.fromHtml(a2));
        }
        this.j.setOnClickListener(new j(this));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        layoutParams.leftMargin = g.a(this.b, 10.0f);
        relativeLayout.addView(this.j, layoutParams);
        String a3 = a(this.h, "label");
        this.k = new TextView(this.b);
        a(this.k);
        if (!TextUtils.isEmpty(a3)) {
            this.k.setText(Html.fromHtml(a3));
        }
        this.k.setOnClickListener(new k(this));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(11, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.rightMargin = g.a(this.b, 10.0f);
        relativeLayout.addView(this.k, layoutParams2);
        if (TextUtils.isEmpty(a3) && TextUtils.isEmpty(a2)) {
            relativeLayout.setVisibility(8);
        }
        this.l = relativeLayout;
    }

    public final i d(String str) {
        this.d = str;
        return this;
    }

    public final String e() {
        return this.d;
    }

    public final boolean f() {
        return this.i == null || this.i.e();
    }

    public final String h() {
        return this.i != null ? this.i.b("user_name") : "";
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextUtils.isEmpty(h());
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.i != null) {
            this.i.f();
        }
    }

    public final void r() {
    }
}
