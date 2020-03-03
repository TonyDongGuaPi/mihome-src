package com.xiaomi.smarthome.framework.crash;

import android.content.Context;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.commonapi.CommonApiV2;
import com.xiaomi.smarthome.framework.log.MyLog;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;

public class PluginCrashHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1536a = 5000;
    private Context b;
    private Thread.UncaughtExceptionHandler c = Thread.getDefaultUncaughtExceptionHandler();

    public PluginCrashHandler(Context context) {
        this.b = context;
    }

    public static void a(Throwable th, long j, long j2) {
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            String obj = stringWriter.toString();
            if (obj.length() > 5000) {
                obj.substring(0, 5000);
            }
            if (th != null) {
                th.getStackTrace()[0].getClassName();
            }
            if (th != null) {
                th.getStackTrace()[0].getMethodName();
            }
            a(CommonApplication.getAppContext(), th, j, j2, (AsyncResponseCallback<Void>) null);
            MyLog.a(th);
        }
    }

    private static void a(Context context, Throwable th, long j, long j2, AsyncResponseCallback<Void> asyncResponseCallback) {
        Throwable th2 = th;
        if (th2 != null) {
            String str = "";
            String str2 = "";
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                str = stackTrace[0].getClassName();
                str2 = stackTrace[0].getMethodName();
            }
            String str3 = str;
            String str4 = str2;
            StringWriter stringWriter = new StringWriter();
            th2.printStackTrace(new PrintWriter(stringWriter));
            String str5 = "===plugin crash===\npluginId=" + j + "\npkgId=" + j2 + "\n";
            try {
                str5 = str5 + "userId=" + CoreApi.a().s() + "\n";
            } catch (Exception unused) {
            }
            String str6 = str5 + stringWriter.toString();
            if (str6.length() > 5000) {
                str6 = str6.substring(0, 5000);
            }
            CommonApiV2.a().a(context, j, j2, str3, str4, str6, asyncResponseCallback);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        XmPluginPackage xmPluginPackage;
        long j;
        PluginHostActivity topPluginHostActivity;
        if (th != null) {
            th.printStackTrace();
            try {
                StackTraceElement[] stackTrace = th.getStackTrace();
                ArrayList arrayList = new ArrayList();
                int i = 0;
                int length = stackTrace.length;
                while (i < length && i < 6) {
                    arrayList.add(stackTrace[i].getClassName());
                    i++;
                }
                xmPluginPackage = PluginRuntimeManager.getInstance().getXmPluginPackageByCrashClassName(arrayList);
            } catch (Exception unused) {
                xmPluginPackage = null;
            }
            if (xmPluginPackage == null && (topPluginHostActivity = PluginHostActivity.getTopPluginHostActivity()) != null) {
                xmPluginPackage = topPluginHostActivity.getXmPluginPackage();
            }
            long j2 = 0;
            if (xmPluginPackage != null) {
                if (xmPluginPackage != null) {
                    j2 = xmPluginPackage.getPluginId();
                    j = xmPluginPackage.getPackageId();
                } else {
                    j = 0;
                }
                a(th, j2, j);
            } else {
                a(th, 0, 0);
            }
        }
        if (this.c != null) {
            this.c.uncaughtException(thread, th);
        }
    }
}
