package com.tmall.wireless.vaf.expr.engine.data;

import android.util.Log;

public class ObjValue extends Value {
    private static final String c = "ObjValue_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    public Object f9354a;

    public ObjValue(Object obj) {
        this.f9354a = obj;
    }

    public void a(Value value) {
        if (value != null) {
            this.f9354a = ((ObjValue) value).f9354a;
        } else {
            Log.e(c, "value is null");
        }
    }

    /* renamed from: a */
    public Value clone() {
        return b.a(this.f9354a);
    }

    public Class<?> b() {
        return this.f9354a.getClass();
    }

    public Object c() {
        return this.f9354a;
    }

    public String toString() {
        return "value type:object, value:" + this.f9354a;
    }
}
