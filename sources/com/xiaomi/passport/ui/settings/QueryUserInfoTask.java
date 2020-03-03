package com.xiaomi.passport.ui.settings;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import com.xiaomi.accountsdk.account.data.XiaomiUserCoreInfo;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.data.XMPassportInfo;
import com.xiaomi.passport.ui.internal.util.SysHelper;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import java.util.ArrayList;

public class QueryUserInfoTask extends AsyncTask<Void, Void, XiaomiUserCoreInfo> {
    private static final String TAG = "QueryUserInfoTask";
    private final Account mAccount;
    private final QueryUserInfoCallback mCallback;
    @SuppressLint({"StaticFieldLeak"})
    private final Context mContext;

    public interface QueryUserInfoCallback {
        void onResult(XiaomiUserCoreInfo xiaomiUserCoreInfo);
    }

    QueryUserInfoTask(Context context, QueryUserInfoCallback queryUserInfoCallback) {
        this.mContext = context;
        this.mCallback = queryUserInfoCallback;
        this.mAccount = AuthenticatorUtil.getXiaomiAccount(context);
    }

    /* access modifiers changed from: protected */
    public XiaomiUserCoreInfo doInBackground(Void[] voidArr) {
        XiaomiUserCoreInfo handleQueryUserInfo = handleQueryUserInfo();
        if (handleQueryUserInfo != null) {
            UserInfoSaver.saveXiaomiUserCoreInfo(this.mContext, this.mAccount, handleQueryUserInfo);
        }
        return handleQueryUserInfo;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(XiaomiUserCoreInfo xiaomiUserCoreInfo) {
        if (this.mCallback != null) {
            this.mCallback.onResult(xiaomiUserCoreInfo);
        }
    }

    private XiaomiUserCoreInfo handleQueryUserInfo() {
        return getUserCoreInfo(this.mContext);
    }

    @Nullable
    public static XiaomiUserCoreInfo getUserCoreInfo(Context context) {
        if (AuthenticatorUtil.getXiaomiAccount(context) == null) {
            AccountLog.w(TAG, "no Xiaomi account, skip to query user info");
            return null;
        }
        XMPassportInfo build = XMPassportInfo.build(context, "passportapi");
        ArrayList arrayList = new ArrayList();
        arrayList.add(XiaomiUserCoreInfo.Flag.BASE_INFO);
        arrayList.add(XiaomiUserCoreInfo.Flag.BIND_ADDRESS);
        arrayList.add(XiaomiUserCoreInfo.Flag.EXTRA_INFO);
        arrayList.add(XiaomiUserCoreInfo.Flag.SETTING_INFO);
        return SysHelper.queryXiaomiUserCoreInfo(context, build, arrayList);
    }
}
