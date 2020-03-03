package com.xiaomi.phonenum.http;

public class HttpClientConfig {
    private static volatile long f = 10000;
    private static volatile long g = 5000;

    /* renamed from: a  reason: collision with root package name */
    public final long f12557a;
    public final long b;
    public final long c;
    public final long d;
    public final int e;

    private HttpClientConfig(Builder builder) {
        this.e = builder.f12558a;
        this.f12557a = f;
        this.d = g;
        this.b = 15000;
        this.c = 15000;
    }

    public static void a(long j) {
        f = j;
    }

    public static void b(long j) {
        g = j;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        int f12558a = -1;

        public Builder a(int i) {
            this.f12558a = i;
            return this;
        }

        public HttpClientConfig a() {
            return new HttpClientConfig(this);
        }
    }
}
