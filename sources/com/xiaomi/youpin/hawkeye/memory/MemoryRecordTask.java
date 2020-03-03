package com.xiaomi.youpin.hawkeye.memory;

import android.os.Debug;
import com.xiaomi.youpin.hawkeye.HawkEye;
import com.xiaomi.youpin.hawkeye.task.ApmTaskConstants;
import com.xiaomi.youpin.hawkeye.task.BaseTask;
import com.xiaomi.youpin.hawkeye.utils.AsyncThreadTask;
import com.xiaomi.youpin.hawkeye.utils.ProcessUtils;
import java.util.concurrent.Future;

public class MemoryRecordTask extends BaseTask {

    /* renamed from: a  reason: collision with root package name */
    private Future f23363a;
    private Runnable b = new Runnable() {
        public void run() {
            MemoryRecordTask.this.b();
        }
    };

    public String a() {
        return ApmTaskConstants.f;
    }

    public void aa_() {
        super.aa_();
        if (e()) {
            this.f23363a = AsyncThreadTask.a(this.b, HawkEye.c().g());
        }
    }

    public void b() {
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        MemoryRecordInfo memoryRecordInfo = new MemoryRecordInfo();
        memoryRecordInfo.mTaskName = a();
        memoryRecordInfo.processName = ProcessUtils.a();
        memoryRecordInfo.dalvikPss = memoryInfo.dalvikPss;
        memoryRecordInfo.nativePss = memoryInfo.nativePss;
        memoryRecordInfo.otherPss = memoryInfo.otherPss;
        memoryRecordInfo.totalPss = memoryInfo.getTotalPss();
        b(memoryRecordInfo);
    }

    public void d() {
        super.d();
        if (this.f23363a != null) {
            this.f23363a.cancel(true);
        }
    }
}
