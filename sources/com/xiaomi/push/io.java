package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Map;

public class io implements iz<io, Object>, Serializable, Cloneable {
    private static final jh A = new jh("", (byte) 11, 8);
    private static final jh B = new jh("", (byte) 11, 9);
    private static final jh C = new jh("", (byte) 11, 10);
    private static final jh D = new jh("", (byte) 11, 11);
    private static final jh E = new jh("", (byte) 11, 12);
    private static final jh F = new jh("", (byte) 8, 13);
    private static final jh G = new jh("", (byte) 8, 14);
    private static final jh H = new jh("", (byte) 11, 15);
    private static final jh I = new jh("", (byte) 11, 16);
    private static final jh J = new jh("", (byte) 11, 17);
    private static final jh K = new jh("", (byte) 11, 18);
    private static final jh L = new jh("", (byte) 8, 19);
    private static final jh M = new jh("", (byte) 8, 20);
    private static final jh N = new jh("", (byte) 2, 21);
    private static final jh O = new jh("", (byte) 10, 22);
    private static final jh P = new jh("", (byte) 10, 23);
    private static final jh Q = new jh("", (byte) 11, 24);
    private static final jh R = new jh("", (byte) 11, 25);
    private static final jh S = new jh("", (byte) 13, 100);
    private static final jh T = new jh("", (byte) 2, 101);
    private static final jh U = new jh("", (byte) 11, 102);
    private static final jp s = new jp("XmPushActionRegistration");
    private static final jh t = new jh("", (byte) 11, 1);
    private static final jh u = new jh("", (byte) 12, 2);
    private static final jh v = new jh("", (byte) 11, 3);
    private static final jh w = new jh("", (byte) 11, 4);
    private static final jh x = new jh("", (byte) 11, 5);
    private static final jh y = new jh("", (byte) 11, 6);
    private static final jh z = new jh("", (byte) 11, 7);

    /* renamed from: a  reason: collision with root package name */
    public int f12807a;

    /* renamed from: a  reason: collision with other field name */
    public long f180a;

    /* renamed from: a  reason: collision with other field name */
    public ic f181a;

    /* renamed from: a  reason: collision with other field name */
    public id f182a;

    /* renamed from: a  reason: collision with other field name */
    public String f183a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f184a = new BitSet(7);

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f185a;

    /* renamed from: a  reason: collision with other field name */
    public boolean f186a = true;
    public int b;

    /* renamed from: b  reason: collision with other field name */
    public long f187b;

    /* renamed from: b  reason: collision with other field name */
    public String f188b;

    /* renamed from: b  reason: collision with other field name */
    public boolean f189b = false;
    public int c;

    /* renamed from: c  reason: collision with other field name */
    public String f190c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;

    public boolean A() {
        return this.f184a.get(6);
    }

    public boolean B() {
        return this.r != null;
    }

