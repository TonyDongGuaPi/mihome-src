package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class ip implements iz<ip, Object>, Serializable, Cloneable {
    private static final jh A = new jh("", (byte) 11, 15);
    private static final jh B = new jh("", (byte) 8, 16);
    private static final jh C = new jh("", (byte) 11, 17);
    private static final jh D = new jh("", (byte) 8, 18);
    private static final jh E = new jh("", (byte) 11, 19);
    private static final jp m = new jp("XmPushActionRegistrationResult");
    private static final jh n = new jh("", (byte) 11, 1);
    private static final jh o = new jh("", (byte) 12, 2);
    private static final jh p = new jh("", (byte) 11, 3);
    private static final jh q = new jh("", (byte) 11, 4);
    private static final jh r = new jh("", (byte) 10, 6);
    private static final jh s = new jh("", (byte) 11, 7);
    private static final jh t = new jh("", (byte) 11, 8);
    private static final jh u = new jh("", (byte) 11, 9);
    private static final jh v = new jh("", (byte) 11, 10);
    private static final jh w = new jh("", (byte) 10, 11);
    private static final jh x = new jh("", (byte) 11, 12);
    private static final jh y = new jh("", (byte) 11, 13);
    private static final jh z = new jh("", (byte) 10, 14);

    /* renamed from: a  reason: collision with root package name */
    public int f12808a;

    /* renamed from: a  reason: collision with other field name */
    public long f191a;

    /* renamed from: a  reason: collision with other field name */
    public id f192a;

    /* renamed from: a  reason: collision with other field name */
    public String f193a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f194a = new BitSet(5);
    public int b;

    /* renamed from: b  reason: collision with other field name */
    public long f195b;

    /* renamed from: b  reason: collision with other field name */
    public String f196b;
    public long c;

    /* renamed from: c  reason: collision with other field name */
    public String f197c;
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
    public int compareTo(ip ipVar) {
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
        if (!getClass().equals(ipVar.getClass())) {
            return getClass().getName().compareTo(ipVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ipVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a19 = ja.a(this.f193a, ipVar.f193a)) != 0) {
            return a19;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ipVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a18 = ja.a((Comparable) this.f192a, (Comparable) ipVar.f192a)) != 0) {
            return a18;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ipVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a17 = ja.a(this.f196b, ipVar.f196b)) != 0) {
            return a17;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ipVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a16 = ja.a(this.f197c, ipVar.f197c)) != 0) {
            return a16;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ipVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a15 = ja.a(this.f191a, ipVar.f191a)) != 0) {
            return a15;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ipVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a14 = ja.a(this.d, ipVar.d)) != 0) {
            return a14;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ipVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a13 = ja.a(this.e, ipVar.e)) != 0) {
            return a13;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ipVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a12 = ja.a(this.f, ipVar.f)) != 0) {
            return a12;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ipVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a11 = ja.a(this.g, ipVar.g)) != 0) {
            return a11;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ipVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (j() && (a10 = ja.a(this.f195b, ipVar.f195b)) != 0) {
            return a10;
        }
        int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(ipVar.k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (k() && (a9 = ja.a(this.h, ipVar.h)) != 0) {
            return a9;
        }
        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(ipVar.l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (l() && (a8 = ja.a(this.i, ipVar.i)) != 0) {
            return a8;
        }
        int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(ipVar.m()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (m() && (a7 = ja.a(this.c, ipVar.c)) != 0) {
            return a7;
        }
        int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(ipVar.n()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (n() && (a6 = ja.a(this.j, ipVar.j)) != 0) {
            return a6;
        }
        int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(ipVar.o()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (o() && (a5 = ja.a(this.f12808a, ipVar.f12808a)) != 0) {
            return a5;
        }
        int compareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(ipVar.p()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (p() && (a4 = ja.a(this.k, ipVar.k)) != 0) {
            return a4;
        }
        int compareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(ipVar.q()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (q() && (a3 = ja.a(this.b, ipVar.b)) != 0) {
            return a3;
        }
        int compareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(ipVar.r()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (!r() || (a2 = ja.a(this.l, ipVar.l)) == 0) {
            return 0;
        }
        return a2;
    }

    public long a() {
        return this.f191a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m223a() {
        return this.f196b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m224a() {
        if (this.f196b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f197c == null) {
            throw new jl("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.push.jk r7) {
        /*
            r6 = this;
            r7.f()
        L_0x0003:
            com.xiaomi.push.jh r0 = r7.h()
            byte r1 = r0.b
            if (r1 != 0) goto L_0x0033
            r7.g()
            boolean r7 = r6.e()
            if (r7 == 0) goto L_0x0018
            r6.a()
            return
        L_0x0018:
            com.xiaomi.push.jl r7 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'errorCode' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r6.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L_0x0033:
            short r1 = r0.c
            r2 = 8
            r3 = 10
            r4 = 1
            r5 = 11
            switch(r1) {
                case 1: goto L_0x0121;
                case 2: goto L_0x010e;
                case 3: goto L_0x0103;
                case 4: goto L_0x00f8;
                case 5: goto L_0x003f;
                case 6: goto L_0x00ea;
                case 7: goto L_0x00df;
                case 8: goto L_0x00d4;
                case 9: goto L_0x00c9;
                case 10: goto L_0x00be;
                case 11: goto L_0x00af;
                case 12: goto L_0x00a3;
                case 13: goto L_0x0097;
                case 14: goto L_0x0088;
                case 15: goto L_0x007c;
                case 16: goto L_0x006d;
                case 17: goto L_0x0061;
                case 18: goto L_0x0052;
                case 19: goto L_0x0046;
                default: goto L_0x003f;
            }
        L_0x003f:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r7, r0)
            goto L_0x012b
        L_0x0046:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.l = r0
            goto L_0x012b
        L_0x0052:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003f
            int r0 = r7.s()
            r6.b = r0
            r6.e(r4)
            goto L_0x012b
        L_0x0061:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.k = r0
            goto L_0x012b
        L_0x006d:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003f
            int r0 = r7.s()
            r6.f12808a = r0
            r6.d(r4)
            goto L_0x012b
        L_0x007c:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.j = r0
            goto L_0x012b
        L_0x0088:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003f
            long r0 = r7.t()
            r6.c = r0
            r6.c(r4)
            goto L_0x012b
        L_0x0097:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.i = r0
            goto L_0x012b
        L_0x00a3:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.h = r0
            goto L_0x012b
        L_0x00af:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003f
            long r0 = r7.t()
            r6.f195b = r0
            r6.b((boolean) r4)
            goto L_0x012b
        L_0x00be:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.g = r0
            goto L_0x012b
        L_0x00c9:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.f = r0
            goto L_0x012b
        L_0x00d4:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.e = r0
            goto L_0x012b
        L_0x00df:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.d = r0
            goto L_0x012b
        L_0x00ea:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003f
            long r0 = r7.t()
            r6.f191a = r0
            r6.a((boolean) r4)
            goto L_0x012b
        L_0x00f8:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.f197c = r0
            goto L_0x012b
        L_0x0103:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.f196b = r0
            goto L_0x012b
        L_0x010e:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x003f
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r6.f192a = r0
            com.xiaomi.push.id r0 = r6.f192a
            r0.a((com.xiaomi.push.jk) r7)
            goto L_0x012b
        L_0x0121:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x003f
            java.lang.String r0 = r7.v()
            r6.f193a = r0
        L_0x012b:
            r7.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ip.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z2) {
        this.f194a.set(0, z2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m225a() {
        return this.f193a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m226a(ip ipVar) {
        if (ipVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = ipVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f193a.equals(ipVar.f193a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = ipVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f192a.compareTo(ipVar.f192a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = ipVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.f196b.equals(ipVar.f196b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = ipVar.d();
        if (((d2 || d3) && (!d2 || !d3 || !this.f197c.equals(ipVar.f197c))) || this.f191a != ipVar.f191a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = ipVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.d.equals(ipVar.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = ipVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.e.equals(ipVar.e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = ipVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.f.equals(ipVar.f))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = ipVar.i();
        if ((i2 || i3) && (!i2 || !i3 || !this.g.equals(ipVar.g))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = ipVar.j();
        if ((j2 || j3) && (!j2 || !j3 || this.f195b != ipVar.f195b)) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = ipVar.k();
        if ((k2 || k3) && (!k2 || !k3 || !this.h.equals(ipVar.h))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = ipVar.l();
        if ((l2 || l3) && (!l2 || !l3 || !this.i.equals(ipVar.i))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = ipVar.m();
        if ((m2 || m3) && (!m2 || !m3 || this.c != ipVar.c)) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = ipVar.n();
        if ((n2 || n3) && (!n2 || !n3 || !this.j.equals(ipVar.j))) {
            return false;
        }
        boolean o2 = o();
        boolean o3 = ipVar.o();
        if ((o2 || o3) && (!o2 || !o3 || this.f12808a != ipVar.f12808a)) {
            return false;
        }
        boolean p2 = p();
        boolean p3 = ipVar.p();
        if ((p2 || p3) && (!p2 || !p3 || !this.k.equals(ipVar.k))) {
            return false;
        }
        boolean q2 = q();
        boolean q3 = ipVar.q();
        if ((q2 || q3) && (!q2 || !q3 || this.b != ipVar.b)) {
            return false;
        }
        boolean r2 = r();
        boolean r3 = ipVar.r();
        if (r2 || r3) {
            return r2 && r3 && this.l.equals(ipVar.l);
        }
        return true;
    }

    public String b() {
        return this.g;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(m);
        if (this.f193a != null && a()) {
            jkVar.a(n);
            jkVar.a(this.f193a);
            jkVar.b();
        }
        if (this.f192a != null && b()) {
            jkVar.a(o);
            this.f192a.b(jkVar);
            jkVar.b();
        }
        if (this.f196b != null) {
            jkVar.a(p);
            jkVar.a(this.f196b);
            jkVar.b();
        }
        if (this.f197c != null) {
            jkVar.a(q);
            jkVar.a(this.f197c);
            jkVar.b();
        }
        jkVar.a(r);
        jkVar.a(this.f191a);
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
        if (this.f != null && h()) {
            jkVar.a(u);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.g != null && i()) {
            jkVar.a(v);
            jkVar.a(this.g);
            jkVar.b();
        }
        if (j()) {
            jkVar.a(w);
            jkVar.a(this.f195b);
            jkVar.b();
        }
        if (this.h != null && k()) {
            jkVar.a(x);
            jkVar.a(this.h);
            jkVar.b();
        }
        if (this.i != null && l()) {
            jkVar.a(y);
            jkVar.a(this.i);
            jkVar.b();
        }
        if (m()) {
            jkVar.a(z);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (this.j != null && n()) {
            jkVar.a(A);
            jkVar.a(this.j);
            jkVar.b();
        }
        if (o()) {
            jkVar.a(B);
            jkVar.a(this.f12808a);
            jkVar.b();
        }
        if (this.k != null && p()) {
            jkVar.a(C);
            jkVar.a(this.k);
            jkVar.b();
        }
        if (q()) {
            jkVar.a(D);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.l != null && r()) {
            jkVar.a(E);
            jkVar.a(this.l);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z2) {
        this.f194a.set(1, z2);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m227b() {
        return this.f192a != null;
    }

    public void c(boolean z2) {
        this.f194a.set(2, z2);
    }

    public boolean c() {
        return this.f196b != null;
    }

    public void d(boolean z2) {
        this.f194a.set(3, z2);
    }

    public boolean d() {
        return this.f197c != null;
    }

    public void e(boolean z2) {
        this.f194a.set(4, z2);
    }

    public boolean e() {
        return this.f194a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ip)) {
            return compareTo((ip) obj);
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
        return this.f != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.g != null;
    }

    public boolean j() {
        return this.f194a.get(1);
    }

    public boolean k() {
        return this.h != null;
    }

    public boolean l() {
        return this.i != null;
    }

    public boolean m() {
        return this.f194a.get(2);
    }

    public boolean n() {
        return this.j != null;
    }

    public boolean o() {
        return this.f194a.get(3);
    }

    public boolean p() {
        return this.k != null;
    }

    public boolean q() {
        return this.f194a.get(4);
    }

    public boolean r() {
        return this.l != null;
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("XmPushActionRegistrationResult(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f193a == null ? "null" : this.f193a);
            z2 = false;
        } else {
            z2 = true;
        }
        if (b()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f192a == null) {
                sb.append("null");
            } else {
                sb.append(this.f192a);
            }
            z2 = false;
        }
        if (!z2) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f196b == null ? "null" : this.f196b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f197c == null ? "null" : this.f197c);
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f191a);
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("regSecret:");
            sb.append(this.f == null ? "null" : this.f);
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.g == null ? "null" : this.g);
        }
        if (j()) {
            sb.append(", ");
            sb.append("registeredAt:");
            sb.append(this.f195b);
        }
        if (k()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.h == null ? "null" : this.h);
        }
        if (l()) {
            sb.append(", ");
            sb.append("clientId:");
            sb.append(this.i == null ? "null" : this.i);
        }
        if (m()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.c);
        }
        if (n()) {
            sb.append(", ");
            sb.append("appVersion:");
            sb.append(this.j == null ? "null" : this.j);
        }
        if (o()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.f12808a);
        }
        if (p()) {
            sb.append(", ");
            sb.append("hybridPushEndpoint:");
            sb.append(this.k == null ? "null" : this.k);
        }
        if (q()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.b);
        }
        if (r()) {
            sb.append(", ");
            sb.append("region:");
            sb.append(this.l == null ? "null" : this.l);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
