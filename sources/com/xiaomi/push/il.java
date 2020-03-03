package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class il implements iz<il, Object>, Serializable, Cloneable {
    private static final jp b = new jp("XmPushActionCustomConfig");
    private static final jh c = new jh("", (byte) 15, 1);

    /* renamed from: a  reason: collision with root package name */
    public List<hz> f12804a;

    /* JADX WARNING: type inference failed for: r0v2, types: [boolean] */
    /* JADX WARNING: type inference failed for: r1v1, types: [boolean] */
    /* renamed from: a */
    public int compareTo(il ilVar) {
        int a2;
        if (!getClass().equals(ilVar.getClass())) {
            return getClass().getName().compareTo(ilVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ilVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() == null || (a2 = ja.a((List) this.f12804a, (List) ilVar.f12804a)) == 0) {
            return 0;
        }
        return a2;
    }

    public List<hz> a() {
        return this.f12804a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m204a() {
        if (this.f12804a == null) {
            throw new jl("Required field 'customConfigs' was not present! Struct: " + toString());
        }
    }

    public void a(jk jkVar) {
        jkVar.f();
        while (true) {
            jh h = jkVar.h();
            if (h.b == 0) {
                jkVar.g();
                a();
                return;
            }
            if (h.c == 1 && h.b == 15) {
                ji l = jkVar.l();
                this.f12804a = new ArrayList(l.b);
                for (int i = 0; i < l.b; i++) {
                    hz hzVar = new hz();
                    hzVar.a(jkVar);
                    this.f12804a.add(hzVar);
                }
                jkVar.m();
            } else {
                jn.a(jkVar, h.b);
            }
            jkVar.i();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m205a() {
        return this.f12804a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m206a(il ilVar) {
        if (ilVar == null) {
            return false;
        }
        List<hz> a2 = a();
        List<hz> a3 = ilVar.a();
        if (a2 == null && a3 == null) {
            return true;
        }
        return (a2 == null || a3 == null || !this.f12804a.equals(ilVar.f12804a)) ? false : true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(b);
        if (this.f12804a != null) {
            jkVar.a(c);
            jkVar.a(new ji((byte) 12, this.f12804a.size()));
            for (hz b2 : this.f12804a) {
                b2.b(jkVar);
            }
            jkVar.e();
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof il)) {
            return compareTo((il) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCustomConfig(");
        sb.append("customConfigs:");
        if (this.f12804a == null) {
            sb.append("null");
        } else {
            sb.append(this.f12804a);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
