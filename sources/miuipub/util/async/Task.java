package miuipub.util.async;

import android.util.Log;
import com.facebook.react.bridge.BaseJavaModule;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.text.Typography;

public abstract class Task<T> {

    /* renamed from: a  reason: collision with root package name */
    private WeakReference<TaskManager> f3027a;
    private Priority b;
    private Thread c;
    private HashSet<Task<?>> d;
    private final AtomicBoolean e;
    private volatile Status f;
    private ArrayList<Listener> g;

    enum Delivery {
        Prepare,
        Result,
        Progress,
        Finalize
    }

    public interface Listener {
        Object a(TaskManager taskManager, Task<?> task, Object obj);

        void a(TaskManager taskManager, Task<?> task);

        void a(TaskManager taskManager, Task<?> task, int i, int i2);

        void a(TaskManager taskManager, Task<?> task, Exception exc);

        void b(TaskManager taskManager, Task<?> task);

        void c(TaskManager taskManager, Task<?> task);
    }

    public enum Priority {
        Low,
        Normal,
        High,
        RealTime
    }

    public enum Status {
        New,
        Queued,
        Executing,
        Done,
        Canceled
    }

    public void a(TaskManager taskManager, int i, int i2) {
    }

    public void a(TaskManager taskManager, Exception exc) {
    }

    public void a(TaskManager taskManager, T t) {
    }

    public void b(TaskManager taskManager) {
    }

    public void c(TaskManager taskManager) {
    }

    public String d() {
        return null;
    }

    public void d(TaskManager taskManager) {
    }

    public abstract T h() throws Exception;

    public Task() {
        this(Priority.Normal);
    }

    public Task(Priority priority) {
        this.e = new AtomicBoolean(false);
        this.f = Status.New;
        this.b = priority;
    }

    /* access modifiers changed from: package-private */
    public final void a(Status status, Object obj) {
        switch (this.f) {
            case Queued:
                switch (status) {
                    case Canceled:
                        a(Delivery.Result, (Object) null);
                        a(Delivery.Finalize, (Object) null);
                        break;
                    case Executing:
                        break;
                    default:
                        Log.w(BaseJavaModule.METHOD_TYPE_ASYNC, "Task " + this + " error status change=> " + status);
                        return;
                }
            case Canceled:
            case Done:
                Log.w(BaseJavaModule.METHOD_TYPE_ASYNC, "Task " + this + " error status change=> " + status);
                return;
            case Executing:
                int i = AnonymousClass1.f3028a[status.ordinal()];
                if (i != 2) {
                    if (i == 4) {
                        a(Delivery.Result, obj);
                        a(Delivery.Finalize, (Object) null);
                        break;
                    } else {
                        Log.w(BaseJavaModule.METHOD_TYPE_ASYNC, "Task " + this + " error status change=> " + status);
                        return;
                    }
                } else {
                    a(Delivery.Result, (Object) null);
                    a(Delivery.Finalize, (Object) null);
                    break;
                }
            case New:
                switch (status) {
                    case Queued:
                        a(Delivery.Prepare, (Object) null);
                        break;
                    case Canceled:
                        a(Delivery.Result, (Object) null);
                        a(Delivery.Finalize, (Object) null);
                        break;
                    default:
                        Log.w(BaseJavaModule.METHOD_TYPE_ASYNC, "Task " + this + " error status change=> " + status);
                        return;
                }
        }
        this.f = status;
    }

    public final Status b() {
        return this.f;
    }

    public final boolean c() {
        return this.f == Status.Queued || this.f == Status.Executing;
    }

    private final boolean a() {
        return this.f == Status.Canceled;
    }

