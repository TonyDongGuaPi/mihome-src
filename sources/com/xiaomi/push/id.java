package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class id implements iz<id, Object>, Serializable, Cloneable {
    private static final jp e = new jp("Target");
    private static final jh f = new jh("", (byte) 10, 1);
    private static final jh g = new jh("", (byte) 11, 2);
    private static final jh h = new jh("", (byte) 11, 3);
    private static final jh i = new jh("", (byte) 11, 4);
    private static final jh j = new jh("", (byte) 2, 5);
    private static final jh k = new jh("", (byte) 11, 7);

    /* renamed from: a  reason: collision with root package name */
    public long f12796a = 5;

    /* renamed from: a  reason: collision with other field name */
    public String f138a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f139a = new BitSet(2);

    /* renamed from: a  reason: collision with other field name */
    public boolean f140a = false;
    public String b = "xiaomi.com";
    public String c = "";
    public String d;

    /* renamed from: a */
    public int compareTo(id idVar) {
        int a2;
        int a3;
        int a4;
        int a5;
        int a6;
        int a7;
        if (!getClass().equals(idVar.getClass())) {
            return getClass().getName().compareTo(idVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(idVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a7 = ja.a(this.f12796a, idVar.f12796a)) != 0) {
            return a7;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(idVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a6 = ja.a(this.f138a, idVar.f138a)) != 0) {
            return a6;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(idVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a5 = ja.a(this.b, idVar.b)) != 0) {
            return a5;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(idVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a4 = ja.a(this.c, idVar.c)) != 0) {
            return a4;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(idVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a3 = ja.a(this.f140a, idVar.f140a)) != 0) {
            return a3;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(idVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (!f() || (a2 = ja.a(this.d, idVar.d)) == 0) {
            return 0;
        }
        return a2;
    }

    public void a() {
        if (this.f138a == null) {
            throw new jl("Required field 'userId' was not present! Struct: " + toString());
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
            boolean r5 = r4.a()
            if (r5 == 0) goto L_0x0018
            r4.a()
            return
        L_0x0018:
            com.xiaomi.push.jl r5 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'channelId' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L_0x0033:
            short r1 = r0.c
            r2 = 7
            r3 = 11
            if (r1 == r2) goto L_0x0084
            r2 = 1
            switch(r1) {
                case 1: goto L_0x0074;
                case 2: goto L_0x0069;
                case 3: goto L_0x005e;
                case 4: goto L_0x0053;
                case 5: goto L_0x0044;
                default: goto L_0x003e;
            }
        L_0x003e:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r5, r0)
            goto L_0x008e
        L_0x0044:
            byte r1 = r0.b
            r3 = 2
            if (r1 != r3) goto L_0x003e
            boolean r0 = r5.p()
            r4.f140a = r0
            r4.b((boolean) r2)
            goto L_0x008e
        L_0x0053:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003e
            java.lang.String r0 = r5.v()
            r4.c = r0
            goto L_0x008e
        L_0x005e:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003e
            java.lang.String r0 = r5.v()
            r4.b = r0
            goto L_0x008e
        L_0x0069:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003e
            java.lang.String r0 = r5.v()
            r4.f138a = r0
            goto L_0x008e
        L_0x0074:
            byte r1 = r0.b
            r3 = 10
            if (r1 != r3) goto L_0x003e
            long r0 = r5.t()
            r4.f12796a = r0
            r4.a((boolean) r2)
            goto L_0x008e
        L_0x0084:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x003e
            java.lang.String r0 = r5.v()
            r4.d = r0
        L_0x008e:
            r5.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.id.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f139a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m174a() {
        return this.f139a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m175a(id idVar) {
        if (idVar == null || this.f12796a != idVar.f12796a) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = idVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f138a.equals(idVar.f138a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = idVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(idVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = idVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.c.equals(idVar.c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = idVar.e();
        if ((e2 || e3) && (!e2 || !e3 || this.f140a != idVar.f140a)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = idVar.f();
        if (f2 || f3) {
            return f2 && f3 && this.d.equals(idVar.d);
        }
        return true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(e);
        jkVar.a(f);
        jkVar.a(this.f12796a);
        jkVar.b();
        if (this.f138a != null) {
            jkVar.a(g);
            jkVar.a(this.f138a);
            jkVar.b();
        }
        if (this.b != null && c()) {
            jkVar.a(h);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.c != null && d()) {
            jkVar.a(i);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (e()) {
            jkVar.a(j);
            jkVar.a(this.f140a);
            jkVar.b();
        }
        if (this.d != null && f()) {
            jkVar.a(k);
            jkVar.a(this.d);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f139a.set(1, z);
    }

    public boolean b() {
        return this.f138a != null;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return this.f139a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof id)) {
            return compareTo((id) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Target(");
        sb.append("channelId:");
        sb.append(this.f12796a);
        sb.append(", ");
        sb.append("userId:");
        sb.append(this.f138a == null ? "null" : this.f138a);
        if (c()) {
            sb.append(", ");
            sb.append("server:");
            sb.append(this.b == null ? "null" : this.b);
        }
        if (d()) {
            sb.append(", ");
            sb.append("resource:");
            sb.append(this.c == null ? "null" : this.c);
        }
        if (e()) {
            sb.append(", ");
            sb.append("isPreview:");
            sb.append(this.f140a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("token:");
            sb.append(this.d == null ? "null" : this.d);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
