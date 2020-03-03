package com.google.common.util.concurrent;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;

@Beta
public abstract class AbstractService implements Service {
    private static final ListenerCallQueue.Callback<Service.Listener> RUNNING_CALLBACK = new ListenerCallQueue.Callback<Service.Listener>("running()") {
        /* access modifiers changed from: package-private */
        public void call(Service.Listener listener) {
            listener.running();
        }
    };
    private static final ListenerCallQueue.Callback<Service.Listener> STARTING_CALLBACK = new ListenerCallQueue.Callback<Service.Listener>("starting()") {
        /* access modifiers changed from: package-private */
        public void call(Service.Listener listener) {
            listener.starting();
        }
    };
    private static final ListenerCallQueue.Callback<Service.Listener> STOPPING_FROM_RUNNING_CALLBACK = stoppingCallback(Service.State.RUNNING);
    private static final ListenerCallQueue.Callback<Service.Listener> STOPPING_FROM_STARTING_CALLBACK = stoppingCallback(Service.State.STARTING);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_NEW_CALLBACK = terminatedCallback(Service.State.NEW);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_RUNNING_CALLBACK = terminatedCallback(Service.State.RUNNING);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_STOPPING_CALLBACK = terminatedCallback(Service.State.STOPPING);
    private final Monitor.Guard hasReachedRunning = new Monitor.Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) >= 0;
        }
    };
    private final Monitor.Guard isStartable = new Monitor.Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state() == Service.State.NEW;
        }
    };
    private final Monitor.Guard isStoppable = new Monitor.Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) <= 0;
        }
    };
    private final Monitor.Guard isStopped = new Monitor.Guard(this.monitor) {
        public boolean isSatisfied() {
            return AbstractService.this.state().isTerminal();
        }
    };
    @GuardedBy("monitor")
    private final List<ListenerCallQueue<Service.Listener>> listeners = Collections.synchronizedList(new ArrayList());
    private final Monitor monitor = new Monitor();
    @GuardedBy("monitor")
    private volatile StateSnapshot snapshot = new StateSnapshot(Service.State.NEW);

    /* access modifiers changed from: protected */
    public abstract void doStart();

    /* access modifiers changed from: protected */
    public abstract void doStop();

    private static ListenerCallQueue.Callback<Service.Listener> terminatedCallback(final Service.State state) {
        String valueOf = String.valueOf(String.valueOf(state));
        StringBuilder sb = new StringBuilder(valueOf.length() + 21);
        sb.append("terminated({from = ");
        sb.append(valueOf);
        sb.append("})");
        return new ListenerCallQueue.Callback<Service.Listener>(sb.toString()) {
            /* access modifiers changed from: package-private */
            public void call(Service.Listener listener) {
                listener.terminated(state);
            }
        };
    }

    private static ListenerCallQueue.Callback<Service.Listener> stoppingCallback(final Service.State state) {
        String valueOf = String.valueOf(String.valueOf(state));
        StringBuilder sb = new StringBuilder(valueOf.length() + 19);
        sb.append("stopping({from = ");
        sb.append(valueOf);
        sb.append("})");
        return new ListenerCallQueue.Callback<Service.Listener>(sb.toString()) {
            /* access modifiers changed from: package-private */
            public void call(Service.Listener listener) {
                listener.stopping(state);
            }
        };
    }

    protected AbstractService() {
    }

    public final Service startAsync() {
        if (this.monitor.enterIf(this.isStartable)) {
            try {
                this.snapshot = new StateSnapshot(Service.State.STARTING);
                starting();
                doStart();
            } catch (Throwable th) {
                this.monitor.leave();
                executeListeners();
                throw th;
            }
            this.monitor.leave();
            executeListeners();
            return this;
        }
        String valueOf = String.valueOf(String.valueOf(this));
        StringBuilder sb = new StringBuilder(valueOf.length() + 33);
        sb.append("Service ");
        sb.append(valueOf);
        sb.append(" has already been started");
        throw new IllegalStateException(sb.toString());
    }

    public final Service stopAsync() {
        if (this.monitor.enterIf(this.isStoppable)) {
            try {
                Service.State state = state();
                switch (state) {
                    case NEW:
                        this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                        terminated(Service.State.NEW);
                        break;
                    case STARTING:
                        this.snapshot = new StateSnapshot(Service.State.STARTING, true, (Throwable) null);
                        stopping(Service.State.STARTING);
                        break;
                    case RUNNING:
                        this.snapshot = new StateSnapshot(Service.State.STOPPING);
                        stopping(Service.State.RUNNING);
                        doStop();
                        break;
                    case STOPPING:
                    case TERMINATED:
                    case FAILED:
                        String valueOf = String.valueOf(String.valueOf(state));
                        StringBuilder sb = new StringBuilder(valueOf.length() + 45);
                        sb.append("isStoppable is incorrectly implemented, saw: ");
                        sb.append(valueOf);
                        throw new AssertionError(sb.toString());
                    default:
                        String valueOf2 = String.valueOf(String.valueOf(state));
                        StringBuilder sb2 = new StringBuilder(valueOf2.length() + 18);
                        sb2.append("Unexpected state: ");
                        sb2.append(valueOf2);
                        throw new AssertionError(sb2.toString());
                }
            } catch (Throwable th) {
                this.monitor.leave();
                executeListeners();
                throw th;
            }
            this.monitor.leave();
            executeListeners();
        }
        return this;
    }

    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            checkCurrentState(Service.State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, j, timeUnit)) {
            try {
                checkCurrentState(Service.State.RUNNING);
            } finally {
                this.monitor.leave();
            }
        } else {
            String valueOf = String.valueOf(String.valueOf(this));
            String valueOf2 = String.valueOf(String.valueOf(state()));
            StringBuilder sb = new StringBuilder(valueOf.length() + 66 + valueOf2.length());
            sb.append("Timed out waiting for ");
            sb.append(valueOf);
            sb.append(" to reach the RUNNING state. ");
            sb.append("Current state: ");
            sb.append(valueOf2);
            throw new TimeoutException(sb.toString());
        }
    }

    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            checkCurrentState(Service.State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, j, timeUnit)) {
            try {
                checkCurrentState(Service.State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            String valueOf = String.valueOf(String.valueOf(this));
            String valueOf2 = String.valueOf(String.valueOf(state()));
            StringBuilder sb = new StringBuilder(valueOf.length() + 65 + valueOf2.length());
            sb.append("Timed out waiting for ");
            sb.append(valueOf);
            sb.append(" to reach a terminal state. ");
            sb.append("Current state: ");
            sb.append(valueOf2);
            throw new TimeoutException(sb.toString());
        }
    }

    @GuardedBy("monitor")
    private void checkCurrentState(Service.State state) {
        Service.State state2 = state();
        if (state2 == state) {
            return;
        }
        if (state2 == Service.State.FAILED) {
            String valueOf = String.valueOf(String.valueOf(state));
            StringBuilder sb = new StringBuilder(valueOf.length() + 55);
            sb.append("Expected the service to be ");
            sb.append(valueOf);
            sb.append(", but the service has FAILED");
            throw new IllegalStateException(sb.toString(), failureCause());
        }
        String valueOf2 = String.valueOf(String.valueOf(state));
        String valueOf3 = String.valueOf(String.valueOf(state2));
        StringBuilder sb2 = new StringBuilder(valueOf2.length() + 37 + valueOf3.length());
        sb2.append("Expected the service to be ");
        sb2.append(valueOf2);
        sb2.append(", but was ");
        sb2.append(valueOf3);
        throw new IllegalStateException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public final void notifyStarted() {
        this.monitor.enter();
        try {
            if (this.snapshot.state == Service.State.STARTING) {
                if (this.snapshot.shutdownWhenStartupFinishes) {
                    this.snapshot = new StateSnapshot(Service.State.STOPPING);
                    doStop();
                } else {
                    this.snapshot = new StateSnapshot(Service.State.RUNNING);
                    running();
                }
                return;
            }
            String valueOf = String.valueOf(String.valueOf(this.snapshot.state));
            StringBuilder sb = new StringBuilder(valueOf.length() + 43);
            sb.append("Cannot notifyStarted() when the service is ");
            sb.append(valueOf);
            IllegalStateException illegalStateException = new IllegalStateException(sb.toString());
            notifyFailed(illegalStateException);
            throw illegalStateException;
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyStopped() {
        this.monitor.enter();
        try {
            Service.State state = this.snapshot.state;
            if (state != Service.State.STOPPING) {
                if (state != Service.State.RUNNING) {
                    String valueOf = String.valueOf(String.valueOf(state));
                    StringBuilder sb = new StringBuilder(valueOf.length() + 43);
                    sb.append("Cannot notifyStopped() when the service is ");
                    sb.append(valueOf);
                    IllegalStateException illegalStateException = new IllegalStateException(sb.toString());
                    notifyFailed(illegalStateException);
                    throw illegalStateException;
                }
            }
            this.snapshot = new StateSnapshot(Service.State.TERMINATED);
            terminated(state);
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyFailed(Throwable th) {
        Preconditions.checkNotNull(th);
        this.monitor.enter();
        try {
            Service.State state = state();
            switch (state) {
                case NEW:
                case TERMINATED:
                    String valueOf = String.valueOf(String.valueOf(state));
                    StringBuilder sb = new StringBuilder(valueOf.length() + 22);
                    sb.append("Failed while in state:");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString(), th);
                case STARTING:
                case RUNNING:
                case STOPPING:
                    this.snapshot = new StateSnapshot(Service.State.FAILED, false, th);
                    failed(state, th);
                    break;
                case FAILED:
                    break;
                default:
                    String valueOf2 = String.valueOf(String.valueOf(state));
                    StringBuilder sb2 = new StringBuilder(valueOf2.length() + 18);
                    sb2.append("Unexpected state: ");
                    sb2.append(valueOf2);
                    throw new AssertionError(sb2.toString());
            }
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    public final boolean isRunning() {
        return state() == Service.State.RUNNING;
    }

    public final Service.State state() {
        return this.snapshot.externalState();
    }

    public final Throwable failureCause() {
        return this.snapshot.failureCause();
    }

    public final void addListener(Service.Listener listener, Executor executor) {
        Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.monitor.enter();
        try {
            if (!state().isTerminal()) {
                this.listeners.add(new ListenerCallQueue(listener, executor));
            }
        } finally {
            this.monitor.leave();
        }
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(getClass().getSimpleName()));
        String valueOf2 = String.valueOf(String.valueOf(state()));
        StringBuilder sb = new StringBuilder(valueOf.length() + 3 + valueOf2.length());
        sb.append(valueOf);
        sb.append(" [");
        sb.append(valueOf2);
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    private void executeListeners() {
        if (!this.monitor.isOccupiedByCurrentThread()) {
            for (int i = 0; i < this.listeners.size(); i++) {
                this.listeners.get(i).execute();
            }
        }
    }

    @GuardedBy("monitor")
    private void starting() {
        STARTING_CALLBACK.enqueueOn(this.listeners);
    }

    @GuardedBy("monitor")
    private void running() {
        RUNNING_CALLBACK.enqueueOn(this.listeners);
    }

    @GuardedBy("monitor")
    private void stopping(Service.State state) {
        if (state == Service.State.STARTING) {
            STOPPING_FROM_STARTING_CALLBACK.enqueueOn(this.listeners);
        } else if (state == Service.State.RUNNING) {
            STOPPING_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
        } else {
            throw new AssertionError();
        }
    }

    @GuardedBy("monitor")
    private void terminated(Service.State state) {
        int i = AnonymousClass10.$SwitchMap$com$google$common$util$concurrent$Service$State[state.ordinal()];
        if (i != 1) {
            switch (i) {
                case 3:
                    TERMINATED_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
                    return;
                case 4:
                    TERMINATED_FROM_STOPPING_CALLBACK.enqueueOn(this.listeners);
                    return;
                default:
                    throw new AssertionError();
            }
        } else {
            TERMINATED_FROM_NEW_CALLBACK.enqueueOn(this.listeners);
        }
    }

    @GuardedBy("monitor")
    private void failed(final Service.State state, final Throwable th) {
        String valueOf = String.valueOf(String.valueOf(state));
        String valueOf2 = String.valueOf(String.valueOf(th));
        StringBuilder sb = new StringBuilder(valueOf.length() + 27 + valueOf2.length());
        sb.append("failed({from = ");
        sb.append(valueOf);
        sb.append(", cause = ");
        sb.append(valueOf2);
        sb.append("})");
        new ListenerCallQueue.Callback<Service.Listener>(sb.toString()) {
            /* access modifiers changed from: package-private */
            public void call(Service.Listener listener) {
                listener.failed(state, th);
            }
        }.enqueueOn(this.listeners);
    }

    @Immutable
    private static final class StateSnapshot {
        @Nullable
        final Throwable failure;
        final boolean shutdownWhenStartupFinishes;
        final Service.State state;

        StateSnapshot(Service.State state2) {
            this(state2, false, (Throwable) null);
        }

        StateSnapshot(Service.State state2, boolean z, @Nullable Throwable th) {
            Preconditions.checkArgument(!z || state2 == Service.State.STARTING, "shudownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", state2);
            Preconditions.checkArgument(!((th != null) ^ (state2 == Service.State.FAILED)), "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", state2, th);
            this.state = state2;
            this.shutdownWhenStartupFinishes = z;
            this.failure = th;
        }

        /* access modifiers changed from: package-private */
        public Service.State externalState() {
            if (!this.shutdownWhenStartupFinishes || this.state != Service.State.STARTING) {
                return this.state;
            }
            return Service.State.STOPPING;
        }

        /* access modifiers changed from: package-private */
        public Throwable failureCause() {
            Preconditions.checkState(this.state == Service.State.FAILED, "failureCause() is only valid if the service has failed, service is %s", this.state);
            return this.failure;
        }
    }
}
