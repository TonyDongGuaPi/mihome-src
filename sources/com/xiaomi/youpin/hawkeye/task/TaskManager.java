package com.xiaomi.youpin.hawkeye.task;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, ITask> f23372a;

    private static class TaskManagerHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static TaskManager f23373a = new TaskManager();

        private TaskManagerHolder() {
        }
    }

    private TaskManager() {
        this.f23372a = new HashMap<>();
    }

    public static TaskManager a() {
        return TaskManagerHolder.f23373a;
    }

    public void a(ITask iTask) {
        if (iTask != null && !TextUtils.isEmpty(iTask.a())) {
            iTask.a(true);
            this.f23372a.put(iTask.a(), iTask);
        }
    }

    public ITask a(String str) {
        if (this.f23372a.get(str) != null) {
            return this.f23372a.get(str);
        }
        return null;
    }

    public void b() {
        if (this.f23372a.size() > 0) {
            for (Map.Entry<String, ITask> value : this.f23372a.entrySet()) {
                ITask iTask = (ITask) value.getValue();
                if (iTask != null && iTask.e()) {
                    iTask.aa_();
                }
            }
        }
    }

    public void c() {
        if (this.f23372a.size() > 0) {
            for (Map.Entry<String, ITask> value : this.f23372a.entrySet()) {
                ITask iTask = (ITask) value.getValue();
                if (iTask != null && iTask.e()) {
                    iTask.d();
                }
            }
        }
    }
}
