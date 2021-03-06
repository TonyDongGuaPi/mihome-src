package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.b;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONObject;

public final class bi extends b implements b.a, b.C0078b {
    private static String w = "download://";
    private b r;
    private ViewGroup s;
    private int t;
    private boolean u;
    private boolean v;

    public bi(Context context) {
        this(context, false, false);
    }

    public bi(Context context, boolean z, boolean z2) {
        super(context);
        this.r = null;
        this.s = null;
        this.t = 0;
        this.u = false;
        this.v = false;
        this.f = 14;
        this.t = ((a.t - a.k) - a.b(this.d)) - (a.s * 3);
        this.u = z;
        this.v = z2;
        this.k = a();
        b();
        d();
    }

    public final void a(JSONObject jSONObject) {
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        ay ayVar = new ay(this.d, this.f9608a.af, this);
        if (this.u) {
            ayVar = new ay(this.d, this.f9608a.af, this.c.a(1030, -1, -1), g.a(this.d, 20.0f), this);
        }
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    public final void c(String str) {
        if (str != null && str.length() > 0 && str.startsWith(w)) {
            String substring = str.substring(w.length());
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(substring));
            this.d.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public final void d() {
        super.d();
        this.r = new b(this.d, this);
        this.r.setOnTouchListener(new bj(this));
        if (this.v) {
            this.r.a(w);
        }
        RelativeLayout.LayoutParams layoutParams = this.t == 0 ? new RelativeLayout.LayoutParams(-1, -1) : new RelativeLayout.LayoutParams(-1, this.t);
        layoutParams.addRule(3, this.k.getId());
        layoutParams.addRule(12, -1);
        this.m.addView(this.r, layoutParams);
        this.s = new RelativeLayout(this.d);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, a.t - a.k);
        layoutParams2.addRule(3, this.k.getId());
        layoutParams2.addRule(12, -1);
        layoutParams2.addRule(10, -1);
        layoutParams2.bottomMargin = 0;
        layoutParams2.topMargin = 0;
        this.m.addView(this.s, layoutParams2);
        ProgressBar progressBar = new ProgressBar(this.d);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13, -1);
        this.s.addView(progressBar, layoutParams3);
        this.r.b(this.f9608a.ag);
        if (this.u) {
            a(this.f9608a.bi, false);
        }
    }

    public final void k() {
        ((InputMethodManager) this.d.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        if (this.u) {
            this.b.a(new bk(this), new bl(this));
            this.b.a(c.bD.Y, c.bD.av, c.bD.W, c.bD.X);
            return;
        }
        super.k();
    }

    public final void r() {
        this.r.setVisibility(8);
        this.s.setVisibility(0);
    }

    public final void s() {
        this.r.setVisibility(0);
        this.s.setVisibility(8);
    }
}
