package org.mp4parser.support;

public abstract class Logger {
    public abstract void a(String str);

    public abstract void b(String str);

    public abstract void c(String str);

    public static Logger a(Class cls) {
        if (System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik")) {
            return new AndroidLogger(cls.getSimpleName());
        }
        return new JuliLogger(cls.getSimpleName());
    }
}
