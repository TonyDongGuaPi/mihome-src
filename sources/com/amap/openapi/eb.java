package com.amap.openapi;

import android.support.annotation.NonNull;
import com.amap.location.common.util.b;

public class eb implements dp {

    /* renamed from: a  reason: collision with root package name */
    private dp f4714a;

    public eb(@NonNull dp dpVar) {
        this.f4714a = dpVar;
    }

    public long a() {
        return b.a(this.f4714a.a(), 0, Long.MAX_VALUE);
    }

    public long a(int i) {
        return b.a(this.f4714a.a(i), 1000, 10000000);
    }

    public long b(int i) {
        return b.a(this.f4714a.b(i), 0, 50000000);
    }

    public void b() {
        this.f4714a.b();
    }

    public long c() {
        return b.a(this.f4714a.c(), 0, 1000000);
    }

    public boolean c(int i) {
        return this.f4714a.c(i);
    }

    public long d() {
        return b.a(this.f4714a.d(), 60000, 86400000);
    }

    public long e() {
        return b.a(this.f4714a.e(), 1000, 3600000);
    }

    public int f() {
        return b.a(this.f4714a.f(), 1000, 600000);
    }

    public long g() {
        return b.a(this.f4714a.g(), 0, 50000000);
    }

    public long h() {
        return b.a(this.f4714a.h(), 0, Long.MAX_VALUE);
    }
}
