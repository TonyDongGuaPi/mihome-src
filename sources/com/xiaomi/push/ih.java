package com.xiaomi.push;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ih implements iz<ih, Object>, Serializable, Cloneable {
    private static final jp b = new jp("XmPushActionCollectData");
    private static final jh c = new jh("", (byte) 15, 1);

    /* renamed from: a  reason: collision with root package name */
    public List<hw> f12800a;

    /* renamed from: a */
    public int compareTo(ih ihVar) {
        int a2;
        if (!getClass().equals(ihVar.getClass())) {
            return getClass().getName().compareTo(ihVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(ihVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!a() || (a2 = ja.a((List) this.f12800a, (List) ihVar.f12800a)) == 0) {
            return 0;
        }
        return a2;
    }

    public ih a(List<hw> list) {
        this.f12800a = list;
        return this;
    }

    public void a() {
        if (this.f12800a == null) {
            throw new jl("Required field 'dataCollectionItems' was not present! Struct: " + toString());
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
                this.f12800a = new ArrayList(l.b);
                for (int i = 0; i < l.b; i++) {
                    hw hwVar = new hw();
                    hwVar.a(jkVar);
                    this.f12800a.add(hwVar);
                }
                jkVar.m();
            } else {
                jn.a(jkVar, h.b);
            }
            jkVar.i();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m184a() {
        return this.f12800a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m185a(ih ihVar) {
        if (ihVar == null) {
            return false;
        }
        boolean a2 = a();
        boolean a3 = ihVar.a();
        if (a2 || a3) {
            return a2 && a3 && this.f12800a.equals(ihVar.f12800a);
        }
        return true;
    }

    public void b(jk jkVar) {
        a();
        jkVar.a(b);
        if (this.f12800a != null) {
            jkVar.a(c);
            jkVar.a(new ji((byte) 12, this.f12800a.size()));
            for (hw b2 : this.f12800a) {
                b2.b(jkVar);
            }
            jkVar.e();
            jkVar.b();
        }
        jkVar.c();
        jkVar.a();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ih)) {
            return compareTo((ih) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCollectData(");
        sb.append("dataCollectionItems:");
        if (this.f12800a == null) {
            sb.append("null");
        } else {
            sb.append(this.f12800a);
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }
}
