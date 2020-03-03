package com.tmall.wireless.vaf.expr.engine.data;

import android.util.Log;

public class FloatValue extends Value {
    private static final String c = "FloatValue_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    public float f9352a;

    public FloatValue(float f) {
        this.f9352a = f;
    }

    public void a(Value value) {
        if (value != null) {
            this.f9352a = ((FloatValue) value).f9352a;
        } else {
            Log.e(c, "value is null");
        }
    }

    /* renamed from: a */
    public Value clone() {
        return b.a(this.f9352a);
    }

    public Class<?> b() {
        return Float.TYPE;
    }

    public Object c() {
        return Float.valueOf(this.f9352a);
    }

    public String toString() {
        return String.format("value type:float, value:%f", new Object[]{Float.valueOf(this.f9352a)});
    }
}
