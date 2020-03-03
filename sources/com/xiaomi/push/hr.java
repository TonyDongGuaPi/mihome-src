package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class hr implements iz<hr, Object>, Serializable, Cloneable {
    private static final jp b = new jp("ClientUploadData");
    private static final jh c = new jh("", (byte) 15, 1);

    /* renamed from: a  reason: collision with root package name */
    public List<hs> f12783a;

    public int a() {
        if (this.f12783a == null) {
            return 0;
        }
        return this.f12783a.size();
    }

    /* renamed from: a */
    public int compareTo(hr hrVar) {
        int a2;
        if (!getClass().equals(hrVar.getClass())) {
            return getClass().getName().compareTo(hrVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(hrVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!a() || (a2 = ja.a((List) this.f12783a, (List) hrVar.f12783a)) == 0) {
            return 0;
        }
        return a2;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m129a() {
        if (this.f12783a == null) {
            throw new jl("Required field 'uploadDataItems' was not present! Struct: " + toString());
        }
    }

    public void a(hs hsVar) {
        if (this.f12783a == null) {
            this.f12783a = new ArrayList();
        }
        this.f12783a.add(hsVar);
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
                this.f12783a = new ArrayList(l.b);
                for (int i = 0; i < l.b; i++) {
                    hs hsVar = new hs();
                    hsVar.a(jkVar);
                    this.f12783a.add(hsVar);
                }
                jkVar.m();
            } else {
                jn.a(jkVar, h.b);
            }
            jkVar.i();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m130a() {
        return this.f12783a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m131a(hr hrVar) {
        if (hrVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = hrVar.a();
        if (a2 || a3) {
            return a2 && a3 && this.f12783a.equals(hrVar.f12783a);
        }
        return true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(b);
        if (this.f12783a != null) {
            jkVar.a(c);
            jkVar.a(new ji((byte) 12, this.f12783a.size()));
            for (hs b2 : this.f12783a) {
                b2.b(jkVar);
            }
            jkVar.e();
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof hr)) {
            return compareTo((hr) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ClientUploadData(");
        sb.append("uploadDataItems:");
        if (this.f12783a == null) {
            sb.append("null");
        } else {
            sb.append(this.f12783a);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
