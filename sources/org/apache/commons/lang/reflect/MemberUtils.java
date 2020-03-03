package org.apache.commons.lang.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.SystemUtils;

abstract class MemberUtils {

    /* renamed from: a  reason: collision with root package name */
    static Class f3397a = null;
    private static final int b = 7;
    private static final Method c;
    private static final Class[] d = {Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};

    static boolean a(int i) {
        return (i & 7) == 0;
    }

    MemberUtils() {
    }

    static {
        Method method;
        Class cls;
        if (SystemUtils.a(1.5f)) {
            try {
                if (f3397a == null) {
                    cls = a("java.lang.reflect.Member");
                    f3397a = cls;
                } else {
                    cls = f3397a;
                }
                method = cls.getMethod("isSynthetic", ArrayUtils.b);
            } catch (Exception unused) {
            }
            c = method;
        }
        method = null;
        c = method;
    }

    static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static void a(AccessibleObject accessibleObject) {
        if (accessibleObject != null && !accessibleObject.isAccessible()) {
            Member member = (Member) accessibleObject;
            if (Modifier.isPublic(member.getModifiers()) && a(member.getDeclaringClass().getModifiers())) {
                try {
                    accessibleObject.setAccessible(true);
                } catch (SecurityException unused) {
                }
            }
        }
    }

    static boolean a(Member member) {
        return member != null && Modifier.isPublic(member.getModifiers()) && !b(member);
    }

    static boolean b(Member member) {
        if (c == null) {
            return false;
        }
        try {
            return ((Boolean) c.invoke(member, (Object[]) null)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    static int a(Class[] clsArr, Class[] clsArr2, Class[] clsArr3) {
        float a2 = a(clsArr3, clsArr);
        float a3 = a(clsArr3, clsArr2);
        if (a2 < a3) {
            return -1;
        }
        return a3 < a2 ? 1 : 0;
    }

    private static float a(Class[] clsArr, Class[] clsArr2) {
        float f = 0.0f;
        for (int i = 0; i < clsArr.length; i++) {
            f += a(clsArr[i], clsArr2[i]);
        }
        return f;
    }

    private static float a(Class cls, Class cls2) {
        if (cls2.isPrimitive()) {
            return b(cls, cls2);
        }
        float f = 0.0f;
        while (true) {
            if (cls != null && !cls2.equals(cls)) {
                if (cls2.isInterface() && ClassUtils.a(cls, cls2)) {
                    f += 0.25f;
                    break;
                }
                f += 1.0f;
                cls = cls.getSuperclass();
            } else {
                break;
            }
        }
        return cls == null ? f + 1.5f : f;
    }

    private static float b(Class cls, Class cls2) {
        float f;
        if (!cls.isPrimitive()) {
            cls = ClassUtils.f(cls);
            f = 0.1f;
        } else {
            f = 0.0f;
        }
        int i = 0;
        while (cls != cls2 && i < d.length) {
            if (cls == d[i]) {
                f += 0.1f;
                if (i < d.length - 1) {
                    cls = d[i + 1];
                }
            }
            i++;
        }
        return f;
    }
}
