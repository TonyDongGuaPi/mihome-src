package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class iv implements iz<iv, Object>, Serializable, Cloneable {
    private static final jp f = new jp("XmPushActionUnRegistrationResult");
    private static final jh g = new jh("", (byte) 11, 1);
    private static final jh h = new jh("", (byte) 12, 2);
    private static final jh i = new jh("", (byte) 11, 3);
    private static final jh j = new jh("", (byte) 11, 4);
    private static final jh k = new jh("", (byte) 10, 6);
    private static final jh l = new jh("", (byte) 11, 7);
    private static final jh m = new jh("", (byte) 11, 8);
    private static final jh n = new jh("", (byte) 10, 9);
    private static final jh o = new jh("", (byte) 10, 10);

    /* renamed from: a  reason: collision with root package name */
    public long f12814a;

    /* renamed from: a  reason: collision with other field name */
    public id f215a;

    /* renamed from: a  reason: collision with other field name */
    public String f216a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f217a = new BitSet(3);
    public long b;

    /* renamed from: b  reason: collision with other field name */
    public String f218b;
    public long c;

    /* renamed from: c  reason: collision with other field name */
    public String f219c;
    public String d;
    public String e;

    /* renamed from: a */
    public int compareTo(iv ivVar) {
        int a2;
        int a3;
        int a4;
        int a5;
        int a6;
        int a7;
        int a8;
        int a9;
        int a10;
        if (!getClass().equals(ivVar.getClass())) {
            return getClass().getName().compareTo(ivVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ivVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a10 = ja.a(this.f216a, ivVar.f216a)) != 0) {
            return a10;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ivVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a9 = ja.a((Comparable) this.f215a, (Comparable) ivVar.f215a)) != 0) {
            return a9;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ivVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a8 = ja.a(this.f218b, ivVar.f218b)) != 0) {
            return a8;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ivVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a7 = ja.a(this.f219c, ivVar.f219c)) != 0) {
            return a7;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ivVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a6 = ja.a(this.f12814a, ivVar.f12814a)) != 0) {
            return a6;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ivVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a5 = ja.a(this.d, ivVar.d)) != 0) {
            return a5;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ivVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a4 = ja.a(this.e, ivVar.e)) != 0) {
            return a4;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ivVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a3 = ja.a(this.b, ivVar.b)) != 0) {
            return a3;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ivVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (!i() || (a2 = ja.a(this.c, ivVar.c)) == 0) {
            return 0;
        }
        return a2;
    }

    public String a() {
        return this.e;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m247a() {
        if (this.f218b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f219c == null) {
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
            if (r1 != 0) goto L_0x0033
            r6.g()
            boolean r6 = r5.e()
            if (r6 == 0) goto L_0x0018
            r5.a()
            return
        L_0x0018:
            com.xiaomi.push.jl r6 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'errorCode' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r5.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L_0x0033:
            short r1 = r0.c
            r2 = 1
            r3 = 10
            r4 = 11
            switch(r1) {
                case 1: goto L_0x00ad;
                case 2: goto L_0x009a;
                case 3: goto L_0x008f;
                case 4: goto L_0x0084;
                case 5: goto L_0x003d;
                case 6: goto L_0x0076;
                case 7: goto L_0x006b;
                case 8: goto L_0x0060;
                case 9: goto L_0x0052;
                case 10: goto L_0x0044;
                default: goto L_0x003d;
            }
        L_0x003d:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r6, r0)
            goto L_0x00b7
        L_0x0044:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003d
            long r0 = r6.t()
            r5.c = r0
            r5.c(r2)
            goto L_0x00b7
        L_0x0052:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003d
            long r0 = r6.t()
            r5.b = r0
            r5.b((boolean) r2)
            goto L_0x00b7
        L_0x0060:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x003d
            java.lang.String r0 = r6.v()
            r5.e = r0
            goto L_0x00b7
        L_0x006b:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x003d
            java.lang.String r0 = r6.v()
            r5.d = r0
            goto L_0x00b7
        L_0x0076:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003d
            long r0 = r6.t()
            r5.f12814a = r0
            r5.a((boolean) r2)
            goto L_0x00b7
        L_0x0084:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x003d
            java.lang.String r0 = r6.v()
            r5.f219c = r0
            goto L_0x00b7
        L_0x008f:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x003d
            java.lang.String r0 = r6.v()
            r5.f218b = r0
            goto L_0x00b7
        L_0x009a:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x003d
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r5.f215a = r0
            com.xiaomi.push.id r0 = r5.f215a
            r0.a((com.xiaomi.push.jk) r6)
            goto L_0x00b7
        L_0x00ad:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x003d
            java.lang.String r0 = r6.v()
            r5.f216a = r0
        L_0x00b7:
            r6.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.iv.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f217a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m248a() {
        return this.f216a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m249a(iv ivVar) {
        if (ivVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = ivVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f216a.equals(ivVar.f216a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = ivVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f215a.compareTo(ivVar.f215a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = ivVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.f218b.equals(ivVar.f218b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = ivVar.d();
        if (((d2 || d3) && (!d2 || !d3 || !this.f219c.equals(ivVar.f219c))) || this.f12814a != ivVar.f12814a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = ivVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.d.equals(ivVar.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = ivVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.e.equals(ivVar.e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = ivVar.h();
        if ((h2 || h3) && (!h2 || !h3 || this.b != ivVar.b)) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = ivVar.i();
        if (i2 || i3) {
            return i2 && i3 && this.c == ivVar.c;
        }
        return true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(f);
        if (this.f216a != null && a()) {
            jkVar.a(g);
            jkVar.a(this.f216a);
            jkVar.b();
        }
        if (this.f215a != null && b()) {
            jkVar.a(h);
            this.f215a.b(jkVar);
            jkVar.b();
        }
        if (this.f218b != null) {
            jkVar.a(i);
            jkVar.a(this.f218b);
            jkVar.b();
        }
        if (this.f219c != null) {
            jkVar.a(j);
            jkVar.a(this.f219c);
            jkVar.b();
        }
        jkVar.a(k);
        jkVar.a(this.f12814a);
        jkVar.b();
        if (this.d != null && f()) {
            jkVar.a(l);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && g()) {
            jkVar.a(m);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (h()) {
            jkVar.a(n);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (i()) {
            jkVar.a(o);
            jkVar.a(this.c);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f217a.set(1, z);
    }

    public boolean b() {
        return this.f215a != null;
    }

    public void c(boolean z) {
        this.f217a.set(2, z);
    }

    public boolean c() {
        return this.f218b != null;
    }

    public boolean d() {
        return this.f219c != null;
    }

    public boolean e() {
        return this.f217a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof iv)) {
            return compareTo((iv) obj);
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
        return this.f217a.get(1);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f217a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistrationResult(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f216a == null ? "null" : this.f216a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f215a == null) {
                sb.append("null");
            } else {
                sb.append(this.f215a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f218b == null ? "null" : this.f218b);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f219c == null ? "null" : this.f219c);
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f12814a);
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("unRegisteredAt:");
            sb.append(this.b);
        }
        if (i()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.c);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
