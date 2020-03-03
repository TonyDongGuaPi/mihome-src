package android.arch.lifecycle;

import android.arch.core.internal.SafeIterableMap;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;

public class MediatorLiveData<T> extends MutableLiveData<T> {

    /* renamed from: a  reason: collision with root package name */
    private SafeIterableMap<LiveData<?>, Source<?>> f454a = new SafeIterableMap<>();

    @MainThread
    public <S> void a(@NonNull LiveData<S> liveData, @NonNull Observer<S> observer) {
        Source source = new Source(liveData, observer);
        Source a2 = this.f454a.a(liveData, source);
        if (a2 != null && a2.b != observer) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        } else if (a2 == null && hasActiveObservers()) {
            source.a();
        }
    }

    @MainThread
    public <S> void a(@NonNull LiveData<S> liveData) {
        Source b = this.f454a.b(liveData);
        if (b != null) {
            b.b();
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onActive() {
        Iterator<Map.Entry<LiveData<?>, Source<?>>> it = this.f454a.iterator();
        while (it.hasNext()) {
            ((Source) it.next().getValue()).a();
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onInactive() {
        Iterator<Map.Entry<LiveData<?>, Source<?>>> it = this.f454a.iterator();
        while (it.hasNext()) {
            ((Source) it.next().getValue()).b();
        }
    }

    private static class Source<V> implements Observer<V> {

        /* renamed from: a  reason: collision with root package name */
        final LiveData<V> f455a;
        final Observer<V> b;
        int c = -1;

        Source(LiveData<V> liveData, Observer<V> observer) {
            this.f455a = liveData;
            this.b = observer;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.f455a.observeForever(this);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.f455a.removeObserver(this);
        }

        public void onChanged(@Nullable V v) {
            if (this.c != this.f455a.getVersion()) {
                this.c = this.f455a.getVersion();
                this.b.onChanged(v);
            }
        }
    }
}
