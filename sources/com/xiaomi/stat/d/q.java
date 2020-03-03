package com.xiaomi.stat.d;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.lang.reflect.Field;

public class q {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23064a = "tv";
    private static final String b = "SystemUtil";
    private static final String c = "box";
    private static final String d = "tvbox";
    private static final String e = "projector";

    public static boolean a(Context context) {
        try {
            if ((context.getPackageManager().getPackageInfo("com.xiaomi.mitv.services", 0).applicationInfo.flags & 1) != 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            k.d("Is not Mi Tv system!");
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            if (!a(context) || !TextUtils.equals(a("ro.mitv.product.overseas"), "true")) {
                return false;
            }
            return true;
        } catch (Exception e2) {
            k.d(b, "isMiTvIntlBuild", e2);
            return false;
        }
    }

    public static String a(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
        } catch (Exception e2) {
            k.d(b, "reflectGetSystemProperties exception", e2);
            return "";
        }
    }

    public static String a() {
        String str;
        try {
            Class<?> cls = Class.forName("mitv.common.ConfigurationManager");
            int parseInt = Integer.parseInt(String.valueOf(cls.getMethod("getProductCategory", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(cls, new Object[0]), new Object[0])));
            Class<?> cls2 = Class.forName("mitv.tv.TvContext");
            if (parseInt == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MITV")))) {
                str = f23064a;
            } else if (parseInt == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MIBOX")))) {
                str = c;
            } else if (parseInt == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MITVBOX")))) {
                str = d;
            } else if (parseInt != Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MIPROJECTOR")))) {
                return "";
            } else {
                str = e;
            }
            return str;
        } catch (Exception e2) {
            k.d(b, "getMiTvProductCategory exception", e2);
            return "";
        }
    }

    private static <T> T a(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get((Object) null);
        } catch (Exception e2) {
            k.d(b, "getStaticVariableValue exception", e2);
            return null;
        }
    }
}