    /* renamed from: a */
    public int compareTo(io ioVar) {
        int a2;
        int a3;
        int a4;
        int a5;
        int a6;
        int a7;
        int a8;
        int a9;
        int a10;
        int a11;
        int a12;
        int a13;
        int a14;
        int a15;
        int a16;
        int a17;
        int a18;
        int a19;
        int a20;
        int a21;
        int a22;
        int a23;
        int a24;
        int a25;
        int a26;
        int a27;
        int a28;
        int a29;
        if (!getClass().equals(ioVar.getClass())) {
            return getClass().getName().compareTo(ioVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ioVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a29 = ja.a(this.f183a, ioVar.f183a)) != 0) {
            return a29;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ioVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a28 = ja.a((Comparable) this.f182a, (Comparable) ioVar.f182a)) != 0) {
            return a28;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ioVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a27 = ja.a(this.f188b, ioVar.f188b)) != 0) {
            return a27;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ioVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a26 = ja.a(this.f190c, ioVar.f190c)) != 0) {
            return a26;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ioVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a25 = ja.a(this.d, ioVar.d)) != 0) {
            return a25;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ioVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a24 = ja.a(this.e, ioVar.e)) != 0) {
            return a24;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ioVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a23 = ja.a(this.f, ioVar.f)) != 0) {
            return a23;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ioVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a22 = ja.a(this.g, ioVar.g)) != 0) {
            return a22;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ioVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a21 = ja.a(this.h, ioVar.h)) != 0) {
            return a21;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ioVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (j() && (a20 = ja.a(this.i, ioVar.i)) != 0) {
            return a20;
        }
        int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(ioVar.k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (k() && (a19 = ja.a(this.j, ioVar.j)) != 0) {
            return a19;
        }
        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(ioVar.l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (l() && (a18 = ja.a(this.k, ioVar.k)) != 0) {
            return a18;
        }
        int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(ioVar.m()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (m() && (a17 = ja.a(this.f12807a, ioVar.f12807a)) != 0) {
            return a17;
        }
        int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(ioVar.n()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (n() && (a16 = ja.a(this.b, ioVar.b)) != 0) {
            return a16;
        }
        int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(ioVar.o()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (o() && (a15 = ja.a(this.l, ioVar.l)) != 0) {
            return a15;
        }
        int compareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(ioVar.p()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (p() && (a14 = ja.a(this.m, ioVar.m)) != 0) {
            return a14;
        }
        int compareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(ioVar.q()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (q() && (a13 = ja.a(this.n, ioVar.n)) != 0) {
            return a13;
        }
        int compareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(ioVar.r()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (r() && (a12 = ja.a(this.o, ioVar.o)) != 0) {
            return a12;
        }
        int compareTo19 = Boolean.valueOf(s()).compareTo(Boolean.valueOf(ioVar.s()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (s() && (a11 = ja.a(this.c, ioVar.c)) != 0) {
            return a11;
        }
        int compareTo20 = Boolean.valueOf(t()).compareTo(Boolean.valueOf(ioVar.t()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (t() && (a10 = ja.a((Comparable) this.f181a, (Comparable) ioVar.f181a)) != 0) {
            return a10;
        }
        int compareTo21 = Boolean.valueOf(u()).compareTo(Boolean.valueOf(ioVar.u()));
        if (compareTo21 != 0) {
            return compareTo21;
        }
        if (u() && (a9 = ja.a(this.f186a, ioVar.f186a)) != 0) {
            return a9;
        }
        int compareTo22 = Boolean.valueOf(v()).compareTo(Boolean.valueOf(ioVar.v()));
        if (compareTo22 != 0) {
            return compareTo22;
        }
        if (v() && (a8 = ja.a(this.f180a, ioVar.f180a)) != 0) {
            return a8;
        }
        int compareTo23 = Boolean.valueOf(w()).compareTo(Boolean.valueOf(ioVar.w()));
        if (compareTo23 != 0) {
            return compareTo23;
        }
        if (w() && (a7 = ja.a(this.f187b, ioVar.f187b)) != 0) {
            return a7;
        }
        int compareTo24 = Boolean.valueOf(x()).compareTo(Boolean.valueOf(ioVar.x()));
        if (compareTo24 != 0) {
            return compareTo24;
        }
        if (x() && (a6 = ja.a(this.p, ioVar.p)) != 0) {
            return a6;
        }
        int compareTo25 = Boolean.valueOf(y()).compareTo(Boolean.valueOf(ioVar.y()));
        if (compareTo25 != 0) {
            return compareTo25;
        }
        if (y() && (a5 = ja.a(this.q, ioVar.q)) != 0) {
            return a5;
        }
        int compareTo26 = Boolean.valueOf(z()).compareTo(Boolean.valueOf(ioVar.z()));
        if (compareTo26 != 0) {
            return compareTo26;
        }
        if (z() && (a4 = ja.a((Map) this.f185a, (Map) ioVar.f185a)) != 0) {
            return a4;
        }
        int compareTo27 = Boolean.valueOf(A()).compareTo(Boolean.valueOf(ioVar.A()));
        if (compareTo27 != 0) {
            return compareTo27;
        }
        if (A() && (a3 = ja.a(this.f189b, ioVar.f189b)) != 0) {
            return a3;
        }
        int compareTo28 = Boolean.valueOf(B()).compareTo(Boolean.valueOf(ioVar.B()));
        if (compareTo28 != 0) {
            return compareTo28;
        }
        if (!B() || (a2 = ja.a(this.r, ioVar.r)) == 0) {
            return 0;
        }
        return a2;
    }

    public io a(int i2) {
        this.f12807a = i2;
        a(true);
        return this;
    }

    public io a(ic icVar) {
        this.f181a = icVar;
        return this;
    }

    public io a(String str) {
        this.f188b = str;
        return this;
    }

    public String a() {
        return this.f188b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m218a() {
        if (this.f188b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f190c == null) {
            throw new jl("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f == null) {
            throw new jl("Required field 'token' was not present! Struct: " + toString());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.push.jk r8) {
        /*
            r7 = this;
            r8.f()
        L_0x0003:
            com.xiaomi.push.jh r0 = r8.h()
            byte r1 = r0.b
            if (r1 != 0) goto L_0x0012
            r8.g()
            r7.a()
            return
        L_0x0012:
            short r1 = r0.c
            r2 = 10
            r3 = 2
            r4 = 8
            r5 = 1
            r6 = 11
            switch(r1) {
                case 1: goto L_0x01a9;
                case 2: goto L_0x0196;
                case 3: goto L_0x018b;
                case 4: goto L_0x0180;
                case 5: goto L_0x0175;
                case 6: goto L_0x016a;
                case 7: goto L_0x015f;
                case 8: goto L_0x0154;
                case 9: goto L_0x0149;
                case 10: goto L_0x013d;
                case 11: goto L_0x0131;
                case 12: goto L_0x0125;
                case 13: goto L_0x0116;
                case 14: goto L_0x0107;
                case 15: goto L_0x00fb;
                case 16: goto L_0x00ef;
                case 17: goto L_0x00e3;
                case 18: goto L_0x00d7;
                case 19: goto L_0x00c8;
                case 20: goto L_0x00b8;
                case 21: goto L_0x00a9;
                case 22: goto L_0x009a;
                case 23: goto L_0x008b;
                case 24: goto L_0x007f;
                case 25: goto L_0x0073;
                default: goto L_0x001f;
            }
        L_0x001f:
            switch(r1) {
                case 100: goto L_0x0044;
                case 101: goto L_0x0035;
                case 102: goto L_0x0029;
                default: goto L_0x0022;
            }
        L_0x0022:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r8, r0)
            goto L_0x01b3
        L_0x0029:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.r = r0
            goto L_0x01b3
        L_0x0035:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x0022
            boolean r0 = r8.p()
            r7.f189b = r0
            r7.g((boolean) r5)
            goto L_0x01b3
        L_0x0044:
            byte r1 = r0.b
            r2 = 13
            if (r1 != r2) goto L_0x0022
            com.xiaomi.push.jj r0 = r8.j()
            java.util.HashMap r1 = new java.util.HashMap
            int r2 = r0.c
            int r2 = r2 * 2
            r1.<init>(r2)
            r7.f185a = r1
            r1 = 0
        L_0x005a:
            int r2 = r0.c
            if (r1 >= r2) goto L_0x006e
            java.lang.String r2 = r8.v()
            java.lang.String r3 = r8.v()
            java.util.Map<java.lang.String, java.lang.String> r4 = r7.f185a
            r4.put(r2, r3)
            int r1 = r1 + 1
            goto L_0x005a
        L_0x006e:
            r8.k()
            goto L_0x01b3
        L_0x0073:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.q = r0
            goto L_0x01b3
        L_0x007f:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.p = r0
            goto L_0x01b3
        L_0x008b:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0022
            long r0 = r8.t()
            r7.f187b = r0
            r7.f((boolean) r5)
            goto L_0x01b3
        L_0x009a:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0022
            long r0 = r8.t()
            r7.f180a = r0
            r7.e((boolean) r5)
            goto L_0x01b3
        L_0x00a9:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x0022
            boolean r0 = r8.p()
            r7.f186a = r0
            r7.d((boolean) r5)
            goto L_0x01b3
        L_0x00b8:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x0022
            int r0 = r8.s()
            com.xiaomi.push.ic r0 = com.xiaomi.push.ic.a(r0)
            r7.f181a = r0
            goto L_0x01b3
        L_0x00c8:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x0022
            int r0 = r8.s()
            r7.c = r0
            r7.c((boolean) r5)
            goto L_0x01b3
        L_0x00d7:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.o = r0
            goto L_0x01b3
        L_0x00e3:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.n = r0
            goto L_0x01b3
        L_0x00ef:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.m = r0
            goto L_0x01b3
        L_0x00fb:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.l = r0
            goto L_0x01b3
        L_0x0107:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x0022
            int r0 = r8.s()
            r7.b = r0
            r7.b((boolean) r5)
            goto L_0x01b3
        L_0x0116:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x0022
            int r0 = r8.s()
            r7.f12807a = r0
            r7.a((boolean) r5)
            goto L_0x01b3
        L_0x0125:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.k = r0
            goto L_0x01b3
        L_0x0131:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.j = r0
            goto L_0x01b3
        L_0x013d:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.i = r0
            goto L_0x01b3
        L_0x0149:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.h = r0
            goto L_0x01b3
        L_0x0154:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.g = r0
            goto L_0x01b3
        L_0x015f:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.f = r0
            goto L_0x01b3
        L_0x016a:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.e = r0
            goto L_0x01b3
        L_0x0175:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.d = r0
            goto L_0x01b3
        L_0x0180:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.f190c = r0
            goto L_0x01b3
        L_0x018b:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.f188b = r0
            goto L_0x01b3
        L_0x0196:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x0022
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r7.f182a = r0
            com.xiaomi.push.id r0 = r7.f182a
            r0.a((com.xiaomi.push.jk) r8)
            goto L_0x01b3
        L_0x01a9:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0022
            java.lang.String r0 = r8.v()
            r7.f183a = r0
        L_0x01b3:
            r8.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.io.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z2) {
        this.f184a.set(0, z2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m219a() {
        return this.f183a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m220a(io ioVar) {
        if (ioVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = ioVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f183a.equals(ioVar.f183a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = ioVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f182a.compareTo(ioVar.f182a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = ioVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.f188b.equals(ioVar.f188b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = ioVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.f190c.equals(ioVar.f190c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = ioVar.e();
        if ((e2 || e3) && (!e2 || !e3 || !this.d.equals(ioVar.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = ioVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.e.equals(ioVar.e))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = ioVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.f.equals(ioVar.f))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = ioVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.g.equals(ioVar.g))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = ioVar.i();
        if ((i2 || i3) && (!i2 || !i3 || !this.h.equals(ioVar.h))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = ioVar.j();
        if ((j2 || j3) && (!j2 || !j3 || !this.i.equals(ioVar.i))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = ioVar.k();
        if ((k2 || k3) && (!k2 || !k3 || !this.j.equals(ioVar.j))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = ioVar.l();
        if ((l2 || l3) && (!l2 || !l3 || !this.k.equals(ioVar.k))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = ioVar.m();
        if ((m2 || m3) && (!m2 || !m3 || this.f12807a != ioVar.f12807a)) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = ioVar.n();
        if ((n2 || n3) && (!n2 || !n3 || this.b != ioVar.b)) {
            return false;
        }
        boolean o2 = o();
        boolean o3 = ioVar.o();
        if ((o2 || o3) && (!o2 || !o3 || !this.l.equals(ioVar.l))) {
            return false;
        }
        boolean p2 = p();
        boolean p3 = ioVar.p();
        if ((p2 || p3) && (!p2 || !p3 || !this.m.equals(ioVar.m))) {
            return false;
        }
        boolean q2 = q();
        boolean q3 = ioVar.q();
        if ((q2 || q3) && (!q2 || !q3 || !this.n.equals(ioVar.n))) {
            return false;
        }
        boolean r2 = r();
        boolean r3 = ioVar.r();
        if ((r2 || r3) && (!r2 || !r3 || !this.o.equals(ioVar.o))) {
            return false;
        }
        boolean s2 = s();
        boolean s3 = ioVar.s();
        if ((s2 || s3) && (!s2 || !s3 || this.c != ioVar.c)) {
            return false;
        }
        boolean t2 = t();
        boolean t3 = ioVar.t();
        if ((t2 || t3) && (!t2 || !t3 || !this.f181a.equals(ioVar.f181a))) {
            return false;
        }
        boolean u2 = u();
        boolean u3 = ioVar.u();
        if ((u2 || u3) && (!u2 || !u3 || this.f186a != ioVar.f186a)) {
            return false;
        }
        boolean v2 = v();
        boolean v3 = ioVar.v();
        if ((v2 || v3) && (!v2 || !v3 || this.f180a != ioVar.f180a)) {
            return false;
        }
        boolean w2 = w();
        boolean w3 = ioVar.w();
        if ((w2 || w3) && (!w2 || !w3 || this.f187b != ioVar.f187b)) {
            return false;
        }
        boolean x2 = x();
        boolean x3 = ioVar.x();
        if ((x2 || x3) && (!x2 || !x3 || !this.p.equals(ioVar.p))) {
            return false;
        }
        boolean y2 = y();
        boolean y3 = ioVar.y();
        if ((y2 || y3) && (!y2 || !y3 || !this.q.equals(ioVar.q))) {
            return false;
        }
        boolean z2 = z();
        boolean z3 = ioVar.z();
        if ((z2 || z3) && (!z2 || !z3 || !this.f185a.equals(ioVar.f185a))) {
            return false;
        }
        boolean A2 = A();
        boolean A3 = ioVar.A();
        if ((A2 || A3) && (!A2 || !A3 || this.f189b != ioVar.f189b)) {
            return false;
        }
        boolean B2 = B();
        boolean B3 = ioVar.B();
        if (B2 || B3) {
            return B2 && B3 && this.r.equals(ioVar.r);
        }
        return true;
    }

    public io b(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    public io b(String str) {
        this.f190c = str;
        return this;
    }

    public String b() {
        return this.f190c;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(s);
        if (this.f183a != null && a()) {
            jkVar.a(t);
            jkVar.a(this.f183a);
            jkVar.b();
        }
        if (this.f182a != null && b()) {
            jkVar.a(u);
            this.f182a.b(jkVar);
            jkVar.b();
        }
        if (this.f188b != null) {
            jkVar.a(v);
            jkVar.a(this.f188b);
            jkVar.b();
        }
        if (this.f190c != null) {
            jkVar.a(w);
            jkVar.a(this.f190c);
            jkVar.b();
        }
        if (this.d != null && e()) {
            jkVar.a(x);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && f()) {
            jkVar.a(y);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f != null) {
            jkVar.a(z);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.g != null && h()) {
            jkVar.a(A);
            jkVar.a(this.g);
            jkVar.b();
        }
        if (this.h != null && i()) {
            jkVar.a(B);
            jkVar.a(this.h);
            jkVar.b();
        }
        if (this.i != null && j()) {
            jkVar.a(C);
            jkVar.a(this.i);
            jkVar.b();
        }
        if (this.j != null && k()) {
            jkVar.a(D);
            jkVar.a(this.j);
            jkVar.b();
        }
        if (this.k != null && l()) {
            jkVar.a(E);
            jkVar.a(this.k);
            jkVar.b();
        }
        if (m()) {
            jkVar.a(F);
            jkVar.a(this.f12807a);
            jkVar.b();
        }
        if (n()) {
            jkVar.a(G);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.l != null && o()) {
            jkVar.a(H);
            jkVar.a(this.l);
            jkVar.b();
        }
        if (this.m != null && p()) {
            jkVar.a(I);
            jkVar.a(this.m);
            jkVar.b();
        }
        if (this.n != null && q()) {
            jkVar.a(J);
            jkVar.a(this.n);
            jkVar.b();
        }
        if (this.o != null && r()) {
            jkVar.a(K);
            jkVar.a(this.o);
            jkVar.b();
        }
        if (s()) {
            jkVar.a(L);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (this.f181a != null && t()) {
            jkVar.a(M);
            jkVar.a(this.f181a.a());
            jkVar.b();
        }
        if (u()) {
            jkVar.a(N);
            jkVar.a(this.f186a);
            jkVar.b();
        }
        if (v()) {
            jkVar.a(O);
            jkVar.a(this.f180a);
            jkVar.b();
        }
        if (w()) {
            jkVar.a(P);
            jkVar.a(this.f187b);
            jkVar.b();
        }
        if (this.p != null && x()) {
            jkVar.a(Q);
            jkVar.a(this.p);
            jkVar.b();
        }
        if (this.q != null && y()) {
            jkVar.a(R);
            jkVar.a(this.q);
            jkVar.b();
        }
        if (this.f185a != null && z()) {
            jkVar.a(S);
            jkVar.a(new jj((byte) 11, (byte) 11, this.f185a.size()));
            for (Map.Entry next : this.f185a.entrySet()) {
                jkVar.a((String) next.getKey());
                jkVar.a((String) next.getValue());
            }
            jkVar.d();
            jkVar.b();
        }
        if (A()) {
            jkVar.a(T);
            jkVar.a(this.f189b);
            jkVar.b();
        }
        if (this.r != null && B()) {
            jkVar.a(U);
            jkVar.a(this.r);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z2) {
        this.f184a.set(1, z2);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m221b() {
        return this.f182a != null;
    }

    public io c(int i2) {
        this.c = i2;
        c(true);
        return this;
    }

    public io c(String str) {
        this.d = str;
        return this;
    }

    public String c() {
        return this.f;
    }

    public void c(boolean z2) {
        this.f184a.set(2, z2);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m222c() {
        return this.f188b != null;
    }

    public io d(String str) {
        this.e = str;
        return this;
    }

    public void d(boolean z2) {
        this.f184a.set(3, z2);
    }

    public boolean d() {
        return this.f190c != null;
    }

    public io e(String str) {
        this.f = str;
        return this;
    }

    public void e(boolean z2) {
        this.f184a.set(4, z2);
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof io)) {
            return compareTo((io) obj);
        }
        return false;
    }

    public io f(String str) {
        this.g = str;
        return this;
    }

    public void f(boolean z2) {
        this.f184a.set(5, z2);
    }

    public boolean f() {
        return this.e != null;
    }

    public io g(String str) {
        this.h = str;
        return this;
    }

    public void g(boolean z2) {
        this.f184a.set(6, z2);
    }

    public boolean g() {
        return this.f != null;
    }

    public io h(String str) {
        this.k = str;
        return this;
    }

    public boolean h() {
        return this.g != null;
    }

    public int hashCode() {
        return 0;
    }

    public io i(String str) {
        this.l = str;
        return this;
    }

    public boolean i() {
        return this.h != null;
    }

    public io j(String str) {
        this.n = str;
        return this;
    }

    public boolean j() {
        return this.i != null;
    }

    public io k(String str) {
        this.o = str;
        return this;
    }

    public boolean k() {
        return this.j != null;
    }

    public boolean l() {
        return this.k != null;
    }

    public boolean m() {
        return this.f184a.get(0);
    }

    public boolean n() {
        return this.f184a.get(1);
    }

    public boolean o() {
        return this.l != null;
    }

    public boolean p() {
        return this.m != null;
    }

    public boolean q() {
        return this.n != null;
    }

    public boolean r() {
        return this.o != null;
    }

    public boolean s() {
        return this.f184a.get(2);
    }

    public boolean t() {
        return this.f181a != null;
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("XmPushActionRegistration(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f183a == null ? "null" : this.f183a);
            z2 = false;
        } else {
            z2 = true;
        }
        if (b()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f182a == null) {
                sb.append("null");
            } else {
                sb.append(this.f182a);
            }
            z2 = false;
        }
        if (!z2) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f188b == null ? "null" : this.f188b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f190c == null ? "null" : this.f190c);
        if (e()) {
            sb.append(", ");
            sb.append("appVersion:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.e == null ? "null" : this.e);
        }
        sb.append(", ");
        sb.append("token:");
        sb.append(this.f == null ? "null" : this.f);
        if (h()) {
            sb.append(", ");
            sb.append("deviceId:");
            sb.append(this.g == null ? "null" : this.g);
        }
        if (i()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.h == null ? "null" : this.h);
        }
        if (j()) {
            sb.append(", ");
            sb.append("sdkVersion:");
            sb.append(this.i == null ? "null" : this.i);
        }
        if (k()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.j == null ? "null" : this.j);
        }
        if (l()) {
            sb.append(", ");
            sb.append("pushSdkVersionName:");
            sb.append(this.k == null ? "null" : this.k);
        }
        if (m()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.f12807a);
        }
        if (n()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.b);
        }
        if (o()) {
            sb.append(", ");
            sb.append("androidId:");
            sb.append(this.l == null ? "null" : this.l);
        }
        if (p()) {
            sb.append(", ");
            sb.append("imei:");
            sb.append(this.m == null ? "null" : this.m);
        }
        if (q()) {
            sb.append(", ");
            sb.append("serial:");
            sb.append(this.n == null ? "null" : this.n);
        }
        if (r()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            sb.append(this.o == null ? "null" : this.o);
        }
        if (s()) {
            sb.append(", ");
            sb.append("spaceId:");
            sb.append(this.c);
        }
        if (t()) {
            sb.append(", ");
            sb.append("reason:");
            if (this.f181a == null) {
                sb.append("null");
            } else {
                sb.append(this.f181a);
            }
        }
        if (u()) {
            sb.append(", ");
            sb.append("validateToken:");
            sb.append(this.f186a);
        }
        if (v()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.f180a);
        }
        if (w()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f187b);
        }
        if (x()) {
            sb.append(", ");
            sb.append("subImei:");
            sb.append(this.p == null ? "null" : this.p);
        }
        if (y()) {
            sb.append(", ");
            sb.append("subImeiMd5:");
            sb.append(this.q == null ? "null" : this.q);
        }
        if (z()) {
            sb.append(", ");
            sb.append("connectionAttrs:");
            if (this.f185a == null) {
                sb.append("null");
            } else {
                sb.append(this.f185a);
            }
        }
        if (A()) {
            sb.append(", ");
            sb.append("cleanOldRegInfo:");
            sb.append(this.f189b);
        }
        if (B()) {
            sb.append(", ");
            sb.append("oldRegId:");
            sb.append(this.r == null ? "null" : this.r);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    public boolean u() {
        return this.f184a.get(3);
    }

    public boolean v() {
        return this.f184a.get(4);
    }

    public boolean w() {
        return this.f184a.get(5);
    }

    public boolean x() {
        return this.p != null;
    }

    public boolean y() {
        return this.q != null;
    }

    public boolean z() {
        return this.f185a != null;
    }
}
