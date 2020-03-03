package com.xiaomi.verificationsdk.internal;

public class VerifyError {

    /* renamed from: a  reason: collision with root package name */
    private int f23131a;
    private String b;
    private int c;

    public int a() {
        return this.f23131a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public VerifyError(Build build) {
        this.f23131a = build.f23132a;
        this.b = build.b;
        this.c = build.c;
    }

    public static class Build {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f23132a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public int c;

        public Build a(int i) {
            this.f23132a = i;
            return this;
        }

        public Build a(String str) {
            this.b = str;
            return this;
        }

        public Build b(int i) {
            this.c = i;
            return this;
        }

        public VerifyError a() {
            return new VerifyError(this);
        }
    }
}
