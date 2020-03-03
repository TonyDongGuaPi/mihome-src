package com.alipay.deviceid.module.x;

import android.content.Context;

public final class ce {

    /* renamed from: a  reason: collision with root package name */
    ci f905a;
    cj b;
    ck c;
    cm d;
    cn e;
    public cp f;
    cq g;
    private cg h = new cg();
    private cl i;
    private co j;
    private boolean k;

    public ce(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, boolean z) {
        Context context2 = context;
        boolean z2 = z;
        Context context3 = context;
        this.f905a = new ci(context3, str, str2, str3, str4, str5);
        this.b = new cj(context);
        this.c = new ck(context);
        this.i = new cl();
        this.d = new cm();
        this.e = new cn();
        this.j = new co();
        this.f = new cp();
        this.g = new cq(context3, str6, str7, str8, str9, str10);
        this.k = z2;
        if (z2) {
            this.e.a(context);
        }
    }

    public final void a(String str) {
        this.g.b.put("user", str);
    }

    public final void a(String str, String str2, boolean z) {
        this.f.a(str, str2, z);
    }

    public final void a() {
        cp cpVar = this.f;
        Integer valueOf = Integer.valueOf(cpVar.c.get());
        if (cpVar.f != null) {
            cpVar.b.put("num", String.valueOf(valueOf));
            cpVar.f = null;
        }
    }
}
