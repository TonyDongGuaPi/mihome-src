package com.xiaomi.smarthome.operation.js_sdk.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import com.xiaomi.smarthome.application.SHApplication;

public class AppBackFrontObserver implements LifecycleObserver {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public CurrentAppState f1568a = null;
    private final Application.ActivityLifecycleCallbacks b = new Application.ActivityLifecycleCallbacks() {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            if (AppBackFrontObserver.this.c != null && AppBackFrontObserver.this.f1568a != CurrentAppState.FRONT) {
                CurrentAppState unused = AppBackFrontObserver.this.f1568a = CurrentAppState.FRONT;
                AppBackFrontObserver.this.c.a();
            }
        }
    };
    /* access modifiers changed from: private */
    public OnAppRunStateChangeListener c;

    private enum CurrentAppState {
        BACK,
        FRONT
    }

    public interface OnAppRunStateChangeListener {
        void a();

        void b();
    }

    public void a(OnAppRunStateChangeListener onAppRunStateChangeListener) {
        this.c = onAppRunStateChangeListener;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(LifecycleOwner lifecycleOwner) {
        SHApplication.getApplication().registerActivityLifecycleCallbacks(this.b);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        SHApplication.getApplication().unregisterActivityLifecycleCallbacks(this.b);
    }

    public void a(int i) {
        if (i == 20 && this.c != null && this.f1568a != CurrentAppState.BACK) {
            this.f1568a = CurrentAppState.BACK;
            this.c.b();
        }
    }
}
