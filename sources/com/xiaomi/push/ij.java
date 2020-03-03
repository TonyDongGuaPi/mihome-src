package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;
import java.util.List;

public class ij implements iz<ij, Object>, Serializable, Cloneable {
    private static final jp g = new jp("XmPushActionCommandResult");
    private static final jh h = new jh("", (byte) 12, 2);
    private static final jh i = new jh("", (byte) 11, 3);
    private static final jh j = new jh("", (byte) 11, 4);
    private static final jh k = new jh("", (byte) 11, 5);
    private static final jh l = new jh("", (byte) 10, 7);
    private static final jh m = new jh("", (byte) 11, 8);
    private static final jh n = new jh("", (byte) 11, 9);
    private static final jh o = new jh("", (byte) 15, 10);
    private static final jh p = new jh("", (byte) 11, 12);
    private static final jh q = new jh("", (byte) 2, 13);

    /* renamed from: a  reason: collision with root package name */
    public long f12802a;

    /* renamed from: a  reason: collision with other field name */
    public id f161a;

    /* renamed from: a  reason: collision with other field name */
    public String f162a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f163a = new BitSet(2);

    /* renamed from: a  reason: collision with other field name */
    public List<String> f164a;

    /* renamed from: a  reason: collision with other field name */
    public boolean f165a = true;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;

