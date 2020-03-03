package com.mipay.common.base;

import com.mipay.common.base.m;
import com.mipay.common.data.h;

public abstract class n<TaskType extends m<Progress, TaskResult>, Progress, TaskResult> implements r<Progress, TaskResult> {
    protected s b;
    protected int c;
    protected TaskType d;

    public n(s sVar, TaskType tasktype) {
        this.b = sVar;
        this.d = tasktype;
        this.c = sVar.a(this.d, this);
    }

    /* access modifiers changed from: protected */
    public h c() {
        return null;
    }

    public void cancel() {
        this.b.b(this.c);
    }

    public TaskType getTask() {
        return this.d;
    }

    public int getTaskId() {
        return this.c;
    }

    public void onProgressUpdate(Progress progress) {
    }

    public void onTaskCancelled(TaskResult taskresult) {
    }

    public void onTaskComplete(TaskResult taskresult) {
    }

    public void onTaskStart() {
    }

    public void restart() {
        restart(true);
    }

    public void restart(boolean z) {
        this.b.a(this.c, z);
    }

    public void start() {
        start(true);
    }

    public void start(boolean z) {
        this.d.setParams(c());
        this.b.a(this.c, z);
    }
}
