package com.mi.mistatistic.sdk;

import android.content.Context;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.mistatistic.sdk.controller.Logger;
import com.xiaomi.stat.c.c;
import miuipub.reflect.Field;

public class BuildSetting {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7314a = "BS";
    private static boolean b = false;
    private static boolean c = false;
    private static boolean d = false;
    private static boolean e = false;

    public static void a(boolean z) {
        b = z;
    }

    public static boolean a() {
        return b;
    }

    public static boolean b() {
        return c;
    }

    public static boolean c() {
        return d;
    }

    public static void b(boolean z) {
        c = z;
    }

    public static void c(boolean z) {
        d = z;
    }

    public static boolean d() {
        return e;
    }

    public static void d(boolean z) {
        e = z;
    }

    public static boolean a(Context context) {
        try {
            context.getPackageManager().getPackageInfo(c.f23036a, 0);
            return true;
        } catch (Exception e2) {
            Logger.b(f7314a, "cannot get pkginfo com.xiaomi.xmsf, not miui.", (Throwable) e2);
            return false;
        }
    }

    private static boolean f() {
        try {
            if (!a(ApplicationContextHolder.a())) {
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

    private static boolean g() {
        try {
            if (!a(ApplicationContextHolder.a())) {
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

    private static boolean h() {
        try {
            if (!a(ApplicationContextHolder.a())) {
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

    public static String e() {
        if (h()) {
            return "S";
        }
        if (g()) {
            return Field.h;
        }
        return f() ? "A" : "";
    }
}
