package com.swmansion.rnscreens;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewParent;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class LifecycleHelper {

    /* renamed from: a  reason: collision with root package name */
    private Map<View, Lifecycle> f8943a = new HashMap();
    private View.OnLayoutChangeListener b = new View.OnLayoutChangeListener() {
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            LifecycleHelper.this.d(view);
            view.removeOnLayoutChangeListener(this);
        }
    };

    @Nullable
    public static Fragment a(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof Screen)) {
            parent = parent.getParent();
        }
        if (parent != null) {
            return ((Screen) parent).getFragment();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void d(View view) {
        Fragment a2 = a(view);
        if (a2 != null && (view instanceof LifecycleObserver)) {
            Lifecycle lifecycle = a2.getLifecycle();
            lifecycle.a((LifecycleObserver) view);
            this.f8943a.put(view, lifecycle);
        }
    }

    public <T extends View & LifecycleObserver> void b(T t) {
        t.addOnLayoutChangeListener(this.b);
    }

    public <T extends View & LifecycleObserver> void c(T t) {
        Lifecycle lifecycle = this.f8943a.get(t);
        if (lifecycle != null) {
            lifecycle.b((LifecycleObserver) t);
        }
    }
}
