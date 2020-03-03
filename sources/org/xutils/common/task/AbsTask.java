package org.xutils.common.task;

import java.util.concurrent.Executor;
import org.xutils.common.Callback;

public abstract class AbsTask<ResultType> implements Callback.Cancelable {

    /* renamed from: a  reason: collision with root package name */
    private TaskProxy f4215a;
    private final Callback.Cancelable b;
    private volatile boolean c;
    private volatile State d;
    private ResultType e;

    /* access modifiers changed from: protected */
    public void a(int i, Object... objArr) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(ResultType resulttype);

    /* access modifiers changed from: protected */
    public abstract void a(Throwable th, boolean z);

    /* access modifiers changed from: protected */
    public void a(Callback.CancelledException cancelledException) {
    }

    /* access modifiers changed from: protected */
    public abstract ResultType c() throws Throwable;

    /* access modifiers changed from: protected */
    public void d() {
    }

    /* access modifiers changed from: protected */
    public void e() {
    }

    /* access modifiers changed from: protected */
    public void f() {
    }

    public Priority g() {
        return null;
    }

    public Executor h() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void i() {
    }

    /* access modifiers changed from: protected */
    public boolean j() {
        return false;
    }

    public AbsTask() {
        this((Callback.Cancelable) null);
    }

    public AbsTask(Callback.Cancelable cancelable) {
        this.f4215a = null;
        this.c = false;
        this.d = State.IDLE;
        this.b = cancelable;
    }

    /* access modifiers changed from: protected */
    public final void b(int i, Object... objArr) {
        if (this.f4215a != null) {
            this.f4215a.a(i, objArr);
        }
    }

    public final synchronized void a() {
        if (!this.c) {
            this.c = true;
            i();
            if (this.b != null && !this.b.b()) {
                this.b.a();
            }
            if (this.d == State.WAITING || (this.d == State.STARTED && j())) {
                if (this.f4215a != null) {
                    this.f4215a.a(new Callback.CancelledException("cancelled by user"));
                    this.f4215a.f();
                } else if (this instanceof TaskProxy) {
                    a(new Callback.CancelledException("cancelled by user"));
                    f();
                }
            }
        }
    }

    public final boolean b() {
        return this.c || this.d == State.CANCELLED || (this.b != null && this.b.b());
    }

    public final boolean k() {
        return this.d.value() > State.STARTED.value();
    }

    public final State l() {
        return this.d;
    }

    public final ResultType m() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public void a(State state) {
        this.d = state;
    }

    /* access modifiers changed from: package-private */
    public final void a(TaskProxy taskProxy) {
        this.f4215a = taskProxy;
    }

    /* access modifiers changed from: package-private */
    public final void b(ResultType resulttype) {
        this.e = resulttype;
    }

    public enum State {
        IDLE(0),
        WAITING(1),
        STARTED(2),
        SUCCESS(3),
        CANCELLED(4),
        ERROR(5);
        
        private final int value;

        private State(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }
}