    /* access modifiers changed from: package-private */
    public final void a(Thread thread) {
        this.c = thread;
        if (thread != null) {
            switch (this.b) {
                case Low:
                    thread.setPriority(1);
                    return;
                case Normal:
                    thread.setPriority(5);
                    return;
                case High:
                case RealTime:
                    thread.setPriority(10);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(TaskManager taskManager) {
        if (this.f3027a != null && this.f3027a.get() != null) {
            return false;
        }
        this.f3027a = new WeakReference<>(taskManager);
        return true;
    }

    private void e(TaskManager taskManager) {
        synchronized (this.e) {
            if (this.d != null) {
                Iterator<Task<?>> it = this.d.iterator();
                while (it.hasNext()) {
                    taskManager.a(it.next());
                }
            }
            this.e.set(true);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean i() {
        /*
            r5 = this;
            miuipub.util.async.Task$Status r0 = r5.f
            miuipub.util.async.Task$Status r1 = miuipub.util.async.Task.Status.Executing
            r2 = 0
            if (r0 == r1) goto L_0x0059
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.e
            monitor-enter(r0)
            miuipub.util.async.Task$Status r1 = r5.f     // Catch:{ all -> 0x0056 }
            miuipub.util.async.Task$Status r3 = miuipub.util.async.Task.Status.Done     // Catch:{ all -> 0x0056 }
            if (r1 != r3) goto L_0x0035
            java.util.concurrent.atomic.AtomicBoolean r1 = r5.e     // Catch:{ all -> 0x0056 }
            boolean r1 = r1.get()     // Catch:{ all -> 0x0056 }
            if (r1 != 0) goto L_0x0035
            java.lang.String r1 = "async"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0056 }
            r3.<init>()     // Catch:{ all -> 0x0056 }
            java.lang.String r4 = "Task "
            r3.append(r4)     // Catch:{ all -> 0x0056 }
            r3.append(r5)     // Catch:{ all -> 0x0056 }
            java.lang.String r4 = " is DONE but successor not done yet"
            r3.append(r4)     // Catch:{ all -> 0x0056 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0056 }
            android.util.Log.e(r1, r3)     // Catch:{ all -> 0x0056 }
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            return r2
        L_0x0035:
            java.util.HashSet<miuipub.util.async.Task<?>> r1 = r5.d     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x0053
            java.util.HashSet<miuipub.util.async.Task<?>> r1 = r5.d     // Catch:{ all -> 0x0056 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0056 }
        L_0x003f:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0056 }
            if (r3 == 0) goto L_0x0053
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0056 }
            miuipub.util.async.Task r3 = (miuipub.util.async.Task) r3     // Catch:{ all -> 0x0056 }
            boolean r3 = r3.i()     // Catch:{ all -> 0x0056 }
            if (r3 != 0) goto L_0x003f
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            return r2
        L_0x0053:
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            r0 = 1
            return r0
        L_0x0056:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            throw r1
        L_0x0059:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.util.async.Task.i():boolean");
    }

    private void a(Delivery delivery, Object obj) {
        TaskManager taskManager = (TaskManager) this.f3027a.get();
        if (taskManager != null) {
            taskManager.a(this, delivery, obj);
            return;
        }
        Log.e(BaseJavaModule.METHOD_TYPE_ASYNC, "Task has delivery " + delivery + ", but has no task manager");
    }

    /* access modifiers changed from: package-private */
    public final void a(TaskManager taskManager, Delivery delivery, Object obj) {
        switch (delivery) {
            case Prepare:
                h(taskManager);
                return;
            case Finalize:
                g(taskManager);
                return;
            case Result:
                if (obj == null || a()) {
                    f(taskManager);
                    return;
                } else if (obj instanceof TaskExecutingException) {
                    b(taskManager, (Exception) ((TaskExecutingException) obj).getCause());
                    return;
                } else {
                    try {
                        b(taskManager, obj);
                        e(taskManager);
                        return;
                    } catch (ClassCastException e2) {
                        Log.e(BaseJavaModule.METHOD_TYPE_ASYNC, "Task " + this + " return result cannot cast to expectation class");
                        b(taskManager, (Exception) e2);
                        return;
                    }
                }
            case Progress:
                int[] iArr = (int[]) obj;
                b(taskManager, iArr[0], iArr[1]);
                return;
            default:
                return;
        }
    }

    private void f(TaskManager taskManager) {
        if (this.g != null) {
            Iterator<Listener> it = this.g.iterator();
            while (it.hasNext()) {
                it.next().b(taskManager, this);
            }
        }
        c(taskManager);
    }

    private void b(TaskManager taskManager, int i, int i2) {
        if (this.g != null) {
            Iterator<Listener> it = this.g.iterator();
            while (it.hasNext()) {
                it.next().a(taskManager, this, i, i2);
            }
        }
        a(taskManager, i, i2);
    }

    private void b(TaskManager taskManager, T t) {
        if (this.g != null) {
            Iterator<Listener> it = this.g.iterator();
            while (it.hasNext()) {
                t = it.next().a(taskManager, (Task<?>) this, (Object) t);
            }
        }
        a(taskManager, t);
    }

    private void b(TaskManager taskManager, Exception exc) {
        if (this.g != null) {
            Iterator<Listener> it = this.g.iterator();
            while (it.hasNext()) {
                it.next().a(taskManager, (Task<?>) this, exc);
            }
        }
        a(taskManager, exc);
    }

    private void g(TaskManager taskManager) {
        if (this.g != null) {
            Iterator<Listener> it = this.g.iterator();
            while (it.hasNext()) {
                it.next().c(taskManager, this);
            }
        }
        d(taskManager);
        this.f3027a.clear();
    }

    private void h(TaskManager taskManager) {
        if (this.g != null) {
            Iterator<Listener> it = this.g.iterator();
            while (it.hasNext()) {
                it.next().a(taskManager, this);
            }
        }
        b(taskManager);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        String d2 = d();
        if (d2 != null) {
            sb.append(Typography.d);
            sb.append(d2);
            sb.append(Typography.e);
        }
        sb.append(": Status=");
        sb.append(this.f);
        sb.append(", Priority=");
        sb.append(this.b);
        return sb.toString();
    }

    public final void a(int i, int i2) {
        a(Delivery.Progress, (Object) new int[]{i, i2});
    }

    public final Task<T> a(Listener listener) {
        if (this.g == null) {
            this.g = new ArrayList<>();
        }
        this.g.add(listener);
        return this;
    }

    public final Task<T> b(Listener listener) {
        if (this.g != null) {
            this.g.remove(listener);
        }
        return this;
    }

    public final void e() {
        TaskManager taskManager;
        if (this.f != Status.Done) {
            if (this.f == Status.Queued && (taskManager = (TaskManager) this.f3027a.get()) != null) {
                taskManager.b((Task<?>) this);
            }
            a(Status.Canceled, (Object) null);
        }
    }

    public final void a(Task<T> task) {
        synchronized (this.e) {
            if (this.e.get()) {
                if (task.d == null) {
                    task.d = new HashSet<>();
                }
                task.d.add(this);
            } else {
                TaskManager taskManager = (TaskManager) this.f3027a.get();
                if (taskManager != null) {
                    taskManager.a((Task<?>) this);
                } else {
                    Log.e(BaseJavaModule.METHOD_TYPE_ASYNC, "Task " + this + " is done but has no task manager to execute task " + task);
                }
            }
        }
    }

    public final Priority f() {
        return this.b;
    }

    public final Task<T> a(Priority priority) {
        TaskManager taskManager;
        if (this.b != priority) {
            if (this.f == Status.Queued && (taskManager = (TaskManager) this.f3027a.get()) != null && taskManager.b((Task<?>) this)) {
                this.b = priority;
                this.f = Status.New;
                taskManager.a((Task<?>) this);
            }
            if (this.f == Status.Executing) {
                a(this.c);
            }
            this.b = priority;
        }
        return this;
    }

    public final boolean g() {
        TaskManager taskManager;
        if (!i()) {
            return false;
        }
        if ((this.f == Status.Queued && ((taskManager = (TaskManager) this.f3027a.get()) == null || !taskManager.b((Task<?>) this))) || this.f == Status.Executing) {
            return false;
        }
        synchronized (this.e) {
            if (this.d != null) {
                Iterator<Task<?>> it = this.d.iterator();
                while (it.hasNext()) {
                    it.next().g();
                }
            }
            this.e.set(true);
        }
        this.f = Status.New;
        return true;
    }
}
