package com.xiaomi.miot.support.monitor.core.activity;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.miot.support.monitor.core.fps.FpsTask;
import com.xiaomi.miot.support.monitor.core.tasks.TaskManager;

public class ApmInstrumentation extends Instrumentation {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1483a = "traceactivity";
    private Instrumentation b = null;

    public ApmInstrumentation(Instrumentation instrumentation) {
        if (instrumentation instanceof Instrumentation) {
            this.b = instrumentation;
        }
    }

    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (this.b != null) {
            return this.b.newActivity(classLoader, str, intent);
        }
        return super.newActivity(classLoader, str, intent);
    }

    public void callApplicationOnCreate(Application application) {
        if (this.b != null) {
            this.b.callApplicationOnCreate(application);
        } else {
            super.callApplicationOnCreate(application);
        }
    }

    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        FpsTask fpsTask = (FpsTask) TaskManager.a().a("fps");
        if (fpsTask != null) {
            fpsTask.a(activity.getComponentName().getClassName(), 1);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.b != null) {
            this.b.callActivityOnCreate(activity, bundle);
        } else {
            super.callActivityOnCreate(activity, bundle);
        }
        ActivityCore.a(activity, currentTimeMillis);
    }

    public void callActivityOnRestart(Activity activity) {
        super.callActivityOnRestart(activity);
        FpsTask fpsTask = (FpsTask) TaskManager.a().a("fps");
        if (fpsTask != null) {
            fpsTask.a(activity.getComponentName().getClassName(), 2);
        }
    }

    public void callActivityOnStart(Activity activity) {
        long currentTimeMillis = System.currentTimeMillis();
        ActivityCore.b = currentTimeMillis;
        if (this.b != null) {
            this.b.callActivityOnStart(activity);
        } else {
            super.callActivityOnStart(activity);
        }
        ActivityCore.a(activity, 2, System.currentTimeMillis() - currentTimeMillis, 3);
    }

    public void callActivityOnResume(Activity activity) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.b != null) {
            this.b.callActivityOnResume(activity);
        } else {
            super.callActivityOnResume(activity);
        }
        ActivityCore.a(activity, 2, System.currentTimeMillis() - currentTimeMillis, 4);
    }

    public void callActivityOnStop(Activity activity) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.b != null) {
            this.b.callActivityOnStop(activity);
        } else {
            super.callActivityOnStop(activity);
        }
        ActivityCore.a(activity, 2, System.currentTimeMillis() - currentTimeMillis, 6);
    }

    public void callActivityOnPause(Activity activity) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.b != null) {
            this.b.callActivityOnPause(activity);
        } else {
            super.callActivityOnPause(activity);
        }
        ActivityCore.a(activity, 2, System.currentTimeMillis() - currentTimeMillis, 5);
        FpsTask fpsTask = (FpsTask) TaskManager.a().a("fps");
        if (fpsTask != null) {
            fpsTask.a(activity.getComponentName().getClassName());
        }
    }

    public void callActivityOnDestroy(Activity activity) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.b != null) {
            this.b.callActivityOnDestroy(activity);
        } else {
            super.callActivityOnDestroy(activity);
        }
        ActivityCore.a(activity, 2, System.currentTimeMillis() - currentTimeMillis, 7);
    }

    private boolean a() {
        return ActivityCore.a();
    }
}
