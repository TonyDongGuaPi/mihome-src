package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;
import com.unionpay.mobile.android.views.order.AbstractMethod;
import com.unionpay.mobile.android.views.order.CViewMethods;
import com.unionpay.mobile.android.views.order.b;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class o extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private Context f9762a;
    private int b = l.f9759a.intValue();
    private int c = l.f9759a.intValue();
    private JSONObject d;
    private JSONObject e;
    private JSONObject f;
    private Drawable g;
    private JSONArray h;
    private List<Map<String, Object>> i;
    private String j;
    private AbstractMethod k;
    private CViewMethods l;
    private Drawable m;
    private boolean n;

    public interface a extends AbstractMethod.a, AbstractMethod.b, CViewMethods.a, b.C0079b {
    }

    private o(Context context) {
        super(context);
        this.f9762a = context;
        setOrientation(1);
    }

    public static o a(Context context, Drawable drawable) {
        o oVar = new o(context);
        oVar.g = drawable;
        return oVar;
    }

    public static o a(Context context, Drawable drawable, Drawable drawable2) {
        o oVar = new o(context);
        oVar.m = drawable2;
        oVar.b = l.c.intValue();
        oVar.c = l.c.intValue();
        oVar.g = drawable;
        oVar.c();
        return oVar;
    }

    public static o a(Context context, JSONArray jSONArray, List<Map<String, Object>> list, Drawable drawable, Drawable drawable2, String str) {
        o oVar = new o(context);
        oVar.m = drawable2;
        oVar.b = l.b.intValue();
        oVar.c = l.b.intValue();
        oVar.g = drawable;
        oVar.h = jSONArray;
        oVar.i = list;
        oVar.j = str;
        oVar.c();
        return oVar;
    }

    public static o b(Context context, JSONArray jSONArray, List<Map<String, Object>> list, Drawable drawable, Drawable drawable2, String str) {
        o oVar = new o(context);
        oVar.m = drawable2;
        oVar.b = l.e.intValue();
        oVar.c = l.e.intValue();
        oVar.g = drawable;
        oVar.h = jSONArray;
        oVar.i = list;
        oVar.j = str;
        oVar.c();
        return oVar;
    }

    public final int a() {
        return this.b;
    }

    public final o a(int i2) {
        this.c = i2;
        return this;
    }

    public final o a(Drawable drawable) {
        this.m = drawable;
        return this;
    }

    public final o a(a aVar) {
        if (this.k != null) {
            this.k.a((AbstractMethod.b) aVar);
            this.k.a((AbstractMethod.a) aVar);
            if (this.k instanceof b) {
                ((b) this.k).a((b.C0079b) aVar);
            }
        }
        if (this.l != null) {
            this.l.a((CViewMethods.a) aVar);
        }
        return this;
    }

    public final o a(String str) {
        this.j = str;
        return this;
    }

    public final o a(List<Map<String, Object>> list) {
        this.i = list;
        return this;
    }

    public final o a(JSONArray jSONArray) {
        this.h = jSONArray;
        return this;
    }

    public final o a(JSONObject jSONObject) {
        this.d = jSONObject;
        if (this.k != null && (this.k instanceof b)) {
            ((b) this.k).a(this.d);
        }
        return this;
    }

    public final o a(boolean z) {
        this.n = z;
        return this;
    }

    public final o b(int i2) {
        this.b = i2;
        return this;
    }

    public final o b(Drawable drawable) {
        if (this.k != null) {
            this.k.a(drawable);
        }
        return this;
    }

    public final o b(JSONObject jSONObject) {
        this.e = jSONObject;
        if (this.k != null && (this.k instanceof i)) {
            ((i) this.k).a(this.e);
        }
        return this;
    }

    public final String b() {
        if (this.k == null || !(this.k instanceof i)) {
            return null;
        }
        return ((i) this.k).h();
    }

    public final void b(String str) {
        ((b) this.k).b(str);
    }

    public final o c(JSONObject jSONObject) {
        this.f = jSONObject;
        if (this.k != null && (this.k instanceof i)) {
            ((i) this.k).b(this.f);
        }
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: com.unionpay.mobile.android.views.order.i} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: com.unionpay.mobile.android.views.order.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: com.unionpay.mobile.android.views.order.i} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: com.unionpay.mobile.android.views.order.i} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x012f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0130  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r10 = this;
            r0 = 0
            r10.k = r0
            int r1 = r10.b
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.b
            int r2 = r2.intValue()
            r3 = 1002(0x3ea, float:1.404E-42)
            r4 = 1016(0x3f8, float:1.424E-42)
            r5 = 1015(0x3f7, float:1.422E-42)
            r6 = 2014(0x7de, float:2.822E-42)
            r7 = -1
            if (r1 != r2) goto L_0x0070
            int r0 = r10.c
            java.lang.Integer r1 = com.unionpay.mobile.android.views.order.l.b
            int r1 = r1.intValue()
            r1 = r1 ^ r7
            r0 = r0 & r1
            r10.c = r0
            com.unionpay.mobile.android.views.order.b r0 = new com.unionpay.mobile.android.views.order.b
            android.content.Context r1 = r10.f9762a
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r2 = r10.i
            java.lang.String r8 = r10.j
            r0.<init>(r1, r2, r8)
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.by
            r0.d(r1)
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.bz
            r0.e(r1)
            org.json.JSONObject r1 = r10.d
            r0.a((org.json.JSONObject) r1)
            org.json.JSONArray r1 = r10.h
            r0.a((org.json.JSONArray) r1)
            boolean r1 = r10.n
            r0.b((boolean) r1)
            android.content.Context r1 = r10.f9762a
            com.unionpay.mobile.android.resource.c r1 = com.unionpay.mobile.android.resource.c.a(r1)
            android.graphics.drawable.Drawable r1 = r1.a(r6, r7, r7)
            r0.b((android.graphics.drawable.Drawable) r1)
            android.content.Context r1 = r10.f9762a
            com.unionpay.mobile.android.resource.c r1 = com.unionpay.mobile.android.resource.c.a(r1)
            android.graphics.drawable.Drawable r2 = r1.a(r5, r7, r7)
            android.graphics.drawable.Drawable r4 = r1.a(r4, r7, r7)
            android.graphics.drawable.Drawable r1 = r1.a(r3, r7, r7)
            r0.a(r2, r4, r1)
        L_0x006c:
            r10.k = r0
            goto L_0x010d
        L_0x0070:
            int r1 = r10.b
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.c
            int r2 = r2.intValue()
            if (r1 != r2) goto L_0x00a6
            int r0 = r10.c
            java.lang.Integer r1 = com.unionpay.mobile.android.views.order.l.c
            int r1 = r1.intValue()
            r1 = r1 ^ r7
            r0 = r0 & r1
            r10.c = r0
            com.unionpay.mobile.android.views.order.i r0 = new com.unionpay.mobile.android.views.order.i
            android.content.Context r1 = r10.f9762a
            r0.<init>(r1)
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.bA
            r0.b((java.lang.String) r1)
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.bB
            r0.d(r1)
            org.json.JSONObject r1 = r10.e
            r0.a((org.json.JSONObject) r1)
            org.json.JSONObject r1 = r10.f
            r0.b((org.json.JSONObject) r1)
            goto L_0x006c
        L_0x00a6:
            int r1 = r10.b
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.e
            int r2 = r2.intValue()
            if (r1 != r2) goto L_0x010d
            int r1 = r10.c
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.e
            int r2 = r2.intValue()
            r2 = r2 ^ r7
            r1 = r1 & r2
            r10.c = r1
            com.unionpay.mobile.android.views.order.b r1 = new com.unionpay.mobile.android.views.order.b
            android.content.Context r2 = r10.f9762a
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r8 = r10.i
            java.lang.String r9 = ""
            r1.<init>(r2, r8, r9)
            java.lang.String r2 = ""
            r1.d(r2)
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.bz
            r1.e(r2)
            r1.a((org.json.JSONObject) r0)
            org.json.JSONArray r0 = r10.h
            r1.a((org.json.JSONArray) r0)
            java.lang.Integer r0 = com.unionpay.mobile.android.views.order.l.e
            int r0 = r0.intValue()
            r1.b((int) r0)
            java.lang.String r0 = ""
            r1.f(r0)
            android.content.Context r0 = r10.f9762a
            com.unionpay.mobile.android.resource.c r0 = com.unionpay.mobile.android.resource.c.a(r0)
            android.graphics.drawable.Drawable r0 = r0.a(r6, r7, r7)
            r1.b((android.graphics.drawable.Drawable) r0)
            android.content.Context r0 = r10.f9762a
            com.unionpay.mobile.android.resource.c r0 = com.unionpay.mobile.android.resource.c.a(r0)
            android.graphics.drawable.Drawable r2 = r0.a(r5, r7, r7)
            android.graphics.drawable.Drawable r4 = r0.a(r4, r7, r7)
            android.graphics.drawable.Drawable r0 = r0.a(r3, r7, r7)
            r1.a(r2, r4, r0)
            r10.k = r1
        L_0x010d:
            com.unionpay.mobile.android.views.order.AbstractMethod r0 = r10.k
            r1 = -2
            if (r0 == 0) goto L_0x0125
            com.unionpay.mobile.android.views.order.AbstractMethod r0 = r10.k
            r0.a()
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r0.<init>(r7, r1)
            int r2 = com.unionpay.mobile.android.global.b.f9560a
            r0.topMargin = r2
            com.unionpay.mobile.android.views.order.AbstractMethod r2 = r10.k
            r10.addView(r2, r0)
        L_0x0125:
            int r0 = r10.b
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.e
            int r2 = r2.intValue()
            if (r0 != r2) goto L_0x0130
            return
        L_0x0130:
            com.unionpay.mobile.android.views.order.CViewMethods r0 = new com.unionpay.mobile.android.views.order.CViewMethods
            android.content.Context r2 = r10.f9762a
            r0.<init>(r2)
            r10.l = r0
            com.unionpay.mobile.android.views.order.CViewMethods r0 = r10.l
            android.graphics.drawable.Drawable r2 = r10.g
            r0.a((android.graphics.drawable.Drawable) r2)
            com.unionpay.mobile.android.views.order.CViewMethods r0 = r10.l
            int r2 = r10.c
            r0.a((int) r2)
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.b
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r3 = r3.by
            r0.put(r2, r3)
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.bp
            if (r2 == 0) goto L_0x016c
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.bp
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x016c
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.d
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r3 = r3.bp
            goto L_0x0172
        L_0x016c:
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.d
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r3 = r3.bo
        L_0x0172:
            r0.put(r2, r3)
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.c
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r3 = r3.bA
            r0.put(r2, r3)
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.br
            if (r2 == 0) goto L_0x0195
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.br
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x0195
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.e
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r3 = r3.br
            goto L_0x019b
        L_0x0195:
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.e
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r3 = r3.bq
        L_0x019b:
            r0.put(r2, r3)
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.f
            com.unionpay.mobile.android.languages.c r3 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r3 = r3.bt
            r0.put(r2, r3)
            com.unionpay.mobile.android.views.order.CViewMethods r2 = r10.l
            r2.a((java.util.HashMap<java.lang.Integer, java.lang.String>) r0)
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.b
            android.graphics.drawable.Drawable r3 = r10.m
            r0.put(r2, r3)
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.d
            android.graphics.drawable.Drawable r3 = r10.m
            r0.put(r2, r3)
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.c
            android.graphics.drawable.Drawable r3 = r10.m
            r0.put(r2, r3)
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.e
            android.graphics.drawable.Drawable r3 = r10.m
            r0.put(r2, r3)
            java.lang.Integer r2 = com.unionpay.mobile.android.views.order.l.f
            android.graphics.drawable.Drawable r3 = r10.m
            r0.put(r2, r3)
            com.unionpay.mobile.android.views.order.CViewMethods r2 = r10.l
            r2.b(r0)
            com.unionpay.mobile.android.views.order.CViewMethods r0 = r10.l
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.bC
            com.unionpay.mobile.android.views.order.CViewMethods r0 = r0.a((java.lang.String) r2)
            r0.a()
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r0.<init>(r7, r1)
            com.unionpay.mobile.android.views.order.CViewMethods r1 = r10.l
            r10.addView(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.views.order.o.c():void");
    }

    public final void c(int i2) {
        if (this.k != null && (this.k instanceof b)) {
            ((b) this.k).a(i2);
        }
    }
}
