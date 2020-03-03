package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class ii implements iz<ii, Object>, Serializable, Cloneable {
    private static final jp f = new jp("XmPushActionCommand");
    private static final jh g = new jh("", (byte) 12, 2);
    private static final jh h = new jh("", (byte) 11, 3);
    private static final jh i = new jh("", (byte) 11, 4);
    private static final jh j = new jh("", (byte) 11, 5);
    private static final jh k = new jh("", (byte) 15, 6);
    private static final jh l = new jh("", (byte) 11, 7);
    private static final jh m = new jh("", (byte) 11, 9);
    private static final jh n = new jh("", (byte) 2, 10);
    private static final jh o = new jh("", (byte) 2, 11);
    private static final jh p = new jh("", (byte) 10, 12);

    /* renamed from: a  reason: collision with root package name */
    public long f12801a;

    /* renamed from: a  reason: collision with other field name */
    public id f155a;

    /* renamed from: a  reason: collision with other field name */
    public String f156a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f157a = new BitSet(3);

    /* renamed from: a  reason: collision with other field name */
    public List<String> f158a;

    /* renamed from: a  reason: collision with other field name */
    public boolean f159a = false;
    public String b;

    /* renamed from: b  reason: collision with other field name */
    public boolean f160b = true;
    public String c;
    public String d;
    public String e;

    /* renamed from: a */
    public int compareTo(ii iiVar) {
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
        if (!getClass().equals(iiVar.getClass())) {
            return getClass().getName().compareTo(iiVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(iiVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a11 = ja.a((Comparable) this.f155a, (Comparable) iiVar.f155a)) != 0) {
            return a11;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(iiVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a10 = ja.a(this.f156a, iiVar.f156a)) != 0) {
            return a10;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(iiVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a9 = ja.a(this.b, iiVar.b)) != 0) {
            return a9;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(iiVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a8 = ja.a(this.c, iiVar.c)) != 0) {
            return a8;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(iiVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a7 = ja.a((List) this.f158a, (List) iiVar.f158a)) != 0) {
            return a7;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(iiVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a6 = ja.a(this.d, iiVar.d)) != 0) {
            return a6;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(iiVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a5 = ja.a(this.e, iiVar.e)) != 0) {
            return a5;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(iiVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a4 = ja.a(this.f159a, iiVar.f159a)) != 0) {
            return a4;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(iiVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a3 = ja.a(this.f160b, iiVar.f160b)) != 0) {
            return a3;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(iiVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (!j() || (a2 = ja.a(this.f12801a, iiVar.f12801a)) == 0) {
            return 0;
        }
        return a2;
    }

    public ii a(String str) {
        this.f156a = str;
        return this;
    }

    public ii a(List<String> list) {
        this.f158a = list;
        return this;
    }

    public String a() {
        return this.c;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m186a() {
        if (this.f156a == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new jl("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new jl("Required field 'cmdName' was not present! Struct: " + toString());
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
            r3 = 1
            r4 = 11
            switch(r1) {
                case 2: goto L_0x00b0;
                case 3: goto L_0x00a5;
                case 4: goto L_0x009a;
                case 5: goto L_0x008f;
                case 6: goto L_0x0067;
                case 7: goto L_0x005c;
                case 8: goto L_0x001b;
                case 9: goto L_0x0051;
                case 10: goto L_0x0042;
                case 11: goto L_0x0033;
                case 12: goto L_0x0022;
                default: goto L_0x001b;
            }
        L_0x001b:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r6, r0)
            goto L_0x00c2
        L_0x0022:
            byte r1 = r0.b
            r2 = 10
            if (r1 != r2) goto L_0x001b
            long r0 = r6.t()
            r5.f12801a = r0
            r5.c((boolean) r3)
            goto L_0x00c2
        L_0x0033:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x001b
            boolean r0 = r6.p()
            r5.f160b = r0
            r5.b((boolean) r3)
            goto L_0x00c2
        L_0x0042:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x001b
            boolean r0 = r6.p()
            r5.f159a = r0
            r5.a((boolean) r3)
            goto L_0x00c2
        L_0x0051:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001b
            java.lang.String r0 = r6.v()
            r5.e = r0
            goto L_0x00c2
        L_0x005c:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001b
            java.lang.String r0 = r6.v()
            r5.d = r0
            goto L_0x00c2
        L_0x0067:
            byte r1 = r0.b
            r2 = 15
            if (r1 != r2) goto L_0x001b
            com.xiaomi.push.ji r0 = r6.l()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.b
            r1.<init>(r2)
            r5.f158a = r1
            r1 = 0
        L_0x007b:
            int r2 = r0.b
            if (r1 >= r2) goto L_0x008b
            java.lang.String r2 = r6.v()
            java.util.List<java.lang.String> r3 = r5.f158a
            r3.add(r2)
            int r1 = r1 + 1
            goto L_0x007b
        L_0x008b:
            r6.m()
            goto L_0x00c2
        L_0x008f:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001b
            java.lang.String r0 = r6.v()
            r5.c = r0
            goto L_0x00c2
        L_0x009a:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001b
            java.lang.String r0 = r6.v()
            r5.b = r0
            goto L_0x00c2
        L_0x00a5:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x001b
            java.lang.String r0 = r6.v()
            r5.f156a = r0
            goto L_0x00c2
        L_0x00b0:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x001b
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r5.f155a = r0
            com.xiaomi.push.id r0 = r5.f155a
            r0.a((com.xiaomi.push.jk) r6)
        L_0x00c2:
            r6.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ii.a(com.xiaomi.push.jk):void");
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m187a(String str) {
        if (this.f158a == null) {
            this.f158a = new ArrayList();
        }
        this.f158a.add(str);
    }

    public void a(boolean z) {
        this.f157a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m188a() {
        return this.f155a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m189a(ii iiVar) {
        if (iiVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = iiVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f155a.compareTo(iiVar.f155a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = iiVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f156a.equals(iiVar.f156a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = iiVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(iiVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = iiVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.c.equals(iiVar.c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = iiVar.e();
        if ((e2 || e3) && (!e2 || !e3 || !this.f158a.equals(iiVar.f158a))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = iiVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.d.equals(iiVar.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = iiVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.e.equals(iiVar.e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = iiVar.h();
        if ((h2 || h3) && (!h2 || !h3 || this.f159a != iiVar.f159a)) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = iiVar.i();
        if ((i2 || i3) && (!i2 || !i3 || this.f160b != iiVar.f160b)) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = iiVar.j();
        if (j2 || j3) {
            return j2 && j3 && this.f12801a == iiVar.f12801a;
        }
        return true;
    }

    public ii b(String str) {
        this.b = str;
        return this;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(f);
        if (this.f155a != null && a()) {
            jkVar.a(g);
            this.f155a.b(jkVar);
            jkVar.b();
        }
        if (this.f156a != null) {
            jkVar.a(h);
            jkVar.a(this.f156a);
            jkVar.b();
        }
        if (this.b != null) {
            jkVar.a(i);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.c != null) {
            jkVar.a(j);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (this.f158a != null && e()) {
            jkVar.a(k);
            jkVar.a(new ji((byte) 11, this.f158a.size()));
            for (String a2 : this.f158a) {
                jkVar.a(a2);
            }
            jkVar.e();
            jkVar.b();
        }
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
            jkVar.a(this.f159a);
            jkVar.b();
        }
        if (i()) {
            jkVar.a(o);
            jkVar.a(this.f160b);
            jkVar.b();
        }
        if (j()) {
            jkVar.a(p);
            jkVar.a(this.f12801a);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f157a.set(1, z);
    }

    public boolean b() {
        return this.f156a != null;
    }

    public ii c(String str) {
        this.c = str;
        return this;
    }

    public void c(boolean z) {
        this.f157a.set(2, z);
    }

    public boolean c() {
        return this.b != null;
    }

    public ii d(String str) {
        this.d = str;
        return this;
    }

    public boolean d() {
        return this.c != null;
    }

    public ii e(String str) {
        this.e = str;
        return this;
    }

    public boolean e() {
        return this.f158a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ii)) {
            return compareTo((ii) obj);
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
        return this.f157a.get(0);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f157a.get(1);
    }

    public boolean j() {
        return this.f157a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommand(");
        if (a()) {
            sb.append("target:");
            if (this.f155a == null) {
                sb.append("null");
            } else {
                sb.append(this.f155a);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f156a == null ? "null" : this.f156a);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.b == null ? "null" : this.b);
        sb.append(", ");
        sb.append("cmdName:");
        sb.append(this.c == null ? "null" : this.c);
        if (e()) {
            sb.append(", ");
            sb.append("cmdArgs:");
            if (this.f158a == null) {
                sb.append("null");
            } else {
                sb.append(this.f158a);
            }
        }
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("updateCache:");
            sb.append(this.f159a);
        }
        if (i()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f160b);
        }
        if (j()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f12801a);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
