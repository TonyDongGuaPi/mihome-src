package com.xiaomi.youpin.hawkeye.task;

import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.storage.StorageManager;
import com.xiaomi.youpin.hawkeye.utils.HLog;

public abstract class BaseTask implements ITask {
    public static final String c = "BaseTask";
    protected boolean d = false;

    public void aa_() {
        HLog.b("BaseTask", a() + "   task is working ");
    }

    public boolean e() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean b(BaseInfo baseInfo) {
        baseInfo.timestamp = System.currentTimeMillis() / 1000;
        return StorageManager.a().a(baseInfo);
    }

    public void d() {
        this.d = false;
        HLog.b("BaseTask", a() + "   task is stop ");
    }
}
