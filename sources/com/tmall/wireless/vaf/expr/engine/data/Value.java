package com.tmall.wireless.vaf.expr.engine.data;

public abstract class Value {
    protected static ValueCache b = ValueCache.a();

    /* renamed from: a */
    public abstract Value clone();

    public abstract void a(Value value);

    public abstract Class<?> b();

    public abstract Object c();
}
