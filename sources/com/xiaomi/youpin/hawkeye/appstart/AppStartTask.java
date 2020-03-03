package com.xiaomi.youpin.hawkeye.appstart;

import com.xiaomi.youpin.hawkeye.entity.StatType;
import com.xiaomi.youpin.hawkeye.task.ApmTaskConstants;
import com.xiaomi.youpin.hawkeye.task.BaseTask;
import com.xiaomi.youpin.hawkeye.utils.TimeUtils;

public class AppStartTask extends BaseTask {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1578a = "HawkEye";
    public static final String b = "appstart_key";

    public String a() {
        return ApmTaskConstants.f23371a;
    }

    public static void b() {
        TimeUtils.a(b);
    }

    public void c() {
        if (e()) {
            long c = TimeUtils.c(b);
            long b2 = TimeUtils.b(b);
            if (b2 >= 200 && b2 <= 10000) {
                AppStartInfo appStartInfo = new AppStartInfo(c, b2, 0);
                appStartInfo.mStatType = StatType.APP_START;
                appStartInfo.mTaskName = a();
                b(appStartInfo);
            }
        }
    }
}
