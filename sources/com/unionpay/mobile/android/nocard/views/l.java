package com.unionpay.mobile.android.nocard.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.tsmclient.model.ErrorCode;
import com.unionpay.mobile.android.model.c;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.utils.a;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.utils.q;
import com.unionpay.mobile.android.widgets.m;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class l extends b implements q.a {
    private String A = null;
    private int B = 0;
    private volatile int C = 0;
    private boolean D = false;
    private boolean E = false;
    private JSONArray F;
    private e G;
    private long H;
    private Activity I;
    private boolean J;
    private boolean K = false;
    public List<c> r = null;
    public List<c> s = null;
    int t = 0;
    String u = "";
    String v = "";
    String w = "";
    int x = 500;
    int y = 5;
    private ProgressBar z = null;

    public l(Context context) {
        super(context);
        this.f = 1;
        this.q = "init";
        d();
        this.I = (Activity) context;
        try {
            this.J = a.a(this.I.getIntent(), this.f9608a);
        } catch (Exception unused) {
            this.J = false;
        }
        if (this.f9608a.aM) {
            setVisibility(8);
            this.b.a(com.unionpay.mobile.android.languages.c.bD.c);
        }
        r();
    }

    private void a(String str, String str2, String str3) {
        m mVar;
        String str4;
        m mVar2 = new m(this, str3);
        n nVar = new n(this);
        if (!str.equalsIgnoreCase("01")) {
            this.b.a(mVar2, nVar);
            mVar = this.b;
            str4 = com.unionpay.mobile.android.languages.c.bD.ae;
        } else {
            this.b.a(mVar2, nVar);
            mVar = this.b;
            str4 = com.unionpay.mobile.android.languages.c.bD.Y;
        }
        mVar.a(str4, str2, com.unionpay.mobile.android.languages.c.bD.af, com.unionpay.mobile.android.languages.c.bD.ag);
    }

    private void b(int i, String str) {
        if (str != null && str.length() > 0) {
            this.f9608a.I.f = str;
        }
        this.z.setVisibility(4);
        a(c(i), true);
    }

    private void c(String str, String str2) {
        if (str2.length() > 0) {
            this.f9608a.I.f = str2;
        }
        this.z.setVisibility(4);
        a(str, true);
    }

    private boolean f(int i) {
        if ((i & 2) == 0) {
            return false;
        }
        this.f9608a.aw = true;
        return true;
    }

    private final boolean y() {
        try {
            String string = this.F != null ? this.F.getString(3) : null;
            return string != null && string.length() > 0 && !"null".equalsIgnoreCase(string);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0053, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0126 A[Catch:{ JSONException -> 0x01f4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void z() {
        /*
            r11 = this;
            monitor-enter(r11)
            java.lang.String r0 = "uppay"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0289 }
            java.lang.String r2 = "showContentView() +++"
            r1.<init>(r2)     // Catch:{ all -> 0x0289 }
            int r2 = r11.C     // Catch:{ all -> 0x0289 }
            r1.append(r2)     // Catch:{ all -> 0x0289 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0289 }
            com.unionpay.mobile.android.utils.k.c(r0, r1)     // Catch:{ all -> 0x0289 }
            int r0 = r11.C     // Catch:{ all -> 0x0289 }
            r1 = 0
            r2 = 2
            r3 = 1
            if (r0 == r2) goto L_0x0054
            boolean r0 = r11.E     // Catch:{ all -> 0x0289 }
            r0 = r0 ^ r3
            boolean r4 = r11.E     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x004d
            java.lang.String r4 = "1"
            com.unionpay.mobile.android.model.b r5 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.lang.String r5 = r5.an     // Catch:{ all -> 0x0289 }
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0289 }
            if (r4 != 0) goto L_0x004d
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            int r4 = r4.ao     // Catch:{ all -> 0x0289 }
            boolean r5 = r11.f(r4)     // Catch:{ all -> 0x0289 }
            if (r5 == 0) goto L_0x0042
            r5 = 69889(0x11101, float:9.7935E-41)
            r4 = r4 & r5
            if (r4 != 0) goto L_0x0042
            r4 = 1
            goto L_0x0043
        L_0x0042:
            r4 = 0
        L_0x0043:
            if (r4 != 0) goto L_0x004d
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r4 = r4.aD     // Catch:{ all -> 0x0289 }
            if (r4 != 0) goto L_0x004d
            r4 = 1
            goto L_0x004e
        L_0x004d:
            r4 = 0
        L_0x004e:
            if (r0 != 0) goto L_0x0052
            if (r4 == 0) goto L_0x0054
        L_0x0052:
            monitor-exit(r11)
            return
        L_0x0054:
            boolean r0 = r11.D     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x005a
            monitor-exit(r11)
            return
        L_0x005a:
            r11.D = r3     // Catch:{ all -> 0x0289 }
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.aM     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x0067
            com.unionpay.mobile.android.widgets.m r0 = r11.b     // Catch:{ all -> 0x0289 }
            r0.c()     // Catch:{ all -> 0x0289 }
        L_0x0067:
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            int r0 = r0.ao     // Catch:{ all -> 0x0289 }
            java.lang.String r4 = "1"
            com.unionpay.mobile.android.model.b r5 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.lang.String r5 = r5.an     // Catch:{ all -> 0x0289 }
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x008c
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r4 = r4.aC     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x0084
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            r5 = 0
            r4.q = r5     // Catch:{ all -> 0x0289 }
            goto L_0x013d
        L_0x0084:
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r5 = r11.r     // Catch:{ all -> 0x0289 }
            r4.q = r5     // Catch:{ all -> 0x0289 }
            goto L_0x013d
        L_0x008c:
            boolean r4 = com.unionpay.mobile.android.model.b.aA     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x00c0
            boolean r4 = com.unionpay.mobile.android.model.b.aB     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x00c0
            boolean r4 = com.unionpay.mobile.android.model.b.bn     // Catch:{ all -> 0x0289 }
            if (r4 != 0) goto L_0x00c0
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r11.s     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x00c0
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r11.s     // Catch:{ all -> 0x0289 }
            int r4 = r4.size()     // Catch:{ all -> 0x0289 }
            if (r4 <= 0) goto L_0x00c0
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r11.s     // Catch:{ all -> 0x0289 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0289 }
        L_0x00aa:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0289 }
            if (r5 == 0) goto L_0x00c0
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0289 }
            com.unionpay.mobile.android.model.c r5 = (com.unionpay.mobile.android.model.c) r5     // Catch:{ all -> 0x0289 }
            int r5 = r5.c()     // Catch:{ all -> 0x0289 }
            if (r5 != r3) goto L_0x00aa
            r4.remove()     // Catch:{ all -> 0x0289 }
            goto L_0x00aa
        L_0x00c0:
            java.lang.String r4 = "0"
            com.unionpay.mobile.android.model.b r5 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.lang.String r5 = r5.an     // Catch:{ all -> 0x0289 }
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x00d3
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r5 = r11.s     // Catch:{ all -> 0x0289 }
        L_0x00d0:
            r4.q = r5     // Catch:{ all -> 0x0289 }
            goto L_0x0108
        L_0x00d3:
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r4 = r4.aC     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x00de
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r5 = r11.s     // Catch:{ all -> 0x0289 }
            goto L_0x00d0
        L_0x00de:
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r11.s     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x00f3
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r11.s     // Catch:{ all -> 0x0289 }
            int r4 = r4.size()     // Catch:{ all -> 0x0289 }
            if (r4 <= 0) goto L_0x00f3
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.q     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r5 = r11.s     // Catch:{ all -> 0x0289 }
            r4.addAll(r5)     // Catch:{ all -> 0x0289 }
        L_0x00f3:
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r11.r     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x0108
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r11.r     // Catch:{ all -> 0x0289 }
            int r4 = r4.size()     // Catch:{ all -> 0x0289 }
            if (r4 <= 0) goto L_0x0108
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.q     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r5 = r11.r     // Catch:{ all -> 0x0289 }
            r4.addAll(r5)     // Catch:{ all -> 0x0289 }
        L_0x0108:
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.q     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x013d
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.q     // Catch:{ all -> 0x0289 }
            int r4 = r4.size()     // Catch:{ all -> 0x0289 }
            if (r4 <= 0) goto L_0x013d
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.q     // Catch:{ all -> 0x0289 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0289 }
        L_0x0120:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0289 }
            if (r5 == 0) goto L_0x013d
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0289 }
            com.unionpay.mobile.android.model.c r5 = (com.unionpay.mobile.android.model.c) r5     // Catch:{ all -> 0x0289 }
            int r6 = r5.c()     // Catch:{ all -> 0x0289 }
            if (r6 == 0) goto L_0x0120
            int r5 = r5.c()     // Catch:{ all -> 0x0289 }
            r5 = r5 & r0
            if (r5 != 0) goto L_0x0120
            r4.remove()     // Catch:{ all -> 0x0289 }
            goto L_0x0120
        L_0x013d:
            java.lang.String r4 = "0"
            com.unionpay.mobile.android.model.b r5 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.lang.String r5 = r5.an     // Catch:{ all -> 0x0289 }
            boolean r4 = r4.equalsIgnoreCase(r5)     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x016a
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.q     // Catch:{ all -> 0x0289 }
            if (r4 == 0) goto L_0x0159
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.util.List<com.unionpay.mobile.android.model.c> r4 = r4.q     // Catch:{ all -> 0x0289 }
            int r4 = r4.size()     // Catch:{ all -> 0x0289 }
            if (r4 > 0) goto L_0x016a
        L_0x0159:
            boolean r0 = r11.f(r0)     // Catch:{ all -> 0x0289 }
            if (r0 != 0) goto L_0x016a
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.lang.String r0 = r0.ap     // Catch:{ all -> 0x0289 }
            java.lang.String r1 = "fail"
            r11.c(r0, r1)     // Catch:{ all -> 0x0289 }
            monitor-exit(r11)
            return
        L_0x016a:
            java.lang.String r0 = "1"
            com.unionpay.mobile.android.model.b r4 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.lang.String r4 = r4.an     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.equalsIgnoreCase(r4)     // Catch:{ all -> 0x0289 }
            if (r0 != 0) goto L_0x01f8
            org.json.JSONArray r0 = r11.F     // Catch:{ JSONException -> 0x01f4 }
            if (r0 == 0) goto L_0x0181
            org.json.JSONArray r0 = r11.F     // Catch:{ JSONException -> 0x01f4 }
            java.lang.String r0 = r0.getString(r1)     // Catch:{ JSONException -> 0x01f4 }
            goto L_0x0185
        L_0x0181:
            com.unionpay.mobile.android.languages.c r0 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x01f4 }
            java.lang.String r0 = r0.ak     // Catch:{ JSONException -> 0x01f4 }
        L_0x0185:
            org.json.JSONArray r1 = r11.F     // Catch:{ JSONException -> 0x01f4 }
            if (r1 == 0) goto L_0x0190
            org.json.JSONArray r1 = r11.F     // Catch:{ JSONException -> 0x01f4 }
            java.lang.String r1 = r1.getString(r3)     // Catch:{ JSONException -> 0x01f4 }
            goto L_0x0194
        L_0x0190:
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x01f4 }
            java.lang.String r1 = r1.aj     // Catch:{ JSONException -> 0x01f4 }
        L_0x0194:
            org.json.JSONArray r4 = r11.F     // Catch:{ JSONException -> 0x01f4 }
            if (r4 == 0) goto L_0x019f
            org.json.JSONArray r4 = r11.F     // Catch:{ JSONException -> 0x01f4 }
            java.lang.String r4 = r4.getString(r2)     // Catch:{ JSONException -> 0x01f4 }
            goto L_0x01a3
        L_0x019f:
            com.unionpay.mobile.android.languages.c r4 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x01f4 }
            java.lang.String r4 = r4.ai     // Catch:{ JSONException -> 0x01f4 }
        L_0x01a3:
            boolean r5 = r11.y()     // Catch:{ JSONException -> 0x01f4 }
            if (r5 == 0) goto L_0x01b1
            org.json.JSONArray r5 = r11.F     // Catch:{ JSONException -> 0x01f4 }
            r6 = 3
            java.lang.String r5 = r5.getString(r6)     // Catch:{ JSONException -> 0x01f4 }
            goto L_0x01b5
        L_0x01b1:
            com.unionpay.mobile.android.languages.c r5 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ JSONException -> 0x01f4 }
            java.lang.String r5 = r5.al     // Catch:{ JSONException -> 0x01f4 }
        L_0x01b5:
            com.unionpay.mobile.android.model.b r6 = r11.f9608a     // Catch:{ JSONException -> 0x01f4 }
            java.util.List<com.unionpay.mobile.android.model.c> r6 = r6.q     // Catch:{ JSONException -> 0x01f4 }
            if (r6 == 0) goto L_0x01f8
            com.unionpay.mobile.android.model.b r6 = r11.f9608a     // Catch:{ JSONException -> 0x01f4 }
            java.util.List<com.unionpay.mobile.android.model.c> r6 = r6.q     // Catch:{ JSONException -> 0x01f4 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ JSONException -> 0x01f4 }
        L_0x01c3:
            boolean r7 = r6.hasNext()     // Catch:{ JSONException -> 0x01f4 }
            if (r7 == 0) goto L_0x01f8
            java.lang.Object r7 = r6.next()     // Catch:{ JSONException -> 0x01f4 }
            com.unionpay.mobile.android.model.c r7 = (com.unionpay.mobile.android.model.c) r7     // Catch:{ JSONException -> 0x01f4 }
            int r8 = r7.c()     // Catch:{ JSONException -> 0x01f4 }
            if (r8 == 0) goto L_0x01c3
            java.lang.String r8 = ""
            int r9 = r7.c()     // Catch:{ JSONException -> 0x01f4 }
            if (r9 == r3) goto L_0x01ef
            r10 = 4
            if (r9 == r10) goto L_0x01ed
            r10 = 8
            if (r9 == r10) goto L_0x01eb
            r10 = 16
            if (r9 == r10) goto L_0x01e9
            goto L_0x01f0
        L_0x01e9:
            r8 = r4
            goto L_0x01f0
        L_0x01eb:
            r8 = r0
            goto L_0x01f0
        L_0x01ed:
            r8 = r1
            goto L_0x01f0
        L_0x01ef:
            r8 = r5
        L_0x01f0:
            r7.a(r8)     // Catch:{ JSONException -> 0x01f4 }
            goto L_0x01c3
        L_0x01f4:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0289 }
        L_0x01f8:
            boolean r0 = com.unionpay.mobile.android.model.b.bn     // Catch:{ all -> 0x0289 }
            if (r0 != 0) goto L_0x0204
            boolean r0 = com.unionpay.mobile.android.model.b.aA     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x0204
            boolean r0 = com.unionpay.mobile.android.model.b.aB     // Catch:{ all -> 0x0289 }
            if (r0 != 0) goto L_0x0213
        L_0x0204:
            boolean r0 = com.unionpay.mobile.android.model.b.bm     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x0213
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.lang.String r0 = r0.ap     // Catch:{ all -> 0x0289 }
            java.lang.String r1 = "fail"
            r11.c(r0, r1)     // Catch:{ all -> 0x0289 }
            monitor-exit(r11)
            return
        L_0x0213:
            boolean r0 = r11.s()     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x023b
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.az     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x023b
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.aC     // Catch:{ all -> 0x0289 }
            if (r0 != 0) goto L_0x023b
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.aD     // Catch:{ all -> 0x0289 }
            if (r0 != 0) goto L_0x023b
            int r4 = r11.t     // Catch:{ all -> 0x0289 }
            int r5 = r11.x     // Catch:{ all -> 0x0289 }
            java.lang.String r6 = r11.u     // Catch:{ all -> 0x0289 }
            java.lang.String r7 = r11.v     // Catch:{ all -> 0x0289 }
            int r8 = r11.y     // Catch:{ all -> 0x0289 }
            java.lang.String r9 = r11.w     // Catch:{ all -> 0x0289 }
            r3 = r11
            r3.a(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0289 }
        L_0x023b:
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.ax     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x0253
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.aD     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x0253
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.ay     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x0253
            r0 = 17
            r11.d(r0)     // Catch:{ all -> 0x0289 }
            goto L_0x0280
        L_0x0253:
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.aE     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x027b
            com.unionpay.mobile.android.model.b r0 = r11.f9608a     // Catch:{ all -> 0x0289 }
            boolean r0 = r0.az     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x027b
            boolean r0 = com.unionpay.mobile.android.model.b.bl     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x027b
            java.util.List<com.unionpay.mobile.android.model.d> r0 = com.unionpay.mobile.android.model.b.bb     // Catch:{ all -> 0x0289 }
            if (r0 == 0) goto L_0x026f
            java.util.List<com.unionpay.mobile.android.model.d> r0 = com.unionpay.mobile.android.model.b.bb     // Catch:{ all -> 0x0289 }
            int r0 = r0.size()     // Catch:{ all -> 0x0289 }
            if (r0 > 0) goto L_0x027b
        L_0x026f:
            com.unionpay.mobile.android.languages.c r0 = com.unionpay.mobile.android.languages.c.bD     // Catch:{ all -> 0x0289 }
            java.lang.String r0 = r0.bq     // Catch:{ all -> 0x0289 }
            com.unionpay.mobile.android.model.b r1 = r11.f9608a     // Catch:{ all -> 0x0289 }
            java.lang.String r1 = r1.bh     // Catch:{ all -> 0x0289 }
            r11.b((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x0289 }
            goto L_0x0280
        L_0x027b:
            com.unionpay.mobile.android.model.e r0 = r11.G     // Catch:{ all -> 0x0289 }
            r11.a((int) r2, (com.unionpay.mobile.android.model.e) r0)     // Catch:{ all -> 0x0289 }
        L_0x0280:
            java.lang.String r0 = "uppay"
            java.lang.String r1 = "showContentView() ---"
            com.unionpay.mobile.android.utils.k.c(r0, r1)     // Catch:{ all -> 0x0289 }
            monitor-exit(r11)
            return
        L_0x0289:
            r0 = move-exception
            monitor-exit(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.l.z():void");
    }

    public void a(int i, int i2, String str, String str2, int i3, String str3) {
    }

    public final void a(int i, byte[] bArr) {
        i();
        if (i != 0) {
            b(i, (String) null);
        }
        k.a("uppay", "status = " + i);
        if (bArr == null) {
            return;
        }
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            b(9, (String) null);
        } else if (p.a(bArr)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            String str = Environment.getExternalStorageDirectory() + File.separator + "UPPay" + File.separator + "UPPayPluginEx.apk";
            k.a("uppay", "apk path:" + str);
            intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
            ((BaseActivity) this.d).startActivityForResult(intent, 100);
        } else {
            b(21, (String) null);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        u();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(org.json.JSONObject r12) {
        /*
            r11 = this;
            java.lang.String r0 = "uppay"
            java.lang.String r1 = "init.parserParamJsonObj() +++"
            com.unionpay.mobile.android.utils.k.a(r0, r1)
            r0 = 2
            if (r12 != 0) goto L_0x000e
            r11.b(r0)
            return
        L_0x000e:
            int r1 = r11.B
            r2 = 1
            r3 = 0
            switch(r1) {
                case 1: goto L_0x0046;
                case 2: goto L_0x0017;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x0496
        L_0x0017:
            com.unionpay.mobile.android.model.b r0 = r11.f9608a
            com.unionpay.mobile.android.nocard.utils.f.c(r0, r12)
            com.unionpay.mobile.android.model.b r0 = r11.f9608a
            int r0 = com.unionpay.mobile.android.nocard.utils.f.b(r0, r12)
            if (r0 == 0) goto L_0x0029
            r11.b(r0)
            goto L_0x0496
        L_0x0029:
            java.lang.String r0 = "userId"
            java.lang.String r0 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x003b
            java.lang.String[] r1 = com.unionpay.mobile.android.utils.o.e
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r1[r3] = r0
        L_0x003b:
            com.unionpay.mobile.android.model.e r12 = com.unionpay.mobile.android.nocard.utils.f.a((org.json.JSONObject) r12)
            r11.G = r12
        L_0x0041:
            r11.u()
            goto L_0x0496
        L_0x0046:
            java.lang.String r1 = "secret"
            java.lang.String r1 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r1)
            java.lang.String r4 = "sec_sign"
            com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r4)
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r4 = r11.e
            r4.d(r1)
            java.lang.String r1 = "talking_data_flag"
            java.lang.String r1 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r1)
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x006a
            java.lang.String r4 = "0"
            boolean r1 = r4.equals(r1)
            com.unionpay.mobile.android.global.a.L = r1
        L_0x006a:
            java.lang.String r1 = "mer_id"
            java.lang.String r1 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r1)
            android.text.TextUtils.isEmpty(r1)
            java.lang.String[] r4 = com.unionpay.mobile.android.utils.o.f9745a
            java.lang.String[] r4 = new java.lang.String[r2]
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r5 = r5.b
            r4[r3] = r5
            java.lang.String[] r4 = com.unionpay.mobile.android.utils.o.b
            java.lang.String[] r4 = new java.lang.String[r2]
            r4[r3] = r1
            java.lang.String[] r1 = com.unionpay.mobile.android.utils.o.c
            java.lang.String[] r1 = new java.lang.String[r2]
            com.unionpay.mobile.android.languages.c r4 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r4 = r4.f9574a
            r1[r3] = r4
            java.lang.String r1 = "upgrade_info"
            org.json.JSONObject r1 = com.unionpay.mobile.android.utils.j.c(r12, r1)
            java.lang.String r4 = "type"
            java.lang.String r4 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r1, (java.lang.String) r4)
            r11.A = r4
            java.lang.String r4 = "desc"
            java.lang.String r4 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r1, (java.lang.String) r4)
            java.lang.String r5 = "url"
            java.lang.String r1 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r1, (java.lang.String) r5)
            java.lang.String r5 = r11.A
            java.lang.String r6 = "02"
            boolean r5 = r5.equalsIgnoreCase(r6)
            if (r5 == 0) goto L_0x00b8
        L_0x00b1:
            java.lang.String r12 = r11.A
            r11.a(r12, r4, r1)
            goto L_0x0496
        L_0x00b8:
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "title"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.o = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "init_button"
            org.json.JSONObject r6 = com.unionpay.mobile.android.utils.j.c(r12, r6)
            r5.j = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "order"
            org.json.JSONArray r6 = com.unionpay.mobile.android.utils.j.d(r12, r6)
            r5.h = r6
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            r6 = 0
        L_0x00dc:
            com.unionpay.mobile.android.model.b r7 = r11.f9608a
            org.json.JSONArray r7 = r7.h
            int r7 = r7.length()
            if (r6 >= r7) goto L_0x0104
            com.unionpay.mobile.android.model.b r7 = r11.f9608a
            org.json.JSONArray r7 = r7.h
            java.lang.Object r7 = com.unionpay.mobile.android.utils.j.b((org.json.JSONArray) r7, (int) r6)
            if (r7 == 0) goto L_0x0101
            org.json.JSONObject r7 = (org.json.JSONObject) r7
            java.lang.String r8 = "label"
            java.lang.String r8 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r7, (java.lang.String) r8)
            java.lang.String r9 = "value"
            java.lang.String r7 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r7, (java.lang.String) r9)
            r5.put(r8, r7)
        L_0x0101:
            int r6 = r6 + 1
            goto L_0x00dc
        L_0x0104:
            java.lang.String[] r6 = com.unionpay.mobile.android.utils.o.d
            java.lang.String[] r6 = new java.lang.String[r2]
            java.lang.String r5 = r5.toString()
            r6[r3] = r5
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "risk_info"
            org.json.JSONObject r6 = com.unionpay.mobile.android.utils.j.c(r12, r6)
            r5.i = r6
            java.lang.String r5 = "cards"
            java.util.List r5 = com.unionpay.mobile.android.utils.j.e(r12, r5)
            int r6 = r5.size()
            if (r6 <= 0) goto L_0x012f
            java.util.ArrayList r6 = new java.util.ArrayList
            int r7 = r5.size()
            r6.<init>(r7)
            r11.r = r6
        L_0x012f:
            r6 = 0
        L_0x0130:
            int r7 = r5.size()
            if (r6 >= r7) goto L_0x0161
            java.lang.Object r7 = r5.get(r6)
            org.json.JSONArray r7 = (org.json.JSONArray) r7
            java.lang.String r7 = com.unionpay.mobile.android.utils.j.a((org.json.JSONArray) r7, (int) r3)
            java.lang.Object r8 = r5.get(r6)
            org.json.JSONArray r8 = (org.json.JSONArray) r8
            java.lang.String r8 = com.unionpay.mobile.android.utils.j.a((org.json.JSONArray) r8, (int) r2)
            java.lang.Object r9 = r5.get(r6)
            org.json.JSONArray r9 = (org.json.JSONArray) r9
            java.lang.String r9 = com.unionpay.mobile.android.utils.j.a((org.json.JSONArray) r9, (int) r0)
            com.unionpay.mobile.android.model.a r10 = new com.unionpay.mobile.android.model.a
            r10.<init>(r7, r8, r9, r3)
            java.util.List<com.unionpay.mobile.android.model.c> r7 = r11.r
            r7.add(r10)
            int r6 = r6 + 1
            goto L_0x0130
        L_0x0161:
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "bank_url"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.s = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "input_info"
            org.json.JSONArray r6 = com.unionpay.mobile.android.utils.j.d(r12, r6)
            r5.t = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "account_info"
            org.json.JSONObject r6 = com.unionpay.mobile.android.utils.j.c(r12, r6)
            r5.v = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "other_card_info"
            org.json.JSONObject r6 = com.unionpay.mobile.android.utils.j.c(r12, r6)
            r5.w = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "user_id"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.u = r6
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r5 = r11.e
            java.lang.String r6 = "sid"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.b(r6)
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r5 = r11.e
            java.lang.String r6 = "secret"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.c(r6)
            java.lang.String r5 = "sid"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r5)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x01c3
            com.unionpay.mobile.android.model.b r6 = r11.f9608a
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r7 = r11.e
            java.lang.String r5 = com.unionpay.mobile.android.utils.c.b((java.lang.String) r5)
            java.lang.String r5 = r7.h(r5)
            r6.k = r5
        L_0x01c3:
            java.lang.String r5 = "secret"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r5)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x01e1
            com.unionpay.mobile.android.model.b r6 = r11.f9608a
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r7 = r11.e
            java.lang.String r8 = com.unionpay.mobile.android.utils.c.b((java.lang.String) r5)
            java.lang.String r7 = r7.h(r8)
            r6.l = r7
            com.unionpay.mobile.android.model.b r6 = r11.f9608a
            r6.m = r5
        L_0x01e1:
            java.lang.String r5 = "uid"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r5)
            if (r5 == 0) goto L_0x01f4
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x01f4
            android.content.Context r6 = r11.d
            com.unionpay.mobile.android.utils.PreferenceUtils.b((android.content.Context) r6, (java.lang.String) r5)
        L_0x01f4:
            boolean r5 = r11.t()
            if (r5 == 0) goto L_0x0279
            boolean r5 = r11.s()
            if (r5 == 0) goto L_0x0230
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "kalefu_info"
            org.json.JSONObject r6 = com.unionpay.mobile.android.utils.j.c(r12, r6)
            r5.x = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "kalefu_button_label"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.y = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r5 = r5.y
            if (r5 == 0) goto L_0x0224
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r5 = r5.y
            int r5 = r5.length()
            if (r5 > 0) goto L_0x0230
        L_0x0224:
            com.unionpay.mobile.android.languages.c r5 = com.unionpay.mobile.android.languages.c.bD
            if (r5 == 0) goto L_0x0230
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            com.unionpay.mobile.android.languages.c r6 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r6 = r6.am
            r5.y = r6
        L_0x0230:
            java.lang.String r5 = "cards_desc"
            org.json.JSONArray r5 = com.unionpay.mobile.android.utils.j.d(r12, r5)
            r11.F = r5
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "trade_privilege"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.an = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "upcard_msg"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.ap = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            r5.ao = r3
            java.lang.String r5 = "upcard_support_type"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r5)
            java.lang.String r6 = "1"
            com.unionpay.mobile.android.model.b r7 = r11.f9608a
            java.lang.String r7 = r7.an
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 != 0) goto L_0x0272
            if (r5 == 0) goto L_0x0272
            int r6 = r5.length()
            if (r6 <= 0) goto L_0x0272
            com.unionpay.mobile.android.model.b r6 = r11.f9608a
            int r5 = java.lang.Integer.parseInt(r5, r0)
            r6.ao = r5
        L_0x0272:
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            int r5 = r5.ao
            r11.f(r5)
        L_0x0279:
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "ad"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.ar = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "pay_tip"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.at = r6
            java.lang.String r5 = "sup_pay_method"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r5)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x02ad
            com.unionpay.mobile.android.model.b r6 = r11.f9608a
            java.lang.String r7 = "01"
            boolean r7 = r7.equals(r5)
            r6.aC = r7
            com.unionpay.mobile.android.model.b r6 = r11.f9608a
            java.lang.String r7 = "001"
            boolean r5 = r7.equals(r5)
            r6.aD = r5
        L_0x02ad:
            java.lang.String r5 = "default_pay_type"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r5)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x02c3
            com.unionpay.mobile.android.model.b r6 = r11.f9608a
            java.lang.String r7 = "0501"
            boolean r5 = r7.equals(r5)
            r6.aE = r5
        L_0x02c3:
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "find_pwd_url"
            org.json.JSONObject r6 = com.unionpay.mobile.android.utils.j.c(r12, r6)
            r5.au = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "reg_url"
            org.json.JSONObject r6 = com.unionpay.mobile.android.utils.j.c(r12, r6)
            r5.Y = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "1"
            java.lang.String r7 = "sup_nfc"
            java.lang.String r7 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r7)
            boolean r6 = r6.equals(r7)
            r5.ay = r6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "1"
            java.lang.String r7 = "sup_hce"
            java.lang.String r7 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r7)
            boolean r6 = r6.equals(r7)
            r5.az = r6
            java.lang.String r5 = "1"
            java.lang.String r6 = "sup_samsung_pay"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            boolean r5 = r5.equals(r6)
            com.unionpay.mobile.android.model.b.aA = r5
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "hce_introduction_url"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.bh = r6
            boolean r5 = r11.s()
            if (r5 == 0) goto L_0x034b
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            boolean r5 = r5.ay
            if (r5 == 0) goto L_0x034b
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            boolean r5 = r5.aC
            if (r5 != 0) goto L_0x034b
            java.lang.String r5 = "nfc_title"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r5)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x0331
            com.unionpay.mobile.android.languages.c r6 = com.unionpay.mobile.android.languages.c.bD
            r6.bo = r5
        L_0x0331:
            java.lang.String r5 = "nfc_button"
            org.json.JSONObject r5 = com.unionpay.mobile.android.utils.j.c(r12, r5)
            if (r5 == 0) goto L_0x034b
            java.lang.String r6 = "label"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r5, (java.lang.String) r6)
            if (r5 == 0) goto L_0x034b
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x034b
            com.unionpay.mobile.android.languages.c r6 = com.unionpay.mobile.android.languages.c.bD
            r6.bp = r5
        L_0x034b:
            boolean r5 = r11.s()
            if (r5 == 0) goto L_0x03e6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            boolean r5 = r5.az
            if (r5 == 0) goto L_0x03e6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            boolean r5 = r5.aC
            if (r5 != 0) goto L_0x03e6
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            boolean r5 = r5.aD
            if (r5 != 0) goto L_0x03e6
            java.lang.String r5 = "hce_title"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r5)
            java.lang.String r6 = "hce_page_size"
            int r6 = com.unionpay.mobile.android.utils.j.b((org.json.JSONObject) r12, (java.lang.String) r6)
            r11.t = r6
            java.lang.String r6 = "hce_button"
            org.json.JSONObject r6 = com.unionpay.mobile.android.utils.j.c(r12, r6)
            java.lang.String r7 = "label"
            java.lang.String r7 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r6, (java.lang.String) r7)
            java.lang.String r8 = "htmlLabel"
            java.lang.String r8 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r6, (java.lang.String) r8)
            boolean r9 = android.text.TextUtils.isEmpty(r5)
            if (r9 != 0) goto L_0x038e
            com.unionpay.mobile.android.languages.c r9 = com.unionpay.mobile.android.languages.c.bD
            r9.bq = r5
            goto L_0x0392
        L_0x038e:
            com.unionpay.mobile.android.languages.c r5 = com.unionpay.mobile.android.languages.c.bD
            r5.bq = r7
        L_0x0392:
            boolean r5 = android.text.TextUtils.isEmpty(r8)
            if (r5 != 0) goto L_0x039d
            com.unionpay.mobile.android.languages.c r5 = com.unionpay.mobile.android.languages.c.bD
            r5.br = r8
            goto L_0x03a1
        L_0x039d:
            com.unionpay.mobile.android.languages.c r5 = com.unionpay.mobile.android.languages.c.bD
            r5.br = r7
        L_0x03a1:
            java.lang.String r5 = "action"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r6, (java.lang.String) r5)
            r11.u = r5
            java.lang.String r5 = "reserved"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r6, (java.lang.String) r5)
            r11.v = r5
            java.lang.String r5 = "iss_ins_code"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r5)
            r11.w = r5
            java.lang.String r5 = "hce_bank_timeout"
            int r5 = com.unionpay.mobile.android.utils.j.b((org.json.JSONObject) r12, (java.lang.String) r5)
            r11.x = r5
            java.lang.String r5 = "hce_concurrent_count"
            int r5 = com.unionpay.mobile.android.utils.j.b((org.json.JSONObject) r12, (java.lang.String) r5)
            r11.y = r5
            java.lang.String r5 = "hce_pay_timeout"
            int r5 = com.unionpay.mobile.android.utils.j.b((org.json.JSONObject) r12, (java.lang.String) r5)
            if (r5 == 0) goto L_0x03d6
            com.unionpay.mobile.android.model.b r6 = r11.f9608a
            r6.be = r5
            goto L_0x03dc
        L_0x03d6:
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            r6 = 5000(0x1388, float:7.006E-42)
            r5.be = r6
        L_0x03dc:
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.lang.String r6 = "no_hce_card_msg"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            r5.bi = r6
        L_0x03e6:
            com.unionpay.mobile.android.model.b r5 = r11.f9608a
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            r5.p = r6
            java.lang.String r6 = "f55"
            org.json.JSONObject r12 = com.unionpay.mobile.android.utils.j.c(r12, r6)
            java.lang.String r6 = "order_amount"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            java.util.HashMap<java.lang.String, java.lang.String> r7 = r5.p
            java.lang.String r8 = "trans_amt"
            if (r6 == 0) goto L_0x0409
            int r9 = r6.length()
            if (r9 <= 0) goto L_0x0409
            r9 = r6
            goto L_0x040b
        L_0x0409:
            java.lang.String r9 = "000000000000"
        L_0x040b:
            r7.put(r8, r9)
            java.lang.String r6 = com.unionpay.mobile.android.utils.c.c(r6)
            r5.bp = r6
            java.lang.String r6 = "order_currency"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            java.util.HashMap<java.lang.String, java.lang.String> r7 = r5.p
            java.lang.String r8 = "trans currcy code"
            if (r6 == 0) goto L_0x0428
            int r9 = r6.length()
            if (r9 <= 0) goto L_0x0428
            r9 = r6
            goto L_0x042a
        L_0x0428:
            java.lang.String r9 = "0156"
        L_0x042a:
            r7.put(r8, r9)
            r5.bq = r6
            java.lang.String r6 = "trans_type"
            java.lang.String r6 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            java.util.HashMap<java.lang.String, java.lang.String> r7 = r5.p
            java.lang.String r8 = "trans_type"
            if (r6 == 0) goto L_0x0442
            int r9 = r6.length()
            if (r9 <= 0) goto L_0x0442
            goto L_0x0444
        L_0x0442:
            java.lang.String r6 = "00"
        L_0x0444:
            r7.put(r8, r6)
            java.lang.String r6 = "mer_name"
            java.lang.String r12 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r12, (java.lang.String) r6)
            java.util.HashMap<java.lang.String, java.lang.String> r5 = r5.p
            java.lang.String r6 = "mer_name"
            if (r12 == 0) goto L_0x045a
            int r7 = r12.length()
            if (r7 <= 0) goto L_0x045a
            goto L_0x045c
        L_0x045a:
            java.lang.String r12 = ""
        L_0x045c:
            r5.put(r6, r12)
            com.unionpay.mobile.android.model.b r12 = r11.f9608a
            boolean r12 = r12.aD
            if (r12 == 0) goto L_0x046b
            com.unionpay.mobile.android.model.b r12 = r11.f9608a
            java.lang.String r5 = ""
            r12.u = r5
        L_0x046b:
            java.lang.String r12 = r11.A
            java.lang.String r5 = "00"
            boolean r12 = r12.equalsIgnoreCase(r5)
            if (r12 != 0) goto L_0x0477
            goto L_0x00b1
        L_0x0477:
            com.unionpay.mobile.android.model.b r12 = r11.f9608a
            java.lang.String r12 = r12.u
            boolean r12 = b((java.lang.String) r12)
            if (r12 == 0) goto L_0x0041
            r11.B = r0
            java.lang.String r12 = "\"user_id\":\"%s\""
            java.lang.Object[] r0 = new java.lang.Object[r2]
            com.unionpay.mobile.android.model.b r1 = r11.f9608a
            java.lang.String r1 = r1.u
            r0[r3] = r1
            java.lang.String r12 = java.lang.String.format(r12, r0)
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r0 = r11.e
            r0.m(r12)
        L_0x0496:
            java.lang.String r12 = "uppay"
            java.lang.String r0 = "init.parserParamJsonObj() ---"
            com.unionpay.mobile.android.utils.k.a(r12, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.l.a(org.json.JSONObject):void");
    }

    public final void a(boolean z2) {
        this.f9608a.ax = z2;
    }

    public final void b(int i) {
        k.a("uppay", toString() + "doErrHappended() +++");
        b(i, "fail");
        k.a("uppay", toString() + "doErrHappended() ---");
    }

    public final void c(String str) {
        this.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        new Thread(new q(this.d, str, this)).start();
    }

    /* access modifiers changed from: protected */
    public final void d() {
        super.d();
        this.m.setBackgroundColor(-1);
        setBackgroundDrawable(this.c.a(ErrorCode.APPLY_TIMES_EXCEED_LIMIT, -1, -1));
        int i = com.unionpay.mobile.android.global.a.I / 2;
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(this.c.a(1027, i, -1));
        imageView.setId(imageView.hashCode());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, -2);
        layoutParams.addRule(14);
        layoutParams.leftMargin = com.unionpay.mobile.android.global.a.j;
        layoutParams.topMargin = (int) (((float) com.unionpay.mobile.android.global.a.t) * 0.3f);
        addView(imageView, layoutParams);
        this.z = new ProgressBar(getContext(), (AttributeSet) null, 16843399);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(14, -1);
        layoutParams2.addRule(3, imageView.getId());
        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.d * 3;
        addView(this.z, layoutParams2);
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(14, -1);
        layoutParams3.addRule(12, -1);
        layoutParams3.bottomMargin = com.unionpay.mobile.android.global.a.b;
        addView(linearLayout, layoutParams3);
        TextView textView = new TextView(this.d);
        textView.setText(com.unionpay.mobile.android.languages.c.bD.f9574a);
        textView.setTextColor(-1);
        textView.setTextSize(14.0f);
        textView.setGravity(1);
        new LinearLayout.LayoutParams(-2, -2).gravity = 14;
        linearLayout.addView(textView, layoutParams3);
        TextView textView2 = new TextView(getContext());
        textView2.setText(com.unionpay.mobile.android.languages.c.bD.b);
        textView2.setTextColor(-1);
        textView2.setTextSize(16.0f);
        textView2.setGravity(1);
        new LinearLayout.LayoutParams(-2, -2).gravity = 14;
        linearLayout.addView(textView2, layoutParams3);
    }

    public final void k() {
    }

    public final void r() {
        int i;
        UPPayEngine uPPayEngine;
        String str;
        if (!this.K) {
            this.K = true;
            v();
            this.E = false;
            Activity activity = this.I;
            boolean z2 = this.J;
            this.e.a();
            try {
                i = Integer.parseInt(this.f9608a.I.c);
            } catch (NumberFormatException unused) {
                i = 0;
            }
            this.H = this.e.initJNIEnv(activity, this.f9608a.f ? 2 : this.f9608a.c ? 1 : 0, i, !"com.unionpay.uppay".equals(f.b(this.d)), this.f9608a.f9576a, this.f9608a.aO, com.unionpay.mobile.android.utils.c.b(this.f9608a.f ? this.f9608a.d : this.f9608a.b));
            if (z2 && this.H != 0 && this.H != -1) {
                this.B = 1;
                this.e.a(this.H);
                if (this.f9608a.f) {
                    uPPayEngine = this.e;
                    str = this.f9608a.e;
                } else {
                    uPPayEngine = this.e;
                    str = this.f9608a.b;
                }
                uPPayEngine.b(str, Constant.DEFAULT_CVN2);
                this.e.a((UPPayEngine.a) this);
            } else if (this.H == -1) {
                b(7, (String) null);
            } else if (!z2) {
                b(5, (String) null);
            }
        }
    }

    public boolean s() {
        return false;
    }

    public boolean t() {
        return false;
    }

    public final void u() {
        if (this.A.equalsIgnoreCase("02")) {
            j();
            return;
        }
        this.C++;
        this.E = true;
        z();
    }

    public void v() {
        w();
    }

    /* access modifiers changed from: protected */
    public final void w() {
        this.C++;
        z();
    }

    public final void x() {
        removeAllViews();
        this.z = null;
    }
}
