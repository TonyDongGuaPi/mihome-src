package com.tmall.wireless.vaf.expr.engine.data;

import android.util.Log;

public class IntValue extends Value {
    private static final String c = "IntValue_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    public int f9353a;

    public IntValue(int i) {
        this.f9353a = i;
    }

    public void a(Value value) {
        if (value != null) {
            this.f9353a = ((IntValue) value).f9353a;
        } else {
            Log.e(c, "value is null");
        }
    }

    /* renamed from: a */
    public Value clone() {
        return b.a(this.f9353a);
    }

    public Class<?> b() {
        return Integer.TYPE;
    }

    public Object c() {
        return Integer.valueOf(this.f9353a);
    }

    public String toString() {
        return String.format("value type:int, value:%d", new Object[]{Integer.valueOf(this.f9353a)});
    }
}
