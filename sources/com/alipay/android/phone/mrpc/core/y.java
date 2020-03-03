package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class y implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    protected g f846a;
    protected Class<?> b;
    protected z c;

    public y(g gVar, Class<?> cls, z zVar) {
        this.f846a = gVar;
        this.b = cls;
        this.c = zVar;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        z zVar = this.c;
        Class<?> cls = this.b;
        return zVar.a(method, objArr);
    }
}
