package com.xiaomi.miot.store.common;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Deprecated
public class AppLifecycleManager {

    /* renamed from: a  reason: collision with root package name */
    static final String f11370a = "AppLifecycleManager";
    public static final String b = "app_quit_broadcast";
    public static final String c = "app_enter_broadcast";
    public static final String d = "app_on_background";
    static final int e = 1;
    static final int f = 5000;
    static final int g = 2;
    static final int h = 5000;
    private static AppLifecycleManager q;
    Application i;
    List<AppLifecycleListener> j = new ArrayList();
    Map<Activity, List<Application.ActivityLifecycleCallbacks>> k = new WeakHashMap();
    Handler l = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (AppLifecycleManager.this.n <= 0) {
                        LogUtils.d(AppLifecycleManager.f11370a, "IsAppQuit");
                        boolean unused = AppLifecycleManager.this.o = true;
                        AppLifecycleManager.this.d();
                        return;
                    }
                    return;
                case 2:
                    boolean unused2 = AppLifecycleManager.this.p = true;
                    AppLifecycleManager.this.e();
                    return;
                default:
                    return;
            }
        }
    };
    Application.ActivityLifecycleCallbacks m = new Application.ActivityLifecycleCallbacks() {
        public void onActivityCreated(Activity activity, Bundle bundle) {
            LogUtils.d(AppLifecycleManager.f11370a, "onActivityCreated:" + activity.getLocalClassName());
            AppLifecycleManager.d(AppLifecycleManager.this);
            if (AppLifecycleManager.this.n <= 0) {
                int unused = AppLifecycleManager.this.n = 1;
            }
            boolean e = AppLifecycleManager.this.o;
            boolean unused2 = AppLifecycleManager.this.o = false;
            AppLifecycleManager.this.l.removeMessages(1);
            if (e) {
                AppLifecycleManager.this.c();
            }
            List list = AppLifecycleManager.this.k.get(activity);
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((Application.ActivityLifecycleCallbacks) list.get(size)).onActivityCreated(activity, bundle);
                }
            }
        }

        public void onActivityStarted(Activity activity) {
            List list = AppLifecycleManager.this.k.get(activity);
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((Application.ActivityLifecycleCallbacks) list.get(size)).onActivityStarted(activity);
                }
            }
        }

        public void onActivityResumed(Activity activity) {
            boolean unused = AppLifecycleManager.this.p = false;
            AppLifecycleManager.this.l.removeMessages(2);
            List list = AppLifecycleManager.this.k.get(activity);
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((Application.ActivityLifecycleCallbacks) list.get(size)).onActivityResumed(activity);
                }
            }
        }

        public void onActivityPaused(Activity activity) {
            List list = AppLifecycleManager.this.k.get(activity);
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((Application.ActivityLifecycleCallbacks) list.get(size)).onActivityPaused(activity);
                }
            }
        }

        public void onActivityStopped(Activity activity) {
            List list = AppLifecycleManager.this.k.get(activity);
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((Application.ActivityLifecycleCallbacks) list.get(size)).onActivityStopped(activity);
                }
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            List list = AppLifecycleManager.this.k.get(activity);
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((Application.ActivityLifecycleCallbacks) list.get(size)).onActivitySaveInstanceState(activity, bundle);
                }
            }
        }

        public void onActivityDestroyed(Activity activity) {
            LogUtils.d(AppLifecycleManager.f11370a, "onActivityDestroyed:" + activity.getLocalClassName());
            List list = AppLifecycleManager.this.k.get(activity);
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((Application.ActivityLifecycleCallbacks) list.get(size)).onActivityDestroyed(activity);
                }
            }
            AppLifecycleManager.this.k.remove(activity);
            AppLifecycleManager.g(AppLifecycleManager.this);
            if (AppLifecycleManager.this.n <= 0) {
                int unused = AppLifecycleManager.this.n = 0;
                AppLifecycleManager.this.l.removeMessages(1);
                AppLifecycleManager.this.l.sendEmptyMessageDelayed(1, 5000);
            }
        }
    };
    /* access modifiers changed from: private */
    public int n = 0;
    /* access modifiers changed from: private */
    public boolean o = false;
    /* access modifiers changed from: private */
    public boolean p = false;
    private final BroadcastReceiver r = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                AppLifecycleManager.this.l.removeMessages(2);
                AppLifecycleManager.this.l.sendEmptyMessageDelayed(2, 5000);
            }
        }
    };
    private final IntentFilter s = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");

    public interface AppLifecycleListener {
        void a();

        void b();

        void c();
    }

    static /* synthetic */ int d(AppLifecycleManager appLifecycleManager) {
        int i2 = appLifecycleManager.n;
        appLifecycleManager.n = i2 + 1;
        return i2;
    }

    static /* synthetic */ int g(AppLifecycleManager appLifecycleManager) {
        int i2 = appLifecycleManager.n;
        appLifecycleManager.n = i2 - 1;
        return i2;
    }

    private AppLifecycleManager() {
    }

    public static synchronized AppLifecycleManager a() {
        AppLifecycleManager appLifecycleManager;
        synchronized (AppLifecycleManager.class) {
            if (q == null) {
                q = new AppLifecycleManager();
            }
            appLifecycleManager = q;
        }
        return appLifecycleManager;
    }

    public synchronized void a(AppLifecycleListener appLifecycleListener) {
        if (!this.j.contains(appLifecycleListener)) {
            this.j.add(appLifecycleListener);
        }
    }

    public synchronized void b(AppLifecycleListener appLifecycleListener) {
        this.j.remove(appLifecycleListener);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.app.Activity r3, android.app.Application.ActivityLifecycleCallbacks r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 == 0) goto L_0x0022
            if (r4 != 0) goto L_0x0006
            goto L_0x0022
        L_0x0006:
            java.util.Map<android.app.Activity, java.util.List<android.app.Application$ActivityLifecycleCallbacks>> r0 = r2.k     // Catch:{ all -> 0x001f }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x001f }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x001a
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x001f }
            r0.<init>()     // Catch:{ all -> 0x001f }
            java.util.Map<android.app.Activity, java.util.List<android.app.Application$ActivityLifecycleCallbacks>> r1 = r2.k     // Catch:{ all -> 0x001f }
            r1.put(r3, r0)     // Catch:{ all -> 0x001f }
        L_0x001a:
            r0.add(r4)     // Catch:{ all -> 0x001f }
            monitor-exit(r2)
            return
        L_0x001f:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x0022:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.store.common.AppLifecycleManager.a(android.app.Activity, android.app.Application$ActivityLifecycleCallbacks):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(android.app.Activity r2, android.app.Application.ActivityLifecycleCallbacks r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 == 0) goto L_0x001a
            if (r3 != 0) goto L_0x0006
            goto L_0x001a
        L_0x0006:
            java.util.Map<android.app.Activity, java.util.List<android.app.Application$ActivityLifecycleCallbacks>> r0 = r1.k     // Catch:{ all -> 0x0017 }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x0017 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x0017 }
            if (r2 != 0) goto L_0x0012
            monitor-exit(r1)
            return
        L_0x0012:
            r2.remove(r3)     // Catch:{ all -> 0x0017 }
            monitor-exit(r1)
            return
        L_0x0017:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L_0x001a:
            monitor-exit(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.store.common.AppLifecycleManager.b(android.app.Activity, android.app.Application$ActivityLifecycleCallbacks):void");
    }

    public synchronized void a(Application application) {
        if (this.i == null) {
            LogUtils.d(f11370a, "initial");
            this.i = application;
            this.i.registerActivityLifecycleCallbacks(this.m);
            this.l.sendEmptyMessageDelayed(1, 5000);
            this.i.registerReceiver(this.r, this.s);
        }
    }

    public boolean b() {
        return this.o;
    }

    /* access modifiers changed from: private */
    public synchronized void c() {
        if (this.i != null) {
            LogUtils.d(f11370a, "appEnter");
            for (int size = this.j.size() - 1; size >= 0; size--) {
                this.j.get(size).a();
            }
            Intent intent = new Intent();
            intent.setAction("app_enter_broadcast");
            LocalBroadcastManager.getInstance(this.i).sendBroadcast(intent);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void d() {
        if (this.i != null) {
            LogUtils.d(f11370a, "appQuit");
            for (int size = this.j.size() - 1; size >= 0; size--) {
                this.j.get(size).b();
            }
            Intent intent = new Intent();
            intent.setAction("app_quit_broadcast");
            LocalBroadcastManager.getInstance(this.i).sendBroadcast(intent);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void e() {
        if (this.i != null) {
            LogUtils.d(f11370a, "appOnBackground");
            for (int size = this.j.size() - 1; size >= 0; size--) {
                this.j.get(size).c();
            }
            Intent intent = new Intent();
            intent.setAction("app_on_background");
            LocalBroadcastManager.getInstance(this.i).sendBroadcast(intent);
        }
    }
}
