package com.xiaomi.smarthome.framework.log;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;

public class LogUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1537a = 2000;

    public static void a(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (GlobalSetting.q || GlobalSetting.w) {
                int i = 0;
                while (i <= str2.length() / 2000) {
                    int i2 = i * 2000;
                    i++;
                    int i3 = i * 2000;
                    if (i3 > str2.length()) {
                        i3 = str2.length();
                    }
                    if (i3 >= 0 && i2 < i3) {
                        Log.d(str, str2.substring(i2, i3));
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public static void b(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (GlobalSetting.q || GlobalSetting.w) {
                int i = 0;
                while (i <= str2.length() / 2000) {
                    int i2 = i * 2000;
                    i++;
                    int i3 = i * 2000;
                    if (i3 > str2.length()) {
                        i3 = str2.length();
                    }
                    Log.e(str, str2.substring(i2, i3));
                }
            }
        }
    }

    public static void c(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (GlobalSetting.q || GlobalSetting.w) {
                int i = 0;
                while (i <= str2.length() / 2000) {
                    int i2 = i * 2000;
                    i++;
                    int i3 = i * 2000;
                    if (i3 > str2.length()) {
                        i3 = str2.length();
                    }
                    Log.i(str, str2.substring(i2, i3));
                }
            }
        }
    }

    public static void d(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (GlobalSetting.q || GlobalSetting.w) {
                int i = 0;
                while (i <= str2.length() / 2000) {
                    int i2 = i * 2000;
                    i++;
                    int i3 = i * 2000;
                    if (i3 > str2.length()) {
                        i3 = str2.length();
                    }
                    Log.v(str, str2.substring(i2, i3));
                }
            }
        }
    }

    public static void e(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (GlobalSetting.q || GlobalSetting.w) {
                int i = 0;
                while (i <= str2.length() / 2000) {
                    int i2 = i * 2000;
                    i++;
                    int i3 = i * 2000;
                    if (i3 > str2.length()) {
                        i3 = str2.length();
                    }
                    Log.w(str, str2.substring(i2, i3));
                }
            }
        }
    }

    public static void f(String str, String str2) {
        if (GlobalSetting.q || GlobalSetting.w) {
            String str3 = str2 + " " + Log.getStackTraceString(new Throwable());
            int i = 0;
            while (i <= str3.length() / 2000) {
                int i2 = i * 2000;
                i++;
                int i3 = i * 2000;
                if (i3 > str3.length()) {
                    i3 = str3.length();
                }
                Log.d(str, str3.substring(i2, i3));
            }
        }
    }

    public static void a(String str, Intent intent) {
        Bundle extras;
        if (intent != null) {
            if ((GlobalSetting.q || GlobalSetting.w) && (extras = intent.getExtras()) != null) {
                for (String str2 : extras.keySet()) {
                    Object obj = extras.get(str2);
                    Log.d(str, String.format("%s %s (%s)", new Object[]{str2, obj.toString(), obj.getClass().getName()}));
                }
            }
        }
    }
}
