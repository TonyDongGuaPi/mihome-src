package com.xiaomi.youpin.login.api.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Pair;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.LoginRouter;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.api.BaseLoginApi;
import com.xiaomi.youpin.login.setting.LoginConstant;
import com.xiaomi.youpin.login.ui.LoginMiSafetyValidateActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BaseLoginManager extends CoreBaseLoginManager {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f23410a = "Login";
    protected MetaLoginData b;

    public BaseLoginManager(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    public void a() {
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public void a(final BaseAccount baseAccount, final BaseLoginCallback baseLoginCallback) {
        ArrayList arrayList = new ArrayList();
        if (this.i) {
            arrayList.addAll(this.g);
        } else {
            arrayList.addAll(this.f);
        }
        BaseLoginApi.a((List<String>) arrayList, baseAccount.f23409a, baseAccount.e, (AsyncCallback<List<AccountInfo>, Error>) new AsyncCallback<List<AccountInfo>, Error>() {
            public void a(List<AccountInfo> list) {
                for (AccountInfo addOrUpdateAccountManager : list) {
                    AuthenticatorUtil.addOrUpdateAccountManager(BaseLoginManager.this.c, addOrUpdateAccountManager);
                }
                BaseLoginManager.this.a(baseAccount, list, baseLoginCallback);
            }

            public void a(Error error) {
                String str;
                HashMap hashMap = new HashMap();
                String str2 = null;
                if (error == null) {
                    str = null;
                } else {
                    str = error.a() + ":" + error.b();
                }
                hashMap.put("error", str);
                if (baseAccount != null) {
                    str2 = baseAccount.f23409a + ":" + baseAccount.f + ":" + baseAccount.b;
                }
                hashMap.put("BaseAccount", str2);
                baseLoginCallback.onLoginFail(LoginErrorCode.h, error.b(), hashMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(BaseAccount baseAccount, List<AccountInfo> list, BaseLoginCallback baseLoginCallback) {
        ArrayList<MiServiceTokenInfo> arrayList = new ArrayList<>();
        arrayList.add(new MiServiceTokenInfo(this.e, baseAccount.b, baseAccount.c, baseAccount.d, LoginConstant.a(this.e), baseAccount.f));
        LoginMiAccount loginMiAccount = new LoginMiAccount();
        loginMiAccount.a(baseAccount.f23409a, baseAccount.e, false);
        for (AccountInfo next : list) {
            arrayList.add(new MiServiceTokenInfo(next.getServiceId(), next.getEncryptedUserId(), next.getServiceToken(), next.getSecurity(), LoginConstant.a(next.getServiceId()), baseAccount.f));
        }
        for (MiServiceTokenInfo a2 : arrayList) {
            loginMiAccount.a(a2);
        }
        a(loginMiAccount, baseLoginCallback);
    }

    /* access modifiers changed from: protected */
    public void a(final String str, final BaseLoginCallback baseLoginCallback) {
        LoginRouter.a(this.c, str);
        IntentFilter intentFilter = new IntentFilter(LoginMiSafetyValidateActivity.ACTION_VALIDATE_COMPLETE);
        LocalBroadcastManager.getInstance(this.c.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(BaseLoginManager.this.c.getApplicationContext()).unregisterReceiver(this);
                if (!intent.getBooleanExtra("result", false)) {
                    BaseLoginManager.this.a();
                    HashMap hashMap = new HashMap();
                    hashMap.put("notificationUrl", str);
                    baseLoginCallback.onLoginFail(-1001, "帐号安全验证失败", hashMap);
                    return;
                }
                BaseLoginManager.this.a(intent, baseLoginCallback);
            }
        }, intentFilter);
    }

    /* access modifiers changed from: private */
    public void a(Intent intent, final BaseLoginCallback baseLoginCallback) {
        final String stringExtra = intent.getStringExtra("userid");
        final String stringExtra2 = intent.getStringExtra(LoginMiSafetyValidateActivity.ACTION_VALIDATE_COMPLETE_PASSTOKEN);
        BaseLoginApi.a(this.e, stringExtra, stringExtra2, this.h, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
            public void a(Pair<AccountInfo, Long> pair) {
                AuthenticatorUtil.addOrUpdateAccountManager(BaseLoginManager.this.c, (AccountInfo) pair.first);
                BaseAccount baseAccount = new BaseAccount();
                baseAccount.a((AccountInfo) pair.first, ((Long) pair.second).longValue());
                BaseLoginManager.this.a(baseAccount, baseLoginCallback);
            }

            public void a(ExceptionError exceptionError) {
                String str;
                BaseLoginManager.this.a();
                HashMap hashMap = new HashMap();
                if (exceptionError == null) {
                    str = null;
                } else {
                    str = exceptionError.a() + ":" + exceptionError.b();
                }
                hashMap.put("error", str);
                hashMap.put("userId", stringExtra);
                hashMap.put("passToken", stringExtra2);
                hashMap.put("mRequiredSid", BaseLoginManager.this.e);
                hashMap.put("mIsNeedTimeDiff", BaseLoginManager.this.h + "");
                baseLoginCallback.onLoginFail(exceptionError.a(), exceptionError.b(), hashMap);
            }
        });
    }

    /* access modifiers changed from: protected */
    public AccountInfo a(BaseAccount baseAccount) {
        return new AccountInfo.Builder().userId(baseAccount.f23409a).encryptedUserId(baseAccount.b).passToken(baseAccount.e).serviceId(this.e).serviceToken(baseAccount.c).security(baseAccount.d).build();
    }
}
