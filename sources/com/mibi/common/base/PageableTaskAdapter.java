package com.mibi.common.base;

import android.content.Context;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.base.BasePaymentTask.Result;
import com.mibi.common.base.PageableTask;

public abstract class PageableTaskAdapter<TaskType extends PageableTask<TaskResult>, TaskResult extends BasePaymentTask.Result> extends BasePaymentTaskAdapter<TaskType, Void, TaskResult> {
    private boolean e;

    public PageableTaskAdapter(Context context, TaskManager taskManager, TaskType tasktype) {
        super(context, taskManager, tasktype);
    }

    /* access modifiers changed from: protected */
    public void c() {
        super.c();
        this.e = true;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        this.e = false;
        return super.d();
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return ((PageableTask) this.d).c();
    }

    /* access modifiers changed from: protected */
    public boolean f() {
        return this.e;
    }

    public void g() {
        if (!this.e) {
            ((PageableTask) this.d).d();
            start();
        }
    }

    public void h() {
        if (!this.e) {
            restart();
        }
    }

    public void i() {
        ((PageableTask) this.d).e();
    }
}
