package com.imi.fastjson.serializer;

import com.taobao.weex.el.parse.Operators;

public class SerialContext {

    /* renamed from: a  reason: collision with root package name */
    private final SerialContext f6177a;
    private final Object b;
    private final Object c;

    public SerialContext(SerialContext serialContext, Object obj, Object obj2) {
        this.f6177a = serialContext;
        this.b = obj;
        this.c = obj2;
    }

    public SerialContext a() {
        return this.f6177a;
    }

    public Object b() {
        return this.b;
    }

    public Object c() {
        return this.c;
    }

    public String d() {
        if (this.f6177a == null) {
            return Operators.DOLLAR_STR;
        }
        if (this.c instanceof Integer) {
            return this.f6177a.d() + Operators.ARRAY_START_STR + this.c + Operators.ARRAY_END_STR;
        }
        return this.f6177a.d() + "." + this.c;
    }

    public String toString() {
        return d();
    }
}
