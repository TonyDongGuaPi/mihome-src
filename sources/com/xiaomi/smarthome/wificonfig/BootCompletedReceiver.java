package com.xiaomi.smarthome.wificonfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.smarthome.app.startup.CTAHelper;
import com.xiaomi.smarthome.application.SHApplication;

public class BootCompletedReceiver extends BroadcastReceiver {
    Context mContext;

    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        if (SHApplication.isApplicationStart()) {
            doWork();
        } else if (!CTAHelper.a(SHApplication.getAppContext()) && CTAHelper.b(SHApplication.getAppContext())) {
            doWork();
        }
    }

    /* access modifiers changed from: package-private */
    public void doWork() {
        SHApplication.getApplication().onApplicationLifeCycleStart();
        WIFIScanHomelogReceiver.initWifiScanMonitors();
    }
}
