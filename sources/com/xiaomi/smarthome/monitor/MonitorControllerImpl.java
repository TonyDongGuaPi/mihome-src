package com.xiaomi.smarthome.monitor;

import com.xiaomi.miot.support.monitor.MiotMonitorClient;
import com.xiaomi.miot.support.monitor.MiotMonitorController;
import com.xiaomi.smarthome.application.ApplicationLifeCycle;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;

public class MonitorControllerImpl extends ApplicationLifeCycle implements MiotMonitorController {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1556a;

    private MonitorControllerImpl() {
        this.f1556a = false;
    }

    static final class Holder {

        /* renamed from: a  reason: collision with root package name */
        static MonitorControllerImpl f20156a = new MonitorControllerImpl();

        Holder() {
        }
    }

    public static MonitorControllerImpl d() {
        return Holder.f20156a;
    }

    public void a() {
        if (!CoreApi.a().D() && SHApplication.getStateNotifier().a() != 3) {
            MiotMonitorClient.k();
        }
    }

    public void N_() {
        MiotMonitorClient.j();
    }

    public void c() {
        if (this.f1556a) {
            if (CoreApi.a().D()) {
                N_();
            } else {
                MiotMonitorClient.i();
            }
        }
    }

    public void b() {
        super.b();
        this.f1556a = true;
        a();
    }
}
