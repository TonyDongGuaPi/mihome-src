package com.xiaomi.jr.common.utils;

import com.xiaomi.jr.common.os.SystemProperties;

public class DeviceHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f10365a = a();
    public static final boolean b = "oled".equals(SystemProperties.a("ro.display.type"));
    public static final boolean c = SystemProperties.a("ro.sys.ft_whole_anim", true);

    private static boolean a() {
        String a2 = SystemProperties.a("ro.build.characteristics");
        return a2 != null && a2.contains("tablet");
    }
}
