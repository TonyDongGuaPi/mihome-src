package android.arch.lifecycle;

import android.arch.lifecycle.ClassesInfoCache;
import android.arch.lifecycle.Lifecycle;

class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    private final Object f461a;
    private final ClassesInfoCache.CallbackInfo b = ClassesInfoCache.f430a.b(this.f461a.getClass());

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f461a = obj;
    }

    public void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        this.b.a(lifecycleOwner, event, this.f461a);
    }
}
