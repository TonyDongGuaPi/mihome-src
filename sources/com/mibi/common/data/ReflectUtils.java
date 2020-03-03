package com.mibi.common.data;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7538a = "ReflectUtils";

    private ReflectUtils() {
    }

    public static Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            Log.e(f7538a, "getClassFromName:" + str, e);
            return null;
        }
    }

    public static Field a(Class<?> cls, String str) {
        Field field;
        try {
            field = cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Log.e(f7538a, "getFiled:" + str, e);
            field = null;
        }
        if (field != null) {
            field.setAccessible(true);
        }
        return field;
    }

    public static <T> T a(Class<?> cls, Object obj, String str) {
        Field a2 = a(cls, str);
        if (a2 == null) {
            return null;
        }
        try {
            return a2.get(obj);
        } catch (Exception e) {
            Log.e(f7538a, "getMember:" + str, e);
            return null;
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Method method;
        try {
            method = cls.getDeclaredMethod(str, clsArr);
        } catch (Exception e) {
            Log.e(f7538a, "getMethod:" + str, e);
            method = null;
        }
        if (method != null) {
            method.setAccessible(true);
        }
        return method;
    }

    public static boolean b(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            cls.getDeclaredMethod(str, clsArr);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static <T> T a(Method method, Object obj, Object... objArr) {
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            Log.e(f7538a, "invoke error", e);
            return null;
        }
    }
}
