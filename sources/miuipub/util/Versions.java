package miuipub.util;

import android.os.Build;
import miuipub.os.SystemProperties;

public class Versions {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3025a = 9;
    public static final int b = 10;
    public static final int c = 11;
    public static final int d = 12;
    public static final int e = 13;
    public static final int f = 14;
    public static final int g = 15;
    public static final int h = 16;
    public static final int i = 16;
    public static final int j = 19;
    private static final int k = 4;
    private static final int l = 9;

    public static int a() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean b() {
        return 9 <= a();
    }

    public static boolean c() {
        return a() >= 11;
    }

    public static boolean d() {
        return a() >= 13;
    }

    public static boolean e() {
        return a() >= 14;
    }

    public static boolean f() {
        return a() >= 15;
    }

    public static boolean g() {
        return a() >= 16;
    }

    public static boolean h() {
        return a() >= 16;
    }

    public static boolean i() {
        return a() >= 19;
    }

    public static boolean j() {
        return SystemProperties.a("ro.miui.ui.version.code", 0) == 4;
    }
}
