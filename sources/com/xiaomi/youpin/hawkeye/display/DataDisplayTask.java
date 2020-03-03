package com.xiaomi.youpin.hawkeye.display;

import com.xiaomi.youpin.hawkeye.ActivityManager;
import com.xiaomi.youpin.hawkeye.display.ui.FloatViewManager;
import com.xiaomi.youpin.hawkeye.task.ApmTaskConstants;
import com.xiaomi.youpin.hawkeye.task.BaseTask;

public class DataDisplayTask extends BaseTask implements ActivityManager.AppStatusListener {
    public String a() {
        return ApmTaskConstants.g;
    }

    public void aa_() {
        super.aa_();
        if (e()) {
            ActivityManager.a((ActivityManager.AppStatusListener) this);
        }
    }

    public void d() {
        super.d();
        ActivityManager.b(this);
    }

    public void ab_() {
        if (e()) {
            FloatViewManager.a().c();
        }
    }

    public void b() {
        FloatViewManager.a().d();
    }
}
