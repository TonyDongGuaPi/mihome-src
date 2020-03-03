package com.amap.openapi;

import android.support.annotation.NonNull;
import com.amap.location.common.util.b;

public class ed implements dq {

    /* renamed from: a  reason: collision with root package name */
    private dq f4720a;

    public ed(@NonNull dq dqVar) {
        this.f4720a = dqVar;
    }

    public int a() {
        return b.a(this.f4720a.a(), 0, Integer.MAX_VALUE);
    }

    public long a(int i) {
        return b.a(this.f4720a.a(i), 1000, 10000000);
    }

    public long b(int i) {
        return b.a(this.f4720a.b(i), 0, 100000000);
    }

    public void b() {
        this.f4720a.b();
    }

    public long c() {
        return b.a(this.f4720a.c(), 0, 20000000);
    }

    public boolean c(int i) {
        return this.f4720a.c(i);
    }

    public long d() {
        return b.a(this.f4720a.d(), 60000, 86400000);
    }

    public long e() {
        return b.a(this.f4720a.e(), 1000, 3600000);
    }

    public int f() {
        return b.a(this.f4720a.f(), 1000, 600000);
    }

    public long g() {
        return b.a(this.f4720a.g(), 0, 50000000);
    }

    public long h() {
        return b.a(this.f4720a.h(), 0, Long.MAX_VALUE);
    }
}
