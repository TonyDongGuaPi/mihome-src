package com.mipay.sdk.permission;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8176a = "ReflectUtil";

    a() {
    }

    public static Object a(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object a(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0012, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r4 = r4.getSuperclass();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Field a(java.lang.Class<?> r4, java.lang.String r5) {
        /*
            r0 = 0
        L_0x0001:
            if (r4 == 0) goto L_0x0032
            java.lang.reflect.Field r1 = r4.getDeclaredField(r5)     // Catch:{ NoSuchFieldException -> 0x0014 }
            r0 = 1
            r1.setAccessible(r0)     // Catch:{ NoSuchFieldException -> 0x0010, Exception -> 0x000d }
            r0 = r1
            goto L_0x0032
        L_0x000d:
            r4 = move-exception
            r0 = r1
            goto L_0x0019
        L_0x0010:
            r0 = r1
            goto L_0x0014
        L_0x0012:
            r4 = move-exception
            goto L_0x0019
        L_0x0014:
            java.lang.Class r4 = r4.getSuperclass()     // Catch:{ Exception -> 0x0012 }
            goto L_0x0001
        L_0x0019:
            java.lang.String r1 = "ReflectUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "no such field: "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            android.util.Log.d(r1, r5)
            r4.printStackTrace()
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mipay.sdk.permission.a.a(java.lang.Class, java.lang.String):java.lang.reflect.Field");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0012, code lost:
        r3 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3 = r3.getSuperclass();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Method a(java.lang.Class<?> r3, java.lang.String r4, java.lang.Class<?>... r5) {
        /*
            r0 = 0
        L_0x0001:
            if (r3 == 0) goto L_0x0032
            java.lang.reflect.Method r1 = r3.getDeclaredMethod(r4, r5)     // Catch:{ NoSuchMethodException -> 0x0014 }
            r0 = 1
            r1.setAccessible(r0)     // Catch:{ NoSuchMethodException -> 0x0010, Exception -> 0x000d }
            r0 = r1
            goto L_0x0032
        L_0x000d:
            r3 = move-exception
            r0 = r1
            goto L_0x0019
        L_0x0010:
            r0 = r1
            goto L_0x0014
        L_0x0012:
            r3 = move-exception
            goto L_0x0019
        L_0x0014:
            java.lang.Class r3 = r3.getSuperclass()     // Catch:{ Exception -> 0x0012 }
            goto L_0x0001
        L_0x0019:
            java.lang.String r5 = "ReflectUtil"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "no such method: "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            android.util.Log.d(r5, r4)
            r3.printStackTrace()
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mipay.sdk.permission.a.a(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }
}
