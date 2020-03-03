package com.loc;

import com.amap.api.location.AMapLocation;

@aw(a = "c")
public class ej {
    @ax(a = "a2", b = 6)

    /* renamed from: a  reason: collision with root package name */
    private String f6587a;
    @ax(a = "a3", b = 5)
    private long b;
    @ax(a = "a4", b = 6)
    private String c;
    private AMapLocation d;

    public final AMapLocation a() {
        return this.d;
    }

    public final void a(long j) {
        this.b = j;
    }

    public final void a(AMapLocation aMapLocation) {
        this.d = aMapLocation;
    }

    public final void a(String str) {
        this.c = str;
    }

    public final String b() {
        return this.c;
    }

    public final void b(String str) {
        this.f6587a = str;
    }

    public final String c() {
        return this.f6587a;
    }

    public final long d() {
        return this.b;
    }
}
