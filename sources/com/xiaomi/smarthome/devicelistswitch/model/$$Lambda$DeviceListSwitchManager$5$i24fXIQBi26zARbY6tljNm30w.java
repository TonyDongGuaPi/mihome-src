package com.xiaomi.smarthome.devicelistswitch.model;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.application.SHApplication;

/* renamed from: com.xiaomi.smarthome.devicelistswitch.model.-$$Lambda$DeviceListSwitchManager$5$i2--4fXIQBi26zARbY6tljNm30w  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DeviceListSwitchManager$5$i24fXIQBi26zARbY6tljNm30w implements Runnable {
    public static final /* synthetic */ $$Lambda$DeviceListSwitchManager$5$i24fXIQBi26zARbY6tljNm30w INSTANCE = new $$Lambda$DeviceListSwitchManager$5$i24fXIQBi26zARbY6tljNm30w();

    private /* synthetic */ $$Lambda$DeviceListSwitchManager$5$i24fXIQBi26zARbY6tljNm30w() {
    }

    public final void run() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
    }
}
