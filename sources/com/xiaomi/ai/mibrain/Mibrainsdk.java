package com.xiaomi.ai.mibrain;

public class Mibrainsdk {
    public static int MIBRAIN_DEBUG_LEVEL_DEBUG = 1;
    public static int MIBRAIN_DEBUG_LEVEL_ERROR = 3;
    public static int MIBRAIN_DEBUG_LEVEL_WARNING = 2;
    public static int MIBRAIN_LOG_CURRENT_LEVEL = MIBRAIN_DEBUG_LEVEL_WARNING;
    private static boolean loaded;

    public interface MibrainsdkLogHook {
        void onLog(int i, String str);
    }

    static synchronized void loadMibrainLibs() {
        synchronized (Mibrainsdk.class) {
            if (!loaded) {
                System.loadLibrary("mibrainsdk");
                System.loadLibrary("mibrainjni");
                loaded = true;
            }
        }
    }

    public static void setLogLevel(int i) {
        MibrainNativeRequest.setDebugLogLevel(i);
        MIBRAIN_LOG_CURRENT_LEVEL = i;
    }

    public static void setMibrainsdkLogHook(MibrainsdkLogHook mibrainsdkLogHook) {
        MibrainNativeRequest.setLogHook(mibrainsdkLogHook);
    }
}
