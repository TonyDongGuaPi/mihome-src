package com.xiaomi.miot.support.monitor.leak;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import java.util.concurrent.TimeUnit;

public final class AndroidRefWatcherBuilder extends RefWatcherBuilder<AndroidRefWatcherBuilder> {

    /* renamed from: a  reason: collision with root package name */
    public static volatile RefWatcher f11479a;
    private static final long b = TimeUnit.SECONDS.toMillis(5);
    private final Context c;
    private boolean d = true;
    private boolean e = false;
    private Application f;
    /* access modifiers changed from: private */
    public long g;
    private Application.ActivityLifecycleCallbacks h = new Application.ActivityLifecycleCallbacks() {

        /* renamed from: a  reason: collision with root package name */
        boolean f11480a = false;
        int b = 0;

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            if (AndroidRefWatcherBuilder.f11479a != null) {
                AndroidRefWatcherBuilder.f11479a.a(activity.getComponentName().getClassName());
            }
            this.b++;
            if (this.f11480a) {
                a();
            }
        }

        public void onActivityStopped(Activity activity) {
            this.b--;
            if (this.b <= 0) {
                this.b = 0;
                b();
            }
        }

        public void onActivityDestroyed(Activity activity) {
            if (AndroidRefWatcherBuilder.f11479a != null) {
                AndroidRefWatcherBuilder.f11479a.a(activity);
            }
        }

        private void a() {
            this.f11480a = false;
        }

        private void b() {
            boolean z = true;
            this.f11480a = true;
            if (AndroidRefWatcherBuilder.f11479a == null || !AndroidRefWatcherBuilder.f11479a.a()) {
                long currentTimeMillis = System.currentTimeMillis();
                if ((currentTimeMillis - AndroidRefWatcherBuilder.this.g) - (MiotMonitorManager.a().c().b.min_check_interval_sec * 1000) <= 0) {
                    z = false;
                }
                if (AndroidRefWatcherBuilder.f11479a != null && z) {
                    long unused = AndroidRefWatcherBuilder.this.g = currentTimeMillis;
                    AndroidRefWatcherBuilder.f11479a.d();
                }
            }
        }
    };

    AndroidRefWatcherBuilder(@NonNull Context context) {
        this.c = context.getApplicationContext();
    }

    @NonNull
    public AndroidRefWatcherBuilder a(long j, @NonNull TimeUnit timeUnit) {
        return (AndroidRefWatcherBuilder) a(new AndroidWatchExecutor(timeUnit.toMillis(j)));
    }

    @NonNull
    public AndroidRefWatcherBuilder a(boolean z) {
        this.d = z;
        return this;
    }

    @NonNull
    public RefWatcher a() {
        if (f11479a == null) {
            RefWatcher f2 = f();
            if (f2 != RefWatcher.f11488a) {
                f11479a = f2;
                if (this.d) {
                    c();
                }
            }
            return f2;
        }
        throw new UnsupportedOperationException("buildAndInstall() should only be called once.");
    }

    /* access modifiers changed from: protected */
    @NonNull
    public WatchExecutor b() {
        return new AndroidWatchExecutor(b);
    }

    public void c() {
        if (this.c != null && this.d && !this.e) {
            if (this.f == null) {
                this.f = (Application) this.c.getApplicationContext();
            }
            this.f.registerActivityLifecycleCallbacks(this.h);
            this.e = true;
        }
    }

    public void d() {
        if (this.f != null) {
            this.f.unregisterActivityLifecycleCallbacks(this.h);
            this.e = false;
        }
        if (f11479a != null) {
            f11479a.c();
        }
    }

    public void e() {
        if (this.f != null) {
            this.f.unregisterActivityLifecycleCallbacks(this.h);
            this.e = false;
        }
        if (f11479a != null) {
            f11479a.c();
            f11479a = null;
        }
    }
}
