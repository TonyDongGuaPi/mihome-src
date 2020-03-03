package miuipub.hybrid;

import android.app.Activity;
import com.miuipub.internal.hybrid.HybridManager;

public class NativeInterface {

    /* renamed from: a  reason: collision with root package name */
    private HybridManager f2948a;

    public NativeInterface(HybridManager hybridManager) {
        this.f2948a = hybridManager;
    }

    public Activity a() {
        return this.f2948a.b();
    }

    public void a(LifecycleListener lifecycleListener) {
        this.f2948a.a(lifecycleListener);
    }

    public void b(LifecycleListener lifecycleListener) {
        this.f2948a.b(lifecycleListener);
    }
}
