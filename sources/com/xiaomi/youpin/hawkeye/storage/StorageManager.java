package com.xiaomi.youpin.hawkeye.storage;

import com.xiaomi.youpin.hawkeye.HawkEye;
import com.xiaomi.youpin.hawkeye.display.DataDisplayTask;
import com.xiaomi.youpin.hawkeye.display.ui.FloatViewManager;
import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.task.ApmTaskConstants;
import com.xiaomi.youpin.hawkeye.task.TaskManager;
import java.util.List;

public class StorageManager {

    /* renamed from: a  reason: collision with root package name */
    private ISaveData f1580a;

    private static class StorageManagerHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static StorageManager f23370a = new StorageManager();

        private StorageManagerHolder() {
        }
    }

    private StorageManager() {
        this.f1580a = HawkEye.c().j();
    }

    public static StorageManager a() {
        return StorageManagerHolder.f23370a;
    }

    public boolean a(BaseInfo baseInfo) {
        b(baseInfo);
        if (this.f1580a != null) {
            return this.f1580a.a(baseInfo);
        }
        return false;
    }

    public void b(BaseInfo baseInfo) {
        DataDisplayTask dataDisplayTask = (DataDisplayTask) TaskManager.a().a(ApmTaskConstants.g);
        if (dataDisplayTask != null && dataDisplayTask.e()) {
            FloatViewManager.a().a(baseInfo);
        }
    }

    public List<BaseInfo> b() {
        if (this.f1580a != null) {
            return this.f1580a.a();
        }
        return null;
    }

    public boolean a(int i) {
        if (this.f1580a != null) {
            return this.f1580a.b(i);
        }
        return false;
    }
}
