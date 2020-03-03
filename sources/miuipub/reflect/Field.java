package miuipub.reflect;

public class Field {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3009a = "Z";
    public static final String b = "B";
    public static final String c = "C";
    public static final String d = "S";
    public static final String e = "I";
    public static final String f = "J";
    public static final String g = "F";
    public static final String h = "D";
    public static final String i = "V";
    private java.lang.reflect.Field j;
    private long k = 0;

    private Field() {
    }

    public static Field a(Class<?> cls, String str, String str2) throws NoSuchFieldException {
        Field field = new Field();
        try {
            field.j = cls.getDeclaredField(str);
            field.j.setAccessible(true);
            return field;
        } catch (Exception e2) {
            throw new NoSuchFieldException((Throwable) e2);
        }
    }

    public static Field a(Class<?> cls, String str, Class<?> cls2) throws NoSuchFieldException {
        Field field = new Field();
        try {
            field.j = cls.getDeclaredField(str);
            field.j.setAccessible(true);
            return field;
        } catch (Exception e2) {
            throw new NoSuchFieldException((Throwable) e2);
        }
    }

    public static Field a(String str, String str2, String str3) throws NoSuchFieldException, NoSuchClassException {
        Field field = new Field();
        try {
            try {
                field.j = Class.forName(str).getDeclaredField(str2);
                field.j.setAccessible(true);
                return field;
            } catch (Exception e2) {
                throw new NoSuchFieldException((Throwable) e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new NoSuchFieldException((Throwable) e3);
        }
    }

    public static Field a(java.lang.reflect.Field field) {
        Field field2 = new Field();
        field2.j = field;
        field2.j.setAccessible(true);
        return field2;
    }

    public java.lang.reflect.Field a() {
        return this.j;
    }

    public void a(Object obj, boolean z) throws IllegalArgumentException {
        try {
            this.j.set(obj, Boolean.valueOf(z));
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public void a(Object obj, byte b2) throws IllegalArgumentException {
        try {
            this.j.set(obj, Byte.valueOf(b2));
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public void a(Object obj, char c2) throws IllegalArgumentException {
        try {
            this.j.set(obj, Character.valueOf(c2));
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public void a(Object obj, short s) throws IllegalArgumentException {
        try {
            this.j.set(obj, Short.valueOf(s));
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public void a(Object obj, int i2) throws IllegalArgumentException {
        try {
            this.j.set(obj, Integer.valueOf(i2));
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public void a(Object obj, long j2) throws IllegalArgumentException {
        try {
            this.j.set(obj, Long.valueOf(j2));
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public void a(Object obj, float f2) throws IllegalArgumentException {
        try {
            this.j.set(obj, Float.valueOf(f2));
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public void a(Object obj, double d2) throws IllegalArgumentException {
        try {
            this.j.set(obj, Double.valueOf(d2));
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public void a(Object obj, Object obj2) throws IllegalArgumentException {
        try {
            this.j.set(obj, obj2);
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public boolean a(Object obj) throws IllegalArgumentException {
        try {
            return this.j.getBoolean(obj);
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public byte b(Object obj) throws IllegalArgumentException {
        try {
            return Byte.valueOf(this.j.getByte(obj)).byteValue();
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public char c(Object obj) throws IllegalArgumentException {
        try {
            return this.j.getChar(obj);
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public short d(Object obj) throws IllegalArgumentException {
        try {
            return this.j.getShort(obj);
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public int e(Object obj) throws IllegalArgumentException {
        try {
            return this.j.getInt(obj);
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public long f(Object obj) throws IllegalArgumentException {
        try {
            return Long.valueOf(this.j.getLong(obj)).longValue();
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public float g(Object obj) throws IllegalArgumentException {
        try {
            return Float.valueOf(this.j.getFloat(obj)).floatValue();
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public double h(Object obj) throws IllegalArgumentException {
        try {
            return this.j.getDouble(obj);
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }

    public Object i(Object obj) throws IllegalArgumentException {
        try {
            return this.j.get(obj);
        } catch (Exception e2) {
            throw new ReflectionException((Throwable) e2);
        }
    }
}
