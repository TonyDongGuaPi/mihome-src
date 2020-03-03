package org.mp4parser.aspectj.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Aspects14 {

    /* renamed from: a  reason: collision with root package name */
    static Class f3755a = null;
    static Class b = null;
    private static final Class[] c = new Class[0];
    private static final Class[] d;
    private static final Class[] e;
    private static final Object[] f = new Object[0];
    private static final String g = "aspectOf";
    private static final String h = "hasAspect";

    static {
        Class cls;
        Class cls2;
        Class[] clsArr = new Class[1];
        if (f3755a == null) {
            cls = a("java.lang.Object");
            f3755a = cls;
        } else {
            cls = f3755a;
        }
        clsArr[0] = cls;
        d = clsArr;
        Class[] clsArr2 = new Class[1];
        if (b == null) {
            cls2 = a("java.lang.Class");
            b = cls2;
        } else {
            cls2 = b;
        }
        clsArr2[0] = cls2;
        e = clsArr2;
    }

    static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    public static Object a(Class cls) throws NoAspectBoundException {
        try {
            return c(cls).invoke((Object) null, f);
        } catch (InvocationTargetException e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        } catch (Exception e3) {
            throw new NoAspectBoundException(cls.getName(), e3);
        }
    }

    public static Object a(Class cls, Object obj) throws NoAspectBoundException {
        try {
            return d(cls).invoke((Object) null, new Object[]{obj});
        } catch (InvocationTargetException e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        } catch (Exception e3) {
            throw new NoAspectBoundException(cls.getName(), e3);
        }
    }

    public static Object a(Class cls, Class cls2) throws NoAspectBoundException {
        try {
            return e(cls).invoke((Object) null, new Object[]{cls2});
        } catch (InvocationTargetException e2) {
            throw new NoAspectBoundException(cls.getName(), e2);
        } catch (Exception e3) {
            throw new NoAspectBoundException(cls.getName(), e3);
        }
    }

    public static boolean b(Class cls) throws NoAspectBoundException {
        try {
            return ((Boolean) f(cls).invoke((Object) null, f)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Class cls, Object obj) throws NoAspectBoundException {
        try {
            return ((Boolean) g(cls).invoke((Object) null, new Object[]{obj})).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Class cls, Class cls2) throws NoAspectBoundException {
        try {
            return ((Boolean) h(cls).invoke((Object) null, new Object[]{cls2})).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    private static Method c(Class cls) throws NoSuchMethodException {
        return a(cls.getDeclaredMethod(g, c), cls);
    }

    private static Method d(Class cls) throws NoSuchMethodException {
        return a(cls.getDeclaredMethod(g, d), cls);
    }

    private static Method e(Class cls) throws NoSuchMethodException {
        return a(cls.getDeclaredMethod(g, e), cls);
    }

    private static Method a(Method method, Class cls) throws NoSuchMethodException {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cls.getName());
        stringBuffer.append(".aspectOf(..) is not accessible public static");
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    private static Method f(Class cls) throws NoSuchMethodException {
        return b(cls.getDeclaredMethod(h, c), cls);
    }

    private static Method g(Class cls) throws NoSuchMethodException {
        return b(cls.getDeclaredMethod(h, d), cls);
    }

    private static Method h(Class cls) throws NoSuchMethodException {
        return b(cls.getDeclaredMethod(h, e), cls);
    }

    private static Method b(Method method, Class cls) throws NoSuchMethodException {
        method.setAccessible(true);
        if (method.isAccessible() && Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cls.getName());
        stringBuffer.append(".hasAspect(..) is not accessible public static");
        throw new NoSuchMethodException(stringBuffer.toString());
    }
}
