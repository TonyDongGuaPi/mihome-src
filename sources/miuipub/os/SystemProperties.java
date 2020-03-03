package miuipub.os;

import java.lang.reflect.InvocationTargetException;

public class SystemProperties {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2987a = 31;
    public static final int b = 91;

    protected SystemProperties() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    public static String a(String str) {
        if (str.length() <= 31) {
            return b(str);
        }
        throw new IllegalArgumentException("key.length > 31");
    }

    public static String a(String str, String str2) {
        if (str.length() <= 31) {
            String b2 = b(str);
            return (b2 == null || b2.length() == 0) ? str2 : b2;
        }
        throw new IllegalArgumentException("key.length > 31");
    }

    public static int a(String str, int i) {
        if (str.length() <= 31) {
            return c(str, i);
        }
        throw new IllegalArgumentException("key.length > 31");
    }

    public static long a(String str, long j) {
        if (str.length() <= 31) {
            return c(str, j);
        }
        throw new IllegalArgumentException("key.length > 31");
    }

    public static boolean a(String str, boolean z) {
        if (str.length() <= 31) {
            return c(str, z);
        }
        throw new IllegalArgumentException("key.length > 31");
    }

    public static void b(String str, String str2) {
        if (str.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    protected static int c(String str, int i) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Integer) cls.getDeclaredMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke(cls, new Object[]{str, Integer.valueOf(i)})).intValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return i;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return i;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return i;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return i;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return i;
        }
    }

    protected static long c(String str, long j) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Long) cls.getDeclaredMethod("getLong", new Class[]{String.class, Long.TYPE}).invoke(cls, new Object[]{str, Long.valueOf(j)})).longValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return j;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return j;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return j;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return j;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return j;
        }
    }

    protected static boolean c(String str, boolean z) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Boolean) cls.getDeclaredMethod("getBoolean", new Class[]{String.class, Boolean.TYPE}).invoke(cls, new Object[]{str, Boolean.valueOf(z)})).booleanValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return z;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return z;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return z;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return z;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return z;
        }
    }

    public static void c(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            cls.getDeclaredMethod("set", new Class[]{String.class, String.class}).invoke(cls, new Object[]{str, str2});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
    }
}
