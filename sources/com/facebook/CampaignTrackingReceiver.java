package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.appevents.AppEventsLogger;

public final class CampaignTrackingReceiver extends BroadcastReceiver {
    static final String INSTALL_REFERRER = "referrer";

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(INSTALL_REFERRER);
        if (stringExtra != null && stringExtra.startsWith("fb")) {
            AppEventsLogger.setInstallReferrer(intent.getStringExtra(INSTALL_REFERRER));
        }
    }
}
