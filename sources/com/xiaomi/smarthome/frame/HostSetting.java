package com.xiaomi.smarthome.frame;

public class HostSetting {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1522a;
    public static String b;
    public static int c;
    public static String d;
    public static int e;
    public static String f;
    public static boolean g;
    public static boolean h;
    public static boolean i;
    public static String j;
    public static boolean k;
    public static boolean l;
    public static boolean m;
    public static String n;
    public static String o;
    public static String p;
    public static String q;

    private HostSetting(Builder builder) {
        f1522a = builder.f1523a;
        b = builder.b;
        c = builder.c;
        d = builder.d;
        e = builder.e;
        f = builder.f;
        g = builder.g;
        h = builder.h;
        i = builder.i;
        j = builder.j;
        k = builder.k;
        l = builder.l;
        m = builder.m;
        n = builder.n;
        o = builder.o;
        p = builder.p;
        q = builder.q;
    }

    public static class Builder {
        boolean A = false;
        boolean B = false;
        boolean C = false;
        boolean D = false;
        boolean E = false;
        boolean F = false;
        boolean G = false;
        boolean H = false;

        /* renamed from: a  reason: collision with root package name */
        boolean f1523a;
        String b;
        int c;
        String d;
        int e;
        String f;
        boolean g;
        boolean h;
        boolean i;
        String j;
        boolean k;
        boolean l;
        boolean m;
        String n;
        String o;
        String p;
        String q;
        boolean r = false;
        boolean s = false;
        boolean t = false;
        boolean u = false;
        boolean v = false;
        boolean w = false;
        boolean x = false;
        boolean y = false;
        boolean z = false;

        public Builder a(boolean z2) {
            this.f1523a = z2;
            this.r = true;
            return this;
        }

        public Builder a(String str) {
            this.b = str;
            this.s = true;
            return this;
        }

        public Builder a(int i2) {
            this.c = i2;
            this.t = true;
            return this;
        }

        public Builder b(String str) {
            this.d = str;
            this.u = true;
            return this;
        }

        public Builder b(int i2) {
            this.e = i2;
            this.v = true;
            return this;
        }

        public Builder c(String str) {
            this.f = str;
            this.w = true;
            return this;
        }

        public Builder b(boolean z2) {
            this.g = z2;
            this.x = true;
            return this;
        }

        public Builder c(boolean z2) {
            this.h = z2;
            this.y = true;
            return this;
        }

        public Builder d(boolean z2) {
            this.i = z2;
            this.z = true;
            return this;
        }

        public Builder d(String str) {
            this.j = str;
            this.A = true;
            return this;
        }

        public Builder e(boolean z2) {
            this.k = z2;
            this.B = true;
            return this;
        }

        public Builder f(boolean z2) {
            this.l = z2;
            this.C = true;
            return this;
        }

        public Builder g(boolean z2) {
            this.m = z2;
            this.D = true;
            return this;
        }

        public Builder e(String str) {
            this.n = str;
            this.E = true;
            return this;
        }

        public Builder f(String str) {
            this.o = str;
            this.F = true;
            return this;
        }

        public Builder g(String str) {
            this.p = str;
            this.G = true;
            return this;
        }

        public Builder h(String str) {
            this.q = str;
            this.H = true;
            return this;
        }

        public HostSetting a() {
            if (!this.r) {
                throw new RuntimeException("debug not set");
            } else if (!this.s) {
                throw new RuntimeException("buildType not set");
            } else if (!this.t) {
                throw new RuntimeException("buildId not set");
            } else if (!this.u) {
                throw new RuntimeException("flavor not set");
            } else if (!this.v) {
                throw new RuntimeException("versionCode not set");
            } else if (!this.w) {
                throw new RuntimeException("versionName not set");
            } else if (!this.x) {
                throw new RuntimeException("isDebugBuildType not set");
            } else if (!this.y) {
                throw new RuntimeException("isReleaseBuildType not set");
            } else if (!this.z) {
                throw new RuntimeException("isSdkBuildType not set");
            } else if (!this.A) {
                throw new RuntimeException("channel not set");
            } else if (!this.B) {
                throw new RuntimeException("isDevChannel not set");
            } else if (!this.C) {
                throw new RuntimeException("isMiuiChannel not set");
            } else if (!this.D) {
                throw new RuntimeException("isMiAppStoreChannel not set");
            } else if (!this.E) {
                throw new RuntimeException("miAppID not set");
            } else if (!this.F) {
                throw new RuntimeException("miAppKey not set");
            } else if (!this.G) {
                throw new RuntimeException("miRedirectUrl not set");
            } else if (this.H) {
                return new HostSetting(this);
            } else {
                throw new RuntimeException("wxAppID not set");
            }
        }
    }
}
