package com.xiaomi.smarthome.framework.plugin.rn.utils;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;

public class RnPluginLog {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17509a = "miot-rn-plugin";
    private static final int b = 2000;
    private static boolean c = false;

    public static void a(boolean z) {
        c = z;
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str) && a()) {
            int length = str.length() / 2000;
            int i = 0;
            while (i <= length) {
                int i2 = i * 2000;
                i++;
                int i3 = i * 2000;
                if (i3 > str.length()) {
                    i3 = str.length();
                }
                if (i2 >= i3) {
                    i2 = i3 - 1;
                }
                Log.i(f17509a, str.substring(i2, i3));
            }
        }
    }

    public static void b(String str) {
        if (!TextUtils.isEmpty(str) && a()) {
            int length = str.length() / 2000;
            int i = 0;
            while (i <= length) {
                int i2 = i * 2000;
                i++;
                int i3 = i * 2000;
                if (i3 > str.length()) {
                    i3 = str.length();
                }
                if (i2 >= i3) {
                    i2 = i3 - 1;
                }
                Log.e(f17509a, str.substring(i2, i3));
            }
        }
    }

    public static void c(String str) {
        if (!TextUtils.isEmpty(str) && a()) {
            int length = str.length() / 2000;
            int i = 0;
            while (i <= length) {
                int i2 = i * 2000;
                i++;
                int i3 = i * 2000;
                if (i3 > str.length()) {
                    i3 = str.length();
                }
                if (i2 >= i3) {
                    i2 = i3 - 1;
                }
                Log.v(f17509a, str.substring(i2, i3));
            }
        }
    }

    public static void d(String str) {
        if (!TextUtils.isEmpty(str) && a()) {
            int length = str.length() / 2000;
            int i = 0;
            while (i <= length) {
                int i2 = i * 2000;
                i++;
                int i3 = i * 2000;
                if (i3 > str.length()) {
                    i3 = str.length();
                }
                if (i2 >= i3) {
                    i2 = i3 - 1;
                }
                Log.d(f17509a, str.substring(i2, i3));
            }
        }
    }

    public static void e(String str) {
        if (!TextUtils.isEmpty(str) && a()) {
            int length = str.length() / 2000;
            int i = 0;
            while (i <= length) {
                int i2 = i * 2000;
                i++;
                int i3 = i * 2000;
                if (i3 > str.length()) {
                    i3 = str.length();
                }
                if (i2 >= i3) {
                    i2 = i3 - 1;
                }
                Log.w(f17509a, str.substring(i2, i3));
            }
        }
    }

    private static boolean a() {
        return c || GlobalSetting.q || GlobalSetting.w || GlobalSetting.s;
    }
}
