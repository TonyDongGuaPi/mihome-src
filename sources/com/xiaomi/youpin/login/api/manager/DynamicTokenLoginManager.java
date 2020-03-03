package com.xiaomi.youpin.login.api.manager;

import android.content.Context;
import android.util.Pair;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.DynamicTokenLoginCallback;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.api.BaseLoginApi;
import java.util.HashMap;

public class DynamicTokenLoginManager extends BaseLoginManager {
    public DynamicTokenLoginManager(Context context) {
        super(context);
    }

    public void a(String str, String str2, String str3, MetaLoginData metaLoginData, boolean z, DynamicTokenLoginCallback dynamicTokenLoginCallback) {
        final DynamicTokenLoginCallback dynamicTokenLoginCallback2 = dynamicTokenLoginCallback;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final boolean z2 = z;
        BaseLoginApi.a(str, str3, metaLoginData, z, str2, this.e, this.h, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
            public void a(Pair<AccountInfo, Long> pair) {
                AccountInfo accountInfo = (AccountInfo) pair.first;
                AuthenticatorUtil.addOrUpdateAccountManager(DynamicTokenLoginManager.this.c, accountInfo);
                BaseAccount baseAccount = new BaseAccount();
                baseAccount.a(accountInfo, ((Long) pair.second).longValue());
                DynamicTokenLoginManager.this.a(baseAccount, (BaseLoginCallback) dynamicTokenLoginCallback2);
            }

            public void a(ExceptionError exceptionError) {
                String str;
                Exception exc = exceptionError.f23518a;
                HashMap hashMap = new HashMap();
                if (exceptionError == null) {
                    str = null;
                } else {
                    str = exceptionError.a() + ":" + exceptionError.b();
                }
                hashMap.put("error", str);
                hashMap.put("username", str4);
                hashMap.put("step1Token", str5);
                hashMap.put("token", str6);
                hashMap.put("trust", z2 + "");
                if (exc == null) {
                    dynamicTokenLoginCallback2.onLoginFail(-999, exceptionError.b(), hashMap);
                } else if (exceptionError.f23518a instanceof NeedVerificationException) {
                    dynamicTokenLoginCallback2.onLoginFail(LoginErrorCode.D, "需要身份验证", hashMap);
                } else if (exceptionError.f23518a instanceof InvalidCredentialException) {
                    dynamicTokenLoginCallback2.onLoginFail(-4001, "动态Token错误", hashMap);
                } else if (exceptionError.f23518a instanceof AccessDeniedException) {
                    dynamicTokenLoginCallback2.onLoginFail(LoginErrorCode.F, "禁止访问", hashMap);
                } else {
                    dynamicTokenLoginCallback2.onLoginFail(-999, exceptionError.f23518a.getMessage(), hashMap);
                }
            }
        });
    }
}
