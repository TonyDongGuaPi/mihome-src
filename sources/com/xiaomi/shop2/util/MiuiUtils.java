package com.xiaomi.shop2.util;

import com.xiaomi.mishopsdk.util.Log;
import java.lang.reflect.Method;

@Deprecated
public class MiuiUtils {
    private static final String TAG = "MiuiUtils";
    private static Method sGetSystemProperties = null;
    private static boolean sHasDetectSystemProperties = false;

    public static String getMiuiBuildCode() {
        return getStringSystemPropery("ro.build.version.incremental");
    }

    public static String getMiuiVersion() {
        return getStringSystemPropery("ro.miui.ui.version.code");
    }

    public static String getStringSystemPropery(String str) {
        Method procOfSystemProperties = getProcOfSystemProperties();
        String str2 = null;
        if (procOfSystemProperties == null) {
            return null;
        }
        try {
            String str3 = (String) procOfSystemProperties.invoke((Object) null, new Object[]{str});
            try {
                Log.e(TAG, "getStringSystemPropery %s=%s.", str, str3);
                return str3;
            } catch (Exception e) {
                Exception exc = e;
                str2 = str3;
                e = exc;
                Log.e(TAG, "getStringSystemPropery failed. key=%s", str, e);
                return str2;
            }
        } catch (Exception e2) {
            e = e2;
            Log.e(TAG, "getStringSystemPropery failed. key=%s", str, e);
            return str2;
        }
    }

    private static Method getProcOfSystemProperties() {
        if (sHasDetectSystemProperties) {
            return null;
        }
        try {
            sGetSystemProperties = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
            sGetSystemProperties.setAccessible(true);
            sHasDetectSystemProperties = true;
        } catch (Exception e) {
            Log.e(TAG, "getProcOfSystemProperties failed.", (Object) e);
        }
        if (sHasDetectSystemProperties) {
            return sGetSystemProperties;
        }
        return null;
    }
}
