package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.q;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

public final class CacheManager {
    @Deprecated
    public static boolean cacheDisabled() {
        bt a2 = bt.a();
        if (a2 != null && a2.b()) {
            return ((Boolean) a2.c().c()).booleanValue();
        }
        Object a3 = q.a("android.webkit.CacheManager", "cacheDisabled");
        if (a3 == null) {
            return false;
        }
        return ((Boolean) a3).booleanValue();
    }

    public static InputStream getCacheFile(String str, boolean z) {
        bt a2 = bt.a();
        if (a2 == null || !a2.b()) {
            return null;
        }
        return a2.c().a(str, z);
    }

    public static Object getCacheFile(String str, Map<String, String> map) {
        bt a2 = bt.a();
        if (a2 != null && a2.b()) {
            return a2.c().g();
        }
        try {
            return q.a(Class.forName("android.webkit.CacheManager"), "getCacheFile", (Class<?>[]) new Class[]{String.class, Map.class}, str, map);
        } catch (Exception unused) {
            return null;
        }
    }

    @Deprecated
    public static File getCacheFileBaseDir() {
        bt a2 = bt.a();
        return (File) ((a2 == null || !a2.b()) ? q.a("android.webkit.CacheManager", "getCacheFileBaseDir") : a2.c().g());
    }
}
