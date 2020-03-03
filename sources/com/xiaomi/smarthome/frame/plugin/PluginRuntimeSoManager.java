package com.xiaomi.smarthome.frame.plugin;

import android.content.Context;
import android.util.Log;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.smarthome.setting.PluginSetting;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PluginRuntimeSoManager {
    private static PluginRuntimeSoManager sInstance;
    private static final Object sLock = new Object();

    private PluginRuntimeSoManager() {
    }

    public static PluginRuntimeSoManager getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new PluginRuntimeSoManager();
                }
            }
        }
        return sInstance;
    }

    public void loadLibrary(Context context, long j, long j2, String str, ClassLoader classLoader) {
        try {
            String str2 = PluginSetting.b(context, j, j2) + File.separator + ShareConstants.o + str + ".so";
            Method declaredMethod = Runtime.getRuntime().getClass().getDeclaredMethod("load", new Class[]{String.class, ClassLoader.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(Runtime.getRuntime(), new Object[]{str2, classLoader});
            Log.e("Init", "init so - " + str2);
        } catch (Throwable th) {
            CrashReport.postCatchedException(th);
        }
    }

    public void loadLibrary(String str, String str2, ClassLoader classLoader) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String str3 = str + File.separator + ShareConstants.o + str2 + ".so";
        Method declaredMethod = Runtime.getRuntime().getClass().getDeclaredMethod("load", new Class[]{String.class, ClassLoader.class});
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(Runtime.getRuntime(), new Object[]{str3, classLoader});
    }
}
