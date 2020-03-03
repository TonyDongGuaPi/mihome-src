package com.xiaomi.smarthome.framework.crash;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.coloros.mcssdk.PushManager;
import com.org.smarthome_activity.R;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.acp.ACPUtil;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.HotFixManager;
import com.xiaomi.smarthome.core.server.internal.plugin.NotificationChannelCreator;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.commonapi.CommonApiV2;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.CommonUtil;
import com.xiaomi.smarthome.library.common.util.ListCache;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainCrashHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1535a = 10000;
    private static Map<String, String> d = new LinkedHashMap();
    private static ListCache<String> e = new ListCache<>(30);
    private Context b;
    private Thread.UncaughtExceptionHandler c = Thread.getDefaultUncaughtExceptionHandler();

    public MainCrashHandler(Context context) {
        this.b = context;
    }

    public static void a(Throwable th) {
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            String obj = stringWriter.toString();
            if (obj.length() > 10000) {
                obj.substring(0, 10000);
            }
            if (th != null) {
                th.getStackTrace()[0].getClassName();
            }
            if (th != null) {
                th.getStackTrace()[0].getMethodName();
            }
            a(CommonApplication.getAppContext(), th, 0, 0, (AsyncResponseCallback<Void>) null);
            MyLog.a(th);
        }
    }

    public static void a(Throwable th, long j, long j2) {
        a(CommonApplication.getAppContext(), th, j, j2, (AsyncResponseCallback<Void>) null);
    }

    private static void a(Context context, Throwable th, long j, long j2, AsyncResponseCallback<Void> asyncResponseCallback) {
        String methodName;
        Throwable th2 = th;
        String className = th2 == null ? "" : th.getStackTrace()[0].getClassName();
        if (th2 == null) {
            methodName = "";
        } else {
            methodName = th.getStackTrace()[0].getMethodName();
        }
        String str = methodName;
        StringBuilder sb = new StringBuilder();
        a(sb);
        if (th2 instanceof OutOfMemoryError) {
            Context context2 = context;
            sb.append(CommonUtil.a(context, GlobalSetting.q || GlobalSetting.w));
        } else {
            Context context3 = context;
        }
        StringWriter stringWriter = new StringWriter();
        th2.printStackTrace(new PrintWriter(stringWriter));
        sb.append(stringWriter.toString());
        String sb2 = sb.toString();
        if (sb2.length() > 10000) {
            sb2 = sb2.substring(sb2.length() - 10000, sb2.length() - 1);
        }
        CommonApiV2.a().a(context, j, j2, className, str, sb2, asyncResponseCallback, 3000);
    }

    public static void a(String str) {
        e.b(str);
    }

    public static StringBuilder a(StringBuilder sb) {
        sb.append("===activity tracking info start===\n");
        for (String str : e.e()) {
            sb.append(str + "\n");
        }
        sb.append("===activity tracking info end===\n");
        return sb;
    }

    private void a(Context context) {
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks != null && runningTasks.size() > 0) {
                ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
                Map<String, String> map = d;
                map.put("t_acti_num", runningTaskInfo.numActivities + "");
                d.put("t_base_acti", runningTaskInfo.baseActivity.getClassName());
                d.put("t_top_acti", runningTaskInfo.topActivity.getClassName());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        long j;
        a(CommonApplication.getAppContext());
        CommonApplication.getGlobalHandler().removeCallbacks(CommonApplication.getApplication().mResetRepeatedCrashRunnable);
        HotFixManager.a();
        if (th != null) {
            th.printStackTrace();
            XmPluginPackage xmPluginPackage = null;
            PluginHostActivity topPluginHostActivity = PluginHostActivity.getTopPluginHostActivity();
            if (topPluginHostActivity != null) {
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
                PluginCrashHandler.a(th, j2, j);
            } else {
                a(th);
            }
        } else {
            a(th);
        }
        try {
            String message = th.getMessage();
            if (th != null && (th instanceof SecurityException) && !TextUtils.isEmpty(message) && message.equalsIgnoreCase("Permission denied (missing INTERNET permission?)")) {
                a();
            }
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            ACPUtil.a(CommonApplication.getAppContext(), stringWriter.toString());
        } catch (Exception unused) {
        }
        if (this.c != null) {
            this.c.uncaughtException(thread, th);
        }
    }

    private void a() {
        Notification.Builder smallIcon = new Notification.Builder(CommonApplication.getAppContext()).setContentTitle("程序运行异常").setContentText("请给米家打开网络权限").setSmallIcon(R.drawable.notify_icon);
        NotificationManager notificationManager = (NotificationManager) CommonApplication.getAppContext().getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                smallIcon.setChannelId(NotificationChannelCreator.b(notificationManager));
            }
            notificationManager.notify(4321, smallIcon.build());
        }
    }
}
