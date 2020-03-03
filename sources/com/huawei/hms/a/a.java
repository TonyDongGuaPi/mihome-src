package com.huawei.hms.a;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5841a = a("ro.build.version.emui", "");

    /* renamed from: com.huawei.hms.a.a$a  reason: collision with other inner class name */
    public static class C0050a {

        /* renamed from: a  reason: collision with root package name */
        public static final int f5842a = a.a("ro.build.hw_emui_api_level", 0);
    }

    public static String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{str, str2});
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            Log.e("HwBuildEx", "An exception occurred while reading: EMUI_VERSION");
            return str2;
        }
    }

    public static int a(String str, int i) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return ((Integer) cls.getDeclaredMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke(cls, new Object[]{str, Integer.valueOf(i)})).intValue();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            Log.e("HwBuildEx", "An exception occurred while reading: EMUI_SDK_INT");
            return i;
        }
    }
}
