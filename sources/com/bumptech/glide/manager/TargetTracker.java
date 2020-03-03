package com.bumptech.glide.manager;

import android.support.annotation.NonNull;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public final class TargetTracker implements LifecycleListener {

    /* renamed from: a  reason: collision with root package name */
    private final Set<Target<?>> f5046a = Collections.newSetFromMap(new WeakHashMap());

    public void a(@NonNull Target<?> target) {
        this.f5046a.add(target);
    }

    public void b(@NonNull Target<?> target) {
        this.f5046a.remove(target);
    }

    public void g() {
        for (T g : Util.a(this.f5046a)) {
            g.g();
        }
    }

    public void h() {
        for (T h : Util.a(this.f5046a)) {
            h.h();
        }
    }

    public void i() {
        for (T i : Util.a(this.f5046a)) {
            i.i();
        }
    }

    @NonNull
    public List<Target<?>> a() {
        return Util.a(this.f5046a);
    }

    public void b() {
        this.f5046a.clear();
    }
}
