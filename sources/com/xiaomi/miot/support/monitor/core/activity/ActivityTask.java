package com.xiaomi.miot.support.monitor.core.activity;

import com.xiaomi.miot.support.monitor.core.tasks.BaseTask;

public class ActivityTask extends BaseTask {
    public String a() {
        return "activity";
    }

    public void b() {
        try {
            super.b();
            if (!InstrumentationHooker.b()) {
                this.d = false;
                this.c = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
