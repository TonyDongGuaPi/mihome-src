package com.xiaomi.shop2.util;

import android.content.Context;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.Log;
import java.lang.reflect.Method;

@Deprecated
public class SystemPropertiesProxy {
    private static final String TAG = "SystemPropertiesProxy";
    private static Method sSysPropMethodGet;
    private static Method sSysPropMethodGetBoolean;
    private static Method sSysPropMethodGetInt;
    private static Method sSysPropMethodGetLong;
    private static Method sSysPropMethodSet;
    private static Class sSystemProperties;

    public static String get(Context context, String str) {
        return get(context, str, "");
    }

    public static String get(Context context, String str, String str2) {
        Method sysPropMethodGet = getSysPropMethodGet();
        if (sysPropMethodGet != null) {
            try {
                return (String) sysPropMethodGet.invoke(sSystemProperties, new Object[]{new String(str)});
            } catch (Exception e) {
                Log.e(TAG, "SystemProperties.get failed, key=%s", str, e);
            }
        }
        return str2;
    }

    public static Integer getInt(Context context, String str, int i) {
        Integer valueOf = Integer.valueOf(i);
        try {
            return (Integer) getSysPropMethodGetInt().invoke(sSystemProperties, new Object[]{new String(str), new Integer(i)});
        } catch (Exception e) {
            Log.e(TAG, "SystemProperties.getInt failed, key=%s", str, e);
            return valueOf;
        }
    }

    public static Long getLong(Context context, String str, long j) {
        Long valueOf = Long.valueOf(j);
        Method sysPropMethodGetLong = getSysPropMethodGetLong();
        if (sysPropMethodGetLong == null) {
            return valueOf;
        }
        try {
            return (Long) sysPropMethodGetLong.invoke(sSystemProperties, new Object[]{new String(str), new Long(j)});
        } catch (Exception e) {
            Log.e(TAG, "SystemProperties.getLong failed, key=%s", str, e);
            return valueOf;
        }
    }

    public static Boolean getBoolean(Context context, String str, boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        Method sysPropMethodGetBoolean = getSysPropMethodGetBoolean();
        if (sysPropMethodGetBoolean == null) {
            return valueOf;
        }
        try {
            return (Boolean) sysPropMethodGetBoolean.invoke(sSystemProperties, new Object[]{new String(str), new Boolean(z)});
        } catch (Exception e) {
            Log.e(TAG, "SystemProperties.getBoolean failed, key=%s", str, e);
            return valueOf;
        }
    }

    public static void set(Context context, String str, String str2) throws IllegalArgumentException {
        Method sysPropMethodSet = getSysPropMethodSet();
        if (sysPropMethodSet != null) {
            try {
                sysPropMethodSet.invoke(sSystemProperties, new Object[]{new String(str), new String(str2)});
            } catch (Exception e) {
                Log.e(TAG, "SystemProperties.set failed, key=%s, val=%s", str, str2, e);
            }
        }
    }

    private static Class getSysPropClass() {
        if (sSystemProperties == null) {
            try {
                sSystemProperties = ShopApp.instance.getClassLoader().loadClass("android.os.SystemProperties");
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "loadClass android.os.SystemProperties failed.", (Object) e);
            }
        }
        return sSystemProperties;
    }

    private static Method getSysPropMethodGet() {
        Class sysPropClass;
        if (sSysPropMethodGet == null && (sysPropClass = getSysPropClass()) != null) {
            try {
                sSysPropMethodGet = sysPropClass.getMethod("get", new Class[]{String.class});
            } catch (NoSuchMethodException e) {
                Log.e(TAG, "get method android.os.SystemProperties.get failed.", (Object) e);
            }
        }
        return sSysPropMethodGet;
    }

    private static Method getSysPropMethodSet() {
        Class sysPropClass;
        if (sSysPropMethodSet == null && (sysPropClass = getSysPropClass()) != null) {
            try {
                sSysPropMethodSet = sysPropClass.getMethod("set", new Class[]{String.class, String.class});
            } catch (NoSuchMethodException e) {
                Log.e(TAG, "get method android.os.SystemProperties.set failed.", (Object) e);
            }
        }
        return sSysPropMethodSet;
    }

    private static Method getSysPropMethodGetInt() {
        if (sSysPropMethodGetInt == null) {
            sSysPropMethodGetInt = getSysPropMethodGetXx(Integer.TYPE, "getInt");
        }
        return sSysPropMethodGetInt;
    }

    private static Method getSysPropMethodGetLong() {
        if (sSysPropMethodGetLong == null) {
            sSysPropMethodGetLong = getSysPropMethodGetXx(Long.TYPE, "getLong");
        }
        return sSysPropMethodGetLong;
    }

    private static Method getSysPropMethodGetBoolean() {
        if (sSysPropMethodGetBoolean == null) {
            sSysPropMethodGetBoolean = getSysPropMethodGetXx(Boolean.TYPE, "getBoolean");
        }
        return sSysPropMethodGetBoolean;
    }

    private static Method getSysPropMethodGetXx(Class cls, String str) {
        Class sysPropClass = getSysPropClass();
        if (sysPropClass == null) {
            return null;
        }
        try {
            return sysPropClass.getMethod(str, new Class[]{String.class, cls});
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "get method android.os.SystemProperties.%s failed.", str, e);
            return null;
        }
    }
}
