package com.mibi.common.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ConcurrentHashMap;

public class DecoratableFragment extends StepFragment {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7456a = "DecoratableFragment";
    private ConcurrentHashMap<Class<? extends FragmentDecorator>, FragmentDecorator> b;

    public DecoratableFragment() {
        DecoratorFactory.a(this);
    }

    /* access modifiers changed from: protected */
    public void a(FragmentDecorator fragmentDecorator) {
        Log.v(f7456a, "DecoratableFragment.decorate, " + fragmentDecorator.getClass() + ", " + getClass());
        if (this.b == null) {
            this.b = new ConcurrentHashMap<>();
        }
        if (!this.b.containsKey(fragmentDecorator.getClass())) {
            this.b.put(fragmentDecorator.getClass(), fragmentDecorator);
            fragmentDecorator.a(this);
            return;
        }
        throw new IllegalArgumentException("Only one decorator is valid for the same type!");
    }

    public <T extends FragmentDecorator> T a(Class<T> cls) {
        if (this.b != null) {
            return (FragmentDecorator) this.b.get(cls);
        }
        return null;
    }

    public void a(Activity activity) {
        super.a(activity);
        if (this.b != null) {
            for (FragmentDecorator a2 : this.b.values()) {
                a2.a(activity);
            }
        }
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        if (this.b != null) {
            for (FragmentDecorator a2 : this.b.values()) {
                a2.a(bundle);
            }
        }
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View a2 = super.a(layoutInflater, viewGroup, bundle);
        if (this.b != null) {
            for (FragmentDecorator a3 : this.b.values()) {
                a3.a(layoutInflater, viewGroup, bundle);
            }
        }
        return a2;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        if (this.b != null) {
            for (FragmentDecorator b2 : this.b.values()) {
                b2.b(bundle);
            }
        }
    }

    public void m() {
        super.m();
        if (this.b != null) {
            for (FragmentDecorator a2 : this.b.values()) {
                a2.a();
            }
        }
    }

    public void k() {
        super.k();
        if (this.b != null) {
            for (FragmentDecorator b2 : this.b.values()) {
                b2.b();
            }
        }
    }

    public void l() {
        super.l();
        if (this.b != null) {
            for (FragmentDecorator c : this.b.values()) {
                c.c();
            }
        }
    }

    public void n() {
        super.n();
        if (this.b != null) {
            for (FragmentDecorator d : this.b.values()) {
                d.d();
            }
        }
    }

    public void o() {
        super.o();
        if (this.b != null) {
            for (FragmentDecorator e : this.b.values()) {
                e.e();
            }
        }
    }

    public void C() {
        super.C();
        if (this.b != null) {
            for (FragmentDecorator f : this.b.values()) {
                f.f();
            }
        }
    }

    public void a(int i, int i2, Intent intent) {
        super.a(i, i2, intent);
        if (this.b != null) {
            for (FragmentDecorator a2 : this.b.values()) {
                a2.a(i, i2, intent);
            }
        }
    }

    public void d(Bundle bundle) {
        super.d(bundle);
        if (this.b != null) {
            for (FragmentDecorator c : this.b.values()) {
                c.c(bundle);
            }
        }
    }
}
