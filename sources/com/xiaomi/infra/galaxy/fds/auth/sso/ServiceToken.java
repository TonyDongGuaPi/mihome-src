package com.xiaomi.infra.galaxy.fds.auth.sso;

import com.taobao.weex.el.parse.Operators;

public class ServiceToken {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f10154a;
    /* access modifiers changed from: private */
    public long b;
    /* access modifiers changed from: private */
    public long c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public long e;
    /* access modifiers changed from: private */
    public String f;

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private boolean f10155a;
        private long b;
        private String c;
        private long d;
        private String e;
        private long f;

        public ServiceToken a() {
            ServiceToken serviceToken = new ServiceToken();
            boolean unused = serviceToken.f10154a = this.f10155a;
            long unused2 = serviceToken.b = this.b;
            String unused3 = serviceToken.d = this.c;
            long unused4 = serviceToken.e = this.d;
            String unused5 = serviceToken.f = this.e;
            long unused6 = serviceToken.c = this.f;
            return serviceToken;
        }

        public Builder a(boolean z) {
            this.f10155a = z;
            return this;
        }

        public Builder a(long j) {
            this.b = j;
            return this;
        }

        public Builder a(String str) {
            this.c = str;
            return this;
        }

        public Builder b(long j) {
            this.d = j;
            return this;
        }

        public Builder b(String str) {
            this.e = str;
            return this;
        }

        public Builder c(long j) {
            this.f = j;
            return this;
        }
    }

    public boolean a() {
        return this.f10154a;
    }

    public long b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public long d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public long f() {
        return this.c;
    }

    public String toString() {
        return "[ServiceToken: tsl=" + this.f10154a + ", uid=" + this.b + ", timestamp=" + this.e + ", version=" + this.f + Operators.ARRAY_END_STR;
    }
}
