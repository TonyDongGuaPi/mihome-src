package com.unionpay.mobile.android.hce;

import android.content.ServiceConnection;

public final class d {

    /* renamed from: a  reason: collision with root package name */
    private String f9563a;
    private String b = null;
    private String c = null;
    private boolean d = false;
    private boolean e = false;
    private ServiceConnection f = null;

    public d(String str) {
        this.f9563a = str;
    }

    public final void a(ServiceConnection serviceConnection) {
        this.f = serviceConnection;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final boolean a() {
        return this.d;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final boolean b() {
        return this.e;
    }

    public final String c() {
        return this.b;
    }

    public final ServiceConnection d() {
        return this.f;
    }

    public final void e() {
        this.d = true;
    }

    public final void f() {
        this.e = true;
    }
}
