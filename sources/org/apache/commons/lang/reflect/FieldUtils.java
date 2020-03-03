package org.apache.commons.lang.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import org.apache.commons.lang.ClassUtils;

public class FieldUtils {
    public static Field a(Class cls, String str) {
        Field a2 = a(cls, str, false);
        MemberUtils.a((AccessibleObject) a2);
        return a2;
    }

    public static Field a(Class cls, String str, boolean z) {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        } else if (str != null) {
            Class cls2 = cls;
            while (cls2 != null) {
                try {
                    Field declaredField = cls2.getDeclaredField(str);
                    if (!Modifier.isPublic(declaredField.getModifiers())) {
                        if (z) {
                            declaredField.setAccessible(true);
                        } else {
                            cls2 = cls2.getSuperclass();
                        }
                    }
                    return declaredField;
                } catch (NoSuchFieldException unused) {
                }
            }
            Field field = null;
            for (Class field2 : ClassUtils.d(cls)) {
                try {
                    Field field3 = field2.getField(str);
                    if (field == null) {
                        field = field3;
                    } else {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Reference to field ");
                        stringBuffer.append(str);
                        stringBuffer.append(" is ambiguous relative to ");
                        stringBuffer.append(cls);
                        stringBuffer.append("; a matching field exists on two or more implemented interfaces.");
                        throw new IllegalArgumentException(stringBuffer.toString());
                    }
                } catch (NoSuchFieldException unused2) {
                }
            }
            return field;
        } else {
            throw new IllegalArgumentException("The field name must not be null");
        }
    }

    public static Field b(Class cls, String str) {
        return b(cls, str, false);
    }

    public static Field b(Class cls, String str, boolean z) {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        } else if (str != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!MemberUtils.a((Member) declaredField)) {
                    if (!z) {
                        return null;
                    }
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
                return null;
            }
        } else {
            throw new IllegalArgumentException("The field name must not be null");
        }
    }

    public static Object a(Field field) throws IllegalAccessException {
        return a(field, false);
    }

    public static Object a(Field field, boolean z) throws IllegalAccessException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        } else if (Modifier.isStatic(field.getModifiers())) {
            return a(field, (Object) null, z);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The field '");
            stringBuffer.append(field.getName());
            stringBuffer.append("' is not static");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public static Object c(Class cls, String str) throws IllegalAccessException {
        return c(cls, str, false);
    }

    public static Object c(Class cls, String str, boolean z) throws IllegalAccessException {
        Field a2 = a(cls, str, z);
        if (a2 != null) {
            return a(a2, false);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Cannot locate field ");
        stringBuffer.append(str);
        stringBuffer.append(" on ");
        stringBuffer.append(cls);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static Object d(Class cls, String str) throws IllegalAccessException {
        return d(cls, str, false);
    }

    public static Object d(Class cls, String str, boolean z) throws IllegalAccessException {
        Field b = b(cls, str, z);
        if (b != null) {
            return a(b, false);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Cannot locate declared field ");
        stringBuffer.append(cls.getName());
        stringBuffer.append(".");
        stringBuffer.append(str);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static Object a(Field field, Object obj) throws IllegalAccessException {
        return a(field, obj, false);
    }

    public static Object a(Field field, Object obj, boolean z) throws IllegalAccessException {
        if (field != null) {
            if (!z || field.isAccessible()) {
                MemberUtils.a((AccessibleObject) field);
            } else {
                field.setAccessible(true);
            }
            return field.get(obj);
        }
        throw new IllegalArgumentException("The field must not be null");
    }

    public static Object a(Object obj, String str) throws IllegalAccessException {
        return a(obj, str, false);
    }

    public static Object a(Object obj, String str, boolean z) throws IllegalAccessException {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            Field a2 = a((Class) cls, str, z);
            if (a2 != null) {
                return a(a2, obj);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate field ");
            stringBuffer.append(str);
            stringBuffer.append(" on ");
            stringBuffer.append(cls);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        throw new IllegalArgumentException("target object must not be null");
    }

    public static Object b(Object obj, String str) throws IllegalAccessException {
        return b(obj, str, false);
    }

    public static Object b(Object obj, String str, boolean z) throws IllegalAccessException {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            Field b = b((Class) cls, str, z);
            if (b != null) {
                return a(b, obj);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate declared field ");
            stringBuffer.append(cls.getName());
            stringBuffer.append(".");
            stringBuffer.append(str);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        throw new IllegalArgumentException("target object must not be null");
    }

    public static void b(Field field, Object obj) throws IllegalAccessException {
        b(field, obj, false);
    }

    public static void b(Field field, Object obj, boolean z) throws IllegalAccessException {
        if (field == null) {
            throw new IllegalArgumentException("The field must not be null");
        } else if (Modifier.isStatic(field.getModifiers())) {
            a(field, (Object) null, obj, z);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The field '");
            stringBuffer.append(field.getName());
            stringBuffer.append("' is not static");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public static void a(Class cls, String str, Object obj) throws IllegalAccessException {
        a(cls, str, obj, false);
    }

    public static void a(Class cls, String str, Object obj, boolean z) throws IllegalAccessException {
        Field a2 = a(cls, str, z);
        if (a2 != null) {
            b(a2, obj);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Cannot locate field ");
        stringBuffer.append(str);
        stringBuffer.append(" on ");
        stringBuffer.append(cls);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static void b(Class cls, String str, Object obj) throws IllegalAccessException {
        b(cls, str, obj, false);
    }

    public static void b(Class cls, String str, Object obj, boolean z) throws IllegalAccessException {
        Field b = b(cls, str, z);
        if (b != null) {
            a(b, (Object) null, obj);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Cannot locate declared field ");
        stringBuffer.append(cls.getName());
        stringBuffer.append(".");
        stringBuffer.append(str);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static void a(Field field, Object obj, Object obj2) throws IllegalAccessException {
        a(field, obj, obj2, false);
    }

    public static void a(Field field, Object obj, Object obj2, boolean z) throws IllegalAccessException {
        if (field != null) {
            if (!z || field.isAccessible()) {
                MemberUtils.a((AccessibleObject) field);
            } else {
                field.setAccessible(true);
            }
            field.set(obj, obj2);
            return;
        }
        throw new IllegalArgumentException("The field must not be null");
    }

    public static void a(Object obj, String str, Object obj2) throws IllegalAccessException {
        a(obj, str, obj2, false);
    }

    public static void a(Object obj, String str, Object obj2, boolean z) throws IllegalAccessException {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            Field a2 = a((Class) cls, str, z);
            if (a2 != null) {
                a(a2, obj, obj2);
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate declared field ");
            stringBuffer.append(cls.getName());
            stringBuffer.append(".");
            stringBuffer.append(str);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        throw new IllegalArgumentException("target object must not be null");
    }

    public static void b(Object obj, String str, Object obj2) throws IllegalAccessException {
        b(obj, str, obj2, false);
    }

    public static void b(Object obj, String str, Object obj2, boolean z) throws IllegalAccessException {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            Field b = b((Class) cls, str, z);
            if (b != null) {
                a(b, obj, obj2);
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot locate declared field ");
            stringBuffer.append(cls.getName());
            stringBuffer.append(".");
            stringBuffer.append(str);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        throw new IllegalArgumentException("target object must not be null");
    }
}
