package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.core.internal.SafeIterableMap;
import android.arch.lifecycle.Lifecycle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class LifecycleRegistry extends Lifecycle {

    /* renamed from: a  reason: collision with root package name */
    private static final String f446a = "LifecycleRegistry";
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> b = new FastSafeIterableMap<>();
    private Lifecycle.State c;
    private final WeakReference<LifecycleOwner> d;
    private int e = 0;
    private boolean f = false;
    private boolean g = false;
    private ArrayList<Lifecycle.State> h = new ArrayList<>();

    public LifecycleRegistry(@NonNull LifecycleOwner lifecycleOwner) {
        this.d = new WeakReference<>(lifecycleOwner);
        this.c = Lifecycle.State.INITIALIZED;
    }

    @MainThread
    public void a(@NonNull Lifecycle.State state) {
        b(state);
    }

    public void a(@NonNull Lifecycle.Event event) {
        b(b(event));
    }

    private void b(Lifecycle.State state) {
        if (this.c != state) {
            this.c = state;
            if (this.f || this.e != 0) {
                this.g = true;
                return;
            }
            this.f = true;
            e();
            this.f = false;
        }
    }

    private boolean c() {
        if (this.b.a() == 0) {
            return true;
        }
        Lifecycle.State state = this.b.d().getValue().f448a;
        Lifecycle.State state2 = this.b.e().getValue().f448a;
        if (state == state2 && this.c == state2) {
            return true;
        }
        return false;
    }

    private Lifecycle.State c(LifecycleObserver lifecycleObserver) {
        Map.Entry<LifecycleObserver, ObserverWithState> d2 = this.b.d(lifecycleObserver);
        Lifecycle.State state = null;
        Lifecycle.State state2 = d2 != null ? d2.getValue().f448a : null;
        if (!this.h.isEmpty()) {
            state = this.h.get(this.h.size() - 1);
        }
        return a(a(this.c, state2), state);
    }

    public void a(@NonNull LifecycleObserver lifecycleObserver) {
        LifecycleOwner lifecycleOwner;
        ObserverWithState observerWithState = new ObserverWithState(lifecycleObserver, this.c == Lifecycle.State.DESTROYED ? Lifecycle.State.DESTROYED : Lifecycle.State.INITIALIZED);
        if (this.b.a(lifecycleObserver, observerWithState) == null && (lifecycleOwner = (LifecycleOwner) this.d.get()) != null) {
            boolean z = this.e != 0 || this.f;
            Lifecycle.State c2 = c(lifecycleObserver);
            this.e++;
            while (observerWithState.f448a.compareTo(c2) < 0 && this.b.c(lifecycleObserver)) {
                c(observerWithState.f448a);
                observerWithState.a(lifecycleOwner, e(observerWithState.f448a));
                d();
                c2 = c(lifecycleObserver);
            }
            if (!z) {
                e();
            }
            this.e--;
        }
    }

    private void d() {
        this.h.remove(this.h.size() - 1);
    }

    private void c(Lifecycle.State state) {
        this.h.add(state);
    }

    public void b(@NonNull LifecycleObserver lifecycleObserver) {
        this.b.b(lifecycleObserver);
    }

    public int b() {
        return this.b.a();
    }

    @NonNull
    public Lifecycle.State a() {
        return this.c;
    }

    static Lifecycle.State b(Lifecycle.Event event) {
        switch (event) {
            case ON_CREATE:
            case ON_STOP:
                return Lifecycle.State.CREATED;
            case ON_START:
            case ON_PAUSE:
                return Lifecycle.State.STARTED;
            case ON_RESUME:
                return Lifecycle.State.RESUMED;
            case ON_DESTROY:
                return Lifecycle.State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    private static Lifecycle.Event d(Lifecycle.State state) {
        switch (state) {
            case INITIALIZED:
                throw new IllegalArgumentException();
            case CREATED:
                return Lifecycle.Event.ON_DESTROY;
            case STARTED:
                return Lifecycle.Event.ON_STOP;
            case RESUMED:
                return Lifecycle.Event.ON_PAUSE;
            case DESTROYED:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + state);
        }
    }

    private static Lifecycle.Event e(Lifecycle.State state) {
        switch (state) {
            case INITIALIZED:
            case DESTROYED:
                return Lifecycle.Event.ON_CREATE;
            case CREATED:
                return Lifecycle.Event.ON_START;
            case STARTED:
                return Lifecycle.Event.ON_RESUME;
            case RESUMED:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + state);
        }
    }

    private void a(LifecycleOwner lifecycleOwner) {
        SafeIterableMap<K, V>.IteratorWithAdditions c2 = this.b.c();
        while (c2.hasNext() && !this.g) {
            Map.Entry entry = (Map.Entry) c2.next();
            ObserverWithState observerWithState = (ObserverWithState) entry.getValue();
            while (observerWithState.f448a.compareTo(this.c) < 0 && !this.g && this.b.c(entry.getKey())) {
                c(observerWithState.f448a);
                observerWithState.a(lifecycleOwner, e(observerWithState.f448a));
                d();
            }
        }
    }

    private void b(LifecycleOwner lifecycleOwner) {
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> b2 = this.b.b();
        while (b2.hasNext() && !this.g) {
            Map.Entry next = b2.next();
            ObserverWithState observerWithState = (ObserverWithState) next.getValue();
            while (observerWithState.f448a.compareTo(this.c) > 0 && !this.g && this.b.c(next.getKey())) {
                Lifecycle.Event d2 = d(observerWithState.f448a);
                c(b(d2));
                observerWithState.a(lifecycleOwner, d2);
                d();
            }
        }
    }

    private void e() {
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.d.get();
        if (lifecycleOwner == null) {
            Log.w(f446a, "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
        while (!c()) {
            this.g = false;
            if (this.c.compareTo(this.b.d().getValue().f448a) < 0) {
                b(lifecycleOwner);
            }
            Map.Entry<LifecycleObserver, ObserverWithState> e2 = this.b.e();
            if (!this.g && e2 != null && this.c.compareTo(e2.getValue().f448a) > 0) {
                a(lifecycleOwner);
            }
        }
        this.g = false;
    }

    static Lifecycle.State a(@NonNull Lifecycle.State state, @Nullable Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    static class ObserverWithState {

        /* renamed from: a  reason: collision with root package name */
        Lifecycle.State f448a;
        GenericLifecycleObserver b;

        ObserverWithState(LifecycleObserver lifecycleObserver, Lifecycle.State state) {
            this.b = Lifecycling.a((Object) lifecycleObserver);
            this.f448a = state;
        }

        /* access modifiers changed from: package-private */
        public void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State b2 = LifecycleRegistry.b(event);
            this.f448a = LifecycleRegistry.a(this.f448a, b2);
            this.b.a(lifecycleOwner, event);
            this.f448a = b2;
        }
    }
}
