package com.ximalaya.ting.android.sdkdownloader.task;

import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.sdkdownloader.task.Callback;
import java.util.concurrent.Executor;

public abstract class AbsTask<ResultType> implements Callback.Cancelable {

    /* renamed from: a  reason: collision with root package name */
    private TaskProxy f2367a;
    private final Callback.Cancelable b;
    private volatile boolean c;
    private volatile boolean d;
    private volatile State e;
    private ResultType f;

    /* access modifiers changed from: protected */
    public abstract ResultType a() throws Throwable;

    /* access modifiers changed from: protected */
    public void a(int i, Object... objArr) {
    }

    /* access modifiers changed from: protected */
    public void a(Callback.CancelledException cancelledException) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(Callback.RemovedException removedException);

    /* access modifiers changed from: protected */
    public abstract void a(ResultType resulttype);

    /* access modifiers changed from: protected */
    public abstract void a(Throwable th, boolean z);

    /* access modifiers changed from: protected */
    public void a(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    /* access modifiers changed from: protected */
    public void e() {
    }

    /* access modifiers changed from: protected */
    public boolean j() {
        return false;
    }

    public Executor k() {
        return null;
    }

    public AbsTask() {
        this((Callback.Cancelable) null);
    }

    public AbsTask(Callback.Cancelable cancelable) {
        this.f2367a = null;
        this.c = false;
        this.d = false;
        this.e = State.IDLE;
        this.b = cancelable;
    }

    public Priority l() {
        return Priority.DEFAULT;
    }

    /* access modifiers changed from: protected */
    public final void b(int i, Object... objArr) {
        if (this.f2367a != null) {
            this.f2367a.a(i, objArr);
        }
    }

    public final synchronized void d() {
        if (!this.c) {
            this.c = true;
            a(false);
            if (this.b != null && !this.b.f()) {
                Logger.a((Object) "AbsTask :  123");
                this.b.d();
            }
            if (this.e == State.WAITING || (this.e == State.STARTED && j())) {
                if (this.f2367a != null) {
                    this.f2367a.a(new Callback.CancelledException("cancelled by user"));
                    this.f2367a.e();
                } else if (this instanceof TaskProxy) {
                    a(new Callback.CancelledException("cancelled by user"));
                    e();
                }
            }
        }
    }

    public final synchronized void h() {
        if (!this.d) {
            this.d = true;
            a(true);
            if (this.b != null) {
                this.b.h();
            }
            if (this.f2367a != null) {
                this.f2367a.a(new Callback.RemovedException("removed by user"));
                this.f2367a.e();
            } else if (this instanceof TaskProxy) {
                a(new Callback.RemovedException("removed by user"));
                e();
            }
        }
    }

    public final boolean f() {
        return this.c || this.e == State.CANCELLED || (this.b != null && this.b.f());
    }

    public final boolean g() {
        return this.d || this.e == State.REMOVED || (this.b != null && this.b.g());
    }

    public final boolean n() {
        return this.e.value() > State.STARTED.value();
    }

    public final State o() {
        return this.e;
    }

    public final ResultType p() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void a(State state) {
        this.e = state;
    }

    /* access modifiers changed from: package-private */
    public final void a(TaskProxy taskProxy) {
        this.f2367a = taskProxy;
    }

    /* access modifiers changed from: package-private */
    public final void b(ResultType resulttype) {
        this.f = resulttype;
    }

    public enum State {
        IDLE(0),
        WAITING(1),
        STARTED(2),
        SUCCESS(3),
        CANCELLED(4),
        ERROR(5),
        REMOVED(6);
        
        private final int value;

        private State(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }
}
