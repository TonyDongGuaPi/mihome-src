package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Map;

/* renamed from: com.xiaomi.push.if  reason: invalid class name */
public class Cif implements iz<Cif, Object>, Serializable, Cloneable {
    private static final jp h = new jp("XmPushActionAckNotification");
    private static final jh i = new jh("", (byte) 11, 1);
    private static final jh j = new jh("", (byte) 12, 2);
    private static final jh k = new jh("", (byte) 11, 3);
    private static final jh l = new jh("", (byte) 11, 4);
    private static final jh m = new jh("", (byte) 11, 5);
    private static final jh n = new jh("", (byte) 10, 7);
    private static final jh o = new jh("", (byte) 11, 8);
    private static final jh p = new jh("", (byte) 13, 9);
    private static final jh q = new jh("", (byte) 11, 10);
    private static final jh r = new jh("", (byte) 11, 11);

    /* renamed from: a  reason: collision with root package name */
    public long f12798a = 0;

    /* renamed from: a  reason: collision with other field name */
    public id f150a;

    /* renamed from: a  reason: collision with other field name */
    public String f151a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f152a = new BitSet(1);

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f153a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;

    /* renamed from: a */
    public int compareTo(Cif ifVar) {
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
        if (!getClass().equals(ifVar.getClass())) {
            return getClass().getName().compareTo(ifVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ifVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a11 = ja.a(this.f151a, ifVar.f151a)) != 0) {
            return a11;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ifVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a10 = ja.a((Comparable) this.f150a, (Comparable) ifVar.f150a)) != 0) {
            return a10;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ifVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a9 = ja.a(this.b, ifVar.b)) != 0) {
            return a9;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ifVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a8 = ja.a(this.c, ifVar.c)) != 0) {
            return a8;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ifVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a7 = ja.a(this.d, ifVar.d)) != 0) {
            return a7;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ifVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a6 = ja.a(this.f12798a, ifVar.f12798a)) != 0) {
            return a6;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ifVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a5 = ja.a(this.e, ifVar.e)) != 0) {
            return a5;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ifVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a4 = ja.a((Map) this.f153a, (Map) ifVar.f153a)) != 0) {
            return a4;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ifVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a3 = ja.a(this.f, ifVar.f)) != 0) {
            return a3;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ifVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (!j() || (a2 = ja.a(this.g, ifVar.g)) == 0) {
            return 0;
        }
        return a2;
    }

    public String a() {
        return this.b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public Map<String, String> m178a() {
        return this.f153a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m179a() {
        if (this.b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.push.jk r6) {
        /*
            r5 = this;
            r6.f()
        L_0x0003:
            com.xiaomi.push.jh r0 = r6.h()
            byte r1 = r0.b
            if (r1 != 0) goto L_0x0012
            r6.g()
            r5.a()
            return
        L_0x0012:
            short r1 = r0.c
            r2 = 11
            switch(r1) {
                case 1: goto L_0x00b6;
                case 2: goto L_0x00a3;
                case 3: goto L_0x0098;
                case 4: goto L_0x008d;
                case 5: goto L_0x0082;
                case 6: goto L_0x0019;
                case 7: goto L_0x0071;
                case 8: goto L_0x0066;
                case 9: goto L_0x0038;
                case 10: goto L_0x002c;
                case 11: goto L_0x0020;
                default: goto L_0x0019;
            }
        L_0x0019:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r6, r0)
            goto L_0x00c0
        L_0x0020:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r6.v()
            r5.g = r0
            goto L_0x00c0
        L_0x002c:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r6.v()
            r5.f = r0
            goto L_0x00c0
        L_0x0038:
            byte r1 = r0.b
            r2 = 13
            if (r1 != r2) goto L_0x0019
            com.xiaomi.push.jj r0 = r6.j()
            java.util.HashMap r1 = new java.util.HashMap
            int r2 = r0.c
            int r2 = r2 * 2
            r1.<init>(r2)
            r5.f153a = r1
            r1 = 0
        L_0x004e:
            int r2 = r0.c
            if (r1 >= r2) goto L_0x0062
            java.lang.String r2 = r6.v()
            java.lang.String r3 = r6.v()
            java.util.Map<java.lang.String, java.lang.String> r4 = r5.f153a
            r4.put(r2, r3)
            int r1 = r1 + 1
            goto L_0x004e
        L_0x0062:
            r6.k()
            goto L_0x00c0
        L_0x0066:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r6.v()
            r5.e = r0
            goto L_0x00c0
        L_0x0071:
            byte r1 = r0.b
            r2 = 10
            if (r1 != r2) goto L_0x0019
            long r0 = r6.t()
            r5.f12798a = r0
            r0 = 1
            r5.a((boolean) r0)
            goto L_0x00c0
        L_0x0082:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r6.v()
            r5.d = r0
            goto L_0x00c0
        L_0x008d:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r6.v()
            r5.c = r0
            goto L_0x00c0
        L_0x0098:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r6.v()
            r5.b = r0
            goto L_0x00c0
        L_0x00a3:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x0019
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r5.f150a = r0
            com.xiaomi.push.id r0 = r5.f150a
            r0.a((com.xiaomi.push.jk) r6)
            goto L_0x00c0
        L_0x00b6:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r6.v()
            r5.f151a = r0
        L_0x00c0:
            r6.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.Cif.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f152a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m180a() {
        return this.f151a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m181a(Cif ifVar) {
        if (ifVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = ifVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f151a.equals(ifVar.f151a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = ifVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f150a.compareTo(ifVar.f150a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = ifVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(ifVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = ifVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.c.equals(ifVar.c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = ifVar.e();
        if ((e2 || e3) && (!e2 || !e3 || !this.d.equals(ifVar.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = ifVar.f();
        if ((f2 || f3) && (!f2 || !f3 || this.f12798a != ifVar.f12798a)) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = ifVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.e.equals(ifVar.e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = ifVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.f153a.equals(ifVar.f153a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = ifVar.i();
        if ((i2 || i3) && (!i2 || !i3 || !this.f.equals(ifVar.f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = ifVar.j();
        if (j2 || j3) {
            return j2 && j3 && this.g.equals(ifVar.g);
        }
        return true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(h);
        if (this.f151a != null && a()) {
            jkVar.a(i);
            jkVar.a(this.f151a);
            jkVar.b();
        }
        if (this.f150a != null && b()) {
            jkVar.a(j);
            this.f150a.b(jkVar);
            jkVar.b();
        }
        if (this.b != null) {
            jkVar.a(k);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.c != null && d()) {
            jkVar.a(l);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (this.d != null && e()) {
            jkVar.a(m);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (f()) {
            jkVar.a(n);
            jkVar.a(this.f12798a);
            jkVar.b();
        }
        if (this.e != null && g()) {
            jkVar.a(o);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f153a != null && h()) {
            jkVar.a(p);
            jkVar.a(new jj((byte) 11, (byte) 11, this.f153a.size()));
            for (Map.Entry next : this.f153a.entrySet()) {
                jkVar.a((String) next.getKey());
                jkVar.a((String) next.getValue());
            }
            jkVar.d();
            jkVar.b();
        }
        if (this.f != null && i()) {
            jkVar.a(q);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.g != null && j()) {
            jkVar.a(r);
            jkVar.a(this.g);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public boolean b() {
        return this.f150a != null;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Cif)) {
            return compareTo((Cif) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f152a.get(0);
    }

    public boolean g() {
        return this.e != null;
    }

    public boolean h() {
        return this.f153a != null;
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

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionAckNotification(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f151a == null ? "null" : this.f151a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f150a == null) {
                sb.append("null");
            } else {
                sb.append(this.f150a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.b == null ? "null" : this.b);
        if (d()) {
            sb.append(", ");
            sb.append("appId:");
            sb.append(this.c == null ? "null" : this.c);
        }
        if (e()) {
            sb.append(", ");
            sb.append("type:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (f()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f12798a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("extra:");
            if (this.f153a == null) {
                sb.append("null");
            } else {
                sb.append(this.f153a);
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
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
