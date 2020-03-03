package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import com.taobao.weex.el.parse.Operators;
import org.json.JSONObject;

public abstract class aa extends z {

    /* renamed from: a  reason: collision with root package name */
    protected int f9764a;
    protected u b;
    /* access modifiers changed from: private */
    public a c;

    public interface a {
        void a(u uVar, String str);
    }

    public aa(Context context, int i, JSONObject jSONObject, String str) {
        this(context, i, jSONObject, str, (byte) 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0141  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public aa(android.content.Context r1, int r2, org.json.JSONObject r3, java.lang.String r4, byte r5) {
        /*
            r0 = this;
            r0.<init>(r1, r3, r4)
            r1 = 0
            r0.b = r1
            r0.c = r1
            r0.f9764a = r2
            android.content.Context r1 = r0.d
            com.unionpay.mobile.android.resource.c.a(r1)
            com.unionpay.mobile.android.widgets.u r1 = new com.unionpay.mobile.android.widgets.u
            android.content.Context r2 = r0.getContext()
            r1.<init>(r2)
            r0.b = r1
            boolean r1 = r0.i
            if (r1 == 0) goto L_0x0028
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            r1.a()
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            r1.d()
        L_0x0028:
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            java.lang.String r2 = r0.i()
            r1.c((java.lang.String) r2)
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            int r2 = com.unionpay.mobile.android.global.a.n
            r4 = -1
            r1.<init>(r4, r2)
            r2 = 15
            r1.addRule(r2, r4)
            android.widget.RelativeLayout r2 = r0.m
            com.unionpay.mobile.android.widgets.u r5 = r0.b
            r2.addView(r5, r1)
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            java.lang.String r2 = "placeholder"
            java.lang.String r2 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r3, (java.lang.String) r2)
            r1.b((java.lang.String) r2)
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            r2 = 1
            r1.setFocusable(r2)
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.widgets.ab r2 = new com.unionpay.mobile.android.widgets.ab
            r2.<init>(r0)
            r1.a((android.widget.TextView.OnEditorActionListener) r2)
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.widgets.ac r2 = new com.unionpay.mobile.android.widgets.ac
            r2.<init>(r0)
            r1.a((android.text.TextWatcher) r2)
            android.content.Context r1 = r0.d
            com.unionpay.mobile.android.resource.c r1 = com.unionpay.mobile.android.resource.c.a(r1)
            r2 = 2000(0x7d0, float:2.803E-42)
            int r3 = com.unionpay.mobile.android.global.a.v
            android.graphics.drawable.Drawable r1 = r1.a(r2, r4, r3)
            com.unionpay.mobile.android.widgets.u r2 = r0.b
            r2.a((android.graphics.drawable.Drawable) r1)
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.af
            if (r1 == 0) goto L_0x00af
            boolean r1 = r0.i
            if (r1 != 0) goto L_0x0090
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aQ
        L_0x008b:
            r1.a((java.lang.String) r2)
            goto L_0x012b
        L_0x0090:
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r0.h
            r2.append(r3)
            java.lang.String r3 = " "
            r2.append(r3)
            java.lang.String r3 = r0.g
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.c((java.lang.String) r2)
            goto L_0x012b
        L_0x00af:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.ap
            if (r1 == 0) goto L_0x00ba
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aR
            goto L_0x008b
        L_0x00ba:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.ah
            if (r1 == 0) goto L_0x00c5
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aT
            goto L_0x008b
        L_0x00c5:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.UPWidget
            if (r1 == 0) goto L_0x00d0
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aS
            goto L_0x008b
        L_0x00d0:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.au
            if (r1 == 0) goto L_0x00db
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aU
            goto L_0x008b
        L_0x00db:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.ao
            if (r1 == 0) goto L_0x00e6
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aV
            goto L_0x008b
        L_0x00e6:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.e
            if (r1 == 0) goto L_0x00f1
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aW
            goto L_0x008b
        L_0x00f1:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.ae
            if (r1 == 0) goto L_0x00fc
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aX
            goto L_0x008b
        L_0x00fc:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.bd
            if (r1 == 0) goto L_0x0107
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aY
            goto L_0x008b
        L_0x0107:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.at
            if (r1 == 0) goto L_0x0113
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aZ
            goto L_0x008b
        L_0x0113:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.av
            if (r1 == 0) goto L_0x011f
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.ba
            goto L_0x008b
        L_0x011f:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.f
            if (r1 == 0) goto L_0x012b
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.bb
            goto L_0x008b
        L_0x012b:
            boolean r1 = r0 instanceof com.unionpay.mobile.android.widgets.k
            if (r1 == 0) goto L_0x0141
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            android.content.Context r2 = r0.d
            com.unionpay.mobile.android.resource.c r2 = com.unionpay.mobile.android.resource.c.a(r2)
            r3 = 1011(0x3f3, float:1.417E-42)
        L_0x0139:
            android.graphics.drawable.Drawable r2 = r2.a(r3, r4, r4)
            r1.setBackgroundDrawable(r2)
            return
        L_0x0141:
            com.unionpay.mobile.android.widgets.u r1 = r0.b
            android.content.Context r2 = r0.d
            com.unionpay.mobile.android.resource.c r2 = com.unionpay.mobile.android.resource.c.a(r2)
            r3 = 1013(0x3f5, float:1.42E-42)
            goto L_0x0139
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.widgets.aa.<init>(android.content.Context, int, org.json.JSONObject, java.lang.String, byte):void");
    }

    public String a() {
        return this.b.b();
    }

    public void a(Editable editable) {
    }

    public final void a(a aVar) {
        this.c = aVar;
    }

    /* access modifiers changed from: protected */
    public final boolean a(View view) {
        if (view != null) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            Log.e("uppay", "v getGlobalVisibleRect():" + rect.toString());
            Rect rect2 = new Rect();
            ((Activity) this.d).getWindow().getDecorView().findViewById(16908290).getGlobalVisibleRect(rect2);
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            StringBuilder sb = new StringBuilder(" locationW = [");
            boolean z = false;
            sb.append(iArr[0]);
            sb.append(",");
            sb.append(iArr[1]);
            sb.append(Operators.ARRAY_END_STR);
            Log.e("uppay", sb.toString());
            int[] iArr2 = new int[2];
            view.getLocationOnScreen(iArr2);
            Log.e("uppay", " locationS = [" + iArr2[0] + "," + iArr2[1] + Operators.ARRAY_END_STR);
            if (iArr[1] + view.getHeight() + 10 > rect2.bottom) {
                z = true;
            }
            View findViewById = ((Activity) this.d).getWindow().getDecorView().findViewById(16908290);
            Rect rect3 = new Rect();
            findViewById.getLocalVisibleRect(rect3);
            Log.e("uppay", " getLocalVisibleRect = " + rect3.toString());
            Rect rect4 = new Rect();
            findViewById.getGlobalVisibleRect(rect4);
            Log.e("uppay", " getGlobalVisibleRect = " + rect4.toString());
            return z;
        }
        throw new NullPointerException();
    }

    public final boolean a(u uVar) {
        return uVar != null && this.b == uVar;
    }

    public boolean c() {
        return (a() == null || a().length() == 0) ? false : true;
    }

    public final void g() {
        if (this.b != null && !this.i) {
            this.b.e();
        }
    }
}
