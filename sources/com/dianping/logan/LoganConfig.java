package com.dianping.logan;

import android.text.TextUtils;

public class LoganConfig {
    private static final long i = 86400000;
    private static final long j = 1048576;
    private static final long k = 259200000;
    private static final long l = 10485760;
    private static final long m = 52428800;
    private static final int n = 500;

    /* renamed from: a  reason: collision with root package name */
    String f5162a;
    String b;
    long c;
    long d;
    long e;
    long f;
    byte[] g;
    byte[] h;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return !TextUtils.isEmpty(this.f5162a) && !TextUtils.isEmpty(this.b);
    }

    private LoganConfig() {
        this.c = l;
        this.d = k;
        this.e = 500;
        this.f = 52428800;
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        this.f5162a = str;
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        this.b = str;
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        this.c = j2;
    }

    /* access modifiers changed from: private */
    public void b(long j2) {
        this.d = j2;
    }

    /* access modifiers changed from: private */
    public void c(long j2) {
        this.f = j2;
    }

    /* access modifiers changed from: private */
    public void a(byte[] bArr) {
        this.g = bArr;
    }

    /* access modifiers changed from: private */
    public void b(byte[] bArr) {
        this.h = bArr;
    }

    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        String f5163a;
        String b;
        long c = LoganConfig.l;
        long d = LoganConfig.k;
        byte[] e;
        byte[] f;
        long g = 52428800;

        public Builder a(String str) {
            this.f5163a = str;
            return this;
        }

        public Builder b(String str) {
            this.b = str;
            return this;
        }

        public Builder a(long j) {
            this.c = j * 1048576;
            return this;
        }

        public Builder b(long j) {
            this.d = j * 86400000;
            return this;
        }

        public Builder a(byte[] bArr) {
            this.e = bArr;
            return this;
        }

        public Builder b(byte[] bArr) {
            this.f = bArr;
            return this;
        }

        public Builder c(long j) {
            this.g = j;
            return this;
        }

        public LoganConfig a() {
            LoganConfig loganConfig = new LoganConfig();
            loganConfig.a(this.f5163a);
            loganConfig.b(this.b);
            loganConfig.a(this.c);
            loganConfig.c(this.g);
            loganConfig.b(this.d);
            loganConfig.a(this.e);
            loganConfig.b(this.f);
            return loganConfig;
        }
    }
}
