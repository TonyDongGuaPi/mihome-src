package com.xiaomi.youpin.youpin_common.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.youpin.log.LogUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AppLifecycleManager extends BroadcastReceiver implements Application.ActivityLifecycleCallbacks {
    public static final String APP_ENTER_BROADCAST = "app_enter_broadcast";
    public static final String APP_ON_BACKGROUND = "app_on_background";
    public static final String APP_ON_FOREGROUND = "app_on_foreground";
    public static final String APP_QUIT_BROADCAST = "app_quit_broadcast";

    /* renamed from: a  reason: collision with root package name */
    private static final String f23794a = "AppLifecycleManager";
    private static final int b = 10001;
    private static final int c = 10002;
    private static final int d = 10003;
    private static final int e = 10004;
    private static final int f = 10005;
    private static final int g = 10006;
    private static final int h = 5000;
    private static final int i = 5000;
    private WeakReference<Activity> j;
    private List<String> k = new ArrayList();
    private Context l;
    private final LifecycleHandler m = new LifecycleHandler(this);
    private int n = 0;
    private int o = 0;
    private boolean p = false;
    private boolean q = false;
    private final List<AppLifecycleListener> r = new LinkedList();
    private boolean s = false;

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    private static class LifecycleHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<AppLifecycleManager> f23796a;

        public LifecycleHandler(AppLifecycleManager appLifecycleManager) {
            super(Looper.getMainLooper());
            this.f23796a = new WeakReference<>(appLifecycleManager);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 10001:
                    if (this.f23796a.get() != null) {
                        ((AppLifecycleManager) this.f23796a.get()).a();
                        break;
                    }
                    break;
                case 10002:
                    if (this.f23796a.get() != null) {
                        ((AppLifecycleManager) this.f23796a.get()).b();
                        break;
                    }
                    break;
                case 10003:
                    if (this.f23796a.get() != null) {
                        ((AppLifecycleManager) this.f23796a.get()).c();
                        break;
                    }
                    break;
                case 10004:
                    if (this.f23796a.get() != null) {
                        ((AppLifecycleManager) this.f23796a.get()).d();
                        break;
                    }
                    break;
                case 10005:
                    if (this.f23796a.get() != null) {
                        ((AppLifecycleManager) this.f23796a.get()).a((AppLifecycleListener) message.obj);
                        break;
                    }
                    break;
                case 10006:
                    if (this.f23796a.get() != null) {
                        ((AppLifecycleManager) this.f23796a.get()).b((AppLifecycleListener) message.obj);
                        break;
                    }
                    break;
            }
            super.handleMessage(message);
        }
    }

    private static class InstanceHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static AppLifecycleManager f23795a = new AppLifecycleManager();

        private InstanceHolder() {
        }
    }

    public static AppLifecycleManager getInstance() {
        return InstanceHolder.f23795a;
    }

    public void init(Application application) {
        LogUtils.d(f23794a, "init");
        if (application != null && !this.s) {
            this.l = application;
            application.registerReceiver(this, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            application.registerActivityLifecycleCallbacks(this);
            this.m.sendEmptyMessageDelayed(10003, 5000);
            this.s = true;
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.p) {
            this.p = false;
            LocalBroadcastManager.getInstance(this.l).sendBroadcast(new Intent(APP_ON_FOREGROUND));
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.p = true;
        LogUtils.d(f23794a, "appOnBackground");
        g();
        LocalBroadcastManager.getInstance(this.l).sendBroadcast(new Intent("app_on_background"));
    }

    /* access modifiers changed from: private */
    public void c() {
        this.q = true;
        LogUtils.d(f23794a, "appQuit");
        f();
        LocalBroadcastManager.getInstance(this.l).sendBroadcast(new Intent("app_quit_broadcast"));
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.q) {
            this.q = false;
            LogUtils.d(f23794a, "appEnter");
            e();
            LocalBroadcastManager.getInstance(this.l).sendBroadcast(new Intent("app_enter_broadcast"));
        }
    }

    private void e() {
        for (int size = this.r.size() - 1; size >= 0; size--) {
            this.r.get(size).a();
        }
    }

    private void f() {
        for (int size = this.r.size() - 1; size >= 0; size--) {
            this.r.get(size).b();
        }
    }

    private void g() {
        for (int size = this.r.size() - 1; size >= 0; size--) {
            this.r.get(size).c();
        }
    }

    /* access modifiers changed from: private */
    public void a(AppLifecycleListener appLifecycleListener) {
        if (appLifecycleListener != null && !this.r.contains(appLifecycleListener)) {
            this.r.add(appLifecycleListener);
        }
    }

    /* access modifiers changed from: private */
    public void b(AppLifecycleListener appLifecycleListener) {
        if (appLifecycleListener != null) {
            this.r.remove(appLifecycleListener);
        }
    }

    public void registerAppLifecycleListener(AppLifecycleListener appLifecycleListener) {
        this.m.obtainMessage(10005, appLifecycleListener).sendToTarget();
    }

    public void unregisterAppLifecycleListener(AppLifecycleListener appLifecycleListener) {
        this.m.obtainMessage(10006, appLifecycleListener);
    }

    public boolean isAppQuit() {
        return this.q;
    }

    public boolean isAppBackground() {
        return this.p;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && TextUtils.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS", intent.getAction())) {
            this.m.removeMessages(10002);
            this.m.sendEmptyMessageDelayed(10002, 5000);
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.o++;
        a(activity.getLocalClassName());
        LogUtils.d(f23794a, "  mActivityCount   " + this.o + "       onActivityCreated:" + activity.getLocalClassName());
        if (this.o <= 0) {
            this.o = 1;
        }
        this.m.removeMessages(10003);
        this.m.sendEmptyMessage(10004);
    }

    public void onActivityResumed(Activity activity) {
        this.j = new WeakReference<>(activity);
        this.m.removeMessages(10002);
        if (this.n == 0) {
            this.m.sendEmptyMessage(10001);
        }
        this.n++;
    }

    public void onActivityPaused(Activity activity) {
        this.n--;
        if (this.n == 0) {
            this.m.removeMessages(10002);
            this.m.sendEmptyMessageDelayed(10002, 5000);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        this.o--;
        b(activity.getLocalClassName());
        LogUtils.d(f23794a, "  mActivityCount   " + this.o + "       onActivityDestroyed:" + activity.getLocalClassName());
        if (this.o <= 0 && this.k != null && this.k.size() == 0) {
            this.o = 0;
            this.m.removeMessages(10003);
            this.m.sendEmptyMessageDelayed(10003, 5000);
        }
    }

    public int getForegroundCount() {
        return this.n;
    }

    public Activity getCurrentActivity() {
        return (Activity) this.j.get();
    }

    private void a(String str) {
        if (this.k != null && !TextUtils.isEmpty(str)) {
            this.k.add(str);
            LogUtils.d(f23794a, "mActivityClassNameList   " + this.k.size());
        }
    }

    private void b(String str) {
        if (this.k != null && !TextUtils.isEmpty(str)) {
            this.k.remove(str);
            LogUtils.d(f23794a, "mActivityClassNameList   " + this.k.size());
        }
    }
}
