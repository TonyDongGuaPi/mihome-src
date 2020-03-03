package com.mibi.sdk.common;

import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Utils {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f7602a = new File("/data/system/server_staging").exists();
    private static final Set<String> b = new HashSet(Arrays.asList(new String[]{"AT", "BE", "BG", "CY", "CZ", "DE", "DK", "EE", ServerCompact.f, "FI", ServerCompact.e, ServerCompact.i, "GR", "HR", "HU", "IE", ServerCompact.h, "LT", "LU", "LV", "MT", "NL", "PL", "PT", "RO", "SE", "SI", "SK"}));

    public static String a() {
        return Locale.getDefault().getLanguage();
    }

    public static String b() {
        return Locale.getDefault().getCountry();
    }

    public static boolean c() {
        return d() && b.contains(e());
    }

    public static boolean d() {
        try {
            Object obj = Class.forName("miuipub.os.Build").getField("IS_INTERNATIONAL_BUILD").get((Object) null);
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    protected static String e() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.miui.region"});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "unknown";
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return "unknown";
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return "unknown";
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return "unknown";
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return "unknown";
        }
    }
}
