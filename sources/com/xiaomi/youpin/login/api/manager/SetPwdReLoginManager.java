package com.xiaomi.youpin.login.api.manager;

import android.content.Context;
import android.util.Pair;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.ReLoginAfterSetPwdCallback;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.api.BaseLoginApi;
import java.util.HashMap;

class SetPwdReLoginManager extends BaseLoginManager {
    public SetPwdReLoginManager(Context context) {
        super(context);
    }

    public void a(String str, String str2, ReLoginAfterSetPwdCallback reLoginAfterSetPwdCallback) {
        BaseAccount baseAccount = new BaseAccount();
        baseAccount.f23409a = str;
        baseAccount.e = str2;
        a(baseAccount, reLoginAfterSetPwdCallback);
    }

    private void a(final BaseAccount baseAccount, final ReLoginAfterSetPwdCallback reLoginAfterSetPwdCallback) {
        BaseLoginApi.a(this.e, baseAccount.f23409a, baseAccount.e, this.h, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
            public void a(Pair<AccountInfo, Long> pair) {
                AccountInfo accountInfo = (AccountInfo) pair.first;
                AuthenticatorUtil.addOrUpdateAccountManager(SetPwdReLoginManager.this.c, accountInfo);
                baseAccount.b = accountInfo.getEncryptedUserId();
                baseAccount.c = accountInfo.getServiceToken();
                baseAccount.d = accountInfo.getSecurity();
                baseAccount.f = ((Long) pair.second).longValue();
                SetPwdReLoginManager.this.a(baseAccount, (BaseLoginCallback) reLoginAfterSetPwdCallback);
            }

            public void a(ExceptionError exceptionError) {
                reLoginAfterSetPwdCallback.onLoginFail(exceptionError.a(), exceptionError.b(), new HashMap());
            }
        });
    }
}
