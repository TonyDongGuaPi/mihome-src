package com.lidroid.xutils.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.lidroid.xutils.util.LogUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class PriorityAsyncTask<Params, Progress, Result> implements TaskHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6357a = 1;
    private static final int b = 2;
    private static final InternalHandler c = new InternalHandler((InternalHandler) null);
    public static final Executor l = new PriorityExecutor();
    private final WorkerRunnable<Params, Result> d = new WorkerRunnable<Params, Result>() {
        public Result call() throws Exception {
            PriorityAsyncTask.this.h.set(true);
            Process.setThreadPriority(10);
            return PriorityAsyncTask.this.d(PriorityAsyncTask.this.c((Params[]) this.b));
        }
    };
    private final FutureTask<Result> e = new FutureTask<Result>(this.d) {
        /* access modifiers changed from: protected */
        public void done() {
            try {
                PriorityAsyncTask.this.c(get());
            } catch (InterruptedException e) {
                LogUtils.a(e.getMessage());
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException unused) {
                PriorityAsyncTask.this.c(null);
            }
        }
    };
    private volatile boolean f = false;
    private final AtomicBoolean g = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final AtomicBoolean h = new AtomicBoolean();
    private Priority i;

    /* access modifiers changed from: protected */
    public void a(Result result) {
    }

    /* access modifiers changed from: protected */
    public void b(Progress... progressArr) {
    }

    /* access modifiers changed from: protected */
    public abstract Result c(Params... paramsArr);

    /* access modifiers changed from: protected */
    public void d() {
    }

    /* access modifiers changed from: protected */
    public void e() {
    }

    public boolean f() {
        return false;
    }

    public boolean g() {
        return false;
    }

    public boolean h() {
        return true;
    }

    public void i() {
    }

    public void j() {
    }

    public boolean l() {
        return false;
    }

    public Priority c() {
        return this.i;
    }

    public void a(Priority priority) {
        this.i = priority;
    }

    /* access modifiers changed from: private */
    public void c(Result result) {
        if (!this.h.get()) {
            d(result);
        }
    }

    /* access modifiers changed from: private */
    public Result d(Result result) {
        c.obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    /* access modifiers changed from: protected */
    public void b(Result result) {
        e();
    }

    public final boolean m() {
        return this.g.get();
    }

    public final boolean a(boolean z) {
        this.g.set(true);
        return this.e.cancel(z);
    }

    public void k() {
        a(true);
    }

    public final Result n() throws InterruptedException, ExecutionException {
        return this.e.get();
    }

    public final Result a(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.e.get(j, timeUnit);
    }

    public final PriorityAsyncTask<Params, Progress, Result> e(Params... paramsArr) {
        return a(l, paramsArr);
    }

    public final PriorityAsyncTask<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (!this.f) {
            this.f = true;
            d();
            this.d.b = paramsArr;
            executor.execute(new PriorityRunnable(this.i, this.e));
            return this;
        }
        throw new IllegalStateException("Cannot execute task: the task is already executed.");
    }

    public static void a(Runnable runnable) {
        a(runnable, Priority.DEFAULT);
    }

    public static void a(Runnable runnable, Priority priority) {
        l.execute(new PriorityRunnable(priority, runnable));
    }

    /* access modifiers changed from: protected */
    public final void f(Progress... progressArr) {
        if (!m()) {
            c.obtainMessage(2, new AsyncTaskResult(this, progressArr)).sendToTarget();
        }
    }

    /* access modifiers changed from: private */
    public void e(Result result) {
        if (m()) {
            b(result);
        } else {
            a(result);
        }
    }

    private static class InternalHandler extends Handler {
        /* synthetic */ InternalHandler(InternalHandler internalHandler) {
            this();
        }

        private InternalHandler() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            switch (message.what) {
                case 1:
                    asyncTaskResult.f6360a.e(asyncTaskResult.b[0]);
                    return;
                case 2:
                    asyncTaskResult.f6360a.b((Progress[]) asyncTaskResult.b);
                    return;
                default:
                    return;
            }
        }
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] b;

        private WorkerRunnable() {
        }

        /* synthetic */ WorkerRunnable(WorkerRunnable workerRunnable) {
            this();
        }
    }

    private static class AsyncTaskResult<Data> {

        /* renamed from: a  reason: collision with root package name */
        final PriorityAsyncTask f6360a;
        final Data[] b;

        AsyncTaskResult(PriorityAsyncTask priorityAsyncTask, Data... dataArr) {
            this.f6360a = priorityAsyncTask;
            this.b = dataArr;
        }
    }
}
