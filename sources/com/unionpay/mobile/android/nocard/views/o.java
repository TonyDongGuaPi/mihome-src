package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.actions.SearchIntents;
import com.mobikwik.sdk.lib.Constants;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upwidget.c;
import com.unionpay.mobile.android.upwidget.g;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.aj;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.mobile.android.widgets.p;
import com.unionpay.mobile.android.widgets.z;
import com.unionpay.tsmservice.data.Constant;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class o extends b implements a.b {
    private int A = 20;
    /* access modifiers changed from: private */
    public int B = 5;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upwidget.a C = null;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upviews.a D = null;
    private b E;
    /* access modifiers changed from: private */
    public String F;
    private View.OnClickListener G = new p(this);
    private View.OnClickListener H = new u(this);
    private boolean I = false;
    LinearLayout r = null;
    private int s = 0;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upviews.a t = null;
    private View.OnClickListener u = null;
    private View.OnClickListener v = null;
    private View.OnClickListener w = null;
    private TextView x = null;
    private int y = 0;
    private int z = 0;

    public interface a {
        void a(int i);
    }

    private class b extends LinearLayout {
        /* access modifiers changed from: private */
        public PopupWindow b;
        /* access modifiers changed from: private */
        public c c;
        private g d;
        private String e;
        private TextView f;
        /* access modifiers changed from: private */
        public int g = 1;
        private final View.OnClickListener h = new ac(this);
        private final AdapterView.OnItemClickListener i = new ad(this);
        /* access modifiers changed from: private */
        public List<Map<String, Object>> j;
        /* access modifiers changed from: private */
        public a k;
        private String l;

        /* JADX WARNING: type inference failed for: r14v0, types: [java.lang.String, org.json.JSONArray] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public b(android.content.Context r11, com.unionpay.mobile.android.nocard.views.o.a r12, java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r13, org.json.JSONArray r14, java.lang.String r15) {
            /*
                r9 = this;
                com.unionpay.mobile.android.nocard.views.o.this = r10
                r9.<init>(r11)
                r11 = 1
                r9.g = r11
                com.unionpay.mobile.android.nocard.views.ac r0 = new com.unionpay.mobile.android.nocard.views.ac
                r0.<init>(r9)
                r9.h = r0
                com.unionpay.mobile.android.nocard.views.ad r0 = new com.unionpay.mobile.android.nocard.views.ad
                r0.<init>(r9)
                r9.i = r0
                r9.setOrientation(r11)
                r9.k = r12
                r9.j = r13
                r9.e = r14
                r9.l = r15
                com.unionpay.mobile.android.upwidget.c r12 = new com.unionpay.mobile.android.upwidget.c
                android.content.Context r2 = r10.d
                java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r3 = r9.j
                java.lang.String r4 = r9.e
                java.lang.String r5 = r9.l
                java.lang.String r6 = ""
                int r7 = r9.g
                r8 = 0
                r1 = r12
                r1.<init>(r2, r3, r4, r5, r6, r7, r8)
                r9.c = r12
                com.unionpay.mobile.android.upwidget.g r12 = new com.unionpay.mobile.android.upwidget.g
                android.content.Context r10 = r10.d
                com.unionpay.mobile.android.upwidget.c r14 = r9.c
                r12.<init>(r10, r14)
                r9.d = r12
                com.unionpay.mobile.android.upwidget.g r10 = r9.d
                android.widget.AdapterView$OnItemClickListener r12 = r9.i
                r10.a((android.widget.AdapterView.OnItemClickListener) r12)
                com.unionpay.mobile.android.upwidget.g r10 = r9.d
                android.view.View$OnClickListener r12 = r9.h
                r10.a((android.view.View.OnClickListener) r12)
                if (r13 == 0) goto L_0x011f
                int r10 = r13.size()
                if (r10 <= 0) goto L_0x011f
                com.unionpay.mobile.android.nocard.views.o r10 = com.unionpay.mobile.android.nocard.views.o.this
                android.content.Context r10 = r10.d
                com.unionpay.mobile.android.resource.c r10 = com.unionpay.mobile.android.resource.c.a(r10)
                r12 = 2014(0x7de, float:2.822E-42)
                r13 = -1
                android.graphics.drawable.Drawable r10 = r10.a(r12, r13, r13)
                android.widget.RelativeLayout r12 = new android.widget.RelativeLayout
                com.unionpay.mobile.android.nocard.views.o r14 = com.unionpay.mobile.android.nocard.views.o.this
                android.content.Context r14 = r14.d
                r12.<init>(r14)
                r12.setBackgroundDrawable(r10)
                com.unionpay.mobile.android.nocard.views.ae r10 = new com.unionpay.mobile.android.nocard.views.ae
                r10.<init>(r9)
                r12.setOnClickListener(r10)
                android.widget.LinearLayout$LayoutParams r10 = new android.widget.LinearLayout$LayoutParams
                int r14 = com.unionpay.mobile.android.global.a.n
                r10.<init>(r13, r14)
                r9.addView(r12, r10)
                android.widget.ImageView r10 = new android.widget.ImageView
                com.unionpay.mobile.android.nocard.views.o r14 = com.unionpay.mobile.android.nocard.views.o.this
                android.content.Context r14 = r14.d
                r10.<init>(r14)
                int r14 = r10.hashCode()
                r10.setId(r14)
                com.unionpay.mobile.android.nocard.views.o r14 = com.unionpay.mobile.android.nocard.views.o.this
                android.content.Context r14 = r14.d
                com.unionpay.mobile.android.resource.c r14 = com.unionpay.mobile.android.resource.c.a(r14)
                r15 = 1002(0x3ea, float:1.404E-42)
                android.graphics.drawable.Drawable r14 = r14.a(r15, r13, r13)
                r10.setBackgroundDrawable(r14)
                com.unionpay.mobile.android.nocard.views.o r14 = com.unionpay.mobile.android.nocard.views.o.this
                android.content.Context r14 = r14.d
                r15 = 1097859072(0x41700000, float:15.0)
                int r14 = com.unionpay.mobile.android.utils.g.a(r14, r15)
                android.widget.RelativeLayout$LayoutParams r15 = new android.widget.RelativeLayout$LayoutParams
                r15.<init>(r14, r14)
                r14 = 11
                r15.addRule(r14, r13)
                r14 = 15
                r15.addRule(r14, r13)
                com.unionpay.mobile.android.nocard.views.o r0 = com.unionpay.mobile.android.nocard.views.o.this
                android.content.Context r0 = r0.d
                r1 = 1092616192(0x41200000, float:10.0)
                int r0 = com.unionpay.mobile.android.utils.g.a(r0, r1)
                r15.rightMargin = r0
                r12.addView(r10, r15)
                android.widget.TextView r15 = new android.widget.TextView
                com.unionpay.mobile.android.nocard.views.o r0 = com.unionpay.mobile.android.nocard.views.o.this
                android.content.Context r0 = r0.d
                r15.<init>(r0)
                r9.f = r15
                android.widget.TextView r15 = r9.f
                float r0 = com.unionpay.mobile.android.global.b.k
                r15.setTextSize(r0)
                android.widget.TextView r15 = r9.f
                r0 = -10066330(0xffffffffff666666, float:-3.0625412E38)
                r15.setTextColor(r0)
                android.widget.RelativeLayout$LayoutParams r15 = new android.widget.RelativeLayout$LayoutParams
                r0 = -2
                r15.<init>(r13, r0)
                android.widget.TextView r0 = r9.f
                android.text.TextUtils$TruncateAt r2 = android.text.TextUtils.TruncateAt.MIDDLE
                r0.setEllipsize(r2)
                android.widget.TextView r0 = r9.f
                r0.setSingleLine(r11)
                com.unionpay.mobile.android.nocard.views.o r11 = com.unionpay.mobile.android.nocard.views.o.this
                android.content.Context r11 = r11.d
                int r11 = com.unionpay.mobile.android.utils.g.a(r11, r1)
                r15.leftMargin = r11
                int r11 = r15.leftMargin
                r15.rightMargin = r11
                r15.addRule(r14, r13)
                r11 = 9
                r15.addRule(r11, r13)
                int r10 = r10.getId()
                r11 = 0
                r15.addRule(r11, r10)
                android.widget.TextView r10 = r9.f
                r12.addView(r10, r15)
                r9.a((int) r11)
            L_0x011f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.o.b.<init>(com.unionpay.mobile.android.nocard.views.o, android.content.Context, com.unionpay.mobile.android.nocard.views.o$a, java.util.List, java.lang.String, java.lang.String):void");
        }

        static /* synthetic */ void a(b bVar, View view) {
            if (bVar.b == null) {
                bVar.b = new PopupWindow(bVar.d, -1, -1, true);
                bVar.b.setBackgroundDrawable(new ColorDrawable(-1342177280));
                bVar.b.update();
            }
            bVar.b.showAtLocation(view, 80, 0, 0);
        }

        public final void a(int i2) {
            int c2 = i2 + this.c.c();
            if (this.f != null) {
                this.f.setText(this.c.b(c2));
            }
        }
    }

    public o(Context context, e eVar) {
        super(context, eVar);
        this.f = 13;
        this.q = this.f9608a.K ? "loginpay_phoneNO_change" : "loginpay";
        this.u = new v(this);
        this.v = new w(this);
        this.w = new x(this);
        if (!p() && !t() && !this.f9608a.aZ) {
            this.I = true;
        }
        setBackgroundColor(-1052684);
        e();
        if (this.f9608a.aF != null) {
            c((JSONObject) null);
        }
    }

    private void a(LinearLayout linearLayout) {
        JSONArray jSONArray = this.f9608a.ac;
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                z a2 = a((JSONObject) j.b(jSONArray, i), this.q);
                if (a2 != null) {
                    linearLayout.addView(a2);
                }
            }
        }
    }

    static /* synthetic */ void a(o oVar, int i) {
        if (oVar.f9608a.ab == null || i != oVar.f9608a.ab.size()) {
            String[] strArr = com.unionpay.mobile.android.utils.o.f;
            new Object[1][0] = Integer.valueOf(i);
            oVar.I = false;
            oVar.z = oVar.y;
            oVar.y = i;
            String a2 = oVar.f9608a.ab.get(i).a();
            oVar.j = false;
            oVar.s = 1;
            oVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
            oVar.e.i(bh.a("1", a2, "1", "2"));
            return;
        }
        oVar.f9608a.aZ = true;
        oVar.I = true;
        oVar.d(13);
    }

    static /* synthetic */ void a(o oVar, String str, String str2) {
        oVar.s = 8;
        oVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        oVar.e.c(str, str2);
    }

    static /* synthetic */ void b(o oVar, String str) {
        oVar.j = false;
        oVar.s = 3;
        oVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        oVar.e.a("1", "2", Constants.YES, str);
    }

    /* access modifiers changed from: private */
    public void d(String str, String str2) {
        this.s = 9;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.B--;
    }

    private void d(JSONObject jSONObject) {
        boolean z2 = false;
        int a2 = f.a(this.f9608a, jSONObject, false);
        if (a2 != 0) {
            b(a2);
            if (1 == this.s) {
                f(this.z);
                return;
            }
            return;
        }
        e a3 = f.a(jSONObject);
        if (5 != this.s) {
            this.p = a3;
            f(this.y);
            this.D.a(s(), this.f9608a.aq, true, (z) null, this.f9608a.ad, this.q);
            this.D.a(this.G);
            this.D.b(this.H);
            this.D.a(this.b, this.f9608a.aU);
            this.D.d(this.f9608a.bt);
            z zVar = null;
            if (this.D != null) {
                zVar = this.D.c("instalment");
            }
            this.t.a(this.f9608a.z, this.f9608a.aq, true, zVar, this.f9608a.ad, this.q);
            TextView textView = this.x;
            if (this.t == null || this.t.e()) {
                z2 = true;
            }
            textView.setEnabled(z2);
        } else if (this.f9608a.z != null && this.f9608a.z.length() > 0) {
            a(6, a3);
        } else if (this.f9608a.D != null && this.f9608a.D.length() > 0) {
            d(5);
        }
    }

    static /* synthetic */ void e(o oVar) {
        if (oVar.t != null) {
            a.C0077a b2 = oVar.t.b();
            if (!b2.a()) {
                oVar.a(b2.b);
                return;
            }
            oVar.j = false;
            oVar.s = 5;
            oVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
            oVar.e.c("bindcardrules", b2.b);
        }
    }

    private void f(int i) {
        this.y = i;
        this.E.a(this.y);
    }

    private JSONArray s() {
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.model.f fVar = (com.unionpay.mobile.android.model.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.f9608a.aU = fVar.a("promotion_instalment_msgbox");
        }
        return jSONArray;
    }

    private final boolean t() {
        return !this.f9608a.aZ && this.f9608a.ab != null && this.f9608a.ab.size() > 0;
    }

    private void u() {
        this.s = 4;
        this.e.a(SearchIntents.EXTRA_QUERY, this.f9608a.aj, 3);
        this.A--;
    }

    public final void a(a.C0077a aVar) {
        this.t.d();
        if (!aVar.a()) {
            a(aVar.b);
            return;
        }
        this.j = false;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        this.e.c("sms", aVar.b);
        this.s = 2;
    }

    public final void a(JSONObject jSONObject) {
        int i = this.s;
        if (i != 16) {
            switch (i) {
                case 1:
                case 5:
                    i();
                    if (!b(jSONObject)) {
                        if (this.s == 5) {
                            this.f9608a.L = true;
                        }
                        d(jSONObject);
                        return;
                    }
                    return;
                case 2:
                    i();
                    this.t.a(com.unionpay.mobile.android.global.b.p);
                    return;
                case 3:
                    this.f9608a.aj = i.a(jSONObject.toString());
                    String a2 = j.a(jSONObject, "qn");
                    if (!TextUtils.isEmpty(a2)) {
                        this.f9608a.n = this.e.h(com.unionpay.mobile.android.utils.c.b(a2));
                    }
                    if (this.f9608a.aj == null) {
                        b(2);
                        return;
                    }
                    this.A = 20;
                    u();
                    return;
                case 4:
                    String a3 = j.a(jSONObject, "status");
                    if (this.A <= 0 || !a3.equalsIgnoreCase("01")) {
                        i();
                        if (a3.equalsIgnoreCase("00")) {
                            this.s = 0;
                            this.f9608a.H = j.d(jSONObject, "result");
                            this.f9608a.P = j.a(jSONObject, "openupgrade_flag");
                            this.f9608a.Q = j.a(jSONObject, "temporary_pay_flag");
                            this.f9608a.R = j.a(jSONObject, "temporary_pay_info");
                            this.f9608a.V = j.a(jSONObject, "front_url");
                            this.f9608a.W = j.a(jSONObject, "front_request");
                            this.f9608a.A = j.a(jSONObject, "title");
                            this.f9608a.B = j.a(jSONObject, "succ_info");
                            com.unionpay.mobile.android.nocard.utils.b.a(jSONObject, this.f9608a);
                            com.unionpay.mobile.android.nocard.utils.b.b(jSONObject, this.f9608a);
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
                        } else if (a3.equalsIgnoreCase(Constant.RECHARGE_MODE_BUSINESS_OFFICE)) {
                            String a4 = j.a(jSONObject, "fail_msg");
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(this.q);
                            sb3.append("_fail");
                            String[] strArr = com.unionpay.mobile.android.utils.o.j;
                            String[] strArr2 = {a3, a4};
                            a(a4);
                            return;
                        } else if (this.A <= 0) {
                            b(19);
                            return;
                        } else {
                            return;
                        }
                    } else {
                        u();
                        return;
                    }
                case 6:
                    i();
                    int a5 = f.a(this.f9608a, jSONObject, true);
                    if (a5 != 0) {
                        b(a5);
                    } else {
                        this.f9608a.K = true;
                        e a6 = f.a(jSONObject);
                        if (this.f9608a.z != null && this.f9608a.z.length() > 0) {
                            a(6, a6);
                        } else if (this.f9608a.D != null && this.f9608a.D.length() > 0) {
                            d(5);
                        }
                    }
                    this.s = 0;
                    return;
                case 7:
                    i();
                    int a7 = f.a(this.f9608a, jSONObject, false);
                    if (a7 != 0) {
                        b(a7);
                        return;
                    }
                    e a8 = f.a(jSONObject);
                    if (this.f9608a.z != null && this.f9608a.z.length() > 0) {
                        a(6, a8);
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
                    if (this.D != null) {
                        this.D.a(d);
                        return;
                    }
                    return;
                case 9:
                    String a9 = j.a(jSONObject, "status");
                    if (a9 == null || !"01".equals(a9)) {
                        JSONArray d2 = j.d(jSONObject, "options");
                        String a10 = j.a(jSONObject, "empty_info");
                        if (this.D != null) {
                            this.D.a(d2, a10);
                            return;
                        }
                        return;
                    }
                    String a11 = j.a(jSONObject, "uuid");
                    if (this.B >= 0) {
                        d(this.F, a11);
                        return;
                    }
                    String str = com.unionpay.mobile.android.languages.c.bD.D;
                    if (this.D != null) {
                        this.D.a((JSONArray) null, str);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            if (this.b.a()) {
                this.b.c();
            }
            new JSONObject();
            if (TextUtils.isEmpty(j.a(jSONObject, "instalment_empty_info"))) {
                jSONObject = j.c(jSONObject, "instalment");
            }
            this.D.a(jSONObject);
            this.s = 0;
        }
    }

    public final void a(boolean z2) {
        this.x.setEnabled(!z2);
    }

    /* access modifiers changed from: protected */
    public final boolean a(String str, JSONObject jSONObject) {
        if (this.s != 1) {
            return false;
        }
        f(this.z);
        i();
        a(str);
        return true;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        String str = com.unionpay.mobile.android.languages.c.bD.o;
        ay ayVar = new ay(this.d, str, this);
        if (this.f9608a.aC && ((this.f9608a.q == null || this.f9608a.q.size() == 0) && !this.f9608a.aZ && !TextUtils.isEmpty(this.f9608a.u))) {
            ayVar = new ay(this.d, str, this.c.a(1030, -1, -1), com.unionpay.mobile.android.utils.g.a(this.d, 20.0f), this);
        }
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    public final void b(int i) {
        if (this.s == 16) {
            if (this.b != null) {
                this.b.c();
            }
            z c = this.D.c("instalment");
            if (c != null) {
                p pVar = (p) c;
                pVar.a(false);
                pVar.b(false);
            }
        }
        super.b(i);
    }

    /* access modifiers changed from: protected */
    public final void b(String str, JSONObject jSONObject) {
        if ("init".equals(str)) {
            a(2);
        } else if ("".equals(str)) {
            if (this.s == 5) {
                this.f9608a.L = true;
            }
            if (jSONObject != null) {
                d(jSONObject);
            }
        } else {
            this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
            this.j = false;
            this.s = 7;
            this.e.c(str, "");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: com.unionpay.mobile.android.upviews.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: android.widget.TextView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: com.unionpay.mobile.android.upviews.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: com.unionpay.mobile.android.upviews.a} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0378, code lost:
        if (r12.t.e() == false) goto L_0x03e3;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r21 = this;
            r12 = r21
            android.widget.RelativeLayout r0 = r12.m
            r0.removeAllViews()
            com.unionpay.mobile.android.upwidget.UPScrollView r0 = r12.o
            r0.a((com.unionpay.mobile.android.upwidget.UPScrollView.a) r12)
            android.widget.LinearLayout r13 = new android.widget.LinearLayout
            android.content.Context r0 = r12.d
            r13.<init>(r0)
            r14 = 1
            r13.setOrientation(r14)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r15 = -2
            r11 = -1
            r0.<init>(r11, r15)
            int r1 = com.unionpay.mobile.android.global.a.f
            r0.topMargin = r1
            r1 = 10
            r0.addRule(r1, r11)
            android.widget.RelativeLayout r1 = r12.m
            r1.addView(r13, r0)
            r12.a((android.widget.LinearLayout) r13)
            org.json.JSONArray r0 = r21.s()
            int r1 = r0.length()
            if (r1 <= 0) goto L_0x007a
            boolean r1 = r21.t()
            if (r1 == 0) goto L_0x007a
            com.unionpay.mobile.android.upviews.a r1 = new com.unionpay.mobile.android.upviews.a
            android.content.Context r2 = r12.d
            java.lang.String r3 = r12.q
            r1.<init>(r2, r0, r12, r3)
            r12.D = r1
            com.unionpay.mobile.android.upviews.a r0 = r12.D
            android.view.View$OnClickListener r1 = r12.G
            r0.a((android.view.View.OnClickListener) r1)
            com.unionpay.mobile.android.upviews.a r0 = r12.D
            android.view.View$OnClickListener r1 = r12.H
            r0.b((android.view.View.OnClickListener) r1)
            com.unionpay.mobile.android.upviews.a r0 = r12.D
            com.unionpay.mobile.android.widgets.m r1 = r12.b
            com.unionpay.mobile.android.model.b r2 = r12.f9608a
            org.json.JSONObject r2 = r2.aU
            r0.a((com.unionpay.mobile.android.widgets.m) r1, (org.json.JSONObject) r2)
            com.unionpay.mobile.android.upviews.a r0 = r12.D
            com.unionpay.mobile.android.model.b r1 = r12.f9608a
            java.lang.String r1 = r1.bt
            r0.d(r1)
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r0.<init>(r11, r15)
            int r1 = com.unionpay.mobile.android.global.a.f
            r0.bottomMargin = r1
            com.unionpay.mobile.android.upviews.a r1 = r12.D
            r13.addView(r1, r0)
        L_0x007a:
            boolean r0 = r21.p()
            r1 = -3419943(0xffffffffffcbd0d9, float:NaN)
            r10 = 1092616192(0x41200000, float:10.0)
            r9 = 0
            if (r0 == 0) goto L_0x014d
            boolean r0 = r21.t()
            if (r0 != 0) goto L_0x00b9
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            java.lang.String r0 = r0.ae
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x00b5
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r0.<init>(r11, r15)
            int r1 = com.unionpay.mobile.android.global.a.f
            r0.topMargin = r1
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r2 = r12.d
            r1.<init>(r2)
            float r2 = com.unionpay.mobile.android.global.b.k
            r1.setTextSize(r2)
            com.unionpay.mobile.android.model.b r2 = r12.f9608a
            java.lang.String r2 = r2.ae
            r1.setText(r2)
            r13.addView(r1, r0)
        L_0x00b5:
            r1 = -2
            r14 = -1
            goto L_0x02c4
        L_0x00b9:
            android.widget.LinearLayout r0 = new android.widget.LinearLayout
            android.content.Context r2 = r12.d
            r0.<init>(r2)
            r0.setBackgroundColor(r1)
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams
            r1.<init>(r11, r14)
            int r2 = com.unionpay.mobile.android.global.a.f
            r1.topMargin = r2
            r13.addView(r0, r1)
            android.widget.LinearLayout$LayoutParams r8 = new android.widget.LinearLayout$LayoutParams
            r8.<init>(r11, r15)
            com.unionpay.mobile.android.nocard.views.o$b r6 = new com.unionpay.mobile.android.nocard.views.o$b
            android.content.Context r2 = r12.d
            com.unionpay.mobile.android.nocard.views.z r3 = new com.unionpay.mobile.android.nocard.views.z
            r3.<init>(r12)
            android.content.Context r0 = r12.d
            com.unionpay.mobile.android.model.b r1 = r12.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r1 = r1.ab
            java.util.List r4 = com.unionpay.mobile.android.nocard.views.xlistview.a.a(r0, r1, r9)
            com.unionpay.mobile.android.languages.c r0 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r5 = r0.bh
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            java.lang.String r1 = r0.aY
            r0 = r6
            r16 = r1
            r1 = r21
            r7 = r6
            r6 = r16
            r0.<init>(r2, r3, r4, r5, r6)
            r12.E = r7
            com.unionpay.mobile.android.nocard.views.o$b r0 = r12.E
            r13.addView(r0, r8)
            com.unionpay.mobile.android.upviews.a r0 = r12.D
            if (r0 == 0) goto L_0x0110
            com.unionpay.mobile.android.upviews.a r0 = r12.D
            java.lang.String r1 = "instalment"
            com.unionpay.mobile.android.widgets.z r0 = r0.c((java.lang.String) r1)
            r17 = r0
            goto L_0x0112
        L_0x0110:
            r17 = 0
        L_0x0112:
            com.unionpay.mobile.android.upviews.a r7 = new com.unionpay.mobile.android.upviews.a
            android.content.Context r1 = r12.d
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            org.json.JSONArray r2 = r0.z
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r0 = r12.e
            long r3 = r0.c()
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            java.lang.String r6 = r0.aq
            r16 = 1
            r18 = 1
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            org.json.JSONArray r5 = r0.ad
            java.lang.String r0 = r12.q
            r19 = r0
            r0 = r7
            r20 = r5
            r5 = r21
            r14 = r7
            r7 = r16
            r15 = r8
            r8 = r18
            r9 = r17
            r10 = r20
            r11 = r19
            r0.<init>(r1, r2, r3, r5, r6, r7, r8, r9, r10, r11)
            r12.t = r14
            com.unionpay.mobile.android.upviews.a r0 = r12.t
            r13.addView(r0, r15)
            goto L_0x00b5
        L_0x014d:
            boolean r0 = r21.t()
            if (r0 != 0) goto L_0x023a
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            java.lang.String r0 = r0.aY
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x018b
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r1 = -2
            r14 = -1
            r0.<init>(r14, r1)
            int r1 = com.unionpay.mobile.android.global.a.f
            r0.topMargin = r1
            android.content.Context r1 = r12.d
            r15 = 1092616192(0x41200000, float:10.0)
            int r1 = com.unionpay.mobile.android.utils.g.a(r1, r15)
            r0.leftMargin = r1
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r2 = r12.d
            r1.<init>(r2)
            float r2 = com.unionpay.mobile.android.global.b.k
            r1.setTextSize(r2)
            com.unionpay.mobile.android.model.b r2 = r12.f9608a
            java.lang.String r2 = r2.ae
            r1.setText(r2)
        L_0x0185:
            r13.addView(r1, r0)
            r1 = -2
            goto L_0x02c4
        L_0x018b:
            r14 = -1
            r15 = 1092616192(0x41200000, float:10.0)
            android.widget.RelativeLayout r0 = new android.widget.RelativeLayout
            android.content.Context r1 = r12.d
            r0.<init>(r1)
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r2 = r12.d
            r1.<init>(r2)
            float r2 = com.unionpay.mobile.android.global.b.k
            r1.setTextSize(r2)
            r2 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            r1.setTextColor(r2)
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.bA
            r1.setText(r2)
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams
            r3 = -2
            r2.<init>(r3, r3)
            r3 = 9
            r2.addRule(r3, r14)
            r3 = 15
            r2.addRule(r3, r14)
            android.content.Context r4 = r12.d
            int r4 = com.unionpay.mobile.android.utils.g.a(r4, r15)
            r2.leftMargin = r4
            r0.addView(r1, r2)
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r2 = r12.d
            r1.<init>(r2)
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.j
            android.text.Spanned r2 = android.text.Html.fromHtml(r2)
            r1.setText(r2)
            float r2 = com.unionpay.mobile.android.global.b.k
            r1.setTextSize(r2)
            r2 = -10705958(0xffffffffff5ca3da, float:-2.9328093E38)
            r4 = -6710887(0xffffffffff999999, float:NaN)
            r5 = -5846275(0xffffffffffa6cafd, float:NaN)
            android.content.res.ColorStateList r2 = com.unionpay.mobile.android.utils.h.a((int) r2, (int) r5, (int) r5, (int) r4)
            r1.setTextColor(r2)
            com.unionpay.mobile.android.nocard.views.y r2 = new com.unionpay.mobile.android.nocard.views.y
            r2.<init>(r12)
            r1.setOnClickListener(r2)
            android.widget.RelativeLayout$LayoutParams r2 = new android.widget.RelativeLayout$LayoutParams
            r4 = -2
            r2.<init>(r4, r4)
            r5 = 11
            r2.addRule(r5, r14)
            android.content.Context r5 = r12.d
            int r5 = com.unionpay.mobile.android.utils.g.a(r5, r15)
            r2.rightMargin = r5
            r2.addRule(r3, r14)
            r0.addView(r1, r2)
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams
            r1.<init>(r14, r4)
            int r2 = com.unionpay.mobile.android.global.a.f
            r1.topMargin = r2
            r13.addView(r0, r1)
            com.unionpay.mobile.android.upviews.a r0 = new com.unionpay.mobile.android.upviews.a
            android.content.Context r1 = r12.d
            com.unionpay.mobile.android.model.b r2 = r12.f9608a
            org.json.JSONArray r2 = r2.t
            java.lang.String r3 = r12.q
            r0.<init>(r1, r2, r12, r3)
            r12.t = r0
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r1 = -2
            r0.<init>(r14, r1)
            int r1 = com.unionpay.mobile.android.global.a.f
            r0.topMargin = r1
            com.unionpay.mobile.android.upviews.a r1 = r12.t
            goto L_0x0185
        L_0x023a:
            r14 = -1
            r15 = 1092616192(0x41200000, float:10.0)
            android.widget.LinearLayout r0 = new android.widget.LinearLayout
            android.content.Context r2 = r12.d
            r0.<init>(r2)
            r0.setBackgroundColor(r1)
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams
            r2 = 1
            r1.<init>(r14, r2)
            r13.addView(r0, r1)
            android.widget.LinearLayout$LayoutParams r7 = new android.widget.LinearLayout$LayoutParams
            r0 = -2
            r7.<init>(r14, r0)
            com.unionpay.mobile.android.nocard.views.o$b r8 = new com.unionpay.mobile.android.nocard.views.o$b
            android.content.Context r2 = r12.d
            com.unionpay.mobile.android.nocard.views.aa r3 = new com.unionpay.mobile.android.nocard.views.aa
            r3.<init>(r12)
            android.content.Context r0 = r12.d
            com.unionpay.mobile.android.model.b r1 = r12.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r1 = r1.ab
            r11 = 0
            java.util.List r4 = com.unionpay.mobile.android.nocard.views.xlistview.a.a(r0, r1, r11)
            com.unionpay.mobile.android.languages.c r0 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r5 = r0.bh
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            java.lang.String r6 = r0.aY
            r0 = r8
            r1 = r21
            r0.<init>(r2, r3, r4, r5, r6)
            r12.E = r8
            com.unionpay.mobile.android.nocard.views.o$b r0 = r12.E
            r13.addView(r0, r7)
            com.unionpay.mobile.android.upviews.a r0 = r12.D
            if (r0 == 0) goto L_0x028d
            com.unionpay.mobile.android.upviews.a r0 = r12.D
            java.lang.String r1 = "instalment"
            com.unionpay.mobile.android.widgets.z r0 = r0.c((java.lang.String) r1)
            r9 = r0
            goto L_0x028e
        L_0x028d:
            r9 = 0
        L_0x028e:
            com.unionpay.mobile.android.upviews.a r10 = new com.unionpay.mobile.android.upviews.a
            android.content.Context r1 = r12.d
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            org.json.JSONArray r2 = r0.z
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r0 = r12.e
            long r3 = r0.c()
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            java.lang.String r6 = r0.aq
            r7 = 1
            r8 = 1
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            org.json.JSONArray r5 = r0.ad
            java.lang.String r0 = r12.q
            r16 = r0
            r0 = r10
            r17 = r5
            r5 = r21
            r15 = r10
            r10 = r17
            r11 = r16
            r0.<init>(r1, r2, r3, r5, r6, r7, r8, r9, r10, r11)
            r12.t = r15
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r1 = -2
            r0.<init>(r14, r1)
            com.unionpay.mobile.android.upviews.a r2 = r12.t
            r13.addView(r2, r0)
        L_0x02c4:
            android.widget.LinearLayout r0 = new android.widget.LinearLayout
            android.content.Context r2 = r12.d
            r0.<init>(r2)
            r2 = 1
            r0.setOrientation(r2)
            int r2 = r0.hashCode()
            r0.setId(r2)
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams
            r2.<init>(r14, r1)
            int r1 = com.unionpay.mobile.android.global.a.d
            r2.topMargin = r1
            r13.addView(r0, r2)
            com.unionpay.mobile.android.model.b r1 = r12.f9608a
            org.json.JSONObject r1 = r1.Z
            if (r1 == 0) goto L_0x0320
            boolean r1 = r21.t()
            if (r1 == 0) goto L_0x0320
            com.unionpay.mobile.android.upwidget.a r1 = new com.unionpay.mobile.android.upwidget.a
            android.content.Context r2 = r12.d
            com.unionpay.mobile.android.model.b r3 = r12.f9608a
            org.json.JSONObject r3 = r3.Z
            com.unionpay.mobile.android.languages.c r4 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r4 = r4.s
            org.json.JSONObject r3 = com.unionpay.mobile.android.nocard.views.xlistview.a.a(r3, r4)
            com.unionpay.mobile.android.nocard.views.q r4 = new com.unionpay.mobile.android.nocard.views.q
            r4.<init>(r12)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r12.q
            r5.append(r6)
            java.lang.String r6 = "_agree_user_protocol"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r1.<init>(r2, r3, r4, r5)
            r12.C = r1
            com.unionpay.mobile.android.upwidget.a r1 = r12.C
            r0.addView(r1)
        L_0x0320:
            android.content.Context r1 = r12.d
            com.unionpay.mobile.android.model.b r2 = r12.f9608a
            org.json.JSONObject r2 = r2.aa
            com.unionpay.mobile.android.resource.c r3 = r12.c
            r4 = 1017(0x3f9, float:1.425E-42)
            android.graphics.drawable.Drawable r3 = r3.a(r4, r14, r14)
            com.unionpay.mobile.android.upwidget.w r1 = com.unionpay.mobile.android.upwidget.w.a(r1, r2, r3)
            if (r1 == 0) goto L_0x034d
            java.lang.String r2 = r1.a()
            com.unionpay.mobile.android.nocard.views.r r3 = new com.unionpay.mobile.android.nocard.views.r
            r3.<init>(r12, r2)
            r1.a(r3)
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams
            r3 = -2
            r2.<init>(r3, r3)
            int r3 = com.unionpay.mobile.android.global.a.f
            r2.topMargin = r3
            r0.addView(r1, r2)
        L_0x034d:
            android.widget.TextView r0 = new android.widget.TextView
            android.content.Context r1 = r12.d
            r0.<init>(r1)
            r12.x = r0
            boolean r0 = r21.t()
            if (r0 == 0) goto L_0x037b
            android.widget.TextView r0 = r12.x
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.p
            r0.setText(r1)
            android.widget.TextView r0 = r12.x
            android.view.View$OnClickListener r1 = r12.u
            r0.setOnClickListener(r1)
            android.widget.TextView r0 = r12.x
            com.unionpay.mobile.android.upviews.a r1 = r12.t
            if (r1 == 0) goto L_0x0396
            com.unionpay.mobile.android.upviews.a r1 = r12.t
            boolean r1 = r1.e()
            if (r1 == 0) goto L_0x03e3
            goto L_0x0396
        L_0x037b:
            boolean r0 = r21.p()
            if (r0 == 0) goto L_0x039b
            android.widget.TextView r0 = r12.x
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.q
            r0.setText(r1)
            android.widget.TextView r0 = r12.x
            com.unionpay.mobile.android.nocard.views.ab r1 = new com.unionpay.mobile.android.nocard.views.ab
            r1.<init>(r12)
        L_0x0391:
            r0.setOnClickListener(r1)
            android.widget.TextView r0 = r12.x
        L_0x0396:
            r1 = 1
        L_0x0397:
            r0.setEnabled(r1)
            goto L_0x03e5
        L_0x039b:
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            java.lang.String r0 = r0.aY
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x03d1
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            boolean r0 = r0.aZ
            if (r0 != 0) goto L_0x03d1
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r0 = r0.q
            if (r0 == 0) goto L_0x03c3
            com.unionpay.mobile.android.model.b r0 = r12.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r0 = r0.q
            int r0 = r0.size()
            if (r0 != 0) goto L_0x03bc
            goto L_0x03c3
        L_0x03bc:
            android.widget.TextView r0 = r12.x
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.bv
            goto L_0x03c9
        L_0x03c3:
            android.widget.TextView r0 = r12.x
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.bu
        L_0x03c9:
            r0.setText(r1)
            android.widget.TextView r0 = r12.x
            android.view.View$OnClickListener r1 = r12.w
            goto L_0x0391
        L_0x03d1:
            android.widget.TextView r0 = r12.x
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.r
            r0.setText(r1)
            android.widget.TextView r0 = r12.x
            android.view.View$OnClickListener r1 = r12.v
            r0.setOnClickListener(r1)
            android.widget.TextView r0 = r12.x
        L_0x03e3:
            r1 = 0
            goto L_0x0397
        L_0x03e5:
            android.widget.TextView r0 = r12.x
            float r1 = com.unionpay.mobile.android.global.b.i
            r0.setTextSize(r1)
            android.widget.TextView r0 = r12.x
            android.content.res.ColorStateList r1 = o()
            r0.setTextColor(r1)
            android.widget.TextView r0 = r12.x
            r1 = 17
            r0.setGravity(r1)
            int r0 = com.unionpay.mobile.android.global.a.n
            com.unionpay.mobile.android.resource.c r1 = r12.c
            r2 = 2008(0x7d8, float:2.814E-42)
            android.graphics.drawable.Drawable r1 = r1.a(r2, r14, r14)
            android.widget.TextView r2 = r12.x
            r2.setBackgroundDrawable(r1)
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams
            r1.<init>(r14, r0)
            int r0 = com.unionpay.mobile.android.global.a.f
            r1.topMargin = r0
            android.content.Context r0 = r12.d
            r2 = 1092616192(0x41200000, float:10.0)
            int r0 = com.unionpay.mobile.android.utils.g.a(r0, r2)
            r1.rightMargin = r0
            r1.leftMargin = r0
            android.widget.TextView r0 = r12.x
            r13.addView(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.o.c():void");
    }

    public final void c(String str) {
        StringBuilder sb;
        String a2;
        this.j = false;
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        if (this.f9608a.aZ) {
            sb = new StringBuilder("\"card\":\"");
            a2 = this.f9608a.aq;
        } else {
            sb = new StringBuilder("\"card\":\"");
            a2 = this.f9608a.ab.get(this.y).a();
        }
        sb.append(a2);
        sb.append("\"");
        String sb2 = sb.toString();
        k.a("uppay", "cmd:" + str + ", ele:" + sb2);
        this.e.c(str, sb2);
        this.s = 6;
    }

    public final void c(String str, String str2) {
        a(str, str2);
    }

    public final void k() {
        if (TextUtils.isEmpty(this.f9608a.u) || !this.f9608a.aC || !(this.f9608a.q == null || this.f9608a.q.size() == 0)) {
            if (this.f9608a.aZ) {
                this.f9608a.aZ = false;
            }
            if (this.t != null && this.t.d()) {
                return;
            }
            if (this.f9608a.u == null || this.f9608a.u.length() <= 0) {
                m();
            } else {
                a(2);
            }
        } else {
            this.b.a(new s(this), new t(this));
            this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, com.unionpay.mobile.android.languages.c.bD.av, com.unionpay.mobile.android.languages.c.bD.W, com.unionpay.mobile.android.languages.c.bD.X);
        }
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public final void r() {
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        z c = this.D.c("promotion");
        String str = "\"\"";
        if (c != null) {
            str = "\"" + ((aj) c).g() + "\"";
        }
        this.e.c("instalment", "\"promotion\":" + str);
        this.s = 16;
    }
}
