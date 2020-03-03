package com.xiaomi.smarthome.core.server.internal.device;

import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.Location;
import com.xiaomi.smarthome.core.entity.device.RouterDevice;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.device.api.RemoteRouterMitvApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.scene.GoLeaveHomeGroupConditionActivity;

public class LocalRouterDeviceSearch {

    /* renamed from: a  reason: collision with root package name */
    public static int f14539a = 2;
    public static final String[] b = {"xiaomi.router.v1", "xiaomi.router.mv1", "xiaomi.router.v2", "xiaomi.router.lv1", "xiaomi.router.v3", "xiaomi.router.lv3"};
    private static Object f = new Object();
    private static LocalRouterDeviceSearch g;
    volatile RouterDevice c = null;
    volatile RemoteRouterMitvApi.MiRouterInfo d;
    volatile boolean e = false;

    public static LocalRouterDeviceSearch a() {
        if (g == null) {
            synchronized (f) {
                if (g == null) {
                    g = new LocalRouterDeviceSearch();
                }
            }
        }
        return g;
    }

    public synchronized void b() {
        if (!CoreApi.a().D()) {
            if (!this.e) {
                this.e = true;
                a((RemoteRouterMitvApi.MiRouterInfo) null);
            }
        }
    }

    public void a(RemoteRouterMitvApi.MiRouterInfo miRouterInfo) {
        this.e = false;
        if (miRouterInfo != null) {
            RouterDevice routerDevice = new RouterDevice();
            routerDevice.a(miRouterInfo.b);
            routerDevice.e(miRouterInfo.f14567a);
            routerDevice.c(miRouterInfo.c);
            routerDevice.e(false);
            routerDevice.e(1);
            routerDevice.a(Location.LOCAL);
            routerDevice.c(f14539a);
            if ("R1D".equals(miRouterInfo.d)) {
                routerDevice.b(b[0]);
            } else if ("R1CM".equals(miRouterInfo.d)) {
                routerDevice.b(b[1]);
            } else if ("R2D".equals(miRouterInfo.d)) {
                routerDevice.b(b[2]);
            } else if ("R1CL".equals(miRouterInfo.d)) {
                routerDevice.b(b[3]);
            } else if ("R3".equals(miRouterInfo.d)) {
                routerDevice.b(b[4]);
            } else if ("R3L".equals(miRouterInfo.d)) {
                routerDevice.b(b[5]);
            } else {
                routerDevice.b(GoLeaveHomeGroupConditionActivity.MI_ROUTER_PREFIX + miRouterInfo.d.toLowerCase());
            }
            routerDevice.c(false);
            routerDevice.d(false);
            routerDevice.b(false);
            routerDevice.a(true);
            routerDevice.h(WifiUtil.b(CoreService.getAppContext()));
            this.c = routerDevice;
            DeviceManager.a().a((Device) routerDevice);
            return;
        }
        if (this.c != null) {
            DeviceManager.a().b((Device) this.c);
        }
        this.c = null;
    }

    public void c() {
        this.c = null;
    }

    public RouterDevice d() {
        return this.c;
    }
}
