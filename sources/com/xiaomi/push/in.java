package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class in implements iz<in, Object>, Serializable, Cloneable {
    private static final jp j = new jp("XmPushActionNotification");
    private static final jh k = new jh("", (byte) 11, 1);
    private static final jh l = new jh("", (byte) 12, 2);
    private static final jh m = new jh("", (byte) 11, 3);
    private static final jh n = new jh("", (byte) 11, 4);
    private static final jh o = new jh("", (byte) 11, 5);
    private static final jh p = new jh("", (byte) 2, 6);
    private static final jh q = new jh("", (byte) 11, 7);
    private static final jh r = new jh("", (byte) 13, 8);
    private static final jh s = new jh("", (byte) 11, 9);
    private static final jh t = new jh("", (byte) 11, 10);
    private static final jh u = new jh("", (byte) 11, 12);
    private static final jh v = new jh("", (byte) 11, 13);
    private static final jh w = new jh("", (byte) 11, 14);
    private static final jh x = new jh("", (byte) 10, 15);
    private static final jh y = new jh("", (byte) 2, 20);

    /* renamed from: a  reason: collision with root package name */
    public long f12806a;

    /* renamed from: a  reason: collision with other field name */
    public id f173a;

    /* renamed from: a  reason: collision with other field name */
    public String f174a;

    /* renamed from: a  reason: collision with other field name */
    public ByteBuffer f175a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f176a;

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f177a;

    /* renamed from: a  reason: collision with other field name */
    public boolean f178a;
    public String b;

    /* renamed from: b  reason: collision with other field name */
    public boolean f179b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;

    public in() {
        this.f176a = new BitSet(3);
        this.f178a = true;
        this.f179b = false;
    }

    public in(String str, boolean z) {
        this();
        this.b = str;
        this.f178a = z;
        a(true);
    }

    /* renamed from: a */
    public int compareTo(in inVar) {
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
        int a13;
        int a14;
        int a15;
        int a16;
        if (!getClass().equals(inVar.getClass())) {
            return getClass().getName().compareTo(inVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(inVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a16 = ja.a(this.f174a, inVar.f174a)) != 0) {
            return a16;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(inVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a15 = ja.a((Comparable) this.f173a, (Comparable) inVar.f173a)) != 0) {
            return a15;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(inVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a14 = ja.a(this.b, inVar.b)) != 0) {
            return a14;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(inVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a13 = ja.a(this.c, inVar.c)) != 0) {
            return a13;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(inVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a12 = ja.a(this.d, inVar.d)) != 0) {
            return a12;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(inVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a11 = ja.a(this.f178a, inVar.f178a)) != 0) {
            return a11;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(inVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a10 = ja.a(this.e, inVar.e)) != 0) {
            return a10;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(inVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a9 = ja.a((Map) this.f177a, (Map) inVar.f177a)) != 0) {
            return a9;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(inVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a8 = ja.a(this.f, inVar.f)) != 0) {
            return a8;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(inVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (j() && (a7 = ja.a(this.g, inVar.g)) != 0) {
            return a7;
        }
        int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(inVar.k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (k() && (a6 = ja.a(this.h, inVar.h)) != 0) {
            return a6;
        }
        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(inVar.l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (l() && (a5 = ja.a(this.i, inVar.i)) != 0) {
            return a5;
        }
        int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(inVar.m()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (m() && (a4 = ja.a((Comparable) this.f175a, (Comparable) inVar.f175a)) != 0) {
            return a4;
        }
        int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(inVar.n()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (n() && (a3 = ja.a(this.f12806a, inVar.f12806a)) != 0) {
            return a3;
        }
        int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(inVar.o()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (!o() || (a2 = ja.a(this.f179b, inVar.f179b)) == 0) {
            return 0;
        }
        return a2;
    }

    public in a(String str) {
        this.b = str;
        return this;
    }

    public in a(ByteBuffer byteBuffer) {
        this.f175a = byteBuffer;
        return this;
    }

    public in a(Map<String, String> map) {
        this.f177a = map;
        return this;
    }

    public in a(boolean z) {
        this.f178a = z;
        a(true);
        return this;
    }

    public in a(byte[] bArr) {
        a(ByteBuffer.wrap(bArr));
        return this;
    }

    public String a() {
        return this.b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public Map<String, String> m210a() {
        return this.f177a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m211a() {
        if (this.b == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
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
            boolean r6 = r5.f()
            if (r6 == 0) goto L_0x0018
            r5.a()
            return
        L_0x0018:
            com.xiaomi.push.jl r6 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'requireAck' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r5.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L_0x0033:
            short r1 = r0.c
            r2 = 20
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L_0x011d
            r2 = 11
            switch(r1) {
                case 1: goto L_0x0112;
                case 2: goto L_0x00ff;
                case 3: goto L_0x00f4;
                case 4: goto L_0x00e9;
                case 5: goto L_0x00de;
                case 6: goto L_0x00d0;
                case 7: goto L_0x00c5;
                case 8: goto L_0x0097;
                case 9: goto L_0x008b;
                case 10: goto L_0x007f;
                default: goto L_0x0040;
            }
        L_0x0040:
            switch(r1) {
                case 12: goto L_0x0073;
                case 13: goto L_0x0067;
                case 14: goto L_0x005b;
                case 15: goto L_0x004a;
                default: goto L_0x0043;
            }
        L_0x0043:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r6, r0)
            goto L_0x012a
        L_0x004a:
            byte r1 = r0.b
            r2 = 10
            if (r1 != r2) goto L_0x0043
            long r0 = r6.t()
            r5.f12806a = r0
            r5.b((boolean) r4)
            goto L_0x012a
        L_0x005b:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.nio.ByteBuffer r0 = r6.w()
            r5.f175a = r0
            goto L_0x012a
        L_0x0067:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.lang.String r0 = r6.v()
            r5.i = r0
            goto L_0x012a
        L_0x0073:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.lang.String r0 = r6.v()
            r5.h = r0
            goto L_0x012a
        L_0x007f:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.lang.String r0 = r6.v()
            r5.g = r0
            goto L_0x012a
        L_0x008b:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.lang.String r0 = r6.v()
            r5.f = r0
            goto L_0x012a
        L_0x0097:
            byte r1 = r0.b
            r2 = 13
            if (r1 != r2) goto L_0x0043
            com.xiaomi.push.jj r0 = r6.j()
            java.util.HashMap r1 = new java.util.HashMap
            int r2 = r0.c
            int r2 = r2 * 2
            r1.<init>(r2)
            r5.f177a = r1
            r1 = 0
        L_0x00ad:
            int r2 = r0.c
            if (r1 >= r2) goto L_0x00c1
            java.lang.String r2 = r6.v()
            java.lang.String r3 = r6.v()
            java.util.Map<java.lang.String, java.lang.String> r4 = r5.f177a
            r4.put(r2, r3)
            int r1 = r1 + 1
            goto L_0x00ad
        L_0x00c1:
            r6.k()
            goto L_0x012a
        L_0x00c5:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.lang.String r0 = r6.v()
            r5.e = r0
            goto L_0x012a
        L_0x00d0:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x0043
            boolean r0 = r6.p()
            r5.f178a = r0
            r5.a((boolean) r4)
            goto L_0x012a
        L_0x00de:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.lang.String r0 = r6.v()
            r5.d = r0
            goto L_0x012a
        L_0x00e9:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.lang.String r0 = r6.v()
            r5.c = r0
            goto L_0x012a
        L_0x00f4:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.lang.String r0 = r6.v()
            r5.b = r0
            goto L_0x012a
        L_0x00ff:
            byte r1 = r0.b
            r2 = 12
            if (r1 != r2) goto L_0x0043
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r5.f173a = r0
            com.xiaomi.push.id r0 = r5.f173a
            r0.a((com.xiaomi.push.jk) r6)
            goto L_0x012a
        L_0x0112:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0043
            java.lang.String r0 = r6.v()
            r5.f174a = r0
            goto L_0x012a
        L_0x011d:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x0043
            boolean r0 = r6.p()
            r5.f179b = r0
            r5.c((boolean) r4)
        L_0x012a:
            r6.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.in.a(com.xiaomi.push.jk):void");
    }

    public void a(String str, String str2) {
        if (this.f177a == null) {
            this.f177a = new HashMap();
        }
        this.f177a.put(str, str2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m212a(boolean z) {
        this.f176a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m213a() {
        return this.f174a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m214a(in inVar) {
        if (inVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = inVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f174a.equals(inVar.f174a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = inVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f173a.compareTo(inVar.f173a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = inVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.b.equals(inVar.b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = inVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.c.equals(inVar.c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = inVar.e();
        if (((e2 || e3) && (!e2 || !e3 || !this.d.equals(inVar.d))) || this.f178a != inVar.f178a) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = inVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.e.equals(inVar.e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = inVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.f177a.equals(inVar.f177a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = inVar.i();
        if ((i2 || i3) && (!i2 || !i3 || !this.f.equals(inVar.f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = inVar.j();
        if ((j2 || j3) && (!j2 || !j3 || !this.g.equals(inVar.g))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = inVar.k();
        if ((k2 || k3) && (!k2 || !k3 || !this.h.equals(inVar.h))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = inVar.l();
        if ((l2 || l3) && (!l2 || !l3 || !this.i.equals(inVar.i))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = inVar.m();
        if ((m2 || m3) && (!m2 || !m3 || !this.f175a.equals(inVar.f175a))) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = inVar.n();
        if ((n2 || n3) && (!n2 || !n3 || this.f12806a != inVar.f12806a)) {
            return false;
        }
        boolean o2 = o();
        boolean o3 = inVar.o();
        if (o2 || o3) {
            return o2 && o3 && this.f179b == inVar.f179b;
        }
        return true;
    }

    /* renamed from: a  reason: collision with other method in class */
    public byte[] m215a() {
        a(ja.c(this.f175a));
        return this.f175a.array();
    }

    public in b(String str) {
        this.c = str;
        return this;
    }

    public String b() {
        return this.c;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(j);
        if (this.f174a != null && a()) {
            jkVar.a(k);
            jkVar.a(this.f174a);
            jkVar.b();
        }
        if (this.f173a != null && b()) {
            jkVar.a(l);
            this.f173a.b(jkVar);
            jkVar.b();
        }
        if (this.b != null) {
            jkVar.a(m);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.c != null && d()) {
            jkVar.a(n);
            jkVar.a(this.c);
            jkVar.b();
        }
        if (this.d != null && e()) {
            jkVar.a(o);
            jkVar.a(this.d);
            jkVar.b();
        }
        jkVar.a(p);
        jkVar.a(this.f178a);
        jkVar.b();
        if (this.e != null && g()) {
            jkVar.a(q);
            jkVar.a(this.e);
            jkVar.b();
        }
        if (this.f177a != null && h()) {
            jkVar.a(r);
            jkVar.a(new jj((byte) 11, (byte) 11, this.f177a.size()));
            for (Map.Entry next : this.f177a.entrySet()) {
                jkVar.a((String) next.getKey());
                jkVar.a((String) next.getValue());
            }
            jkVar.d();
            jkVar.b();
        }
        if (this.f != null && i()) {
            jkVar.a(s);
            jkVar.a(this.f);
            jkVar.b();
        }
        if (this.g != null && j()) {
            jkVar.a(t);
            jkVar.a(this.g);
            jkVar.b();
        }
        if (this.h != null && k()) {
            jkVar.a(u);
            jkVar.a(this.h);
            jkVar.b();
        }
        if (this.i != null && l()) {
            jkVar.a(v);
            jkVar.a(this.i);
            jkVar.b();
        }
        if (this.f175a != null && m()) {
            jkVar.a(w);
            jkVar.a(this.f175a);
            jkVar.b();
        }
        if (n()) {
            jkVar.a(x);
            jkVar.a(this.f12806a);
            jkVar.b();
        }
        if (o()) {
            jkVar.a(y);
            jkVar.a(this.f179b);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f176a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m216b() {
        return this.f173a != null;
    }

    public in c(String str) {
        this.d = str;
        return this;
    }

    public String c() {
        return this.f;
    }

    public void c(boolean z) {
        this.f176a.set(2, z);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m217c() {
        return this.b != null;
    }

    public in d(String str) {
        this.f = str;
        return this;
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof in)) {
            return compareTo((in) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f176a.get(0);
    }

    public boolean g() {
        return this.e != null;
    }

    public boolean h() {
        return this.f177a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f != null;
    }

    public boolean j() {
        return this.g != null;
    }

    public boolean k() {
        return this.h != null;
    }

    public boolean l() {
        return this.i != null;
    }

    public boolean m() {
        return this.f175a != null;
    }

    public boolean n() {
        return this.f176a.get(1);
    }

    public boolean o() {
        return this.f176a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionNotification(");
        if (a()) {
            sb.append("debug:");
            sb.append(this.f174a == null ? "null" : this.f174a);
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.f173a == null) {
                sb.append("null");
            } else {
                sb.append(this.f173a);
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
            sb.append("type:");
            sb.append(this.d == null ? "null" : this.d);
        }
        sb.append(", ");
        sb.append("requireAck:");
        sb.append(this.f178a);
        if (g()) {
            sb.append(", ");
            sb.append("payload:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("extra:");
            if (this.f177a == null) {
                sb.append("null");
            } else {
                sb.append(this.f177a);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.f == null ? "null" : this.f);
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            sb.append(this.g == null ? "null" : this.g);
        }
        if (k()) {
            sb.append(", ");
            sb.append("regId:");
            sb.append(this.h == null ? "null" : this.h);
        }
        if (l()) {
            sb.append(", ");
            sb.append("aliasName:");
            sb.append(this.i == null ? "null" : this.i);
        }
        if (m()) {
            sb.append(", ");
            sb.append("binaryExtra:");
            if (this.f175a == null) {
                sb.append("null");
            } else {
                ja.a(this.f175a, sb);
            }
        }
        if (n()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f12806a);
        }
        if (o()) {
            sb.append(", ");
            sb.append("alreadyLogClickInXmq:");
            sb.append(this.f179b);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
