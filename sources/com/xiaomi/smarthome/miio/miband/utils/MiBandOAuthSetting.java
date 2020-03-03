package com.xiaomi.smarthome.miio.miband.utils;

import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import java.util.Arrays;

public class MiBandOAuthSetting {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19476a = "1435821822";
    public static final String b = "NWZkYjMwMDFjNGRiMjhjOWRmMTkzNGMxYjRhODcyZWI";

    public static String b() {
        return GlobalSetting.f1547a;
    }

    public static String c() {
        return GlobalSetting.b;
    }

    public static String d() {
        return GlobalSetting.c;
    }

    public static String e() {
        return GlobalSetting.d;
    }

    public static long a() {
        try {
            return Long.parseLong(GlobalSetting.f1547a);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static int[] f() {
        return Arrays.copyOf(new int[]{1}, 1);
    }
}
