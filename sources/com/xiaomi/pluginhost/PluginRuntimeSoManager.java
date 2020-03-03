package com.xiaomi.pluginhost;

import android.content.Context;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PluginRuntimeSoManager {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f12600a = new Object();
    private static PluginRuntimeSoManager b;

    private PluginRuntimeSoManager() {
    }

    public static PluginRuntimeSoManager a() {
        if (b == null) {
            synchronized (f12600a) {
                if (b == null) {
                    b = new PluginRuntimeSoManager();
                }
            }
        }
        return b;
    }

    public void a(Context context, long j, long j2, String str, ClassLoader classLoader) {
        try {
            String str2 = PluginSettings.a(context, j, j2) + File.separator + ShareConstants.o + str + ".so";
            Method declaredMethod = Runtime.getRuntime().getClass().getDeclaredMethod("load", new Class[]{String.class, ClassLoader.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(Runtime.getRuntime(), new Object[]{str2, classLoader});
        } catch (Throwable unused) {
        }
    }

    public void a(String str, String str2, ClassLoader classLoader) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String str3 = str + File.separator + ShareConstants.o + str2 + ".so";
        Method declaredMethod = Runtime.getRuntime().getClass().getDeclaredMethod("load", new Class[]{String.class, ClassLoader.class});
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(Runtime.getRuntime(), new Object[]{str3, classLoader});
    }
}
