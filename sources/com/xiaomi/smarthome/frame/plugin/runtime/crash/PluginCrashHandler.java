package com.xiaomi.smarthome.frame.plugin.runtime.crash;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.crash.FrameCrashApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;

public class PluginCrashHandler implements Thread.UncaughtExceptionHandler {
    public static final int MAX_CRASH_LOG_LENGTH = 5000;
    private Context mAppContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

    public PluginCrashHandler(Context context) {
        this.mAppContext = context;
    }

    public static void handlePluginException(Throwable th, long j, long j2) {
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            String obj = stringWriter.toString();
            if (obj.length() > 5000) {
                obj.substring(0, 5000);
            }
            sendCrashLogInternal(FrameManager.b().c(), th, j, j2);
        }
    }

    private static void sendCrashLogInternal(Context context, Throwable th, long j, long j2) {
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
            String str7 = str6;
            String g = ProcessUtil.g(context);
            if (!TextUtils.isEmpty(g) && g.startsWith("com.xiaomi.smarthome:")) {
                g = g.replace("com.xiaomi.smarthome:", "");
            }
            FrameCrashApi.a().a(context, str3, str4, str7, g, HostSetting.j, j, j2);
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
            if (xmPluginPackage != null) {
                long j2 = 0;
                if (xmPluginPackage != null) {
                    j2 = xmPluginPackage.getPluginId();
                    j = xmPluginPackage.getPackageId();
                } else {
                    j = 0;
                }
                handlePluginException(th, j2, j);
            }
        }
        if (this.mDefaultHandler != null) {
            this.mDefaultHandler.uncaughtException(thread, th);
        }
    }
}
