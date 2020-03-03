package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class c extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private Drawable f9716a;
    private Drawable b;
    private Drawable c;
    private Context d;
    private String e = null;
    private String f = null;
    private String g = null;
    private boolean h = false;
    private int i = 1;
    private int j = 0;
    private List<Map<String, Object>> k;
    /* access modifiers changed from: private */
    public ArrayList<View.OnClickListener> l = new ArrayList<>();
    private View.OnClickListener m = new d(this);

    public c(Context context, List<Map<String, Object>> list, String str, String str2, String str3, int i2, int i3) {
        this.d = context;
        this.k = list;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.i = i2;
        this.j = i3;
        this.f9716a = com.unionpay.mobile.android.resource.c.a(this.d).a(1015, -1, -1);
        this.b = com.unionpay.mobile.android.resource.c.a(this.d).a(1016, -1, -1);
        this.c = com.unionpay.mobile.android.resource.c.a(this.d).a(1002, -1, -1);
    }

    private boolean d() {
        return this.e != null && !TextUtils.isEmpty(this.e);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000f, code lost:
        r3 = r2.k.get(r3).get(com.mi.global.shop.model.Tags.MiHome.AVAILABLE);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean d(int r3) {
        /*
            r2 = this;
            int r0 = r2.c()
            int r3 = r3 - r0
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r0 = r2.k
            int r0 = r0.size()
            r1 = 1
            if (r3 != r0) goto L_0x000f
            return r1
        L_0x000f:
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r0 = r2.k
            java.lang.Object r3 = r0.get(r3)
            java.util.Map r3 = (java.util.Map) r3
            java.lang.String r0 = "available"
            java.lang.Object r3 = r3.get(r0)
            if (r3 == 0) goto L_0x0026
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            if (r0 != r3) goto L_0x0026
            r1 = 0
        L_0x0026:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upwidget.c.d(int):boolean");
    }

    private boolean e() {
        return this.f != null && !TextUtils.isEmpty(this.f);
    }

    public final void a() {
        this.h = !this.h;
    }

    public final void a(int i2) {
        this.i = i2;
    }

    public final void a(View.OnClickListener onClickListener) {
        this.l.add(onClickListener);
    }

    public final void a(String str) {
        this.e = str;
    }

    public final Spanned b(int i2) {
        int c2 = i2 - c();
        if (c2 == this.k.size()) {
            return null;
        }
        Map map = this.k.get(c2);
        return Html.fromHtml(((String) map.get("text1")) + " " + ((String) map.get("text2")));
    }

    public final void b(String str) {
        this.g = str;
    }

    public final boolean b() {
        return this.h;
    }

    public final int c() {
        return d() ? 1 : 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000f, code lost:
        r3 = r2.k.get(r3).get("editable");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean c(int r3) {
        /*
            r2 = this;
            int r0 = r2.c()
            int r3 = r3 - r0
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r0 = r2.k
            int r0 = r0.size()
            r1 = 1
            if (r3 != r0) goto L_0x000f
            return r1
        L_0x000f:
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r0 = r2.k
            java.lang.Object r3 = r0.get(r3)
            java.util.Map r3 = (java.util.Map) r3
            java.lang.String r0 = "editable"
            java.lang.Object r3 = r3.get(r0)
            if (r3 == 0) goto L_0x0026
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            if (r0 != r3) goto L_0x0026
            r1 = 0
        L_0x0026:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upwidget.c.c(int):boolean");
    }

    public final int getCount() {
        if (this.k == null) {
            return 0;
        }
        return this.k.size() + c() + (e() ? 1 : 0);
    }

    public final Object getItem(int i2) {
        if (i2 == 0 || this.k == null || i2 >= this.k.size()) {
            return null;
        }
        return this.k.get(i2 - c());
    }

    public final long getItemId(int i2) {
        return (long) i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x016c, code lost:
        if (r3 != null) goto L_0x015a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01f6, code lost:
        if (r9 != false) goto L_0x01fa;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View getView(int r17, android.view.View r18, android.view.ViewGroup r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            android.widget.LinearLayout r2 = new android.widget.LinearLayout
            android.content.Context r3 = r0.d
            r2.<init>(r3)
            r3 = 1
            r2.setOrientation(r3)
            android.widget.RelativeLayout r4 = new android.widget.RelativeLayout
            android.content.Context r5 = r0.d
            r4.<init>(r5)
            int r5 = com.unionpay.mobile.android.global.b.g
            r4.setPadding(r5, r5, r5, r5)
            r2.addView(r4)
            android.widget.LinearLayout r6 = new android.widget.LinearLayout
            android.content.Context r7 = r0.d
            r6.<init>(r7)
            r7 = -3419943(0xffffffffffcbd0d9, float:NaN)
            r6.setBackgroundColor(r7)
            android.widget.LinearLayout$LayoutParams r7 = new android.widget.LinearLayout$LayoutParams
            r8 = -1
            r7.<init>(r8, r3)
            int r9 = r16.c()
            int r9 = r1 - r9
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r10 = r0.k
            int r10 = r10.size()
            if (r9 != r10) goto L_0x0041
            r9 = 1
            goto L_0x0042
        L_0x0041:
            r9 = 0
        L_0x0042:
            boolean r10 = r16.d()
            r12 = 11
            r13 = 9
            r14 = 15
            r15 = -2
            if (r10 == 0) goto L_0x00ba
            if (r1 != 0) goto L_0x00ba
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r5 = r0.d
            r1.<init>(r5)
            java.lang.String r5 = r0.e
            r1.setText(r5)
            float r5 = com.unionpay.mobile.android.global.b.k
            r1.setTextSize(r5)
            r5 = -13421773(0xffffffffff333333, float:-2.3819765E38)
            r1.setTextColor(r5)
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams
            r9.<init>(r15, r15)
            r9.addRule(r13, r8)
            r9.addRule(r14, r8)
            int r10 = com.unionpay.mobile.android.global.b.f
            r9.leftMargin = r10
            r4.addView(r1, r9)
            java.lang.String r1 = r0.g
            if (r1 == 0) goto L_0x0087
            java.lang.String r1 = r0.g
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0087
            goto L_0x0088
        L_0x0087:
            r3 = 0
        L_0x0088:
            if (r3 == 0) goto L_0x00b5
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r3 = r0.d
            r1.<init>(r3)
            java.lang.String r3 = r0.g
            r1.setText(r3)
            float r3 = com.unionpay.mobile.android.global.b.k
            r1.setTextSize(r3)
            r1.setTextColor(r5)
            android.view.View$OnClickListener r3 = r0.m
            r1.setOnClickListener(r3)
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            r3.<init>(r15, r15)
            r3.addRule(r12, r8)
            r3.addRule(r14, r8)
            int r5 = com.unionpay.mobile.android.global.b.f
            r3.rightMargin = r5
            r4.addView(r1, r3)
        L_0x00b5:
            r2.addView(r6, r7)
            goto L_0x01fa
        L_0x00ba:
            boolean r10 = r16.e()
            r11 = -10066330(0xffffffffff666666, float:-3.0625412E38)
            r3 = 1101004800(0x41a00000, float:20.0)
            if (r10 == 0) goto L_0x010b
            if (r9 == 0) goto L_0x010b
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r5 = r0.d
            r1.<init>(r5)
            java.lang.String r5 = r0.f
            r1.setText(r5)
            float r5 = com.unionpay.mobile.android.global.b.k
            r1.setTextSize(r5)
            r1.setTextColor(r11)
            android.widget.RelativeLayout$LayoutParams r5 = new android.widget.RelativeLayout$LayoutParams
            r5.<init>(r15, r15)
            r5.addRule(r14, r8)
            r5.addRule(r13, r8)
            r4.addView(r1, r5)
            android.widget.ImageView r1 = new android.widget.ImageView
            android.content.Context r5 = r0.d
            r1.<init>(r5)
            android.graphics.drawable.Drawable r5 = r0.c
            r1.setBackgroundDrawable(r5)
            android.content.Context r5 = r0.d
            int r3 = com.unionpay.mobile.android.utils.g.a(r5, r3)
            android.widget.RelativeLayout$LayoutParams r5 = new android.widget.RelativeLayout$LayoutParams
            r5.<init>(r3, r3)
            r5.addRule(r14, r8)
            r5.addRule(r12, r8)
            r4.addView(r1, r5)
            goto L_0x01fa
        L_0x010b:
            android.widget.ImageView r10 = new android.widget.ImageView
            android.content.Context r12 = r0.d
            r10.<init>(r12)
            r12 = 4
            r10.setVisibility(r12)
            int r12 = r10.hashCode()
            r10.setId(r12)
            android.widget.TextView r12 = new android.widget.TextView
            android.content.Context r15 = r0.d
            r12.<init>(r15)
            r15 = 1
            r12.setSingleLine(r15)
            android.text.TextUtils$TruncateAt r15 = android.text.TextUtils.TruncateAt.END
            r12.setEllipsize(r15)
            android.text.Spanned r15 = r16.b((int) r17)
            r12.setText(r15)
            float r15 = com.unionpay.mobile.android.global.b.k
            r12.setTextSize(r15)
            r12.setTextColor(r11)
            android.content.Context r11 = r0.d
            int r11 = com.unionpay.mobile.android.utils.g.a(r11, r3)
            int r15 = r0.j
            if (r15 != 0) goto L_0x0196
            boolean r3 = r0.h
            if (r3 == 0) goto L_0x014d
            android.graphics.drawable.Drawable r3 = r0.b
            goto L_0x014f
        L_0x014d:
            android.graphics.drawable.Drawable r3 = r0.f9716a
        L_0x014f:
            boolean r5 = r0.h
            if (r5 != 0) goto L_0x0161
            int r5 = r0.i
            if (r5 != r1) goto L_0x0161
            if (r3 == 0) goto L_0x0161
            r5 = 0
        L_0x015a:
            r10.setVisibility(r5)
            r10.setBackgroundDrawable(r3)
            goto L_0x016f
        L_0x0161:
            r5 = 0
            boolean r1 = r16.c(r17)
            if (r1 == 0) goto L_0x016f
            boolean r1 = r0.h
            if (r1 == 0) goto L_0x016f
            if (r3 == 0) goto L_0x016f
            goto L_0x015a
        L_0x016f:
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r11, r11)
            r1.addRule(r14, r8)
            r1.addRule(r13, r8)
            r4.addView(r10, r1)
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = -2
            r1.<init>(r3, r3)
            r1.addRule(r14, r8)
            int r3 = r10.hashCode()
            r15 = 1
            r1.addRule(r15, r3)
            int r3 = com.unionpay.mobile.android.global.b.g
            r1.leftMargin = r3
            r4.addView(r12, r1)
            goto L_0x01f0
        L_0x0196:
            r15 = 1
            int r13 = r0.j
            if (r13 != r15) goto L_0x01f0
            int r13 = r0.i
            if (r13 != r1) goto L_0x01a2
            r13 = 1008(0x3f0, float:1.413E-42)
            goto L_0x01a4
        L_0x01a2:
            r13 = 1007(0x3ef, float:1.411E-42)
        L_0x01a4:
            android.content.Context r15 = r0.d
            int r3 = com.unionpay.mobile.android.utils.g.a(r15, r3)
            android.content.Context r15 = r0.d
            com.unionpay.mobile.android.resource.c r15 = com.unionpay.mobile.android.resource.c.a(r15)
            android.graphics.drawable.Drawable r11 = r15.a(r13, r11, r11)
            boolean r1 = r16.d(r17)
            if (r1 == 0) goto L_0x01be
            r1 = 0
            r10.setVisibility(r1)
        L_0x01be:
            r10.setBackgroundDrawable(r11)
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r1.<init>(r3, r3)
            r1.addRule(r14, r8)
            r3 = 11
            r1.addRule(r3, r8)
            r4.addView(r10, r1)
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams
            r3 = -2
            r1.<init>(r3, r3)
            r1.addRule(r14, r8)
            r3 = 9
            r1.addRule(r3, r8)
            int r3 = r10.hashCode()
            r8 = 0
            r1.addRule(r8, r3)
            int r3 = com.unionpay.mobile.android.global.b.g
            r1.rightMargin = r3
            r4.addView(r12, r1)
            r7.leftMargin = r5
        L_0x01f0:
            boolean r1 = r16.e()
            if (r1 == 0) goto L_0x00b5
            if (r9 != 0) goto L_0x01fa
            goto L_0x00b5
        L_0x01fa:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upwidget.c.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public final boolean isEnabled(int i2) {
        if ((!d() || i2 != 0) && d(i2)) {
            return super.isEnabled(i2);
        }
        return false;
    }
}
