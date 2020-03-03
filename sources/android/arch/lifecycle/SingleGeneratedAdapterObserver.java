package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    private final GeneratedAdapter f465a;

    SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter) {
        this.f465a = generatedAdapter;
    }

    public void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        this.f465a.a(lifecycleOwner, event, false, (MethodCallsLogger) null);
        this.f465a.a(lifecycleOwner, event, true, (MethodCallsLogger) null);
    }
}
