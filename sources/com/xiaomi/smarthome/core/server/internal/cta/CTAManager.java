package com.xiaomi.smarthome.core.server.internal.cta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;

public class CTAManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14494a = "CTAManager.onCTAReadyInternal";
    private static CTAManager c;
    private static Object d = new Object();
    Context b = CoreService.getAppContext();
    private boolean e = false;

    public interface IsCTAReadyCallback {
        void a();
    }

    private CTAManager() {
    }

    public static CTAManager a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new CTAManager();
                }
            }
        }
        return c;
    }

    public synchronized boolean b() {
        return this.e;
    }

    public synchronized void a(boolean z) {
        this.e = z;
    }

    public void a(final IsCTAReadyCallback isCTAReadyCallback) {
        if (isCTAReadyCallback != null) {
            boolean b2 = GlobalDynamicSettingManager.a().b();
            boolean c2 = GlobalDynamicSettingManager.a().c();
            boolean b3 = b();
            if (!c2 || (b2 && !b3)) {
                IntentFilter intentFilter = new IntentFilter(f14494a);
                LocalBroadcastManager.getInstance(CoreService.getAppContext()).registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        LocalBroadcastManager.getInstance(CoreService.getAppContext()).unregisterReceiver(this);
                        if (isCTAReadyCallback != null) {
                            isCTAReadyCallback.a();
                        }
                    }
                }, intentFilter);
                return;
            }
            CommonApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    isCTAReadyCallback.a();
                }
            });
        }
    }
}
