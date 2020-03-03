package com.xiaomi.push;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ba {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f12642a = new HashMap();

    public static class a<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Class<? extends T> f12643a;
        public final T b;
    }

    static {
        f12642a.put(Boolean.class, Boolean.TYPE);
        f12642a.put(Byte.class, Byte.TYPE);
        f12642a.put(Character.class, Character.TYPE);
        f12642a.put(Short.class, Short.TYPE);
        f12642a.put(Integer.class, Integer.TYPE);
        f12642a.put(Float.class, Float.TYPE);
        f12642a.put(Long.class, Long.TYPE);
        f12642a.put(Double.class, Double.TYPE);
        f12642a.put(Boolean.TYPE, Boolean.TYPE);
        f12642a.put(Byte.TYPE, Byte.TYPE);
        f12642a.put(Character.TYPE, Character.TYPE);
        f12642a.put(Short.TYPE, Short.TYPE);
        f12642a.put(Integer.TYPE, Integer.TYPE);
        f12642a.put(Float.TYPE, Float.TYPE);
        f12642a.put(Long.TYPE, Long.TYPE);
        f12642a.put(Double.TYPE, Double.TYPE);
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0015 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T a(java.lang.Class<? extends java.lang.Object> r3, java.lang.Object r4, java.lang.String r5) {
        /*
            r0 = 0
        L_0x0001:
            r1 = 1
            if (r0 != 0) goto L_0x001b
            java.lang.reflect.Field r2 = r3.getDeclaredField(r5)     // Catch:{ NoSuchFieldException -> 0x000e }
            r2.setAccessible(r1)     // Catch:{ NoSuchFieldException -> 0x000d }
            r0 = r2
            goto L_0x0012
        L_0x000d:
            r0 = r2
        L_0x000e:
            java.lang.Class r3 = r3.getSuperclass()
        L_0x0012:
            if (r3 == 0) goto L_0x0015
            goto L_0x0001
        L_0x0015:
            java.lang.NoSuchFieldException r3 = new java.lang.NoSuchFieldException
            r3.<init>()
            throw r3
        L_0x001b:
            r0.setAccessible(r1)
            java.lang.Object r3 = r0.get(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ba.a(java.lang.Class, java.lang.Object, java.lang.String):java.lang.Object");
    }

    public static <T> T a(Class<? extends Object> cls, String str) {
        try {
            return a(cls, (Object) null, str);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> T a(Class<?> cls, String str, Object... objArr) {
        return a(cls, str, (Class<?>[]) a(objArr)).invoke((Object) null, b(objArr));
    }

    public static <T> T a(Object obj, String str) {
        try {
            return a((Class<? extends Object>) obj.getClass(), obj, str);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> T a(Object obj, String str, Object... objArr) {
        try {
            return b(obj, str, objArr);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call Method '" + str + "' in " + obj, e);
            return null;
        }
    }

    public static <T> T a(String str, String str2) {
        try {
            return a((Class<? extends Object>) t.a((Context) null, str), (Object) null, str2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (ClassNotFoundException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static <T> T a(String str, String str2, Object... objArr) {
        try {
            return a(t.a((Context) null, str), str2, objArr);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call Method '" + str2 + "' in " + str, e);
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Method a2 = a(cls.getDeclaredMethods(), str, clsArr);
        if (a2 != null) {
            a2.setAccessible(true);
            return a2;
        } else if (cls.getSuperclass() != null) {
            return a((Class<?>) cls.getSuperclass(), str, clsArr);
        } else {
            throw new NoSuchMethodException();
        }
    }

    private static Method a(Method[] methodArr, String str, Class<?>[] clsArr) {
        if (str != null) {
            for (Method method : methodArr) {
                if (method.getName().equals(str) && a((Class<?>[]) method.getParameterTypes(), clsArr)) {
                    return method;
                }
            }
            return null;
        }
        throw new NullPointerException("Method name must not be null.");
    }

    private static boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr == null) {
            return clsArr2 == null || clsArr2.length == 0;
        }
        if (clsArr2 == null) {
            return clsArr.length == 0;
        }
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr.length; i++) {
            if (clsArr2[i] != null && !clsArr[i].isAssignableFrom(clsArr2[i]) && (!f12642a.containsKey(clsArr[i]) || !f12642a.get(clsArr[i]).equals(f12642a.get(clsArr2[i])))) {
                return false;
            }
        }
        return true;
    }

    private static Class<?>[] a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            a aVar = objArr[i];
            if (aVar == null || !(aVar instanceof a)) {
                clsArr[i] = aVar == null ? null : aVar.getClass();
            } else {
                clsArr[i] = aVar.f12643a;
            }
        }
        return clsArr;
    }

    public static <T> T b(Object obj, String str, Object... objArr) {
        return a(obj.getClass(), str, (Class<?>[]) a(objArr)).invoke(obj, b(objArr));
    }

    private static Object[] b(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            a aVar = objArr[i];
            if (aVar == null || !(aVar instanceof a)) {
                objArr2[i] = aVar;
            } else {
                objArr2[i] = aVar.b;
            }
        }
        return objArr2;
    }
}
