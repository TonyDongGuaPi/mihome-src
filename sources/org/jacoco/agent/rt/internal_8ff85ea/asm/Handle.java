package org.jacoco.agent.rt.internal_8ff85ea.asm;

import com.taobao.weex.el.parse.Operators;

public final class Handle {

    /* renamed from: a  reason: collision with root package name */
    final int f3593a;
    final String b;
    final String c;
    final String d;
    final boolean e;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public Handle(int i, String str, String str2, String str3) {
        this(i, str, str2, str3, i == 9);
    }

    public Handle(int i, String str, String str2, String str3, boolean z) {
        this.f3593a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
    }

    public int a() {
        return this.f3593a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Handle)) {
            return false;
        }
        Handle handle = (Handle) obj;
        if (this.f3593a != handle.f3593a || this.e != handle.e || !this.b.equals(handle.b) || !this.c.equals(handle.c) || !this.d.equals(handle.d)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f3593a + (this.e ? 64 : 0) + (this.b.hashCode() * this.c.hashCode() * this.d.hashCode());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append('.');
        sb.append(this.c);
        sb.append(this.d);
        sb.append(" (");
        sb.append(this.f3593a);
        sb.append(this.e ? " itf" : "");
        sb.append(Operators.BRACKET_END);
        return sb.toString();
    }
}
