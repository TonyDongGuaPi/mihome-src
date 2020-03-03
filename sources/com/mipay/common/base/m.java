package com.mipay.common.base;

import android.os.AsyncTask;
import com.mipay.common.data.b;
import com.mipay.common.data.h;

public abstract class m<Progress, TaskResult> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public r<Progress, TaskResult> f8119a;
    /* access modifiers changed from: private */
    public Class<TaskResult> b;
    /* access modifiers changed from: private */
    public h c;
    /* access modifiers changed from: private */
    public TaskResult d;
    private boolean e;
    /* access modifiers changed from: private */
    public m<Progress, TaskResult>.a f;

    private class a extends AsyncTask<Void, Progress, TaskResult> {
        private Class<TaskResult> b;
        private h c;

        private a() {
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
            m.this.a(this.c);
            m.this.a(this.c, taskresult);
            return taskresult;
        }

        /* access modifiers changed from: protected */
        public void a(Progress progress) {
            super.publishProgress(new Object[]{progress});
        }

        /* access modifiers changed from: protected */
        public void onCancelled(TaskResult taskresult) {
            m.this.b(taskresult);
            Object unused = m.this.d = taskresult;
            if (m.this.f8119a != null) {
                m.this.f8119a.onTaskCancelled(taskresult);
            }
            a unused2 = m.this.f = null;
        }

        /* access modifiers changed from: protected */
        public final void onPostExecute(TaskResult taskresult) {
            m.this.a(taskresult);
            Object unused = m.this.d = taskresult;
            if (m.this.f8119a != null) {
                m.this.f8119a.onTaskComplete(taskresult);
            }
            a unused2 = m.this.f = null;
        }

        /* access modifiers changed from: protected */
        public final void onPreExecute() {
            a unused = m.this.f = this;
            if (m.this.f8119a != null) {
                m.this.f8119a.onTaskStart();
            }
            this.b = m.this.b;
            this.c = m.this.c;
            m.this.a();
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Progress... progressArr) {
            if (!(m.this.f8119a == null || progressArr == null || progressArr.length <= 0)) {
                m.this.f8119a.onProgressUpdate(progressArr[0]);
            }
            m.this.a(progressArr);
        }
    }

    public m() {
        this((Class) null);
    }

    public m(Class<TaskResult> cls) {
        this(cls, false);
    }

    public m(Class<TaskResult> cls, boolean z) {
        this.c = new h();
        this.b = cls;
        this.e = z;
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: package-private */
    public void a(r<Progress, TaskResult> rVar) {
        this.f8119a = rVar;
    }

    /* access modifiers changed from: protected */
    public void a(h hVar) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(h hVar, TaskResult taskresult);

    /* access modifiers changed from: protected */
    public void a(TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public void a(Progress... progressArr) {
    }

    /* access modifiers changed from: package-private */
    public void b() {
        a aVar = new a();
        if (!this.e || !b.c()) {
            aVar.execute(new Void[0]);
        } else {
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void b(TaskResult taskresult) {
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.f != null) {
            this.f.cancel(true);
        }
    }

    /* access modifiers changed from: protected */
    public void c(Progress progress) {
        if (this.f != null) {
            this.f.a(progress);
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.f8119a = null;
    }

    /* access modifiers changed from: package-private */
    public r<Progress, TaskResult> e() {
        return this.f8119a;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        h();
    }

    /* access modifiers changed from: package-private */
    public void g() {
        i();
    }

    public TaskResult getResult() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void h() {
    }

    /* access modifiers changed from: protected */
    public void i() {
    }

    public boolean isCancelled() {
        if (this.f != null) {
            return this.f.isCancelled();
        }
        return false;
    }

    public void setParams(h hVar) {
        if (hVar == null) {
            hVar = new h();
        }
        this.c = hVar;
    }
}
