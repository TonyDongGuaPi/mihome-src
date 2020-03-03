package com.imi.fastjson.parser;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Type;

public class ParseContext {

    /* renamed from: a  reason: collision with root package name */
    private Object f6090a;
    private final ParseContext b;
    private final Object c;
    private Type d;

    public ParseContext(ParseContext parseContext, Object obj, Object obj2) {
        this.b = parseContext;
        this.f6090a = obj;
        this.c = obj2;
    }

    public Type a() {
        return this.d;
    }

    public void a(Type type) {
        this.d = type;
    }

    public Object b() {
        return this.f6090a;
    }

    public void a(Object obj) {
        this.f6090a = obj;
    }

    public ParseContext c() {
        return this.b;
    }

    public String d() {
        if (this.b == null) {
            return Operators.DOLLAR_STR;
        }
        if (this.c instanceof Integer) {
            return this.b.d() + Operators.ARRAY_START_STR + this.c + Operators.ARRAY_END_STR;
        }
        return this.b.d() + "." + this.c;
    }

    public String toString() {
        return d();
    }
}
