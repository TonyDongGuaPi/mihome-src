package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.os.Handler;
import android.support.annotation.NonNull;

public class ServiceLifecycleDispatcher {

    /* renamed from: a  reason: collision with root package name */
    private final LifecycleRegistry f463a;
    private final Handler b = new Handler();
    private DispatchRunnable c;

    public ServiceLifecycleDispatcher(@NonNull LifecycleOwner lifecycleOwner) {
        this.f463a = new LifecycleRegistry(lifecycleOwner);
    }

    private void a(Lifecycle.Event event) {
        if (this.c != null) {
            this.c.run();
        }
        this.c = new DispatchRunnable(this.f463a, event);
        this.b.postAtFrontOfQueue(this.c);
    }

    public void a() {
        a(Lifecycle.Event.ON_CREATE);
    }

    public void b() {
        a(Lifecycle.Event.ON_START);
    }

    public void c() {
        a(Lifecycle.Event.ON_START);
    }

    public void d() {
        a(Lifecycle.Event.ON_STOP);
        a(Lifecycle.Event.ON_DESTROY);
    }

    public Lifecycle e() {
        return this.f463a;
    }

    static class DispatchRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final Lifecycle.Event f464a;
        private final LifecycleRegistry b;
        private boolean c = false;

        DispatchRunnable(@NonNull LifecycleRegistry lifecycleRegistry, Lifecycle.Event event) {
            this.b = lifecycleRegistry;
            this.f464a = event;
        }

        public void run() {
            if (!this.c) {
                this.b.a(this.f464a);
                this.c = true;
            }
        }
    }
}
