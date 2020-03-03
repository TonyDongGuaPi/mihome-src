package com.miui.deviceid;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

public class IdentifierManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8192a = "IdentifierManager";
    private static Object b;
    private static Class<?> c;
    private static Method d;
    private static Method e;
    private static Method f;
    private static Method g;

    static {
        try {
            c = Class.forName("com.android.id.impl.IdProviderImpl");
            b = c.newInstance();
            d = c.getMethod("getUDID", new Class[]{Context.class});
            e = c.getMethod("getOAID", new Class[]{Context.class});
            f = c.getMethod("getVAID", new Class[]{Context.class});
            g = c.getMethod("getAAID", new Class[]{Context.class});
        } catch (Exception e2) {
            Log.e(f8192a, "reflect exception!", e2);
        }
    }

    public static boolean a() {
        return (c == null || b == null) ? false : true;
    }

    public static String a(Context context) {
        return a(context, d);
    }

    public static String b(Context context) {
        return a(context, e);
    }

    public static String c(Context context) {
        return a(context, f);
    }

    public static String d(Context context) {
        return a(context, g);
    }

    private static String a(Context context, Method method) {
        if (b == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(b, new Object[]{context});
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Exception e2) {
            Log.e(f8192a, "invoke exception!", e2);
            return null;
        }
    }
}
