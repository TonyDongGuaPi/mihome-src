package com.xiaomi.youpin;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.plugin.XmPluginHostApi;
import java.io.File;

public class AppStoreConstants {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23147a = "com.xiaomi.smarthome.shop.action.TAB_DOUBLE_TAP";
    public static final String b = "com.xiaomi.smarthome.shop.extra.POSITION";
    public static final String c = "com.xiaomi.smarthome.shop.addr.SELECTED";
    public static final String d = "com.xiaomi.smarthome.shop.addr.UPDATED";
    public static final String e = "com.xiaomi.smarthome.shop.order.UPDATED";
    static SharedPreferences f = null;
    static boolean g = false;
    static int h = 0;
    public static final String i = "miotStore1.0";
    public static final String j = "StoreDebugMode";
    public static final String k = "PageModel";
    private static String l;
    private static String m;

    public static synchronized String a() {
        String str;
        synchronized (AppStoreConstants.class) {
            if (TextUtils.isEmpty(m)) {
                File externalCacheDir = XmPluginHostApi.instance().context().getExternalCacheDir();
                if (externalCacheDir != null) {
                    l = externalCacheDir.getPath();
                } else {
                    l = XmPluginHostApi.instance().context().getCacheDir().getPath();
                }
                m = l + File.separator + "share";
            }
            str = m;
        }
        return str;
    }

    static void b() {
        if (f == null) {
            f = XmPluginHostApi.instance().context().getSharedPreferences("miotStore1.0", 0);
        }
    }

    public static boolean c() {
        b();
        if (f != null) {
            g = f.getBoolean("StoreDebugMode", false);
        }
        return g;
    }

    public static void a(boolean z) {
        b();
        if (f == null || g != z) {
            g = z;
            f.edit().putBoolean("StoreDebugMode", z).commit();
        }
    }

    public static int d() {
        b();
        if (f != null) {
            h = f.getInt(k, 0);
        }
        return h;
    }

    public static void a(int i2) {
        b();
        h = i2;
        f.edit().putInt(k, h).commit();
    }
}
