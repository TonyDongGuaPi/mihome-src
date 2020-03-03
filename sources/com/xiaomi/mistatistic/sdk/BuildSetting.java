package com.xiaomi.mistatistic.sdk;

import android.content.ContentResolver;
import android.content.Context;
import com.xiaomi.mistatistic.sdk.controller.c;
import com.xiaomi.mistatistic.sdk.controller.h;
import com.xiaomi.mistatistic.sdk.controller.q;
import java.lang.reflect.Method;
import miuipub.reflect.Field;

public class BuildSetting {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f11993a = false;
    private static boolean b = false;
    private static Boolean c = null;
    private static boolean d = true;
    private static long e = 60000;
    private static long f;

    public static void a(boolean z) {
        f11993a = z;
    }

    public static boolean a() {
        return f11993a;
    }

    public static void b() {
        b = true;
    }

    public static boolean c() {
        return b;
    }

    public static boolean a(Context context) {
        if (!d) {
            h.b("isDisabled false, sRespectUEP " + d);
            return false;
        }
        if (c == null || q.a(f, e)) {
            if (!q.d(context) || !q.e(context)) {
                c = false;
                h.b("isDisabled false, not miui app or OS ");
            } else {
                c = Boolean.valueOf(!b(context));
            }
            f = System.currentTimeMillis();
        }
        return c.booleanValue();
    }

    public static boolean b(Context context) {
        boolean z;
        try {
            Method declaredMethod = Class.forName("android.provider.MiuiSettings$Secure").getDeclaredMethod("isUserExperienceProgramEnable", new Class[]{ContentResolver.class});
            declaredMethod.setAccessible(true);
            z = ((Boolean) declaredMethod.invoke((Object) null, new Object[]{context.getContentResolver()})).booleanValue();
        } catch (Exception e2) {
            h.b("BS", "isUserExperienceProgramEnable exception:", (Throwable) e2);
            z = true;
        }
        h.b("UEP " + z);
        return z;
    }

    public static boolean c(Context context) {
        boolean z = true;
        try {
            Method declaredMethod = Class.forName("android.provider.MiuiSettings$Secure").getDeclaredMethod("isUploadDebugLogEnable", new Class[]{ContentResolver.class});
            declaredMethod.setAccessible(true);
            boolean booleanValue = ((Boolean) declaredMethod.invoke((Object) null, new Object[]{context.getContentResolver()})).booleanValue();
            try {
                h.a("isUploadDebugLogEnable: " + booleanValue);
                return booleanValue;
            } catch (Exception e2) {
                Exception exc = e2;
                z = booleanValue;
                e = exc;
            }
        } catch (Exception e3) {
            e = e3;
            h.b("BS", "isUploadDebugLogEnable exception:", (Throwable) e);
            return z;
        }
    }

    public static void d() {
        d = false;
    }

    public static boolean e() {
        try {
            if (q.d(c.a())) {
                return ((Boolean) Class.forName("miui.os.Build").getField("IS_CTA_BUILD").get((Object) null)).booleanValue();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean h() {
        try {
            if (!q.d(c.a())) {
                return false;
            }
            Object obj = Class.forName("miui.os.Build").getField("IS_ALPHA_BUILD").get((Object) null);
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
            return false;
        } catch (Exception unused) {
        }
    }

    private static boolean i() {
        try {
            if (!q.d(c.a())) {
                return false;
            }
            Object obj = Class.forName("miui.os.Build").getField("IS_DEVELOPMENT_VERSION").get((Object) null);
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
            return false;
        } catch (Exception unused) {
        }
    }

    private static boolean j() {
        try {
            if (!q.d(c.a())) {
                return false;
            }
            Object obj = Class.forName("miui.os.Build").getField("IS_STABLE_VERSION").get((Object) null);
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
            return false;
        } catch (Exception unused) {
        }
    }

    public static boolean f() {
        try {
            return ((Boolean) Class.forName("miui.os.Build").getField("IS_INTERNATIONAL_BUILD").get((Object) null)).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static String g() {
        if (j()) {
            return "S";
        }
        if (i()) {
            return Field.h;
        }
        return h() ? "A" : "";
    }
}
