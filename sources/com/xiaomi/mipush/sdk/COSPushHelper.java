package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.b;

public class COSPushHelper {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f11503a = false;
    private static long b;

    public static void a(Context context, String str) {
        h.a(context, d.ASSEMBLE_PUSH_COS, str);
    }

    public static void a(Intent intent) {
        h.a(intent);
    }

    public static synchronized void a(boolean z) {
        synchronized (COSPushHelper.class) {
            f11503a = z;
        }
    }

    public static boolean a() {
        return f11503a;
    }

    public static boolean a(Context context) {
        return h.b(context);
    }

    public static void b(Context context) {
        AbstractPushManager c = e.a(context).c(d.ASSEMBLE_PUSH_COS);
        if (c != null) {
            b.a("ASSEMBLE_PUSH :  register cos when network change!");
            c.a();
        }
    }

    public static void b(Context context, String str) {
    }

    public static void c(Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!a()) {
            return;
        }
        if (b <= 0 || b + 300000 <= elapsedRealtime) {
            b = elapsedRealtime;
            b(context);
        }
    }

    public static void c(Context context, String str) {
    }
}
