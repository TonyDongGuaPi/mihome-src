package com.alipay.deviceid.module.x;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class bj implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    protected au f892a;
    protected Class<?> b;
    protected bk c;

    public bj(au auVar, Class<?> cls, bk bkVar) {
        this.f892a = auVar;
        this.b = cls;
        this.c = bkVar;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return this.c.a(method, objArr);
    }
}
