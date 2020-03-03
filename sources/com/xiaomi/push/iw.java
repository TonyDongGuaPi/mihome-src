package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.List;

public class iw implements iz<iw, Object>, Serializable, Cloneable {
    private static final jp g = new jp("XmPushActionUnSubscription");
    private static final jh h = new jh("", (byte) 11, 1);
    private static final jh i = new jh("", (byte) 12, 2);
    private static final jh j = new jh("", (byte) 11, 3);
    private static final jh k = new jh("", (byte) 11, 4);
    private static final jh l = new jh("", (byte) 11, 5);
    private static final jh m = new jh("", (byte) 11, 6);
    private static final jh n = new jh("", (byte) 11, 7);
    private static final jh o = new jh("", (byte) 15, 8);

    /* renamed from: a  reason: collision with root package name */
    public id f12815a;

    /* renamed from: a  reason: collision with other field name */
    public String f220a;

    /* renamed from: a  reason: collision with other field name */
    public List<String> f221a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;

    /* renamed from: a */
    public int compareTo(iw iwVar) {
        int a2;
        int a3;
        int a4;
        int a5;
        int a6;
        int a7;
        int a8;
        int a9;
        if (!getClass().equals(iwVar.getClass())) {
            return getClass().getName().compareTo(iwVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(iwVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a9 = ja.a(this.f220a, iwVar.f220a)) != 0) {
            return a9;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(iwVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a8 = ja.a((Comparable) this.f12815a, (Comparable) iwVar.f12815a)) != 0) {
            return a8;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(iwVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a7 = ja.a(this.b, iwVar.b)) != 0) {
            return a7;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(iwVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a6 = ja.a(this.c, iwVar.c)) != 0) {
            return a6;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(iwVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a5 = ja.a(this.d, iwVar.d)) != 0) {
            return a5;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(iwVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a4 = ja.a(this.e, iwVar.e)) != 0) {
            return a4;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(iwVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a3 = ja.a(this.f, iwVar.f)) != 0) {
            return a3;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(iwVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (!h() || (a2 = ja.a((List) this.f221a, (List) iwVar.f221a)) == 0) {
            return 0;
        }
        return a2;
    }

    public iw a(String str) {
        this.b = str;
        return this;
    }

    public void a() {
        if (this.b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new jl("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new jl("Required field 'topic' was not present! Struct: " + toString());
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
            r2 = 11
            switch(r1) {
                case 1: goto L_0x0092;
                case 2: goto L_0x007f;
                case 3: goto L_0x0074;
                case 4: goto L_0x0069;
                case 5: goto L_0x005e;
                case 6: goto L_0x0053;
                case 7: goto L_0x0048;
                case 8: goto L_0x0020;
                default: goto L_0x0019;
            }
        L_0x0019:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r5, r0)
            goto L_0x009c
        L_0x0020:
            byte r1 = r0.b
            r2 = 15
            if (r1 != r2) goto L_0x0019
            com.xiaomi.push.ji r0 = r5.l()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.b
            r1.<init>(r2)
            r4.f221a = r1
            r1 = 0
        L_0x0034:
            int r2 = r0.b
            if (r1 >= r2) goto L_0x0044
            java.lang.String r2 = r5.v()
            java.util.List<java.lang.String> r3 = r4.f221a
            r3.add(r2)
            int r1 = r1 + 1
            goto L_0x0034
        L_0x0044:
            r5.m()
            goto L_0x009c
        L_0x0048:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r5.v()
            r4.f = r0
            goto L_0x009c
        L_0x0053:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r5.v()
            r4.e = r0
            goto L_0x009c
        L_0x005e:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r5.v()
            r4.d = r0
            goto L_0x009c
        L_0x0069:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r5.v()
            r4.c = r0
            goto L_0x009c
        L_0x0074:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r5.v()
            r4.b = r0
            goto L_0x009c
        L_0x007f:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x0019
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r4.f12815a = r0
            com.xiaomi.push.id r0 = r4.f12815a
            r0.a((com.xiaomi.push.jk) r5)
            goto L_0x009c
        L_0x0092:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r5.v()
            r4.f220a = r0
        L_0x009c:
            r5.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.iw.a(com.xiaomi.push.jk):void");
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m250a() {
        return this.f220a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m251a(iw iwVar) {
        if (iwVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = iwVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f220a.equals(iwVar.f220a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = iwVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f12815a.compareTo(iwVar.f12815a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = iwVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(iwVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = iwVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.c.equals(iwVar.c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = iwVar.e();
        if ((e2 || e3) && (!e2 || !e3 || !this.d.equals(iwVar.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = iwVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.e.equals(iwVar.e))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = iwVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.f.equals(iwVar.f))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = iwVar.h();
        if (h2 || h3) {
            return h2 && h3 && this.f221a.equals(iwVar.f221a);
        }
        return true;
    }

    public iw b(String str) {
        this.c = str;
        return this;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(g);
        if (this.f220a != null && a()) {
            jkVar.a(h);
            jkVar.a(this.f220a);
            jkVar.b();
        }
        if (this.f12815a != null && b()) {
            jkVar.a(i);
            this.f12815a.b(jkVar);
            jkVar.b();
        }
        if (this.b != null) {
            jkVar.a(j);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.c != null) {
            jkVar.a(k);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (this.d != null) {
            jkVar.a(l);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && f()) {
            jkVar.a(m);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f != null && g()) {
            jkVar.a(n);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.f221a != null && h()) {
            jkVar.a(o);
            jkVar.a(new ji((byte) 11, this.f221a.size()));
            for (String a2 : this.f221a) {
                jkVar.a(a2);
            }
            jkVar.e();
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public boolean b() {
        return this.f12815a != null;
    }

    public iw c(String str) {
        this.d = str;
        return this;
    }

    public boolean c() {
        return this.b != null;
    }

    public iw d(String str) {
        this.e = str;
        return this;
    }

    public boolean d() {
        return this.c != null;
    }

    public iw e(String str) {
        this.f = str;
        return this;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof iw)) {
            return compareTo((iw) obj);
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
        return this.f221a != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnSubscription(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f220a == null ? "null" : this.f220a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f12815a == null) {
                sb.append("null");
            } else {
                sb.append(this.f12815a);
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
        sb.append(", ");
        sb.append("topic:");
        sb.append(this.d == null ? "null" : this.d);
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (g()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f == null ? "null" : this.f);
        }
        if (h()) {
            sb.append(", ");
            sb.append("aliases:");
            if (this.f221a == null) {
                sb.append("null");
            } else {
                sb.append(this.f221a);
            }
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
