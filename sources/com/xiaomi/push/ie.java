package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Map;

public class ie implements iz<ie, Object>, Serializable, Cloneable {
    private static final jh A = new jh("", (byte) 11, 14);
    private static final jh B = new jh("", (byte) 6, 15);
    private static final jh C = new jh("", (byte) 6, 16);
    private static final jh D = new jh("", (byte) 11, 20);
    private static final jh E = new jh("", (byte) 11, 21);
    private static final jh F = new jh("", (byte) 8, 22);
    private static final jh G = new jh("", (byte) 13, 23);
    private static final jp m = new jp("XmPushActionAckMessage");
    private static final jh n = new jh("", (byte) 11, 1);
    private static final jh o = new jh("", (byte) 12, 2);
    private static final jh p = new jh("", (byte) 11, 3);
    private static final jh q = new jh("", (byte) 11, 4);
    private static final jh r = new jh("", (byte) 10, 5);
    private static final jh s = new jh("", (byte) 11, 6);
    private static final jh t = new jh("", (byte) 11, 7);
    private static final jh u = new jh("", (byte) 12, 8);
    private static final jh v = new jh("", (byte) 11, 9);
    private static final jh w = new jh("", (byte) 11, 10);
    private static final jh x = new jh("", (byte) 2, 11);
    private static final jh y = new jh("", (byte) 11, 12);
    private static final jh z = new jh("", (byte) 11, 13);

    /* renamed from: a  reason: collision with root package name */
    public int f12797a;

    /* renamed from: a  reason: collision with other field name */
    public long f141a;

    /* renamed from: a  reason: collision with other field name */
    public id f142a;

    /* renamed from: a  reason: collision with other field name */
    public ir f143a;

    /* renamed from: a  reason: collision with other field name */
    public String f144a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f145a = new BitSet(5);

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f146a;

    /* renamed from: a  reason: collision with other field name */
    public short f147a;

    /* renamed from: a  reason: collision with other field name */
    public boolean f148a = false;
    public String b;

    /* renamed from: b  reason: collision with other field name */
    public short f149b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;

