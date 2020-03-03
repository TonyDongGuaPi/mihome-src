package com.xiaomi.smarthome.shop.utils;

import android.content.SharedPreferences;
import com.xiaomi.smarthome.application.SHApplication;
import java.io.File;

public class DeviceShopConstants {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22186a = "com.xiaomi.smarthome.shop.action.TAB_DOUBLE_TAP";
    public static final String b = "com.xiaomi.smarthome.shop.extra.POSITION";
    public static final String c = "com.xiaomi.smarthome.shop.addr.SELECTED";
    public static final String d = "com.xiaomi.smarthome.shop.addr.UPDATED";
    public static final String e = "com.xiaomi.smarthome.shop.order.UPDATED";
    public static final String f;
    public static final String g = (f + File.separator + "share");
    static SharedPreferences h = null;
    static boolean i = false;
    public static final String j = "miotStore1.0";
    public static final String k = "StoreDebugMode";

    static {
        File externalCacheDir = SHApplication.getAppContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            f = externalCacheDir.getPath();
        } else {
            f = SHApplication.getAppContext().getCacheDir().getPath();
        }
    }

    public static boolean a() {
        if (h == null) {
            h = SHApplication.getAppContext().getSharedPreferences("miotStore1.0", 0);
            if (h != null) {
                i = h.getBoolean("StoreDebugMode", false);
            }
        }
        return i;
    }

    public static void a(boolean z) {
        if (h == null || i != z) {
            i = z;
            if (h == null) {
                h = SHApplication.getAppContext().getSharedPreferences("miotStore1.0", 0);
            }
            if (h != null) {
                h.edit().putBoolean("StoreDebugMode", z).commit();
            }
        }
    }
}
