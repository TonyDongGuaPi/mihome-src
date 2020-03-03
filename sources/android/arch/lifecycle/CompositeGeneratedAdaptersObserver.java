package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    private final GeneratedAdapter[] f433a;

    CompositeGeneratedAdaptersObserver(GeneratedAdapter[] generatedAdapterArr) {
        this.f433a = generatedAdapterArr;
    }

    public void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        MethodCallsLogger methodCallsLogger = new MethodCallsLogger();
        for (GeneratedAdapter a2 : this.f433a) {
            a2.a(lifecycleOwner, event, false, methodCallsLogger);
        }
        for (GeneratedAdapter a3 : this.f433a) {
            a3.a(lifecycleOwner, event, true, methodCallsLogger);
        }
    }
}
