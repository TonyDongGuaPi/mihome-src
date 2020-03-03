package com.bumptech.glide.manager;

import android.support.annotation.NonNull;
import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

class ActivityFragmentLifecycle implements Lifecycle {

    /* renamed from: a  reason: collision with root package name */
    private final Set<LifecycleListener> f5036a = Collections.newSetFromMap(new WeakHashMap());
    private boolean b;
    private boolean c;

    ActivityFragmentLifecycle() {
    }

    public void a(@NonNull LifecycleListener lifecycleListener) {
        this.f5036a.add(lifecycleListener);
        if (this.c) {
            lifecycleListener.i();
        } else if (this.b) {
            lifecycleListener.g();
        } else {
            lifecycleListener.h();
        }
    }

    public void b(@NonNull LifecycleListener lifecycleListener) {
        this.f5036a.remove(lifecycleListener);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.b = true;
        for (T g : Util.a(this.f5036a)) {
            g.g();
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.b = false;
        for (T h : Util.a(this.f5036a)) {
            h.h();
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.c = true;
        for (T i : Util.a(this.f5036a)) {
            i.i();
        }
    }
}
