package com.mipay.common.base;

class q implements r<Void, TaskResult> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ o f8123a;
    final /* synthetic */ p b;

    q(p pVar, o oVar) {
        this.b = pVar;
        this.f8123a = oVar;
    }

    /* renamed from: a */
    public void onProgressUpdate(Void voidR) {
    }

    public void onTaskCancelled(TaskResult taskresult) {
    }

    public void onTaskComplete(TaskResult taskresult) {
        if (this.f8123a != null) {
            this.f8123a.a(taskresult);
        }
    }

    public void onTaskStart() {
    }
}
