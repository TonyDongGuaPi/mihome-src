package org.libsodium.jni;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NaCl {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f3715a = Logger.getLogger(NaCl.class.getName());

    static {
        String property = System.getProperty("java.library.path");
        Logger logger = f3715a;
        Level level = Level.INFO;
        logger.log(level, "librarypath=" + property);
        System.loadLibrary("sodiumjni");
    }

    public static Sodium a() {
        Sodium.a();
        return SingletonHolder.f3716a;
    }

    private static final class SingletonHolder {

        /* renamed from: a  reason: collision with root package name */
        public static final Sodium f3716a = new Sodium();

        private SingletonHolder() {
        }
    }

    private NaCl() {
    }
}
