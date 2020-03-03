package com.xiaomi.smarthome.newui;

import com.xiaomi.smarthome.application.SHApplication;

public class DeviceMainPageHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20243a = "device_main_page_sp";
    public static final String b = "has_any_cache";
    private static volatile boolean c = true;
    private static volatile boolean d = false;

    public static boolean a() {
        if (!d) {
            c = SHApplication.getAppContext().getSharedPreferences(f20243a, 0).getBoolean(b, false);
            d = true;
        }
        return c;
    }

    public static void a(boolean z) {
        if (z) {
            SHApplication.getAppContext().getSharedPreferences(f20243a, 0).edit().putBoolean(b, true).apply();
            c = true;
        }
    }
}
