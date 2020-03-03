package com.unionpay.mobile.android.hce;

import com.unionpay.mobile.android.hce.service.a;

public final class l {

    /* renamed from: a  reason: collision with root package name */
    private String f9571a;
    private String b = null;
    private a c = null;
    private boolean d = false;
    private boolean e = false;

    public l(String str) {
        this.f9571a = str;
    }

    public final void a(a aVar) {
        this.c = aVar;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final boolean a() {
        return this.d;
    }

    public final a b() {
        return this.c;
    }

    public final String c() {
        return this.b;
    }

    public final void d() {
        this.d = true;
    }

    public final void e() {
        this.e = true;
    }
}
