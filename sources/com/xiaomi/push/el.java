package com.xiaomi.push;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class el {

    public static final class a extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12711a;
        private int b = 0;
        private boolean c;
        private boolean d = false;
        private boolean e;
        private int f = 0;
        private boolean g;
        private boolean h = false;
        private List<String> i = Collections.emptyList();
        private int j = -1;

        public static a b(byte[] bArr) {
            return (a) new a().a(bArr);
        }

        public static a c(b bVar) {
            return new a().a(bVar);
        }

        public int a() {
            if (this.j < 0) {
                b();
            }
            return this.j;
        }

        public a a(int i2) {
            this.f12711a = true;
            this.b = i2;
            return this;
        }

        public a a(String str) {
            if (str != null) {
                if (this.i.isEmpty()) {
                    this.i = new ArrayList();
                }
                this.i.add(str);
                return this;
            }
            throw new NullPointerException();
        }

        public a a(boolean z) {
            this.c = true;
            this.d = z;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.b(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
            if (k()) {
                cVar.a(4, j());
            }
            for (String a2 : l()) {
                cVar.a(5, a2);
            }
        }

        public int b() {
            int i2 = 0;
            int d2 = e() ? c.d(1, d()) + 0 : 0;
            if (g()) {
                d2 += c.b(2, f());
            }
            if (i()) {
                d2 += c.c(3, h());
            }
            if (k()) {
                d2 += c.b(4, j());
            }
            for (String b2 : l()) {
                i2 += c.b(b2);
            }
            int size = d2 + i2 + (l().size() * 1);
            this.j = size;
            return size;
        }

        public a b(int i2) {
            this.e = true;
            this.f = i2;
            return this;
        }

        /* renamed from: b */
        public a a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 8) {
                    a(bVar.i());
                } else if (a2 == 16) {
                    a(bVar.f());
                } else if (a2 == 24) {
                    b(bVar.e());
                } else if (a2 == 32) {
                    b(bVar.f());
                } else if (a2 == 42) {
                    a(bVar.g());
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public a b(boolean z) {
            this.g = true;
            this.h = z;
            return this;
        }

        public int d() {
            return this.b;
        }

        public boolean e() {
            return this.f12711a;
        }

        public boolean f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public int h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public boolean j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public List<String> l() {
            return this.i;
        }

        public int m() {
            return this.i.size();
        }
    }
}
