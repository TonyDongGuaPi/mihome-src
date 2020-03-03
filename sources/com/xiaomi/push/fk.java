package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class fk implements iz<fk, Object>, Serializable, Cloneable {
    private static final jp f = new jp("StatsEvent");
    private static final jh g = new jh("", (byte) 3, 1);
    private static final jh h = new jh("", (byte) 8, 2);
    private static final jh i = new jh("", (byte) 8, 3);
    private static final jh j = new jh("", (byte) 11, 4);
    private static final jh k = new jh("", (byte) 11, 5);
    private static final jh l = new jh("", (byte) 8, 6);
    private static final jh m = new jh("", (byte) 11, 7);
    private static final jh n = new jh("", (byte) 11, 8);
    private static final jh o = new jh("", (byte) 8, 9);
    private static final jh p = new jh("", (byte) 8, 10);

    /* renamed from: a  reason: collision with root package name */
    public byte f12736a;

    /* renamed from: a  reason: collision with other field name */
    public int f73a;

    /* renamed from: a  reason: collision with other field name */
    public String f74a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f75a = new BitSet(6);
    public int b;

    /* renamed from: b  reason: collision with other field name */
    public String f76b;
    public int c;

    /* renamed from: c  reason: collision with other field name */
    public String f77c;
    public int d;

    /* renamed from: d  reason: collision with other field name */
    public String f78d;
    public int e;

    /* renamed from: a */
    public int compareTo(fk fkVar) {
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
        if (!getClass().equals(fkVar.getClass())) {
            return getClass().getName().compareTo(fkVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(fkVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a11 = ja.a(this.f12736a, fkVar.f12736a)) != 0) {
            return a11;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(fkVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a10 = ja.a(this.f73a, fkVar.f73a)) != 0) {
            return a10;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(fkVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a9 = ja.a(this.b, fkVar.b)) != 0) {
            return a9;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(fkVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a8 = ja.a(this.f74a, fkVar.f74a)) != 0) {
            return a8;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(fkVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a7 = ja.a(this.f76b, fkVar.f76b)) != 0) {
            return a7;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(fkVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a6 = ja.a(this.c, fkVar.c)) != 0) {
            return a6;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(fkVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a5 = ja.a(this.f77c, fkVar.f77c)) != 0) {
            return a5;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(fkVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a4 = ja.a(this.f78d, fkVar.f78d)) != 0) {
            return a4;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(fkVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a3 = ja.a(this.d, fkVar.d)) != 0) {
            return a3;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(fkVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (!j() || (a2 = ja.a(this.e, fkVar.e)) == 0) {
            return 0;
        }
        return a2;
    }

    public fk a(byte b2) {
        this.f12736a = b2;
        a(true);
        return this;
    }

    public fk a(int i2) {
        this.f73a = i2;
        b(true);
        return this;
    }

    public fk a(String str) {
        this.f74a = str;
        return this;
    }

    public void a() {
        if (this.f74a == null) {
            throw new jl("Required field 'connpt' was not present! Struct: " + toString());
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
            if (r1 != 0) goto L_0x0075
            r6.g()
            boolean r6 = r5.a()
            if (r6 == 0) goto L_0x005a
            boolean r6 = r5.b()
            if (r6 == 0) goto L_0x003f
            boolean r6 = r5.c()
            if (r6 == 0) goto L_0x0024
            r5.a()
            return
        L_0x0024:
            com.xiaomi.push.jl r6 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'value' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r5.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L_0x003f:
            com.xiaomi.push.jl r6 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'type' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r5.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L_0x005a:
            com.xiaomi.push.jl r6 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'chid' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r5.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L_0x0075:
            short r1 = r0.c
            r2 = 11
            r3 = 8
            r4 = 1
            switch(r1) {
                case 1: goto L_0x00f9;
                case 2: goto L_0x00eb;
                case 3: goto L_0x00dd;
                case 4: goto L_0x00d2;
                case 5: goto L_0x00c7;
                case 6: goto L_0x00b9;
                case 7: goto L_0x00ae;
                case 8: goto L_0x00a3;
                case 9: goto L_0x0095;
                case 10: goto L_0x0086;
                default: goto L_0x007f;
            }
        L_0x007f:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r6, r0)
            goto L_0x0107
        L_0x0086:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x007f
            int r0 = r6.s()
            r5.e = r0
            r5.f(r4)
            goto L_0x0107
        L_0x0095:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x007f
            int r0 = r6.s()
            r5.d = r0
            r5.e(r4)
            goto L_0x0107
        L_0x00a3:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x007f
            java.lang.String r0 = r6.v()
            r5.f78d = r0
            goto L_0x0107
        L_0x00ae:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x007f
            java.lang.String r0 = r6.v()
            r5.f77c = r0
            goto L_0x0107
        L_0x00b9:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x007f
            int r0 = r6.s()
            r5.c = r0
            r5.d((boolean) r4)
            goto L_0x0107
        L_0x00c7:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x007f
            java.lang.String r0 = r6.v()
            r5.f76b = r0
            goto L_0x0107
        L_0x00d2:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x007f
            java.lang.String r0 = r6.v()
            r5.f74a = r0
            goto L_0x0107
        L_0x00dd:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x007f
            int r0 = r6.s()
            r5.b = r0
            r5.c((boolean) r4)
            goto L_0x0107
        L_0x00eb:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x007f
            int r0 = r6.s()
            r5.f73a = r0
            r5.b((boolean) r4)
            goto L_0x0107
        L_0x00f9:
            byte r1 = r0.b
            r2 = 3
            if (r1 != r2) goto L_0x007f
            byte r0 = r6.q()
            r5.f12736a = r0
            r5.a((boolean) r4)
        L_0x0107:
            r6.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.fk.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f75a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m119a() {
        return this.f75a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m120a(fk fkVar) {
        if (fkVar == null || this.f12736a != fkVar.f12736a || this.f73a != fkVar.f73a || this.b != fkVar.b) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = fkVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.f74a.equals(fkVar.f74a))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = fkVar.e();
        if ((e2 || e3) && (!e2 || !e3 || !this.f76b.equals(fkVar.f76b))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = fkVar.f();
        if ((f2 || f3) && (!f2 || !f3 || this.c != fkVar.c)) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = fkVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.f77c.equals(fkVar.f77c))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = fkVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.f78d.equals(fkVar.f78d))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = fkVar.i();
        if ((i2 || i3) && (!i2 || !i3 || this.d != fkVar.d)) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = fkVar.j();
        if (j2 || j3) {
            return j2 && j3 && this.e == fkVar.e;
        }
        return true;
    }

    public fk b(int i2) {
        this.b = i2;
        c(true);
        return this;
    }

    public fk b(String str) {
        this.f76b = str;
        return this;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(f);
        jkVar.a(g);
        jkVar.a(this.f12736a);
        jkVar.b();
        jkVar.a(h);
        jkVar.a(this.f73a);
        jkVar.b();
        jkVar.a(i);
        jkVar.a(this.b);
        jkVar.b();
        if (this.f74a != null) {
            jkVar.a(j);
            jkVar.a(this.f74a);
            jkVar.b();
        }
        if (this.f76b != null && e()) {
            jkVar.a(k);
            jkVar.a(this.f76b);
            jkVar.b();
        }
        if (f()) {
            jkVar.a(l);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (this.f77c != null && g()) {
            jkVar.a(m);
            jkVar.a(this.f77c);
            jkVar.b();
        }
        if (this.f78d != null && h()) {
            jkVar.a(n);
            jkVar.a(this.f78d);
            jkVar.b();
        }
        if (i()) {
            jkVar.a(o);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (j()) {
            jkVar.a(p);
            jkVar.a(this.e);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f75a.set(1, z);
    }

    public boolean b() {
        return this.f75a.get(1);
    }

    public fk c(int i2) {
        this.c = i2;
        d(true);
        return this;
    }

    public fk c(String str) {
        this.f77c = str;
        return this;
    }

    public void c(boolean z) {
        this.f75a.set(2, z);
    }

    public boolean c() {
        return this.f75a.get(2);
    }

    public fk d(int i2) {
        this.d = i2;
        e(true);
        return this;
    }

    public fk d(String str) {
        this.f78d = str;
        return this;
    }

    public void d(boolean z) {
        this.f75a.set(3, z);
    }

    public boolean d() {
        return this.f74a != null;
    }

    public void e(boolean z) {
        this.f75a.set(4, z);
    }

    public boolean e() {
        return this.f76b != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof fk)) {
            return compareTo((fk) obj);
        }
        return false;
    }

    public void f(boolean z) {
        this.f75a.set(5, z);
    }

    public boolean f() {
        return this.f75a.get(3);
    }

    public boolean g() {
        return this.f77c != null;
    }

    public boolean h() {
        return this.f78d != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f75a.get(4);
    }

    public boolean j() {
        return this.f75a.get(5);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvent(");
        sb.append("chid:");
        sb.append(this.f12736a);
        sb.append(", ");
        sb.append("type:");
        sb.append(this.f73a);
        sb.append(", ");
        sb.append("value:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("connpt:");
        sb.append(this.f74a == null ? "null" : this.f74a);
        if (e()) {
            sb.append(", ");
            sb.append("host:");
            sb.append(this.f76b == null ? "null" : this.f76b);
        }
        if (f()) {
            sb.append(", ");
            sb.append("subvalue:");
            sb.append(this.c);
        }
        if (g()) {
            sb.append(", ");
            sb.append("annotation:");
            sb.append(this.f77c == null ? "null" : this.f77c);
        }
        if (h()) {
            sb.append(", ");
            sb.append("user:");
            sb.append(this.f78d == null ? "null" : this.f78d);
        }
        if (i()) {
            sb.append(", ");
            sb.append("time:");
            sb.append(this.d);
        }
        if (j()) {
            sb.append(", ");
            sb.append("clientIp:");
            sb.append(this.e);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
