package com.tencent.tinker.loader.hotplug.handler;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.hotplug.interceptor.HandlerMessageInterceptor;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MHMessageHandler implements HandlerMessageInterceptor.MessageHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9240a = "Tinker.MHMsgHndlr";
    private static final int b;
    private final Context c;

    static {
        int i = 100;
        if (Build.VERSION.SDK_INT < 27) {
            try {
                i = ShareReflectUtil.a(Class.forName("android.app.ActivityThread$H"), "LAUNCH_ACTIVITY").getInt((Object) null);
            } catch (Throwable unused) {
            }
        }
        b = i;
    }

    public MHMessageHandler(Context context) {
        Context baseContext;
        while ((context instanceof ContextWrapper) && (baseContext = ((ContextWrapper) context).getBaseContext()) != null) {
            context = baseContext;
        }
        this.c = context;
    }

    public boolean a(Message message) {
        if (message.what == b) {
            try {
                Object obj = message.obj;
                if (obj == null) {
                    Log.w(f9240a, "msg: [" + message.what + "] has no 'obj' value.");
                    return false;
                }
                Intent intent = (Intent) ShareReflectUtil.a(obj, "intent").get(obj);
                if (intent == null) {
                    Log.w(f9240a, "cannot fetch intent from message received by mH.");
                    return false;
                }
                ShareIntentUtil.a(intent, this.c.getClassLoader());
                ComponentName componentName = (ComponentName) intent.getParcelableExtra(EnvConsts.c);
                if (componentName == null) {
                    Log.w(f9240a, "oldComponent was null, start " + intent.getComponent() + " next.");
                    return false;
                }
                ActivityInfo activityInfo = (ActivityInfo) ShareReflectUtil.a(obj, "activityInfo").get(obj);
                if (activityInfo == null) {
                    return false;
                }
                ActivityInfo b2 = IncrementComponentManager.b(componentName.getClassName());
                if (b2 == null) {
                    Log.e(f9240a, "Failed to query target activity's info, perhaps the target is not hotpluged component. Target: " + componentName.getClassName());
                    return false;
                }
                a(obj, b2.screenOrientation);
                a(activityInfo, b2);
                intent.setComponent(componentName);
                intent.removeExtra(EnvConsts.c);
            } catch (Throwable th) {
                Log.e(f9240a, "exception in handleMessage.", th);
            }
        }
        return false;
    }

    private void a(ActivityInfo activityInfo, ActivityInfo activityInfo2) {
        a(activityInfo2, activityInfo);
    }

    private <T> void a(T t, T t2) {
        if (t != null && t2 != null) {
            for (Class cls = t.getClass(); !cls.equals(Object.class); cls = cls.getSuperclass()) {
                for (Field field : cls.getDeclaredFields()) {
                    if (!field.isSynthetic() && !Modifier.isStatic(field.getModifiers())) {
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        try {
                            field.set(t2, field.get(t));
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
        }
    }

    private void a(Object obj, int i) {
        if (i == -1) {
            i = 2;
        }
        try {
            Object obj2 = ShareReflectUtil.a(obj, "token").get(obj);
            Object invoke = ShareReflectUtil.a(Class.forName("android.app.ActivityManagerNative"), "getDefault", (Class<?>[]) new Class[0]).invoke((Object) null, new Object[0]);
            ShareReflectUtil.a(invoke, "setRequestedOrientation", (Class<?>[]) new Class[]{IBinder.class, Integer.TYPE}).invoke(invoke, new Object[]{obj2, Integer.valueOf(i)});
        } catch (Throwable th) {
            Log.e(f9240a, "Failed to fix screen orientation.", th);
        }
    }
}
