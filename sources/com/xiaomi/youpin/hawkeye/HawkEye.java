package com.xiaomi.youpin.hawkeye;

import android.app.Application;
import android.content.Context;
import com.xiaomi.youpin.hawkeye.appstart.AppStartTask;
import com.xiaomi.youpin.hawkeye.config.HawkEyeConfig;
import com.xiaomi.youpin.hawkeye.network.NetWorkRecordTask;
import com.xiaomi.youpin.hawkeye.task.ITask;
import com.xiaomi.youpin.hawkeye.task.TaskManager;
import com.xiaomi.youpin.hawkeye.timecounter.ActivityLaunchTask;
import com.xiaomi.youpin.hawkeye.timecounter.BlockTask;
import com.xiaomi.youpin.hawkeye.upload.UploadManager;
import com.xiaomi.youpin.hawkeye.utils.HLog;

public class HawkEye {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23350a = "HawkEye";
    private static boolean b = false;
    private static HawkEyeConfig c;
    private static Application d;

    public static void a(Application application, HawkEyeConfig hawkEyeConfig) {
        if (b) {
            HLog.b("HawkEye", "hawkeye was initialize");
            return;
        }
        d = application;
        c = hawkEyeConfig;
        e();
        b = true;
    }

    public static boolean a() {
        return b;
    }

    private static void e() {
        Env.f23349a = c.a();
        ActivityManager.a(d);
        UploadManager.a().a((Context) d);
    }

    public static void b() {
        TaskManager.a().a((ITask) new AppStartTask());
        TaskManager.a().a((ITask) new BlockTask());
        TaskManager.a().a((ITask) new ActivityLaunchTask());
        TaskManager.a().a((ITask) new NetWorkRecordTask());
        TaskManager.a().b();
    }

    public static HawkEyeConfig c() {
        return c;
    }

    public static Context d() {
        return d;
    }
}
