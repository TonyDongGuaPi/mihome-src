package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ReportFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

public class ProcessLifecycleOwner implements LifecycleOwner {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final long f457a = 700;
    private static final ProcessLifecycleOwner j = new ProcessLifecycleOwner();
    private int b = 0;
    private int c = 0;
    private boolean d = true;
    private boolean e = true;
    private Handler f;
    private final LifecycleRegistry g = new LifecycleRegistry(this);
    private Runnable h = new Runnable() {
        public void run() {
            ProcessLifecycleOwner.this.f();
            ProcessLifecycleOwner.this.g();
        }
    };
    /* access modifiers changed from: private */
    public ReportFragment.ActivityInitializationListener i = new ReportFragment.ActivityInitializationListener() {
        public void a() {
        }

        public void b() {
            ProcessLifecycleOwner.this.b();
        }

        public void c() {
            ProcessLifecycleOwner.this.c();
        }
    };

    public static LifecycleOwner a() {
        return j;
    }

    static void a(Context context) {
        j.b(context);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.b++;
        if (this.b == 1 && this.e) {
            this.g.a(Lifecycle.Event.ON_START);
            this.e = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.c++;
        if (this.c != 1) {
            return;
        }
        if (this.d) {
            this.g.a(Lifecycle.Event.ON_RESUME);
            this.d = false;
            return;
        }
        this.f.removeCallbacks(this.h);
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.c--;
        if (this.c == 0) {
            this.f.postDelayed(this.h, f457a);
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.b--;
        g();
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.c == 0) {
            this.d = true;
            this.g.a(Lifecycle.Event.ON_PAUSE);
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.b == 0 && this.d) {
            this.g.a(Lifecycle.Event.ON_STOP);
            this.e = true;
        }
    }

    private ProcessLifecycleOwner() {
    }

    /* access modifiers changed from: package-private */
    public void b(Context context) {
        this.f = new Handler();
        this.g.a(Lifecycle.Event.ON_CREATE);
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new EmptyActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ReportFragment.b(activity).a(ProcessLifecycleOwner.this.i);
            }

            public void onActivityPaused(Activity activity) {
                ProcessLifecycleOwner.this.d();
            }

            public void onActivityStopped(Activity activity) {
                ProcessLifecycleOwner.this.e();
            }
        });
    }

    @NonNull
    public Lifecycle getLifecycle() {
        return this.g;
    }
}
