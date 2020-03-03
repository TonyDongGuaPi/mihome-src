package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Map;

public class hs implements iz<hs, Object>, Serializable, Cloneable {
    private static final jp h = new jp("ClientUploadDataItem");
    private static final jh i = new jh("", (byte) 11, 1);
    private static final jh j = new jh("", (byte) 11, 2);
    private static final jh k = new jh("", (byte) 11, 3);
    private static final jh l = new jh("", (byte) 10, 4);
    private static final jh m = new jh("", (byte) 10, 5);
    private static final jh n = new jh("", (byte) 2, 6);
    private static final jh o = new jh("", (byte) 11, 7);
    private static final jh p = new jh("", (byte) 11, 8);
    private static final jh q = new jh("", (byte) 11, 9);
    private static final jh r = new jh("", (byte) 13, 10);
    private static final jh s = new jh("", (byte) 11, 11);

    /* renamed from: a  reason: collision with root package name */
    public long f12784a;

    /* renamed from: a  reason: collision with other field name */
    public String f96a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f97a = new BitSet(3);

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f98a;

    /* renamed from: a  reason: collision with other field name */
    public boolean f99a;
    public long b;

    /* renamed from: b  reason: collision with other field name */
    public String f100b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;

    /* renamed from: a */
    public int compareTo(hs hsVar) {
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
        if (!getClass().equals(hsVar.getClass())) {
            return getClass().getName().compareTo(hsVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(hsVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a12 = ja.a(this.f96a, hsVar.f96a)) != 0) {
            return a12;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hsVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a11 = ja.a(this.f100b, hsVar.f100b)) != 0) {
            return a11;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hsVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a10 = ja.a(this.c, hsVar.c)) != 0) {
            return a10;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(hsVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a9 = ja.a(this.f12784a, hsVar.f12784a)) != 0) {
            return a9;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(hsVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a8 = ja.a(this.b, hsVar.b)) != 0) {
            return a8;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(hsVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a7 = ja.a(this.f99a, hsVar.f99a)) != 0) {
            return a7;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(hsVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a6 = ja.a(this.d, hsVar.d)) != 0) {
            return a6;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(hsVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a5 = ja.a(this.e, hsVar.e)) != 0) {
            return a5;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(hsVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a4 = ja.a(this.f, hsVar.f)) != 0) {
            return a4;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(hsVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (j() && (a3 = ja.a((Map) this.f98a, (Map) hsVar.f98a)) != 0) {
            return a3;
        }
        int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(hsVar.k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (!k() || (a2 = ja.a(this.g, hsVar.g)) == 0) {
            return 0;
        }
        return a2;
    }

    public long a() {
        return this.b;
    }

    public hs a(long j2) {
        this.f12784a = j2;
        a(true);
        return this;
    }

    public hs a(String str) {
        this.f96a = str;
        return this;
    }

    public hs a(boolean z) {
        this.f99a = z;
        c(true);
        return this;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m132a() {
        return this.f96a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m133a() {
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
            r2 = 2
            r3 = 10
            r4 = 1
            r5 = 11
            switch(r1) {
                case 1: goto L_0x00c0;
                case 2: goto L_0x00b5;
                case 3: goto L_0x00aa;
                case 4: goto L_0x009c;
                case 5: goto L_0x008e;
                case 6: goto L_0x0080;
                case 7: goto L_0x0075;
                case 8: goto L_0x006a;
                case 9: goto L_0x005f;
                case 10: goto L_0x0030;
                case 11: goto L_0x0024;
                default: goto L_0x001d;
            }
        L_0x001d:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r7, r0)
            goto L_0x00ca
        L_0x0024:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001d
            java.lang.String r0 = r7.v()
            r6.g = r0
            goto L_0x00ca
        L_0x0030:
            byte r1 = r0.b
            r3 = 13
            if (r1 != r3) goto L_0x001d
            com.xiaomi.push.jj r0 = r7.j()
            java.util.HashMap r1 = new java.util.HashMap
            int r3 = r0.c
            int r3 = r3 * 2
            r1.<init>(r3)
            r6.f98a = r1
            r1 = 0
        L_0x0046:
            int r2 = r0.c
            if (r1 >= r2) goto L_0x005a
            java.lang.String r2 = r7.v()
            java.lang.String r3 = r7.v()
            java.util.Map<java.lang.String, java.lang.String> r4 = r6.f98a
            r4.put(r2, r3)
            int r1 = r1 + 1
            goto L_0x0046
        L_0x005a:
            r7.k()
            goto L_0x00ca
        L_0x005f:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001d
            java.lang.String r0 = r7.v()
            r6.f = r0
            goto L_0x00ca
        L_0x006a:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001d
            java.lang.String r0 = r7.v()
            r6.e = r0
            goto L_0x00ca
        L_0x0075:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001d
            java.lang.String r0 = r7.v()
            r6.d = r0
            goto L_0x00ca
        L_0x0080:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x001d
            boolean r0 = r7.p()
            r6.f99a = r0
            r6.c((boolean) r4)
            goto L_0x00ca
        L_0x008e:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001d
            long r0 = r7.t()
            r6.b = r0
            r6.b((boolean) r4)
            goto L_0x00ca
        L_0x009c:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001d
            long r0 = r7.t()
            r6.f12784a = r0
            r6.a((boolean) r4)
            goto L_0x00ca
        L_0x00aa:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001d
            java.lang.String r0 = r7.v()
            r6.c = r0
            goto L_0x00ca
        L_0x00b5:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001d
            java.lang.String r0 = r7.v()
            r6.f100b = r0
            goto L_0x00ca
        L_0x00c0:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x001d
            java.lang.String r0 = r7.v()
            r6.f96a = r0
        L_0x00ca:
            r7.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hs.a(com.xiaomi.push.jk):void");
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m134a(boolean z) {
        this.f97a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m135a() {
        return this.f96a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m136a(hs hsVar) {
        if (hsVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = hsVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f96a.equals(hsVar.f96a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = hsVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f100b.equals(hsVar.f100b))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = hsVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.c.equals(hsVar.c))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = hsVar.d();
        if ((d2 || d3) && (!d2 || !d3 || this.f12784a != hsVar.f12784a)) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = hsVar.e();
        if ((e2 || e3) && (!e2 || !e3 || this.b != hsVar.b)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = hsVar.f();
        if ((f2 || f3) && (!f2 || !f3 || this.f99a != hsVar.f99a)) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = hsVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.d.equals(hsVar.d))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = hsVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.e.equals(hsVar.e))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = hsVar.i();
        if ((i2 || i3) && (!i2 || !i3 || !this.f.equals(hsVar.f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = hsVar.j();
        if ((j2 || j3) && (!j2 || !j3 || !this.f98a.equals(hsVar.f98a))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = hsVar.k();
        if (k2 || k3) {
            return k2 && k3 && this.g.equals(hsVar.g);
        }
        return true;
    }

    public hs b(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public hs b(String str) {
        this.f100b = str;
        return this;
    }

    public String b() {
        return this.c;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(h);
        if (this.f96a != null && a()) {
            jkVar.a(i);
            jkVar.a(this.f96a);
            jkVar.b();
        }
        if (this.f100b != null && b()) {
            jkVar.a(j);
            jkVar.a(this.f100b);
            jkVar.b();
        }
        if (this.c != null && c()) {
            jkVar.a(k);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (d()) {
            jkVar.a(l);
            jkVar.a(this.f12784a);
            jkVar.b();
        }
        if (e()) {
            jkVar.a(m);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (f()) {
            jkVar.a(n);
            jkVar.a(this.f99a);
            jkVar.b();
        }
        if (this.d != null && g()) {
            jkVar.a(o);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && h()) {
            jkVar.a(p);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f != null && i()) {
            jkVar.a(q);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.f98a != null && j()) {
            jkVar.a(r);
            jkVar.a(new jj((byte) 11, (byte) 11, this.f98a.size()));
            for (Map.Entry next : this.f98a.entrySet()) {
                jkVar.a((String) next.getKey());
                jkVar.a((String) next.getValue());
            }
            jkVar.d();
            jkVar.b();
        }
        if (this.g != null && k()) {
            jkVar.a(s);
            jkVar.a(this.g);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f97a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m137b() {
        return this.f100b != null;
    }

    public hs c(String str) {
        this.c = str;
        return this;
    }

    public String c() {
        return this.e;
    }

    public void c(boolean z) {
        this.f97a.set(2, z);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m138c() {
        return this.c != null;
    }

    public hs d(String str) {
        this.d = str;
        return this;
    }

    public String d() {
        return this.f;
    }

    /* renamed from: d  reason: collision with other method in class */
    public boolean m139d() {
        return this.f97a.get(0);
    }

    public hs e(String str) {
        this.e = str;
        return this;
    }

    public String e() {
        return this.g;
    }

    /* renamed from: e  reason: collision with other method in class */
    public boolean m140e() {
        return this.f97a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hs)) {
            return compareTo((hs) obj);
        }
        return false;
    }

    public hs f(String str) {
        this.f = str;
        return this;
    }

    public boolean f() {
        return this.f97a.get(2);
    }

    public hs g(String str) {
        this.g = str;
        return this;
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
        return this.f98a != null;
    }

    public boolean k() {
        return this.g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ClientUploadDataItem(");
        if (a()) {
            sb.append("channel:");
            sb.append(this.f96a == null ? "null" : this.f96a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("data:");
            sb.append(this.f100b == null ? "null" : this.f100b);
            z = false;
        }
        if (c()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("name:");
            sb.append(this.c == null ? "null" : this.c);
            z = false;
        }
        if (d()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("counter:");
            sb.append(this.f12784a);
            z = false;
        }
        if (e()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("timestamp:");
            sb.append(this.b);
            z = false;
        }
        if (f()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("fromSdk:");
            sb.append(this.f99a);
            z = false;
        }
        if (g()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("category:");
            sb.append(this.d == null ? "null" : this.d);
            z = false;
        }
        if (h()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("sourcePackage:");
            sb.append(this.e == null ? "null" : this.e);
            z = false;
        }
        if (i()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("id:");
            sb.append(this.f == null ? "null" : this.f);
            z = false;
        }
        if (j()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("extra:");
            if (this.f98a == null) {
                sb.append("null");
            } else {
                sb.append(this.f98a);
            }
            z = false;
        }
        if (k()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("pkgName:");
            sb.append(this.g == null ? "null" : this.g);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
