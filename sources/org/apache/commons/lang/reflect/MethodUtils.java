package org.apache.commons.lang.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;

public class MethodUtils {
    public static Object a(Object obj, String str, Object obj2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return a(obj, str, new Object[]{obj2});
    }

    public static Object a(Object obj, String str, Object[] objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return a(obj, str, objArr, clsArr);
    }

    public static Object a(Object obj, String str, Object[] objArr, Class[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (clsArr == null) {
            clsArr = ArrayUtils.b;
        }
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        Method b = b((Class) obj.getClass(), str, clsArr);
        if (b != null) {
            return b.invoke(obj, objArr);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("No such accessible method: ");
        stringBuffer.append(str);
        stringBuffer.append("() on object: ");
        stringBuffer.append(obj.getClass().getName());
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    public static Object b(Object obj, String str, Object obj2) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return b(obj, str, new Object[]{obj2});
    }

    public static Object b(Object obj, String str, Object[] objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return b(obj, str, objArr, clsArr);
    }

    public static Object b(Object obj, String str, Object[] objArr, Class[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.b;
        }
        Method a2 = a((Class) obj.getClass(), str, clsArr);
        if (a2 != null) {
            return a2.invoke(obj, objArr);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("No such accessible method: ");
        stringBuffer.append(str);
        stringBuffer.append("() on object: ");
        stringBuffer.append(obj.getClass().getName());
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    public static Object a(Class cls, String str, Object[] objArr, Class[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.b;
        }
        Method a2 = a(cls, str, clsArr);
        if (a2 != null) {
            return a2.invoke((Object) null, objArr);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("No such accessible method: ");
        stringBuffer.append(str);
        stringBuffer.append("() on class: ");
        stringBuffer.append(cls.getName());
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    public static Object a(Class cls, String str, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return a(cls, str, new Object[]{obj});
    }

    public static Object a(Class cls, String str, Object[] objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return b(cls, str, objArr, clsArr);
    }

    public static Object b(Class cls, String str, Object[] objArr, Class[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (clsArr == null) {
            clsArr = ArrayUtils.b;
        }
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        Method b = b(cls, str, clsArr);
        if (b != null) {
            return b.invoke((Object) null, objArr);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("No such accessible method: ");
        stringBuffer.append(str);
        stringBuffer.append("() on class: ");
        stringBuffer.append(cls.getName());
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    public static Object b(Class cls, String str, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return b(cls, str, new Object[]{obj});
    }

    public static Object b(Class cls, String str, Object[] objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return a(cls, str, objArr, clsArr);
    }

    public static Method a(Class cls, String str, Class cls2) {
        return a(cls, str, new Class[]{cls2});
    }

    public static Method a(Class cls, String str, Class[] clsArr) {
        try {
            return a(cls.getMethod(str, clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Method a(Method method) {
        if (!MemberUtils.a((Member) method)) {
            return null;
        }
        Class<?> declaringClass = method.getDeclaringClass();
        if (Modifier.isPublic(declaringClass.getModifiers())) {
            return method;
        }
        String name = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        Method d = d(declaringClass, name, parameterTypes);
        return d == null ? c(declaringClass, name, parameterTypes) : d;
    }

    private static Method c(Class cls, String str, Class[] clsArr) {
        Class superclass = cls.getSuperclass();
        while (superclass != null) {
            if (Modifier.isPublic(superclass.getModifiers())) {
                try {
                    return superclass.getMethod(str, clsArr);
                } catch (NoSuchMethodException unused) {
                    return null;
                }
            } else {
                superclass = superclass.getSuperclass();
            }
        }
        return null;
    }

    private static Method d(Class cls, String str, Class[] clsArr) {
        Method method = null;
        while (cls != null) {
            Class[] interfaces = cls.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (Modifier.isPublic(interfaces[i].getModifiers())) {
                    try {
                        method = interfaces[i].getDeclaredMethod(str, clsArr);
                    } catch (NoSuchMethodException unused) {
                    }
                    if (method != null || (method = d(interfaces[i], str, clsArr)) != null) {
                        break;
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return method;
    }

    public static Method b(Class cls, String str, Class[] clsArr) {
        Method a2;
        try {
            Method method = cls.getMethod(str, clsArr);
            MemberUtils.a((AccessibleObject) method);
            return method;
        } catch (NoSuchMethodException unused) {
            Method method2 = null;
            Method[] methods = cls.getMethods();
            int length = methods.length;
            for (int i = 0; i < length; i++) {
                if (methods[i].getName().equals(str) && ClassUtils.a(clsArr, methods[i].getParameterTypes(), true) && (a2 = a(methods[i])) != null && (method2 == null || MemberUtils.a(a2.getParameterTypes(), method2.getParameterTypes(), clsArr) < 0)) {
                    method2 = a2;
                }
            }
            if (method2 != null) {
                MemberUtils.a((AccessibleObject) method2);
            }
            return method2;
        }
    }
}
