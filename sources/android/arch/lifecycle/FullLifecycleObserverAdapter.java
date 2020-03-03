package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    private final FullLifecycleObserver f438a;

    FullLifecycleObserverAdapter(FullLifecycleObserver fullLifecycleObserver) {
        this.f438a = fullLifecycleObserver;
    }

    public void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        switch (event) {
            case ON_CREATE:
                this.f438a.a(lifecycleOwner);
                return;
            case ON_START:
                this.f438a.b(lifecycleOwner);
                return;
            case ON_RESUME:
                this.f438a.c(lifecycleOwner);
                return;
            case ON_PAUSE:
                this.f438a.d(lifecycleOwner);
                return;
            case ON_STOP:
                this.f438a.e(lifecycleOwner);
                return;
            case ON_DESTROY:
                this.f438a.f(lifecycleOwner);
                return;
            case ON_ANY:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                return;
        }
    }
}
