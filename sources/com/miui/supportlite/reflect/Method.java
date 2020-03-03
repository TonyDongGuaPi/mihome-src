package com.miui.supportlite.reflect;

public class Method {

    /* renamed from: a  reason: collision with root package name */
    private java.lang.reflect.Method f8211a;

    private Method() {
    }

    public static Method a(Class<?> cls, String str, Class<?> cls2, Class<?>... clsArr) throws NoSuchMethodException {
        Method method = new Method();
        try {
            method.f8211a = cls.getDeclaredMethod(str, clsArr);
            method.f8211a.setAccessible(true);
            return method;
        } catch (Exception e) {
            throw new NoSuchMethodException((Throwable) e);
        }
    }

    public void a(Class<?> cls, Object obj, Object... objArr) {
        try {
            this.f8211a.invoke(obj, objArr);
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }

    public boolean b(Class<?> cls, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return ((Boolean) this.f8211a.invoke(obj, objArr)).booleanValue();
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }

    public byte c(Class<?> cls, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return ((Byte) this.f8211a.invoke(obj, objArr)).byteValue();
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }

    public char d(Class<?> cls, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return ((Character) this.f8211a.invoke(obj, objArr)).charValue();
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }

    public short e(Class<?> cls, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return ((Short) this.f8211a.invoke(obj, objArr)).shortValue();
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }

    public int f(Class<?> cls, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return ((Integer) this.f8211a.invoke(obj, objArr)).intValue();
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }

    public long g(Class<?> cls, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return ((Long) this.f8211a.invoke(obj, objArr)).longValue();
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }

    public float h(Class<?> cls, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return ((Float) this.f8211a.invoke(obj, objArr)).floatValue();
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }

    public double i(Class<?> cls, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return ((Double) this.f8211a.invoke(obj, objArr)).doubleValue();
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }

    public Object j(Class<?> cls, Object obj, Object... objArr) throws IllegalArgumentException {
        try {
            return this.f8211a.invoke(obj, objArr);
        } catch (Exception e) {
            throw new ReflectionException((Throwable) e);
        }
    }
}