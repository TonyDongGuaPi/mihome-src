package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class iq implements iz<iq, Object>, Serializable, Cloneable {
    private static final jp f = new jp("XmPushActionSendFeedbackResult");
    private static final jh g = new jh("", (byte) 11, 1);
    private static final jh h = new jh("", (byte) 12, 2);
    private static final jh i = new jh("", (byte) 11, 3);
    private static final jh j = new jh("", (byte) 11, 4);
    private static final jh k = new jh("", (byte) 10, 6);
    private static final jh l = new jh("", (byte) 11, 7);
    private static final jh m = new jh("", (byte) 11, 8);

    /* renamed from: a  reason: collision with root package name */
    public long f12809a;

    /* renamed from: a  reason: collision with other field name */
    public id f198a;

    /* renamed from: a  reason: collision with other field name */
    public String f199a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f200a = new BitSet(1);
    public String b;
    public String c;
    public String d;
    public String e;

    /* renamed from: a */
    public int compareTo(iq iqVar) {
        int a2;
        int a3;
        int a4;
        int a5;
        int a6;
        int a7;
        int a8;
        if (!getClass().equals(iqVar.getClass())) {
            return getClass().getName().compareTo(iqVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(iqVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a8 = ja.a(this.f199a, iqVar.f199a)) != 0) {
            return a8;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(iqVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a7 = ja.a((Comparable) this.f198a, (Comparable) iqVar.f198a)) != 0) {
            return a7;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(iqVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a6 = ja.a(this.b, iqVar.b)) != 0) {
            return a6;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(iqVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a5 = ja.a(this.c, iqVar.c)) != 0) {
            return a5;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(iqVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a4 = ja.a(this.f12809a, iqVar.f12809a)) != 0) {
            return a4;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(iqVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a3 = ja.a(this.d, iqVar.d)) != 0) {
            return a3;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(iqVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (!g() || (a2 = ja.a(this.e, iqVar.e)) == 0) {
            return 0;
        }
        return a2;
    }

    public void a() {
        if (this.b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new jl("Required field 'appId' was not present! Struct: " + toString());
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
            if (r1 != 0) goto L_0x0033
            r4.g()
            boolean r4 = r3.e()
            if (r4 == 0) goto L_0x0018
            r3.a()
            return
        L_0x0018:
            com.xiaomi.push.jl r4 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'errorCode' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r3.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        L_0x0033:
            short r1 = r0.c
            r2 = 11
            switch(r1) {
                case 1: goto L_0x0090;
                case 2: goto L_0x007d;
                case 3: goto L_0x0072;
                case 4: goto L_0x0067;
                case 5: goto L_0x003a;
                case 6: goto L_0x0056;
                case 7: goto L_0x004b;
                case 8: goto L_0x0040;
                default: goto L_0x003a;
            }
        L_0x003a:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r4, r0)
            goto L_0x009a
        L_0x0040:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003a
            java.lang.String r0 = r4.v()
            r3.e = r0
            goto L_0x009a
        L_0x004b:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003a
            java.lang.String r0 = r4.v()
            r3.d = r0
            goto L_0x009a
        L_0x0056:
            byte r1 = r0.b
            r2 = 10
            if (r1 != r2) goto L_0x003a
            long r0 = r4.t()
            r3.f12809a = r0
            r0 = 1
            r3.a((boolean) r0)
            goto L_0x009a
        L_0x0067:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003a
            java.lang.String r0 = r4.v()
            r3.c = r0
            goto L_0x009a
        L_0x0072:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003a
            java.lang.String r0 = r4.v()
            r3.b = r0
            goto L_0x009a
        L_0x007d:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x003a
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r3.f198a = r0
            com.xiaomi.push.id r0 = r3.f198a
            r0.a((com.xiaomi.push.jk) r4)
            goto L_0x009a
        L_0x0090:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003a
            java.lang.String r0 = r4.v()
            r3.f199a = r0
        L_0x009a:
            r4.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.iq.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f200a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m228a() {
        return this.f199a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m229a(iq iqVar) {
        if (iqVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = iqVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f199a.equals(iqVar.f199a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = iqVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f198a.compareTo(iqVar.f198a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = iqVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(iqVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = iqVar.d();
        if (((d2 || d3) && (!d2 || !d3 || !this.c.equals(iqVar.c))) || this.f12809a != iqVar.f12809a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = iqVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.d.equals(iqVar.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = iqVar.g();
        if (g2 || g3) {
            return g2 && g3 && this.e.equals(iqVar.e);
        }
        return true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(f);
        if (this.f199a != null && a()) {
            jkVar.a(g);
            jkVar.a(this.f199a);
            jkVar.b();
        }
        if (this.f198a != null && b()) {
            jkVar.a(h);
            this.f198a.b(jkVar);
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
        jkVar.a(k);
        jkVar.a(this.f12809a);
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
        jkVar.c();
        jkVar.a();
    }

    public boolean b() {
        return this.f198a != null;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return this.f200a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof iq)) {
            return compareTo((iq) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.e != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendFeedbackResult(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f199a == null ? "null" : this.f199a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f198a == null) {
                sb.append("null");
            } else {
                sb.append(this.f198a);
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
        sb.append("errorCode:");
        sb.append(this.f12809a);
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (g()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.e == null ? "null" : this.e);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
