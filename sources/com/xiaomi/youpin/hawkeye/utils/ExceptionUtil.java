package com.xiaomi.youpin.hawkeye.utils;

public class ExceptionUtil {
    public static <T extends Throwable> void a(Throwable th, Class<T> cls) throws Throwable {
        if (cls.isInstance(th)) {
            throw th;
        }
    }

    public static RuntimeException a(Throwable th) {
        a(th, Error.class);
        a(th, RuntimeException.class);
        throw new RuntimeException(th);
    }

    public static <T extends Throwable> void b(Throwable th) throws Throwable {
        throw th;
    }
}
