package com.xiaomi.passport.ui.settings;

import android.accounts.Account;
import android.content.Context;
import android.support.annotation.WorkerThread;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.data.XiaomiUserCoreInfo;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.internal.Constants;

public class UserInfoSaver {
    private static final String TAG = "UserInfoSaver";

    @WorkerThread
    static void saveXiaomiUserCoreInfo(Context context, Account account, XiaomiUserCoreInfo xiaomiUserCoreInfo) {
        if (account == null) {
            AccountLog.w(TAG, "no Xiaomi account, skip to save user info");
            return;
        }
        AccountManager accountManager = AccountManager.get(context);
        accountManager.setUserData(account, "acc_user_name", xiaomiUserCoreInfo.userName);
        accountManager.setUserData(account, "acc_nick_name", xiaomiUserCoreInfo.nickName);
        accountManager.setUserData(account, "acc_user_email", xiaomiUserCoreInfo.emailAddress);
        accountManager.setUserData(account, "acc_user_phone", xiaomiUserCoreInfo.safePhone);
        if (xiaomiUserCoreInfo.gender != null) {
            accountManager.setUserData(account, Constants.ACCOUNT_USER_GENDER, xiaomiUserCoreInfo.gender.getType());
        }
        String str = "xiaomi_user_avatar_" + account.name;
        if (saveUserAvatarAsFile(context, xiaomiUserCoreInfo, str, accountManager.getUserData(account, "acc_avatar_url"))) {
            accountManager.setUserData(account, "acc_avatar_url", xiaomiUserCoreInfo.avatarAddress);
            accountManager.setUserData(account, "acc_avatar_file_name", str);
        }
    }

    static void saveByType(Context context, Account account, String str, String str2) {
        AccountManager.get(context).setUserData(account, str, str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d A[SYNTHETIC, Splitter:B:19:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean saveUserAvatarAsFile(android.content.Context r2, com.xiaomi.accountsdk.account.data.XiaomiUserCoreInfo r3, java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.String r3 = r3.avatarAddress
            java.io.File r0 = r2.getFileStreamPath(r4)
            if (r3 == 0) goto L_0x005f
            boolean r5 = android.text.TextUtils.equals(r3, r5)
            if (r5 == 0) goto L_0x001a
            boolean r5 = r0.isFile()
            if (r5 == 0) goto L_0x001a
            boolean r5 = r0.exists()
            if (r5 != 0) goto L_0x005f
        L_0x001a:
            r5 = 0
            com.xiaomi.accountsdk.request.SimpleRequest$StreamContent r3 = com.xiaomi.accountsdk.request.SimpleRequest.getAsStream(r3, r5, r5)     // Catch:{ IOException -> 0x0032, AccessDeniedException -> 0x0029, AuthenticationFailureException -> 0x0020 }
            goto L_0x003b
        L_0x0020:
            r3 = move-exception
            java.lang.String r0 = "UserInfoSaver"
            java.lang.String r1 = "auth failed when download avatar"
            com.xiaomi.accountsdk.utils.AccountLog.e(r0, r1, r3)
            goto L_0x003a
        L_0x0029:
            r3 = move-exception
            java.lang.String r0 = "UserInfoSaver"
            java.lang.String r1 = "access denied when download avatar"
            com.xiaomi.accountsdk.utils.AccountLog.e(r0, r1, r3)
            goto L_0x003a
        L_0x0032:
            r3 = move-exception
            java.lang.String r0 = "UserInfoSaver"
            java.lang.String r1 = "IO error when download avatar"
            com.xiaomi.accountsdk.utils.AccountLog.e(r0, r1, r3)
        L_0x003a:
            r3 = r5
        L_0x003b:
            if (r3 == 0) goto L_0x005f
            java.io.InputStream r5 = r3.getStream()     // Catch:{ IOException -> 0x0052 }
            java.io.File r2 = com.xiaomi.passport.ui.internal.util.BitmapUtils.saveAsFile(r2, r5, r4)     // Catch:{ IOException -> 0x0052 }
            if (r2 == 0) goto L_0x004c
            r2 = 1
            r3.closeStream()
            return r2
        L_0x004c:
            r3.closeStream()
            goto L_0x005f
        L_0x0050:
            r2 = move-exception
            goto L_0x005b
        L_0x0052:
            r2 = move-exception
            java.lang.String r4 = "UserInfoSaver"
            java.lang.String r5 = "failed to save avatar"
            com.xiaomi.accountsdk.utils.AccountLog.e(r4, r5, r2)     // Catch:{ all -> 0x0050 }
            goto L_0x004c
        L_0x005b:
            r3.closeStream()
            throw r2
        L_0x005f:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.settings.UserInfoSaver.saveUserAvatarAsFile(android.content.Context, com.xiaomi.accountsdk.account.data.XiaomiUserCoreInfo, java.lang.String, java.lang.String):boolean");
    }
}
