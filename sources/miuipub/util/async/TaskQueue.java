package miuipub.util.async;

import android.util.Log;
import com.facebook.react.bridge.BaseJavaModule;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import miuipub.util.async.Task;
import miuipub.util.concurrent.ConcurrentRingQueue;
import miuipub.util.concurrent.Queue;

class TaskQueue implements Queue<Task<?>> {

    /* renamed from: a  reason: collision with root package name */
    private final Queue<Task<?>> f3036a;
    private final Queue<Task<?>> b;
    private final Queue<Task<?>> c;
    private final Semaphore d = new Semaphore(0, true);
    private final AtomicBoolean e = new AtomicBoolean(false);
    private final TaskManager f;

    public int d() {
        return -1;
    }

    public TaskQueue(TaskManager taskManager, int i) {
        this.f = taskManager;
        this.f3036a = new ConcurrentRingQueue(i, true, true);
        this.b = new ConcurrentRingQueue(i, true, true);
        this.c = new ConcurrentRingQueue(i, true, true);
    }

    /* renamed from: a */
    public boolean b(Task<?> task) {
        boolean z;
        switch (task.f()) {
            case Low:
                z = this.c.b(task);
                break;
            case Normal:
                z = this.b.b(task);
                break;
            case High:
                z = this.f3036a.b(task);
                break;
            case RealTime:
                Log.e(BaseJavaModule.METHOD_TYPE_ASYNC, "Realtime task must NOT be put in Queue");
                break;
        }
        z = false;
        if (z) {
            task.a(Task.Status.Queued, (Object) null);
            this.d.release();
        }
        return z;
    }

    private Task<?> h() {
        Task<?> g = this.f3036a.g();
        if (g == null) {
            g = this.b.g();
        }
        return g == null ? this.c.g() : g;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r4.d.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002a */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public miuipub.util.async.Task<?> g() {
        /*
            r4 = this;
            miuipub.util.async.TaskManager r0 = r4.f
            boolean r0 = r0.e()
            r1 = 0
            if (r0 != 0) goto L_0x0044
        L_0x0009:
            r0 = r1
        L_0x000a:
            if (r0 != 0) goto L_0x0059
            java.util.concurrent.Semaphore r0 = r4.d     // Catch:{ InterruptedException -> 0x0043 }
            r0.acquire()     // Catch:{ InterruptedException -> 0x0043 }
            java.util.concurrent.atomic.AtomicBoolean r0 = r4.e
            boolean r0 = r0.get()
            if (r0 == 0) goto L_0x0036
            java.util.concurrent.atomic.AtomicBoolean r0 = r4.e
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicBoolean r2 = r4.e     // Catch:{ all -> 0x0033 }
            boolean r2 = r2.get()     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x0031
            java.util.concurrent.atomic.AtomicBoolean r2 = r4.e     // Catch:{ InterruptedException -> 0x002a }
            r2.wait()     // Catch:{ InterruptedException -> 0x002a }
            goto L_0x0031
        L_0x002a:
            java.util.concurrent.Semaphore r2 = r4.d     // Catch:{ all -> 0x0033 }
            r2.release()     // Catch:{ all -> 0x0033 }
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return r1
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            goto L_0x0036
        L_0x0033:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r1
        L_0x0036:
            miuipub.util.async.Task r0 = r4.h()
            miuipub.util.async.Task$Status r2 = r0.b()
            miuipub.util.async.Task$Status r3 = miuipub.util.async.Task.Status.Canceled
            if (r2 != r3) goto L_0x000a
            goto L_0x0009
        L_0x0043:
            return r1
        L_0x0044:
            r0 = r1
        L_0x0045:
            java.util.concurrent.Semaphore r1 = r4.d
            boolean r1 = r1.tryAcquire()
            if (r1 == 0) goto L_0x0059
            miuipub.util.async.Task r0 = r4.h()
            miuipub.util.async.Task$Status r1 = r0.b()
            miuipub.util.async.Task$Status r2 = miuipub.util.async.Task.Status.Canceled
            if (r1 == r2) goto L_0x0045
        L_0x0059:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.util.async.TaskQueue.g():miuipub.util.async.Task");
    }

    /* renamed from: b */
    public boolean a(Task<?> task) {
        if (!this.d.tryAcquire()) {
            return false;
        }
        boolean a2 = this.f3036a.a(task);
        if (!a2) {
            a2 = this.b.a(task);
        }
        if (!a2) {
            a2 = this.c.a(task);
        }
        if (a2) {
            return a2;
        }
        this.d.release();
        return a2;
    }

    public int a(Queue.Predicate<Task<?>> predicate) {
        throw new RuntimeException("no support for this method");
    }

    public int b() {
        int i = 0;
        while (this.d.tryAcquire()) {
            if (h().b() != Task.Status.Canceled) {
                i++;
            }
        }
        return i;
    }

    public boolean c() {
        return this.d.availablePermits() == 0;
    }

    public void e() {
        synchronized (this.e) {
            this.e.set(true);
        }
    }

    public void f() {
        synchronized (this.e) {
            this.e.set(false);
            this.e.notifyAll();
        }
    }
}
