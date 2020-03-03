package com.xiaomi.miot.support.monitor.crash;

import android.content.Context;
import com.xiaomi.miot.support.monitor.utils.LogX;
import java.lang.Thread;

public class MiotMonitorCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String c = "MiotMonitorCrashHandler";

    /* renamed from: a  reason: collision with root package name */
    protected Context f11477a;
    protected Thread.UncaughtExceptionHandler b;

    private MiotMonitorCrashHandler() {
    }

    public static MiotMonitorCrashHandler a() {
        return Holder.f11478a;
    }

    static class Holder {

        /* renamed from: a  reason: collision with root package name */
        static MiotMonitorCrashHandler f11478a = new MiotMonitorCrashHandler();

        Holder() {
        }
    }

    public void a(Context context) {
        this.f11477a = context.getApplicationContext();
        this.b = Thread.getDefaultUncaughtExceptionHandler();
    }

    public void uncaughtException(Thread thread, Throwable th) {
        LogX.d(c, "uncaughtException: " + th.toString());
    }
}
