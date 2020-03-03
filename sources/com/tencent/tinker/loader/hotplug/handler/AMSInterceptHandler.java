package com.tencent.tinker.loader.hotplug.handler;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.tinker.loader.hotplug.ActivityStubManager;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.hotplug.interceptor.ServiceBinderInterceptor;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Method;

public class AMSInterceptHandler implements ServiceBinderInterceptor.BinderInvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9239a = "Tinker.AMSIntrcptHndlr";
    private static final int[] b = {16842840};
    private static final int c;
    private final Context d;

    static {
        int i = 2;
        if (Build.VERSION.SDK_INT < 27) {
            try {
                i = ((Integer) ShareReflectUtil.a((Class<?>) ActivityManager.class, "INTENT_SENDER_ACTIVITY").get((Object) null)).intValue();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        c = i;
    }

    public AMSInterceptHandler(Context context) {
        Context baseContext;
        while ((context instanceof ContextWrapper) && (baseContext = ((ContextWrapper) context).getBaseContext()) != null) {
            context = baseContext;
        }
        this.d = context;
    }

    public Object a(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        if (WBConstants.H.equals(name)) {
            return b(obj, method, objArr);
        }
        if ("startActivities".equals(name)) {
            return c(obj, method, objArr);
        }
        if ("startActivityAndWait".equals(name)) {
            return b(obj, method, objArr);
        }
        if ("startActivityWithConfig".equals(name)) {
            return b(obj, method, objArr);
        }
        if ("startActivityAsUser".equals(name)) {
            return b(obj, method, objArr);
        }
        if ("getIntentSender".equals(name)) {
            return d(obj, method, objArr);
        }
        return method.invoke(obj, objArr);
    }

    private Object b(Object obj, Method method, Object[] objArr) throws Throwable {
        int i = 0;
        while (true) {
            if (i >= objArr.length) {
                i = -1;
                break;
            } else if (objArr[i] instanceof Intent) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            Intent intent = new Intent((Intent) objArr[i]);
            a(intent);
            objArr[i] = intent;
        }
        return method.invoke(obj, objArr);
    }

    private Object c(Object obj, Method method, Object[] objArr) throws Throwable {
        int i = 0;
        while (true) {
            if (i >= objArr.length) {
                i = -1;
                break;
            } else if (objArr[i] instanceof Intent[]) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            Intent[] intentArr = objArr[i];
            for (int i2 = 0; i2 < intentArr.length; i2++) {
                Intent intent = new Intent(intentArr[i2]);
                a(intent);
                intentArr[i2] = intent;
            }
        }
        return method.invoke(obj, objArr);
    }

    private Object d(Object obj, Method method, Object[] objArr) throws Throwable {
        int i = 0;
        while (true) {
            if (i >= objArr.length) {
                i = -1;
                break;
            } else if (objArr[i] instanceof Intent[]) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1 && objArr[0].intValue() == c) {
            Intent[] intentArr = objArr[i];
            for (int i2 = 0; i2 < intentArr.length; i2++) {
                Intent intent = new Intent(intentArr[i2]);
                a(intent);
                intentArr[i2] = intent;
            }
        }
        return method.invoke(obj, objArr);
    }

    private void a(Intent intent) {
        String str;
        String str2 = null;
        if (intent.getComponent() != null) {
            str2 = intent.getComponent().getPackageName();
            str = intent.getComponent().getClassName();
        } else {
            ResolveInfo resolveActivity = this.d.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null) {
                resolveActivity = IncrementComponentManager.a(intent);
            }
            if (resolveActivity == null || resolveActivity.filter == null || !resolveActivity.filter.hasCategory("android.intent.category.DEFAULT")) {
                str = null;
            } else {
                str2 = resolveActivity.activityInfo.packageName;
                str = resolveActivity.activityInfo.name;
            }
        }
        if (IncrementComponentManager.a(str)) {
            ActivityInfo b2 = IncrementComponentManager.b(str);
            a(intent, str2, str, ActivityStubManager.a(str, b2.launchMode, a(b2)));
        }
    }

    private void a(Intent intent, String str, String str2, String str3) {
        ComponentName componentName = new ComponentName(str, str2);
        ShareIntentUtil.a(intent, this.d.getClassLoader());
        intent.putExtra(EnvConsts.c, componentName);
        intent.setComponent(new ComponentName(str, str3));
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(android.content.pm.ActivityInfo r4) {
        /*
            r3 = this;
            int r4 = r4.getThemeResource()
            android.content.Context r0 = r3.d
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.Resources$Theme r0 = r0.newTheme()
            r1 = 1
            r0.applyStyle(r4, r1)
            r4 = 0
            r1 = 0
            int[] r2 = b     // Catch:{ Throwable -> 0x0030, all -> 0x0029 }
            android.content.res.TypedArray r0 = r0.obtainStyledAttributes(r2)     // Catch:{ Throwable -> 0x0030, all -> 0x0029 }
            boolean r1 = r0.getBoolean(r4, r4)     // Catch:{ Throwable -> 0x0027, all -> 0x0024 }
            if (r0 == 0) goto L_0x0023
            r0.recycle()
        L_0x0023:
            return r1
        L_0x0024:
            r4 = move-exception
            r1 = r0
            goto L_0x002a
        L_0x0027:
            r1 = r0
            goto L_0x0030
        L_0x0029:
            r4 = move-exception
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.recycle()
        L_0x002f:
            throw r4
        L_0x0030:
            if (r1 == 0) goto L_0x0035
            r1.recycle()
        L_0x0035:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.hotplug.handler.AMSInterceptHandler.a(android.content.pm.ActivityInfo):boolean");
    }
}