    /* renamed from: a */
    public int compareTo(ij ijVar) {
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
        if (!getClass().equals(ijVar.getClass())) {
            return getClass().getName().compareTo(ijVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ijVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a11 = ja.a((Comparable) this.f161a, (Comparable) ijVar.f161a)) != 0) {
            return a11;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ijVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a10 = ja.a(this.f162a, ijVar.f162a)) != 0) {
            return a10;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ijVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a9 = ja.a(this.b, ijVar.b)) != 0) {
            return a9;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ijVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a8 = ja.a(this.c, ijVar.c)) != 0) {
            return a8;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ijVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a7 = ja.a(this.f12802a, ijVar.f12802a)) != 0) {
            return a7;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ijVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a6 = ja.a(this.d, ijVar.d)) != 0) {
            return a6;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ijVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a5 = ja.a(this.e, ijVar.e)) != 0) {
            return a5;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ijVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a4 = ja.a((List) this.f164a, (List) ijVar.f164a)) != 0) {
            return a4;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ijVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a3 = ja.a(this.f, ijVar.f)) != 0) {
            return a3;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ijVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (!j() || (a2 = ja.a(this.f165a, ijVar.f165a)) == 0) {
            return 0;
        }
        return a2;
    }

    public String a() {
        return this.c;
    }

    /* renamed from: a  reason: collision with other method in class */
    public List<String> m190a() {
        return this.f164a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m191a() {
        if (this.f162a == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new jl("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new jl("Required field 'cmdName' was not present! Struct: " + toString());
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
            if (r1 != 0) goto L_0x0033
            r5.g()
            boolean r5 = r4.e()
            if (r5 == 0) goto L_0x0018
            r4.a()
            return
        L_0x0018:
            com.xiaomi.push.jl r5 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'errorCode' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L_0x0033:
            short r1 = r0.c
            r2 = 1
            r3 = 11
            switch(r1) {
                case 2: goto L_0x00cd;
                case 3: goto L_0x00c2;
                case 4: goto L_0x00b7;
                case 5: goto L_0x00ac;
                case 6: goto L_0x003b;
                case 7: goto L_0x009c;
                case 8: goto L_0x0091;
                case 9: goto L_0x0086;
                case 10: goto L_0x005e;
                case 11: goto L_0x003b;
                case 12: goto L_0x0052;
                case 13: goto L_0x0042;
                default: goto L_0x003b;
            }
        L_0x003b:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r5, r0)
            goto L_0x00df
        L_0x0042:
            byte r1 = r0.b
            r3 = 2
            if (r1 != r3) goto L_0x003b
            boolean r0 = r5.p()
            r4.f165a = r0
            r4.b((boolean) r2)
            goto L_0x00df
        L_0x0052:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003b
            java.lang.String r0 = r5.v()
            r4.f = r0
            goto L_0x00df
        L_0x005e:
            byte r1 = r0.b
            r2 = 15
            if (r1 != r2) goto L_0x003b
            com.xiaomi.push.ji r0 = r5.l()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.b
            r1.<init>(r2)
            r4.f164a = r1
            r1 = 0
        L_0x0072:
            int r2 = r0.b
            if (r1 >= r2) goto L_0x0082
            java.lang.String r2 = r5.v()
            java.util.List<java.lang.String> r3 = r4.f164a
            r3.add(r2)
            int r1 = r1 + 1
            goto L_0x0072
        L_0x0082:
            r5.m()
            goto L_0x00df
        L_0x0086:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003b
            java.lang.String r0 = r5.v()
            r4.e = r0
            goto L_0x00df
        L_0x0091:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003b
            java.lang.String r0 = r5.v()
            r4.d = r0
            goto L_0x00df
        L_0x009c:
            byte r1 = r0.b
            r3 = 10
            if (r1 != r3) goto L_0x003b
            long r0 = r5.t()
            r4.f12802a = r0
            r4.a((boolean) r2)
            goto L_0x00df
        L_0x00ac:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003b
            java.lang.String r0 = r5.v()
            r4.c = r0
            goto L_0x00df
        L_0x00b7:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003b
            java.lang.String r0 = r5.v()
            r4.b = r0
            goto L_0x00df
        L_0x00c2:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003b
            java.lang.String r0 = r5.v()
            r4.f162a = r0
            goto L_0x00df
        L_0x00cd:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x003b
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r4.f161a = r0
            com.xiaomi.push.id r0 = r4.f161a
            r0.a((com.xiaomi.push.jk) r5)
        L_0x00df:
            r5.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ij.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f163a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m192a() {
        return this.f161a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m193a(ij ijVar) {
        if (ijVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = ijVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f161a.compareTo(ijVar.f161a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = ijVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f162a.equals(ijVar.f162a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = ijVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(ijVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = ijVar.d();
        if (((d2 || d3) && (!d2 || !d3 || !this.c.equals(ijVar.c))) || this.f12802a != ijVar.f12802a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = ijVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.d.equals(ijVar.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = ijVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.e.equals(ijVar.e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = ijVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.f164a.equals(ijVar.f164a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = ijVar.i();
        if ((i2 || i3) && (!i2 || !i3 || !this.f.equals(ijVar.f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = ijVar.j();
        if (j2 || j3) {
            return j2 && j3 && this.f165a == ijVar.f165a;
        }
        return true;
    }

    public String b() {
        return this.f;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(g);
        if (this.f161a != null && a()) {
            jkVar.a(h);
            this.f161a.b(jkVar);
            jkVar.b();
        }
        if (this.f162a != null) {
            jkVar.a(i);
            jkVar.a(this.f162a);
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
        jkVar.a(l);
        jkVar.a(this.f12802a);
        jkVar.b();
        if (this.d != null && f()) {
            jkVar.a(m);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (this.e != null && g()) {
            jkVar.a(n);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f164a != null && h()) {
            jkVar.a(o);
            jkVar.a(new ji((byte) 11, this.f164a.size()));
            for (String a2 : this.f164a) {
                jkVar.a(a2);
            }
            jkVar.e();
            jkVar.b();
        }
        if (this.f != null && i()) {
            jkVar.a(p);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (j()) {
            jkVar.a(q);
            jkVar.a(this.f165a);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f163a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m194b() {
        return this.f162a != null;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return this.f163a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ij)) {
            return compareTo((ij) obj);
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
        return this.f164a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f != null;
    }

    public boolean j() {
        return this.f163a.get(1);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommandResult(");
        if (a()) {
            sb.append("target:");
            if (this.f161a == null) {
                sb.append("null");
            } else {
                sb.append(this.f161a);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        sb.append(this.f162a == null ? "null" : this.f162a);
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.b == null ? "null" : this.b);
        sb.append(", ");
        sb.append("cmdName:");
        sb.append(this.c == null ? "null" : this.c);
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f12802a);
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
            sb.append("cmdArgs:");
            if (this.f164a == null) {
                sb.append("null");
            } else {
                sb.append(this.f164a);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.f == null ? "null" : this.f);
        }
        if (j()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f165a);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
