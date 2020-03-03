package com.mibi.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.ConcurrentHashMap;

public class DecoratableActivity extends StepActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7455a = "DecoratableActivity";
    private ConcurrentHashMap<Class<? extends ActivityDecorator>, ActivityDecorator> b;

    public DecoratableActivity() {
        DecoratorFactory.a(this);
    }

    /* access modifiers changed from: protected */
    public void decorate(ActivityDecorator activityDecorator) {
        Log.v(f7455a, "DecoratableActivity.decorate, " + activityDecorator.getClass() + ", " + getClass());
        if (this.b == null) {
            this.b = new ConcurrentHashMap<>();
        }
        if (!this.b.containsKey(activityDecorator.getClass())) {
            this.b.put(activityDecorator.getClass(), activityDecorator);
            activityDecorator.a(this);
            return;
        }
        throw new IllegalArgumentException("Only one decorator is valid for the same type!");
    }

    public void unDecorate(ActivityDecorator activityDecorator) {
        Log.v(f7455a, "DecoratableActivity.unDecorate, " + activityDecorator.getClass() + ", " + getClass());
        if (this.b != null) {
            this.b.remove(activityDecorator.getClass());
        }
    }

    public <T extends ActivityDecorator> T findDecoratorByType(Class<T> cls) {
        if (this.b != null) {
            return (ActivityDecorator) this.b.get(cls);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void doPreCreate(Bundle bundle) {
        super.doPreCreate(bundle);
        if (this.b != null) {
            for (ActivityDecorator a2 : this.b.values()) {
                a2.a(bundle);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        if (this.b != null) {
            for (ActivityDecorator b2 : this.b.values()) {
                b2.b(bundle);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doStart() {
        super.doStart();
        if (this.b != null) {
            for (ActivityDecorator a2 : this.b.values()) {
                a2.a();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        super.doResume();
        if (this.b != null) {
            for (ActivityDecorator b2 : this.b.values()) {
                b2.b();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doPause() {
        super.doPause();
        if (this.b != null) {
            for (ActivityDecorator c : this.b.values()) {
                c.c();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doStop() {
        super.doStop();
        if (this.b != null) {
            for (ActivityDecorator d : this.b.values()) {
                d.d();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        super.doDestroy();
        if (this.b != null) {
            for (ActivityDecorator e : this.b.values()) {
                e.e();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doActivityResult(int i, int i2, Intent intent) {
        super.doActivityResult(i, i2, intent);
        if (this.b != null) {
            for (ActivityDecorator a2 : this.b.values()) {
                a2.a(i, i2, intent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doSaveInstanceState(Bundle bundle) {
        super.doSaveInstanceState(bundle);
        if (this.b != null) {
            for (ActivityDecorator c : this.b.values()) {
                c.c(bundle);
            }
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        doWindowFocusChanged(z);
    }

    /* access modifiers changed from: protected */
    public void doWindowFocusChanged(boolean z) {
        if (this.b != null) {
            for (ActivityDecorator a2 : this.b.values()) {
                a2.a(z);
            }
        }
    }
}
