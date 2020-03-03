package com.xiaomi.market.sdk;

public class Patcher {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1468a = "MarketPatcher";
    private static boolean b = a();
    private static final String c = "sdk_patcher_jni";

    public static native int applyPatch(String str, String str2, String str3);

    public static boolean a() {
        try {
            System.loadLibrary(c);
            return true;
        } catch (Throwable th) {
            Log.b(f1468a, "load patcher library failed : " + th.toString());
            return false;
        }
    }

    public static boolean b() {
        return b;
    }

    public static int a(String str, String str2, String str3) {
        return applyPatch(str, str2, str3);
    }
}
