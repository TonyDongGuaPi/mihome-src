package com.mi.mistatistic.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mi.mistatistic.sdk.MiStatInterface;
import com.mi.mistatistic.sdk.StaticConstants;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.mistatistic.sdk.controller.PrefPersistUtils;

public class MiReferrerTrackingReceiver extends BroadcastReceiver {
    public static final String INSTALL_ACTION = "com.android.vending.INSTALL_REFERRER";

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        if (INSTALL_ACTION.equals(intent.getAction()) && stringExtra != null) {
            MiStatInterface.a("referrer", stringExtra);
            PrefPersistUtils.b(ApplicationContextHolder.a(), StaticConstants.f7320a, stringExtra);
        }
    }
}
