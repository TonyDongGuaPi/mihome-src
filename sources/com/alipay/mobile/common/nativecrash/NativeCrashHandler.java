package com.alipay.mobile.common.nativecrash;

import android.content.Context;
import android.util.Log;
import com.alipay.mobile.common.nativecrash.NativeCrashHandlerApi;
import com.xiaomi.smarthome.framework.crash.MainNativeCrashHandler;
import java.io.File;

public class NativeCrashHandler {
    public static final boolean ENABLE_NATIVE_LOG = true;
    public static final boolean ENANBLE_JAVA_LOG = false;
    public static final String FILE_PATH = "app_crash";

    /* renamed from: a  reason: collision with root package name */
    private static String f973a;

    public static void setNewInstall() {
    }

    public static final synchronized String getPath() {
        String str;
        synchronized (NativeCrashHandler.class) {
            str = f973a;
        }
        return str;
    }

    public static void initialize(Context context) {
        f973a = context.getApplicationInfo().dataDir + File.separator + FILE_PATH;
        prepareCrashInfo();
        NativeCrashHandlerApi.setCrashGetter(new NativeCrashHandlerApi.NativeCrashApiGetter() {
        });
        Log.w(MainNativeCrashHandler.f16496a, "initialize ok");
    }

    public static void prepareCrashInfo() {
        try {
            System.loadLibrary("crashsdk");
        } catch (Throwable unused) {
            Log.w(MainNativeCrashHandler.f16496a, "loadLibrary exception");
        }
    }
}
