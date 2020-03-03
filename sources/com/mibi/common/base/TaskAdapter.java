package com.mibi.common.base;

import com.mibi.common.base.Task;
import com.mibi.common.data.SortedParameter;

public abstract class TaskAdapter<TaskType extends Task<Progress, TaskResult>, Progress, TaskResult> implements TaskListener<Progress, TaskResult> {
    protected TaskManager b;
    protected int c;
    protected TaskType d;

    /* access modifiers changed from: protected */
    public SortedParameter j() {
        return null;
    }

    public void onProgressUpdate(Progress progress) {
    }

    public void onTaskCancelled(TaskResult taskresult) {
    }

    public void onTaskComplete(TaskResult taskresult) {
    }

    public void onTaskStart() {
    }

    public TaskAdapter(TaskManager taskManager, TaskType tasktype) {
        this.b = taskManager;
        this.d = tasktype;
        this.c = taskManager.a(this.d, this);
    }

    public void restart(boolean z) {
        this.b.a(this.c, z);
    }

    public void restart() {
        restart(true);
    }

    public void start(boolean z) {
        this.d.c(j());
        this.b.a(this.c, z);
    }

    public void start() {
        start(true);
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
}
