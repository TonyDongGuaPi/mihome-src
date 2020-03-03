package com.unionpay.mobile.android.nocard.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.utils.d;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.resource.c;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.aa;
import com.unionpay.mobile.android.widgets.as;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.mobile.android.widgets.m;
import com.unionpay.mobile.android.widgets.u;
import org.json.JSONObject;

public abstract class b extends RelativeLayout implements UPPayEngine.a, a, UPScrollView.a, aa.a, ay.a {

    /* renamed from: a  reason: collision with root package name */
    protected com.unionpay.mobile.android.model.b f9608a;
    protected m b;
    protected c c;
    protected Context d;
    protected UPPayEngine e;
    protected int f;
    protected String g;
    protected String h;
    protected String i;
    protected boolean j;
    protected RelativeLayout k;
    protected ViewGroup l;
    protected RelativeLayout m;
    protected as n;
    protected UPScrollView o;
    protected e p;
    protected String q;
    /* access modifiers changed from: private */
    public LinearLayout r;
    private LinearLayout s;
    private LinearLayout t;
    /* access modifiers changed from: private */
    public int u;
    /* access modifiers changed from: private */
    public int v;
    private Activity w;

    public b(Context context) {
        this(context, (e) null);
    }

    public b(Context context, e eVar) {
        super(context);
        this.f9608a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = true;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.q = "uppay";
        this.w = null;
        this.f = 0;
        this.d = context;
        if (this.d instanceof Activity) {
            this.w = (Activity) this.d;
        }
        this.p = eVar;
        BaseActivity baseActivity = (BaseActivity) context;
        this.e = (UPPayEngine) baseActivity.a(UPPayEngine.class.toString());
        this.f9608a = (com.unionpay.mobile.android.model.b) baseActivity.a((String) null);
        this.b = (m) baseActivity.a(m.class.toString());
        this.c = c.a(context);
        setId(8888);
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        setBackgroundColor(-1);
        k.b("uppayEx", "UPViewBase:" + toString());
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r3, boolean r4, boolean r5) {
        /*
            r2 = this;
            android.content.Context r0 = r2.d
            com.unionpay.mobile.android.plugin.BaseActivity r0 = (com.unionpay.mobile.android.plugin.BaseActivity) r0
            r1 = 0
            switch(r3) {
                case 2: goto L_0x007f;
                case 3: goto L_0x0008;
                case 4: goto L_0x0008;
                case 5: goto L_0x0077;
                case 6: goto L_0x003c;
                case 7: goto L_0x0008;
                case 8: goto L_0x0034;
                case 9: goto L_0x0008;
                case 10: goto L_0x002c;
                case 11: goto L_0x0024;
                case 12: goto L_0x001c;
                case 13: goto L_0x0013;
                case 14: goto L_0x000a;
                case 15: goto L_0x0008;
                case 16: goto L_0x0008;
                case 17: goto L_0x007f;
                case 18: goto L_0x007f;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0083
        L_0x000a:
            com.unionpay.mobile.android.nocard.views.bi r1 = new com.unionpay.mobile.android.nocard.views.bi
            android.content.Context r3 = r2.d
            r1.<init>(r3, r4, r5)
            goto L_0x0083
        L_0x0013:
            com.unionpay.mobile.android.nocard.views.o r3 = new com.unionpay.mobile.android.nocard.views.o
            android.content.Context r4 = r2.d
            r3.<init>(r4, r1)
            r1 = r3
            goto L_0x0083
        L_0x001c:
            com.unionpay.mobile.android.nocard.views.af r1 = new com.unionpay.mobile.android.nocard.views.af
            android.content.Context r3 = r2.d
            r1.<init>(r3)
            goto L_0x0083
        L_0x0024:
            com.unionpay.mobile.android.nocard.views.ai r1 = new com.unionpay.mobile.android.nocard.views.ai
            android.content.Context r3 = r2.d
            r1.<init>(r3)
            goto L_0x0083
        L_0x002c:
            com.unionpay.mobile.android.nocard.views.ak r1 = new com.unionpay.mobile.android.nocard.views.ak
            android.content.Context r3 = r2.d
            r1.<init>(r3)
            goto L_0x0083
        L_0x0034:
            com.unionpay.mobile.android.nocard.views.bd r1 = new com.unionpay.mobile.android.nocard.views.bd
            android.content.Context r3 = r2.d
            r1.<init>(r3)
            goto L_0x0083
        L_0x003c:
            r3 = 0
            com.unionpay.mobile.android.model.b r4 = r2.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.q
            if (r4 == 0) goto L_0x005f
            com.unionpay.mobile.android.model.b r4 = r2.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.q
            int r4 = r4.size()
            if (r4 <= 0) goto L_0x005f
            com.unionpay.mobile.android.model.b r3 = r2.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r3 = r3.q
            com.unionpay.mobile.android.model.b r4 = r2.f9608a
            int r4 = r4.N
            java.lang.Object r3 = r3.get(r4)
            com.unionpay.mobile.android.model.c r3 = (com.unionpay.mobile.android.model.c) r3
            int r3 = r3.c()
        L_0x005f:
            boolean r4 = r2.h()
            if (r4 != 0) goto L_0x0067
            if (r3 != 0) goto L_0x0075
        L_0x0067:
            com.unionpay.mobile.android.model.b r3 = r2.f9608a
            boolean r3 = r3.br
            if (r3 != 0) goto L_0x0075
            com.unionpay.mobile.android.nocard.views.at r1 = new com.unionpay.mobile.android.nocard.views.at
            android.content.Context r3 = r2.d
            r1.<init>(r3)
            goto L_0x0083
        L_0x0075:
            r3 = 6
            goto L_0x007f
        L_0x0077:
            com.unionpay.mobile.android.nocard.views.g r1 = new com.unionpay.mobile.android.nocard.views.g
            android.content.Context r3 = r2.d
            r1.<init>(r3)
            goto L_0x0083
        L_0x007f:
            com.unionpay.mobile.android.nocard.views.b r1 = r0.a(r3, r1)
        L_0x0083:
            if (r1 == 0) goto L_0x0088
            r0.a((com.unionpay.mobile.android.nocard.views.b) r1)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.b.a(int, boolean, boolean):void");
    }

    protected static boolean b(String str) {
        return str != null && str.length() > 0;
    }

    protected static ColorStateList o() {
        return h.a(com.unionpay.mobile.android.global.b.b, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.d);
    }

    private RelativeLayout r() {
        int i2;
        LinearLayout linearLayout;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (this.k != null) {
            layoutParams.addRule(3, this.k.getId());
            layoutParams.addRule(12, -1);
        }
        FrameLayout frameLayout = new FrameLayout(this.d);
        addView(frameLayout, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        this.o = new UPScrollView(this.d);
        this.o.setPadding(0, 0, 0, 0);
        frameLayout.addView(this.o, layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        int a2 = g.a(this.d, 10.0f);
        this.t = new LinearLayout(this.d);
        this.t.setId(this.t.hashCode());
        this.t.setOrientation(1);
        if (!this.f9608a.aM || com.unionpay.mobile.android.model.b.bm) {
            linearLayout = this.t;
            i2 = -267336;
        } else {
            linearLayout = this.t;
            i2 = -34177;
        }
        linearLayout.setBackgroundColor(i2);
        this.t.setPadding(a2, a2, a2, a2);
        String str = "";
        if (b(this.f9608a.ar)) {
            str = str + this.f9608a.ar;
        }
        if (b(str)) {
            TextView textView = new TextView(this.d);
            if (!this.f9608a.aM || com.unionpay.mobile.android.model.b.bm) {
                textView.setTextColor(-10066330);
            } else {
                textView.setTextColor(-1);
            }
            textView.setText(str);
            textView.setTextSize(com.unionpay.mobile.android.global.b.k);
            this.t.addView(textView);
        } else {
            this.t.setVisibility(8);
        }
        this.t.setVisibility(8);
        frameLayout.addView(this.t, layoutParams3);
        RelativeLayout relativeLayout = new RelativeLayout(this.d);
        relativeLayout.setBackgroundColor(-1052684);
        this.o.addView(relativeLayout, new RelativeLayout.LayoutParams(-1, -1));
        return relativeLayout;
    }

    /* access modifiers changed from: protected */
    public final RelativeLayout a() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setId(relativeLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10, -1);
        addView(relativeLayout, layoutParams);
        return relativeLayout;
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.unionpay.mobile.android.widgets.ao] */
    /* JADX WARNING: type inference failed for: r0v6, types: [com.unionpay.mobile.android.widgets.au] */
    /* JADX WARNING: type inference failed for: r0v7, types: [com.unionpay.mobile.android.widgets.y] */
    /* JADX WARNING: type inference failed for: r0v8, types: [com.unionpay.mobile.android.widgets.ae] */
    /* JADX WARNING: type inference failed for: r0v9, types: [com.unionpay.mobile.android.widgets.g] */
    /* JADX WARNING: type inference failed for: r0v10, types: [com.unionpay.mobile.android.widgets.f] */
    /* JADX WARNING: type inference failed for: r0v11, types: [com.unionpay.mobile.android.widgets.ad] */
    /* JADX WARNING: type inference failed for: r0v12, types: [com.unionpay.mobile.android.widgets.at] */
    /* JADX WARNING: type inference failed for: r0v14, types: [com.unionpay.mobile.android.widgets.av] */
    /* JADX WARNING: type inference failed for: r0v15, types: [com.unionpay.mobile.android.widgets.e] */
    /* JADX WARNING: type inference failed for: r0v16, types: [com.unionpay.mobile.android.widgets.ap] */
    /* JADX WARNING: type inference failed for: r0v17, types: [com.unionpay.mobile.android.widgets.ah] */
    /* JADX WARNING: type inference failed for: r0v18, types: [com.unionpay.mobile.android.widgets.af] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.unionpay.mobile.android.widgets.z a(org.json.JSONObject r11, java.lang.String r12) {
        /*
            r10 = this;
            java.lang.String r0 = "type"
            java.lang.String r0 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r11, (java.lang.String) r0)
            int r1 = com.unionpay.mobile.android.global.a.I
            int r2 = com.unionpay.mobile.android.global.a.f
            int r2 = r2 * 4
            int r7 = r1 - r2
            java.lang.String r1 = "pan"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x001f
            com.unionpay.mobile.android.widgets.af r0 = new com.unionpay.mobile.android.widgets.af
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x001f:
            java.lang.String r1 = "mobile"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x0030
            com.unionpay.mobile.android.widgets.ah r0 = new com.unionpay.mobile.android.widgets.ah
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x0030:
            java.lang.String r1 = "sms"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x0041
            com.unionpay.mobile.android.widgets.ap r0 = new com.unionpay.mobile.android.widgets.ap
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x0041:
            java.lang.String r1 = "cvn2"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x0052
            com.unionpay.mobile.android.widgets.e r0 = new com.unionpay.mobile.android.widgets.e
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x0052:
            java.lang.String r1 = "expire"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x0063
            com.unionpay.mobile.android.widgets.av r0 = new com.unionpay.mobile.android.widgets.av
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x0063:
            java.lang.String r1 = "pwd"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x007d
            com.unionpay.mobile.android.widgets.UPWidget r0 = new com.unionpay.mobile.android.widgets.UPWidget
            android.content.Context r4 = r10.d
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r1 = r10.e
            long r5 = r1.c()
            r3 = r0
            r8 = r11
            r9 = r12
            r3.<init>(r4, r5, r7, r8, r9)
            goto L_0x00ff
        L_0x007d:
            java.lang.String r1 = "text"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x008e
            com.unionpay.mobile.android.widgets.at r0 = new com.unionpay.mobile.android.widgets.at
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x008e:
            java.lang.String r1 = "string"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x009e
            com.unionpay.mobile.android.widgets.ad r0 = new com.unionpay.mobile.android.widgets.ad
            android.content.Context r1 = r10.d
            r0.<init>(r1, r11, r12)
            goto L_0x00ff
        L_0x009e:
            java.lang.String r1 = "cert_id"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x00ae
            com.unionpay.mobile.android.widgets.f r0 = new com.unionpay.mobile.android.widgets.f
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x00ae:
            java.lang.String r1 = "cert_type"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x00be
            com.unionpay.mobile.android.widgets.g r0 = new com.unionpay.mobile.android.widgets.g
            android.content.Context r1 = r10.d
            r0.<init>(r1, r11, r12)
            goto L_0x00ff
        L_0x00be:
            java.lang.String r1 = "name"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x00ce
            com.unionpay.mobile.android.widgets.ae r0 = new com.unionpay.mobile.android.widgets.ae
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x00ce:
            java.lang.String r1 = "hidden"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x00de
            com.unionpay.mobile.android.widgets.y r0 = new com.unionpay.mobile.android.widgets.y
            android.content.Context r1 = r10.d
            r0.<init>(r1, r11, r12)
            goto L_0x00ff
        L_0x00de:
            java.lang.String r1 = "user_name"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x00ee
            com.unionpay.mobile.android.widgets.au r0 = new com.unionpay.mobile.android.widgets.au
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x00ee:
            java.lang.String r1 = "password"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x00fe
            com.unionpay.mobile.android.widgets.ao r0 = new com.unionpay.mobile.android.widgets.ao
            android.content.Context r1 = r10.d
            r0.<init>(r1, r7, r11, r12)
            goto L_0x00ff
        L_0x00fe:
            r0 = 0
        L_0x00ff:
            if (r0 == 0) goto L_0x010b
            boolean r11 = r0 instanceof com.unionpay.mobile.android.widgets.aa
            if (r11 == 0) goto L_0x010b
            r11 = r0
            com.unionpay.mobile.android.widgets.aa r11 = (com.unionpay.mobile.android.widgets.aa) r11
            r11.a((com.unionpay.mobile.android.widgets.aa.a) r10)
        L_0x010b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.b.a(org.json.JSONObject, java.lang.String):com.unionpay.mobile.android.widgets.z");
    }

    public final void a(int i2) {
        ((BaseActivity) this.d).a(i2);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r3, com.unionpay.mobile.android.model.e r4) {
        /*
            r2 = this;
            android.content.Context r0 = r2.d
            com.unionpay.mobile.android.plugin.BaseActivity r0 = (com.unionpay.mobile.android.plugin.BaseActivity) r0
            switch(r3) {
                case 2: goto L_0x008c;
                case 3: goto L_0x0007;
                case 4: goto L_0x0007;
                case 5: goto L_0x0084;
                case 6: goto L_0x003d;
                case 7: goto L_0x0007;
                case 8: goto L_0x0035;
                case 9: goto L_0x0007;
                case 10: goto L_0x002d;
                case 11: goto L_0x0025;
                case 12: goto L_0x001c;
                case 13: goto L_0x0013;
                case 14: goto L_0x000a;
                case 15: goto L_0x0007;
                case 16: goto L_0x0007;
                case 17: goto L_0x008c;
                case 18: goto L_0x008c;
                default: goto L_0x0007;
            }
        L_0x0007:
            r3 = 0
            goto L_0x0090
        L_0x000a:
            com.unionpay.mobile.android.nocard.views.bi r3 = new com.unionpay.mobile.android.nocard.views.bi
            android.content.Context r4 = r2.d
            r3.<init>(r4)
            goto L_0x0090
        L_0x0013:
            com.unionpay.mobile.android.nocard.views.o r3 = new com.unionpay.mobile.android.nocard.views.o
            android.content.Context r1 = r2.d
            r3.<init>(r1, r4)
            goto L_0x0090
        L_0x001c:
            com.unionpay.mobile.android.nocard.views.af r3 = new com.unionpay.mobile.android.nocard.views.af
            android.content.Context r4 = r2.d
            r3.<init>(r4)
            goto L_0x0090
        L_0x0025:
            com.unionpay.mobile.android.nocard.views.ai r3 = new com.unionpay.mobile.android.nocard.views.ai
            android.content.Context r4 = r2.d
            r3.<init>(r4)
            goto L_0x0090
        L_0x002d:
            com.unionpay.mobile.android.nocard.views.ak r3 = new com.unionpay.mobile.android.nocard.views.ak
            android.content.Context r4 = r2.d
            r3.<init>(r4)
            goto L_0x0090
        L_0x0035:
            com.unionpay.mobile.android.nocard.views.bd r3 = new com.unionpay.mobile.android.nocard.views.bd
            android.content.Context r4 = r2.d
            r3.<init>(r4)
            goto L_0x0090
        L_0x003d:
            r3 = 0
            com.unionpay.mobile.android.model.b r1 = r2.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r1 = r1.q
            if (r1 == 0) goto L_0x0060
            com.unionpay.mobile.android.model.b r1 = r2.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r1 = r1.q
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x0060
            com.unionpay.mobile.android.model.b r3 = r2.f9608a
            java.util.List<com.unionpay.mobile.android.model.c> r3 = r3.q
            com.unionpay.mobile.android.model.b r1 = r2.f9608a
            int r1 = r1.N
            java.lang.Object r3 = r3.get(r1)
            com.unionpay.mobile.android.model.c r3 = (com.unionpay.mobile.android.model.c) r3
            int r3 = r3.c()
        L_0x0060:
            boolean r1 = r2.h()
            if (r1 != 0) goto L_0x0074
            if (r3 == 0) goto L_0x0074
            com.unionpay.mobile.android.model.b r3 = r2.f9608a
            int r3 = r3.aP
            java.lang.Integer r1 = com.unionpay.mobile.android.views.order.l.c
            int r1 = r1.intValue()
            if (r3 != r1) goto L_0x0082
        L_0x0074:
            com.unionpay.mobile.android.model.b r3 = r2.f9608a
            boolean r3 = r3.br
            if (r3 != 0) goto L_0x0082
            com.unionpay.mobile.android.nocard.views.at r3 = new com.unionpay.mobile.android.nocard.views.at
            android.content.Context r1 = r2.d
            r3.<init>(r1, r4)
            goto L_0x0090
        L_0x0082:
            r3 = 6
            goto L_0x008c
        L_0x0084:
            com.unionpay.mobile.android.nocard.views.g r3 = new com.unionpay.mobile.android.nocard.views.g
            android.content.Context r4 = r2.d
            r3.<init>(r4)
            goto L_0x0090
        L_0x008c:
            com.unionpay.mobile.android.nocard.views.b r3 = r0.a(r3, r4)
        L_0x0090:
            if (r3 == 0) goto L_0x0095
            r0.a((com.unionpay.mobile.android.nocard.views.b) r3)
        L_0x0095:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.b.a(int, com.unionpay.mobile.android.model.e):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0122  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 1
            r5.j = r0
            java.lang.String r1 = "uppay"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = " "
            r2.<init>(r3)
            java.lang.String r3 = r5.toString()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.unionpay.mobile.android.utils.k.a(r1, r2)
            if (r6 != 0) goto L_0x012d
            java.lang.String r6 = "uppay"
            java.lang.String r1 = "parserResponseMesage() +++"
            com.unionpay.mobile.android.utils.k.a(r6, r1)
            r6 = 0
            r1 = 2
            if (r7 == 0) goto L_0x010c
            int r2 = r7.length()
            if (r2 != 0) goto L_0x002f
            goto L_0x010c
        L_0x002f:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00fd }
            r2.<init>(r7)     // Catch:{ JSONException -> 0x00fd }
            java.lang.String r7 = "resp"
            java.lang.String r7 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r2, (java.lang.String) r7)     // Catch:{ JSONException -> 0x00fd }
            r5.g = r7     // Catch:{ JSONException -> 0x00fd }
            java.lang.String r7 = "msg"
            java.lang.String r7 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r2, (java.lang.String) r7)     // Catch:{ JSONException -> 0x00fd }
            r5.h = r7     // Catch:{ JSONException -> 0x00fd }
            java.lang.String r7 = "cmd"
            java.lang.String r7 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r2, (java.lang.String) r7)     // Catch:{ JSONException -> 0x00fd }
            r5.i = r7     // Catch:{ JSONException -> 0x00fd }
            java.lang.String r7 = "params"
            org.json.JSONObject r7 = com.unionpay.mobile.android.utils.j.c(r2, r7)     // Catch:{ JSONException -> 0x00fd }
            java.lang.String r6 = r5.g     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r2 = "00"
            boolean r6 = r6.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x00fb }
            r2 = 0
            if (r6 != 0) goto L_0x00f9
            java.lang.String r6 = "pay"
            java.lang.String r3 = r5.i     // Catch:{ JSONException -> 0x00fb }
            boolean r6 = r6.equals(r3)     // Catch:{ JSONException -> 0x00fb }
            if (r6 != 0) goto L_0x0083
            com.unionpay.mobile.android.model.b r6 = r5.f9608a     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r6 = r6.E     // Catch:{ JSONException -> 0x00fb }
            if (r6 == 0) goto L_0x009e
            com.unionpay.mobile.android.model.b r6 = r5.f9608a     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r6 = r6.E     // Catch:{ JSONException -> 0x00fb }
            int r6 = r6.length()     // Catch:{ JSONException -> 0x00fb }
            if (r6 <= 0) goto L_0x009e
            com.unionpay.mobile.android.model.b r6 = r5.f9608a     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r6 = r6.E     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = r5.i     // Catch:{ JSONException -> 0x00fb }
            boolean r6 = r6.equals(r3)     // Catch:{ JSONException -> 0x00fb }
            if (r6 == 0) goto L_0x009e
        L_0x0083:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00fb }
            r6.<init>()     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = r5.q     // Catch:{ JSONException -> 0x00fb }
            r6.append(r3)     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = "_fail"
            r6.append(r3)     // Catch:{ JSONException -> 0x00fb }
            java.lang.String[] r6 = com.unionpay.mobile.android.utils.o.j     // Catch:{ JSONException -> 0x00fb }
            java.lang.String[] r6 = new java.lang.String[r1]     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = r5.g     // Catch:{ JSONException -> 0x00fb }
            r6[r2] = r3     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = r5.h     // Catch:{ JSONException -> 0x00fb }
            r6[r0] = r3     // Catch:{ JSONException -> 0x00fb }
        L_0x009e:
            java.lang.String r6 = "rules"
            java.lang.String r3 = r5.i     // Catch:{ JSONException -> 0x00fb }
            boolean r6 = r6.equals(r3)     // Catch:{ JSONException -> 0x00fb }
            if (r6 == 0) goto L_0x00c3
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00fb }
            r6.<init>()     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = r5.q     // Catch:{ JSONException -> 0x00fb }
            r6.append(r3)     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = "_cardNO_fail"
            r6.append(r3)     // Catch:{ JSONException -> 0x00fb }
            java.lang.String[] r6 = com.unionpay.mobile.android.utils.o.j     // Catch:{ JSONException -> 0x00fb }
            java.lang.String[] r6 = new java.lang.String[r1]     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = r5.g     // Catch:{ JSONException -> 0x00fb }
            r6[r2] = r3     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = r5.h     // Catch:{ JSONException -> 0x00fb }
            r6[r0] = r3     // Catch:{ JSONException -> 0x00fb }
        L_0x00c3:
            java.lang.String r6 = "getuserinfo"
            java.lang.String r3 = r5.i     // Catch:{ JSONException -> 0x00fb }
            boolean r6 = r6.equals(r3)     // Catch:{ JSONException -> 0x00fb }
            if (r6 == 0) goto L_0x00d9
            java.lang.String[] r6 = com.unionpay.mobile.android.utils.o.j     // Catch:{ JSONException -> 0x00fb }
            java.lang.String[] r6 = new java.lang.String[r1]     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r3 = r5.g     // Catch:{ JSONException -> 0x00fb }
            r6[r2] = r3     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r2 = r5.h     // Catch:{ JSONException -> 0x00fb }
            r6[r0] = r2     // Catch:{ JSONException -> 0x00fb }
        L_0x00d9:
            java.lang.String r6 = r5.g     // Catch:{ JSONException -> 0x00fb }
            java.lang.String r0 = "21"
            boolean r6 = r6.equalsIgnoreCase(r0)     // Catch:{ JSONException -> 0x00fb }
            if (r6 == 0) goto L_0x00ef
            r6 = 17
            java.lang.String r0 = "uppay"
            java.lang.String r2 = " ERROR_ORDER_TIMEOUT"
            com.unionpay.mobile.android.utils.k.a(r0, r2)     // Catch:{ JSONException -> 0x00fb }
            r1 = 17
            goto L_0x0114
        L_0x00ef:
            r6 = 3
            java.lang.String r0 = "uppay"
            java.lang.String r2 = " ERROR_TRANSACTION"
            com.unionpay.mobile.android.utils.k.a(r0, r2)     // Catch:{ JSONException -> 0x00fb }
            r1 = 3
            goto L_0x0114
        L_0x00f9:
            r1 = 0
            goto L_0x0114
        L_0x00fb:
            r6 = move-exception
            goto L_0x0101
        L_0x00fd:
            r7 = move-exception
            r4 = r7
            r7 = r6
            r6 = r4
        L_0x0101:
            r6.printStackTrace()
            java.lang.String r6 = "uppay"
            java.lang.String r0 = " ERROR_MSG_FORMAT"
            com.unionpay.mobile.android.utils.k.a(r6, r0)
            goto L_0x0114
        L_0x010c:
            java.lang.String r7 = "uppay"
            java.lang.String r0 = " ERROR_MSG_FORMAT"
            com.unionpay.mobile.android.utils.k.a(r7, r0)
            r7 = r6
        L_0x0114:
            if (r1 == 0) goto L_0x0122
            java.lang.String r6 = r5.h
            boolean r6 = r5.a((java.lang.String) r6, (org.json.JSONObject) r7)
            if (r6 != 0) goto L_0x0125
            r5.b((int) r1)
            goto L_0x0125
        L_0x0122:
            r5.a(r7)
        L_0x0125:
            java.lang.String r6 = "uppay"
            java.lang.String r7 = "parserResponseMesage() ---"
            com.unionpay.mobile.android.utils.k.a(r6, r7)
            return
        L_0x012d:
            r5.b((int) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.b.a(int, java.lang.String):void");
    }

    public final void a(u uVar, String str) {
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        a(str, false);
    }

    /* access modifiers changed from: protected */
    public final void a(String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.b.a(onClickListener, onClickListener2);
        if (this.w != null && !this.w.isFinishing() && com.unionpay.mobile.android.languages.c.bD != null) {
            this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, str, com.unionpay.mobile.android.languages.c.bD.W, com.unionpay.mobile.android.languages.c.bD.X, false);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(String str, String str2) {
        a(str, str2, false, false);
    }

    /* access modifiers changed from: protected */
    public final void a(String str, String str2, boolean z, boolean z2) {
        ((InputMethodManager) this.d.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        this.f9608a.ag = str2;
        this.f9608a.af = str;
        a(14, z, z2);
    }

    /* access modifiers changed from: protected */
    public void a(String str, boolean z) {
        d dVar = new d(this, z);
        k.a("uppay", " showErrDialog(msg, boolean)  ");
        this.b.a(dVar, (View.OnClickListener) null);
        if (this.w != null && !this.w.isFinishing() && com.unionpay.mobile.android.languages.c.bD != null) {
            this.b.a(com.unionpay.mobile.android.languages.c.bD.Y, str, com.unionpay.mobile.android.languages.c.bD.W);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, JSONObject jSONObject) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    public void b(int i2) {
        String c2;
        boolean z;
        if (i2 == 8 || i2 == 17 || i2 == 19) {
            this.f9608a.I.f = "fail";
            k.a("uppay", "showErrDialog 1");
            c2 = c(i2);
            z = true;
        } else {
            k.a("uppay", "showErrDialog 2");
            c2 = c(i2);
            z = false;
        }
        a(c2, z);
    }

    /* access modifiers changed from: protected */
    public final void b(String str, String str2) {
        a(str, str2, true, true);
    }

    /* access modifiers changed from: protected */
    public void b(String str, JSONObject jSONObject) {
    }

    /* access modifiers changed from: protected */
    public final boolean b(JSONObject jSONObject) {
        if (!f.c(this.f9608a, jSONObject)) {
            return false;
        }
        c(jSONObject);
        return true;
    }

    /* access modifiers changed from: protected */
    public final String c(int i2) {
        switch (i2) {
            case 2:
                return com.unionpay.mobile.android.languages.c.bD.aB;
            case 3:
                break;
            case 4:
                return com.unionpay.mobile.android.languages.c.bD.az;
            case 5:
                return com.unionpay.mobile.android.languages.c.bD.aH;
            case 6:
                return com.unionpay.mobile.android.languages.c.bD.aI;
            case 7:
                return com.unionpay.mobile.android.languages.c.bD.aG;
            case 8:
                return com.unionpay.mobile.android.languages.c.bD.aJ;
            case 9:
                return com.unionpay.mobile.android.languages.c.bD.aK;
            default:
                switch (i2) {
                    case 16:
                        return com.unionpay.mobile.android.languages.c.bD.aM;
                    case 17:
                        break;
                    case 18:
                        return com.unionpay.mobile.android.languages.c.bD.aP;
                    case 19:
                        return com.unionpay.mobile.android.languages.c.bD.aN;
                    case 20:
                        return com.unionpay.mobile.android.languages.c.bD.aO;
                    case 21:
                        return com.unionpay.mobile.android.languages.c.bD.aL;
                    default:
                        return com.unionpay.mobile.android.languages.c.bD.aA;
                }
        }
        return this.h;
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    /* access modifiers changed from: protected */
    public final void c(JSONObject jSONObject) {
        this.b.a(new e(this, jSONObject), new f(this, jSONObject));
        if (this.w != null && !this.w.isFinishing() && this.f9608a != null) {
            this.b.a(this.f9608a.aG, this.f9608a.aH, this.f9608a.aI, this.f9608a.aK);
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        this.m = r();
    }

    /* access modifiers changed from: protected */
    public final void d(int i2) {
        a(i2, false, false);
    }

    /* access modifiers changed from: protected */
    public final void e() {
        this.k = a();
        b();
        RelativeLayout r2 = r();
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-1114114);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10, -1);
        r2.addView(linearLayout, layoutParams);
        this.l = linearLayout;
        this.l.setBackgroundColor(0);
        f();
        int id = this.l.getId();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(12, -1);
        layoutParams2.addRule(3, id);
        RelativeLayout relativeLayout = new RelativeLayout(this.d);
        r2.addView(relativeLayout, layoutParams2);
        this.m = relativeLayout;
        c();
    }

    public final void e(int i2) {
        if (i2 >= this.v) {
            if (this.t.getVisibility() != 0 && this.t != null && this.r.getVisibility() == 0) {
                this.t.setVisibility(0);
            }
        } else if (i2 <= this.v + this.u && this.t.getVisibility() == 0 && this.t != null) {
            this.t.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void f() {
        this.s = new LinearLayout(this.d);
        boolean z = true;
        this.s.setOrientation(1);
        if (!this.f9608a.aM || com.unionpay.mobile.android.model.b.bm) {
            this.s.setBackgroundColor(-267336);
        } else {
            this.s.setBackgroundColor(-34177);
        }
        int a2 = g.a(this.d, 10.0f);
        if (b(this.f9608a.ar)) {
            this.s.setPadding(a2, a2, a2, 0);
        } else {
            this.s.setPadding(a2, a2, a2, a2);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = 0;
        this.l.addView(this.s, layoutParams);
        String str = "";
        if (b(this.f9608a.at)) {
            str = str + this.f9608a.at;
        }
        if (b(str)) {
            TextView textView = new TextView(this.d);
            if (!this.f9608a.aM || com.unionpay.mobile.android.model.b.bm) {
                textView.setTextColor(-10066330);
            } else {
                textView.setTextColor(-1);
            }
            textView.setText(str);
            textView.setTextSize(com.unionpay.mobile.android.global.b.k);
            this.s.addView(textView);
        } else {
            this.s.setVisibility(8);
        }
        this.r = new LinearLayout(this.d);
        this.r.setOrientation(1);
        if (!this.f9608a.aM || com.unionpay.mobile.android.model.b.bm) {
            this.r.setBackgroundColor(-267336);
        } else {
            this.r.setBackgroundColor(-34177);
        }
        this.r.setPadding(a2, a2, a2, a2);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = 0;
        this.l.addView(this.r, layoutParams2);
        String str2 = "";
        if (b(this.f9608a.ar)) {
            str2 = str2 + this.f9608a.ar;
        }
        if (b(str2)) {
            TextView textView2 = new TextView(this.d);
            if (!this.f9608a.aM || com.unionpay.mobile.android.model.b.bm) {
                textView2.setTextColor(-10066330);
            } else {
                textView2.setTextColor(-1);
            }
            textView2.setText(str2);
            textView2.setTextSize(com.unionpay.mobile.android.global.b.k);
            this.r.addView(textView2);
        } else {
            this.r.setVisibility(8);
        }
        this.r.getViewTreeObserver().addOnPreDrawListener(new c(this));
        com.unionpay.mobile.android.views.order.m mVar = new com.unionpay.mobile.android.views.order.m(this.d);
        mVar.a(this.c.a(1003, -1, -1), this.c.a(1001, -1, -1));
        if (this instanceof ao) {
            z = false;
        }
        mVar.a(z, this.f9608a.h, this.f9608a.i);
        this.l.addView(mVar, new LinearLayout.LayoutParams(-1, -2));
        Drawable a3 = this.c.a(1026, -1, -1);
        LinearLayout linearLayout = new LinearLayout(this.d);
        if (a3 != null) {
            linearLayout.setBackgroundDrawable(a3);
        }
        this.l.addView(linearLayout, new LinearLayout.LayoutParams(-1, g.a(this.d, 2.0f)));
    }

    public final int g() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public final boolean h() {
        return this.f9608a.J || this.f9608a.q == null || this.f9608a.q.size() == 0;
    }

    /* access modifiers changed from: protected */
    public final void i() {
        if (this.b != null && this.b.a()) {
            this.b.c();
        }
    }

    public final void j() {
        d.a(this.d, this.f9608a);
    }

    public void k() {
        if (this.j) {
            m();
        }
    }

    public final void l() {
        k();
    }

    public final void m() {
        ((BaseActivity) this.d).b();
    }

    /* access modifiers changed from: protected */
    public final boolean n() {
        boolean z = this.b != null && this.b.a();
        k.a("uppay", " dialog showing:" + z);
        return z;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        k.b("uppayEx", toString() + " onAttachedToWindow()");
        super.onAttachedToWindow();
        this.e.a((UPPayEngine.a) this);
    }

    /* access modifiers changed from: protected */
    public final boolean p() {
        return !this.f9608a.c;
    }

    public final void q() {
        if (this.d != null) {
            ((InputMethodManager) this.d.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }
}
