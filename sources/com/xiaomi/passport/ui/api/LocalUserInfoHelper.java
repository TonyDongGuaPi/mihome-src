package com.xiaomi.passport.ui.api;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.xiaomi.accountsdk.account.data.XiaomiUserCoreInfo;
import com.xiaomi.passport.ui.settings.QueryUserInfoTask;
import com.xiaomi.passport.ui.settings.UserInfoSaver;

public class LocalUserInfoHelper {
    @WorkerThread
    public static boolean saveUserAvatarAsFile(Context context, XiaomiUserCoreInfo xiaomiUserCoreInfo, String str, @Nullable String str2) {
        return UserInfoSaver.saveUserAvatarAsFile(context, xiaomiUserCoreInfo, str, str2);
    }

    @WorkerThread
    public static XiaomiUserCoreInfo queryUserCoreInfo(Context context) {
        return QueryUserInfoTask.getUserCoreInfo(context);
    }
}
