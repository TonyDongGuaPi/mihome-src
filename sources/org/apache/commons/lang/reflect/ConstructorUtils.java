package org.apache.commons.lang.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;

public class ConstructorUtils {
    public static Object a(Class cls, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return a(cls, new Object[]{obj});
    }

    public static Object a(Class cls, Object[] objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return a(cls, objArr, clsArr);
    }

    public static Object a(Class cls, Object[] objArr, Class[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (clsArr == null) {
            clsArr = ArrayUtils.b;
        }
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        Constructor b = b(cls, clsArr);
        if (b != null) {
            return b.newInstance(objArr);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("No such accessible constructor on object: ");
        stringBuffer.append(cls.getName());
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    public static Object b(Class cls, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return b(cls, new Object[]{obj});
    }

    public static Object b(Class cls, Object[] objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return b(cls, objArr, clsArr);
    }

    public static Object b(Class cls, Object[] objArr, Class[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (objArr == null) {
            objArr = ArrayUtils.f3354a;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.b;
        }
        Constructor a2 = a(cls, clsArr);
        if (a2 != null) {
            return a2.newInstance(objArr);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("No such accessible constructor on object: ");
        stringBuffer.append(cls.getName());
        throw new NoSuchMethodException(stringBuffer.toString());
    }

    public static Constructor a(Class cls, Class cls2) {
        return a(cls, new Class[]{cls2});
    }

    public static Constructor a(Class cls, Class[] clsArr) {
        try {
            return a(cls.getConstructor(clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Constructor a(Constructor constructor) {
        if (!MemberUtils.a((Member) constructor) || !Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) {
            return null;
        }
        return constructor;
    }

    public static Constructor b(Class cls, Class[] clsArr) {
        Constructor a2;
        try {
            Constructor constructor = cls.getConstructor(clsArr);
            MemberUtils.a((AccessibleObject) constructor);
            return constructor;
        } catch (NoSuchMethodException unused) {
            Constructor constructor2 = null;
            Constructor[] constructors = cls.getConstructors();
            for (int i = 0; i < constructors.length; i++) {
                if (ClassUtils.a(clsArr, constructors[i].getParameterTypes(), true) && (a2 = a(constructors[i])) != null) {
                    MemberUtils.a((AccessibleObject) a2);
                    if (constructor2 == null || MemberUtils.a(a2.getParameterTypes(), constructor2.getParameterTypes(), clsArr) < 0) {
                        constructor2 = a2;
                    }
                }
            }
            return constructor2;
        }
    }
}
