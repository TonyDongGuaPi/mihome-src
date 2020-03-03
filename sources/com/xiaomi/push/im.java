package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class im implements iz<im, Object>, Serializable, Cloneable {
    private static final jp b = new jp("XmPushActionNormalConfig");
    private static final jh c = new jh("", (byte) 15, 1);

    /* renamed from: a  reason: collision with root package name */
    public List<hx> f12805a;

    /* JADX WARNING: type inference failed for: r0v2, types: [boolean] */
    /* JADX WARNING: type inference failed for: r1v1, types: [boolean] */
    /* renamed from: a */
    public int compareTo(im imVar) {
        int a2;
        if (!getClass().equals(imVar.getClass())) {
            return getClass().getName().compareTo(imVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(imVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a() == null || (a2 = ja.a((List) this.f12805a, (List) imVar.f12805a)) == 0) {
            return 0;
        }
        return a2;
    }

    public List<hx> a() {
        return this.f12805a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m207a() {
        if (this.f12805a == null) {
            throw new jl("Required field 'normalConfigs' was not present! Struct: " + toString());
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
                this.f12805a = new ArrayList(l.b);
                for (int i = 0; i < l.b; i++) {
                    hx hxVar = new hx();
                    hxVar.a(jkVar);
                    this.f12805a.add(hxVar);
                }
                jkVar.m();
            } else {
                jn.a(jkVar, h.b);
            }
            jkVar.i();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m208a() {
        return this.f12805a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m209a(im imVar) {
        if (imVar == null) {
            return false;
        }
        List<hx> a2 = a();
        List<hx> a3 = imVar.a();
        if (a2 == null && a3 == null) {
            return true;
        }
        return (a2 == null || a3 == null || !this.f12805a.equals(imVar.f12805a)) ? false : true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(b);
        if (this.f12805a != null) {
            jkVar.a(c);
            jkVar.a(new ji((byte) 12, this.f12805a.size()));
            for (hx b2 : this.f12805a) {
                b2.b(jkVar);
            }
            jkVar.e();
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof im)) {
            return compareTo((im) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionNormalConfig(");
        sb.append("normalConfigs:");
        if (this.f12805a == null) {
            sb.append("null");
        } else {
            sb.append(this.f12805a);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
