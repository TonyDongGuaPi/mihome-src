package com.tsmclient.smartcard;

import android.util.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReflectUtil {
    private static final String TAG = "ReflectUtil";

    private ReflectUtil() {
    }

    public static Object callObjectMethod(Object obj, String str, Class<?>[] clsArr, Object... objArr) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(obj, objArr);
    }

    public static Class<?> getClassFromName(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "getClassFromName:" + str, e);
            return null;
        }
    }

    public static Object newInstance(String str, Class<?>[] clsArr, Object... objArr) {
        Class<?> classFromName = getClassFromName(str);
        if (classFromName != null) {
            return newInstance(classFromName, clsArr, objArr);
        }
        return null;
    }

    public static Object newInstance(Class<?> cls, Class<?>[] clsArr, Object... objArr) {
        try {
            return cls.getConstructor(clsArr).newInstance(objArr);
        } catch (Exception e) {
            Log.e(TAG, "newInstance failed", e);
            return null;
        }
    }

    public static Object proxyCallBack(ClassLoader classLoader, String str, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(classLoader, new Class[]{getClassFromName(str)}, invocationHandler);
    }
}
