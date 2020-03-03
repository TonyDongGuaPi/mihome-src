package com.xiaomi.smarthome.library.deviceId;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

public class IdentifierManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19096a = "IdentifierManager";
    private static Object b;
    private static Class<?> c;
    private static Method d;

    public static boolean a() {
        return (c == null || b == null) ? false : true;
    }

    public static String a(Context context) {
        return a(context, d);
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
        } catch (Exception unused) {
            Log.e(f19096a, "invoke exception!");
            return null;
        }
    }

    static {
        try {
            c = Class.forName("com.android.id.impl.IdProviderImpl");
            b = c.newInstance();
            d = c.getMethod("getOAID", new Class[]{Context.class});
        } catch (Exception unused) {
            Log.e(f19096a, "reflect exception!");
        }
    }
}
