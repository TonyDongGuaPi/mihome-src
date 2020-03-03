package com.xiaomi.smarthome.auth;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import com.xiaomi.account.openid.OauthAccount;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.login.util.LoginEventUtil;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import java.io.IOException;

public class OAuthXiaomiAccount implements OauthAccount {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1507a = "OAuthXiaomiAccount";
    private static final String b = "oauth2.0";
    private MiAccountManager c;
    private Context d;

    public OAuthXiaomiAccount(Context context) {
        this.d = context;
        this.c = MiAccountManager.get(context);
    }

    public void getLoginStatus(final ValueCallback<Boolean> valueCallback) {
        CoreApi.a().a(this.d, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                LogUtil.a(OAuthXiaomiAccount.f1507a, "isLogin " + CoreApi.a().q());
                valueCallback.onReceiveValue(Boolean.valueOf(CoreApi.a().q()));
            }
        });
    }

    public void login(Activity activity, final OauthAccount.LoginFinishedListener loginFinishedListener) {
        LogUtil.a(f1507a, "login");
        if (loginFinishedListener != null) {
            final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(FrameManager.b().c());
            instance.registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (LoginEventUtil.f16346a.equals(intent.getAction())) {
                        instance.unregisterReceiver(this);
                        if (intent.getBooleanExtra("login_success", false)) {
                            loginFinishedListener.onSuccess();
                        } else {
                            loginFinishedListener.canceled();
                        }
                    }
                }
            }, new IntentFilter(LoginEventUtil.f16346a));
            LoginApi.a().a(activity.getApplicationContext(), 6, (LoginApi.LoginCallback) null);
            return;
        }
        throw new IllegalArgumentException("loginFinishedListener == null");
    }

    public String getUserId() {
        LogUtil.a(f1507a, "getUserId " + CoreApi.a().s());
        return CoreApi.a().s();
    }

    public String getServiceToken() {
        if (CoreApi.a().v()) {
            Bundle bundle = null;
            try {
                bundle = AccountManager.get(this.d).getAuthToken(AccountManagerUtil.c(this.d), b, true, (AccountManagerCallback) null, (Handler) null).getResult();
            } catch (OperationCanceledException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (AuthenticatorException e3) {
                e3.printStackTrace();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            if (bundle != null) {
                String string = bundle.getString("authtoken");
                if (!TextUtils.isEmpty(string)) {
                    String[] split = string.split(",");
                    if (split.length >= 2) {
                        String str = split[0];
                        LogUtil.a(f1507a, "getServiceToken " + str);
                        return str;
                    }
                }
            }
            LogUtil.a(f1507a, "getServiceToken empty");
            return "";
        }
        String str2 = this.c.getServiceToken(this.d, b).get().serviceToken;
        LogUtil.a(f1507a, "getServiceToken " + str2);
        return str2;
    }

    public void invalideServiceToken() {
        LogUtil.a(f1507a, "invalideServiceToken");
        MiAccountManager.get(this.d).invalidateServiceToken(this.d, this.c.getServiceToken(this.d, b).get());
    }
}
