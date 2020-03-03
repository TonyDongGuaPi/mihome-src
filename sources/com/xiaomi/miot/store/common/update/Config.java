package com.xiaomi.miot.store.common.update;

public class Config {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f11396a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public long d;
    /* access modifiers changed from: private */
    public long e;
    /* access modifiers changed from: private */
    public boolean f;
    /* access modifiers changed from: private */
    public boolean g;
    /* access modifiers changed from: private */
    public boolean h;
    /* access modifiers changed from: private */
    public boolean i;
    /* access modifiers changed from: private */
    public ReloadStrategy j;
    /* access modifiers changed from: private */
    public int k;

    public boolean a() {
        return this.f;
    }

    public String b() {
        return this.f11396a;
    }

    public long c() {
        return this.d;
    }

    public String d() {
        return this.b;
    }

    public boolean e() {
        return this.g;
    }

    public boolean f() {
        return this.h;
    }

    public boolean g() {
        return this.i;
    }

    public long h() {
        return this.e;
    }

    public String i() {
        return this.c;
    }

    public ReloadStrategy j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private String f11397a;
        private String b;
        private String c;
        private long d;
        private long e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private ReloadStrategy j;
        private int k;

        public static Builder a() {
            return new Builder();
        }

        public Builder a(boolean z) {
            this.f = z;
            return this;
        }

        public Builder a(String str) {
            this.f11397a = str;
            return this;
        }

        public Builder a(long j2) {
            this.d = j2;
            return this;
        }

        public Builder b(String str) {
            this.b = str;
            return this;
        }

        public Builder b(boolean z) {
            this.g = z;
            return this;
        }

        public Builder c(boolean z) {
            this.h = z;
            return this;
        }

        public Builder d(boolean z) {
            this.i = z;
            return this;
        }

        public Builder b(long j2) {
            this.e = j2;
            return this;
        }

        public Builder c(String str) {
            this.c = str;
            return this;
        }

        public Builder a(ReloadStrategy reloadStrategy) {
            this.j = reloadStrategy;
            return this;
        }

        public Builder a(int i2) {
            this.k = i2;
            return this;
        }

        public Config b() {
            Config config = new Config();
            String unused = config.f11396a = this.f11397a;
            String unused2 = config.b = this.b;
            String unused3 = config.c = this.c;
            long unused4 = config.d = this.d;
            boolean unused5 = config.i = this.i;
            long unused6 = config.e = this.e;
            boolean unused7 = config.f = this.f;
            boolean unused8 = config.g = this.g;
            boolean unused9 = config.h = this.h;
            ReloadStrategy unused10 = config.j = this.j;
            int unused11 = config.k = this.k;
            return config;
        }
    }
}
