package com.xiaomi.plugin.update;

import android.content.Context;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.update.pojo.UpdateInfo;

public abstract class AppUpdateManager {
    public static final String APP_UPDATE_BORADCAST_DOWNLOADING = "app_update_broadcast_downloding";
    public static final String APP_UPDATE_BORADCAST_DOWNLOAD_FAILED = "app_update_broadcast_failed";
    public static final String APP_UPDATE_BORADCAST_DOWNLOAD_SUCCESS = "app_update_broadcast_sucess";

    public abstract void checkAppUpdate(Callback<UpdateInfo> callback);

    public abstract boolean forceUpdate();

    public abstract UpdateInfo getUpdateInfo();

    public abstract boolean hasNewVersion();

    public abstract boolean isNeedShowAppstoreComment(boolean z);

    public abstract void launchAppMarket(Context context);

    public abstract boolean needUpdate();

    public abstract void saveNeedUpdateVerison();

    public abstract void showAppstoreComment(Context context, boolean z);

    public abstract void update();
}
