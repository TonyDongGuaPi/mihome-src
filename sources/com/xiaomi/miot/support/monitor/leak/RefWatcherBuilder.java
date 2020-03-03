package com.xiaomi.miot.support.monitor.leak;

import com.xiaomi.miot.support.monitor.leak.RefWatcherBuilder;

public class RefWatcherBuilder<T extends RefWatcherBuilder<T>> {

    /* renamed from: a  reason: collision with root package name */
    private WatchExecutor f11490a;
    private GcTrigger b;

    /* access modifiers changed from: protected */
    public boolean g() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final T i() {
        return this;
    }

    public final T a(WatchExecutor watchExecutor) {
        this.f11490a = watchExecutor;
        return i();
    }

    public final RefWatcher f() {
        if (g()) {
            return RefWatcher.f11488a;
        }
        WatchExecutor watchExecutor = this.f11490a;
        if (watchExecutor == null) {
            watchExecutor = b();
        }
        GcTrigger gcTrigger = this.b;
        if (gcTrigger == null) {
            gcTrigger = h();
        }
        return new RefWatcher(watchExecutor, gcTrigger);
    }

    /* access modifiers changed from: protected */
    public WatchExecutor b() {
        return WatchExecutor.b;
    }

    /* access modifiers changed from: protected */
    public GcTrigger h() {
        return GcTrigger.f11485a;
    }
}
