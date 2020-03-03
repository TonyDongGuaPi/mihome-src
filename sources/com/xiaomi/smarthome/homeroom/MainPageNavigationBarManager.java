package com.xiaomi.smarthome.homeroom;

import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;

public class MainPageNavigationBarManager {

    /* renamed from: a  reason: collision with root package name */
    private static MainPageNavigationBarManager f18124a;
    private boolean b = SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.o, true);
    private boolean c;

    public void b(boolean z) {
    }

    public static MainPageNavigationBarManager a() {
        if (f18124a == null) {
            synchronized (HomeManager.class) {
                if (f18124a == null) {
                    f18124a = new MainPageNavigationBarManager();
                }
            }
        }
        return f18124a;
    }

    private MainPageNavigationBarManager() {
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean z) {
        if (this.b != z) {
            this.b = z;
            SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.o, z);
        }
    }

    public boolean c() {
        return this.c;
    }
}
