package com.xiaomi.shopviews.adapter.countdown;

public class DynamicConfig {

    /* renamed from: a  reason: collision with root package name */
    private Builder f13100a;

    public static class SuffixGravity {

        /* renamed from: a  reason: collision with root package name */
        public static final int f13103a = 0;
        public static final int b = 1;
        public static final int c = 2;
    }

    private DynamicConfig(Builder builder) {
        this.f13100a = builder;
    }

    public Float a() {
        return this.f13100a.f13102a;
    }

    public Integer b() {
        return this.f13100a.b;
    }

    public Boolean c() {
        return this.f13100a.c;
    }

    public Float d() {
        return this.f13100a.d;
    }

    public Integer e() {
        return this.f13100a.e;
    }

    public Boolean f() {
        return this.f13100a.g;
    }

    public String g() {
        return this.f13100a.o;
    }

    public String h() {
        return this.f13100a.p;
    }

    public String i() {
        return this.f13100a.q;
    }

    public String j() {
        return this.f13100a.r;
    }

    public String k() {
        return this.f13100a.s;
    }

    public String l() {
        return this.f13100a.t;
    }

    public Integer m() {
        return this.f13100a.f;
    }

    public Float n() {
        return this.f13100a.u;
    }

    public Float o() {
        return this.f13100a.v;
    }

    public Float p() {
        return this.f13100a.w;
    }

    public Float q() {
        return this.f13100a.z;
    }

    public Float r() {
        return this.f13100a.A;
    }

    public Float s() {
        return this.f13100a.B;
    }

    public Float t() {
        return this.f13100a.C;
    }

    public Float u() {
        return this.f13100a.x;
    }

    public Float v() {
        return this.f13100a.y;
    }

    public Float w() {
        return this.f13100a.D;
    }

    public Boolean x() {
        return Boolean.valueOf(this.f13100a.m);
    }

    public Boolean y() {
        return this.f13100a.h;
    }

    public Boolean z() {
        return this.f13100a.i;
    }

    public Boolean A() {
        return this.f13100a.j;
    }

    public Boolean B() {
        return this.f13100a.k;
    }

    public Boolean C() {
        return this.f13100a.l;
    }

