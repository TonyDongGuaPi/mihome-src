package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.actions.SearchIntents;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

public final class ak extends b implements a.b {
    /* access modifiers changed from: private */
    public int r = 100;
    private int s = 20;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upwidget.a t = null;
    private TextView u = null;
    /* access modifiers changed from: private */
    public a v = null;
    private View.OnClickListener w = new al(this);
    private View.OnClickListener x = new am(this);

    public ak(Context context) {
        super(context);
        this.f = 10;
        this.q = "openupgrade";
        setBackgroundColor(-1052684);
        this.k = a();
        b();
        super.d();
        c();
    }

    private void s() {
        this.r = 103;
        StringBuilder sb = new StringBuilder();
        sb.append(this.s);
        k.c("uppay", sb.toString());
        this.e.a(SearchIntents.EXTRA_QUERY, this.f9608a.aj, 3);
        this.s--;
    }

    public final void a(a.C0077a aVar) {
        if (!aVar.a()) {
            a(aVar.b);
            return;
        }
        this.j = false;
        this.r = 101;
        this.b.a(c.bD.U);
        k.a("uppay", "sms elements:" + aVar.b);
        this.e.c("sms", aVar.b);
    }

    public final void a(JSONObject jSONObject) {
        switch (this.r) {
            case 101:
                this.v.a(b.p);
                this.b.c();
                this.r = 100;
                return;
            case 102:
                this.f9608a.aj = i.a(jSONObject.toString());
                if (this.f9608a.aj == null || this.f9608a.aj.length() <= 0) {
                    b(2);
                    return;
                }
                this.s = 20;
                s();
                return;
            case 103:
                String a2 = j.a(jSONObject, "status");
                String a3 = j.a(jSONObject, "fail_msg");
                this.f9608a.S = j.a(jSONObject, "open_info");
                this.f9608a.A = j.a(jSONObject, "title");
                this.f9608a.B = j.a(jSONObject, "succ_info");
                if (this.s <= 0 || !a2.equalsIgnoreCase("01")) {
                    this.r = 100;
                    i();
                    if (a2.equalsIgnoreCase("00")) {
                        d(11);
                        return;
                    } else if (a2.equalsIgnoreCase(Constant.RECHARGE_MODE_BUSINESS_OFFICE)) {
                        this.b.a(new an(this), (View.OnClickListener) null);
                        this.b.a(c.bD.ab, a3, c.bD.ac);
                        return;
                    } else if (this.s <= 0) {
                        b(20);
                        return;
                    } else {
                        return;
                    }
                } else {
                    s();
                    return;
                }
            default:
                return;
        }
    }

    public final void a(boolean z) {
        if (this.u != null) {
            this.u.setEnabled(!z);
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        ay ayVar = new ay(getContext(), this.f9608a.A, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void c() {
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setBackgroundColor(-1);
        boolean z = true;
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int i = com.unionpay.mobile.android.global.a.f;
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
        this.m.addView(linearLayout, layoutParams);
        this.v = new a(this.d, this.f9608a.T, this.e.c(), this, this.f9608a.aq, true, this.q);
        new LinearLayout.LayoutParams(-1, -1).topMargin = com.unionpay.mobile.android.global.a.f;
        linearLayout.addView(this.v);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        linearLayout2.setId(linearLayout2.hashCode());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.d;
        layoutParams2.leftMargin = com.unionpay.mobile.android.global.a.d;
        layoutParams2.addRule(3, linearLayout.getId());
        this.m.addView(linearLayout2, layoutParams2);
        Context context = this.d;
        JSONObject jSONObject = this.f9608a.al;
        View.OnClickListener onClickListener = this.x;
        this.t = new com.unionpay.mobile.android.upwidget.a(context, jSONObject, onClickListener, this.q + "_agree_user_protocol");
        linearLayout2.addView(this.t);
        this.u = new TextView(this.d);
        this.u.setText(j.a(this.f9608a.C, "label"));
        this.u.setTextSize(b.i);
        this.u.setTextColor(o());
        this.u.setGravity(17);
        int i2 = com.unionpay.mobile.android.global.a.n;
        this.u.setBackgroundDrawable(this.c.a(2008, -1, -1));
        this.u.setOnClickListener(this.w);
        TextView textView = this.u;
        if (this.v != null && !this.v.e()) {
            z = false;
        }
        textView.setEnabled(z);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams3.topMargin = com.unionpay.mobile.android.global.a.d;
        int i3 = com.unionpay.mobile.android.global.a.d;
        layoutParams3.rightMargin = i3;
        layoutParams3.leftMargin = i3;
        layoutParams3.addRule(3, linearLayout2.getId());
        this.m.addView(this.u, layoutParams3);
    }

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    public final void k() {
        if (!this.v.d()) {
            m();
        }
    }

    public final void r() {
    }
}
