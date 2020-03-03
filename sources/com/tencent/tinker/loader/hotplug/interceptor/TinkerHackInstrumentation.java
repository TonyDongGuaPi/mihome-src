package com.tencent.tinker.loader.hotplug.interceptor;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.Log;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;

public class TinkerHackInstrumentation extends Instrumentation {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1357a = "Tinker.Instrumentation";
    private final Instrumentation b;
    private final Object c;
    private final Field d;

    public static TinkerHackInstrumentation create(Context context) {
        try {
            Object a2 = ShareReflectUtil.a(context, (Class<?>) null);
            Field a3 = ShareReflectUtil.a(a2, "mInstrumentation");
            Instrumentation instrumentation = (Instrumentation) a3.get(a2);
            if (instrumentation instanceof TinkerHackInstrumentation) {
                return (TinkerHackInstrumentation) instrumentation;
            }
            return new TinkerHackInstrumentation(instrumentation, a2, a3);
        } catch (Throwable th) {
            throw new TinkerRuntimeException("see next stacktrace", th);
        }
    }

    public void install() throws IllegalAccessException {
        if (this.d.get(this.c) instanceof TinkerHackInstrumentation) {
            Log.w(f1357a, "already installed, skip rest logic.");
        } else {
            this.d.set(this.c, this);
        }
    }

    public void uninstall() throws IllegalAccessException {
        this.d.set(this.c, this.b);
    }

    private TinkerHackInstrumentation(Instrumentation instrumentation, Object obj, Field field) throws TinkerRuntimeException {
        this.b = instrumentation;
        this.c = obj;
        this.d = field;
        try {
            a(instrumentation);
        } catch (Throwable th) {
            throw new TinkerRuntimeException(th.getMessage(), th);
        }
    }

    public Activity newActivity(Class<?> cls, Context context, IBinder iBinder, Application application, Intent intent, ActivityInfo activityInfo, CharSequence charSequence, Activity activity, String str, Object obj) throws InstantiationException, IllegalAccessException {
        a(context.getClassLoader(), intent);
        return super.newActivity(cls, context, iBinder, application, intent, activityInfo, charSequence, activity, str, obj);
    }

    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (a(classLoader, intent)) {
            return super.newActivity(classLoader, intent.getComponent().getClassName(), intent);
        }
        return super.newActivity(classLoader, str, intent);
    }

    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        ActivityInfo b2;
        if (!(activity == null || (b2 = IncrementComponentManager.b(activity.getClass().getName())) == null)) {
            a(activity, b2);
        }
        super.callActivityOnCreate(activity, bundle);
    }

    public void callActivityOnCreate(Activity activity, Bundle bundle, PersistableBundle persistableBundle) {
        ActivityInfo b2;
        if (!(activity == null || (b2 = IncrementComponentManager.b(activity.getClass().getName())) == null)) {
            a(activity, b2);
        }
        super.callActivityOnCreate(activity, bundle, persistableBundle);
    }

    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        if (activity != null) {
            a(activity.getClass().getClassLoader(), intent);
        }
        super.callActivityOnNewIntent(activity, intent);
    }

    private boolean a(ClassLoader classLoader, Intent intent) {
        if (intent == null) {
            return false;
        }
        ShareIntentUtil.a(intent, classLoader);
        ComponentName componentName = (ComponentName) intent.getParcelableExtra(EnvConsts.c);
        if (componentName == null) {
            Log.w(f1357a, "oldComponent was null, start " + intent.getComponent() + " next.");
            return false;
        }
        String className = componentName.getClassName();
        if (IncrementComponentManager.b(className) == null) {
            Log.e(f1357a, "Failed to query target activity's info, perhaps the target is not hotpluged component. Target: " + className);
            return false;
        }
        intent.setComponent(componentName);
        intent.removeExtra(EnvConsts.c);
        return true;
    }

    private void a(Activity activity, ActivityInfo activityInfo) {
        activity.setRequestedOrientation(activityInfo.screenOrientation);
        activity.setTheme(activityInfo.theme);
        try {
            ShareReflectUtil.a((Object) activity, "mActivityInfo").set(activity, activityInfo);
        } catch (Throwable th) {
            throw new TinkerRuntimeException("see next stacktrace.", th);
        }
    }

    private void a(Instrumentation instrumentation) throws IllegalAccessException {
        Field[] declaredFields = Instrumentation.class.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            declaredFields[i].set(this, declaredFields[i].get(instrumentation));
        }
    }
}
