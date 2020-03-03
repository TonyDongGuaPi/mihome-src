package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;
import java.util.List;

public class hx implements iz<hx, Object>, Serializable, Cloneable {
    private static final jp b = new jp("NormalConfig");
    private static final jh c = new jh("", (byte) 8, 1);
    private static final jh d = new jh("", (byte) 15, 2);
    private static final jh e = new jh("", (byte) 8, 3);

    /* renamed from: a  reason: collision with root package name */
    public int f12789a;

    /* renamed from: a  reason: collision with other field name */
    public hu f110a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f111a = new BitSet(1);

    /* renamed from: a  reason: collision with other field name */
    public List<hz> f112a;

    public int a() {
        return this.f12789a;
    }

    /* renamed from: a */
    public int compareTo(hx hxVar) {
        int a2;
        int a3;
        int a4;
        if (!getClass().equals(hxVar.getClass())) {
            return getClass().getName().compareTo(hxVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(hxVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a4 = ja.a(this.f12789a, hxVar.f12789a)) != 0) {
            return a4;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hxVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a3 = ja.a((List) this.f112a, (List) hxVar.f112a)) != 0) {
            return a3;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hxVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (!c() || (a2 = ja.a((Comparable) this.f110a, (Comparable) hxVar.f110a)) == 0) {
            return 0;
        }
        return a2;
    }

    /* renamed from: a  reason: collision with other method in class */
    public hu m144a() {
        return this.f110a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m145a() {
        if (this.f112a == null) {
            throw new jl("Required field 'configItems' was not present! Struct: " + toString());
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
            java.lang.String r1 = "Required field 'version' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L_0x0033:
            short r1 = r0.c
            r2 = 8
            switch(r1) {
                case 1: goto L_0x007b;
                case 2: goto L_0x004f;
                case 3: goto L_0x0040;
                default: goto L_0x003a;
            }
        L_0x003a:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r5, r0)
            goto L_0x0089
        L_0x0040:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003a
            int r0 = r5.s()
            com.xiaomi.push.hu r0 = com.xiaomi.push.hu.a(r0)
            r4.f110a = r0
            goto L_0x0089
        L_0x004f:
            byte r1 = r0.b
            r2 = 15
            if (r1 != r2) goto L_0x003a
            com.xiaomi.push.ji r0 = r5.l()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.b
            r1.<init>(r2)
            r4.f112a = r1
            r1 = 0
        L_0x0063:
            int r2 = r0.b
            if (r1 >= r2) goto L_0x0077
            com.xiaomi.push.hz r2 = new com.xiaomi.push.hz
            r2.<init>()
            r2.a((com.xiaomi.push.jk) r5)
            java.util.List<com.xiaomi.push.hz> r3 = r4.f112a
            r3.add(r2)
            int r1 = r1 + 1
            goto L_0x0063
        L_0x0077:
            r5.m()
            goto L_0x0089
        L_0x007b:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x003a
            int r0 = r5.s()
            r4.f12789a = r0
            r0 = 1
            r4.a((boolean) r0)
        L_0x0089:
            r5.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hx.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f111a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m146a() {
        return this.f111a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m147a(hx hxVar) {
        if (hxVar == null || this.f12789a != hxVar.f12789a) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = hxVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f112a.equals(hxVar.f112a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = hxVar.c();
        if (c2 || c3) {
            return c2 && c3 && this.f110a.equals(hxVar.f110a);
        }
        return true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(b);
        jkVar.a(c);
        jkVar.a(this.f12789a);
        jkVar.b();
        if (this.f112a != null) {
            jkVar.a(d);
            jkVar.a(new ji((byte) 12, this.f112a.size()));
            for (hz b2 : this.f112a) {
                b2.b(jkVar);
            }
            jkVar.e();
            jkVar.b();
        }
        if (this.f110a != null && c()) {
            jkVar.a(e);
            jkVar.a(this.f110a.a());
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public boolean b() {
        return this.f112a != null;
    }

    public boolean c() {
        return this.f110a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hx)) {
            return compareTo((hx) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NormalConfig(");
        sb.append("version:");
        sb.append(this.f12789a);
        sb.append(", ");
        sb.append("configItems:");
        if (this.f112a == null) {
            sb.append("null");
        } else {
            sb.append(this.f112a);
        }
        if (c()) {
            sb.append(", ");
            sb.append("type:");
            if (this.f110a == null) {
                sb.append("null");
            } else {
                sb.append(this.f110a);
            }
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
