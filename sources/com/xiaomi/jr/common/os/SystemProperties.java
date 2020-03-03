package com.xiaomi.jr.common.os;

import java.lang.reflect.InvocationTargetException;

public class SystemProperties {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10360a = 60;
    public static final int b = 91;

    protected SystemProperties() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    public static String a(String str) {
        if (str.length() <= 60) {
            return b(str);
        }
        throw new IllegalArgumentException("key.length > 60");
    }

    public static String a(String str, String str2) {
        if (str.length() <= 60) {
            String b2 = b(str);
            return (b2 == null || b2.length() == 0) ? str2 : b2;
        }
        throw new IllegalArgumentException("key.length > 60");
    }

    public static int a(String str, int i) {
        if (str.length() <= 60) {
            return c(str, i);
        }
        throw new IllegalArgumentException("key.length > 60");
    }

    public static long a(String str, long j) {
        if (str.length() <= 60) {
            return c(str, j);
        }
        throw new IllegalArgumentException("key.length > 60");
    }

    public static boolean a(String str, boolean z) {
        if (str.length() <= 60) {
            return c(str, z);
        }
        throw new IllegalArgumentException("key.length > 60");
    }

    public static void b(String str, String str2) {
        if (str.length() > 60) {
            throw new IllegalArgumentException("key.length > 60");
        } else if (str2 == null || str2.length() <= 91) {
            c(str, str2);
        } else {
            throw new IllegalArgumentException("val.length > 91");
        }
    }

    public static void b(String str, int i) {
        b(str, Integer.toString(i));
    }

    public static void b(String str, long j) {
        b(str, Long.toString(j));
    }

    public static void b(String str, boolean z) {
        b(str, Boolean.toString(z));
    }

    protected static String b(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static int c(String str, int i) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Integer) cls.getDeclaredMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke(cls, new Object[]{str, Integer.valueOf(i)})).intValue();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return i;
        }
    }

    protected static long c(String str, long j) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Long) cls.getDeclaredMethod("getLong", new Class[]{String.class, Long.TYPE}).invoke(cls, new Object[]{str, Long.valueOf(j)})).longValue();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return j;
        }
    }

    protected static boolean c(String str, boolean z) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Boolean) cls.getDeclaredMethod("getBoolean", new Class[]{String.class, Boolean.TYPE}).invoke(cls, new Object[]{str, Boolean.valueOf(z)})).booleanValue();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static void c(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            cls.getDeclaredMethod("set", new Class[]{String.class, String.class}).invoke(cls, new Object[]{str, str2});
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
