package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.libra.Color;
import com.mi.mistatistic.sdk.data.EventData;
import com.unionpay.mobile.android.resource.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.j;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import org.json.JSONObject;

abstract class ba extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private String f9784a = null;
    private String b = null;
    private String c = null;
    protected Context d = null;
    protected int e = -16777216;
    protected int f = Color.c;
    protected String g = null;
    protected String h = null;
    protected boolean i = false;
    protected String j = null;
    protected LinearLayout k = null;
    protected TextView l = null;
    protected RelativeLayout m = null;
    protected JSONObject n;
    private String o = null;
    private TextView p = null;
    private ImageView q = null;
    private boolean r = false;
    private String s = "uppay";

    public interface a {
        String a();

        boolean b();

        boolean c();
    }

    public ba(Context context, JSONObject jSONObject, String str) {
        super(context);
        this.n = jSONObject;
        this.d = context;
        this.h = j.a(jSONObject, "label");
        this.o = j.a(jSONObject, "placeholder");
        this.c = j.a(jSONObject, "tip");
        this.f9784a = j.a(jSONObject, "name");
        this.g = j.a(jSONObject, "value");
        this.b = j.a(jSONObject, "type");
        this.j = j.a(jSONObject, "regexp");
        String a2 = j.a(jSONObject, ShareDeviceActivity.KEY_SHARE_TYPE_READONLY);
        if (a2 != null && a2.equalsIgnoreCase("true")) {
            this.i = true;
        }
        this.r = j.a(jSONObject, "margin").length() > 0;
        this.s = str;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        setBackgroundColor(0);
        setOrientation(1);
        setPadding(2, 2, 2, 2);
        if (!this.b.equalsIgnoreCase(EventData.b)) {
            if (!a((LinearLayout) this, this.h)) {
                this.p = new TextView(this.d);
                this.p.setTextSize(20.0f);
                this.p.setText("");
                this.p.setTextColor(this.e);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.leftMargin = com.unionpay.mobile.android.global.a.f;
                addView(this.p, layoutParams);
                if (!(this.h == null || this.h.length() == 0)) {
                    this.p.setText(this.h);
                }
                this.p.setVisibility(8);
            }
            g();
            if (!b_()) {
                this.k = new LinearLayout(this.d);
                this.k.setBackgroundColor(-267336);
                addView(this.k, new LinearLayout.LayoutParams(-1, -2));
                this.l = new TextView(this.d);
                this.l.setTextSize(15.0f);
                this.l.setTextColor(this.f);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                int a3 = g.a(this.d, 10.0f);
                layoutParams2.rightMargin = a3;
                layoutParams2.leftMargin = a3;
                int a4 = g.a(this.d, 5.0f);
                layoutParams2.bottomMargin = a4;
                layoutParams2.topMargin = a4;
                this.k.addView(this.l, layoutParams2);
                if (this.c == null || this.c.length() <= 0) {
                    this.k.setVisibility(8);
                    this.q.setVisibility(8);
                    return;
                }
                this.q.setVisibility(0);
                this.l.setText(this.c);
                return;
            }
            return;
        }
        g();
    }

    private void g() {
        FrameLayout frameLayout = new FrameLayout(this.d);
        addView(frameLayout, new LinearLayout.LayoutParams(-1, -2));
        this.m = new RelativeLayout(this.d);
        frameLayout.addView(this.m, new FrameLayout.LayoutParams(-1, -2));
        this.q = new ImageView(this.d);
        this.q.setBackgroundDrawable(c.a(this.d).a(1038, -1, -1));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(g.a(this.d, 10.0f), g.a(this.d, 5.0f));
        layoutParams.gravity = 80;
        layoutParams.leftMargin = g.a(this.d, 20.0f);
        this.q.setVisibility(8);
        frameLayout.addView(this.q, layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void a(CharSequence charSequence, TextView.BufferType bufferType) {
        if (this.p != null && charSequence.length() > 0) {
            this.p.setText(charSequence, bufferType);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(LinearLayout linearLayout, String str) {
        return false;
    }

    public boolean a(String str) {
        return str == null || str.length() == 0;
    }

    /* access modifiers changed from: protected */
    public boolean b_() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void c(String str) {
        if (this.l != null && str != null && str.length() > 0) {
            this.l.setText(str);
        }
    }

    /* access modifiers changed from: protected */
    public String d() {
        return "_input_method";
    }

    public boolean f() {
        return true;
    }

    public String i() {
        return this.g;
    }

    public final String n() {
        return this.f9784a;
    }

    public final String o() {
        return this.b;
    }

    public final String p() {
        return this.h;
    }

    public final String q() {
        return this.c;
    }

    public final String r() {
        return this.o;
    }

    /* access modifiers changed from: protected */
    public final String s() {
        return this.s;
    }

    /* access modifiers changed from: protected */
    public final void t() {
        if (this.p != null) {
            this.p.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public final void u() {
        if (this.l != null) {
            this.l.setVisibility(0);
            this.q.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public final void v() {
        if (this.p != null) {
            this.p.setTextSize(16.0f);
        }
    }
}
