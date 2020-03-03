package com.xiaomi.mimc.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class UnsafeUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final Unsafe f11353a = d();
    private static final boolean b = f();
    private static final boolean c = e();
    private static final long d = ((long) g());
    private static final long e = a(a((Class<?>) Buffer.class, "address"));

    private UnsafeUtil() {
    }

    static boolean a() {
        return c;
    }

    static boolean b() {
        return b;
    }

    static long c() {
        return d;
    }

    static byte a(byte[] bArr, long j) {
        return f11353a.getByte(bArr, j);
    }

    static void a(byte[] bArr, long j, byte b2) {
        f11353a.putByte(bArr, j, b2);
    }

    static void a(byte[] bArr, long j, byte[] bArr2, long j2, long j3) {
        f11353a.copyMemory(bArr, j, bArr2, j2, j3);
    }

    static long b(byte[] bArr, long j) {
        return f11353a.getLong(bArr, j);
    }

    static byte a(long j) {
        return f11353a.getByte(j);
    }

    static void a(long j, byte b2) {
        f11353a.putByte(j, b2);
    }

    static long b(long j) {
        return f11353a.getLong(j);
    }

    static void a(long j, long j2, long j3) {
        f11353a.copyMemory(j, j2, j3);
    }

    static long a(ByteBuffer byteBuffer) {
        return f11353a.getLong(byteBuffer, e);
    }

    private static Unsafe d() {
        try {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                /* renamed from: a */
                public Unsafe run() throws Exception {
                    Class<Unsafe> cls = Unsafe.class;
                    for (Field field : cls.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get((Object) null);
                        if (cls.isInstance(obj)) {
                            return cls.cast(obj);
                        }
                    }
                    return null;
                }
            });
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean e() {
        if (f11353a != null) {
            try {
                Class<?> cls = f11353a.getClass();
                cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
                cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
                cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
                cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
                return true;
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static boolean f() {
        if (f11353a != null) {
            try {
                Class<?> cls = f11353a.getClass();
                cls.getMethod("objectFieldOffset", new Class[]{Field.class});
                cls.getMethod("getByte", new Class[]{Long.TYPE});
                cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
                cls.getMethod("getLong", new Class[]{Long.TYPE});
                cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
                return true;
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static int g() {
        if (c) {
            return f11353a.arrayBaseOffset(byte[].class);
        }
        return -1;
    }

    private static long a(Field field) {
        if (field == null || f11353a == null) {
            return -1;
        }
        return f11353a.objectFieldOffset(field);
    }

    private static Field a(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }
}