    public BackgroundInfo D() {
        return this.f13100a.n;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public Float A;
        /* access modifiers changed from: private */
        public Float B;
        /* access modifiers changed from: private */
        public Float C;
        /* access modifiers changed from: private */
        public Float D;
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Float f13102a;
        /* access modifiers changed from: private */
        public Integer b;
        /* access modifiers changed from: private */
        public Boolean c;
        /* access modifiers changed from: private */
        public Float d;
        /* access modifiers changed from: private */
        public Integer e;
        /* access modifiers changed from: private */
        public Integer f;
        /* access modifiers changed from: private */
        public Boolean g;
        /* access modifiers changed from: private */
        public Boolean h;
        /* access modifiers changed from: private */
        public Boolean i;
        /* access modifiers changed from: private */
        public Boolean j;
        /* access modifiers changed from: private */
        public Boolean k;
        /* access modifiers changed from: private */
        public Boolean l;
        /* access modifiers changed from: private */
        public boolean m;
        /* access modifiers changed from: private */
        public BackgroundInfo n;
        /* access modifiers changed from: private */
        public String o;
        /* access modifiers changed from: private */
        public String p;
        /* access modifiers changed from: private */
        public String q;
        /* access modifiers changed from: private */
        public String r;
        /* access modifiers changed from: private */
        public String s;
        /* access modifiers changed from: private */
        public String t;
        /* access modifiers changed from: private */
        public Float u;
        /* access modifiers changed from: private */
        public Float v;
        /* access modifiers changed from: private */
        public Float w;
        /* access modifiers changed from: private */
        public Float x;
        /* access modifiers changed from: private */
        public Float y;
        /* access modifiers changed from: private */
        public Float z;

        public Builder a(float f2) {
            this.f13102a = Float.valueOf(f2);
            return this;
        }

        public Builder a(int i2) {
            this.b = Integer.valueOf(i2);
            return this;
        }

        public Builder a(boolean z2) {
            this.c = Boolean.valueOf(z2);
            return this;
        }

        public Builder b(float f2) {
            this.d = Float.valueOf(f2);
            return this;
        }

        public Builder b(int i2) {
            this.e = Integer.valueOf(i2);
            return this;
        }

        public Builder b(boolean z2) {
            this.g = Boolean.valueOf(z2);
            return this;
        }

        public Builder a(String str) {
            this.o = str;
            return this;
        }

        public Builder b(String str) {
            this.p = str;
            return this;
        }

        public Builder c(String str) {
            this.q = str;
            return this;
        }

        public Builder d(String str) {
            this.r = str;
            return this;
        }

        public Builder e(String str) {
            this.s = str;
            return this;
        }

        public Builder f(String str) {
            this.t = str;
            return this;
        }

        public Builder c(float f2) {
            this.u = Float.valueOf(f2);
            return this;
        }

        public Builder d(float f2) {
            this.v = Float.valueOf(f2);
            return this;
        }

        public Builder e(float f2) {
            this.w = Float.valueOf(f2);
            return this;
        }

        public Builder f(float f2) {
            this.z = Float.valueOf(f2);
            return this;
        }

        public Builder g(float f2) {
            this.A = Float.valueOf(f2);
            return this;
        }

        public Builder h(float f2) {
            this.B = Float.valueOf(f2);
            return this;
        }

        public Builder i(float f2) {
            this.C = Float.valueOf(f2);
            return this;
        }

        public Builder j(float f2) {
            this.x = Float.valueOf(f2);
            return this;
        }

        public Builder k(float f2) {
            this.y = Float.valueOf(f2);
            return this;
        }

        public Builder l(float f2) {
            this.D = Float.valueOf(f2);
            return this;
        }

        public Builder c(int i2) {
            this.f = Integer.valueOf(i2);
            return this;
        }

        public Builder a(Boolean bool) {
            this.m = bool.booleanValue();
            return this;
        }

        public Builder b(Boolean bool) {
            this.h = bool;
            return this;
        }

        public Builder c(Boolean bool) {
            this.i = bool;
            return this;
        }

        public Builder d(Boolean bool) {
            this.j = bool;
            return this;
        }

        public Builder e(Boolean bool) {
            this.k = bool;
            return this;
        }

        public Builder f(Boolean bool) {
            this.l = bool;
            return this;
        }

        public Builder a(BackgroundInfo backgroundInfo) {
            this.n = backgroundInfo;
            return this;
        }

        private void b() {
            if (this.f13102a != null && this.f13102a.floatValue() <= 0.0f) {
                this.f13102a = null;
            }
            if (this.d != null && this.d.floatValue() <= 0.0f) {
                this.d = null;
            }
            if (this.n != null && !this.n.f13101a) {
                this.n = null;
            }
            if (this.n != null) {
                Boolean d2 = this.n.d();
                if (d2 == null || !d2.booleanValue()) {
                    this.n.b((Integer) null);
                    this.n.c((Float) null);
                }
                Boolean g2 = this.n.g();
                if (g2 == null || !g2.booleanValue()) {
                    this.n.c((Integer) null);
                    this.n.e((Float) null);
                    this.n.d((Float) null);
                }
                if (this.n.f() != null && this.n.f().floatValue() <= 0.0f) {
                    this.n.a((Float) null);
                }
            }
            if (this.f == null) {
                return;
            }
            if (this.f.intValue() < 0 || this.f.intValue() > 2) {
                this.f = null;
            }
        }

        public DynamicConfig a() {
            b();
            return new DynamicConfig(this);
        }
    }

    public static class BackgroundInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public boolean f13101a = false;
        private Integer b;
        private Float c;
        private Float d;
        private Boolean e;
        private Integer f;
        private Float g;
        private Boolean h;
        private Integer i;
        private Float j;
        private Float k;

        public BackgroundInfo a(Integer num) {
            this.f13101a = true;
            this.b = num;
            return this;
        }

        public BackgroundInfo a(Float f2) {
            this.f13101a = true;
            this.c = f2;
            return this;
        }

        public BackgroundInfo b(Float f2) {
            this.f13101a = true;
            this.d = f2;
            return this;
        }

        public BackgroundInfo a(Boolean bool) {
            this.f13101a = true;
            this.e = bool;
            return this;
        }

        public BackgroundInfo c(Float f2) {
            this.f13101a = true;
            this.g = f2;
            return this;
        }

        public BackgroundInfo b(Integer num) {
            this.f13101a = true;
            this.f = num;
            return this;
        }

        public BackgroundInfo b(Boolean bool) {
            this.f13101a = true;
            this.h = bool;
            return this;
        }

        public BackgroundInfo d(Float f2) {
            this.f13101a = true;
            this.k = f2;
            return this;
        }

        public BackgroundInfo c(Integer num) {
            this.f13101a = true;
            this.i = num;
            return this;
        }

        public BackgroundInfo e(Float f2) {
            this.f13101a = true;
            this.j = f2;
            return this;
        }

        public Integer a() {
            return this.b;
        }

        public Integer b() {
            return this.f;
        }

        public Float c() {
            return this.g;
        }

        public Boolean d() {
            return this.e;
        }

        public Float e() {
            return this.d;
        }

        public Float f() {
            return this.c;
        }

        public Boolean g() {
            return this.h;
        }

        public Integer h() {
            return this.i;
        }

        public Float i() {
            return this.k;
        }

        public Float j() {
            return this.j;
        }
    }
}
