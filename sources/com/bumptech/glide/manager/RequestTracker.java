package com.bumptech.glide.manager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public class RequestTracker {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5043a = "RequestTracker";
    private final Set<Request> b = Collections.newSetFromMap(new WeakHashMap());
    private final List<Request> c = new ArrayList();
    private boolean d;

    public void a(@NonNull Request request) {
        this.b.add(request);
        if (!this.d) {
            request.a();
            return;
        }
        request.b();
        if (Log.isLoggable(f5043a, 2)) {
            Log.v(f5043a, "Paused, delaying request");
        }
        this.c.add(request);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void b(Request request) {
        this.b.add(request);
    }

    public boolean c(@Nullable Request request) {
        return a(request, true);
    }

    private boolean a(@Nullable Request request, boolean z) {
        boolean z2 = true;
        if (request == null) {
            return true;
        }
        boolean remove = this.b.remove(request);
        if (!this.c.remove(request) && !remove) {
            z2 = false;
        }
        if (z2) {
            request.b();
            if (z) {
                request.h();
            }
        }
        return z2;
    }

    public boolean a() {
        return this.d;
    }

    public void b() {
        this.d = true;
        for (T t : Util.a(this.b)) {
            if (t.c()) {
                t.b();
                this.c.add(t);
            }
        }
    }

    public void c() {
        this.d = true;
        for (T t : Util.a(this.b)) {
            if (t.c() || t.d()) {
                t.b();
                this.c.add(t);
            }
        }
    }

    public void d() {
        this.d = false;
        for (T t : Util.a(this.b)) {
            if (!t.d() && !t.c()) {
                t.a();
            }
        }
        this.c.clear();
    }

    public void e() {
        for (T a2 : Util.a(this.b)) {
            a(a2, false);
        }
        this.c.clear();
    }

    public void f() {
        for (T t : Util.a(this.b)) {
            if (!t.d() && !t.f()) {
                t.b();
                if (!this.d) {
                    t.a();
                } else {
                    this.c.add(t);
                }
            }
        }
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.b.size() + ", isPaused=" + this.d + "}";
    }
}
