package com.xiaomi.smarthome.frame;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.mistatistic.sdk.URLStatsRecorder;
import com.xiaomi.mistatistic.sdk.controller.HttpEventFilter;
import com.xiaomi.mistatistic.sdk.data.HttpEvent;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.server.CoreApiStub;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.ICoreApi;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.frame.baseui.PageHostApi;
import com.xiaomi.smarthome.frame.core.ClientApiStub;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.core.CoreHostApi;
import com.xiaomi.smarthome.frame.login.LoginHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginActivityHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;

public class FrameManager {

    /* renamed from: a  reason: collision with root package name */
    static final String f1516a = "FrameManager";
    private static FrameManager b;
    private static Object c = new Object();
    private static Application p = null;
    private Context d;
    private Handler e = new Handler(Looper.getMainLooper());
    private MessageHandlerThread f = null;
    private volatile Handler g = null;
    /* access modifiers changed from: private */
    public IClientApi.Stub h;
    private CoreApiStub i;
    private LoginHostApi j;
    private PageHostApi k;
    private PluginHostApi l;
    private CoreHostApi m;
    private PluginActivityHostApi n;
    private ServiceConnection o = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(FrameManager.f1516a, "onServiceConnected");
            if (GlobalSetting.u) {
                Log.d("login", "FrameManager onServiceConnected");
            }
            ICoreApi a2 = CoreApi.a(iBinder);
            if (GlobalSetting.u) {
                Log.d("login", "FrameManager CoreApi.generateICoreApi generated");
            }
            CoreApi.a().a(a2);
            if (GlobalSetting.u) {
                Log.d("login", "FrameManager setCoreApiProxy end");
            }
            try {
                a2.registerClientApi(FrameManager.this.h);
                if (GlobalSetting.u) {
                    Log.d("login", "FrameManager registerClientApi end sIsMainProcess=" + GlobalSetting.H + ",myPid()" + Process.myPid());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("login", "FrameManager registerClientApi exception " + e.getMessage());
            }
            if (GlobalSetting.u) {
                Log.d("login", "FrameManager registerClientApi end");
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            FrameManager.this.l();
            CoreApi.a().g();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    FrameManager.this.o();
                }
            }, 30000);
        }
    };

    public CoreApiStub a() {
        return this.i;
    }

    private void n() {
        this.i = new CoreApiStub();
        CoreApi.a().a((ICoreApi) this.i);
        this.i.registerClientApi(new ClientApiStub(b().c(), b().j()));
        CoreService.initCore(new CoreService.InitCoreCallback() {
            public void a(boolean z, String str) {
                try {
                    if (GlobalSetting.u) {
                        Log.d("login", "CoreApiStub onAccountReady isMiLoggedIn=" + z + ",start ipc callback");
                    }
                    for (IClientApi onAccountReady : CoreManager.a().d()) {
                        onAccountReady.onAccountReady(AccountManager.a().l(), AccountManager.a().m());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (GlobalSetting.u) {
                        Log.d("login", "CoreApiStub onAccountReady isMiLoggedIn=" + z + ",ipc exception " + e.getMessage());
                    }
                }
            }

            public void a() {
                try {
                    for (IClientApi onGlobalDynamicSettingReady : CoreManager.a().d()) {
                        onGlobalDynamicSettingReady.onGlobalDynamicSettingReady();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void b() {
                try {
                    for (IClientApi onStatisticReady : CoreManager.a().d()) {
                        onStatisticReady.onStatisticReady();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void c() {
                try {
                    for (IClientApi onPluginReady : CoreManager.a().d()) {
                        onPluginReady.onPluginReady();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void d() {
                try {
                    for (IClientApi onCoreReady : CoreManager.a().d()) {
                        onCoreReady.onCoreReady();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (GlobalSetting.u) {
            Log.d("login", "ClientApiStub registerClientApi CoreAsyncTask end");
        }
    }

    private FrameManager() {
    }

    public static FrameManager b() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new FrameManager();
                }
            }
        }
        return b;
    }

    public Context c() {
        return this.d;
    }

    public Handler d() {
        return this.e;
    }

    public Handler e() {
        if (this.g == null) {
            synchronized (c) {
                if (this.g == null) {
                    this.f = new MessageHandlerThread("FrameManager worker");
                    this.f.start();
                    this.g = new Handler(this.f.getLooper());
                }
            }
        }
        return this.g;
    }

    public static synchronized void a(Application application) {
        synchronized (FrameManager.class) {
            if (b == null) {
                p = application;
            }
        }
    }

    public static Context f() {
        Context context;
        if (b == null) {
            return p;
        }
        synchronized (b) {
            context = b.d == null ? p : b.d;
        }
        return context;
    }

    public void a(Application application, SDKSetting sDKSetting, HostSetting hostSetting, HostDependency hostDependency) {
        LogUtil.a(f1516a, "start");
        if (application == null) {
            throw new RuntimeException("Application is null");
        } else if (sDKSetting == null) {
            throw new RuntimeException("SDKSetting is null");
        } else if (hostSetting == null) {
            throw new RuntimeException("HostSetting is null");
        } else if (hostDependency != null) {
            synchronized (b) {
                this.d = application;
                p = null;
            }
            if (ProcessUtil.h(this.d)) {
                try {
                    XMPassportSettings.setApplicationContext(application);
                } catch (Throwable unused) {
                }
            }
            MiStatInterface.a(this.d, HostSetting.n, HostSetting.o, HostSetting.j);
            MiStatInterface.a(4, 600000);
            if (ServerCompact.g(this.d)) {
                URLStatsRecorder.a((HttpEventFilter) new HttpEventFilter() {
                    public HttpEvent a(HttpEvent httpEvent) {
                        return null;
                    }
                });
            }
            if (!ProcessUtil.i(this.d)) {
                this.m = hostDependency.a();
                this.j = hostDependency.c();
                this.k = hostDependency.b();
                this.l = hostDependency.d();
                this.n = hostDependency.e();
                if (ProcessUtil.h(this.d)) {
                    n();
                    return;
                }
                this.h = new ClientApiStub(this.d, this.m);
                o();
            }
        } else {
            throw new RuntimeException("HostDependency is null");
        }
    }

    public LoginHostApi g() {
        return this.j;
    }

    public PageHostApi h() {
        return this.k;
    }

    public PluginHostApi i() {
        return this.l;
    }

    public CoreHostApi j() {
        return this.m;
    }

    public PluginActivityHostApi k() {
        return this.n;
    }

    /* access modifiers changed from: private */
    public void o() {
        LogUtil.a(f1516a, "bindCoreService");
        try {
            this.d.bindService(new Intent(this.d, CoreService.class), this.o, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void l() {
        try {
            this.d.unbindService(this.o);
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0012 A[LOOP:0: B:1:0x0012->B:4:0x0026, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m() {
        /*
            r3 = this;
            android.content.Context r0 = r3.d
            java.lang.String r1 = "activity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            java.util.List r0 = r0.getRunningAppProcesses()
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0028
            java.lang.Object r1 = r0.next()
            android.app.ActivityManager$RunningAppProcessInfo r1 = (android.app.ActivityManager.RunningAppProcessInfo) r1
            java.lang.String r1 = r1.processName
            java.lang.String r2 = "com.xiaomi.smarthome:core"
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 == 0) goto L_0x0012
        L_0x0028:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.FrameManager.m():void");
    }
}
