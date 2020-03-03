package com.alipay.deviceid.module.x;

import android.content.Context;

public final class by {
    public static synchronized void a(Context context, String str, String str2) {
        synchronized (by.class) {
            c(context, "hash" + str, str2);
        }
    }

    public static synchronized String a(Context context, String str) {
        String a2;
        synchronized (by.class) {
            a2 = cb.a(context, "alipay_device_id_storage", "hash" + str);
        }
        return a2;
    }

    public static synchronized void b(Context context, String str, String str2) {
        synchronized (by.class) {
            c(context, "apdidtoekn" + str, str2);
        }
    }

    public static synchronized String b(Context context, String str) {
        String a2;
        synchronized (by.class) {
            a2 = cb.a(context, "alipay_device_id_storage", "apdidtoekn" + str);
        }
        return a2;
    }

    private static void c(Context context, String str, String str2) {
        cb.a(context, "alipay_device_id_storage", str, str2);
    }

    public static synchronized void a() {
        synchronized (by.class) {
        }
    }
}
