package com.xiaomi.miot.support.monitor.core.memory;

import android.os.Debug;
import com.xiaomi.miot.support.monitor.Env;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.core.MiotDataStorage;
import com.xiaomi.miot.support.monitor.core.tasks.BaseTask;
import com.xiaomi.miot.support.monitor.core.tasks.MiotApmTask;
import com.xiaomi.miot.support.monitor.utils.AndroidUtils;
import com.xiaomi.miot.support.monitor.utils.AsyncThreadTask;
import com.xiaomi.miot.support.monitor.utils.LogX;
import java.util.Random;

public class MemoryTask extends BaseTask {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11463a = "MemoryTask";
    /* access modifiers changed from: private */
    public Runnable e = new Runnable() {
        public void run() {
            if (MemoryTask.this.f()) {
                if (!AndroidUtils.a(MiotMonitorManager.a().h())) {
                    AsyncThreadTask.a();
                    AsyncThreadTask.a(MemoryTask.this.e, 60000);
                } else if (MiotDataStorage.a().c() == 0) {
                    Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
                    Debug.getMemoryInfo(memoryInfo);
                    MiotDataStorage.a().a(memoryInfo.getTotalPss());
                }
            }
        }
    };

    public String a() {
        return MiotApmTask.d;
    }

    public void b() {
        try {
            super.b();
            int nextInt = new Random().nextInt(5);
            LogX.d(Env.c, "memeory start: " + nextInt);
            AsyncThreadTask.a(this.e, (long) ((nextInt + 1) * 60 * 1000));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
