package org.xutils.common.task;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.common.Callback;
import org.xutils.common.TaskController;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

public final class TaskControllerImpl implements TaskController {

    /* renamed from: a  reason: collision with root package name */
    private static volatile TaskController f4219a;

    private TaskControllerImpl() {
    }

    public static void a() {
        if (f4219a == null) {
            synchronized (TaskController.class) {
                if (f4219a == null) {
                    f4219a = new TaskControllerImpl();
                }
            }
        }
        x.Ext.a(f4219a);
    }

    public <T> AbsTask<T> a(AbsTask<T> absTask) {
        TaskProxy taskProxy;
        if (absTask instanceof TaskProxy) {
            taskProxy = (TaskProxy) absTask;
        } else {
            taskProxy = new TaskProxy(absTask);
        }
        try {
            taskProxy.c();
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
        return taskProxy;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r4.a(r0, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        r4.f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        r2 = r1;
        r1 = r0;
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a A[ExcHandler: Throwable (r0v2 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T b(org.xutils.common.task.AbsTask<T> r4) throws java.lang.Throwable {
        /*
            r3 = this;
            r0 = 0
            r4.d()     // Catch:{ CancelledException -> 0x0020, Throwable -> 0x001a }
            r4.e()     // Catch:{ CancelledException -> 0x0020, Throwable -> 0x001a }
            java.lang.Object r1 = r4.c()     // Catch:{ CancelledException -> 0x0020, Throwable -> 0x001a }
            r4.a(r1)     // Catch:{ CancelledException -> 0x0013, Throwable -> 0x001a }
            r4.f()
            r0 = r1
            goto L_0x0027
        L_0x0013:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L_0x0021
        L_0x0018:
            r0 = move-exception
            goto L_0x0028
        L_0x001a:
            r0 = move-exception
            r1 = 0
            r4.a((java.lang.Throwable) r0, (boolean) r1)     // Catch:{ all -> 0x0018 }
            throw r0     // Catch:{ all -> 0x0018 }
        L_0x0020:
            r1 = move-exception
        L_0x0021:
            r4.a((org.xutils.common.Callback.CancelledException) r1)     // Catch:{ all -> 0x0018 }
            r4.f()
        L_0x0027:
            return r0
        L_0x0028:
            r4.f()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.common.task.TaskControllerImpl.b(org.xutils.common.task.AbsTask):java.lang.Object");
    }

    public <T extends AbsTask<?>> Callback.Cancelable a(final Callback.GroupCallback<T> groupCallback, final T... tArr) {
        if (tArr != null) {
            AnonymousClass1 r6 = new Runnable() {
                private final int d = tArr.length;
                private final AtomicInteger e = new AtomicInteger(0);

                public void run() {
                    if (this.e.incrementAndGet() == this.d && groupCallback != null) {
                        groupCallback.a();
                    }
                }
            };
            for (final T t : tArr) {
                final Callback.GroupCallback<T> groupCallback2 = groupCallback;
                final AnonymousClass1 r5 = r6;
                a(new TaskProxy(t) {
                    /* access modifiers changed from: protected */
                    public void a(Object obj) {
                        super.a(obj);
                        TaskControllerImpl.this.b((Runnable) new Runnable() {
                            public void run() {
                                if (groupCallback2 != null) {
                                    groupCallback2.a(t);
                                }
                            }
                        });
                    }

                    /* access modifiers changed from: protected */
                    public void a(final Callback.CancelledException cancelledException) {
                        super.a(cancelledException);
                        TaskControllerImpl.this.b((Runnable) new Runnable() {
                            public void run() {
                                if (groupCallback2 != null) {
                                    groupCallback2.a(t, cancelledException);
                                }
                            }
                        });
                    }

                    /* access modifiers changed from: protected */
                    public void a(final Throwable th, final boolean z) {
                        super.a(th, z);
                        TaskControllerImpl.this.b((Runnable) new Runnable() {
                            public void run() {
                                if (groupCallback2 != null) {
                                    groupCallback2.a(t, th, z);
                                }
                            }
                        });
                    }

                    /* access modifiers changed from: protected */
                    public void f() {
                        super.f();
                        TaskControllerImpl.this.b((Runnable) new Runnable() {
                            public void run() {
                                if (groupCallback2 != null) {
                                    groupCallback2.b(t);
                                }
                                r5.run();
                            }
                        });
                    }
                });
            }
            return new Callback.Cancelable() {
                public void a() {
                    for (AbsTask a2 : tArr) {
                        a2.a();
                    }
                }

                public boolean b() {
                    boolean z = true;
                    for (AbsTask b2 : tArr) {
                        if (!b2.b()) {
                            z = false;
                        }
                    }
                    return z;
                }
            };
        }
        throw new IllegalArgumentException("task must not be null");
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                runnable.run();
            } else {
                TaskProxy.e.post(runnable);
            }
        }
    }

    public void b(Runnable runnable) {
        if (runnable != null) {
            TaskProxy.e.post(runnable);
        }
    }

    public void a(Runnable runnable, long j) {
        if (runnable != null) {
            TaskProxy.e.postDelayed(runnable, j);
        }
    }

    public void c(Runnable runnable) {
        if (!TaskProxy.f.c()) {
            TaskProxy.f.execute(runnable);
        } else {
            new Thread(runnable).start();
        }
    }

    public void d(Runnable runnable) {
        TaskProxy.e.removeCallbacks(runnable);
    }
}
