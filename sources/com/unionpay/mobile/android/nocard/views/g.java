package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.actions.SearchIntents;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.o;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONObject;

public final class g extends b implements a.b {
    private int r = 20;
    /* access modifiers changed from: private */
    public int s = 100;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upwidget.a t = null;
    private TextView u = null;
    /* access modifiers changed from: private */
    public a v = null;
    /* access modifiers changed from: private */
    public boolean w = false;
    private boolean x = true;
    private View.OnClickListener y = new h(this);
    private View.OnClickListener z = new i(this);

    public g(Context context) {
        super(context);
        this.f = 5;
        this.q = this.f9608a.K ? "entrust_phoneNO_change" : "entrust";
        this.x = true ^ this.f9608a.K;
        setBackgroundColor(-1052684);
        e();
    }

    private void d(JSONObject jSONObject) {
        i();
        this.f9608a.z = j.d(jSONObject, "rules");
        this.f9608a.A = j.a(jSONObject, "title");
        this.f9608a.C = j.c(jSONObject, "followrules_button");
        this.f9608a.al = j.c(jSONObject, "service_checkbox");
        this.f9608a.am = j.c(jSONObject, "bind_card_checkbox");
        this.f9608a.aq = j.a(jSONObject, "pan");
        if (this.f9608a.z == null || this.f9608a.z.length() <= 0) {
            b(2);
            return;
        }
        if (this.v != null) {
            this.v.f();
        }
        e a2 = f.a(jSONObject);
        this.f9608a.K = false;
        a(6, a2);
    }

    /* access modifiers changed from: private */
    public void s() {
        this.e.j(this.v.a("pan"));
        this.s = 104;
    }

    private void t() {
        this.s = 103;
        this.e.a(SearchIntents.EXTRA_QUERY, this.f9608a.aj, 3);
        this.r--;
    }

    public final void a(a.C0077a aVar) {
        if (aVar.a()) {
            this.j = false;
            this.s = 101;
            this.b.a(c.bD.U);
            k.a("uppay", "sms elements:" + aVar.b);
            this.e.c("sms", aVar.b);
            return;
        }
        a(aVar.b);
    }

    public final void a(JSONObject jSONObject) {
        this.x = false;
        switch (this.s) {
            case 101:
                this.v.a(b.p);
                this.b.c();
                this.s = 100;
                return;
            case 102:
                this.f9608a.aj = i.a(jSONObject.toString());
                if (this.f9608a.aj == null || this.f9608a.aj.length() <= 0) {
                    b(2);
                    return;
                }
                this.r = 20;
                t();
                return;
            case 103:
                String a2 = j.a(jSONObject, "status");
                String a3 = j.a(jSONObject, "fail_msg");
                if (this.r <= 0 || !a2.equalsIgnoreCase("01")) {
                    this.s = 100;
                    if (a2.equalsIgnoreCase("00")) {
                        this.w = true;
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.q);
                        sb.append("_open_succeed");
                        s();
                        return;
                    }
                    i();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.q);
                    sb2.append("_open_fail");
                    String[] strArr = o.j;
                    String[] strArr2 = {a2, a3};
                    if (a2.equalsIgnoreCase(Constant.RECHARGE_MODE_BUSINESS_OFFICE)) {
                        j jVar = new j(this);
                        k kVar = new k(this);
                        if (this.f9608a.F) {
                            this.b.a(jVar, kVar);
                            this.b.a(c.bD.ab, a3, c.bD.ac, c.bD.ad);
                            return;
                        }
                        this.b.a(jVar, (View.OnClickListener) null);
                        this.b.a(c.bD.ab, a3, c.bD.ac);
                        return;
                    } else if (this.r <= 0) {
                        a(this.f9608a.ak);
                        return;
                    } else {
                        return;
                    }
                } else {
                    t();
                    return;
                }
            case 104:
                if (!b(jSONObject)) {
                    d(jSONObject);
                    return;
                }
                return;
            case 105:
                i();
                int a4 = f.a(this.f9608a, jSONObject, false);
                if (a4 != 0) {
                    b(a4);
                    return;
                }
                e a5 = f.a(jSONObject);
                if (this.f9608a.z != null && this.f9608a.z.length() > 0) {
                    a(6, a5);
                    return;
                } else if (this.f9608a.D != null && this.f9608a.D.length() > 0) {
                    d(5);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public final void a(boolean z2) {
        if (this.u != null) {
            this.u.setEnabled(!z2);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a(String str, JSONObject jSONObject) {
        this.x = false;
        return false;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        ay ayVar = new ay(getContext(), this.f9608a.A, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void b(String str, JSONObject jSONObject) {
        if ("init".equals(str)) {
            a(2);
        } else if ("".equals(str)) {
            d(jSONObject);
        } else {
            this.b.a(c.bD.U);
            this.j = false;
            this.s = 105;
            this.e.c(str, "");
        }
    }

    /* access modifiers changed from: protected */
    public final void c() {
        this.o.a((UPScrollView.a) this);
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int i = com.unionpay.mobile.android.global.a.d;
        this.m.addView(linearLayout, layoutParams);
        this.v = new a(this.d, this.f9608a.D, this.e.c(), this, this.f9608a.aq, true, this.q);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
        linearLayout.addView(this.v, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.topMargin = i;
        layoutParams3.leftMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams3.addRule(3, linearLayout.getId());
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        boolean z2 = false;
        linearLayout2.setOrientation(0);
        linearLayout2.setId(linearLayout2.hashCode());
        Context context = this.d;
        JSONObject jSONObject = this.f9608a.al;
        View.OnClickListener onClickListener = this.z;
        this.t = new com.unionpay.mobile.android.upwidget.a(context, jSONObject, onClickListener, this.q + "_agree_user_protocol");
        linearLayout2.addView(this.t, new LinearLayout.LayoutParams(-2, -2));
        this.m.addView(linearLayout2, layoutParams3);
        this.u = new TextView(this.d);
        this.u.setText(j.a(this.f9608a.C, "label"));
        this.u.setTextSize(b.i);
        this.u.setTextColor(o());
        this.u.setGravity(17);
        int i2 = com.unionpay.mobile.android.global.a.n;
        this.u.setBackgroundDrawable(this.c.a(2008, -1, -1));
        this.u.setOnClickListener(this.y);
        TextView textView = this.u;
        if (this.v == null || this.v.e()) {
            z2 = true;
        }
        textView.setEnabled(z2);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams4.topMargin = com.unionpay.mobile.android.global.a.f;
        int a2 = com.unionpay.mobile.android.utils.g.a(this.d, 10.0f);
        layoutParams4.rightMargin = a2;
        layoutParams4.leftMargin = a2;
        layoutParams4.addRule(3, linearLayout2.getId());
        this.m.addView(this.u, layoutParams4);
    }

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    public final void k() {
        if (!this.v.d()) {
            if (this.f9608a.L) {
                a(13);
                this.f9608a.L = false;
            } else if (!this.f9608a.K || !this.x) {
                this.f9608a.K = false;
                this.f9608a.G = null;
                a(2);
            } else {
                this.f9608a.K = false;
                f.a(this.f9608a, this.f9608a.G);
                m();
            }
        }
    }

    public final void r() {
    }
}
