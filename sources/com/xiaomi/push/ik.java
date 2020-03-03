package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;

public class ik implements iz<ik, Object>, Serializable, Cloneable {
    private static final jp c = new jp("XmPushActionContainer");
    private static final jh d = new jh("", (byte) 8, 1);
    private static final jh e = new jh("", (byte) 2, 2);
    private static final jh f = new jh("", (byte) 2, 3);
    private static final jh g = new jh("", (byte) 11, 4);
    private static final jh h = new jh("", (byte) 11, 5);
    private static final jh i = new jh("", (byte) 11, 6);
    private static final jh j = new jh("", (byte) 12, 7);
    private static final jh k = new jh("", (byte) 12, 8);

    /* renamed from: a  reason: collision with root package name */
    public ho f12803a;

    /* renamed from: a  reason: collision with other field name */
    public ib f166a;

    /* renamed from: a  reason: collision with other field name */
    public id f167a;

    /* renamed from: a  reason: collision with other field name */
    public String f168a;

    /* renamed from: a  reason: collision with other field name */
    public ByteBuffer f169a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f170a = new BitSet(2);

    /* renamed from: a  reason: collision with other field name */
    public boolean f171a = true;
    public String b;

    /* renamed from: b  reason: collision with other field name */
    public boolean f172b = true;

