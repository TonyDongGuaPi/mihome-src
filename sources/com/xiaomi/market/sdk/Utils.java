package com.xiaomi.market.sdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.Locale;

public class Utils {

    /* renamed from: a  reason: collision with root package name */
    static final String f11117a = "MarketSdkUtils";
    static boolean b = new File("/sdcard/com.xiaomi.market.sdk/sdk_debug").exists();
    protected static final int c = 1000000;
    protected static final int d = 1000;
    /* access modifiers changed from: private */
    public static Singleton<String> e = new Singleton<String>() {
        /* access modifiers changed from: protected */
        /* renamed from: c */
        public String a() {
            return (!Client.r() || Client.s()) ? "" : "com.xiaomi.market";
        }
    };
    private static volatile Singleton<Boolean> f = new Singleton<Boolean>() {
        /* access modifiers changed from: protected */
        /* renamed from: c */
        public Boolean a() {
            String str = (String) Utils.e.b();
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                int applicationEnabledSetting = AppGlobal.a().getPackageManager().getApplicationEnabledSetting(str);
                boolean z = true;
                if (applicationEnabledSetting != 0) {
                    if (applicationEnabledSetting != 1) {
                        z = false;
                    }
                }
                return Boolean.valueOf(z);
            } catch (Exception unused) {
                return false;
            }
        }
    };

    static String a() {
        return e.b();
    }

    static boolean a(Context context) {
        return f.b().booleanValue();
    }

    static boolean b() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.build.characteristics"})).contains("tablet");
        } catch (Exception e2) {
            Log.b(f11117a, e2.getMessage(), (Throwable) e2);
            return false;
        }
    }

    public static String a(long j, Context context) {
        int i;
        String str;
        if (context == null || j < 0) {
            return "";
        }
        if (j > 1000000) {
            Locale locale = Locale.getDefault();
            double d2 = (double) j;
            Double.isNaN(d2);
            str = String.format(locale, "%.1f", new Object[]{Double.valueOf((d2 * 1.0d) / 1000000.0d)});
            i = R.string.xiaomi_update_sdk_megabytes_unit;
        } else if (j > 1000) {
            Locale locale2 = Locale.getDefault();
            double d3 = (double) j;
            Double.isNaN(d3);
            str = String.format(locale2, "%.1f", new Object[]{Double.valueOf((d3 * 1.0d) / 1000.0d)});
            i = R.string.xiaomi_update_sdk_kilobytes_unit;
        } else {
            str = String.valueOf(j);
            i = R.string.xiaomi_update_sdk_bytes_unit;
        }
        return context.getString(i, new Object[]{str});
    }

    static boolean b(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    static boolean c(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    static boolean d(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 0) {
            return false;
        }
        return true;
    }

    static boolean a(boolean z) {
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState)) {
            return true;
        }
        if (!"mounted_ro".equals(externalStorageState) || z) {
            return false;
        }
        return true;
    }
}
