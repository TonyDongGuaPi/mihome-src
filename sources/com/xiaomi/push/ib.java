package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class ib implements iz<ib, Object>, Serializable, Cloneable {
    private static final jp f = new jp("PushMetaInfo");
    private static final jh g = new jh("", (byte) 11, 1);
    private static final jh h = new jh("", (byte) 10, 2);
    private static final jh i = new jh("", (byte) 11, 3);
    private static final jh j = new jh("", (byte) 11, 4);
    private static final jh k = new jh("", (byte) 11, 5);
    private static final jh l = new jh("", (byte) 8, 6);
    private static final jh m = new jh("", (byte) 11, 7);
    private static final jh n = new jh("", (byte) 8, 8);
    private static final jh o = new jh("", (byte) 8, 9);
    private static final jh p = new jh("", (byte) 13, 10);
    private static final jh q = new jh("", (byte) 13, 11);
    private static final jh r = new jh("", (byte) 2, 12);
    private static final jh s = new jh("", (byte) 13, 13);

    /* renamed from: a  reason: collision with root package name */
    public int f12794a;

    /* renamed from: a  reason: collision with other field name */
    public long f127a;

    /* renamed from: a  reason: collision with other field name */
    public String f128a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f129a;

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f130a;

    /* renamed from: a  reason: collision with other field name */
    public boolean f131a;
    public int b;

    /* renamed from: b  reason: collision with other field name */
    public String f132b;

    /* renamed from: b  reason: collision with other field name */
    public Map<String, String> f133b;
    public int c;

    /* renamed from: c  reason: collision with other field name */
    public String f134c;

    /* renamed from: c  reason: collision with other field name */
    public Map<String, String> f135c;
    public String d;
    public String e;

    public ib() {
        this.f129a = new BitSet(5);
        this.f131a = false;
    }

    public ib(ib ibVar) {
        this.f129a = new BitSet(5);
        this.f129a.clear();
        this.f129a.or(ibVar.f129a);
        if (ibVar.a()) {
            this.f128a = ibVar.f128a;
        }
        this.f127a = ibVar.f127a;
        if (ibVar.c()) {
            this.f132b = ibVar.f132b;
        }
        if (ibVar.d()) {
            this.f134c = ibVar.f134c;
        }
        if (ibVar.e()) {
            this.d = ibVar.d;
        }
        this.f12794a = ibVar.f12794a;
        if (ibVar.g()) {
            this.e = ibVar.e;
        }
        this.b = ibVar.b;
        this.c = ibVar.c;
        if (ibVar.j()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : ibVar.f130a.entrySet()) {
                hashMap.put((String) next.getKey(), (String) next.getValue());
            }
            this.f130a = hashMap;
        }
        if (ibVar.k()) {
            HashMap hashMap2 = new HashMap();
            for (Map.Entry next2 : ibVar.f133b.entrySet()) {
                hashMap2.put((String) next2.getKey(), (String) next2.getValue());
            }
            this.f133b = hashMap2;
        }
        this.f131a = ibVar.f131a;
        if (ibVar.n()) {
            HashMap hashMap3 = new HashMap();
            for (Map.Entry next3 : ibVar.f135c.entrySet()) {
                hashMap3.put((String) next3.getKey(), (String) next3.getValue());
            }
            this.f135c = hashMap3;
        }
    }

    public int a() {
        return this.f12794a;
    }

    /* renamed from: a */
    public int compareTo(ib ibVar) {
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
        if (!getClass().equals(ibVar.getClass())) {
            return getClass().getName().compareTo(ibVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ibVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a14 = ja.a(this.f128a, ibVar.f128a)) != 0) {
            return a14;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(ibVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a13 = ja.a(this.f127a, ibVar.f127a)) != 0) {
            return a13;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ibVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (c() && (a12 = ja.a(this.f132b, ibVar.f132b)) != 0) {
            return a12;
        }
        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ibVar.d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (d() && (a11 = ja.a(this.f134c, ibVar.f134c)) != 0) {
            return a11;
        }
        int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ibVar.e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (e() && (a10 = ja.a(this.d, ibVar.d)) != 0) {
            return a10;
        }
        int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ibVar.f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (f() && (a9 = ja.a(this.f12794a, ibVar.f12794a)) != 0) {
            return a9;
        }
        int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ibVar.g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (g() && (a8 = ja.a(this.e, ibVar.e)) != 0) {
            return a8;
        }
        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ibVar.h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (h() && (a7 = ja.a(this.b, ibVar.b)) != 0) {
            return a7;
        }
        int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ibVar.i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (i() && (a6 = ja.a(this.c, ibVar.c)) != 0) {
            return a6;
        }
        int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(ibVar.j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (j() && (a5 = ja.a((Map) this.f130a, (Map) ibVar.f130a)) != 0) {
            return a5;
        }
        int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(ibVar.k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (k() && (a4 = ja.a((Map) this.f133b, (Map) ibVar.f133b)) != 0) {
            return a4;
        }
        int compareTo12 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(ibVar.m()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (m() && (a3 = ja.a(this.f131a, ibVar.f131a)) != 0) {
            return a3;
        }
        int compareTo13 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(ibVar.n()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (!n() || (a2 = ja.a((Map) this.f135c, (Map) ibVar.f135c)) == 0) {
            return 0;
        }
        return a2;
    }

    /* renamed from: a  reason: collision with other method in class */
    public long m161a() {
        return this.f127a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public ib m162a() {
        return new ib(this);
    }

    public ib a(int i2) {
        this.f12794a = i2;
        b(true);
        return this;
    }

    public ib a(String str) {
        this.f128a = str;
        return this;
    }

    public ib a(Map<String, String> map) {
        this.f130a = map;
        return this;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m163a() {
        return this.f128a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public Map<String, String> m164a() {
        return this.f130a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m165a() {
        if (this.f128a == null) {
            throw new jl("Required field 'id' was not present! Struct: " + toString());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00cc, code lost:
        r9.k();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.push.jk r9) {
        /*
            r8 = this;
            r9.f()
        L_0x0003:
            com.xiaomi.push.jh r0 = r9.h()
            byte r1 = r0.b
            if (r1 != 0) goto L_0x0033
            r9.g()
            boolean r9 = r8.b()
            if (r9 == 0) goto L_0x0018
            r8.a()
            return
        L_0x0018:
            com.xiaomi.push.jl r9 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'messageTs' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r8.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        L_0x0033:
            short r1 = r0.c
            r2 = 0
            r3 = 13
            r4 = 8
            r5 = 2
            r6 = 11
            r7 = 1
            switch(r1) {
                case 1: goto L_0x0137;
                case 2: goto L_0x0127;
                case 3: goto L_0x011c;
                case 4: goto L_0x0111;
                case 5: goto L_0x0106;
                case 6: goto L_0x00f8;
                case 7: goto L_0x00ed;
                case 8: goto L_0x00df;
                case 9: goto L_0x00d1;
                case 10: goto L_0x00a5;
                case 11: goto L_0x007e;
                case 12: goto L_0x006f;
                case 13: goto L_0x0048;
                default: goto L_0x0041;
            }
        L_0x0041:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r9, r0)
            goto L_0x0141
        L_0x0048:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x0041
            com.xiaomi.push.jj r0 = r9.j()
            java.util.HashMap r1 = new java.util.HashMap
            int r3 = r0.c
            int r3 = r3 * 2
            r1.<init>(r3)
            r8.f135c = r1
        L_0x005b:
            int r1 = r0.c
            if (r2 >= r1) goto L_0x00cc
            java.lang.String r1 = r9.v()
            java.lang.String r3 = r9.v()
            java.util.Map<java.lang.String, java.lang.String> r4 = r8.f135c
            r4.put(r1, r3)
            int r2 = r2 + 1
            goto L_0x005b
        L_0x006f:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x0041
            boolean r0 = r9.p()
            r8.f131a = r0
            r8.e(r7)
            goto L_0x0141
        L_0x007e:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x0041
            com.xiaomi.push.jj r0 = r9.j()
            java.util.HashMap r1 = new java.util.HashMap
            int r3 = r0.c
            int r3 = r3 * 2
            r1.<init>(r3)
            r8.f133b = r1
        L_0x0091:
            int r1 = r0.c
            if (r2 >= r1) goto L_0x00cc
            java.lang.String r1 = r9.v()
            java.lang.String r3 = r9.v()
            java.util.Map<java.lang.String, java.lang.String> r4 = r8.f133b
            r4.put(r1, r3)
            int r2 = r2 + 1
            goto L_0x0091
        L_0x00a5:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x0041
            com.xiaomi.push.jj r0 = r9.j()
            java.util.HashMap r1 = new java.util.HashMap
            int r3 = r0.c
            int r3 = r3 * 2
            r1.<init>(r3)
            r8.f130a = r1
        L_0x00b8:
            int r1 = r0.c
            if (r2 >= r1) goto L_0x00cc
            java.lang.String r1 = r9.v()
            java.lang.String r3 = r9.v()
            java.util.Map<java.lang.String, java.lang.String> r4 = r8.f130a
            r4.put(r1, r3)
            int r2 = r2 + 1
            goto L_0x00b8
        L_0x00cc:
            r9.k()
            goto L_0x0141
        L_0x00d1:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x0041
            int r0 = r9.s()
            r8.c = r0
            r8.d((boolean) r7)
            goto L_0x0141
        L_0x00df:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x0041
            int r0 = r9.s()
            r8.b = r0
            r8.c((boolean) r7)
            goto L_0x0141
        L_0x00ed:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0041
            java.lang.String r0 = r9.v()
            r8.e = r0
            goto L_0x0141
        L_0x00f8:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x0041
            int r0 = r9.s()
            r8.f12794a = r0
            r8.b((boolean) r7)
            goto L_0x0141
        L_0x0106:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0041
            java.lang.String r0 = r9.v()
            r8.d = r0
            goto L_0x0141
        L_0x0111:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0041
            java.lang.String r0 = r9.v()
            r8.f134c = r0
            goto L_0x0141
        L_0x011c:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0041
            java.lang.String r0 = r9.v()
            r8.f132b = r0
            goto L_0x0141
        L_0x0127:
            byte r1 = r0.b
            r2 = 10
            if (r1 != r2) goto L_0x0041
            long r0 = r9.t()
            r8.f127a = r0
            r8.a((boolean) r7)
            goto L_0x0141
        L_0x0137:
            byte r1 = r0.b
            if (r1 != r6) goto L_0x0041
            java.lang.String r0 = r9.v()
            r8.f128a = r0
        L_0x0141:
            r9.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ib.a(com.xiaomi.push.jk):void");
    }

    public void a(String str, String str2) {
        if (this.f130a == null) {
            this.f130a = new HashMap();
        }
        this.f130a.put(str, str2);
    }

    public void a(boolean z) {
        this.f129a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m166a() {
        return this.f128a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m167a(ib ibVar) {
        if (ibVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = ibVar.a();
        if (((a2 || a3) && (!a2 || !a3 || !this.f128a.equals(ibVar.f128a))) || this.f127a != ibVar.f127a) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = ibVar.c();
        if ((c2 || c3) && (!c2 || !c3 || !this.f132b.equals(ibVar.f132b))) {
            return false;
        }
        boolean d2 = d();
        boolean d3 = ibVar.d();
        if ((d2 || d3) && (!d2 || !d3 || !this.f134c.equals(ibVar.f134c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = ibVar.e();
        if ((e2 || e3) && (!e2 || !e3 || !this.d.equals(ibVar.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = ibVar.f();
        if ((f2 || f3) && (!f2 || !f3 || this.f12794a != ibVar.f12794a)) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = ibVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.e.equals(ibVar.e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = ibVar.h();
        if ((h2 || h3) && (!h2 || !h3 || this.b != ibVar.b)) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = ibVar.i();
        if ((i2 || i3) && (!i2 || !i3 || this.c != ibVar.c)) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = ibVar.j();
        if ((j2 || j3) && (!j2 || !j3 || !this.f130a.equals(ibVar.f130a))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = ibVar.k();
        if ((k2 || k3) && (!k2 || !k3 || !this.f133b.equals(ibVar.f133b))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = ibVar.m();
        if ((m2 || m3) && (!m2 || !m3 || this.f131a != ibVar.f131a)) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = ibVar.n();
        if (n2 || n3) {
            return n2 && n3 && this.f135c.equals(ibVar.f135c);
        }
        return true;
    }

    public int b() {
        return this.b;
    }

    public ib b(int i2) {
        this.b = i2;
        c(true);
        return this;
    }

    public ib b(String str) {
        this.f132b = str;
        return this;
    }

    /* renamed from: b  reason: collision with other method in class */
    public String m168b() {
        return this.f132b;
    }

    /* renamed from: b  reason: collision with other method in class */
    public Map<String, String> m169b() {
        return this.f133b;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(f);
        if (this.f128a != null) {
            jkVar.a(g);
            jkVar.a(this.f128a);
            jkVar.b();
        }
        jkVar.a(h);
        jkVar.a(this.f127a);
        jkVar.b();
        if (this.f132b != null && c()) {
            jkVar.a(i);
            jkVar.a(this.f132b);
            jkVar.b();
        }
        if (this.f134c != null && d()) {
            jkVar.a(j);
            jkVar.a(this.f134c);
            jkVar.b();
        }
        if (this.d != null && e()) {
            jkVar.a(k);
            jkVar.a(this.d);
            jkVar.b();
        }
        if (f()) {
            jkVar.a(l);
            jkVar.a(this.f12794a);
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
        if (this.f130a != null && j()) {
            jkVar.a(p);
            jkVar.a(new jj((byte) 11, (byte) 11, this.f130a.size()));
            for (Map.Entry next : this.f130a.entrySet()) {
                jkVar.a((String) next.getKey());
                jkVar.a((String) next.getValue());
            }
            jkVar.d();
            jkVar.b();
        }
        if (this.f133b != null && k()) {
            jkVar.a(q);
            jkVar.a(new jj((byte) 11, (byte) 11, this.f133b.size()));
            for (Map.Entry next2 : this.f133b.entrySet()) {
                jkVar.a((String) next2.getKey());
                jkVar.a((String) next2.getValue());
            }
            jkVar.d();
            jkVar.b();
        }
        if (m()) {
            jkVar.a(r);
            jkVar.a(this.f131a);
            jkVar.b();
        }
        if (this.f135c != null && n()) {
            jkVar.a(s);
            jkVar.a(new jj((byte) 11, (byte) 11, this.f135c.size()));
            for (Map.Entry next3 : this.f135c.entrySet()) {
                jkVar.a((String) next3.getKey());
                jkVar.a((String) next3.getValue());
            }
            jkVar.d();
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public void b(String str, String str2) {
        if (this.f133b == null) {
            this.f133b = new HashMap();
        }
        this.f133b.put(str, str2);
    }

    public void b(boolean z) {
        this.f129a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m170b() {
        return this.f129a.get(0);
    }

    public int c() {
        return this.c;
    }

    public ib c(int i2) {
        this.c = i2;
        d(true);
        return this;
    }

    public ib c(String str) {
        this.f134c = str;
        return this;
    }

    /* renamed from: c  reason: collision with other method in class */
    public String m171c() {
        return this.f134c;
    }

    public void c(boolean z) {
        this.f129a.set(2, z);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m172c() {
        return this.f132b != null;
    }

    public ib d(String str) {
        this.d = str;
        return this;
    }

    public String d() {
        return this.d;
    }

    public void d(boolean z) {
        this.f129a.set(3, z);
    }

    /* renamed from: d  reason: collision with other method in class */
    public boolean m173d() {
        return this.f134c != null;
    }

    public void e(boolean z) {
        this.f129a.set(4, z);
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ib)) {
            return compareTo((ib) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f129a.get(1);
    }

    public boolean g() {
        return this.e != null;
    }

    public boolean h() {
        return this.f129a.get(2);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f129a.get(3);
    }

    public boolean j() {
        return this.f130a != null;
    }

    public boolean k() {
        return this.f133b != null;
    }

    public boolean l() {
        return this.f131a;
    }

    public boolean m() {
        return this.f129a.get(4);
    }

    public boolean n() {
        return this.f135c != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PushMetaInfo(");
        sb.append("id:");
        sb.append(this.f128a == null ? "null" : this.f128a);
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f127a);
        if (c()) {
            sb.append(", ");
            sb.append("topic:");
            sb.append(this.f132b == null ? "null" : this.f132b);
        }
        if (d()) {
            sb.append(", ");
            sb.append("title:");
            sb.append(this.f134c == null ? "null" : this.f134c);
        }
        if (e()) {
            sb.append(", ");
            sb.append("description:");
            sb.append(this.d == null ? "null" : this.d);
        }
        if (f()) {
            sb.append(", ");
            sb.append("notifyType:");
            sb.append(this.f12794a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("url:");
            sb.append(this.e == null ? "null" : this.e);
        }
        if (h()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.b);
        }
        if (i()) {
            sb.append(", ");
            sb.append("notifyId:");
            sb.append(this.c);
        }
        if (j()) {
            sb.append(", ");
            sb.append("extra:");
            if (this.f130a == null) {
                sb.append("null");
            } else {
                sb.append(this.f130a);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("internal:");
            if (this.f133b == null) {
                sb.append("null");
            } else {
                sb.append(this.f133b);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("ignoreRegInfo:");
            sb.append(this.f131a);
        }
        if (n()) {
            sb.append(", ");
            sb.append("apsProperFields:");
            if (this.f135c == null) {
                sb.append("null");
            } else {
                sb.append(this.f135c);
            }
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
