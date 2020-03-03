package com.xiaomi.smarthome.ad.view;

import android.os.RemoteException;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.ad.api.AdPosition;
import com.xiaomi.smarthome.ad.api.Advertisement;

public final class PluginAdUtil {
    public static void a(Advertisement advertisement) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().reportAdShow(advertisement);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a() {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().reportAdClick();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void b(Advertisement advertisement) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().reportAdClose(advertisement);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static Advertisement a(AdPosition adPosition) {
        Advertisement advertisement = adPosition.b().get(0);
        for (Advertisement next : adPosition.b()) {
            if (advertisement.h() < next.h()) {
                advertisement = next;
            }
        }
        return advertisement;
    }
}
