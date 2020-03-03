package com.xiaomi.smarthome.fastvideo;

import android.util.Log;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;

public class LogUtil {

    /* renamed from: a  reason: collision with root package name */
    private static String f15884a = "fastvideo";

    public static void a(String str, String str2) {
        if (GlobalSetting.q || GlobalSetting.w) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (GlobalSetting.q || GlobalSetting.w) {
            Log.e(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (GlobalSetting.q || GlobalSetting.w) {
            Log.e(str, str2, th);
        }
    }

    public static void c(String str, String str2) {
        b(str, str2, (Throwable) null);
    }

    public static void b(String str, String str2, Throwable th) {
        if (GlobalSetting.q || GlobalSetting.w) {
            XmPluginHostApi instance = XmPluginHostApi.instance();
            String str3 = f15884a;
            instance.logForModel(str3, str + ":" + str2);
            StringBuilder sb = new StringBuilder();
            sb.append(f15884a);
            sb.append(str);
            Log.e(sb.toString(), str2, th);
        }
    }
}
