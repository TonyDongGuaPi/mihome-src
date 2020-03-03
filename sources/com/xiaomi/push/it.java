package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class it implements iz<it, Object>, Serializable, Cloneable {
    private static final jp h = new jp("XmPushActionSubscriptionResult");
    private static final jh i = new jh("", (byte) 11, 1);
    private static final jh j = new jh("", (byte) 12, 2);
    private static final jh k = new jh("", (byte) 11, 3);
    private static final jh l = new jh("", (byte) 11, 4);
    private static final jh m = new jh("", (byte) 10, 6);
    private static final jh n = new jh("", (byte) 11, 7);
    private static final jh o = new jh("", (byte) 11, 8);
    private static final jh p = new jh("", (byte) 11, 9);
    private static final jh q = new jh("", (byte) 11, 10);

    /* renamed from: a  reason: collision with root package name */
    public long f12812a;

    /* renamed from: a  reason: collision with other field name */
    public id f208a;

    /* renamed from: a  reason: collision with other field name */
    public String f209a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f210a = new BitSet(1);
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;

    /* renamed from: a */
    public int compareTo(it itVar) {
        int a2;
        int a3;
        int a4;
        int a5;
        int a6;
        int a7;
        int a8;
        int a9;
        int a10;
        if (!getClass().equals(itVar.getClass())) {
            return getClass().getName().compareTo(itVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(itVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a10 = ja.a(this.f209a, itVar.f209a)) != 0) {
            return a10;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(itVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a9 = ja.a((Comparable) this.f208a, (Comparable) itVar.f208a)) != 0) {
            return a9;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(itVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a8 = ja.a(this.b, itVar.b)) != 0) {
            return a8;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(itVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a7 = ja.a(this.c, itVar.c)) != 0) {
            return a7;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(itVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a6 = ja.a(this.f12812a, itVar.f12812a)) != 0) {
            return a6;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(itVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a5 = ja.a(this.d, itVar.d)) != 0) {
            return a5;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(itVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a4 = ja.a(this.e, itVar.e)) != 0) {
            return a4;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(itVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a3 = ja.a(this.f, itVar.f)) != 0) {
            return a3;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(itVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (!i() || (a2 = ja.a(this.g, itVar.g)) == 0) {
            return 0;
        }
        return a2;
    }

    public String a() {
        return this.e;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m241a() {
        if (this.b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.push.jk r4) {
        /*
            r3 = this;
            r4.f()
        L_0x0003:
            com.xiaomi.push.jh r0 = r4.h()
            byte r1 = r0.b
            if (r1 != 0) goto L_0x0012
            r4.g()
            r3.a()
            return
        L_0x0012:
            short r1 = r0.c
            r2 = 11
            switch(r1) {
                case 1: goto L_0x0086;
                case 2: goto L_0x0073;
                case 3: goto L_0x0068;
                case 4: goto L_0x005d;
                case 5: goto L_0x0019;
                case 6: goto L_0x004c;
                case 7: goto L_0x0041;
                case 8: goto L_0x0036;
                case 9: goto L_0x002b;
                case 10: goto L_0x0020;
                default: goto L_0x0019;
            }
        L_0x0019:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r4, r0)
            goto L_0x0090
        L_0x0020:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r4.v()
            r3.g = r0
            goto L_0x0090
        L_0x002b:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r4.v()
            r3.f = r0
            goto L_0x0090
        L_0x0036:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r4.v()
            r3.e = r0
            goto L_0x0090
        L_0x0041:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r4.v()
            r3.d = r0
            goto L_0x0090
        L_0x004c:
            byte r1 = r0.b
            r2 = 10
            if (r1 != r2) goto L_0x0019
            long r0 = r4.t()
            r3.f12812a = r0
            r0 = 1
            r3.a((boolean) r0)
            goto L_0x0090
        L_0x005d:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r4.v()
            r3.c = r0
            goto L_0x0090
        L_0x0068:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r4.v()
            r3.b = r0
            goto L_0x0090
        L_0x0073:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x0019
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r3.f208a = r0
            com.xiaomi.push.id r0 = r3.f208a
            r0.a((com.xiaomi.push.jk) r4)
            goto L_0x0090
        L_0x0086:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r4.v()
            r3.f209a = r0
        L_0x0090:
            r4.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.it.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f210a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m242a() {
        return this.f209a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m243a(it itVar) {
        if (itVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = itVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f209a.equals(itVar.f209a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = itVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f208a.compareTo(itVar.f208a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = itVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(itVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = itVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.c.equals(itVar.c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = itVar.e();
        if ((e2 || e3) && (!e2 || !e3 || this.f12812a != itVar.f12812a)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = itVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.d.equals(itVar.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = itVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.e.equals(itVar.e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = itVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.f.equals(itVar.f))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = itVar.i();
        if (i2 || i3) {
            return i2 && i3 && this.g.equals(itVar.g);
        }
        return true;
    }

    public String b() {
        return this.g;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(h);
        if (this.f209a != null && a()) {
            jkVar.a(i);
            jkVar.a(this.f209a);
            jkVar.b();
        }
        if (this.f208a != null && b()) {
            jkVar.a(j);
            this.f208a.b(jkVar);
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
        if (e()) {
            jkVar.a(m);
            jkVar.a(this.f12812a);
            jkVar.b();
        }
        if (this.d != null && f()) {
            jkVar.a(n);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && g()) {
            jkVar.a(o);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f != null && h()) {
            jkVar.a(p);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.g != null && i()) {
            jkVar.a(q);
            jkVar.a(this.g);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m244b() {
        return this.f208a != null;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return this.f210a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof it)) {
            return compareTo((it) obj);
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

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscriptionResult(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f209a == null ? "null" : this.f209a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f208a == null) {
                sb.append("null");
            } else {
                sb.append(this.f208a);
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
            sb.append("errorCode:");
            sb.append(this.f12812a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f == null ? "null" : this.f);
        }
        if (i()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.g == null ? "null" : this.g);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
