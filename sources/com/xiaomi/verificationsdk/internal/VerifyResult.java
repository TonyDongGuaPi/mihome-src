package com.xiaomi.verificationsdk.internal;

public class VerifyResult {

    /* renamed from: a  reason: collision with root package name */
    private String f23134a;
    private String b;
    private String c;
    private String d;

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.f23134a;
    }

    public String d() {
        return this.d;
    }

    private VerifyResult(Builder builder) {
        this.f23134a = builder.f23135a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f23135a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;

        public Builder a(String str) {
            this.f23135a = str;
            return this;
        }

        public Builder b(String str) {
            this.b = str;
            return this;
        }

        public Builder c(String str) {
            this.c = str;
            return this;
        }

        public Builder d(String str) {
            this.d = str;
            return this;
        }

        public VerifyResult a() {
            return new VerifyResult(this);
        }
    }
}
