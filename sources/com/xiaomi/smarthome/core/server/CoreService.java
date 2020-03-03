package com.xiaomi.smarthome.core.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.LongSparseArray;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothService;
import com.xiaomi.smarthome.core.server.internal.cta.CTAManager;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.core.server.internal.wifiscanservice.WifiScanServices;
import com.xiaomi.smarthome.frame.FrameManager;
import com.ximalaya.ting.android.xmpayordersdk.PayOrderManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CoreService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1510a = "CoreService.onCoreCreateInternal";
    private static final String b = "CoreService.onCoreReadyInternal";
    /* access modifiers changed from: private */
    public static final Object c = new Object();
    private static Context d;
    private static boolean e = false;
    private static volatile boolean f = false;
    private static Handler j;
    /* access modifiers changed from: private */
    public static AtomicBoolean k = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static List<InitCoreCallback> l = new ArrayList();
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public WifiScanServices h = new WifiScanServices();
    private SceneExecutor i = new SceneExecutor();

    public interface InitCoreCallback {
        void a();

        void a(boolean z, String str);

        void b();

        void c();

        void d();
    }

    public interface IsCoreCreateCallback {
        void a();
    }

    public interface IsCoreReadyCallback {
        void a();
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        return 1;
    }

    public static Context getAppContext() {
        Context appContext;
        synchronized (c) {
            appContext = CommonApplication.getAppContext();
        }
        return appContext;
    }

    private static void a(Context context) {
        synchronized (c) {
            d = context;
        }
    }

    public static boolean isCoreCreate() {
        boolean z;
        synchronized (c) {
            z = e;
        }
        return z;
    }

    public static boolean isCoreReady() {
        boolean z;
        synchronized (c) {
            z = f;
        }
        return z;
    }

    public static void isCoreReady(final Context context, final IsCoreReadyCallback isCoreReadyCallback) {
        if (context != null && isCoreReadyCallback != null) {
            isCoreCreate(context, new IsCoreCreateCallback() {
                public void a() {
                    if (!CoreService.isCoreReady()) {
                        IntentFilter intentFilter = new IntentFilter(CoreService.b);
                        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
                            public void onReceive(Context context, Intent intent) {
                                LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
                                if (isCoreReadyCallback != null) {
                                    isCoreReadyCallback.a();
                                }
                            }
                        }, intentFilter);
                    } else if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                        isCoreReadyCallback.a();
                    } else {
                        CommonApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                isCoreReadyCallback.a();
                            }
                        });
                    }
                }
            });
        }
    }

    public static void isCoreCreate(final Context context, final IsCoreCreateCallback isCoreCreateCallback) {
        if (context != null && isCoreCreateCallback != null) {
            if (!isCoreCreate()) {
                IntentFilter intentFilter = new IntentFilter(f1510a);
                LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
                        if (isCoreCreateCallback != null) {
                            isCoreCreateCallback.a();
                        }
                    }
                }, intentFilter);
                context.startService(new Intent(context, CoreService.class));
            } else if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                isCoreCreateCallback.a();
            } else {
                CommonApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        isCoreCreateCallback.a();
                    }
                });
            }
        }
    }

    public static void setCoreReady() {
        synchronized (c) {
            if (!f) {
                f = true;
                LocalBroadcastManager.getInstance(getAppContext()).sendBroadcast(new Intent(b));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initCore(final com.xiaomi.smarthome.core.server.CoreService.InitCoreCallback r3) {
        /*
            boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u
            if (r0 == 0) goto L_0x0024
            java.lang.String r0 = "login"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "CoreService initCore in callback="
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = ",sIsMainProcess="
            r1.append(r2)
            boolean r2 = com.xiaomi.smarthome.globalsetting.GlobalSetting.H
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
        L_0x0024:
            java.lang.Object r0 = c
            monitor-enter(r0)
            boolean r1 = f     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0032
            if (r3 == 0) goto L_0x0030
            onAllReady(r3)     // Catch:{ all -> 0x0054 }
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            return
        L_0x0032:
            java.util.concurrent.atomic.AtomicBoolean r1 = k     // Catch:{ all -> 0x0054 }
            r2 = 1
            boolean r1 = r1.getAndSet(r2)     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0042
            java.util.List<com.xiaomi.smarthome.core.server.CoreService$InitCoreCallback> r1 = l     // Catch:{ all -> 0x0054 }
            r1.add(r3)     // Catch:{ all -> 0x0054 }
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            return
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            com.xiaomi.smarthome.core.server.CoreManager r0 = com.xiaomi.smarthome.core.server.CoreManager.a()
            android.os.Handler r0 = r0.b()
            com.xiaomi.smarthome.core.server.CoreService$4 r1 = new com.xiaomi.smarthome.core.server.CoreService$4
            r1.<init>(r3)
            r0.post(r1)
            return
        L_0x0054:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.CoreService.initCore(com.xiaomi.smarthome.core.server.CoreService$InitCoreCallback):void");
    }

    public static void onAllReady(InitCoreCallback initCoreCallback) {
        if (initCoreCallback != null) {
            initCoreCallback.a(AccountManager.a().l(), AccountManager.a().m());
            initCoreCallback.a();
            initCoreCallback.b();
            initCoreCallback.c();
            initCoreCallback.d();
        }
    }

    public void onCreate() {
        super.onCreate();
        LogUtil.a("CoreService", "onCreate " + getApplicationContext());
        a(getApplicationContext());
        if (j == null) {
            j = new Handler();
        }
        FrameManager.b().a().setPromoteStatus(promoteCoreServiceLevel());
        a();
        CTAManager.a().a((CTAManager.IsCTAReadyCallback) new CTAManager.IsCTAReadyCallback() {
            public void a() {
                CommonApplication.getGlobalHandler().postDelayed(new Runnable() {
                    public void run() {
                        CoreService.this.h.f();
                        CoreService.this.initReceiver();
                        boolean unused = CoreService.this.g = true;
                    }
                }, 2000);
            }
        });
        BluetoothService.a();
    }

    public IBinder onBind(Intent intent) {
        return FrameManager.b().a();
    }

    public void onDestroy() {
        super.onDestroy();
        Log.e("SceneExector", PayOrderManager.a.f);
        CTAManager.a().a((CTAManager.IsCTAReadyCallback) new CTAManager.IsCTAReadyCallback() {
            public void a() {
                CommonApplication.getGlobalHandler().postDelayed(new Runnable() {
                    public void run() {
                        if (CoreService.this.g) {
                            CoreService.this.h.g();
                        }
                    }
                }, 1000);
            }
        });
        try {
            Log.e("SceneExector", "start unregistor");
            unregisterReceiver(this.i);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean promoteCoreServiceLevel() {
        try {
            Method declaredMethod = Class.forName("com.miui.whetstone.WhetstoneActivityManager").getDeclaredMethod("promoteApplicationLevel", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke((Object) null, new Object[]{2});
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    public static Handler getGlobalHandler() {
        return j;
    }

    private void a() {
        if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22 || Build.VERSION.SDK_INT == 23) {
            b();
        }
    }

    private void b() {
        LongSparseArray longSparseArray;
        Method declaredMethod;
        LongSparseArray[] longSparseArrayArr;
        try {
            Field declaredField = Class.forName("android.content.res.ResourcesImpl").getDeclaredField("sPreloadedDrawables");
            declaredField.setAccessible(true);
            if (declaredField.isAccessible() && (longSparseArrayArr = (LongSparseArray[]) declaredField.get((Object) null)) != null) {
                for (LongSparseArray longSparseArray2 : longSparseArrayArr) {
                    Method declaredMethod2 = LongSparseArray.class.getDeclaredMethod("clear", new Class[0]);
                    if (declaredMethod2 != null) {
                        declaredMethod2.setAccessible(true);
                        declaredMethod2.invoke(longSparseArray2, new Object[0]);
                    }
                }
            }
            Field declaredField2 = Resources.class.getDeclaredField("sPreloadedColorDrawables");
            declaredField2.setAccessible(true);
            if (declaredField2.isAccessible() && (longSparseArray = (LongSparseArray) declaredField2.get((Object) null)) != null && (declaredMethod = LongSparseArray.class.getDeclaredMethod("clear", new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(longSparseArray, new Object[0]);
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaomi.metoknlp.geofencing.state_change");
        try {
            registerReceiver(this.i, intentFilter);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
