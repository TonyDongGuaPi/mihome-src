package com.mibi.common.base;

import android.os.AsyncTask;
import com.mibi.common.data.Client;
import com.mibi.common.data.SortedParameter;

public abstract class Task<Progress, TaskResult> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public TaskListener<Progress, TaskResult> f7471a;
    /* access modifiers changed from: private */
    public Class<TaskResult> b;
    /* access modifiers changed from: private */
    public SortedParameter c;
    /* access modifiers changed from: private */
    public TaskResult d;
    private boolean e;
    /* access modifiers changed from: private */
    public Task<Progress, TaskResult>.InnerTask f;

    /* access modifiers changed from: protected */
    public abstract void a(SortedParameter sortedParameter, TaskResult taskresult);

    /* access modifiers changed from: protected */
    public void a(TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public void a(Progress... progressArr) {
    }

    /* access modifiers changed from: protected */
    public void b(SortedParameter sortedParameter) {
    }

    /* access modifiers changed from: protected */
    public void b(TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public void f() {
    }

    /* access modifiers changed from: protected */
    public void n() {
    }

    /* access modifiers changed from: protected */
    public void o() {
    }

    public Task() {
        this((Class) null);
    }

    public Task(Class<TaskResult> cls) {
        this(cls, false);
    }

    public Task(Class<TaskResult> cls, boolean z) {
        this.c = new SortedParameter();
        this.b = cls;
        this.e = z;
    }

    private class InnerTask extends AsyncTask<Void, Progress, TaskResult> {
        private Class<TaskResult> b;
        private SortedParameter c;

        private InnerTask() {
        }

        /* access modifiers changed from: protected */
        public final void onPreExecute() {
            InnerTask unused = Task.this.f = this;
            if (Task.this.f7471a != null) {
                Task.this.f7471a.onTaskStart();
            }
            this.b = Task.this.b;
            this.c = Task.this.c;
            Task.this.f();
        }

        /* access modifiers changed from: protected */
        public void onCancelled(TaskResult taskresult) {
            Task.this.b(taskresult);
            Object unused = Task.this.d = taskresult;
            if (Task.this.f7471a != null) {
                Task.this.f7471a.onTaskCancelled(taskresult);
            }
            InnerTask unused2 = Task.this.f = null;
        }

        /* access modifiers changed from: protected */
        public final void onPostExecute(TaskResult taskresult) {
            Task.this.a(taskresult);
            Object unused = Task.this.d = taskresult;
            if (Task.this.f7471a != null) {
                Task.this.f7471a.onTaskComplete(taskresult);
            }
            InnerTask unused2 = Task.this.f = null;
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Progress... progressArr) {
            if (!(Task.this.f7471a == null || progressArr == null || progressArr.length <= 0)) {
                Task.this.f7471a.onProgressUpdate(progressArr[0]);
            }
            Task.this.a(progressArr);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public TaskResult doInBackground(Void... voidArr) {
            TaskResult taskresult;
            if (this.b == null || this.b == Void.class) {
                taskresult = null;
            } else {
                try {
                    taskresult = this.b.newInstance();
                } catch (InstantiationException e) {
                    throw new IllegalStateException(e);
                } catch (IllegalAccessException e2) {
                    throw new IllegalStateException(e2);
                }
            }
            Task.this.b(this.c);
            Task.this.a(this.c, taskresult);
            return taskresult;
        }

        /* access modifiers changed from: protected */
        public void a(Progress progress) {
            super.publishProgress(new Object[]{progress});
        }
    }

    /* access modifiers changed from: protected */
    public void c(Progress progress) {
        if (this.f != null) {
            this.f.a(progress);
        }
    }

    public boolean g() {
        if (this.f != null) {
            return this.f.isCancelled();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        InnerTask innerTask = new InnerTask();
        if (!this.e || !Client.c()) {
            innerTask.execute(new Void[0]);
        } else {
            innerTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void i() {
        if (this.f != null) {
            this.f.cancel(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(TaskListener<Progress, TaskResult> taskListener) {
        this.f7471a = taskListener;
    }

    /* access modifiers changed from: package-private */
    public void j() {
        this.f7471a = null;
    }

    /* access modifiers changed from: package-private */
    public TaskListener<Progress, TaskResult> k() {
        return this.f7471a;
    }

    /* access modifiers changed from: package-private */
    public void l() {
        n();
    }

    /* access modifiers changed from: package-private */
    public void m() {
        o();
    }

    public void c(SortedParameter sortedParameter) {
        if (sortedParameter == null) {
            sortedParameter = new SortedParameter();
        }
        this.c = sortedParameter;
    }

    public TaskResult p() {
        return this.d;
    }
}
