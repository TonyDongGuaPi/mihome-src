package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class ig implements iz<ig, Object>, Serializable, Cloneable {
    private static final jp c = new jp("XmPushActionCheckClientInfo");
    private static final jh d = new jh("", (byte) 8, 1);
    private static final jh e = new jh("", (byte) 8, 2);

    /* renamed from: a  reason: collision with root package name */
    public int f12799a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f154a = new BitSet(2);
    public int b;

    /* renamed from: a */
    public int compareTo(ig igVar) {
        int a2;
        int a3;
        if (!getClass().equals(igVar.getClass())) {
            return getClass().getName().compareTo(igVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(igVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a3 = ja.a(this.f12799a, igVar.f12799a)) != 0) {
            return a3;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(igVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (!b() || (a2 = ja.a(this.b, igVar.b)) == 0) {
            return 0;
        }
        return a2;
    }

    public ig a(int i) {
        this.f12799a = i;
        a(true);
        return this;
    }

    public void a() {
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
            if (r1 != 0) goto L_0x0054
            r5.g()
            boolean r5 = r4.a()
            if (r5 == 0) goto L_0x0039
            boolean r5 = r4.b()
            if (r5 == 0) goto L_0x001e
            r4.a()
            return
        L_0x001e:
            com.xiaomi.push.jl r5 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'pluginConfigVersion' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L_0x0039:
            com.xiaomi.push.jl r5 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'miscConfigVersion' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r4.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L_0x0054:
            short r1 = r0.c
            r2 = 1
            r3 = 8
            switch(r1) {
                case 1: goto L_0x0070;
                case 2: goto L_0x0062;
                default: goto L_0x005c;
            }
        L_0x005c:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r5, r0)
            goto L_0x007d
        L_0x0062:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x005c
            int r0 = r5.s()
            r4.b = r0
            r4.b((boolean) r2)
            goto L_0x007d
        L_0x0070:
            byte r1 = r0.b
            if (r1 != r3) goto L_0x005c
            int r0 = r5.s()
            r4.f12799a = r0
            r4.a((boolean) r2)
        L_0x007d:
            r5.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ig.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f154a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m182a() {
        return this.f154a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m183a(ig igVar) {
        return igVar != null && this.f12799a == igVar.f12799a && this.b == igVar.b;
    }

    public ig b(int i) {
        this.b = i;
        b(true);
        return this;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(c);
        jkVar.a(d);
        jkVar.a(this.f12799a);
        jkVar.b();
        jkVar.a(e);
        jkVar.a(this.b);
        jkVar.b();
        jkVar.c();
        jkVar.a();
    }

    public void b(boolean z) {
        this.f154a.set(1, z);
    }

    public boolean b() {
        return this.f154a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ig)) {
            return compareTo((ig) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "XmPushActionCheckClientInfo(" + "miscConfigVersion:" + this.f12799a + ", " + "pluginConfigVersion:" + this.b + Operators.BRACKET_END_STR;
    }
}
