package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class ia implements iz<ia, Object>, Serializable, Cloneable {
    private static final jh A = new jh("", (byte) 2, 14);
    private static final jh B = new jh("", (byte) 11, 15);
    private static final jh C = new jh("", (byte) 10, 16);
    private static final jh D = new jh("", (byte) 11, 20);
    private static final jh E = new jh("", (byte) 11, 21);
    private static final jp m = new jp("PushMessage");
    private static final jh n = new jh("", (byte) 12, 1);
    private static final jh o = new jh("", (byte) 11, 2);
    private static final jh p = new jh("", (byte) 11, 3);
    private static final jh q = new jh("", (byte) 11, 4);
    private static final jh r = new jh("", (byte) 10, 5);
    private static final jh s = new jh("", (byte) 10, 6);
    private static final jh t = new jh("", (byte) 11, 7);
    private static final jh u = new jh("", (byte) 11, 8);
    private static final jh v = new jh("", (byte) 11, 9);
    private static final jh w = new jh("", (byte) 11, 10);
    private static final jh x = new jh("", (byte) 11, 11);
    private static final jh y = new jh("", (byte) 12, 12);
    private static final jh z = new jh("", (byte) 11, 13);

    /* renamed from: a  reason: collision with root package name */
    public long f12793a;

    /* renamed from: a  reason: collision with other field name */
    public ib f120a;

    /* renamed from: a  reason: collision with other field name */
    public id f121a;

    /* renamed from: a  reason: collision with other field name */
    public String f122a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f123a = new BitSet(4);

    /* renamed from: a  reason: collision with other field name */
    public boolean f124a = false;
    public long b;

    /* renamed from: b  reason: collision with other field name */
    public String f125b;
    public long c;

    /* renamed from: c  reason: collision with other field name */
    public String f126c;
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
    public int compareTo(ia iaVar) {
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
        if (!getClass().equals(iaVar.getClass())) {
            return getClass().getName().compareTo(iaVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(iaVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a19 = ja.a((Comparable) this.f121a, (Comparable) iaVar.f121a)) != 0) {
            return a19;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(iaVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a18 = ja.a(this.f122a, iaVar.f122a)) != 0) {
            return a18;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(iaVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a17 = ja.a(this.f125b, iaVar.f125b)) != 0) {
            return a17;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(iaVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a16 = ja.a(this.f126c, iaVar.f126c)) != 0) {
            return a16;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(iaVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a15 = ja.a(this.f12793a, iaVar.f12793a)) != 0) {
            return a15;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(iaVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a14 = ja.a(this.b, iaVar.b)) != 0) {
            return a14;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(iaVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a13 = ja.a(this.d, iaVar.d)) != 0) {
            return a13;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(iaVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a12 = ja.a(this.e, iaVar.e)) != 0) {
            return a12;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(iaVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a11 = ja.a(this.f, iaVar.f)) != 0) {
            return a11;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(iaVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (j() && (a10 = ja.a(this.g, iaVar.g)) != 0) {
            return a10;
        }
        int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(iaVar.k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (k() && (a9 = ja.a(this.h, iaVar.h)) != 0) {
            return a9;
        }
        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(iaVar.l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (l() && (a8 = ja.a((Comparable) this.f120a, (Comparable) iaVar.f120a)) != 0) {
            return a8;
        }
        int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(iaVar.m()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (m() && (a7 = ja.a(this.i, iaVar.i)) != 0) {
            return a7;
        }
        int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(iaVar.n()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (n() && (a6 = ja.a(this.f124a, iaVar.f124a)) != 0) {
            return a6;
        }
        int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(iaVar.o()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (o() && (a5 = ja.a(this.j, iaVar.j)) != 0) {
            return a5;
        }
        int compareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(iaVar.p()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (p() && (a4 = ja.a(this.c, iaVar.c)) != 0) {
            return a4;
        }
        int compareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(iaVar.q()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (q() && (a3 = ja.a(this.k, iaVar.k)) != 0) {
            return a3;
        }
        int compareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(iaVar.r()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (!r() || (a2 = ja.a(this.l, iaVar.l)) == 0) {
            return 0;
        }
        return a2;
    }

    public long a() {
        return this.f12793a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m155a() {
        return this.f122a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m156a() {
        if (this.f122a == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f125b == null) {
            throw new jl("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f126c == null) {
            throw new jl("Required field 'payload' was not present! Struct: " + toString());
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
            if (r1 != 0) goto L_0x0012
            r7.g()
            r6.a()
            return
        L_0x0012:
            short r1 = r0.c
            r2 = 12
            r3 = 10
            r4 = 1
            r5 = 11
            switch(r1) {
                case 1: goto L_0x00fc;
                case 2: goto L_0x00f1;
                case 3: goto L_0x00e6;
                case 4: goto L_0x00db;
                case 5: goto L_0x00cd;
                case 6: goto L_0x00bf;
                case 7: goto L_0x00b4;
                case 8: goto L_0x00a9;
                case 9: goto L_0x009e;
                case 10: goto L_0x0092;
                case 11: goto L_0x0086;
                case 12: goto L_0x0074;
                case 13: goto L_0x0068;
                case 14: goto L_0x0058;
                case 15: goto L_0x004c;
                case 16: goto L_0x003d;
                case 17: goto L_0x001e;
                case 18: goto L_0x001e;
                case 19: goto L_0x001e;
                case 20: goto L_0x0031;
                case 21: goto L_0x0025;
                default: goto L_0x001e;
            }
        L_0x001e:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r7, r0)
            goto L_0x010c
        L_0x0025:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.l = r0
            goto L_0x010c
        L_0x0031:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.k = r0
            goto L_0x010c
        L_0x003d:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001e
            long r0 = r7.t()
            r6.c = r0
            r6.d(r4)
            goto L_0x010c
        L_0x004c:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.j = r0
            goto L_0x010c
        L_0x0058:
            byte r1 = r0.b
            r2 = 2
            if (r1 != r2) goto L_0x001e
            boolean r0 = r7.p()
            r6.f124a = r0
            r6.c(r4)
            goto L_0x010c
        L_0x0068:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.i = r0
            goto L_0x010c
        L_0x0074:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x001e
            com.xiaomi.push.ib r0 = new com.xiaomi.push.ib
            r0.<init>()
            r6.f120a = r0
            com.xiaomi.push.ib r0 = r6.f120a
            r0.a((com.xiaomi.push.jk) r7)
            goto L_0x010c
        L_0x0086:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.h = r0
            goto L_0x010c
        L_0x0092:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.g = r0
            goto L_0x010c
        L_0x009e:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.f = r0
            goto L_0x010c
        L_0x00a9:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.e = r0
            goto L_0x010c
        L_0x00b4:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.d = r0
            goto L_0x010c
        L_0x00bf:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001e
            long r0 = r7.t()
            r6.b = r0
            r6.b((boolean) r4)
            goto L_0x010c
        L_0x00cd:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001e
            long r0 = r7.t()
            r6.f12793a = r0
            r6.a((boolean) r4)
            goto L_0x010c
        L_0x00db:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.f126c = r0
            goto L_0x010c
        L_0x00e6:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.f125b = r0
            goto L_0x010c
        L_0x00f1:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001e
            java.lang.String r0 = r7.v()
            r6.f122a = r0
            goto L_0x010c
        L_0x00fc:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x001e
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r6.f121a = r0
            com.xiaomi.push.id r0 = r6.f121a
            r0.a((com.xiaomi.push.jk) r7)
        L_0x010c:
            r7.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ia.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z2) {
        this.f123a.set(0, z2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m157a() {
        return this.f121a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m158a(ia iaVar) {
        if (iaVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = iaVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f121a.compareTo(iaVar.f121a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = iaVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f122a.equals(iaVar.f122a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = iaVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.f125b.equals(iaVar.f125b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = iaVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.f126c.equals(iaVar.f126c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = iaVar.e();
        if ((e2 || e3) && (!e2 || !e3 || this.f12793a != iaVar.f12793a)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = iaVar.f();
        if ((f2 || f3) && (!f2 || !f3 || this.b != iaVar.b)) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = iaVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.d.equals(iaVar.d))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = iaVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.e.equals(iaVar.e))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = iaVar.i();
        if ((i2 || i3) && (!i2 || !i3 || !this.f.equals(iaVar.f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = iaVar.j();
        if ((j2 || j3) && (!j2 || !j3 || !this.g.equals(iaVar.g))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = iaVar.k();
        if ((k2 || k3) && (!k2 || !k3 || !this.h.equals(iaVar.h))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = iaVar.l();
        if ((l2 || l3) && (!l2 || !l3 || !this.f120a.compareTo(iaVar.f120a))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = iaVar.m();
        if ((m2 || m3) && (!m2 || !m3 || !this.i.equals(iaVar.i))) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = iaVar.n();
        if ((n2 || n3) && (!n2 || !n3 || this.f124a != iaVar.f124a)) {
            return false;
        }
        boolean o2 = o();
        boolean o3 = iaVar.o();
        if ((o2 || o3) && (!o2 || !o3 || !this.j.equals(iaVar.j))) {
            return false;
        }
        boolean p2 = p();
        boolean p3 = iaVar.p();
        if ((p2 || p3) && (!p2 || !p3 || this.c != iaVar.c)) {
            return false;
        }
        boolean q2 = q();
        boolean q3 = iaVar.q();
        if ((q2 || q3) && (!q2 || !q3 || !this.k.equals(iaVar.k))) {
            return false;
        }
        boolean r2 = r();
        boolean r3 = iaVar.r();
        if (r2 || r3) {
            return r2 && r3 && this.l.equals(iaVar.l);
        }
        return true;
    }

    public String b() {
        return this.f125b;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(m);
        if (this.f121a != null && a()) {
            jkVar.a(n);
            this.f121a.b(jkVar);
            jkVar.b();
        }
        if (this.f122a != null) {
            jkVar.a(o);
            jkVar.a(this.f122a);
            jkVar.b();
        }
        if (this.f125b != null) {
            jkVar.a(p);
            jkVar.a(this.f125b);
            jkVar.b();
        }
        if (this.f126c != null) {
            jkVar.a(q);
            jkVar.a(this.f126c);
            jkVar.b();
        }
        if (e()) {
            jkVar.a(r);
            jkVar.a(this.f12793a);
            jkVar.b();
        }
        if (f()) {
            jkVar.a(s);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.d != null && g()) {
            jkVar.a(t);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && h()) {
            jkVar.a(u);
            jkVar.a(this.e);
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
        if (this.h != null && k()) {
            jkVar.a(x);
            jkVar.a(this.h);
            jkVar.b();
        }
        if (this.f120a != null && l()) {
            jkVar.a(y);
            this.f120a.b(jkVar);
            jkVar.b();
        }
        if (this.i != null && m()) {
            jkVar.a(z);
            jkVar.a(this.i);
            jkVar.b();
        }
        if (n()) {
            jkVar.a(A);
            jkVar.a(this.f124a);
            jkVar.b();
        }
        if (this.j != null && o()) {
            jkVar.a(B);
            jkVar.a(this.j);
            jkVar.b();
        }
        if (p()) {
            jkVar.a(C);
            jkVar.a(this.c);
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
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z2) {
        this.f123a.set(1, z2);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m159b() {
        return this.f122a != null;
    }

    public String c() {
        return this.f126c;
    }

    public void c(boolean z2) {
        this.f123a.set(2, z2);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m160c() {
        return this.f125b != null;
    }

    public void d(boolean z2) {
        this.f123a.set(3, z2);
    }

    public boolean d() {
        return this.f126c != null;
    }

    public boolean e() {
        return this.f123a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ia)) {
            return compareTo((ia) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f123a.get(1);
    }

    public boolean g() {
        return this.d != null;
    }

    public boolean h() {
        return this.e != null;
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
        return this.h != null;
    }

    public boolean l() {
        return this.f120a != null;
    }

    public boolean m() {
        return this.i != null;
    }

    public boolean n() {
        return this.f123a.get(2);
    }

    public boolean o() {
        return this.j != null;
    }

    public boolean p() {
        return this.f123a.get(3);
    }

    public boolean q() {
        return this.k != null;
    }

    public boolean r() {
        return this.l != null;
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("PushMessage(");
        if (a()) {
            sb.append("to:");
            if (this.f121a == null) {
                sb.append("null");
            } else {
                sb.append(this.f121a);
            }
            z2 = false;
        } else {
            z2 = true;
        }
        if (!z2) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f122a == null ? "null" : this.f122a);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f125b == null ? "null" : this.f125b);
        sb.append(", ");
        sb.append("payload:");
        sb.append(this.f126c == null ? "null" : this.f126c);
        if (e()) {
            sb.append(", ");
            sb.append("createAt:");
            sb.append(this.f12793a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("ttl:");
            sb.append(this.b);
        }
        if (g()) {
            sb.append(", ");
            sb.append("collapseKey:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (h()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (i()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.f == null ? "null" : this.f);
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.g == null ? "null" : this.g);
        }
        if (k()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.h == null ? "null" : this.h);
        }
        if (l()) {
            sb.append(", ");
            sb.append("metaInfo:");
            if (this.f120a == null) {
                sb.append("null");
            } else {
                sb.append(this.f120a);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.i == null ? "null" : this.i);
        }
        if (n()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.f124a);
        }
        if (o()) {
            sb.append(", ");
            sb.append("userAccount:");
            sb.append(this.j == null ? "null" : this.j);
        }
        if (p()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.c);
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
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
