package com.xiaomi.youpin.login.api.manager;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.MiuiSystemLoginCallback;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import com.xiaomi.youpin.login.okhttpApi.api.MiuiSystemLoginApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class MiuiSystemLoginManager extends CoreBaseLoginManager {

    /* renamed from: a  reason: collision with root package name */
    private boolean f23436a = MiLoginApi.a().i();

    public MiuiSystemLoginManager(Context context) {
        super(context);
    }

    public void a(@Nullable final Activity activity, final MiuiSystemLoginCallback miuiSystemLoginCallback) {
        MiuiSystemLoginApi.a(this.f23436a, activity, this.e, this.h, (AsyncCallback<MiServiceTokenInfo, ExceptionError>) new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                MiuiSystemLoginManager.this.a(activity, miServiceTokenInfo, miuiSystemLoginCallback);
            }

            public void a(ExceptionError exceptionError) {
                String str;
                HashMap hashMap = new HashMap();
                if (exceptionError == null) {
                    str = null;
                } else {
                    str = exceptionError.a() + ":" + exceptionError.b();
                }
                hashMap.put("error", str);
                hashMap.put("mRequiredSid", MiuiSystemLoginManager.this.e);
                hashMap.put("mIsNeedTimeDiff", "" + MiuiSystemLoginManager.this.h);
                miuiSystemLoginCallback.onLoginFail(exceptionError.a(), exceptionError.b(), hashMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(@Nullable Activity activity, final MiServiceTokenInfo miServiceTokenInfo, final MiuiSystemLoginCallback miuiSystemLoginCallback) {
        final ArrayList arrayList = new ArrayList();
        if (this.i) {
            arrayList.addAll(this.g);
        } else {
            arrayList.addAll(this.f);
        }
        MiuiSystemLoginApi.a(this.f23436a, activity, arrayList, new AsyncCallback<List<MiServiceTokenInfo>, Error>() {
            public void a(List<MiServiceTokenInfo> list) {
                list.add(miServiceTokenInfo);
                for (MiServiceTokenInfo miServiceTokenInfo : list) {
                    miServiceTokenInfo.e = miServiceTokenInfo.e;
                }
                LoginMiAccount loginMiAccount = new LoginMiAccount();
                loginMiAccount.a(AccountManagerUtil.b(MiuiSystemLoginManager.this.c), (String) null, true);
                for (MiServiceTokenInfo a2 : list) {
                    loginMiAccount.a(a2);
                }
                MiuiSystemLoginManager.this.a(loginMiAccount, miuiSystemLoginCallback);
            }

            public void a(Error error) {
                String str;
                HashMap hashMap = new HashMap();
                if (error == null) {
                    str = null;
                } else {
                    str = error.a() + ":" + error.b();
                }
                hashMap.put("error", str);
                hashMap.put("optionalSids", Arrays.toString(arrayList.toArray()));
                miuiSystemLoginCallback.onLoginFail(error.a(), error.b(), hashMap);
            }
        });
    }
}
