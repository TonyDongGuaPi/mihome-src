package com.xiaomi.youpin.hawkeye.fps;

import android.view.Choreographer;
import com.xiaomi.youpin.hawkeye.display.DataDisplayTask;
import com.xiaomi.youpin.hawkeye.display.ui.FloatViewManager;
import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.task.ApmTaskConstants;
import com.xiaomi.youpin.hawkeye.task.BaseTask;
import com.xiaomi.youpin.hawkeye.task.TaskManager;

public class FPSRecordTask extends BaseTask {

    /* renamed from: a  reason: collision with root package name */
    private static final float f23361a = 1.0E9f;
    /* access modifiers changed from: private */
    public long b;
    /* access modifiers changed from: private */
    public int e;
    private Choreographer.FrameCallback f = new Choreographer.FrameCallback() {
        public void doFrame(long j) {
            if (!(FPSRecordTask.this.b == 0 || j - FPSRecordTask.this.b == 0)) {
                int unused = FPSRecordTask.this.e = Math.round(FPSRecordTask.f23361a / ((float) (j - FPSRecordTask.this.b)));
            }
            long unused2 = FPSRecordTask.this.b = j;
            Choreographer.getInstance().postFrameCallback(this);
            FPSRecordTask.this.a((BaseInfo) new FPSRecordInfo(FPSRecordTask.this.e));
        }
    };

    public String a() {
        return ApmTaskConstants.e;
    }

    public void aa_() {
        super.aa_();
        if (e()) {
            Choreographer.getInstance().postFrameCallback(this.f);
        }
    }

    public void a(BaseInfo baseInfo) {
        DataDisplayTask dataDisplayTask = (DataDisplayTask) TaskManager.a().a(ApmTaskConstants.g);
        if (dataDisplayTask != null && dataDisplayTask.e()) {
            FloatViewManager.a().a(baseInfo);
        }
    }

    public void d() {
        super.d();
        Choreographer.getInstance().removeFrameCallback(this.f);
    }
}
