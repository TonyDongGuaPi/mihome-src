package com.xiaomi.miot.support.monitor.core.activity;

import android.app.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InstrumentationHooker {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f11458a = false;

    public static void a() {
        try {
            c();
            f11458a = true;
        } catch (Exception unused) {
        }
    }

    public static boolean b() {
        return f11458a;
    }

    private static void c() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Class<?> cls = Class.forName("android.app.ActivityThread");
        Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
        boolean isAccessible = declaredMethod.isAccessible();
        if (!isAccessible) {
            declaredMethod.setAccessible(true);
        }
        Object invoke = declaredMethod.invoke((Object) null, new Object[0]);
        if (!isAccessible) {
            declaredMethod.setAccessible(isAccessible);
        }
        Field declaredField = cls.getDeclaredField("mInstrumentation");
        boolean isAccessible2 = declaredField.isAccessible();
        if (!isAccessible2) {
            declaredField.setAccessible(true);
        }
        declaredField.set(invoke, new ApmInstrumentation((Instrumentation) declaredField.get(invoke)));
        if (!isAccessible2) {
            declaredField.setAccessible(isAccessible2);
        }
    }
}
