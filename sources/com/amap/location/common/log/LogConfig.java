package com.amap.location.common.log;

public class LogConfig {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f4577a;
    /* access modifiers changed from: private */
    public boolean b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public Product e;
    /* access modifiers changed from: private */
    public a f;
    /* access modifiers changed from: private */
    public boolean g;
    /* access modifiers changed from: private */
    public boolean h;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public int k;

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private LogConfig f4578a = new LogConfig();

        public Builder a(int i) {
            int unused = this.f4578a.i = i;
            return this;
        }

        public Builder a(a aVar) {
            a unused = this.f4578a.f = aVar;
            return this;
        }

        public Builder a(boolean z) {
            boolean unused = this.f4578a.f4577a = z;
            return this;
        }

        public LogConfig a(Product product, String str) {
            if (product != null) {
                if (this.f4578a.b && (str == null || str.trim().length() == 0)) {
                    boolean unused = this.f4578a.b = false;
                    str = null;
                }
                Product unused2 = this.f4578a.e = product;
                String unused3 = this.f4578a.d = str;
                return this.f4578a;
            }
            throw new IllegalArgumentException("product 不能为 null ");
        }

        public Builder b(int i) {
            int unused = this.f4578a.j = i;
            return this;
        }

        public Builder b(boolean z) {
            boolean unused = this.f4578a.b = z;
            return this;
        }

        public Builder c(int i) {
            int unused = this.f4578a.k = i;
            return this;
        }

        public Builder c(boolean z) {
            boolean unused = this.f4578a.c = z;
            return this;
        }

        public Builder d(boolean z) {
            boolean unused = this.f4578a.g = z;
            return this;
        }

        public Builder e(boolean z) {
            boolean unused = this.f4578a.h = z;
            return this;
        }
    }

    public enum Product {
        FLP,
        NLP,
        SDK
    }

    public interface a {
        void a(String str);

        boolean a();
    }

    private LogConfig() {
        this.f4577a = false;
        this.b = false;
        this.c = false;
        this.d = "";
        this.e = Product.SDK;
        this.g = false;
        this.h = true;
        this.i = 204800;
        this.j = 1048576;
        this.k = 20;
    }

    public boolean a() {
        return this.f4577a;
    }

    public boolean b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.h;
    }

    public boolean e() {
        return this.g;
    }

    public int f() {
        return this.k;
    }

    public int g() {
        return this.i;
    }

    public int h() {
        return this.j;
    }

    public String i() {
        return this.d;
    }

    public Product j() {
        return this.e;
    }

    public a k() {
        return this.f;
    }
}
