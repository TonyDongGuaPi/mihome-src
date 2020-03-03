package com.xiaomi.youpin.login.okhttpApi.api;

import android.content.Context;
import android.util.Pair;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.other.common.Executors;

public class PwdLoginApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23562a = "PwdLoginApi";

    /* access modifiers changed from: private */
    public static void b(String str) {
    }

    public static void a(String str, String str2, String str3, String str4, String str5, boolean z, MetaLoginData metaLoginData, boolean z2, AsyncCallback<Pair<AccountInfo, Long>, ExceptionError> asyncCallback) {
        b("loginMiByPasswordSDK");
        final String str6 = str;
        final String str7 = str5;
        final String str8 = str2;
        final String str9 = str3;
        final String str10 = str4;
        final MetaLoginData metaLoginData2 = metaLoginData;
        final boolean z3 = z2;
        final AsyncCallback<Pair<AccountInfo, Long>, ExceptionError> asyncCallback2 = asyncCallback;
        final boolean z4 = z;
        Executors.c(new Runnable() {
            public void run() {
                String str;
                Context o = MiLoginApi.a().o();
                HashedDeviceIdUtil.GlobalConfig.getInstance().setPolicy(HashedDeviceIdUtil.DeviceIdPolicy.CACHED_THEN_RUNTIME_THEN_PSEUDO);
                String hashedDeviceIdNoThrow = new HashedDeviceIdUtil(o).getHashedDeviceIdNoThrow();
                AccountInfo accountInfo = null;
                try {
                    e = null;
                    accountInfo = XMPassport.loginByPassword(str6, str7, hashedDeviceIdNoThrow, str8, str9, str10, metaLoginData2, z3, AccountHelper.getEnvInfoArray());
                } catch (Exception e2) {
                    e = e2;
                }
                if (accountInfo == null) {
                    if (e == null) {
                        str = "unknown";
                    } else {
                        str = e.getMessage();
                    }
                    ExceptionError exceptionError = new ExceptionError(-1, str);
                    exceptionError.f23518a = e;
                    PwdLoginApi.b("loginMiByPasswordSDK failed " + str);
                    if (asyncCallback2 != null) {
                        asyncCallback2.b(exceptionError);
                        return;
                    }
                    return;
                }
                long j = 0;
                if (z4) {
                    Pair<Long, Boolean> c2 = MiLoginApi.a().h().c();
                    long longValue = ((Long) c2.first).longValue();
                    if (!((Boolean) c2.second).booleanValue()) {
                        asyncCallback2.b(new ExceptionError(LoginErrorCode.i, "获取timeDiff失败"));
                        return;
                    }
                    j = longValue;
                }
                PwdLoginApi.b("loginMiByPasswordSDK success ");
                if (asyncCallback2 != null) {
                    asyncCallback2.b(new Pair(accountInfo, Long.valueOf(j)));
                }
            }
        });
    }
}
