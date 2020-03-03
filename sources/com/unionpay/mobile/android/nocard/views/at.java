package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.text.TextUtils;
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
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.o;
import com.unionpay.mobile.android.widgets.aj;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.mobile.android.widgets.p;
import com.unionpay.mobile.android.widgets.z;
import com.unionpay.tsmservice.data.Constant;
import org.json.JSONArray;
import org.json.JSONObject;

public final class at extends b implements a.b {
    /* access modifiers changed from: private */
    public a A;
    /* access modifiers changed from: private */
    public a B;
    /* access modifiers changed from: private */
    public boolean C;
    private boolean D;
    /* access modifiers changed from: private */
    public String E;
    private View.OnClickListener F;
    private View.OnClickListener G;
    private View.OnClickListener H;
    private View.OnClickListener I;
    private View.OnClickListener J;
    LinearLayout r;
    private String s;
    private int t;
    /* access modifiers changed from: private */
    public int u;
    private int v;
    /* access modifiers changed from: private */
    public int w;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upwidget.a x;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upwidget.a y;
    private TextView z;

    public at(Context context) {
        this(context, (e) null);
    }

    public at(Context context, e eVar) {
        super(context, eVar);
        this.s = "00";
        this.t = 0;
        this.u = 0;
        this.v = 20;
        this.w = 5;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = false;
        this.D = false;
        this.r = new LinearLayout(this.d);
        this.F = new au(this);
        this.G = new av(this);
        this.H = new aw(this);
        this.I = new ax(this);
        this.J = new ay(this);
        this.f = 6;
        this.q = this.f9608a.K ? "bankpay_phoneNO_change" : "bankpay";
        this.D = !this.f9608a.K;
        setBackgroundColor(-1052684);
        e();
    }

    static /* synthetic */ void a(at atVar, String str, String str2) {
        atVar.u = 8;
        atVar.b.a(c.bD.U);
        atVar.e.c(str, str2);
    }

    static /* synthetic */ void a(at atVar, boolean z2, String str) {
        atVar.j = false;
        if (!z2) {
            atVar.u = 2;
            atVar.e.c(atVar.f9608a.E, str);
            return;
        }
        atVar.e(str);
    }

    private void d(String str) {
        a(str, (View.OnClickListener) new az(this), (View.OnClickListener) new ba(this));
    }

    /* access modifiers changed from: private */
    public void d(String str, String str2) {
        this.u = 9;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.w--;
    }

    private static boolean d(JSONObject jSONObject) {
        return "0".equalsIgnoreCase(j.a(jSONObject, "reopen_flag"));
    }

    /* access modifiers changed from: private */
    public void e(String str) {
        this.j = false;
        this.u = 3;
        this.e.c(bh.a(this.f9608a.C), bh.c("1", "1", this.x != null ? this.x.a() : null, str));
    }

    private void f(int i) {
        this.u = 4;
        this.t = i;
        this.e.a(SearchIntents.EXTRA_QUERY, this.f9608a.aj, 3);
        this.v--;
    }

    static /* synthetic */ void g(at atVar) {
        atVar.f9608a.K = true;
        atVar.b.a(c.bD.U);
        atVar.j = false;
        atVar.u = 7;
        atVar.e.c("reopenrules", "");
    }

    /* access modifiers changed from: private */
    public String s() {
        String str = "";
        if (this.B != null) {
            a.C0077a b = this.B.b();
            if (b.a()) {
                str = str + b.b;
            }
        }
        if (this.A == null) {
            return str;
        }
        a.C0077a b2 = this.A.b();
        if (!b2.a()) {
            return str;
        }
        String str2 = b2.b;
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (!TextUtils.isEmpty(str)) {
            str = str + ",";
        }
        return str + str2;
    }

    public final void a(a.C0077a aVar) {
        if (!aVar.a()) {
            a(aVar.b);
            return;
        }
        k.a("uppay", "sms elements:" + aVar.b);
        this.j = false;
        this.b.a(c.bD.U);
        this.e.c("sms", aVar.b);
        this.u = 1;
    }

