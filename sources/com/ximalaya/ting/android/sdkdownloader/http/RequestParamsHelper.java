package com.ximalaya.ting.android.sdkdownloader.http;

import android.os.Parcelable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

final class RequestParamsHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final ClassLoader f2360a = String.class.getClassLoader();

    interface ParseKVListener {
        void a(String str, Object obj);
    }

    private RequestParamsHelper() {
    }

    static void a(Object obj, Class<?> cls, ParseKVListener parseKVListener) {
        ClassLoader classLoader;
        if (obj != null && cls != null && cls != RequestParams.class && cls != Object.class && (classLoader = cls.getClassLoader()) != null && classLoader != f2360a) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (!Modifier.isTransient(field.getModifiers()) && field.getType() != Parcelable.Creator.class) {
                        field.setAccessible(true);
                        try {
                            String name = field.getName();
                            Object obj2 = field.get(obj);
                            if (obj2 != null) {
                                parseKVListener.a(name, obj2);
                            }
                        } catch (IllegalAccessException unused) {
                        }
                    }
                }
            }
            a(obj, cls.getSuperclass(), parseKVListener);
        }
    }
}
