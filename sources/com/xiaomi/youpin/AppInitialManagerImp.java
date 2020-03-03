package com.xiaomi.youpin;

import android.app.Application;
import com.xiaomi.pluginhost.AppInitialApi;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.youpin.utils.TimeTraceUtils;

public class AppInitialManagerImp extends AppInitialApi {
    static volatile boolean c = false;

    private AppInitialManagerImp() {
    }

    public static synchronized void a(Application application) {
        synchronized (AppInitialManagerImp.class) {
            if (!c) {
                application.registerActivityLifecycleCallbacks(new ActivityLifecycleManager());
                AppInitialManagerImp appInitialManagerImp = new AppInitialManagerImp();
                a((AppInitialApi) appInitialManagerImp);
                appInitialManagerImp.a(SHApplication.getAppContext());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        TimeTraceUtils.b();
        PluginManager.a().d();
        TimeTraceUtils.a("PluginManager initial");
    }
}
