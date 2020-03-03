package com.xiaomi.push;

public final class em {

    public static final class a extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12712a;
        private int b = 0;
        private boolean c;
        private long d = 0;
        private boolean e;
        private String f = "";
        private boolean g;
        private String h = "";
        private boolean i;
        private String j = "";
        private boolean k;
        private String l = "";
        private boolean m;
        private String n = "";
        private boolean o;
        private int p = 1;
        private boolean q;
        private int r = 0;
        private boolean s;
        private int t = 0;
        private boolean u;
        private String v = "";
        private int w = -1;

        public int a() {
            if (this.w < 0) {
                b();
            }
            return this.w;
        }

        public a a(int i2) {
            this.f12712a = true;
            this.b = i2;
            return this;
        }

        public a a(long j2) {
            this.c = true;
            this.d = j2;
            return this;
        }

        public a a(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.b(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
            if (k()) {
                cVar.a(4, j());
            }
            if (m()) {
                cVar.a(5, l());
            }
            if (o()) {
                cVar.a(6, n());
            }
            if (r()) {
                cVar.a(7, q());
            }
            if (s()) {
                cVar.a(8, t());
            }
            if (v()) {
                cVar.a(9, u());
            }
            if (x()) {
                cVar.a(10, w());
            }
            if (z()) {
                cVar.a(11, y());
            }
        }

        public int b() {
            int i2 = 0;
            if (e()) {
                i2 = 0 + c.c(1, d());
            }
            if (g()) {
                i2 += c.d(2, f());
            }
            if (i()) {
                i2 += c.b(3, h());
            }
            if (k()) {
                i2 += c.b(4, j());
            }
            if (m()) {
                i2 += c.b(5, l());
            }
            if (o()) {
                i2 += c.b(6, n());
            }
            if (r()) {
                i2 += c.b(7, q());
            }
            if (s()) {
                i2 += c.c(8, t());
            }
            if (v()) {
                i2 += c.c(9, u());
            }
            if (x()) {
                i2 += c.c(10, w());
            }
            if (z()) {
                i2 += c.b(11, y());
            }
            this.w = i2;
            return i2;
        }

        public a b(int i2) {
            this.o = true;
            this.p = i2;
            return this;
        }

        /* renamed from: b */
        public a a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                switch (a2) {
                    case 0:
                        return this;
                    case 8:
                        a(bVar.e());
                        break;
                    case 16:
                        a(bVar.d());
                        break;
                    case 26:
                        a(bVar.g());
                        break;
                    case 34:
                        b(bVar.g());
                        break;
                    case 42:
                        c(bVar.g());
                        break;
                    case 50:
                        d(bVar.g());
                        break;
                    case 58:
                        e(bVar.g());
                        break;
                    case 64:
                        b(bVar.e());
                        break;
                    case 72:
                        c(bVar.e());
                        break;
                    case 80:
                        d(bVar.e());
                        break;
                    case 90:
                        f(bVar.g());
                        break;
                    default:
                        if (a(bVar, a2)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public a b(String str) {
            this.g = true;
            this.h = str;
            return this;
        }

        public a c(int i2) {
            this.q = true;
            this.r = i2;
            return this;
        }

        public a c(String str) {
            this.i = true;
            this.j = str;
            return this;
        }

        public int d() {
            return this.b;
        }

        public a d(int i2) {
            this.s = true;
            this.t = i2;
            return this;
        }

        public a d(String str) {
            this.k = true;
            this.l = str;
            return this;
        }

        public a e(String str) {
            this.m = true;
            this.n = str;
            return this;
        }

        public boolean e() {
            return this.f12712a;
        }

        public long f() {
            return this.d;
        }

        public a f(String str) {
            this.u = true;
            this.v = str;
            return this;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public String j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public String l() {
            return this.j;
        }

        public boolean m() {
            return this.i;
        }

        public String n() {
            return this.l;
        }

        public boolean o() {
            return this.k;
        }

        public a p() {
            this.k = false;
            this.l = "";
            return this;
        }

        public String q() {
            return this.n;
        }

        public boolean r() {
            return this.m;
        }

        public boolean s() {
            return this.o;
        }

        public int t() {
            return this.p;
        }

        public int u() {
            return this.r;
        }

        public boolean v() {
            return this.q;
        }

        public int w() {
            return this.t;
        }

        public boolean x() {
            return this.s;
        }

        public String y() {
            return this.v;
        }

        public boolean z() {
            return this.u;
        }
    }

    public static final class b extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12713a;
        private boolean b = false;
        private boolean c;
        private int d = 0;
        private boolean e;
        private int f = 0;
        private boolean g;
        private int h = 0;
        private int i = -1;

        public static b b(byte[] bArr) {
            return (b) new b().a(bArr);
        }

        public int a() {
            if (this.i < 0) {
                b();
            }
            return this.i;
        }

        public b a(int i2) {
            this.c = true;
            this.d = i2;
            return this;
        }

        public b a(boolean z) {
            this.f12713a = true;
            this.b = z;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(3, f());
            }
            if (i()) {
                cVar.a(4, h());
            }
            if (k()) {
                cVar.a(5, j());
            }
        }

        public int b() {
            int i2 = 0;
            if (e()) {
                i2 = 0 + c.b(1, d());
            }
            if (g()) {
                i2 += c.c(3, f());
            }
            if (i()) {
                i2 += c.c(4, h());
            }
            if (k()) {
                i2 += c.c(5, j());
            }
            this.i = i2;
            return i2;
        }

        public b b(int i2) {
            this.e = true;
            this.f = i2;
            return this;
        }

        /* renamed from: b */
        public b a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 8) {
                    a(bVar.f());
                } else if (a2 == 24) {
                    a(bVar.e());
                } else if (a2 == 32) {
                    b(bVar.e());
                } else if (a2 == 40) {
                    c(bVar.e());
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public b c(int i2) {
            this.g = true;
            this.h = i2;
            return this;
        }

        public boolean d() {
            return this.b;
        }

        public boolean e() {
            return this.f12713a;
        }

        public int f() {
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

        public int j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }
    }

    public static final class c extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12714a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private boolean g;
        private String h = "";
        private boolean i;
        private String j = "";
        private boolean k;
        private String l = "";
        private int m = -1;

        public int a() {
            if (this.m < 0) {
                b();
            }
            return this.m;
        }

        public c a(String str) {
            this.f12714a = true;
            this.b = str;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
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
            if (m()) {
                cVar.a(5, l());
            }
            if (o()) {
                cVar.a(6, n());
            }
        }

        public int b() {
            int i2 = 0;
            if (e()) {
                i2 = 0 + c.b(1, d());
            }
            if (g()) {
                i2 += c.b(2, f());
            }
            if (i()) {
                i2 += c.b(3, h());
            }
            if (k()) {
                i2 += c.b(4, j());
            }
            if (m()) {
                i2 += c.b(5, l());
            }
            if (o()) {
                i2 += c.b(6, n());
            }
            this.m = i2;
            return i2;
        }

        /* renamed from: b */
        public c a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 10) {
                    a(bVar.g());
                } else if (a2 == 18) {
                    b(bVar.g());
                } else if (a2 == 26) {
                    c(bVar.g());
                } else if (a2 == 34) {
                    d(bVar.g());
                } else if (a2 == 42) {
                    e(bVar.g());
                } else if (a2 == 50) {
                    f(bVar.g());
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public c b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public c c(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public c d(String str) {
            this.g = true;
            this.h = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public c e(String str) {
            this.i = true;
            this.j = str;
            return this;
        }

        public boolean e() {
            return this.f12714a;
        }

        public c f(String str) {
            this.k = true;
            this.l = str;
            return this;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public String j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public String l() {
            return this.j;
        }

        public boolean m() {
            return this.i;
        }

        public String n() {
            return this.l;
        }

        public boolean o() {
            return this.k;
        }
    }

    public static final class d extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12715a;
        private boolean b = false;
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private boolean g;
        private String h = "";
        private int i = -1;

        public static d b(byte[] bArr) {
            return (d) new d().a(bArr);
        }

        public int a() {
            if (this.i < 0) {
                b();
            }
            return this.i;
        }

        public d a(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public d a(boolean z) {
            this.f12715a = true;
            this.b = z;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
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
        }

        public int b() {
            int i2 = 0;
            if (e()) {
                i2 = 0 + c.b(1, d());
            }
            if (g()) {
                i2 += c.b(2, f());
            }
            if (i()) {
                i2 += c.b(3, h());
            }
            if (k()) {
                i2 += c.b(4, j());
            }
            this.i = i2;
            return i2;
        }

        /* renamed from: b */
        public d a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 8) {
                    a(bVar.f());
                } else if (a2 == 18) {
                    a(bVar.g());
                } else if (a2 == 26) {
                    b(bVar.g());
                } else if (a2 == 34) {
                    c(bVar.g());
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public d b(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public d c(String str) {
            this.g = true;
            this.h = str;
            return this;
        }

        public boolean d() {
            return this.b;
        }

        public boolean e() {
            return this.f12715a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public String j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }
    }

    public static final class e extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12716a;
        private int b = 0;
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private boolean g;
        private String h = "";
        private boolean i;
        private int j = 0;
        private boolean k;
        private String l = "";
        private boolean m;
        private String n = "";
        private boolean o;
        private String p = "";
        private boolean q;
        private b r = null;
        private boolean s;
        private int t = 0;
        private int u = -1;

        public int a() {
            if (this.u < 0) {
                b();
            }
            return this.u;
        }

        public e a(int i2) {
            this.f12716a = true;
            this.b = i2;
            return this;
        }

        public e a(b bVar) {
            if (bVar != null) {
                this.q = true;
                this.r = bVar;
                return this;
            }
            throw new NullPointerException();
        }

        public e a(String str) {
            this.c = true;
            this.d = str;
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
            if (m()) {
                cVar.a(5, l());
            }
            if (o()) {
                cVar.a(6, n());
            }
            if (q()) {
                cVar.a(7, p());
            }
            if (s()) {
                cVar.a(8, r());
            }
            if (t()) {
                cVar.a(9, (e) u());
            }
            if (w()) {
                cVar.a(10, v());
            }
        }

        public int b() {
            int i2 = 0;
            if (e()) {
                i2 = 0 + c.d(1, d());
            }
            if (g()) {
                i2 += c.b(2, f());
            }
            if (i()) {
                i2 += c.b(3, h());
            }
            if (k()) {
                i2 += c.b(4, j());
            }
            if (m()) {
                i2 += c.c(5, l());
            }
            if (o()) {
                i2 += c.b(6, n());
            }
            if (q()) {
                i2 += c.b(7, p());
            }
            if (s()) {
                i2 += c.b(8, r());
            }
            if (t()) {
                i2 += c.b(9, (e) u());
            }
            if (w()) {
                i2 += c.c(10, v());
            }
            this.u = i2;
            return i2;
        }

        public e b(int i2) {
            this.i = true;
            this.j = i2;
            return this;
        }

        /* renamed from: b */
        public e a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                switch (a2) {
                    case 0:
                        return this;
                    case 8:
                        a(bVar.i());
                        break;
                    case 18:
                        a(bVar.g());
                        break;
                    case 26:
                        b(bVar.g());
                        break;
                    case 34:
                        c(bVar.g());
                        break;
                    case 40:
                        b(bVar.e());
                        break;
                    case 50:
                        d(bVar.g());
                        break;
                    case 58:
                        e(bVar.g());
                        break;
                    case 66:
                        f(bVar.g());
                        break;
                    case 74:
                        b bVar2 = new b();
                        bVar.a((e) bVar2);
                        a(bVar2);
                        break;
                    case 80:
                        c(bVar.e());
                        break;
                    default:
                        if (a(bVar, a2)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }

        public e b(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public e c(int i2) {
            this.s = true;
            this.t = i2;
            return this;
        }

        public e c(String str) {
            this.g = true;
            this.h = str;
            return this;
        }

        public int d() {
            return this.b;
        }

        public e d(String str) {
            this.k = true;
            this.l = str;
            return this;
        }

        public e e(String str) {
            this.m = true;
            this.n = str;
            return this;
        }

        public boolean e() {
            return this.f12716a;
        }

        public e f(String str) {
            this.o = true;
            this.p = str;
            return this;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public String j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public int l() {
            return this.j;
        }

        public boolean m() {
            return this.i;
        }

        public String n() {
            return this.l;
        }

        public boolean o() {
            return this.k;
        }

        public String p() {
            return this.n;
        }

        public boolean q() {
            return this.m;
        }

        public String r() {
            return this.p;
        }

        public boolean s() {
            return this.o;
        }

        public boolean t() {
            return this.q;
        }

        public b u() {
            return this.r;
        }

        public int v() {
            return this.t;
        }

        public boolean w() {
            return this.s;
        }
    }

    public static final class f extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12717a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private b f = null;
        private int g = -1;

        public static f b(byte[] bArr) {
            return (f) new f().a(bArr);
        }

        public int a() {
            if (this.g < 0) {
                b();
            }
            return this.g;
        }

        public f a(b bVar) {
            if (bVar != null) {
                this.e = true;
                this.f = bVar;
                return this;
            }
            throw new NullPointerException();
        }

        public f a(String str) {
            this.f12717a = true;
            this.b = str;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (h()) {
                cVar.a(3, (e) i());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            if (g()) {
                i += c.b(2, f());
            }
            if (h()) {
                i += c.b(3, (e) i());
            }
            this.g = i;
            return i;
        }

        /* renamed from: b */
        public f a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 10) {
                    a(bVar.g());
                } else if (a2 == 18) {
                    b(bVar.g());
                } else if (a2 == 26) {
                    b bVar2 = new b();
                    bVar.a((e) bVar2);
                    a(bVar2);
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public f b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public boolean e() {
            return this.f12717a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public boolean h() {
            return this.e;
        }

        public b i() {
            return this.f;
        }
    }

    public static final class g extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12718a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private int g = -1;

        public static g b(byte[] bArr) {
            return (g) new g().a(bArr);
        }

        public int a() {
            if (this.g < 0) {
                b();
            }
            return this.g;
        }

        public g a(String str) {
            this.f12718a = true;
            this.b = str;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            if (g()) {
                i += c.b(2, f());
            }
            if (i()) {
                i += c.b(3, h());
            }
            this.g = i;
            return i;
        }

        /* renamed from: b */
        public g a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 10) {
                    a(bVar.g());
                } else if (a2 == 18) {
                    b(bVar.g());
                } else if (a2 == 26) {
                    c(bVar.g());
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public g b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public g c(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public boolean e() {
            return this.f12718a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }
    }

    public static final class h extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12719a;
        private int b = 0;
        private boolean c;
        private String d = "";
        private int e = -1;

        public static h b(byte[] bArr) {
            return (h) new h().a(bArr);
        }

        public int a() {
            if (this.e < 0) {
                b();
            }
            return this.e;
        }

        public h a(int i) {
            this.f12719a = true;
            this.b = i;
            return this;
        }

        public h a(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.c(1, d());
            }
            if (g()) {
                i += c.b(2, f());
            }
            this.e = i;
            return i;
        }

        /* renamed from: b */
        public h a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 8) {
                    a(bVar.e());
                } else if (a2 == 18) {
                    a(bVar.g());
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public int d() {
            return this.b;
        }

        public boolean e() {
            return this.f12719a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }
    }

    public static final class i extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12720a;
        private a b = a.f12621a;
        private int c = -1;

        public static i b(byte[] bArr) {
            return (i) new i().a(bArr);
        }

        public int a() {
            if (this.c < 0) {
                b();
            }
            return this.c;
        }

        public i a(a aVar) {
            this.f12720a = true;
            this.b = aVar;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            this.c = i;
            return i;
        }

        /* renamed from: b */
        public i a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 10) {
                    a(bVar.h());
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public a d() {
            return this.b;
        }

        public boolean e() {
            return this.f12720a;
        }
    }

    public static final class j extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12721a;
        private a b = a.f12621a;
        private boolean c;
        private b d = null;
        private int e = -1;

        public static j b(byte[] bArr) {
            return (j) new j().a(bArr);
        }

        public int a() {
            if (this.e < 0) {
                b();
            }
            return this.e;
        }

        public j a(a aVar) {
            this.f12721a = true;
            this.b = aVar;
            return this;
        }

        public j a(b bVar) {
            if (bVar != null) {
                this.c = true;
                this.d = bVar;
                return this;
            }
            throw new NullPointerException();
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (f()) {
                cVar.a(2, (e) g());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + c.b(1, d());
            }
            if (f()) {
                i += c.b(2, (e) g());
            }
            this.e = i;
            return i;
        }

        /* renamed from: b */
        public j a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 10) {
                    a(bVar.h());
                } else if (a2 == 18) {
                    b bVar2 = new b();
                    bVar.a((e) bVar2);
                    a(bVar2);
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public a d() {
            return this.b;
        }

        public boolean e() {
            return this.f12721a;
        }

        public boolean f() {
            return this.c;
        }

        public b g() {
            return this.d;
        }
    }

    public static final class k extends e {

        /* renamed from: a  reason: collision with root package name */
        private boolean f12722a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private long f = 0;
        private boolean g;
        private long h = 0;
        private boolean i;
        private boolean j = false;
        private boolean k;
        private int l = 0;
        private int m = -1;

        public static k b(byte[] bArr) {
            return (k) new k().a(bArr);
        }

        public int a() {
            if (this.m < 0) {
                b();
            }
            return this.m;
        }

        public k a(int i2) {
            this.k = true;
            this.l = i2;
            return this;
        }

        public k a(long j2) {
            this.e = true;
            this.f = j2;
            return this;
        }

        public k a(String str) {
            this.f12722a = true;
            this.b = str;
            return this;
        }

        public k a(boolean z) {
            this.i = true;
            this.j = z;
            return this;
        }

        public void a(c cVar) {
            if (e()) {
                cVar.a(1, d());
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
            if (m()) {
                cVar.a(5, l());
            }
            if (o()) {
                cVar.a(6, n());
            }
        }

        public int b() {
            int i2 = 0;
            if (e()) {
                i2 = 0 + c.b(1, d());
            }
            if (g()) {
                i2 += c.b(2, f());
            }
            if (i()) {
                i2 += c.c(3, h());
            }
            if (k()) {
                i2 += c.c(4, j());
            }
            if (m()) {
                i2 += c.b(5, l());
            }
            if (o()) {
                i2 += c.c(6, n());
            }
            this.m = i2;
            return i2;
        }

        public k b(long j2) {
            this.g = true;
            this.h = j2;
            return this;
        }

        /* renamed from: b */
        public k a(b bVar) {
            while (true) {
                int a2 = bVar.a();
                if (a2 == 0) {
                    return this;
                }
                if (a2 == 10) {
                    a(bVar.g());
                } else if (a2 == 18) {
                    b(bVar.g());
                } else if (a2 == 24) {
                    a(bVar.c());
                } else if (a2 == 32) {
                    b(bVar.c());
                } else if (a2 == 40) {
                    a(bVar.f());
                } else if (a2 == 48) {
                    a(bVar.e());
                } else if (!a(bVar, a2)) {
                    return this;
                }
            }
        }

        public k b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public boolean e() {
            return this.f12722a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public long h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public long j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public boolean l() {
            return this.j;
        }

        public boolean m() {
            return this.i;
        }

        public int n() {
            return this.l;
        }

        public boolean o() {
            return this.k;
        }
    }
}
