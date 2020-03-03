package com.xiaomi.mimc.common;

import java.util.concurrent.TimeUnit;

public class Backoff {

    /* renamed from: a  reason: collision with root package name */
    private static final long f11168a = 200;
    private static final int b = 2;
    private static final long c = Long.MAX_VALUE;
    private static final int d = Integer.MAX_VALUE;
    private final long e;
    private final int f;
    private final long g;
    private final int h;
    private long i;

    private Backoff(long j, int i2, long j2, int i3) {
        this.i = 0;
        this.e = j;
        this.f = i2;
        this.g = j2;
        this.h = i3;
    }

    public static Builder a() {
        return new Builder();
    }

    public void b() {
        if (this.i != 0) {
            this.i = 0;
        }
    }

    public void c() throws InterruptedException {
        if (this.i < ((long) this.h)) {
            long j = this.i;
            this.i = 1 + j;
            a(j);
            return;
        }
        a((long) this.h);
    }

    public void a(long j) throws InterruptedException {
        Thread.sleep(b(j));
    }

    public long b(long j) {
        long pow = this.e * ((long) Math.pow((double) this.f, (double) j));
        if (pow < 0) {
            pow = Long.MAX_VALUE;
        }
        return Math.min(Math.max(pow, this.e), this.g);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private long f11169a = 200;
        private int b = 2;
        private long c = Long.MAX_VALUE;
        private int d = Integer.MAX_VALUE;

        Builder() {
        }

        public Builder a(TimeUnit timeUnit, long j) {
            this.f11169a = timeUnit.toMillis(j);
            return this;
        }

        public Builder a(int i) {
            this.b = i;
            return this;
        }

        public Builder b(TimeUnit timeUnit, long j) {
            this.c = timeUnit.toMillis(j);
            return this;
        }

        public Builder b(int i) {
            this.d = i;
            return this;
        }

        public Backoff a() {
            if (this.c >= this.f11169a) {
                return new Backoff(this.f11169a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Initial backoff cannot be more than maximum.");
        }
    }
}
