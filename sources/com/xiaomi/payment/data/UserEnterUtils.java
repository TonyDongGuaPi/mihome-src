package com.xiaomi.payment.data;

import android.content.Context;
import com.mibi.common.data.ReflectUtils;
import java.lang.reflect.Method;
import miuipub.os.Build;

public class UserEnterUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12235a = "com.xiaomi.payment";

    public static boolean a(Context context) {
        return true;
    }

    public static boolean b(Context context) {
        Class<?> a2;
        Method a3;
        if (!Build.V || (a2 = ReflectUtils.a("android.provider.MiuiSettings$Privacy")) == null || (a3 = ReflectUtils.a(a2, "isEnabled", (Class<?>[]) new Class[]{Context.class, String.class})) == null) {
            return true;
        }
        return ((Boolean) ReflectUtils.a(a3, (Object) a2, context, "com.xiaomi.payment")).booleanValue();
    }

    public static void a(Context context, boolean z) {
        Class<?> a2;
        Method a3;
        if (Build.V && (a2 = ReflectUtils.a("android.provider.MiuiSettings$Privacy")) != null && (a3 = ReflectUtils.a(a2, "setEnabled", (Class<?>[]) new Class[]{Context.class, String.class, Boolean.TYPE})) != null) {
            a3.setAccessible(true);
            ReflectUtils.a(a3, (Object) a2, context, "com.xiaomi.payment", Boolean.valueOf(z));
        }
    }
}
