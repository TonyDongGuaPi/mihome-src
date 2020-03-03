package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.actions.SearchIntents;
import com.unionpay.mobile.android.model.c;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.nocard.views.bh;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.o;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.tsmservice.data.Constant;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class y extends b implements Handler.Callback, a.b {
    /* access modifiers changed from: private */
    public a A = null;
    /* access modifiers changed from: private */
    public String B;
    private boolean C = false;
    /* access modifiers changed from: private */
    public Handler D = null;
    private View.OnClickListener E = new z(this);
    private View.OnClickListener F = new aa(this);
    private View.OnClickListener G = new ab(this);
    private View.OnClickListener H = new ac(this);
    private View.OnClickListener I = new ae(this);
    private String r = "00";
    private int s = 0;
    /* access modifiers changed from: private */
    public int t = 0;
    private int u = 20;
    /* access modifiers changed from: private */
    public int v = 5;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upwidget.a w = null;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upwidget.a x = null;
    private TextView y = null;
    private a z = null;

    public y(Context context, e eVar) {
        super(context, eVar);
        this.f = 6;
        this.q = "sdpay";
        this.D = new Handler(this);
        this.C = this.f9608a.K;
        setBackgroundColor(-1052684);
        e();
    }

    static /* synthetic */ String C(y yVar) {
        String str = "";
        if (yVar.A != null) {
            a.C0077a b = yVar.A.b();
            if (b.a()) {
                str = str + b.b;
            }
        }
        if (yVar.z == null) {
            return str;
        }
        a.C0077a b2 = yVar.z.b();
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

    static /* synthetic */ void a(y yVar, c cVar, String str, HashMap hashMap) {
        yVar.t = 3;
        com.unionpay.mobile.android.pro.pboc.engine.b s2 = yVar.s();
        if (s2 == null) {
            yVar.b(5);
        } else {
            new Thread(new ad(yVar, s2, cVar, str, hashMap)).start();
        }
    }

    static /* synthetic */ void a(y yVar, String str, String str2) {
        yVar.t = 7;
        yVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        yVar.e.c(str, str2);
    }

    /* access modifiers changed from: private */
    public void d(String str, String str2) {
        this.t = 8;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.v--;
    }

    private void f(int i) {
        this.t = 4;
        this.s = i;
        this.e.a(SearchIntents.EXTRA_QUERY, this.f9608a.aj, 3);
        this.u--;
    }

    static /* synthetic */ void f(y yVar, String str) {
        yVar.j = false;
        yVar.t = 3;
        yVar.e.c(bh.a(yVar.f9608a.C), bh.c("2", "1", yVar.w != null ? yVar.w.a() : null, str));
    }

    static /* synthetic */ HashMap i(y yVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (yVar.A != null) {
            hashMap = yVar.A.c();
        }
        if (yVar.z != null) {
            HashMap<String, String> c = yVar.z.c();
            if (c == null || hashMap == null) {
                return (c == null || hashMap != null) ? hashMap : c;
            }
            hashMap.putAll(c);
        }
    }

    public final void a(a.C0077a aVar) {
        if (!aVar.a()) {
            a(aVar.b);
            return;
        }
        k.a("uppay", "sms elements:" + aVar.b);
        this.j = false;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        this.e.c("sms", aVar.b);
        this.t = 1;
    }

    public final void a(JSONObject jSONObject) {
        int i;
        this.C = false;
        switch (this.t) {
            case 1:
                i();
                this.t = 0;
                this.A.a(com.unionpay.mobile.android.global.b.p);
                return;
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
                this.u = 20;
                f(this.t);
                return;
            case 4:
                this.r = j.a(jSONObject, "status");
                if (this.u <= 0 || !this.r.equalsIgnoreCase("01")) {
                    this.t = 0;
                    if (this.r.equalsIgnoreCase("00")) {
                        i();
                        this.t = 0;
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
                        if (this.A != null) {
                            this.A.f();
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.q);
                        sb.append("_succeed");
                        if (this.f9608a.f) {
                            this.f9608a.I.f = "success";
                            j();
                            return;
                        }
                        d(8);
                        return;
                    } else if (this.r.equalsIgnoreCase(Constant.RECHARGE_MODE_BUSINESS_OFFICE)) {
                        String a3 = j.a(jSONObject, "fail_msg");
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(this.q);
                        sb2.append("_fail");
                        String[] strArr = o.j;
                        String[] strArr2 = {this.r, a3};
                        if (this.t == 3) {
                            a(a3);
                            return;
                        }
                        af afVar = new af(this);
                        ag agVar = new ag(this);
                        if (this.f9608a.F) {
                            this.b.a(afVar, agVar);
                            this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, a3, com.unionpay.mobile.android.languages.c.bD.W, com.unionpay.mobile.android.languages.c.bD.X);
                            return;
                        }
                        this.b.a(afVar, (View.OnClickListener) null);
                        this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, a3, com.unionpay.mobile.android.languages.c.bD.W);
                        return;
                    } else if (this.u > 0) {
                        return;
                    } else {
                        if (this.s == 3) {
                            a(this.f9608a.ak, true);
                            return;
                        } else {
                            a(this.f9608a.ak);
                            return;
                        }
                    }
                } else {
                    f(this.s);
                    return;
                }
            case 6:
                i();
                int a4 = f.a(this.f9608a, jSONObject, false);
                if (a4 != 0) {
                    b(a4);
                } else {
                    this.f9608a.K = true;
                    if (this.f9608a.z != null && this.f9608a.z.length() > 0) {
                        i = 6;
                    } else if (this.f9608a.D != null && this.f9608a.D.length() > 0) {
                        i = 5;
                    }
                    d(i);
                }
                this.t = 0;
                return;
            case 7:
                i();
                JSONArray d = j.d(jSONObject, "options");
                if (this.z != null) {
                    this.z.a(d);
                    return;
                }
                return;
            case 8:
                String a5 = j.a(jSONObject, "status");
                if (a5 == null || !"01".equals(a5)) {
                    JSONArray d2 = j.d(jSONObject, "options");
                    String a6 = j.a(jSONObject, "empty_info");
                    if (this.z != null) {
                        this.z.a(d2, a6);
                        return;
                    }
                    return;
                }
                String a7 = j.a(jSONObject, "uuid");
                if (this.v >= 0) {
                    d(this.B, a7);
                    return;
                }
                String str = com.unionpay.mobile.android.languages.c.bD.D;
                if (this.z != null) {
                    this.z.a((JSONArray) null, str);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final void a(boolean z2) {
        if (this.y != null) {
            this.y.setEnabled(!z2);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a(String str, JSONObject jSONObject) {
        this.C = false;
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
    public final void c() {
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setId(linearLayout.hashCode());
        boolean z2 = true;
        linearLayout.setOrientation(1);
        this.m.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -2));
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.model.f fVar = (com.unionpay.mobile.android.model.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.f9608a.aU = fVar.a("promotion_instalment_msgbox");
        }
        if (jSONArray.length() > 0) {
            this.z = new a(this.d, jSONArray, this, this.q);
            this.z.a(this.b, this.f9608a.aU);
            this.z.a(this.F);
            this.z.b(this.G);
            this.z.c(this.H);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
            linearLayout.addView(this.z, layoutParams);
        }
        if (this.z != null) {
            this.z.c("instalment");
        }
        this.A = new a(this.d, this.f9608a.z, this.e.c(), this, this.f9608a.aq, false, this.q);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
        linearLayout.addView(this.A, layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        linearLayout2.setId(linearLayout2.hashCode());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(3, linearLayout.getId());
        layoutParams3.leftMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams3.topMargin = layoutParams3.leftMargin;
        this.m.addView(linearLayout2, layoutParams3);
        if (!(!h() && this.f9608a.al == null && this.f9608a.am == null)) {
            if (this.f9608a.al != null) {
                Context context = this.d;
                JSONObject jSONObject = this.f9608a.al;
                View.OnClickListener onClickListener = this.I;
                this.x = new com.unionpay.mobile.android.upwidget.a(context, jSONObject, onClickListener, this.q + "_agree_user_protocol");
                linearLayout2.addView(this.x);
            }
            if (this.f9608a.am != null) {
                Context context2 = this.d;
                JSONObject jSONObject2 = this.f9608a.am;
                this.w = new com.unionpay.mobile.android.upwidget.a(context2, jSONObject2, (View.OnClickListener) null, this.q + "_remember_bankNO");
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams4.topMargin = com.unionpay.mobile.android.global.a.f;
                linearLayout2.addView(this.w, layoutParams4);
            }
        }
        this.y = new TextView(this.d);
        this.y.setText(j.a(this.f9608a.C, "label"));
        this.y.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.y.setTextColor(o());
        this.y.setGravity(17);
        TextView textView = this.y;
        if (this.A != null && !this.A.e()) {
            z2 = false;
        }
        textView.setEnabled(z2);
        int i = com.unionpay.mobile.android.global.a.n;
        this.y.setBackgroundDrawable(this.c.a(2008, -1, -1));
        this.y.setOnClickListener(this.E);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, i);
        layoutParams5.addRule(3, linearLayout2.getId());
        layoutParams5.topMargin = com.unionpay.mobile.android.global.a.f;
        int a2 = g.a(this.d, 10.0f);
        layoutParams5.rightMargin = a2;
        layoutParams5.leftMargin = a2;
        this.m.addView(this.y, layoutParams5);
    }

    public final void c(String str) {
        this.j = false;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        String str2 = "\"card\":\"" + this.f9608a.q.get(this.f9608a.N).a() + "\"";
        k.a("uppay", "cmd:" + str + ", ele:" + str2);
        this.e.c(str, str2);
        this.t = 6;
    }

    public final void c(String str, String str2) {
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            Bundle bundle = (Bundle) message.obj;
            String string = bundle.getString("action_resp_code");
            String string2 = bundle.getString("action_resp_message");
            if (!"0000".equalsIgnoreCase(string)) {
                a(this.f9608a.ap, false);
            } else if (string2 != null) {
                a(0, string2);
            }
            return true;
        }
        b(1);
        return true;
    }

    public final void k() {
        if (!this.A.d()) {
            if (!this.f9608a.K || !this.C) {
                this.f9608a.K = false;
                this.f9608a.br = false;
                a(2);
                return;
            }
            this.f9608a.K = false;
            m();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.A.d();
    }

    public final void r() {
    }

    public com.unionpay.mobile.android.pro.pboc.engine.b s() {
        return null;
    }
}
