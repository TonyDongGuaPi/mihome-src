package com.ximalaya.ting.android.opensdk.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodUtil {
    public static Object a(Object obj, String str, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class[] a2 = BaseUtil.a(clsArr);
        Object[] a3 = BaseUtil.a(objArr);
        Method method = obj.getClass().getMethod(str, a2);
        if (method != null) {
            return method.invoke(obj, a3);
        }
        throw new NoSuchMethodException("No such accessible method: " + str + "() on object: " + obj.getClass().getName());
    }

    public static Object a(Class cls, String str, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (cls == null) {
            return null;
        }
        Class[] a2 = BaseUtil.a(clsArr);
        Object[] a3 = BaseUtil.a(objArr);
        Method method = cls.getMethod(str, a2);
        if (method != null) {
            return method.invoke((Object) null, a3);
        }
        throw new NoSuchMethodException("No such accessible method: " + str + "() on object: " + cls.getName());
    }
}
