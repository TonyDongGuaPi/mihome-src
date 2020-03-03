package bolts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Task<TResult> {

    /* renamed from: a  reason: collision with root package name */
    public static final ExecutorService f506a = BoltsExecutors.a();
    public static final Executor b = AndroidExecutors.b();
    private static final Executor c = BoltsExecutors.c();
    private static volatile UnobservedExceptionHandler d;
    private static Task<?> m = new Task<>((Object) null);
    private static Task<Boolean> n = new Task<>(true);
    private static Task<Boolean> o = new Task<>(false);
    private static Task<?> p = new Task<>(true);
    private final Object e = new Object();
    private boolean f;
    private boolean g;
    private TResult h;
    private Exception i;
    private boolean j;
    private UnobservedErrorNotifier k;
    private List<Continuation<TResult, Void>> l = new ArrayList();

    public interface UnobservedExceptionHandler {
        void a(Task<?> task, UnobservedTaskException unobservedTaskException);
    }

    public <TOut> Task<TOut> j() {
        return this;
    }

    public static UnobservedExceptionHandler a() {
        return d;
    }

    public static void a(UnobservedExceptionHandler unobservedExceptionHandler) {
        d = unobservedExceptionHandler;
    }

    Task() {
    }

    private Task(TResult tresult) {
        b(tresult);
    }

    private Task(boolean z) {
        if (z) {
            l();
        } else {
            b((Object) null);
        }
    }

    public static <TResult> Task<TResult>.TaskCompletionSource b() {
        Task task = new Task();
        task.getClass();
        return new TaskCompletionSource();
    }

    public boolean c() {
        boolean z;
        synchronized (this.e) {
            z = this.f;
        }
        return z;
    }

    public boolean d() {
        boolean z;
        synchronized (this.e) {
            z = this.g;
        }
        return z;
    }

    public boolean e() {
        boolean z;
        synchronized (this.e) {
            z = g() != null;
        }
        return z;
    }

    public TResult f() {
        TResult tresult;
        synchronized (this.e) {
            tresult = this.h;
        }
        return tresult;
    }

    public Exception g() {
        Exception exc;
        synchronized (this.e) {
            if (this.i != null) {
                this.j = true;
                if (this.k != null) {
                    this.k.a();
                    this.k = null;
                }
            }
            exc = this.i;
        }
        return exc;
    }

    public void h() throws InterruptedException {
        synchronized (this.e) {
            if (!c()) {
                this.e.wait();
            }
        }
    }

    public boolean a(long j2, TimeUnit timeUnit) throws InterruptedException {
        boolean c2;
        synchronized (this.e) {
            if (!c()) {
                this.e.wait(timeUnit.toMillis(j2));
            }
            c2 = c();
        }
        return c2;
    }

    public static <TResult> Task<TResult> a(TResult tresult) {
        if (tresult == null) {
            return m;
        }
        if (tresult instanceof Boolean) {
            return ((Boolean) tresult).booleanValue() ? n : o;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.b(tresult);
        return taskCompletionSource.a();
    }

    public static <TResult> Task<TResult> a(Exception exc) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.b(exc);
        return taskCompletionSource.a();
    }

    public static <TResult> Task<TResult> i() {
        return p;
    }

    public static Task<Void> a(long j2) {
        return a(j2, BoltsExecutors.b(), (CancellationToken) null);
    }

    public static Task<Void> a(long j2, CancellationToken cancellationToken) {
        return a(j2, BoltsExecutors.b(), cancellationToken);
    }

    static Task<Void> a(long j2, ScheduledExecutorService scheduledExecutorService, CancellationToken cancellationToken) {
        if (cancellationToken != null && cancellationToken.a()) {
            return i();
        }
        if (j2 <= 0) {
            return a((Object) null);
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                taskCompletionSource.a(null);
            }
        }, j2, TimeUnit.MILLISECONDS);
        if (cancellationToken != null) {
            cancellationToken.a(new Runnable() {
                public void run() {
                    schedule.cancel(true);
                    taskCompletionSource.b();
                }
            });
        }
        return taskCompletionSource.a();
    }

    public Task<Void> k() {
        return b(new Continuation<TResult, Task<Void>>() {
            /* renamed from: a */
            public Task<Void> then(Task<TResult> task) throws Exception {
                if (task.d()) {
                    return Task.i();
                }
                if (task.e()) {
                    return Task.a(task.g());
                }
                return Task.a(null);
            }
        });
    }

    public static <TResult> Task<TResult> a(Callable<TResult> callable) {
        return a(callable, (Executor) f506a, (CancellationToken) null);
    }

    public static <TResult> Task<TResult> a(Callable<TResult> callable, CancellationToken cancellationToken) {
        return a(callable, (Executor) f506a, cancellationToken);
    }

    public static <TResult> Task<TResult> a(Callable<TResult> callable, Executor executor) {
        return a(callable, executor, (CancellationToken) null);
    }

    public static <TResult> Task<TResult> a(final Callable<TResult> callable, Executor executor, final CancellationToken cancellationToken) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (cancellationToken == null || !cancellationToken.a()) {
                        try {
                            taskCompletionSource.b(callable.call());
                        } catch (CancellationException unused) {
                            taskCompletionSource.c();
                        } catch (Exception e) {
                            taskCompletionSource.b(e);
                        }
                    } else {
                        taskCompletionSource.c();
                    }
                }
            });
        } catch (Exception e2) {
            taskCompletionSource.b((Exception) new ExecutorException(e2));
        }
        return taskCompletionSource.a();
    }

    public static <TResult> Task<TResult> b(Callable<TResult> callable) {
        return a(callable, c, (CancellationToken) null);
    }

    public static <TResult> Task<TResult> b(Callable<TResult> callable, CancellationToken cancellationToken) {
        return a(callable, c, cancellationToken);
    }

    public static <TResult> Task<Task<TResult>> a(Collection<? extends Task<TResult>> collection) {
        if (collection.size() == 0) {
            return a((Object) null);
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        for (Task a2 : collection) {
            a2.a(new Continuation<TResult, Void>() {
                /* renamed from: a */
                public Void then(Task<TResult> task) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        taskCompletionSource.b(task);
                        return null;
                    }
                    task.g();
                    return null;
                }
            });
        }
        return taskCompletionSource.a();
    }

    public static Task<Task<?>> b(Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return a((Object) null);
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        for (Task a2 : collection) {
            a2.a(new Continuation<Object, Void>() {
                /* renamed from: a */
                public Void then(Task<Object> task) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        taskCompletionSource.b(task);
                        return null;
                    }
                    task.g();
                    return null;
                }
            });
        }
        return taskCompletionSource.a();
    }

    public static <TResult> Task<List<TResult>> c(final Collection<? extends Task<TResult>> collection) {
        return d((Collection<? extends Task<?>>) collection).c(new Continuation<Void, List<TResult>>() {
            /* renamed from: a */
            public List<TResult> then(Task<Void> task) throws Exception {
                if (collection.size() == 0) {
                    return Collections.emptyList();
                }
                ArrayList arrayList = new ArrayList();
                for (Task f : collection) {
                    arrayList.add(f.f());
                }
                return arrayList;
            }
        });
    }

    public static Task<Void> d(Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return a((Object) null);
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        ArrayList arrayList = new ArrayList();
        Object obj = new Object();
        AtomicInteger atomicInteger = new AtomicInteger(collection.size());
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        for (Task a2 : collection) {
            final Object obj2 = obj;
            final ArrayList arrayList2 = arrayList;
            final AtomicBoolean atomicBoolean2 = atomicBoolean;
            final AtomicInteger atomicInteger2 = atomicInteger;
            final TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
            a2.a(new Continuation<Object, Void>() {
                /* renamed from: a */
                public Void then(Task<Object> task) {
                    if (task.e()) {
                        synchronized (obj2) {
                            arrayList2.add(task.g());
                        }
                    }
                    if (task.d()) {
                        atomicBoolean2.set(true);
                    }
                    if (atomicInteger2.decrementAndGet() == 0) {
                        if (arrayList2.size() != 0) {
                            if (arrayList2.size() == 1) {
                                taskCompletionSource2.b((Exception) arrayList2.get(0));
                            } else {
                                taskCompletionSource2.b((Exception) new AggregateException(String.format("There were %d exceptions.", new Object[]{Integer.valueOf(arrayList2.size())}), (List<? extends Throwable>) arrayList2));
                            }
                        } else if (atomicBoolean2.get()) {
                            taskCompletionSource2.c();
                        } else {
                            taskCompletionSource2.b(null);
                        }
                    }
                    return null;
                }
            });
        }
        return taskCompletionSource.a();
    }

    public Task<Void> a(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation) {
        return a(callable, continuation, c, (CancellationToken) null);
    }

    public Task<Void> a(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation, CancellationToken cancellationToken) {
        return a(callable, continuation, c, cancellationToken);
    }

    public Task<Void> a(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation, Executor executor) {
        return a(callable, continuation, executor, (CancellationToken) null);
    }

    public Task<Void> a(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation, Executor executor, CancellationToken cancellationToken) {
        Capture capture = new Capture();
        final CancellationToken cancellationToken2 = cancellationToken;
        final Callable<Boolean> callable2 = callable;
        final Continuation<Void, Task<Void>> continuation2 = continuation;
        final Executor executor2 = executor;
        final Capture capture2 = capture;
        capture.a(new Continuation<Void, Task<Void>>() {
            /* renamed from: a */
            public Task<Void> then(Task<Void> task) throws Exception {
                if (cancellationToken2 != null && cancellationToken2.a()) {
                    return Task.i();
                }
                if (((Boolean) callable2.call()).booleanValue()) {
                    return Task.a(null).d(continuation2, executor2).d((Continuation) capture2.a(), executor2);
                }
                return Task.a(null);
            }
        });
        return k().b((Continuation) capture.a(), executor);
    }

    public <TContinuationResult> Task<TContinuationResult> a(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return a(continuation, executor, (CancellationToken) null);
    }

    public <TContinuationResult> Task<TContinuationResult> a(Continuation<TResult, TContinuationResult> continuation, Executor executor, CancellationToken cancellationToken) {
        boolean c2;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.e) {
            c2 = c();
            if (!c2) {
                final TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
                final Continuation<TResult, TContinuationResult> continuation2 = continuation;
                final Executor executor2 = executor;
                final CancellationToken cancellationToken2 = cancellationToken;
                this.l.add(new Continuation<TResult, Void>() {
                    /* renamed from: a */
                    public Void then(Task<TResult> task) {
                        Task.c(taskCompletionSource2, continuation2, task, executor2, cancellationToken2);
                        return null;
                    }
                });
            }
        }
        if (c2) {
            c(taskCompletionSource, continuation, this, executor, cancellationToken);
        }
        return taskCompletionSource.a();
    }

    public <TContinuationResult> Task<TContinuationResult> a(Continuation<TResult, TContinuationResult> continuation) {
        return a(continuation, c, (CancellationToken) null);
    }

    public <TContinuationResult> Task<TContinuationResult> a(Continuation<TResult, TContinuationResult> continuation, CancellationToken cancellationToken) {
        return a(continuation, c, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> b(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return b(continuation, executor, (CancellationToken) null);
    }

    public <TContinuationResult> Task<TContinuationResult> b(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor, CancellationToken cancellationToken) {
        boolean c2;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.e) {
            c2 = c();
            if (!c2) {
                final TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
                final Continuation<TResult, Task<TContinuationResult>> continuation2 = continuation;
                final Executor executor2 = executor;
                final CancellationToken cancellationToken2 = cancellationToken;
                this.l.add(new Continuation<TResult, Void>() {
                    /* renamed from: a */
                    public Void then(Task<TResult> task) {
                        Task.d(taskCompletionSource2, continuation2, task, executor2, cancellationToken2);
                        return null;
                    }
                });
            }
        }
        if (c2) {
            d(taskCompletionSource, continuation, this, executor, cancellationToken);
        }
        return taskCompletionSource.a();
    }

    public <TContinuationResult> Task<TContinuationResult> b(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return b(continuation, c, (CancellationToken) null);
    }

    public <TContinuationResult> Task<TContinuationResult> b(Continuation<TResult, Task<TContinuationResult>> continuation, CancellationToken cancellationToken) {
        return b(continuation, c, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> c(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return c(continuation, executor, (CancellationToken) null);
    }

    public <TContinuationResult> Task<TContinuationResult> c(final Continuation<TResult, TContinuationResult> continuation, Executor executor, final CancellationToken cancellationToken) {
        return b(new Continuation<TResult, Task<TContinuationResult>>() {
            /* renamed from: a */
            public Task<TContinuationResult> then(Task<TResult> task) {
                if (cancellationToken != null && cancellationToken.a()) {
                    return Task.i();
                }
                if (task.e()) {
                    return Task.a(task.g());
                }
                if (task.d()) {
                    return Task.i();
                }
                return task.a((Continuation<TResult, TContinuationResult>) continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> c(Continuation<TResult, TContinuationResult> continuation) {
        return c(continuation, c, (CancellationToken) null);
    }

    public <TContinuationResult> Task<TContinuationResult> c(Continuation<TResult, TContinuationResult> continuation, CancellationToken cancellationToken) {
        return c(continuation, c, cancellationToken);
    }

    public <TContinuationResult> Task<TContinuationResult> d(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return d(continuation, executor, (CancellationToken) null);
    }

    public <TContinuationResult> Task<TContinuationResult> d(final Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor, final CancellationToken cancellationToken) {
        return b(new Continuation<TResult, Task<TContinuationResult>>() {
            /* renamed from: a */
            public Task<TContinuationResult> then(Task<TResult> task) {
                if (cancellationToken != null && cancellationToken.a()) {
                    return Task.i();
                }
                if (task.e()) {
                    return Task.a(task.g());
                }
                if (task.d()) {
                    return Task.i();
                }
                return task.b((Continuation<TResult, Task<TContinuationResult>>) continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> d(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return d(continuation, c);
    }

    public <TContinuationResult> Task<TContinuationResult> d(Continuation<TResult, Task<TContinuationResult>> continuation, CancellationToken cancellationToken) {
        return d(continuation, c, cancellationToken);
    }

    /* access modifiers changed from: private */
    public static <TContinuationResult, TResult> void c(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (cancellationToken == null || !cancellationToken.a()) {
                        try {
                            taskCompletionSource.b(continuation.then(task));
                        } catch (CancellationException unused) {
                            taskCompletionSource.c();
                        } catch (Exception e) {
                            taskCompletionSource.b(e);
                        }
                    } else {
                        taskCompletionSource.c();
                    }
                }
            });
        } catch (Exception e2) {
            taskCompletionSource.b((Exception) new ExecutorException(e2));
        }
    }

    /* access modifiers changed from: private */
    public static <TContinuationResult, TResult> void d(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, Executor executor, final CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (cancellationToken == null || !cancellationToken.a()) {
                        try {
                            Task task = (Task) continuation.then(task);
                            if (task == null) {
                                taskCompletionSource.b(null);
                            } else {
                                task.a(new Continuation<TContinuationResult, Void>() {
                                    /* renamed from: a */
                                    public Void then(Task<TContinuationResult> task) {
                                        if (cancellationToken == null || !cancellationToken.a()) {
                                            if (task.d()) {
                                                taskCompletionSource.c();
                                            } else if (task.e()) {
                                                taskCompletionSource.b(task.g());
                                            } else {
                                                taskCompletionSource.b(task.f());
                                            }
                                            return null;
                                        }
                                        taskCompletionSource.c();
                                        return null;
                                    }
                                });
                            }
                        } catch (CancellationException unused) {
                            taskCompletionSource.c();
                        } catch (Exception e) {
                            taskCompletionSource.b(e);
                        }
                    } else {
                        taskCompletionSource.c();
                    }
                }
            });
        } catch (Exception e2) {
            taskCompletionSource.b((Exception) new ExecutorException(e2));
        }
    }

    private void m() {
        synchronized (this.e) {
            for (Continuation then : this.l) {
                try {
                    then.then(this);
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
            this.l = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean l() {
        synchronized (this.e) {
            if (this.f) {
                return false;
            }
            this.f = true;
            this.g = true;
            this.e.notifyAll();
            m();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(TResult tresult) {
        synchronized (this.e) {
            if (this.f) {
                return false;
            }
            this.f = true;
            this.h = tresult;
            this.e.notifyAll();
            m();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(java.lang.Exception r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.e
            monitor-enter(r0)
            boolean r1 = r3.f     // Catch:{ all -> 0x002c }
            r2 = 0
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return r2
        L_0x000a:
            r1 = 1
            r3.f = r1     // Catch:{ all -> 0x002c }
            r3.i = r4     // Catch:{ all -> 0x002c }
            r3.j = r2     // Catch:{ all -> 0x002c }
            java.lang.Object r4 = r3.e     // Catch:{ all -> 0x002c }
            r4.notifyAll()     // Catch:{ all -> 0x002c }
            r3.m()     // Catch:{ all -> 0x002c }
            boolean r4 = r3.j     // Catch:{ all -> 0x002c }
            if (r4 != 0) goto L_0x002a
            bolts.Task$UnobservedExceptionHandler r4 = a()     // Catch:{ all -> 0x002c }
            if (r4 == 0) goto L_0x002a
            bolts.UnobservedErrorNotifier r4 = new bolts.UnobservedErrorNotifier     // Catch:{ all -> 0x002c }
            r4.<init>(r3)     // Catch:{ all -> 0x002c }
            r3.k = r4     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return r1
        L_0x002c:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.Task.b(java.lang.Exception):boolean");
    }

    public class TaskCompletionSource extends TaskCompletionSource<TResult> {
        TaskCompletionSource() {
        }
    }
}
