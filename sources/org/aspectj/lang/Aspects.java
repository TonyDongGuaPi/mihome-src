package org.aspectj.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Aspects {

    /* renamed from: a  reason: collision with root package name */
    private static final Class[] f3453a = new Class[0];
    private static final Class[] b = {Object.class};
    private static final Class[] c = {Class.class};
    private static final Object[] d = new Object[0];
    private static final String e = "aspectOf";
    private static final String f = "hasAspect";

    public static <T> T a(Class<T> cls) throws NoAspectBoundException {
        try {
            return c(cls).invoke((Object) null, d);
        } catch (InvocationTargetException e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        } catch (Exception e3) {
            throw new NoAspectBoundException(cls.getName(), e3);
        }
    }

    public static <T> T a(Class<T> cls, Object obj) throws NoAspectBoundException {
        try {
            return d(cls).invoke((Object) null, new Object[]{obj});
        } catch (InvocationTargetException e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        } catch (Exception e3) {
            throw new NoAspectBoundException(cls.getName(), e3);
        }
    }

    public static <T> T a(Class<T> cls, Class<?> cls2) throws NoAspectBoundException {
        try {
            return e(cls).invoke((Object) null, new Object[]{cls2});
        } catch (InvocationTargetException e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        } catch (Exception e3) {
            throw new NoAspectBoundException(cls.getName(), e3);
        }
    }

    public static boolean b(Class<?> cls) throws NoAspectBoundException {
        try {
            return ((Boolean) f(cls).invoke((Object) null, d)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Class<?> cls, Object obj) throws NoAspectBoundException {
        try {
            return ((Boolean) g(cls).invoke((Object) null, new Object[]{obj})).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Class<?> cls, Class<?> cls2) throws NoAspectBoundException {
        try {
            return ((Boolean) h(cls).invoke((Object) null, new Object[]{cls2})).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    private static Method c(Class<?> cls) throws NoSuchMethodException {
        return a(cls.getDeclaredMethod(e, f3453a), cls);
    }

    private static Method d(Class<?> cls) throws NoSuchMethodException {
        return a(cls.getDeclaredMethod(e, b), cls);
    }

    private static Method e(Class<?> cls) throws NoSuchMethodException {
        return a(cls.getDeclaredMethod(e, c), cls);
    }

    private static Method a(Method method, Class<?> cls) throws NoSuchMethodException {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        throw new NoSuchMethodException(cls.getName() + ".aspectOf(..) is not accessible public static");
    }

    private static Method f(Class cls) throws NoSuchMethodException {
        return b(cls.getDeclaredMethod(f, f3453a), cls);
    }

    private static Method g(Class cls) throws NoSuchMethodException {
        return b(cls.getDeclaredMethod(f, b), cls);
    }

    private static Method h(Class cls) throws NoSuchMethodException {
        return b(cls.getDeclaredMethod(f, c), cls);
    }

    private static Method b(Method method, Class cls) throws NoSuchMethodException {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        throw new NoSuchMethodException(cls.getName() + ".hasAspect(..) is not accessible public static");
    }
}
