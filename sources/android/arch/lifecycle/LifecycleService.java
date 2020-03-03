package android.arch.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

public class LifecycleService extends Service implements LifecycleOwner {

    /* renamed from: a  reason: collision with root package name */
    private final ServiceLifecycleDispatcher f449a = new ServiceLifecycleDispatcher(this);

    @CallSuper
    public void onCreate() {
        this.f449a.a();
        super.onCreate();
    }

    @Nullable
    @CallSuper
    public IBinder onBind(Intent intent) {
        this.f449a.b();
        return null;
    }

    @CallSuper
    public void onStart(Intent intent, int i) {
        this.f449a.c();
        super.onStart(intent, i);
    }

    @CallSuper
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    @CallSuper
    public void onDestroy() {
        this.f449a.d();
        super.onDestroy();
    }

    public Lifecycle getLifecycle() {
        return this.f449a.e();
    }
}
