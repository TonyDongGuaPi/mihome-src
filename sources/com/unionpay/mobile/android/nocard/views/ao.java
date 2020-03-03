package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.model.d;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.views.order.l;
import com.unionpay.mobile.android.views.order.o;
import com.unionpay.mobile.android.widgets.ay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class ao extends b implements a.b {
    private Handler A = new Handler(new ap(this));
    List<Map<String, Object>> r = new ArrayList(1);
    /* access modifiers changed from: private */
    public int s = 0;
    /* access modifiers changed from: private */
    public int t = -1;
    private Button u = null;
    private com.unionpay.mobile.android.upviews.a v = null;
    /* access modifiers changed from: private */
    public o w;
    /* access modifiers changed from: private */
    public LinearLayout x;
    private boolean y = false;
    private Handler z = null;

    public class a implements o.a {
        public a() {
        }

        public final int a() {
            ao.this.f9608a.J = true;
            ao.this.d(2);
            return 0;
        }

        public final int a(int i) {
            int unused = ao.this.t = i;
            int unused2 = ao.this.s = 3;
            ao.this.j = false;
            ao.this.b.a(c.bD.U);
            ao.this.e.l(ao.this.f9608a.q.get(i).a());
            return 0;
        }

        public final void a(int i, boolean z, int i2, a.C0077a aVar) {
            String str;
            if (ao.this.w != null) {
                ao.this.f9608a.aQ = ao.this.w.a();
                StringBuilder sb = new StringBuilder();
                sb.append(ao.this.f9608a.aQ);
                k.c("functionEx", sb.toString());
            }
            if (i == l.e.intValue()) {
                ao.c(ao.this, i2);
            } else if (z) {
                ao.this.f9608a.N = i2;
                ao.this.j = false;
                int unused = ao.this.s = 2;
                ao.this.b.a(c.bD.U);
                if (ao.this.f9608a.q.get(i2).c() == 0) {
                    String a2 = ao.this.f9608a.q.get(i2).a();
                    ao.this.f9608a.M = "1";
                    str = bh.a(ao.this.f9608a.M, a2, "1", "1");
                } else {
                    ao.this.f9608a.M = "0";
                    String b = ao.this.f9608a.q.get(i2).b();
                    String str2 = ao.this.f9608a.M;
                    str = bh.b(str2, "\"pan\":\"" + b + "\"", "2", "1\",\"carrier_tp\":\"2");
                }
                ao.this.e.c(bh.a(ao.this.f9608a.j), str);
            } else if (!aVar.a()) {
                ao.this.a(aVar.b);
            } else if (i == l.c.intValue()) {
                ao.this.j = false;
                int unused2 = ao.this.s = 4;
                ao.this.b.a(c.bD.U);
                ao.this.e.m(aVar.b);
            } else {
                ao.this.j = false;
                int unused3 = ao.this.s = 2;
                ao.this.b.a(c.bD.U);
                ao.this.f9608a.M = "0";
                ao.this.e.c(bh.a(ao.this.f9608a.j), bh.b(ao.this.f9608a.M, aVar.b, "1", "1"));
            }
        }

        public final void a(String str, String str2) {
            ao.this.a(str, str2);
        }

        public final int b(int i) {
            String[] strArr = com.unionpay.mobile.android.utils.o.f;
            new Object[1][0] = Integer.valueOf(i);
            return 0;
        }

        public final void c(int i) {
            ao.this.q();
            if (i == l.b.intValue()) {
                ao.this.f9608a.aP = l.b.intValue();
                ao.this.d(2);
            } else if (i == l.c.intValue()) {
                ao.d(ao.this);
            } else if (i == l.d.intValue()) {
                ao.this.d(17);
            } else if (i == l.e.intValue()) {
                ao.f(ao.this);
            } else if (i == l.f.intValue()) {
                ao.this.d(ao.this.f9608a.bp, ao.this.f9608a.bq);
            }
        }
    }

    public ao(Context context, e eVar) {
        super(context, eVar);
        this.f = 2;
        if (this.f9608a.aE && this.f9608a.az) {
            this.f9608a.aP = l.e.intValue();
        }
        this.q = "order";
        setBackgroundColor(-1052684);
        e();
        if (!TextUtils.isEmpty(this.f9608a.u) || this.f9608a.aC) {
            this.z = new Handler(new aq(this));
        }
        if (!b.bl && this.f9608a.aP == l.e.intValue() && this.f9608a.az && !this.f9608a.aC && !this.f9608a.aD) {
            this.A.sendMessageDelayed(this.A.obtainMessage(500), 8000);
            k.c("uppay", "mHceHandler.sendMessageDelayed(msg, 8000)");
        }
    }

    /* access modifiers changed from: private */
    public static Map<String, Object> b(d dVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("text1", dVar.b() + dVar.c());
        hashMap.put("text2", dVar.a().substring(0, 4) + " **** " + dVar.a().substring(dVar.a().length() - 4));
        return hashMap;
    }

    static /* synthetic */ void c(ao aoVar, int i) {
        aoVar.s = 6;
        aoVar.j = false;
        aoVar.b.a(c.bD.U);
        String a2 = b.bb.get(i).a();
        String e = b.bb.get(i).e();
        String d = b.bb.get(i).d();
        aoVar.f9608a.bc = i;
        String str = "\"pan\":\"" + a2 + "\",\"carrier_tp\":\"9\",\"issuer\":\"" + e + "\",\"type\":\"" + d + "\"";
        k.c("uppay", str);
        aoVar.e.c("cardrules", str);
    }

    static /* synthetic */ void d(ao aoVar) {
        if (aoVar.f9608a.u == null || aoVar.f9608a.u.length() <= 0) {
            aoVar.f9608a.aP = l.c.intValue();
            aoVar.d(2);
            return;
        }
        aoVar.s = 4;
        aoVar.j = false;
        aoVar.b.a(c.bD.U);
        aoVar.e.m(String.format("\"user_id\":\"%s\"", new Object[]{aoVar.f9608a.u}));
    }

    private void d(JSONObject jSONObject) {
        int a2 = f.a(this.f9608a, jSONObject, false);
        if (a2 != 0) {
            b(a2);
        } else {
            e a3 = f.a(jSONObject);
            if (this.f9608a.z != null && this.f9608a.z.length() > 0) {
                a(6, a3);
            } else if (this.f9608a.D != null && this.f9608a.D.length() > 0) {
                d(5);
            }
        }
        this.s = 0;
    }

    private void e(JSONObject jSONObject) {
        int b = f.b(this.f9608a, jSONObject);
        if (b != 0) {
            b(b);
            return;
        }
        String a2 = j.a(jSONObject, "userId");
        if (!TextUtils.isEmpty(a2)) {
            String[] strArr = com.unionpay.mobile.android.utils.o.e;
            new Object[1][0] = a2;
        }
        if (this.w != null) {
            String b2 = this.w.b();
            if (!TextUtils.isEmpty(b2)) {
                PreferenceUtils.d(this.d, b2);
            }
        }
        a(13, f.a(jSONObject));
        this.s = 0;
    }

    static /* synthetic */ void f(ao aoVar) {
        if (!b.bl || (b.bb != null && b.bb.size() > 0)) {
            aoVar.f9608a.aP = l.e.intValue();
            aoVar.d(2);
            return;
        }
        aoVar.a(c.bD.bq, aoVar.f9608a.bh, aoVar.f9608a.aE, true);
    }

    /* access modifiers changed from: private */
    public void v() {
        if (this.f9608a.aP == l.e.intValue() && this.f9608a.az && !this.f9608a.aC && !this.f9608a.aD) {
            this.A.removeMessages(500);
            k.c("uppay", "mHceHandler remove Message");
        }
    }

    /* access modifiers changed from: protected */
    public void a(Handler handler) {
    }

    /* access modifiers changed from: protected */
    public final void a(com.unionpay.mobile.android.model.a aVar) {
        this.f9608a.br = true;
        this.f9608a.bs = aVar;
        this.s = 2;
        this.b.a(c.bD.U);
        this.f9608a.M = "0";
        String str = this.f9608a.M;
        this.e.c(bh.a(this.f9608a.j), bh.b(str, "\"pan\":\"" + aVar.b() + "\"", "2", "1\",\"carrier_tp\":\"10"));
    }

    public final void a(a.C0077a aVar) {
    }

    public final void a(JSONObject jSONObject) {
        this.b.c();
        switch (this.s) {
            case 1:
                b bVar = this.f9608a;
                bVar.X = j.d(jSONObject, "login_rules");
                bVar.Y = j.c(jSONObject, "register_url");
                if (((bVar.X == null || bVar.X.length() <= 0) ? (char) 2 : 0) != 0) {
                    b(2);
                } else {
                    d(12);
                }
                this.s = 0;
                return;
            case 2:
                if (!b(jSONObject)) {
                    d(jSONObject);
                    return;
                }
                return;
            case 3:
                b bVar2 = this.f9608a;
                int i = this.t;
                if (bVar2.q != null && i < bVar2.q.size()) {
                    bVar2.q.remove(i);
                }
                if (this.f9608a.q == null || this.f9608a.q.size() <= 0) {
                    this.f9608a.N = -1;
                    c();
                } else {
                    if (this.t == this.f9608a.N) {
                        this.f9608a.N = 0;
                    } else if (this.t < this.f9608a.N) {
                        b bVar3 = this.f9608a;
                        bVar3.N--;
                    }
                    this.w.c(this.f9608a.N);
                }
                this.s = 0;
                return;
            case 4:
                if (!b(jSONObject)) {
                    e(jSONObject);
                    return;
                }
                return;
            case 5:
                i();
                int a2 = f.a(this.f9608a, jSONObject, false);
                if (a2 != 0) {
                    b(a2);
                    return;
                }
                e a3 = f.a(jSONObject);
                if (this.f9608a.z != null && this.f9608a.z.length() > 0) {
                    a(6, a3);
                    return;
                } else if (this.f9608a.D != null && this.f9608a.D.length() > 0) {
                    d(5);
                    return;
                } else {
                    return;
                }
            case 6:
                this.b.c();
                int a4 = f.a(this.f9608a, jSONObject, false);
                if (a4 != 0) {
                    b(a4);
                    return;
                }
                e a5 = f.a(jSONObject);
                v();
                a(18, a5);
                return;
            default:
                return;
        }
    }

    public final void a(boolean z2) {
        if (this.u != null && this.u != null) {
            this.u.setEnabled(!z2);
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        String str;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        String str2 = this.f9608a.o;
        ay ayVar = new ay(this.d, str2, this);
        if (!this.f9608a.J) {
            if ((this.f9608a.aE && this.f9608a.aP == l.e.intValue()) || this.f9608a.aP == l.f9759a.intValue() || (this.f9608a.aP == l.c.intValue() && this.f9608a.aC && (this.f9608a.q == null || this.f9608a.q.size() <= 0))) {
                ayVar = new ay(this.d, str2, this.c.a(1030, -1, -1), g.a(this.d, 20.0f), this);
            }
            if (this.f9608a.aP == l.e.intValue()) {
                str = c.bD.bq;
            }
            layoutParams.addRule(13, -1);
            this.k.addView(ayVar, layoutParams);
        }
        str = c.bD.l;
        ayVar.a(str);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void b(String str, JSONObject jSONObject) {
        if ("init".equals(str)) {
            if (this.f9608a.J) {
                m();
            }
        } else if (!"".equals(str)) {
            this.b.a(c.bD.U);
            this.j = false;
            this.s = 5;
            this.e.c(str, "");
        } else if (this.s == 2) {
            d(jSONObject);
        } else if (this.s == 4) {
            e(jSONObject);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x02bf  */
    /* JADX WARNING: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x026c A[SYNTHETIC, Splitter:B:93:0x026c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r10 = this;
            android.widget.RelativeLayout r0 = r10.m
            r0.removeAllViews()
            com.unionpay.mobile.android.upwidget.UPScrollView r0 = r10.o
            r0.a((com.unionpay.mobile.android.upwidget.UPScrollView.a) r10)
            com.unionpay.mobile.android.resource.c r0 = r10.c
            r1 = -1
            r2 = 2014(0x7de, float:2.822E-42)
            android.graphics.drawable.Drawable r6 = r0.a(r2, r1, r1)
            com.unionpay.mobile.android.resource.c r0 = r10.c
            r2 = 1002(0x3ea, float:1.404E-42)
            android.graphics.drawable.Drawable r7 = r0.a(r2, r1, r1)
            com.unionpay.mobile.android.model.b r0 = r10.f9608a
            int r0 = r0.aP
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.b
            int r2 = r2.intValue()
            r9 = 0
            r3 = 1
            if (r0 == r2) goto L_0x0206
            com.unionpay.mobile.android.model.b r0 = r10.f9608a
            boolean r0 = r0.J
            if (r0 == 0) goto L_0x0031
            goto L_0x0206
        L_0x0031:
            com.unionpay.mobile.android.model.b r0 = r10.f9608a
            int r0 = r0.aP
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.c
            int r2 = r2.intValue()
            if (r0 != r2) goto L_0x0045
            android.content.Context r0 = r10.d
            com.unionpay.mobile.android.views.order.o r0 = com.unionpay.mobile.android.views.order.o.a(r0, r6, r7)
            goto L_0x0244
        L_0x0045:
            com.unionpay.mobile.android.model.b r0 = r10.f9608a
            int r0 = r0.aP
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.e
            int r2 = r2.intValue()
            if (r0 != r2) goto L_0x0098
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            boolean r2 = com.unionpay.mobile.android.model.b.bl
            if (r2 == 0) goto L_0x0083
            java.util.List<com.unionpay.mobile.android.model.d> r0 = com.unionpay.mobile.android.model.b.bb
            if (r0 == 0) goto L_0x0088
            java.util.List<com.unionpay.mobile.android.model.d> r0 = com.unionpay.mobile.android.model.b.bb
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0088
            java.util.List<com.unionpay.mobile.android.model.d> r0 = com.unionpay.mobile.android.model.b.bb
            int r0 = r0.size()
            r2 = 0
        L_0x006d:
            if (r2 >= r0) goto L_0x0088
            java.util.List<com.unionpay.mobile.android.model.d> r3 = com.unionpay.mobile.android.model.b.bb
            java.lang.Object r3 = r3.get(r2)
            com.unionpay.mobile.android.model.d r3 = (com.unionpay.mobile.android.model.d) r3
            java.util.Map r3 = b((com.unionpay.mobile.android.model.d) r3)
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r4 = r10.r
            r4.add(r3)
            int r2 = r2 + 1
            goto L_0x006d
        L_0x0083:
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r2 = r10.r
            r2.add(r0)
        L_0x0088:
            android.content.Context r3 = r10.d
            com.unionpay.mobile.android.model.b r0 = r10.f9608a
            org.json.JSONArray r4 = r0.t
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r5 = r10.r
            java.lang.String r8 = ""
            com.unionpay.mobile.android.views.order.o r0 = com.unionpay.mobile.android.views.order.o.b(r3, r4, r5, r6, r7, r8)
            goto L_0x0244
        L_0x0098:
            android.content.Context r0 = r10.d
            com.unionpay.mobile.android.views.order.o r0 = com.unionpay.mobile.android.views.order.o.a(r0, r6)
            r10.w = r0
            com.unionpay.mobile.android.views.order.o r0 = r10.w
            r0.a((android.graphics.drawable.Drawable) r7)
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.f9759a
            int r2 = r2.intValue()
            com.unionpay.mobile.android.model.b r4 = r10.f9608a
            org.json.JSONArray r4 = r4.t
            boolean r4 = com.unionpay.mobile.android.nocard.utils.f.a((org.json.JSONArray) r4)
            if (r4 != 0) goto L_0x0174
            com.unionpay.mobile.android.model.b r5 = r10.f9608a
            org.json.JSONObject r5 = r5.v
            java.lang.String r6 = "label"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r5, (java.lang.String) r6)
            com.unionpay.mobile.android.model.b r6 = r10.f9608a
            org.json.JSONObject r6 = r6.v
            if (r6 == 0) goto L_0x00d2
            int r5 = r5.length()
            if (r5 <= 0) goto L_0x00d2
            java.lang.Integer r5 = com.unionpay.mobile.android.views.order.l.c
            int r5 = r5.intValue()
            r2 = r2 | r5
        L_0x00d2:
            java.lang.String r5 = "uppay"
            java.lang.String r6 = java.lang.String.valueOf(r2)
            com.unionpay.mobile.android.utils.k.a(r5, r6)
            com.unionpay.mobile.android.model.b r5 = r10.f9608a
            org.json.JSONObject r5 = r5.w
            java.lang.String r6 = "label"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r5, (java.lang.String) r6)
            com.unionpay.mobile.android.model.b r6 = r10.f9608a
            org.json.JSONObject r6 = r6.w
            if (r6 == 0) goto L_0x010e
            int r5 = r5.length()
            if (r5 <= 0) goto L_0x010e
            com.unionpay.mobile.android.model.b r5 = r10.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r5 = r5.q
            if (r5 == 0) goto L_0x010e
            com.unionpay.mobile.android.model.b r5 = r10.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r5 = r5.q
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x010e
            android.content.Context r5 = r10.d
            com.unionpay.mobile.android.model.b r6 = r10.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r6 = r6.q
            java.util.List r3 = com.unionpay.mobile.android.nocard.views.xlistview.a.a(r5, r6, r3)
            r0.a((java.util.List<java.util.Map<java.lang.String, java.lang.Object>>) r3)
        L_0x010e:
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            boolean r3 = r3.ax
            if (r3 == 0) goto L_0x0127
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            boolean r3 = r3.ay
            if (r3 == 0) goto L_0x0127
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            boolean r3 = r3.aC
            if (r3 != 0) goto L_0x0127
            java.lang.Integer r3 = com.unionpay.mobile.android.views.order.l.d
            int r3 = r3.intValue()
            r2 = r2 | r3
        L_0x0127:
            boolean r3 = r10.t()
            if (r3 == 0) goto L_0x0140
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            boolean r3 = r3.az
            if (r3 == 0) goto L_0x0140
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            boolean r3 = r3.aC
            if (r3 != 0) goto L_0x0140
            java.lang.Integer r3 = com.unionpay.mobile.android.views.order.l.e
            int r3 = r3.intValue()
            r2 = r2 | r3
        L_0x0140:
            boolean r3 = r10.t()
            if (r3 == 0) goto L_0x0174
            boolean r3 = com.unionpay.mobile.android.model.b.aA
            if (r3 == 0) goto L_0x0174
            boolean r3 = com.unionpay.mobile.android.model.b.aB
            if (r3 == 0) goto L_0x0174
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            int r3 = r3.aP
            java.lang.Integer r5 = com.unionpay.mobile.android.views.order.l.f9759a
            int r5 = r5.intValue()
            if (r3 != r5) goto L_0x0174
            java.lang.String r3 = "1"
            com.unionpay.mobile.android.model.b r5 = r10.f9608a
            java.lang.String r5 = r5.an
            boolean r3 = r3.equalsIgnoreCase(r5)
            if (r3 != 0) goto L_0x0174
            java.lang.String r3 = "spay"
            java.lang.String r5 = "in"
            com.unionpay.mobile.android.utils.k.c(r3, r5)
            java.lang.Integer r3 = com.unionpay.mobile.android.views.order.l.f
            int r3 = r3.intValue()
            r2 = r2 | r3
        L_0x0174:
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            boolean r3 = r3.aD
            if (r3 == 0) goto L_0x0180
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.f9759a
            int r2 = r2.intValue()
        L_0x0180:
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            boolean r3 = r3.aC
            if (r3 == 0) goto L_0x01b8
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            java.lang.String r3 = r3.u
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x01b8
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r3 = r3.q
            if (r3 == 0) goto L_0x01a0
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r3 = r3.q
            int r3 = r3.size()
            if (r3 > 0) goto L_0x01b8
        L_0x01a0:
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.f9759a
            int r2 = r2.intValue()
            java.lang.Integer r3 = com.unionpay.mobile.android.views.order.l.f9759a
            int r3 = r3.intValue()
            java.lang.Integer r5 = com.unionpay.mobile.android.views.order.l.c
            int r5 = r5.intValue()
            r3 = r3 | r5
            r0.b((int) r2)
            r2 = r3
            goto L_0x01df
        L_0x01b8:
            android.content.Context r3 = r10.d
            com.unionpay.mobile.android.model.b r5 = r10.f9608a
            int r3 = com.unionpay.mobile.android.nocard.utils.c.a(r3, r5)
            r3 = r3 & r2
            if (r3 != 0) goto L_0x01cf
            com.unionpay.mobile.android.model.b r5 = r10.f9608a
            boolean r5 = r5.aD
            if (r5 != 0) goto L_0x01cf
            java.lang.Integer r3 = com.unionpay.mobile.android.views.order.l.b
            int r3 = r3.intValue()
        L_0x01cf:
            r0.b((int) r3)
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            boolean r3 = r3.aD
            if (r3 != 0) goto L_0x01df
            java.lang.Integer r3 = com.unionpay.mobile.android.views.order.l.b
            int r3 = r3.intValue()
            r2 = r2 | r3
        L_0x01df:
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            org.json.JSONArray r3 = r3.t
            r0.a((org.json.JSONArray) r3)
            java.lang.String r3 = "uppay"
            java.lang.String r5 = java.lang.String.valueOf(r2)
            com.unionpay.mobile.android.utils.k.a(r3, r5)
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            org.json.JSONObject r3 = r3.w
            java.lang.String r5 = "label"
            java.lang.String r3 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r3, (java.lang.String) r5)
            r0.a((java.lang.String) r3)
            r0.a((int) r2)
            r0.a((boolean) r4)
            r0.c()
            goto L_0x0246
        L_0x0206:
            android.content.Context r0 = r10.d
            com.unionpay.mobile.android.model.b r2 = r10.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r2 = r2.q
            java.util.List r0 = com.unionpay.mobile.android.nocard.views.xlistview.a.a(r0, r2, r3)
            com.unionpay.mobile.android.model.b r2 = r10.f9608a
            boolean r2 = r2.J
            if (r2 == 0) goto L_0x022f
            com.unionpay.mobile.android.model.b r2 = r10.f9608a
            int r2 = r2.aP
            java.lang.Integer r3 = com.unionpay.mobile.android.views.order.l.b
            int r3 = r3.intValue()
            if (r2 == r3) goto L_0x022e
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.f9759a
            int r2 = r2.intValue()
            com.unionpay.mobile.android.model.b r3 = r10.f9608a
            int r3 = r3.aP
            if (r2 != r3) goto L_0x022f
        L_0x022e:
            r0 = 0
        L_0x022f:
            r5 = r0
            com.unionpay.mobile.android.model.b r0 = r10.f9608a
            org.json.JSONObject r0 = r0.w
            java.lang.String r2 = "label"
            java.lang.String r8 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r0, (java.lang.String) r2)
            android.content.Context r3 = r10.d
            com.unionpay.mobile.android.model.b r0 = r10.f9608a
            org.json.JSONArray r4 = r0.t
            com.unionpay.mobile.android.views.order.o r0 = com.unionpay.mobile.android.views.order.o.a(r3, r4, r5, r6, r7, r8)
        L_0x0244:
            r10.w = r0
        L_0x0246:
            com.unionpay.mobile.android.views.order.o r0 = r10.w
            com.unionpay.mobile.android.nocard.views.ao$a r2 = new com.unionpay.mobile.android.nocard.views.ao$a
            r2.<init>()
            r0.a((com.unionpay.mobile.android.views.order.o.a) r2)
            com.unionpay.mobile.android.views.order.o r0 = r10.w
            com.unionpay.mobile.android.model.b r2 = r10.f9608a
            org.json.JSONObject r2 = r2.au
            r0.b((org.json.JSONObject) r2)
            com.unionpay.mobile.android.views.order.o r0 = r10.w
            com.unionpay.mobile.android.model.b r2 = r10.f9608a
            org.json.JSONObject r2 = r2.Y
            r0.c((org.json.JSONObject) r2)
            com.unionpay.mobile.android.model.b r0 = r10.f9608a
            java.lang.String r0 = r0.s
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0296
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0292 }
            r0.<init>()     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r2 = "href"
            com.unionpay.mobile.android.model.b r3 = r10.f9608a     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r3 = r3.s     // Catch:{ JSONException -> 0x0292 }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r2 = "title"
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r3 = r3.k     // Catch:{ JSONException -> 0x0292 }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r2 = "label"
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x0292 }
            java.lang.String r3 = r3.j     // Catch:{ JSONException -> 0x0292 }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0292 }
            com.unionpay.mobile.android.views.order.o r2 = r10.w     // Catch:{ JSONException -> 0x0292 }
            r2.a((org.json.JSONObject) r0)     // Catch:{ JSONException -> 0x0292 }
            goto L_0x0296
        L_0x0292:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0296:
            com.unionpay.mobile.android.resource.c r0 = r10.c
            r2 = 2008(0x7d8, float:2.814E-42)
            android.graphics.drawable.Drawable r0 = r0.a(r2, r1, r1)
            com.unionpay.mobile.android.views.order.o r2 = r10.w
            r2.b((android.graphics.drawable.Drawable) r0)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r0.<init>(r1, r1)
            android.widget.RelativeLayout r2 = r10.m
            com.unionpay.mobile.android.views.order.o r3 = r10.w
            r2.addView(r3, r0)
            com.unionpay.mobile.android.model.b r0 = r10.f9608a
            int r0 = r0.aP
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.e
            int r2 = r2.intValue()
            if (r0 != r2) goto L_0x032f
            boolean r0 = com.unionpay.mobile.android.model.b.bl
            if (r0 != 0) goto L_0x032f
            android.os.Handler r0 = r10.A
            r10.a((android.os.Handler) r0)
            android.widget.LinearLayout r0 = new android.widget.LinearLayout
            android.content.Context r2 = r10.d
            r0.<init>(r2)
            r10.x = r0
            android.widget.LinearLayout r0 = r10.x
            r0.setOrientation(r9)
            int r0 = com.unionpay.mobile.android.global.a.w
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams
            r2.<init>(r0, r0)
            r0 = 17
            r2.gravity = r0
            android.widget.ProgressBar r3 = new android.widget.ProgressBar
            android.content.Context r4 = r10.d
            r3.<init>(r4)
            android.widget.LinearLayout r4 = r10.x
            r4.addView(r3, r2)
            android.widget.TextView r2 = new android.widget.TextView
            android.content.Context r3 = r10.d
            r2.<init>(r3)
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r3 = r3.bs
            r2.setText(r3)
            float r3 = com.unionpay.mobile.android.global.b.l
            r2.setTextSize(r3)
            r3 = -10066330(0xffffffffff666666, float:-3.0625412E38)
            r2.setTextColor(r3)
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            r4 = -2
            r3.<init>(r4, r4)
            r3.gravity = r0
            int r0 = com.unionpay.mobile.android.global.a.b
            r3.leftMargin = r0
            android.widget.LinearLayout r0 = r10.x
            r0.addView(r2, r3)
            com.unionpay.mobile.android.views.order.o r0 = r10.w
            r2 = 8
            r0.setVisibility(r2)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r0.<init>(r4, r4)
            int r2 = com.unionpay.mobile.android.global.a.n
            r0.topMargin = r2
            r2 = 14
            r0.addRule(r2, r1)
            android.widget.RelativeLayout r1 = r10.m
            android.widget.LinearLayout r2 = r10.x
            r1.addView(r2, r0)
        L_0x032f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.ao.c():void");
    }

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public void d(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public final void e(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            this.f9608a.I.f = str2;
        }
        a(str, true);
    }

    public final void k() {
        if (!this.f9608a.aE && this.f9608a.J) {
            super.k();
            v();
            this.f9608a.J = false;
        } else if (this.f9608a.aE || ((this.f9608a.aP == l.f9759a.intValue() || (this.f9608a.aP == l.c.intValue() && this.f9608a.aC && (this.f9608a.q == null || this.f9608a.q.size() <= 0))) && this.f9608a.aP != l.e.intValue())) {
            v();
            s();
        } else {
            super.k();
            v();
            this.f9608a.aP = l.f9759a.intValue();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (b.bm && this.f9608a.aP == l.f9759a.intValue() && !"1".equalsIgnoreCase(this.f9608a.an)) {
            k.c("spay", "out");
            d(this.f9608a.bp, this.f9608a.bq);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.v != null) {
            this.v.clearFocus();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.y && this.z != null) {
            this.y = true;
            this.z.sendEmptyMessage(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i, int i2, int i3, int i4) {
        super.onLayout(z2, i, i2, i3, i4);
    }

    public final void r() {
    }

    /* access modifiers changed from: protected */
    public final void s() {
        this.b.a(new ar(this), new as(this));
        this.b.a(c.bD.Y, c.bD.av, c.bD.W, c.bD.X);
    }

    /* access modifiers changed from: protected */
    public boolean t() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void u() {
    }
}
