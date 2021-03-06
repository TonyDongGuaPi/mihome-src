package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.libra.Color;
import com.unionpay.mobile.android.data.a;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class ad extends z {

    /* renamed from: a  reason: collision with root package name */
    private int f9767a;
    private String b;
    private TextView c;
    private TextView o;

    public ad(Context context, int i, JSONObject jSONObject, String str) {
        this(context, i, jSONObject, str, (byte) 0);
    }

    private ad(Context context, int i, JSONObject jSONObject, String str, byte b2) {
        super(context, jSONObject, str);
        this.f9767a = 0;
        this.f9767a = i;
        if (jSONObject != null) {
            this.b = j.a(jSONObject, "style");
        }
        RelativeLayout relativeLayout = this.m;
        LinearLayout linearLayout = new LinearLayout(this.d);
        relativeLayout.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(0);
        this.c = new TextView(this.d);
        this.c.setTextSize(b.k);
        this.c.setText(p());
        this.c.setGravity(3);
        this.c.setTextColor(-13421773);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 0.3f);
        layoutParams.gravity = 3;
        linearLayout.addView(this.c, layoutParams);
        this.o = new TextView(this.d);
        this.o.setGravity(16);
        this.o.setTextSize(b.k);
        this.o.setText(a.a(i(), this.b));
        this.o.setPadding(0, 0, com.unionpay.mobile.android.global.a.g, 0);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 0.7f);
        layoutParams2.gravity = 21;
        linearLayout.addView(this.o, layoutParams2);
    }

    public ad(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
        this.f9767a = 0;
        String p = p();
        if (p != null && p.length() > 0) {
            this.c = new TextView(this.d);
            this.c.setTextSize(b.k);
            this.c.setText(p());
            this.c.setTextColor(Color.c);
            addView(this.c);
        }
        String i = i();
        if (i != null && i.length() > 0) {
            this.o = new TextView(this.d);
            this.o.setTextSize(b.k);
            this.o.setTextColor(Color.c);
            this.o.setText(i());
            addView(this.o);
        }
    }

    public final String a() {
        return null;
    }

    public final void a(float f) {
        this.o.setTextSize(f);
    }

    public final boolean b() {
        return true;
    }

    public final boolean c() {
        return true;
    }

    public final void g() {
        this.o.setTextColor(-6710887);
    }
}
