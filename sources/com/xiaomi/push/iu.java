package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class iu implements iz<iu, Object>, Serializable, Cloneable {
    private static final jp j = new jp("XmPushActionUnRegistration");
    private static final jh k = new jh("", (byte) 11, 1);
    private static final jh l = new jh("", (byte) 12, 2);
    private static final jh m = new jh("", (byte) 11, 3);
    private static final jh n = new jh("", (byte) 11, 4);
    private static final jh o = new jh("", (byte) 11, 5);
    private static final jh p = new jh("", (byte) 11, 6);
    private static final jh q = new jh("", (byte) 11, 7);
    private static final jh r = new jh("", (byte) 11, 8);
    private static final jh s = new jh("", (byte) 11, 9);
    private static final jh t = new jh("", (byte) 11, 10);
    private static final jh u = new jh("", (byte) 2, 11);
    private static final jh v = new jh("", (byte) 10, 12);

    /* renamed from: a  reason: collision with root package name */
    public long f12813a;

    /* renamed from: a  reason: collision with other field name */
    public id f211a;

    /* renamed from: a  reason: collision with other field name */
    public String f212a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f213a = new BitSet(2);

    /* renamed from: a  reason: collision with other field name */
    public boolean f214a = true;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;

    /* renamed from: a */
    public int compareTo(iu iuVar) {
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
        if (!getClass().equals(iuVar.getClass())) {
            return getClass().getName().compareTo(iuVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(iuVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a13 = ja.a(this.f212a, iuVar.f212a)) != 0) {
            return a13;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(iuVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a12 = ja.a((Comparable) this.f211a, (Comparable) iuVar.f211a)) != 0) {
            return a12;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(iuVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a11 = ja.a(this.b, iuVar.b)) != 0) {
            return a11;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(iuVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a10 = ja.a(this.c, iuVar.c)) != 0) {
            return a10;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(iuVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a9 = ja.a(this.d, iuVar.d)) != 0) {
            return a9;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(iuVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a8 = ja.a(this.e, iuVar.e)) != 0) {
            return a8;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(iuVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a7 = ja.a(this.f, iuVar.f)) != 0) {
            return a7;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(iuVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a6 = ja.a(this.g, iuVar.g)) != 0) {
            return a6;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(iuVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a5 = ja.a(this.h, iuVar.h)) != 0) {
            return a5;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(iuVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (j() && (a4 = ja.a(this.i, iuVar.i)) != 0) {
            return a4;
        }
        int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(iuVar.k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (k() && (a3 = ja.a(this.f214a, iuVar.f214a)) != 0) {
            return a3;
        }
        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(iuVar.l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (!l() || (a2 = ja.a(this.f12813a, iuVar.f12813a)) == 0) {
            return 0;
        }
        return a2;
    }

    public iu a(String str) {
        this.b = str;
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
    public void a(com.xiaomi.push.jk r5) {
        /*
            r4 = this;
            r5.f()
        L_0x0003:
            com.xiaomi.push.jh r0 = r5.h()
            byte r1 = r0.b
            if (r1 != 0) goto L_0x0012
            r5.g()
            r4.a()
            return
        L_0x0012:
            short r1 = r0.c
            r2 = 1
            r3 = 11
            switch(r1) {
                case 1: goto L_0x00ae;
                case 2: goto L_0x009b;
                case 3: goto L_0x0090;
                case 4: goto L_0x0085;
                case 5: goto L_0x007a;
                case 6: goto L_0x006f;
                case 7: goto L_0x0064;
                case 8: goto L_0x0059;
                case 9: goto L_0x004e;
                case 10: goto L_0x0042;
                case 11: goto L_0x0032;
                case 12: goto L_0x0021;
                default: goto L_0x001a;
            }
        L_0x001a:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r5, r0)
            goto L_0x00b8
        L_0x0021:
            byte r1 = r0.b
            r3 = 10
            if (r1 != r3) goto L_0x001a
            long r0 = r5.t()
            r4.f12813a = r0
            r4.b((boolean) r2)
            goto L_0x00b8
        L_0x0032:
            byte r1 = r0.b
            r3 = 2
            if (r1 != r3) goto L_0x001a
            boolean r0 = r5.p()
            r4.f214a = r0
            r4.a((boolean) r2)
            goto L_0x00b8
        L_0x0042:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001a
            java.lang.String r0 = r5.v()
            r4.i = r0
            goto L_0x00b8
        L_0x004e:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001a
            java.lang.String r0 = r5.v()
            r4.h = r0
            goto L_0x00b8
        L_0x0059:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001a
            java.lang.String r0 = r5.v()
            r4.g = r0
            goto L_0x00b8
        L_0x0064:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001a
            java.lang.String r0 = r5.v()
            r4.f = r0
            goto L_0x00b8
        L_0x006f:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001a
            java.lang.String r0 = r5.v()
            r4.e = r0
            goto L_0x00b8
        L_0x007a:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001a
            java.lang.String r0 = r5.v()
            r4.d = r0
            goto L_0x00b8
        L_0x0085:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001a
            java.lang.String r0 = r5.v()
            r4.c = r0
            goto L_0x00b8
        L_0x0090:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001a
            java.lang.String r0 = r5.v()
            r4.b = r0
            goto L_0x00b8
        L_0x009b:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x001a
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r4.f211a = r0
            com.xiaomi.push.id r0 = r4.f211a
            r0.a((com.xiaomi.push.jk) r5)
            goto L_0x00b8
        L_0x00ae:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001a
            java.lang.String r0 = r5.v()
            r4.f212a = r0
        L_0x00b8:
            r5.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.iu.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f213a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m245a() {
        return this.f212a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m246a(iu iuVar) {
        if (iuVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = iuVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f212a.equals(iuVar.f212a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = iuVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f211a.compareTo(iuVar.f211a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = iuVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(iuVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = iuVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.c.equals(iuVar.c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = iuVar.e();
        if ((e2 || e3) && (!e2 || !e3 || !this.d.equals(iuVar.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = iuVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.e.equals(iuVar.e))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = iuVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.f.equals(iuVar.f))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = iuVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.g.equals(iuVar.g))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = iuVar.i();
        if ((i2 || i3) && (!i2 || !i3 || !this.h.equals(iuVar.h))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = iuVar.j();
        if ((j2 || j3) && (!j2 || !j3 || !this.i.equals(iuVar.i))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = iuVar.k();
        if ((k2 || k3) && (!k2 || !k3 || this.f214a != iuVar.f214a)) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = iuVar.l();
        if (l2 || l3) {
            return l2 && l3 && this.f12813a == iuVar.f12813a;
        }
        return true;
    }

    public iu b(String str) {
        this.c = str;
        return this;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(j);
        if (this.f212a != null && a()) {
            jkVar.a(k);
            jkVar.a(this.f212a);
            jkVar.b();
        }
        if (this.f211a != null && b()) {
            jkVar.a(l);
            this.f211a.b(jkVar);
            jkVar.b();
        }
        if (this.b != null) {
            jkVar.a(m);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.c != null) {
            jkVar.a(n);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (this.d != null && e()) {
            jkVar.a(o);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && f()) {
            jkVar.a(p);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f != null && g()) {
            jkVar.a(q);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.g != null && h()) {
            jkVar.a(r);
            jkVar.a(this.g);
            jkVar.b();
        }
        if (this.h != null && i()) {
            jkVar.a(s);
            jkVar.a(this.h);
            jkVar.b();
        }
        if (this.i != null && j()) {
            jkVar.a(t);
            jkVar.a(this.i);
            jkVar.b();
        }
        if (k()) {
            jkVar.a(u);
            jkVar.a(this.f214a);
            jkVar.b();
        }
        if (l()) {
            jkVar.a(v);
            jkVar.a(this.f12813a);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f213a.set(1, z);
    }

    public boolean b() {
        return this.f211a != null;
    }

    public iu c(String str) {
        this.d = str;
        return this;
    }

    public boolean c() {
        return this.b != null;
    }

    public iu d(String str) {
        this.f = str;
        return this;
    }

    public boolean d() {
        return this.c != null;
    }

    public iu e(String str) {
        this.g = str;
        return this;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof iu)) {
            return compareTo((iu) obj);
        }
        return false;
    }

    public boolean f() {
        return this.e != null;
    }

    public boolean g() {
        return this.f != null;
    }

    public boolean h() {
        return this.g != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.h != null;
    }

    public boolean j() {
        return this.i != null;
    }

    public boolean k() {
        return this.f213a.get(0);
    }

    public boolean l() {
        return this.f213a.get(1);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistration(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f212a == null ? "null" : this.f212a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f211a == null) {
                sb.append("null");
            } else {
                sb.append(this.f211a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.b == null ? "null" : this.b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.c == null ? "null" : this.c);
        if (e()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (f()) {
            sb.append(", ");
            sb.append("appVersion:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (g()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f == null ? "null" : this.f);
        }
        if (h()) {
            sb.append(", ");
            sb.append("token:");
            sb.append(this.g == null ? "null" : this.g);
        }
        if (i()) {
            sb.append(", ");
            sb.append("deviceId:");
            sb.append(this.h == null ? "null" : this.h);
        }
        if (j()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.i == null ? "null" : this.i);
        }
        if (k()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f214a);
        }
        if (l()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f12813a);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