    public final void a(JSONObject jSONObject) {
        this.D = false;
        int i = this.u;
        if (i != 16) {
            switch (i) {
                case 1:
                    i();
                    this.u = 0;
                    this.B.a(b.p);
                    return;
                case 2:
                case 3:
                    this.f9608a.aj = i.a(jSONObject.toString());
                    String a2 = j.a(jSONObject, "qn");
                    if (!TextUtils.isEmpty(a2)) {
                        this.f9608a.n = this.e.h(com.unionpay.mobile.android.utils.c.b(a2));
                    }
                    if (this.f9608a.aj == null || this.f9608a.aj.length() <= 0) {
                        b(2);
                        return;
                    }
                    this.v = 20;
                    f(this.u);
                    return;
                case 4:
                    this.s = j.a(jSONObject, "status");
                    if (d(jSONObject)) {
                        d(j.a(jSONObject, "fail_msg"));
                        return;
                    } else if (this.v <= 0 || !this.s.equalsIgnoreCase("01")) {
                        this.u = 0;
                        if (this.s.equalsIgnoreCase("00")) {
                            if (this.t != 2) {
                                i();
                                this.u = 0;
                                this.f9608a.H = j.d(jSONObject, "result");
                                this.f9608a.P = j.a(jSONObject, "openupgrade_flag");
                                this.f9608a.Q = j.a(jSONObject, "temporary_pay_flag");
                                this.f9608a.R = j.a(jSONObject, "temporary_pay_info");
                                this.f9608a.V = j.a(jSONObject, "front_url");
                                this.f9608a.W = j.a(jSONObject, "front_request");
                                this.f9608a.A = j.a(jSONObject, "title");
                                this.f9608a.B = j.a(jSONObject, "succ_info");
                                com.unionpay.mobile.android.nocard.utils.b.b(jSONObject, this.f9608a);
                                com.unionpay.mobile.android.nocard.utils.b.a(jSONObject, this.f9608a);
                                if (this.B != null) {
                                    this.B.f();
                                }
                                StringBuilder sb = new StringBuilder();
                                sb.append(this.q);
                                sb.append("_succeed");
                                if (this.f9608a.f) {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(this.f9608a.aQ);
                                    PreferenceUtils.c(this.d, sb2.toString());
                                    this.f9608a.I.f = "success";
                                    j();
                                    return;
                                }
                                d(8);
                                return;
                            }
                            this.C = true;
                            e(s());
                            return;
                        } else if (this.s.equalsIgnoreCase(Constant.RECHARGE_MODE_BUSINESS_OFFICE)) {
                            String a3 = j.a(jSONObject, "fail_msg");
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(this.q);
                            sb3.append("_fail");
                            String[] strArr = o.j;
                            String[] strArr2 = {this.s, a3};
                            if (this.t == 3) {
                                a(a3);
                                return;
                            }
                            bb bbVar = new bb(this);
                            bc bcVar = new bc(this);
                            if (this.f9608a.F) {
                                this.b.a(bbVar, bcVar);
                                this.b.a(c.bD.ab, a3, c.bD.ac, c.bD.ad);
                                return;
                            }
                            this.b.a(bbVar, (View.OnClickListener) null);
                            this.b.a(c.bD.ab, a3, c.bD.ac);
                            return;
                        } else if (this.v <= 0) {
                            String c = c(19);
                            if (this.f9608a.ak != null && !TextUtils.isEmpty(this.f9608a.ak)) {
                                c = this.f9608a.ak;
                            }
                            if (this.t == 3) {
                                a(c, true);
                                return;
                            } else {
                                a(c);
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        f(this.t);
                        return;
                    }
                default:
                    switch (i) {
                        case 6:
                            i();
                            int a4 = f.a(this.f9608a, jSONObject, true);
                            if (a4 != 0) {
                                b(a4);
                            } else {
                                this.f9608a.K = true;
                                e a5 = f.a(jSONObject);
                                if (this.f9608a.z != null && this.f9608a.z.length() > 0) {
                                    a(6, a5);
                                } else if (this.f9608a.D != null && this.f9608a.D.length() > 0) {
                                    d(5);
                                }
                            }
                            this.u = 0;
                            return;
                        case 7:
                            i();
                            int a6 = f.a(this.f9608a, jSONObject, false);
                            if (a6 != 0) {
                                b(a6);
                                return;
                            }
                            e a7 = f.a(jSONObject);
                            if (this.f9608a.z != null && this.f9608a.z.length() > 0) {
                                a(6, a7);
                                return;
                            } else if (this.f9608a.D != null && this.f9608a.D.length() > 0) {
                                d(5);
                                return;
                            } else {
                                return;
                            }
                        case 8:
                            i();
                            JSONArray d = j.d(jSONObject, "options");
                            if (this.A != null) {
                                this.A.a(d);
                                return;
                            }
                            return;
                        case 9:
                            String a8 = j.a(jSONObject, "status");
                            if (a8 == null || !"01".equals(a8)) {
                                JSONArray d2 = j.d(jSONObject, "options");
                                String a9 = j.a(jSONObject, "empty_info");
                                if (this.A != null) {
                                    this.A.a(d2, a9);
                                    return;
                                }
                                return;
                            }
                            String a10 = j.a(jSONObject, "uuid");
                            if (this.w >= 0) {
                                d(this.E, a10);
                                return;
                            }
                            String str = c.bD.D;
                            if (this.A != null) {
                                this.A.a((JSONArray) null, str);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
            }
        } else {
            if (this.b.a()) {
                this.b.c();
            }
            new JSONObject();
            if (TextUtils.isEmpty(j.a(jSONObject, "instalment_empty_info"))) {
                jSONObject = j.c(jSONObject, "instalment");
            }
            this.A.a(jSONObject);
            this.u = 0;
        }
    }

    public final void a(boolean z2) {
        if (this.z != null) {
            this.z.setEnabled(!z2);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a(String str, JSONObject jSONObject) {
        this.D = false;
        if (this.u != 1 || !d(jSONObject)) {
            return false;
        }
        d(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        ay ayVar = new ay(getContext(), this.f9608a.A, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    public final void b(int i) {
        if (this.u == 16) {
            if (this.b != null) {
                this.b.c();
            }
            z c = this.A.c("instalment");
            if (c != null) {
                p pVar = (p) c;
                pVar.a(false);
                pVar.b(false);
            }
        }
        super.b(i);
    }

    /* access modifiers changed from: protected */
    public final void c() {
        this.m.removeAllViews();
        this.o.a((UPScrollView.a) this);
        this.r.setId(this.r.hashCode());
        this.r.setOrientation(1);
        this.r.setBackgroundColor(0);
        this.m.addView(this.r, new RelativeLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout = this.r;
        linearLayout.removeAllViews();
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.model.f fVar = (com.unionpay.mobile.android.model.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.f9608a.aU = fVar.a("promotion_instalment_msgbox");
        }
        if (jSONArray.length() > 0) {
            this.A = new a(this.d, jSONArray, this, this.q);
            this.A.a(this.b, this.f9608a.aU);
            this.A.d(this.f9608a.bt);
            this.A.a(this.G);
            this.A.b(this.H);
            this.A.c(this.I);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
            linearLayout.addView(this.A, layoutParams);
        }
        a aVar = r0;
        a aVar2 = new a(this.d, this.f9608a.z, this.e.c(), this, this.f9608a.aq, true, false, this.A != null ? this.A.c("instalment") : null, this.f9608a.ad, this.q);
        this.B = aVar;
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
        linearLayout.addView(this.B, layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        boolean z2 = true;
        linearLayout2.setOrientation(1);
        linearLayout2.setId(linearLayout2.hashCode());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(3, this.r.getId());
        layoutParams3.leftMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams3.topMargin = layoutParams3.leftMargin;
        this.m.addView(linearLayout2, layoutParams3);
        if (!(!h() && this.f9608a.al == null && this.f9608a.am == null)) {
            if (this.f9608a.al != null) {
                Context context = this.d;
                JSONObject jSONObject = this.f9608a.al;
                View.OnClickListener onClickListener = this.J;
                this.y = new com.unionpay.mobile.android.upwidget.a(context, jSONObject, onClickListener, this.q + "_agree_user_protocol");
                linearLayout2.addView(this.y);
            }
            if (this.f9608a.am != null) {
                Context context2 = this.d;
                JSONObject jSONObject2 = this.f9608a.am;
                this.x = new com.unionpay.mobile.android.upwidget.a(context2, jSONObject2, (View.OnClickListener) null, this.q + "_remember_bankNO");
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams4.topMargin = com.unionpay.mobile.android.global.a.f;
                linearLayout2.addView(this.x, layoutParams4);
            }
        }
        this.z = new TextView(this.d);
        this.z.setText(j.a(this.f9608a.C, "label"));
        this.z.setTextSize(b.i);
        this.z.setTextColor(o());
        this.z.setGravity(17);
        TextView textView = this.z;
        if (this.B != null && !this.B.e()) {
            z2 = false;
        }
        textView.setEnabled(z2);
        int i = com.unionpay.mobile.android.global.a.n;
        this.z.setBackgroundDrawable(this.c.a(2008, -1, -1));
        this.z.setOnClickListener(this.F);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, i);
        layoutParams5.addRule(3, linearLayout2.getId());
        layoutParams5.topMargin = com.unionpay.mobile.android.global.a.f;
        int a2 = g.a(this.d, 10.0f);
        layoutParams5.rightMargin = a2;
        layoutParams5.leftMargin = a2;
        this.m.addView(this.z, layoutParams5);
    }

    public final void c(String str) {
        StringBuilder sb;
        String a2;
        this.j = false;
        this.b.a(c.bD.U);
        if (h()) {
            sb = new StringBuilder("\"card\":\"");
            a2 = this.f9608a.aq;
        } else {
            sb = new StringBuilder("\"card\":\"");
            a2 = this.f9608a.q.get(this.f9608a.N).a();
        }
        sb.append(a2);
        sb.append("\"");
        String sb2 = sb.toString();
        k.a("uppay", "cmd:" + str + ", ele:" + sb2);
        this.e.c(str, sb2);
        this.u = 6;
    }

    public final void c(String str, String str2) {
        a(str, str2);
    }

    public final void k() {
        if (!this.B.d()) {
            if (this.f9608a.L) {
                a(13);
                this.f9608a.L = false;
            } else if (!this.f9608a.K || !this.D) {
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

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.B.d();
    }

    public final void r() {
        this.b.a(c.bD.U);
        z c = this.A.c("promotion");
        String str = "\"\"";
        if (c != null) {
            str = "\"" + ((aj) c).g() + "\"";
        }
        this.e.c("instalment", "\"promotion\":" + str);
        this.u = 16;
    }
}
