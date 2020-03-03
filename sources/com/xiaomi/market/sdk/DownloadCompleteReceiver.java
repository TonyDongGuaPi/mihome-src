package com.xiaomi.market.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.smarthome.download.DownloadManager;

public class DownloadCompleteReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11102a = "MarketSDKDownloadReceiver";

    public void onReceive(Context context, Intent intent) {
        if (DownloadManager.D.equals(intent.getAction())) {
            long longExtra = intent.getLongExtra(DownloadManager.G, -1);
            Log.a(f11102a, "on sdk download complete : id = " + longExtra);
            if (longExtra != -1) {
                DownloadInstallManager.a(context).a(longExtra);
            }
        }
    }
}
