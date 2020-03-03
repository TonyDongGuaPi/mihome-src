package com.ximalaya.ting.android.sdkdownloader.task;

import android.os.Handler;
import android.os.Looper;
import com.ximalaya.ting.android.sdkdownloader.task.AbsTask;
import com.ximalaya.ting.android.sdkdownloader.task.Callback;
import java.util.concurrent.Executor;

class TaskProxy<ResultType> extends AbsTask<ResultType> {

    /* renamed from: a  reason: collision with root package name */
    static final PriorityExecutor f2373a = new PriorityExecutor(true);
    static final Handler b = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final AbsTask<ResultType> c;
    private final Executor d;
    /* access modifiers changed from: private */
    public volatile boolean e = false;
    /* access modifiers changed from: private */
    public volatile boolean f = false;
    private volatile boolean g = false;
    private PriorityRunnable h;

    TaskProxy(AbsTask<ResultType> absTask) {
        super(absTask);
        this.c = absTask;
        this.c.a(this);
        a((TaskProxy) null);
        Executor k = absTask.k();
        this.d = k == null ? f2373a : k;
    }

    /* access modifiers changed from: protected */
    public final ResultType a() throws Throwable {
        b();
        m();
        return null;
    }

    /* access modifiers changed from: protected */
    public void m() {
        this.h = new PriorityRunnable(this.c.l(), new Runnable() {
            public void run() {
                try {
                    if (TaskProxy.this.e || TaskProxy.this.f()) {
                        throw new Callback.CancelledException("");
                    } else if (TaskProxy.this.f || TaskProxy.this.g()) {
                        throw new Callback.RemovedException("");
                    } else {
                        TaskProxy.this.c();
                        if (TaskProxy.this.f()) {
                            throw new Callback.CancelledException("");
                        } else if (!TaskProxy.this.g()) {
                            TaskProxy.this.c.b(TaskProxy.this.c.a());
                            TaskProxy.this.b(TaskProxy.this.c.p());
                            if (TaskProxy.this.f()) {
                                throw new Callback.CancelledException("");
                            } else if (!TaskProxy.this.g()) {
                                TaskProxy.this.a(TaskProxy.this.c.p());
                                TaskProxy.this.e();
                            } else {
                                throw new Callback.RemovedException("");
                            }
                        } else {
                            throw new Callback.RemovedException("");
                        }
                    }
                } catch (Callback.CancelledException e) {
                    TaskProxy.this.a(e);
                } catch (Callback.RemovedException e2) {
                    TaskProxy.this.a(e2);
                } catch (Throwable th) {
                    TaskProxy.this.e();
                    throw th;
                }
            }
        });
        this.d.execute(this.h);
    }

    /* access modifiers changed from: protected */
    public void b() {
        a(AbsTask.State.WAITING);
        this.c.b();
    }

    /* access modifiers changed from: protected */
    public void c() {
        a(AbsTask.State.STARTED);
        this.c.c();
    }

    /* access modifiers changed from: protected */
    public void a(ResultType resulttype) {
        a(AbsTask.State.SUCCESS);
        this.c.a(p());
    }

    /* access modifiers changed from: protected */
    public void a(Throwable th, boolean z) {
        a(AbsTask.State.ERROR);
        this.c.a(th, false);
    }

    /* access modifiers changed from: protected */
    public void a(Callback.RemovedException removedException) {
        a(AbsTask.State.REMOVED);
        if (!this.f) {
            this.f = true;
            this.c.a(removedException);
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i, Object... objArr) {
        this.c.a(i, objArr);
    }

    /* access modifiers changed from: protected */
    public void a(Callback.CancelledException cancelledException) {
        a(AbsTask.State.CANCELLED);
        if (!this.e) {
            this.e = true;
            this.c.a(cancelledException);
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        if (!this.g) {
            this.g = true;
            this.c.e();
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(AbsTask.State state) {
        super.a(state);
        this.c.a(state);
    }

    public final Priority l() {
        return this.c.l();
    }

    public final Executor k() {
        return this.d;
    }

    public void i() {
        try {
            if (this.d != null && this.h != null && (this.d instanceof PriorityExecutor)) {
                ((PriorityExecutor) this.d).a((Runnable) this.h);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