    /* renamed from: a */
    public int compareTo(ie ieVar) {
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
        if (!getClass().equals(ieVar.getClass())) {
            return getClass().getName().compareTo(ieVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ieVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a21 = ja.a(this.f144a, ieVar.f144a)) != 0) {
            return a21;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ieVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a20 = ja.a((Comparable) this.f142a, (Comparable) ieVar.f142a)) != 0) {
            return a20;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ieVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a19 = ja.a(this.b, ieVar.b)) != 0) {
            return a19;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ieVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a18 = ja.a(this.c, ieVar.c)) != 0) {
            return a18;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ieVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a17 = ja.a(this.f141a, ieVar.f141a)) != 0) {
            return a17;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ieVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a16 = ja.a(this.d, ieVar.d)) != 0) {
            return a16;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ieVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a15 = ja.a(this.e, ieVar.e)) != 0) {
            return a15;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ieVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a14 = ja.a((Comparable) this.f143a, (Comparable) ieVar.f143a)) != 0) {
            return a14;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ieVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a13 = ja.a(this.f, ieVar.f)) != 0) {
            return a13;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ieVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (j() && (a12 = ja.a(this.g, ieVar.g)) != 0) {
            return a12;
        }
        int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(ieVar.k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (k() && (a11 = ja.a(this.f148a, ieVar.f148a)) != 0) {
            return a11;
        }
        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(ieVar.l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (l() && (a10 = ja.a(this.h, ieVar.h)) != 0) {
            return a10;
        }
        int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(ieVar.m()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (m() && (a9 = ja.a(this.i, ieVar.i)) != 0) {
            return a9;
        }
        int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(ieVar.n()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (n() && (a8 = ja.a(this.j, ieVar.j)) != 0) {
            return a8;
        }
        int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(ieVar.o()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (o() && (a7 = ja.a(this.f147a, ieVar.f147a)) != 0) {
            return a7;
        }
        int compareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(ieVar.p()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (p() && (a6 = ja.a(this.f149b, ieVar.f149b)) != 0) {
            return a6;
        }
        int compareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(ieVar.q()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (q() && (a5 = ja.a(this.k, ieVar.k)) != 0) {
            return a5;
        }
        int compareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(ieVar.r()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (r() && (a4 = ja.a(this.l, ieVar.l)) != 0) {
            return a4;
        }
        int compareTo19 = Boolean.valueOf(s()).compareTo(Boolean.valueOf(ieVar.s()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (s() && (a3 = ja.a(this.f12797a, ieVar.f12797a)) != 0) {
            return a3;
        }
        int compareTo20 = Boolean.valueOf(t()).compareTo(Boolean.valueOf(ieVar.t()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (!t() || (a2 = ja.a((Map) this.f146a, (Map) ieVar.f146a)) == 0) {
            return 0;
        }
        return a2;
    }

    public ie a(long j2) {
        this.f141a = j2;
        a(true);
        return this;
    }

    public ie a(String str) {
        this.b = str;
        return this;
    }

    public ie a(short s2) {
        this.f147a = s2;
        c(true);
        return this;
    }

    public void a() {
        if (this.b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new jl("Required field 'appId' was not present! Struct: " + toString());
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
            if (r1 != 0) goto L_0x0033
            r8.g()
            boolean r8 = r7.e()
            if (r8 == 0) goto L_0x0018
            r7.a()
            return
        L_0x0018:
            com.xiaomi.push.jl r8 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'messageTs' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r7.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        L_0x0033:
            short r1 = r0.c
            r2 = 6
            r3 = 2
            r4 = 12
            r5 = 1
            r6 = 11
            switch(r1) {
                case 1: goto L_0x0165;
                case 2: goto L_0x0154;
                case 3: goto L_0x0149;
                case 4: goto L_0x013e;
                case 5: goto L_0x012e;
                case 6: goto L_0x0123;
                case 7: goto L_0x0118;
                case 8: goto L_0x0107;
                case 9: goto L_0x00fb;
                case 10: goto L_0x00ef;
                case 11: goto L_0x00e0;
                case 12: goto L_0x00d4;
                case 13: goto L_0x00c8;
                case 14: goto L_0x00bc;
                case 15: goto L_0x00ad;
                case 16: goto L_0x009e;
                case 17: goto L_0x003f;
                case 18: goto L_0x003f;
                case 19: goto L_0x003f;
                case 20: goto L_0x0092;
                case 21: goto L_0x0086;
                case 22: goto L_0x0075;
                case 23: goto L_0x0046;
                default: goto L_0x003f;
            }
        L_0x003f:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r8, r0)
            goto L_0x016f
        L_0x0046:
            byte r1 = r0.b
            r2 = 13
            if (r1 != r2) goto L_0x003f
            com.xiaomi.push.jj r0 = r8.j()
            java.util.HashMap r1 = new java.util.HashMap
            int r2 = r0.c
            int r2 = r2 * 2
            r1.<init>(r2)
            r7.f146a = r1
            r1 = 0
        L_0x005c:
            int r2 = r0.c
            if (r1 >= r2) goto L_0x0070
            java.lang.String r2 = r8.v()
            java.lang.String r3 = r8.v()
            java.util.Map<java.lang.String, java.lang.String> r4 = r7.f146a
            r4.put(r2, r3)
            int r1 = r1 + 1
            goto L_0x005c
        L_0x0070:
            r8.k()
            goto L_0x016f
        L_0x0075:
            byte r1 = r0.b
            r2 = 8
            if (r1 != r2) goto L_0x003f
            int r0 = r8.s()
            r7.f12797a = r0
            r7.e(r5)
            goto L_0x016f
        L_0x0086:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.l = r0
            goto L_0x016f
        L_0x0092:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.k = r0
            goto L_0x016f
        L_0x009e:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003f
            short r0 = r8.r()
            r7.f149b = r0
            r7.d((boolean) r5)
            goto L_0x016f
        L_0x00ad:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003f
            short r0 = r8.r()
            r7.f147a = r0
            r7.c((boolean) r5)
            goto L_0x016f
        L_0x00bc:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.j = r0
            goto L_0x016f
        L_0x00c8:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.i = r0
            goto L_0x016f
        L_0x00d4:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.h = r0
            goto L_0x016f
        L_0x00e0:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003f
            boolean r0 = r8.p()
            r7.f148a = r0
            r7.b((boolean) r5)
            goto L_0x016f
        L_0x00ef:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.g = r0
            goto L_0x016f
        L_0x00fb:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.f = r0
            goto L_0x016f
        L_0x0107:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x003f
            com.xiaomi.push.ir r0 = new com.xiaomi.push.ir
            r0.<init>()
            r7.f143a = r0
            com.xiaomi.push.ir r0 = r7.f143a
            r0.a((com.xiaomi.push.jk) r8)
            goto L_0x016f
        L_0x0118:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.e = r0
            goto L_0x016f
        L_0x0123:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.d = r0
            goto L_0x016f
        L_0x012e:
            byte r1 = r0.b
            r2 = 10
            if (r1 != r2) goto L_0x003f
            long r0 = r8.t()
            r7.f141a = r0
            r7.a((boolean) r5)
            goto L_0x016f
        L_0x013e:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.c = r0
            goto L_0x016f
        L_0x0149:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.b = r0
            goto L_0x016f
        L_0x0154:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x003f
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r7.f142a = r0
            com.xiaomi.push.id r0 = r7.f142a
            r0.a((com.xiaomi.push.jk) r8)
            goto L_0x016f
        L_0x0165:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x003f
            java.lang.String r0 = r8.v()
            r7.f144a = r0
        L_0x016f:
            r8.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ie.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z2) {
        this.f145a.set(0, z2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m176a() {
        return this.f144a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m177a(ie ieVar) {
        if (ieVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = ieVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f144a.equals(ieVar.f144a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = ieVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f142a.compareTo(ieVar.f142a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = ieVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(ieVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = ieVar.d();
        if (((d2 || d3) && (!d2 || !d3 || !this.c.equals(ieVar.c))) || this.f141a != ieVar.f141a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = ieVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.d.equals(ieVar.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = ieVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.e.equals(ieVar.e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = ieVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.f143a.compareTo(ieVar.f143a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = ieVar.i();
        if ((i2 || i3) && (!i2 || !i3 || !this.f.equals(ieVar.f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = ieVar.j();
        if ((j2 || j3) && (!j2 || !j3 || !this.g.equals(ieVar.g))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = ieVar.k();
        if ((k2 || k3) && (!k2 || !k3 || this.f148a != ieVar.f148a)) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = ieVar.l();
        if ((l2 || l3) && (!l2 || !l3 || !this.h.equals(ieVar.h))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = ieVar.m();
        if ((m2 || m3) && (!m2 || !m3 || !this.i.equals(ieVar.i))) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = ieVar.n();
        if ((n2 || n3) && (!n2 || !n3 || !this.j.equals(ieVar.j))) {
            return false;
        }
        boolean o2 = o();
        boolean o3 = ieVar.o();
        if ((o2 || o3) && (!o2 || !o3 || this.f147a != ieVar.f147a)) {
            return false;
        }
        boolean p2 = p();
        boolean p3 = ieVar.p();
        if ((p2 || p3) && (!p2 || !p3 || this.f149b != ieVar.f149b)) {
            return false;
        }
        boolean q2 = q();
        boolean q3 = ieVar.q();
        if ((q2 || q3) && (!q2 || !q3 || !this.k.equals(ieVar.k))) {
            return false;
        }
        boolean r2 = r();
        boolean r3 = ieVar.r();
        if ((r2 || r3) && (!r2 || !r3 || !this.l.equals(ieVar.l))) {
            return false;
        }
        boolean s2 = s();
        boolean s3 = ieVar.s();
        if ((s2 || s3) && (!s2 || !s3 || this.f12797a != ieVar.f12797a)) {
            return false;
        }
        boolean t2 = t();
        boolean t3 = ieVar.t();
        if (t2 || t3) {
            return t2 && t3 && this.f146a.equals(ieVar.f146a);
        }
        return true;
    }

    public ie b(String str) {
        this.c = str;
        return this;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(m);
        if (this.f144a != null && a()) {
            jkVar.a(n);
            jkVar.a(this.f144a);
            jkVar.b();
        }
        if (this.f142a != null && b()) {
            jkVar.a(o);
            this.f142a.b(jkVar);
            jkVar.b();
        }
        if (this.b != null) {
            jkVar.a(p);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.c != null) {
            jkVar.a(q);
            jkVar.a(this.c);
            jkVar.b();
        }
        jkVar.a(r);
        jkVar.a(this.f141a);
        jkVar.b();
        if (this.d != null && f()) {
            jkVar.a(s);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && g()) {
            jkVar.a(t);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f143a != null && h()) {
            jkVar.a(u);
            this.f143a.b(jkVar);
            jkVar.b();
        }
        if (this.f != null && i()) {
            jkVar.a(v);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.g != null && j()) {
            jkVar.a(w);
            jkVar.a(this.g);
            jkVar.b();
        }
        if (k()) {
            jkVar.a(x);
            jkVar.a(this.f148a);
            jkVar.b();
        }
        if (this.h != null && l()) {
            jkVar.a(y);
            jkVar.a(this.h);
            jkVar.b();
        }
        if (this.i != null && m()) {
            jkVar.a(z);
            jkVar.a(this.i);
            jkVar.b();
        }
        if (this.j != null && n()) {
            jkVar.a(A);
            jkVar.a(this.j);
            jkVar.b();
        }
        if (o()) {
            jkVar.a(B);
            jkVar.a(this.f147a);
            jkVar.b();
        }
        if (p()) {
            jkVar.a(C);
            jkVar.a(this.f149b);
            jkVar.b();
        }
        if (this.k != null && q()) {
            jkVar.a(D);
            jkVar.a(this.k);
            jkVar.b();
        }
        if (this.l != null && r()) {
            jkVar.a(E);
            jkVar.a(this.l);
            jkVar.b();
        }
        if (s()) {
            jkVar.a(F);
            jkVar.a(this.f12797a);
            jkVar.b();
        }
        if (this.f146a != null && t()) {
            jkVar.a(G);
            jkVar.a(new jj((byte) 11, (byte) 11, this.f146a.size()));
            for (Map.Entry next : this.f146a.entrySet()) {
                jkVar.a((String) next.getKey());
                jkVar.a((String) next.getValue());
            }
            jkVar.d();
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z2) {
        this.f145a.set(1, z2);
    }

    public boolean b() {
        return this.f142a != null;
    }

    public ie c(String str) {
        this.d = str;
        return this;
    }

    public void c(boolean z2) {
        this.f145a.set(2, z2);
    }

    public boolean c() {
        return this.b != null;
    }

    public ie d(String str) {
        this.e = str;
        return this;
    }

    public void d(boolean z2) {
        this.f145a.set(3, z2);
    }

    public boolean d() {
        return this.c != null;
    }

    public void e(boolean z2) {
        this.f145a.set(4, z2);
    }

    public boolean e() {
        return this.f145a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ie)) {
            return compareTo((ie) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.e != null;
    }

    public boolean h() {
        return this.f143a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f != null;
    }

    public boolean j() {
        return this.g != null;
    }

    public boolean k() {
        return this.f145a.get(1);
    }

    public boolean l() {
        return this.h != null;
    }

    public boolean m() {
        return this.i != null;
    }

    public boolean n() {
        return this.j != null;
    }

    public boolean o() {
        return this.f145a.get(2);
    }

    public boolean p() {
        return this.f145a.get(3);
    }

    public boolean q() {
        return this.k != null;
    }

    public boolean r() {
        return this.l != null;
    }

    public boolean s() {
        return this.f145a.get(4);
    }

    public boolean t() {
        return this.f146a != null;
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("XmPushActionAckMessage(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f144a == null ? "null" : this.f144a);
            z2 = false;
        } else {
            z2 = true;
        }
        if (b()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f142a == null) {
                sb.append("null");
            } else {
                sb.append(this.f142a);
            }
            z2 = false;
        }
        if (!z2) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.b == null ? "null" : this.b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.c == null ? "null" : this.c);
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f141a);
        if (f()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("request:");
            if (this.f143a == null) {
                sb.append("null");
            } else {
                sb.append(this.f143a);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f == null ? "null" : this.f);
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.g == null ? "null" : this.g);
        }
        if (k()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.f148a);
        }
        if (l()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.h == null ? "null" : this.h);
        }
        if (m()) {
            sb.append(", ");
            sb.append("callbackUrl:");
            sb.append(this.i == null ? "null" : this.i);
        }
        if (n()) {
            sb.append(", ");
            sb.append("userAccount:");
            sb.append(this.j == null ? "null" : this.j);
        }
        if (o()) {
            sb.append(", ");
            sb.append("deviceStatus:");
            sb.append(this.f147a);
        }
        if (p()) {
            sb.append(", ");
            sb.append("geoMsgStatus:");
            sb.append(this.f149b);
        }
        if (q()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            sb.append(this.k == null ? "null" : this.k);
        }
        if (r()) {
            sb.append(", ");
            sb.append("deviceId:");
            sb.append(this.l == null ? "null" : this.l);
        }
        if (s()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.f12797a);
        }
        if (t()) {
            sb.append(", ");
            sb.append("extra:");
            if (this.f146a == null) {
                sb.append("null");
            } else {
                sb.append(this.f146a);
            }
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
