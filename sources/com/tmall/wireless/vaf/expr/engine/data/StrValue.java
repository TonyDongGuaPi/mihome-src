package com.tmall.wireless.vaf.expr.engine.data;

import android.util.Log;

public class StrValue extends Value {
    private static final String c = "StrValue_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    public String f9355a;

    public StrValue(String str) {
        this.f9355a = str;
    }

    public void a(Value value) {
        if (value != null) {
            this.f9355a = new String(((StrValue) value).f9355a);
        } else {
            Log.e(c, "value is null");
        }
    }

    /* renamed from: a */
    public Value clone() {
        return b.a(this.f9355a);
    }

    public Class<?> b() {
        return String.class;
    }

    public Object c() {
        return this.f9355a;
    }

    public String toString() {
        return "value type:string, value:" + this.f9355a;
    }
}
