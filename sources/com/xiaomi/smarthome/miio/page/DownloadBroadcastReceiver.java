package com.xiaomi.smarthome.miio.page;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.smarthome.download.DownloadManager;
import com.xiaomi.smarthome.library.common.util.DownloadManagerPro;

public class DownloadBroadcastReceiver extends BroadcastReceiver {
    public static final String DOWNLOAD_MITV_ASSEST_APK_ID = "download.mitv.assest.apk.id";
    public static final String DOWNLOAD_MIWIFI_ASSEST_APK_ID = "download.miwifi.assest.apk.id";

    public void onReceive(Context context, Intent intent) {
        if (DownloadManager.D.equals(intent.getAction())) {
            long longExtra = intent.getLongExtra(DownloadManager.G, -1);
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            long j = defaultSharedPreferences.getLong(DOWNLOAD_MITV_ASSEST_APK_ID, -2);
            if (j == longExtra) {
                installDownloadId(context, j);
                defaultSharedPreferences.edit().remove(DOWNLOAD_MITV_ASSEST_APK_ID).apply();
            }
        }
    }

    public static void installDownloadId(Context context, long j) {
        TextUtils.isEmpty(new DownloadManagerPro((android.app.DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).d(j));
    }
}
