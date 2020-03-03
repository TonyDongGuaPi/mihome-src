package com.transitionseverywhere.utils;

import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9502a = "ReflectionUtils";
    private static final Object[] b = new Object[0];
    private static final Object[] c = new Object[1];
    private static final Object[] d = new Object[2];
    private static final Object[] e = new Object[3];
    private static final Object[] f = new Object[4];

    private ReflectionUtils() {
    }

    public static Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        if (cls == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException | SecurityException unused) {
            return null;
        }
    }

    public static Method b(Class<?> cls, String str, Class<?>... clsArr) {
        if (cls == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException | SecurityException unused) {
            return null;
        }
    }

    public static Object a(Object obj, Object obj2, Method method, Object... objArr) {
        if (method == null) {
            return obj2;
        }
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e2) {
            Log.e(f9502a, "Exception in invoke", e2);
            return obj2;
        }
    }

    public static Field a(Class<?> cls, String str) {
        if (cls == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (SecurityException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static void a(Object obj, Field field, Object obj2) {
        if (field != null) {
            try {
                field.set(obj, obj2);
            } catch (Exception e2) {
                Log.e(f9502a, "Exception in setFieldValue", e2);
            }
        }
    }

    public static Object a(Object obj, Object obj2, Field field) {
        if (field == null) {
            return obj2;
        }
        try {
            return field.get(obj);
        } catch (Exception e2) {
            Log.e(f9502a, "Exception in getFieldValue", e2);
            return obj2;
        }
    }

    public static Object a(Object obj, Object obj2, Method method) {
        return a(obj, obj2, method, b);
    }

    public static Object a(Object obj, Object obj2, Method method, Object obj3) {
        c[0] = obj3;
        Object a2 = a(obj, obj2, method, c);
        c[0] = null;
        return a2;
    }

    public static Object a(Object obj, Object obj2, Method method, Object obj3, Object obj4) {
        d[0] = obj3;
        d[1] = obj4;
        Object a2 = a(obj, obj2, method, d);
        d[0] = null;
        d[1] = null;
        return a2;
    }

    public static Object a(Object obj, Object obj2, Method method, Object obj3, Object obj4, Object obj5) {
        e[0] = obj3;
        e[1] = obj4;
        e[2] = obj5;
        Object a2 = a(obj, obj2, method, e);
        e[0] = null;
        e[1] = null;
        e[2] = null;
        return a2;
    }

    public static Object a(Object obj, Object obj2, Method method, Object obj3, Object obj4, Object obj5, Object obj6) {
        f[0] = obj3;
        f[1] = obj4;
        f[2] = obj5;
        f[3] = obj6;
        Object a2 = a(obj, obj2, method, f);
        f[0] = null;
        f[1] = null;
        f[2] = null;
        f[3] = null;
        return a2;
    }
}
