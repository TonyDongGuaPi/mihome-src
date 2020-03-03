package org.xutils.common.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.Executor;
import org.xutils.common.Callback;
import org.xutils.common.task.AbsTask;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

class TaskProxy<ResultType> extends AbsTask<ResultType> {
    static final InternalHandler e = new InternalHandler();
    static final PriorityExecutor f = new PriorityExecutor(true);
    private static final int g = 1000000000;
    private static final int h = 1000000001;
    private static final int i = 1000000002;
    private static final int j = 1000000003;
    private static final int k = 1000000004;
    private static final int l = 1000000005;
    private static final int m = 1000000006;
    private static final int n = 1000000007;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final AbsTask<ResultType> f4227a;
    private final Executor b;
    /* access modifiers changed from: private */
    public volatile boolean c = false;
    /* access modifiers changed from: private */
    public volatile boolean d = false;

    TaskProxy(AbsTask<ResultType> absTask) {
        super(absTask);
        this.f4227a = absTask;
        this.f4227a.a(this);
        a((TaskProxy) null);
        Executor h2 = absTask.h();
        this.b = h2 == null ? f : h2;
    }

    /* access modifiers changed from: protected */
    public final ResultType c() throws Throwable {
        d();
        this.b.execute(new PriorityRunnable(this.f4227a.g(), new Runnable() {
            public void run() {
                try {
                    if (TaskProxy.this.c || TaskProxy.this.b()) {
                        throw new Callback.CancelledException("");
                    }
                    TaskProxy.this.e();
                    if (!TaskProxy.this.b()) {
                        TaskProxy.this.f4227a.b(TaskProxy.this.f4227a.c());
                        TaskProxy.this.b(TaskProxy.this.f4227a.m());
                        if (!TaskProxy.this.b()) {
                            TaskProxy.this.a(TaskProxy.this.f4227a.m());
                            TaskProxy.this.f();
                            return;
                        }
                        throw new Callback.CancelledException("");
                    }
                    throw new Callback.CancelledException("");
                } catch (Callback.CancelledException e) {
                    TaskProxy.this.a(e);
                } catch (Throwable th) {
                    TaskProxy.this.f();
                    throw th;
                }
            }
        }));
        return null;
    }

    /* access modifiers changed from: protected */
    public void d() {
        a(AbsTask.State.WAITING);
        e.obtainMessage(h, this).sendToTarget();
    }

    /* access modifiers changed from: protected */
    public void e() {
        a(AbsTask.State.STARTED);
        e.obtainMessage(i, this).sendToTarget();
    }

    /* access modifiers changed from: protected */
    public void a(ResultType resulttype) {
        a(AbsTask.State.SUCCESS);
        e.obtainMessage(j, this).sendToTarget();
    }

    /* access modifiers changed from: protected */
    public void a(Throwable th, boolean z) {
        a(AbsTask.State.ERROR);
        e.obtainMessage(k, new ArgsObj(this, th)).sendToTarget();
    }

    /* access modifiers changed from: protected */
    public void a(int i2, Object... objArr) {
        e.obtainMessage(l, i2, i2, new ArgsObj(this, objArr)).sendToTarget();
    }

    /* access modifiers changed from: protected */
    public void a(Callback.CancelledException cancelledException) {
        a(AbsTask.State.CANCELLED);
        e.obtainMessage(m, new ArgsObj(this, cancelledException)).sendToTarget();
    }

    /* access modifiers changed from: protected */
    public void f() {
        e.obtainMessage(n, this).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public final void a(AbsTask.State state) {
        super.a(state);
        this.f4227a.a(state);
    }

    public final Priority g() {
        return this.f4227a.g();
    }

    public final Executor h() {
        return this.b;
    }

    private static class ArgsObj {

        /* renamed from: a  reason: collision with root package name */
        final TaskProxy f4229a;
        final Object[] b;

        public ArgsObj(TaskProxy taskProxy, Object... objArr) {
            this.f4229a = taskProxy;
            this.b = objArr;
        }
    }

    static final class InternalHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ boolean f4230a = (!TaskProxy.class.desiredAssertionStatus());

        private InternalHandler() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            Object[] objArr;
            if (message.obj != null) {
                TaskProxy taskProxy = null;
                if (message.obj instanceof TaskProxy) {
                    taskProxy = (TaskProxy) message.obj;
                    objArr = null;
                } else if (message.obj instanceof ArgsObj) {
                    ArgsObj argsObj = (ArgsObj) message.obj;
                    taskProxy = argsObj.f4229a;
                    objArr = argsObj.b;
                } else {
                    objArr = null;
                }
                if (taskProxy != null) {
                    try {
                        switch (message.what) {
                            case TaskProxy.h /*1000000001*/:
                                taskProxy.f4227a.d();
                                return;
                            case TaskProxy.i /*1000000002*/:
                                taskProxy.f4227a.e();
                                return;
                            case TaskProxy.j /*1000000003*/:
                                taskProxy.f4227a.a(taskProxy.m());
                                return;
                            case TaskProxy.k /*1000000004*/:
                                if (!f4230a) {
                                    if (objArr == null) {
                                        throw new AssertionError();
                                    }
                                }
                                Throwable th = (Throwable) objArr[0];
                                LogUtil.a(th.getMessage(), th);
                                taskProxy.f4227a.a(th, false);
                                return;
                            case TaskProxy.l /*1000000005*/:
                                taskProxy.f4227a.a(message.arg1, objArr);
                                return;
                            case TaskProxy.m /*1000000006*/:
                                if (!taskProxy.c) {
                                    boolean unused = taskProxy.c = true;
                                    if (!f4230a) {
                                        if (objArr == null) {
                                            throw new AssertionError();
                                        }
                                    }
                                    taskProxy.f4227a.a((Callback.CancelledException) objArr[0]);
                                    return;
                                }
                                return;
                            case TaskProxy.n /*1000000007*/:
                                if (!taskProxy.d) {
                                    boolean unused2 = taskProxy.d = true;
                                    taskProxy.f4227a.f();
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    } catch (Throwable th2) {
                        taskProxy.a(AbsTask.State.ERROR);
                        if (message.what != TaskProxy.k) {
                            taskProxy.f4227a.a(th2, true);
                        } else if (x.a()) {
                            throw new RuntimeException(th2);
                        }
                    }
                } else {
                    throw new RuntimeException("msg.obj not instanceof TaskProxy");
                }
            } else {
                throw new IllegalArgumentException("msg must not be null");
            }
        }
    }
}
