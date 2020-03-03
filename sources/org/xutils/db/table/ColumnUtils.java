package org.xutils.db.table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import org.xutils.common.util.LogUtil;
import org.xutils.db.converter.ColumnConverterFactory;

public final class ColumnUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final HashSet<Class<?>> f4247a = new HashSet<>(2);
    private static final HashSet<Class<?>> b = new HashSet<>(2);
    private static final HashSet<Class<?>> c = new HashSet<>(4);

    private ColumnUtils() {
    }

    static {
        f4247a.add(Boolean.TYPE);
        f4247a.add(Boolean.class);
        b.add(Integer.TYPE);
        b.add(Integer.class);
        c.addAll(b);
        c.add(Long.TYPE);
        c.add(Long.class);
    }

    public static boolean a(Class<?> cls) {
        return c.contains(cls);
    }

    public static boolean b(Class<?> cls) {
        return b.contains(cls);
    }

    public static boolean c(Class<?> cls) {
        return f4247a.contains(cls);
    }

    public static Object a(Object obj) {
        return obj != null ? ColumnConverterFactory.a(obj.getClass()).a(obj) : obj;
    }

    static Method a(Class<?> cls, Field field) {
        Method method = null;
        if (Object.class.equals(cls)) {
            return null;
        }
        String name = field.getName();
        if (c(field.getType())) {
            method = a(cls, name);
        }
        if (method == null) {
            String str = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                method = cls.getDeclaredMethod(str, new Class[0]);
            } catch (NoSuchMethodException unused) {
                LogUtil.a(cls.getName() + "#" + str + " not exist");
            }
        }
        return method == null ? a((Class<?>) cls.getSuperclass(), field) : method;
    }

    static Method b(Class<?> cls, Field field) {
        Method method = null;
        if (Object.class.equals(cls)) {
            return null;
        }
        String name = field.getName();
        Class<?> type = field.getType();
        if (c(type)) {
            method = a(cls, name, type);
        }
        if (method == null) {
            String str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                method = cls.getDeclaredMethod(str, new Class[]{type});
            } catch (NoSuchMethodException unused) {
                LogUtil.a(cls.getName() + "#" + str + " not exist");
            }
        }
        return method == null ? b(cls.getSuperclass(), field) : method;
    }

    private static Method a(Class<?> cls, String str) {
        if (!str.startsWith("is")) {
            str = "is" + str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        try {
            return cls.getDeclaredMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            LogUtil.a(cls.getName() + "#" + str + " not exist");
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?> cls2) {
        String str2;
        if (str.startsWith("is")) {
            str2 = "set" + str.substring(2, 3).toUpperCase() + str.substring(3);
        } else {
            str2 = "set" + str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        try {
            return cls.getDeclaredMethod(str2, new Class[]{cls2});
        } catch (NoSuchMethodException unused) {
            LogUtil.a(cls.getName() + "#" + str2 + " not exist");
            return null;
        }
    }
}
