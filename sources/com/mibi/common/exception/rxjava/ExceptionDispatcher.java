package com.mibi.common.exception.rxjava;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;

public class ExceptionDispatcher {

    /* renamed from: a  reason: collision with root package name */
    private Map<Class<? extends Throwable>, ExceptionHandler> f7558a = new HashMap();

    public interface ExceptionHandler {
        Class<? extends Throwable> a();

        boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher);
    }

    public ExceptionDispatcher a(ExceptionHandler exceptionHandler) {
        Assert.assertNotNull(exceptionHandler);
        Assert.assertNotNull(exceptionHandler.a());
        this.f7558a.put(exceptionHandler.a(), exceptionHandler);
        return this;
    }

    public boolean a(Throwable th) {
        return a(th, new Bundle(), th.getClass());
    }

    public boolean a(Throwable th, Bundle bundle, Class<? extends Throwable> cls) {
        boolean isAssignableFrom = cls.isAssignableFrom(th.getClass());
        Class<? super Object> cls2 = cls;
        if (!isAssignableFrom) {
            return false;
        }
        while (cls2 != null && Throwable.class.isAssignableFrom(cls2)) {
            ExceptionHandler exceptionHandler = this.f7558a.get(cls2);
            if (exceptionHandler != null && exceptionHandler.a(th, bundle, this)) {
                return true;
            }
            cls2 = cls2.getSuperclass();
        }
        return false;
    }
}
