package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.List;
import org.cybergarage.upnp.event.Subscription;

public class fl implements iz<fl, Object>, Serializable, Cloneable {
    private static final jp c = new jp("StatsEvents");
    private static final jh d = new jh("", (byte) 11, 1);
    private static final jh e = new jh("", (byte) 11, 2);
    private static final jh f = new jh("", (byte) 15, 3);

    /* renamed from: a  reason: collision with root package name */
    public String f12737a;

    /* renamed from: a  reason: collision with other field name */
    public List<fk> f79a;
    public String b;

    public fl() {
    }

    public fl(String str, List<fk> list) {
        this();
        this.f12737a = str;
        this.f79a = list;
    }

    /* renamed from: a */
    public int compareTo(fl flVar) {
        int a2;
        int a3;
        int a4;
        if (!getClass().equals(flVar.getClass())) {
            return getClass().getName().compareTo(flVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(flVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a4 = ja.a(this.f12737a, flVar.f12737a)) != 0) {
            return a4;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(flVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a3 = ja.a(this.b, flVar.b)) != 0) {
            return a3;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(flVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (!c() || (a2 = ja.a((List) this.f79a, (List) flVar.f79a)) == 0) {
            return 0;
        }
        return a2;
    }

    public fl a(String str) {
        this.b = str;
        return this;
    }

    public void a() {
        if (this.f12737a == null) {
            throw new jl("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.f79a == null) {
            throw new jl("Required field 'events' was not present! Struct: " + toString());
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
            if (r1 != 0) goto L_0x0012
            r5.g()
            r4.a()
            return
        L_0x0012:
            short r1 = r0.c
            r2 = 11
            switch(r1) {
                case 1: goto L_0x0056;
                case 2: goto L_0x004b;
                case 3: goto L_0x001f;
                default: goto L_0x0019;
            }
        L_0x0019:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r5, r0)
            goto L_0x0060
        L_0x001f:
            byte r1 = r0.b
            r2 = 15
            if (r1 != r2) goto L_0x0019
            com.xiaomi.push.ji r0 = r5.l()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.b
            r1.<init>(r2)
            r4.f79a = r1
            r1 = 0
        L_0x0033:
            int r2 = r0.b
            if (r1 >= r2) goto L_0x0047
            com.xiaomi.push.fk r2 = new com.xiaomi.push.fk
            r2.<init>()
            r2.a((com.xiaomi.push.jk) r5)
            java.util.List<com.xiaomi.push.fk> r3 = r4.f79a
            r3.add(r2)
            int r1 = r1 + 1
            goto L_0x0033
        L_0x0047:
            r5.m()
            goto L_0x0060
        L_0x004b:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r5.v()
            r4.b = r0
            goto L_0x0060
        L_0x0056:
            byte r1 = r0.b
            if (r1 != r2) goto L_0x0019
            java.lang.String r0 = r5.v()
            r4.f12737a = r0
        L_0x0060:
            r5.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.fl.a(com.xiaomi.push.jk):void");
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m121a() {
        return this.f12737a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m122a(fl flVar) {
        if (flVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = flVar.a();
        if ((a2 || a3) && (!a2 || !a3 || !this.f12737a.equals(flVar.f12737a))) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = flVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.b.equals(flVar.b))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = flVar.c();
        if (c2 || c3) {
            return c2 && c3 && this.f79a.equals(flVar.f79a);
        }
        return true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(c);
        if (this.f12737a != null) {
            jkVar.a(d);
            jkVar.a(this.f12737a);
            jkVar.b();
        }
        if (this.b != null && b()) {
            jkVar.a(e);
            jkVar.a(this.b);
            jkVar.b();
        }
        if (this.f79a != null) {
            jkVar.a(f);
            jkVar.a(new ji((byte) 12, this.f79a.size()));
            for (fk b2 : this.f79a) {
                b2.b(jkVar);
            }
            jkVar.e();
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean c() {
        return this.f79a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof fl)) {
            return compareTo((fl) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvents(");
        sb.append(Subscription.UUID);
        sb.append(this.f12737a == null ? "null" : this.f12737a);
        if (b()) {
            sb.append(", ");
            sb.append("operator:");
            sb.append(this.b == null ? "null" : this.b);
        }
        sb.append(", ");
        sb.append("events:");
        if (this.f79a == null) {
            sb.append("null");
        } else {
            sb.append(this.f79a);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
