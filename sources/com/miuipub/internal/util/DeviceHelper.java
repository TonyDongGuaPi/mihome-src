package com.miuipub.internal.util;

import android.os.Build;
import android.text.TextUtils;
import miuipub.os.SystemProperties;

public class DeviceHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f8283a = ("cancro".equals(Build.DEVICE) && Build.MODEL.startsWith("MI 4"));
    public static final boolean b = SystemProperties.a("ro.product.mod_device", "").endsWith("_alpha");
    public static final boolean c = "1".equals(SystemProperties.a("ro.miui.cta"));
    public static final boolean d = SystemProperties.a("ro.sys.ft_whole_anim", true);
    public static final boolean e = SystemProperties.a("ro.product.mod_device", "").endsWith("_global");
    public static final boolean f = b();
    public static final boolean g = (!TextUtils.isEmpty(Build.VERSION.INCREMENTAL) && Build.VERSION.INCREMENTAL.matches(j));
    public static final boolean h = ("user".equals(Build.TYPE) && !g);
    public static final boolean i;
    private static final String j = "\\d+.\\d+.\\d+(-internal)?";

    static {
        boolean z = false;
        if (SystemProperties.a("ro.debuggable", 0) == 1) {
            z = true;
        }
        i = z;
    }

    private static boolean b() {
        return SystemProperties.a("ro.build.characteristics").contains("tablet");
    }

    public static String a() {
        return SystemProperties.a("ro.miui.region", "CN");
    }
}
