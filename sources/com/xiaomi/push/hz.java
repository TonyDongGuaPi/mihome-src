package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class hz implements iz<hz, Object>, Serializable, Cloneable {
    private static final jp d = new jp("OnlineConfigItem");
    private static final jh e = new jh("", (byte) 8, 1);
    private static final jh f = new jh("", (byte) 8, 2);
    private static final jh g = new jh("", (byte) 2, 3);
    private static final jh h = new jh("", (byte) 8, 4);
    private static final jh i = new jh("", (byte) 10, 5);
    private static final jh j = new jh("", (byte) 11, 6);
    private static final jh k = new jh("", (byte) 2, 7);

    /* renamed from: a  reason: collision with root package name */
    public int f12791a;

    /* renamed from: a  reason: collision with other field name */
    public long f115a;

    /* renamed from: a  reason: collision with other field name */
    public String f116a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f117a = new BitSet(6);

    /* renamed from: a  reason: collision with other field name */
    public boolean f118a;
    public int b;

    /* renamed from: b  reason: collision with other field name */
    public boolean f119b;
    public int c;

    public int a() {
        return this.f12791a;
    }

    /* renamed from: a */
    public int compareTo(hz hzVar) {
        int a2;
        int a3;
        int a4;
        int a5;
        int a6;
        int a7;
        int a8;
        if (!getClass().equals(hzVar.getClass())) {
            return getClass().getName().compareTo(hzVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(hzVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a8 = ja.a(this.f12791a, hzVar.f12791a)) != 0) {
            return a8;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hzVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a7 = ja.a(this.b, hzVar.b)) != 0) {
            return a7;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hzVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a6 = ja.a(this.f118a, hzVar.f118a)) != 0) {
            return a6;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(hzVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a5 = ja.a(this.c, hzVar.c)) != 0) {
            return a5;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(hzVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a4 = ja.a(this.f115a, hzVar.f115a)) != 0) {
            return a4;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(hzVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a3 = ja.a(this.f116a, hzVar.f116a)) != 0) {
            return a3;
        }
        int compareTo7 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(hzVar.h()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (!h() || (a2 = ja.a(this.f119b, hzVar.f119b)) == 0) {
            return 0;
        }
        return a2;
    }

    /* renamed from: a  reason: collision with other method in class */
    public long m148a() {
        return this.f115a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m149a() {
        return this.f116a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m150a() {
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
            r3 = 8
            r4 = 1
            switch(r1) {
                case 1: goto L_0x0076;
                case 2: goto L_0x0068;
                case 3: goto L_0x005a;
                case 4: goto L_0x004c;
                case 5: goto L_0x003c;
                case 6: goto L_0x002f;
                case 7: goto L_0x0021;
                default: goto L_0x001b;
            }
        L_0x001b:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r6, r0)
            goto L_0x0083
        L_0x0021:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x001b
            boolean r0 = r6.p()
            r5.f119b = r0
            r5.f(r4)
            goto L_0x0083
        L_0x002f:
            byte r1 = r0.b
            r2 = 11
            if (r1 != r2) goto L_0x001b
            java.lang.String r0 = r6.v()
            r5.f116a = r0
            goto L_0x0083
        L_0x003c:
            byte r1 = r0.b
            r2 = 10
            if (r1 != r2) goto L_0x001b
            long r0 = r6.t()
            r5.f115a = r0
            r5.e(r4)
            goto L_0x0083
        L_0x004c:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001b
            int r0 = r6.s()
            r5.c = r0
            r5.d(r4)
            goto L_0x0083
        L_0x005a:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x001b
            boolean r0 = r6.p()
            r5.f118a = r0
            r5.c(r4)
            goto L_0x0083
        L_0x0068:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001b
            int r0 = r6.s()
            r5.b = r0
            r5.b((boolean) r4)
            goto L_0x0083
        L_0x0076:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x001b
            int r0 = r6.s()
            r5.f12791a = r0
            r5.a((boolean) r4)
        L_0x0083:
            r6.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hz.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f117a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m151a() {
        return this.f117a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m152a(hz hzVar) {
        if (hzVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = hzVar.a();
        if ((a2 || a3) && (!a2 || !a3 || this.f12791a != hzVar.f12791a)) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = hzVar.b();
        if ((b2 || b3) && (!b2 || !b3 || this.b != hzVar.b)) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = hzVar.c();
        if ((c2 || c3) && (!c2 || !c3 || this.f118a != hzVar.f118a)) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = hzVar.d();
        if ((d2 || d3) && (!d2 || !d3 || this.c != hzVar.c)) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = hzVar.e();
        if ((e2 || e3) && (!e2 || !e3 || this.f115a != hzVar.f115a)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = hzVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.f116a.equals(hzVar.f116a))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = hzVar.h();
        if (h2 || h3) {
            return h2 && h3 && this.f119b == hzVar.f119b;
        }
        return true;
    }

    public int b() {
        return this.b;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(d);
        if (a()) {
            jkVar.a(e);
            jkVar.a(this.f12791a);
            jkVar.b();
        }
        if (b()) {
            jkVar.a(f);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (c()) {
            jkVar.a(g);
            jkVar.a(this.f118a);
            jkVar.b();
        }
        if (d()) {
            jkVar.a(h);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (e()) {
            jkVar.a(i);
            jkVar.a(this.f115a);
            jkVar.b();
        }
        if (this.f116a != null && f()) {
            jkVar.a(j);
            jkVar.a(this.f116a);
            jkVar.b();
        }
        if (h()) {
            jkVar.a(k);
            jkVar.a(this.f119b);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f117a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m153b() {
        return this.f117a.get(1);
    }

    public int c() {
        return this.c;
    }

    public void c(boolean z) {
        this.f117a.set(2, z);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m154c() {
        return this.f117a.get(2);
    }

    public void d(boolean z) {
        this.f117a.set(3, z);
    }

    public boolean d() {
        return this.f117a.get(3);
    }

    public void e(boolean z) {
        this.f117a.set(4, z);
    }

    public boolean e() {
        return this.f117a.get(4);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hz)) {
            return compareTo((hz) obj);
        }
        return false;
    }

    public void f(boolean z) {
        this.f117a.set(5, z);
    }

    public boolean f() {
        return this.f116a != null;
    }

    public boolean g() {
        return this.f119b;
    }

    public boolean h() {
        return this.f117a.get(5);
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("OnlineConfigItem(");
        if (a()) {
            sb.append("key:");
            sb.append(this.f12791a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("type:");
            sb.append(this.b);
            z = false;
        }
        if (c()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("clear:");
            sb.append(this.f118a);
            z = false;
        }
        if (d()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("intValue:");
            sb.append(this.c);
            z = false;
        }
        if (e()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("longValue:");
            sb.append(this.f115a);
            z = false;
        }
        if (f()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("stringValue:");
            sb.append(this.f116a == null ? "null" : this.f116a);
            z = false;
        }
        if (h()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("boolValue:");
            sb.append(this.f119b);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
