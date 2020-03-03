package com.xiaomi.smarthome.frame.crash;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

public class FrameCrashHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1527a = 5000;
    private Context b;
    private Thread.UncaughtExceptionHandler c = Thread.getDefaultUncaughtExceptionHandler();

    public FrameCrashHandler(Context context) {
        this.b = context;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String className;
        String methodName;
        Throwable th2 = th;
        if (th2 != null) {
            th.printStackTrace();
            StringWriter stringWriter = new StringWriter();
            th2.printStackTrace(new PrintWriter(stringWriter));
            String obj = stringWriter.toString();
            if (obj.length() > 5000) {
                obj = obj.substring(0, 5000);
            }
            String str = obj;
            if (th2 == null) {
                className = "";
            } else {
                className = th.getStackTrace()[0].getClassName();
            }
            String str2 = className;
            if (th2 == null) {
                methodName = "";
            } else {
                methodName = th.getStackTrace()[0].getMethodName();
            }
            String str3 = methodName;
            String g = ProcessUtil.g(this.b);
            if (!TextUtils.isEmpty(g) && g.startsWith("com.xiaomi.smarthome:")) {
                g = g.replace("com.xiaomi.smarthome:", "");
            }
            FrameCrashApi.a().a(this.b, str2, str3, str, g, HostSetting.j, 0, 0);
            if (this.c != null) {
                this.c.uncaughtException(thread, th2);
            }
        }
    }
}
