package com.xiaomi.youpin.hawkeye.timecounter;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Printer;
import com.xiaomi.youpin.hawkeye.task.TaskManager;
import com.xiaomi.youpin.hawkeye.utils.HLog;

public class HPrinterParser implements Printer {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23378a = "HPrinterParser";
    public static final int b = 100;
    public static final int c = 101;
    private static final String d = ">>>>> Dispatching";
    private static final String e = "<<<<< Finished";
    private static final String f = "android.app.ActivityThread$H";
    private static boolean h;
    private int g;
    private BlockTask i = ((BlockTask) TaskManager.a().a("BlockTask"));
    private ActivityLaunchTask j = ((ActivityLaunchTask) TaskManager.a().a("ActivityLaunchTask"));

    public static void a() {
        if (h) {
            HLog.b(f23378a, "HPrinterParser was bind");
            return;
        }
        Looper.getMainLooper().setMessageLogging(new HPrinterParser());
        h = true;
    }

    public static void b() {
        if (h) {
            Looper.getMainLooper().setMessageLogging((Printer) null);
            h = false;
        }
    }

    public void println(String str) {
        a(str);
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith(d)) {
                if (d()) {
                    this.i.b();
                }
                if (c() && str.contains(f)) {
                    if (str.endsWith(String.valueOf(101))) {
                        this.j.f();
                        this.g = 101;
                        HLog.b(f23378a, "**** parse log  :  pause ");
                    } else if (str.endsWith(String.valueOf(100))) {
                        this.j.g();
                        this.g = 100;
                        HLog.b(f23378a, "**** parse log  :  launch ");
                    }
                }
            } else if (str.startsWith(e)) {
                if (d()) {
                    this.i.f();
                }
                if (c() && str.contains(f)) {
                    if (this.g == 101) {
                        this.j.h();
                        HLog.b(f23378a, "**** parse log  :  pause end ");
                    } else if (this.g == 100) {
                        this.j.i();
                        HLog.b(f23378a, "**** parse log  :  launch end ");
                    }
                    this.g = 0;
                }
            }
        }
    }

    private boolean c() {
        return this.j != null && this.j.e();
    }

    private boolean d() {
        return this.i != null && this.i.e();
    }
}
