package com.ximalaya.ting.android.opensdk.player.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Parcelable;
import com.xiaomi.youpin.hawkeye.entity.StatType;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.opensdk.util.NetworkType;

public class NetWorkChangeReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2194a = "SNetWorkChangeReceiver";
    public static boolean isMobileType = false;

    public void onReceive(Context context, Intent intent) {
        Parcelable parcelableExtra;
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            if (NetworkType.d(context)) {
                if (!isMobileType) {
                    Logger.c(f2194a, "startTrafficStatistic");
                }
                Logger.e(f2194a, "connect to mobile");
                isMobileType = true;
            } else if (NetworkType.c(context)) {
                if (isMobileType) {
                    Logger.c(f2194a, "endTrafficStatistic");
                }
                Logger.e(f2194a, "connect to wifi");
                isMobileType = false;
            }
        }
        if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction()) && (parcelableExtra = intent.getParcelableExtra(StatType.NETWORKINFO)) != null) {
            NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
            if ((networkInfo.getState() == NetworkInfo.State.CONNECTED) && networkInfo.getType() == 1) {
                if (isMobileType) {
                    Logger.c(f2194a, "endTrafficStatistic");
                }
                Logger.e(f2194a, "connect to wifi");
                isMobileType = false;
            }
        }
    }
}
