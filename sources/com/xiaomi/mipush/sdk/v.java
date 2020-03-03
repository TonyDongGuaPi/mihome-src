package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ai;
import com.xiaomi.push.az;
import com.xiaomi.push.bf;
import com.xiaomi.push.ht;
import com.xiaomi.push.r;
import com.xiaomi.push.service.ah;
import java.lang.Thread;

class v implements Thread.UncaughtExceptionHandler {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final Object f11565a = new Object();
    private static final String[] b = {"com.xiaomi.channel.commonutils", "com.xiaomi.common.logger", "com.xiaomi.measite.smack", "com.xiaomi.metoknlp", "com.xiaomi.mipush.sdk", "com.xiaomi.network", "com.xiaomi.push", "com.xiaomi.slim", "com.xiaomi.smack", "com.xiaomi.stats", "com.xiaomi.tinyData", "com.xiaomi.xmpush.thrift", "com.xiaomi.clientreport"};
    /* access modifiers changed from: private */
    public Context c;
    private SharedPreferences d;
    private Thread.UncaughtExceptionHandler e;

    public v(Context context) {
        this(context, Thread.getDefaultUncaughtExceptionHandler());
    }

    public v(Context context, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.c = context;
        this.e = uncaughtExceptionHandler;
    }

    private void a(Throwable th) {
        String c2 = c(th);
        if (!TextUtils.isEmpty(c2)) {
            String b2 = b(th);
            if (!TextUtils.isEmpty(b2)) {
                s.a(this.c).a(c2, b2);
                if (b()) {
                    c();
                }
            }
        }
    }

    private boolean a(boolean z, String str) {
        for (String contains : b) {
            if (str.contains(contains)) {
                return true;
            }
        }
        return z;
    }

    private String b(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(3, stackTrace.length); i++) {
            sb.append(stackTrace[i].toString() + "\r\n");
        }
        String sb2 = sb.toString();
        return TextUtils.isEmpty(sb2) ? "" : bf.a(sb2);
    }

    private boolean b() {
        this.d = this.c.getSharedPreferences("mipush_extra", 4);
        if (az.f(this.c)) {
            if (!ah.a(this.c).a(ht.Crash4GUploadSwitch.a(), true)) {
                return false;
            }
            return ((float) Math.abs((System.currentTimeMillis() / 1000) - this.d.getLong("last_crash_upload_time_stamp", 0))) >= ((float) Math.max(3600, ah.a(this.c).a(ht.Crash4GUploadFrequency.a(), 3600))) * 0.9f;
        } else if (!az.e(this.c)) {
            return true;
        } else {
            return Math.abs((System.currentTimeMillis() / 1000) - this.d.getLong("last_crash_upload_time_stamp", 0)) >= ((long) Math.max(60, ah.a(this.c).a(ht.CrashWIFIUploadFrequency.a(), 1800)));
        }
    }

    private String c(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder(th.toString());
        sb.append("\r\n");
        boolean z = false;
        for (StackTraceElement stackTraceElement : stackTrace) {
            String stackTraceElement2 = stackTraceElement.toString();
            z = a(z, stackTraceElement2);
            sb.append(stackTraceElement2);
            sb.append("\r\n");
        }
        return z ? sb.toString() : "";
    }

    private void c() {
        ai.a(this.c).a((Runnable) new w(this));
    }

    /* access modifiers changed from: private */
    public void d() {
        this.d = this.c.getSharedPreferences("mipush_extra", 4);
        SharedPreferences.Editor edit = this.d.edit();
        edit.putLong("last_crash_upload_time_stamp", System.currentTimeMillis() / 1000);
        r.a(edit);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        a(th);
        synchronized (f11565a) {
            try {
                f11565a.wait(3000);
            } catch (InterruptedException e2) {
                b.a((Throwable) e2);
            }
        }
        if (this.e != null) {
            this.e.uncaughtException(thread, th);
            return;
        }
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}
