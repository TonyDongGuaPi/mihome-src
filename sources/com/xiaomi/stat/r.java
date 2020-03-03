package com.xiaomi.stat;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;

class r implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f23079a;
    private int b;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    r(e eVar) {
        this.f23079a = eVar;
    }

    public void onActivityStarted(Activity activity) {
        if (this.f23079a.i == 0) {
            long unused = this.f23079a.l = SystemClock.elapsedRealtime();
            int unused2 = this.f23079a.j = 0;
            int unused3 = this.f23079a.k = 0;
            this.f23079a.e.execute(new s(this));
        }
        e.g(this.f23079a);
    }

    public void onActivityResumed(Activity activity) {
        e.h(this.f23079a);
        this.b = System.identityHashCode(activity);
        long unused = this.f23079a.f = SystemClock.elapsedRealtime();
        this.f23079a.h();
    }

    public void onActivityPaused(Activity activity) {
        e.j(this.f23079a);
        if (this.b == System.identityHashCode(activity)) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.f23079a.f;
            long l = this.f23079a.d();
            this.f23079a.a(activity.getClass().getName(), l - elapsedRealtime, l);
            this.f23079a.h();
        }
    }

    public void onActivityStopped(Activity activity) {
        e.m(this.f23079a);
        if (this.f23079a.i == 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.f23079a.l;
            long b2 = com.xiaomi.stat.d.r.b();
            this.f23079a.a(this.f23079a.j, this.f23079a.k, b2 - elapsedRealtime, b2);
            this.f23079a.e.execute(new t(this));
        }
    }
}