    /* renamed from: a */
    public int compareTo(ik ikVar) {
        int a2;
        int a3;
        int a4;
        int a5;
        int a6;
        int a7;
        int a8;
        int a9;
        if (!getClass().equals(ikVar.getClass())) {
            return getClass().getName().compareTo(ikVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ikVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a9 = ja.a((Comparable) this.f12803a, (Comparable) ikVar.f12803a)) != 0) {
            return a9;
        }
        int compareTo2 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(ikVar.c()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (c() && (a8 = ja.a(this.f171a, ikVar.f171a)) != 0) {
            return a8;
        }
        int compareTo3 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(ikVar.d()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (d() && (a7 = ja.a(this.f172b, ikVar.f172b)) != 0) {
            return a7;
        }
        int compareTo4 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(ikVar.e()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (e() && (a6 = ja.a((Comparable) this.f169a, (Comparable) ikVar.f169a)) != 0) {
            return a6;
        }
        int compareTo5 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(ikVar.f()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (f() && (a5 = ja.a(this.f168a, ikVar.f168a)) != 0) {
            return a5;
        }
        int compareTo6 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(ikVar.g()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (g() && (a4 = ja.a(this.b, ikVar.b)) != 0) {
            return a4;
        }
        int compareTo7 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(ikVar.h()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (h() && (a3 = ja.a((Comparable) this.f167a, (Comparable) ikVar.f167a)) != 0) {
            return a3;
        }
        int compareTo8 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(ikVar.i()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (!i() || (a2 = ja.a((Comparable) this.f166a, (Comparable) ikVar.f166a)) == 0) {
            return 0;
        }
        return a2;
    }

    public ho a() {
        return this.f12803a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public ib m195a() {
        return this.f166a;
    }

    public ik a(ho hoVar) {
        this.f12803a = hoVar;
        return this;
    }

    public ik a(ib ibVar) {
        this.f166a = ibVar;
        return this;
    }

    public ik a(id idVar) {
        this.f167a = idVar;
        return this;
    }

    public ik a(String str) {
        this.f168a = str;
        return this;
    }

    public ik a(ByteBuffer byteBuffer) {
        this.f169a = byteBuffer;
        return this;
    }

    public ik a(boolean z) {
        this.f171a = z;
        a(true);
        return this;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m196a() {
        return this.f168a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m197a() {
        if (this.f12803a == null) {
            throw new jl("Required field 'action' was not present! Struct: " + toString());
        } else if (this.f169a == null) {
            throw new jl("Required field 'pushAction' was not present! Struct: " + toString());
        } else if (this.f167a == null) {
            throw new jl("Required field 'target' was not present! Struct: " + toString());
        }
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
            if (r1 != 0) goto L_0x0054
            r7.g()
            boolean r7 = r6.c()
            if (r7 == 0) goto L_0x0039
            boolean r7 = r6.d()
            if (r7 == 0) goto L_0x001e
            r6.a()
            return
        L_0x001e:
            com.xiaomi.push.jl r7 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'isRequest' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r6.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L_0x0039:
            com.xiaomi.push.jl r7 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'encryptAction' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r6.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L_0x0054:
            short r1 = r0.c
            r2 = 1
            r3 = 12
            r4 = 2
            r5 = 11
            switch(r1) {
                case 1: goto L_0x00c5;
                case 2: goto L_0x00b7;
                case 3: goto L_0x00a9;
                case 4: goto L_0x009e;
                case 5: goto L_0x0093;
                case 6: goto L_0x0088;
                case 7: goto L_0x0077;
                case 8: goto L_0x0066;
                default: goto L_0x005f;
            }
        L_0x005f:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r7, r0)
            goto L_0x00d5
        L_0x0066:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x005f
            com.xiaomi.push.ib r0 = new com.xiaomi.push.ib
            r0.<init>()
            r6.f166a = r0
            com.xiaomi.push.ib r0 = r6.f166a
            r0.a((com.xiaomi.push.jk) r7)
            goto L_0x00d5
        L_0x0077:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x005f
            com.xiaomi.push.id r0 = new com.xiaomi.push.id
            r0.<init>()
            r6.f167a = r0
            com.xiaomi.push.id r0 = r6.f167a
            r0.a((com.xiaomi.push.jk) r7)
            goto L_0x00d5
        L_0x0088:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x005f
            java.lang.String r0 = r7.v()
            r6.b = r0
            goto L_0x00d5
        L_0x0093:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x005f
            java.lang.String r0 = r7.v()
            r6.f168a = r0
            goto L_0x00d5
        L_0x009e:
            byte r1 = r0.b
            if (r1 != r5) goto L_0x005f
            java.nio.ByteBuffer r0 = r7.w()
            r6.f169a = r0
            goto L_0x00d5
        L_0x00a9:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x005f
            boolean r0 = r7.p()
            r6.f172b = r0
            r6.b((boolean) r2)
            goto L_0x00d5
        L_0x00b7:
            byte r1 = r0.b
            if (r1 != r4) goto L_0x005f
            boolean r0 = r7.p()
            r6.f171a = r0
            r6.a((boolean) r2)
            goto L_0x00d5
        L_0x00c5:
            byte r1 = r0.b
            r2 = 8
            if (r1 != r2) goto L_0x005f
            int r0 = r7.s()
            com.xiaomi.push.ho r0 = com.xiaomi.push.ho.a(r0)
            r6.f12803a = r0
        L_0x00d5:
            r7.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ik.a(com.xiaomi.push.jk):void");
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m198a(boolean z) {
        this.f170a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m199a() {
        return this.f12803a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m200a(ik ikVar) {
        if (ikVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = ikVar.a();
        if (((a2 || a3) && (!a2 || !a3 || !this.f12803a.equals(ikVar.f12803a))) || this.f171a != ikVar.f171a || this.f172b != ikVar.f172b) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = ikVar.e();
        if ((e2 || e3) && (!e2 || !e3 || !this.f169a.equals(ikVar.f169a))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = ikVar.f();
        if ((f2 || f3) && (!f2 || !f3 || !this.f168a.equals(ikVar.f168a))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = ikVar.g();
        if ((g2 || g3) && (!g2 || !g3 || !this.b.equals(ikVar.b))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = ikVar.h();
        if ((h2 || h3) && (!h2 || !h3 || !this.f167a.compareTo(ikVar.f167a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = ikVar.i();
        if (i2 || i3) {
            return i2 && i3 && this.f166a.compareTo(ikVar.f166a);
        }
        return true;
    }

    /* renamed from: a  reason: collision with other method in class */
    public byte[] m201a() {
        a(ja.c(this.f169a));
        return this.f169a.array();
    }

    public ik b(String str) {
        this.b = str;
        return this;
    }

    public ik b(boolean z) {
        this.f172b = z;
        b(true);
        return this;
    }

    public String b() {
        return this.b;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(c);
        if (this.f12803a != null) {
            jkVar.a(d);
            jkVar.a(this.f12803a.a());
            jkVar.b();
        }
        jkVar.a(e);
        jkVar.a(this.f171a);
        jkVar.b();
        jkVar.a(f);
        jkVar.a(this.f172b);
        jkVar.b();
        if (this.f169a != null) {
            jkVar.a(g);
            jkVar.a(this.f169a);
            jkVar.b();
        }
        if (this.f168a != null && f()) {
            jkVar.a(h);
            jkVar.a(this.f168a);
            jkVar.b();
        }
        if (this.b != null && g()) {
            jkVar.a(i);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.f167a != null) {
            jkVar.a(j);
            this.f167a.b(jkVar);
            jkVar.b();
        }
        if (this.f166a != null && i()) {
            jkVar.a(k);
            this.f166a.b(jkVar);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    /* renamed from: b  reason: collision with other method in class */
    public void m202b(boolean z) {
        this.f170a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m203b() {
        return this.f171a;
    }

    public boolean c() {
        return this.f170a.get(0);
    }

    public boolean d() {
        return this.f170a.get(1);
    }

    public boolean e() {
        return this.f169a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ik)) {
            return compareTo((ik) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f168a != null;
    }

    public boolean g() {
        return this.b != null;
    }

    public boolean h() {
        return this.f167a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f166a != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionContainer(");
        sb.append("action:");
        if (this.f12803a == null) {
            sb.append("null");
        } else {
            sb.append(this.f12803a);
        }
        sb.append(", ");
        sb.append("encryptAction:");
        sb.append(this.f171a);
        sb.append(", ");
        sb.append("isRequest:");
        sb.append(this.f172b);
        sb.append(", ");
        sb.append("pushAction:");
        if (this.f169a == null) {
            sb.append("null");
        } else {
            ja.a(this.f169a, sb);
        }
        if (f()) {
            sb.append(", ");
            sb.append("appid:");
            sb.append(this.f168a == null ? "null" : this.f168a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("packageName:");
            sb.append(this.b == null ? "null" : this.b);
        }
        sb.append(", ");
        sb.append("target:");
        if (this.f167a == null) {
            sb.append("null");
        } else {
            sb.append(this.f167a);
        }
        if (i()) {
            sb.append(", ");
            sb.append("metaInfo:");
            if (this.f166a == null) {
                sb.append("null");
            } else {
                sb.append(this.f166a);
            }
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
