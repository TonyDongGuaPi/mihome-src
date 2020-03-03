package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.BitSet;

public class hw implements iz<hw, Object>, Serializable, Cloneable {
    private static final jp b = new jp("DataCollectionItem");
    private static final jh c = new jh("", (byte) 10, 1);
    private static final jh d = new jh("", (byte) 8, 2);
    private static final jh e = new jh("", (byte) 11, 3);

    /* renamed from: a  reason: collision with root package name */
    public long f12788a;

    /* renamed from: a  reason: collision with other field name */
    public hq f107a;

    /* renamed from: a  reason: collision with other field name */
    public String f108a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f109a = new BitSet(1);

    /* renamed from: a */
    public int compareTo(hw hwVar) {
        int a2;
        int a3;
        int a4;
        if (!getClass().equals(hwVar.getClass())) {
            return getClass().getName().compareTo(hwVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(hwVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() && (a4 = ja.a(this.f12788a, hwVar.f12788a)) != 0) {
            return a4;
        }
        int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hwVar.b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (b() && (a3 = ja.a((Comparable) this.f107a, (Comparable) hwVar.f107a)) != 0) {
            return a3;
        }
        int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hwVar.c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (!c() || (a2 = ja.a(this.f108a, hwVar.f108a)) == 0) {
            return 0;
        }
        return a2;
    }

    public hw a(long j) {
        this.f12788a = j;
        a(true);
        return this;
    }

    public hw a(hq hqVar) {
        this.f107a = hqVar;
        return this;
    }

    public hw a(String str) {
        this.f108a = str;
        return this;
    }

    public String a() {
        return this.f108a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m141a() {
        if (this.f107a == null) {
            throw new jl("Required field 'collectionType' was not present! Struct: " + toString());
        } else if (this.f108a == null) {
            throw new jl("Required field 'content' was not present! Struct: " + toString());
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
            boolean r4 = r3.a()
            if (r4 == 0) goto L_0x0018
            r3.a()
            return
        L_0x0018:
            com.xiaomi.push.jl r4 = new com.xiaomi.push.jl
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Required field 'collectedAt' was not found in serialized data! Struct: "
            r0.append(r1)
            java.lang.String r1 = r3.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        L_0x0033:
            short r1 = r0.c
            switch(r1) {
                case 1: goto L_0x005c;
                case 2: goto L_0x004b;
                case 3: goto L_0x003e;
                default: goto L_0x0038;
            }
        L_0x0038:
            byte r0 = r0.b
            com.xiaomi.push.jn.a(r4, r0)
            goto L_0x006c
        L_0x003e:
            byte r1 = r0.b
            r2 = 11
            if (r1 != r2) goto L_0x0038
            java.lang.String r0 = r4.v()
            r3.f108a = r0
            goto L_0x006c
        L_0x004b:
            byte r1 = r0.b
            r2 = 8
            if (r1 != r2) goto L_0x0038
            int r0 = r4.s()
            com.xiaomi.push.hq r0 = com.xiaomi.push.hq.a(r0)
            r3.f107a = r0
            goto L_0x006c
        L_0x005c:
            byte r1 = r0.b
            r2 = 10
            if (r1 != r2) goto L_0x0038
            long r0 = r4.t()
            r3.f12788a = r0
            r0 = 1
            r3.a((boolean) r0)
        L_0x006c:
            r4.i()
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hw.a(com.xiaomi.push.jk):void");
    }

    public void a(boolean z) {
        this.f109a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m142a() {
        return this.f109a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m143a(hw hwVar) {
        if (hwVar == null || this.f12788a != hwVar.f12788a) {
            return false;
        }
        boolean b2 = b();
        boolean b3 = hwVar.b();
        if ((b2 || b3) && (!b2 || !b3 || !this.f107a.equals(hwVar.f107a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = hwVar.c();
        if (c2 || c3) {
            return c2 && c3 && this.f108a.equals(hwVar.f108a);
        }
        return true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(b);
        jkVar.a(c);
        jkVar.a(this.f12788a);
        jkVar.b();
        if (this.f107a != null) {
            jkVar.a(d);
            jkVar.a(this.f107a.a());
            jkVar.b();
        }
        if (this.f108a != null) {
            jkVar.a(e);
            jkVar.a(this.f108a);
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public boolean b() {
        return this.f107a != null;
    }

    public boolean c() {
        return this.f108a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hw)) {
            return compareTo((hw) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataCollectionItem(");
        sb.append("collectedAt:");
        sb.append(this.f12788a);
        sb.append(", ");
        sb.append("collectionType:");
        if (this.f107a == null) {
            sb.append("null");
        } else {
            sb.append(this.f107a);
        }
        sb.append(", ");
        sb.append("content:");
        sb.append(this.f108a == null ? "null" : this.f108a);
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
