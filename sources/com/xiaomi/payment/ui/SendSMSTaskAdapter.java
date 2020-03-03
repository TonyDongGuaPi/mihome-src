package com.xiaomi.payment.ui;

import com.mibi.common.base.TaskAdapter;
import com.mibi.common.base.TaskManager;
import com.xiaomi.payment.task.SendSMSTask;
import com.xiaomi.payment.task.SendSMSTask.Result;

public class SendSMSTaskAdapter<TaskType extends SendSMSTask<TaskResult>, TaskResult extends SendSMSTask.Result> extends TaskAdapter<TaskType, Void, TaskResult> {
    public void a() {
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SendSMSTaskAdapter(TaskManager taskManager, TaskType tasktype) {
        super(taskManager, tasktype);
    }

    /* renamed from: a */
    public void onTaskComplete(SendSMSTask.Result result) {
        switch (result.f12415a) {
            case -1:
                e();
                return;
            case 101:
                a();
                return;
            case 102:
                b();
                return;
            case 104:
            case 105:
                d();
                return;
            default:
                c();
                return;
        }
    }
}
