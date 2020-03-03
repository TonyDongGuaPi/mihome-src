package com.xiaomi.audioprocess;

import android.content.Context;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

public final class AudioUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f10007a = {"Nexus 5", "D6503"};
    private static final String[] b = {"Nexus 6"};
    private static final int c = 44100;

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 17;
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static String e() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + Operators.ARRAY_END_STR;
    }

    public static boolean f() {
        return Build.HARDWARE.equals("goldfish") && Build.BRAND.startsWith("generic_");
    }

    public static boolean g() {
        return Arrays.asList(f10007a).contains(Build.MODEL);
    }

    public static boolean h() {
        return Arrays.asList(b).contains(Build.MODEL);
    }

    public static boolean i() {
        if (!b()) {
            return false;
        }
        return AcousticEchoCanceler.isAvailable();
    }

    public static boolean j() {
        if (g()) {
            return false;
        }
        return i();
    }

    public static void a(String str) {
        Log.d(str, "Android SDK: " + Build.VERSION.SDK_INT + ", Release: " + Build.VERSION.RELEASE + ", Brand: " + Build.BRAND + ", Device: " + Build.DEVICE + ", Id: " + Build.ID + ", Hardware: " + Build.HARDWARE + ", Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", Product: " + Build.PRODUCT);
    }

    public static boolean a(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }
}
