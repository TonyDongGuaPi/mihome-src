package com.tmall.wireless.vaf.expr.engine.data;

import java.util.LinkedList;
import java.util.List;

public class ValueCache {

    /* renamed from: a  reason: collision with root package name */
    private static ValueCache f9356a;
    private List<IntValue> b = new LinkedList();
    private List<FloatValue> c = new LinkedList();
    private List<StrValue> d = new LinkedList();
    private List<ObjValue> e = new LinkedList();

    public static ValueCache a() {
        if (f9356a == null) {
            f9356a = new ValueCache();
        }
        return f9356a;
    }

    private ValueCache() {
    }

    public IntValue a(int i) {
        if (this.b.size() <= 0) {
            return new IntValue(i);
        }
        IntValue remove = this.b.remove(0);
        remove.f9353a = i;
        return remove;
    }

    public void a(IntValue intValue) {
        this.b.add(intValue);
    }

    public FloatValue a(float f) {
        if (this.c.size() <= 0) {
            return new FloatValue(f);
        }
        FloatValue remove = this.c.remove(0);
        remove.f9352a = f;
        return remove;
    }

    public void a(FloatValue floatValue) {
        this.c.add(floatValue);
    }

    public StrValue a(String str) {
        if (this.d.size() <= 0) {
            return new StrValue(str);
        }
        StrValue remove = this.d.remove(0);
        remove.f9355a = str;
        return remove;
    }

    public void a(StrValue strValue) {
        this.d.add(strValue);
    }

    public ObjValue a(Object obj) {
        if (this.e.size() <= 0) {
            return new ObjValue(obj);
        }
        ObjValue remove = this.e.remove(0);
        remove.f9354a = obj;
        return remove;
    }

    public void a(ObjValue objValue) {
        this.e.add(objValue);
    }
}
