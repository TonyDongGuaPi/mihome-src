package com.hianalytics.android.v1;

import android.content.Context;
import android.os.Handler;
import com.hianalytics.android.b.a.a;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HiAnalytics {

    /* renamed from: a  reason: collision with root package name */
    private static ScheduledExecutorService f5798a = Executors.newScheduledThreadPool(1);
    private static boolean b = false;
    private static int c = 0;

    public static void a(int i) {
        if (i >= 0) {
            a.a(i);
        }
    }

    public static void a(long j) {
        if (j >= 30) {
            a.d(Long.valueOf(j * 60));
        }
    }

    public static void a(Context context) {
        if (context == null) {
            a.h();
            return;
        }
        Handler g = a.g();
        if (g != null) {
            g.post(new d(context, 0, System.currentTimeMillis()));
        }
        a.h();
    }

    public static void a(Context context, int i) {
        if (i == 0) {
            try {
                b = false;
                f5798a.shutdown();
                f5798a = Executors.newScheduledThreadPool(1);
            } catch (Exception e) {
                e.getMessage();
                a.h();
                e.printStackTrace();
            }
        } else if (!b) {
            b = true;
            a.h();
            long j = (long) i;
            f5798a.scheduleAtFixedRate(new d(context, 2, System.currentTimeMillis()), j, j, TimeUnit.SECONDS);
        } else if (b && i != c) {
            c = i;
            a.h();
            f5798a.shutdown();
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
            f5798a = newScheduledThreadPool;
            long j2 = (long) i;
            newScheduledThreadPool.scheduleAtFixedRate(new d(context, 2, System.currentTimeMillis()), j2, j2, TimeUnit.SECONDS);
        }
    }

    public static void a(Context context, String str, String str2) {
        if (context == null) {
            a.h();
        } else if (str == null || str.equals("")) {
            a.h();
        } else if (str2 == null || str2.equals("")) {
            a.h();
        } else {
            Handler g = a.g();
            if (g != null) {
                g.post(new a(context, str, str2, System.currentTimeMillis()));
            }
            a.h();
        }
    }

    public static void a(Long l) {
        if (l.longValue() >= 30) {
            a.a(l);
        }
    }

    public static void a(boolean z) {
        a.a(z);
    }

    public static void b(Context context) {
        if (context == null) {
            a.h();
            return;
        }
        Handler g = a.g();
        if (g != null) {
            g.post(new d(context, 1, System.currentTimeMillis()));
        }
        a.h();
    }

    public static void b(Long l) {
        if (l.longValue() >= 24) {
            a.b(Long.valueOf(l.longValue() * 60 * 60));
        }
    }

    public static void c(Context context) {
        if (context == null) {
            a.h();
            return;
        }
        Handler g = a.g();
        if (g != null) {
            g.post(new d(context, 2, System.currentTimeMillis()));
        }
        a.h();
    }

    public static void c(Long l) {
        if (l.longValue() >= 1000) {
            a.c(l);
        }
    }
}
