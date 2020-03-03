package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Map;

public class ir implements iz<ir, Object>, Serializable, Cloneable {
    private static final jp i = new jp("XmPushActionSendMessage");
    private static final jh j = new jh("", (byte) 11, 1);
    private static final jh k = new jh("", (byte) 12, 2);
    private static final jh l = new jh("", (byte) 11, 3);
    private static final jh m = new jh("", (byte) 11, 4);
    private static final jh n = new jh("", (byte) 11, 5);
    private static final jh o = new jh("", (byte) 11, 6);
    private static final jh p = new jh("", (byte) 11, 7);
    private static final jh q = new jh("", (byte) 12, 8);
    private static final jh r = new jh("", (byte) 2, 9);
    private static final jh s = new jh("", (byte) 13, 10);
    private static final jh t = new jh("", (byte) 11, 11);
    private static final jh u = new jh("", (byte) 11, 12);

    /* renamed from: a  reason: collision with root package name */
    public ia f12810a;

    /* renamed from: a  reason: collision with other field name */
    public id f201a;

    /* renamed from: a  reason: collision with other field name */
    public String f202a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f203a = new BitSet(1);

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f204a;

    /* renamed from: a  reason: collision with other field name */
    public boolean f205a = true;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;

    /* renamed from: a */
    public int compareTo(ir irVar) {
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
        if (!getClass().equals(irVar.getClass())) {
            return getClass().getName().compareTo(irVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(irVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a13 = ja.a(this.f202a, irVar.f202a)) != 0) {
            return a13;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(irVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a12 = ja.a((Comparable) this.f201a, (Comparable) irVar.f201a)) != 0) {
            return a12;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(irVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a11 = ja.a(this.b, irVar.b)) != 0) {
            return a11;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(irVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a10 = ja.a(this.c, irVar.c)) != 0) {
            return a10;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(irVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a9 = ja.a(this.d, irVar.d)) != 0) {
            return a9;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(irVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a8 = ja.a(this.e, irVar.e)) != 0) {
            return a8;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(irVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a7 = ja.a(this.f, irVar.f)) != 0) {
            return a7;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(irVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a6 = ja.a((Comparable) this.f12810a, (Comparable) irVar.f12810a)) != 0) {
            return a6;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(irVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a5 = ja.a(this.f205a, irVar.f205a)) != 0) {
            return a5;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(irVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (j() && (a4 = ja.a((Map) this.f204a, (Map) irVar.f204a)) != 0) {
            return a4;
        }
        int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(irVar.k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (k() && (a3 = ja.a(this.g, irVar.g)) != 0) {
            return a3;
        }
        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(irVar.l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (!l() || (a2 = ja.a(this.h, irVar.h)) == 0) {
            return 0;
        }
        return a2;
    }

    public ia a() {
        return this.f12810a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m230a() {
        return this.b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m231a() {
        if (this.b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new jl("Required field 'appId' was not present! Struct: " + toString());
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
            r2 = 2
            r3 = 12
            r4 = 11
            switch(r1) {
                case 1: goto L_0x00d2;
                case 2: goto L_0x00c1;
                case 3: goto L_0x00b6;
                case 4: goto L_0x00ab;
                case 5: goto L_0x00a0;
                case 6: goto L_0x0095;
                case 7: goto L_0x008a;
                case 8: goto L_0x0079;
                case 9: goto L_0x006a;
                case 10: goto L_0x003b;
                case 11: goto L_0x002f;
                case 12: goto L_0x0023;
                default: goto L_0x001c;
            }
        L_0x001c:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r6, r0)
            goto L_0x00dc
        L_0x0023:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001c
            java.lang.String r0 = r6.v()
            r5.h = r0
            goto L_0x00dc
        L_0x002f:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001c
            java.lang.String r0 = r6.v()
            r5.g = r0
            goto L_0x00dc
        L_0x003b:
            byte r1 = r0.b
            r3 = 13
            if (r1 != r3) goto L_0x001c
            com.xiaomi.push.jj r0 = r6.j()
            java.util.HashMap r1 = new java.util.HashMap
            int r3 = r0.c
            int r3 = r3 * 2
            r1.<init>(r3)
            r5.f204a = r1
            r1 = 0
        L_0x0051:
            int r2 = r0.c
            if (r1 >= r2) goto L_0x0065
            java.lang.String r2 = r6.v()
            java.lang.String r3 = r6.v()
            java.util.Map<java.lang.String, java.lang.String> r4 = r5.f204a
            r4.put(r2, r3)
            int r1 = r1 + 1
            goto L_0x0051
        L_0x0065:
            r6.k()
            goto L_0x00dc
        L_0x006a:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x001c
            boolean r0 = r6.p()
            r5.f205a = r0
            r0 = 1
            r5.a((boolean) r0)
            goto L_0x00dc
        L_0x0079:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001c
            com.xiaomi.push.ia r0 = new com.xiaomi.push.ia
            r0.<init>()
            r5.f12810a = r0
            com.xiaomi.push.ia r0 = r5.f12810a
            r0.a((com.xiaomi.push.jk) r6)
            goto L_0x00dc
        L_0x008a:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001c
            java.lang.String r0 = r6.v()
            r5.f = r0
            goto L_0x00dc
        L_0x0095:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001c
            java.lang.String r0 = r6.v()
            r5.e = r0
            goto L_0x00dc
        L_0x00a0:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001c
            java.lang.String r0 = r6.v()
            r5.d = r0
            goto L_0x00dc
        L_0x00ab:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001c
            java.lang.String r0 = r6.v()
            r5.c = r0
            goto L_0x00dc
        L_0x00b6:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001c
            java.lang.String r0 = r6.v()
            r5.b = r0
            goto L_0x00dc
        L_0x00c1:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001c
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r5.f201a = r0
            com.xiaomi.push.id r0 = r5.f201a
            r0.a((com.xiaomi.push.jk) r6)
            goto L_0x00dc
        L_0x00d2:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001c
            java.lang.String r0 = r6.v()
            r5.f202a = r0
        L_0x00dc:
            r6.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ir.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f203a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m232a() {
        return this.f202a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m233a(ir irVar) {
        if (irVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = irVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f202a.equals(irVar.f202a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = irVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f201a.compareTo(irVar.f201a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = irVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(irVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = irVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.c.equals(irVar.c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = irVar.e();
        if ((e2 || e3) && (!e2 || !e3 || !this.d.equals(irVar.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = irVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.e.equals(irVar.e))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = irVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.f.equals(irVar.f))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = irVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.f12810a.compareTo(irVar.f12810a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = irVar.i();
        if ((i2 || i3) && (!i2 || !i3 || this.f205a != irVar.f205a)) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = irVar.j();
        if ((j2 || j3) && (!j2 || !j3 || !this.f204a.equals(irVar.f204a))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = irVar.k();
        if ((k2 || k3) && (!k2 || !k3 || !this.g.equals(irVar.g))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = irVar.l();
        if (l2 || l3) {
            return l2 && l3 && this.h.equals(irVar.h);
        }
        return true;
    }

    public String b() {
        return this.c;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(i);
        if (this.f202a != null && a()) {
            jkVar.a(j);
            jkVar.a(this.f202a);
            jkVar.b();
        }
        if (this.f201a != null && b()) {
            jkVar.a(k);
            this.f201a.b(jkVar);
            jkVar.b();
        }
        if (this.b != null) {
            jkVar.a(l);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.c != null) {
            jkVar.a(m);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (this.d != null && e()) {
            jkVar.a(n);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && f()) {
            jkVar.a(o);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f != null && g()) {
            jkVar.a(p);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.f12810a != null && h()) {
            jkVar.a(q);
            this.f12810a.b(jkVar);
            jkVar.b();
        }
        if (i()) {
            jkVar.a(r);
            jkVar.a(this.f205a);
            jkVar.b();
        }
        if (this.f204a != null && j()) {
            jkVar.a(s);
            jkVar.a(new jj((byte) 11, (byte) 11, this.f204a.size()));
            for (Map.Entry next : this.f204a.entrySet()) {
                jkVar.a((String) next.getKey());
                jkVar.a((String) next.getValue());
            }
            jkVar.d();
            jkVar.b();
        }
        if (this.g != null && k()) {
            jkVar.a(t);
            jkVar.a(this.g);
            jkVar.b();
        }
        if (this.h != null && l()) {
            jkVar.a(u);
            jkVar.a(this.h);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m234b() {
        return this.f201a != null;
    }

    public String c() {
        return this.e;
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m235c() {
        return this.b != null;
    }

    public String d() {
        return this.f;
    }

    /* renamed from: d  reason: collision with other method in class */
    public boolean m236d() {
        return this.c != null;
    }

    public String e() {
        return this.g;
    }

    /* renamed from: e  reason: collision with other method in class */
    public boolean m237e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ir)) {
            return compareTo((ir) obj);
        }
        return false;
    }

    public String f() {
        return this.h;
    }

    /* renamed from: f  reason: collision with other method in class */
    public boolean m238f() {
        return this.e != null;
    }

    public boolean g() {
        return this.f != null;
    }

    public boolean h() {
        return this.f12810a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f203a.get(0);
    }

    public boolean j() {
        return this.f204a != null;
    }

    public boolean k() {
        return this.g != null;
    }

    public boolean l() {
        return this.h != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendMessage(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f202a == null ? "null" : this.f202a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f201a == null) {
                sb.append("null");
            } else {
                sb.append(this.f201a);
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
            sb.append("packageName:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (f()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (g()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.f == null ? "null" : this.f);
        }
        if (h()) {
            sb.append(", ");
            sb.append("message:");
            if (this.f12810a == null) {
                sb.append("null");
            } else {
                sb.append(this.f12810a);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f205a);
        }
        if (j()) {
            sb.append(", ");
            sb.append("params:");
            if (this.f204a == null) {
                sb.append("null");
            } else {
                sb.append(this.f204a);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.g == null ? "null" : this.g);
        }
        if (l()) {
            sb.append(", ");
            sb.append("userAccount:");
            sb.append(this.h == null ? "null" : this.h);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
