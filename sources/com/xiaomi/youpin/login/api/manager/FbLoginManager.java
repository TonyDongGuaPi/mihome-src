package com.xiaomi.youpin.login.api.manager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.jr.stats.SensorsDataManager;
import com.xiaomi.passport.snscorelib.SNSManager;
import com.xiaomi.passport.snscorelib.internal.entity.SNSBindParameter;
import com.xiaomi.passport.snscorelib.internal.entity.SNSLoginParameter;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.LoginRouter;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.FacebookLoginCallback;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.entity.third_part.LoginByThirdPartAccessTokenError;
import com.xiaomi.youpin.login.okhttpApi.LoginApiInternal;
import com.xiaomi.youpin.login.okhttpApi.api.BaseLoginApi;
import com.xiaomi.youpin.login.other.log.LogUtils;
import com.xiaomi.youpin.login.ui.web.LoginFBBindMiWebActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FbLoginManager extends BaseLoginManager {
    private static List<String> j = new ArrayList();
    /* access modifiers changed from: private */
    public LoginManager k = LoginManager.getInstance();

    static {
        j.add("public_profile");
        j.add("email");
        j.add("user_birthday");
    }

    public FbLoginManager(Context context) {
        super(context);
    }

    public void a(Activity activity, final CallbackManager callbackManager, final FacebookLoginCallback facebookLoginCallback) {
        if (!MiLoginApi.a().c()) {
            facebookLoginCallback.onLoginFail(LoginErrorCode.as, "", new HashMap());
            return;
        }
        this.k.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            /* renamed from: a */
            public void onSuccess(LoginResult loginResult) {
                FbLoginManager.this.b(loginResult.getAccessToken(), facebookLoginCallback);
                FbLoginManager.this.k.logOut();
                FbLoginManager.this.k.registerCallback(callbackManager, (FacebookCallback<LoginResult>) null);
                facebookLoginCallback.b();
            }

            public void onCancel() {
                FbLoginManager.this.k.registerCallback(callbackManager, (FacebookCallback<LoginResult>) null);
                facebookLoginCallback.a();
            }

            public void onError(FacebookException facebookException) {
                FbLoginManager.this.k.registerCallback(callbackManager, (FacebookCallback<LoginResult>) null);
                facebookLoginCallback.a(LoginErrorCode.at, facebookException.getMessage());
            }
        });
        this.k.logInWithReadPermissions(activity, (Collection<String>) j);
    }

    private void a(AccessToken accessToken, final FacebookLoginCallback facebookLoginCallback) {
        if (accessToken == null || TextUtils.isEmpty(accessToken.getToken())) {
            facebookLoginCallback.onLoginFail(LoginErrorCode.aA, "accessToken is empty", new HashMap());
        } else {
            LoginApiInternal.a(this.d, this.e, accessToken.getToken(), accessToken.getExpires().getTime(), (AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError>) new AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError>() {
                public void a(BaseAccount baseAccount) {
                    AuthenticatorUtil.addOrUpdateAccountManager(FbLoginManager.this.c, FbLoginManager.this.a(baseAccount));
                    FbLoginManager.this.a(baseAccount, (BaseLoginCallback) facebookLoginCallback);
                }

                public void a(LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError) {
                    FbLoginManager.this.a(loginByThirdPartAccessTokenError, facebookLoginCallback);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(AccessToken accessToken, FacebookLoginCallback facebookLoginCallback) {
        a(accessToken, facebookLoginCallback);
    }

    /* access modifiers changed from: protected */
    public void a(AccountInfo accountInfo, AsyncCallback<List<AccountInfo>, Error> asyncCallback) {
        AccountInfo accountInfo2 = accountInfo;
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.i) {
            arrayList.addAll(this.g);
        } else {
            arrayList.addAll(this.f);
        }
        ArrayList arrayList2 = new ArrayList();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (String str : arrayList) {
            String str2 = accountInfo2.userId;
            final String str3 = str;
            final ArrayList arrayList3 = arrayList2;
            final AtomicInteger atomicInteger2 = atomicInteger;
            AnonymousClass3 r12 = r0;
            final ArrayList arrayList4 = arrayList;
            String str4 = accountInfo2.passToken;
            final AsyncCallback<List<AccountInfo>, Error> asyncCallback2 = asyncCallback;
            AnonymousClass3 r0 = new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
                public void a(Pair<AccountInfo, Long> pair) {
                    LogUtils.b("FbLoginManager", str3 + " Get serviceToken onSuccess " + pair.first);
                    arrayList3.add(pair.first);
                    if (atomicInteger2.incrementAndGet() == arrayList4.size() && asyncCallback2 != null) {
                        asyncCallback2.b(arrayList3);
                    }
                }

                public void a(ExceptionError exceptionError) {
                    LogUtils.b("FbLoginManager", " Get serviceToken fail " + exceptionError);
                    if (atomicInteger2.incrementAndGet() == arrayList4.size() && asyncCallback2 != null) {
                        asyncCallback2.b(arrayList3);
                    }
                }
            };
            a(str, str2, str4, false, r12);
        }
    }

    public static void a(String str, String str2, String str3, boolean z, final AsyncCallback<Pair<AccountInfo, Long>, ExceptionError> asyncCallback) {
        new SNSManager().snsLogin(new SNSLoginParameter.Builder().sid(str).appid(MiLoginApi.a().f()).enToken(str3).build(), new SNSManager.SNSLoginCallback() {
            public void onSnsLoginSucess(AccountInfo accountInfo) {
                LogUtils.c(SensorsDataManager.o, "onSnsLoginSucess");
                asyncCallback.a(new Pair(accountInfo, 0L));
            }

            public void onSnsLoginFailed(int i, String str) {
                LogUtils.c(SensorsDataManager.o, "onSnsLoginFailed");
                asyncCallback.a(new ExceptionError(i, str));
            }

            public void onNetWorkErrorException() {
                LogUtils.c(SensorsDataManager.o, "onNetWorkErrorException");
                asyncCallback.a(new ExceptionError(-1007, "network error exception"));
            }

            public void onNeedNotificationException(String str, String str2) {
                asyncCallback.a(new ExceptionError(LoginErrorCode.b, "Need Notification"));
            }

            public void onNeedLoginForBind(SNSBindParameter sNSBindParameter) {
                asyncCallback.a(new ExceptionError(LoginErrorCode.b, "Need Login For Bind"));
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError, final FacebookLoginCallback facebookLoginCallback) {
        if (loginByThirdPartAccessTokenError.a() != -8003) {
            facebookLoginCallback.onLoginFail(LoginErrorCode.au, "获取Passtoken失败 " + loginByThirdPartAccessTokenError.b(), new HashMap());
            return;
        }
        IntentFilter intentFilter = new IntentFilter(LoginFBBindMiWebActivity.ACTION_COMPLETE);
        LocalBroadcastManager.getInstance(this.c.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(FbLoginManager.this.c.getApplicationContext()).unregisterReceiver(this);
                if (intent.getBooleanExtra("bind_success", false)) {
                    FbLoginManager.this.a(intent.getStringExtra("userId"), intent.getStringExtra("passToken"), facebookLoginCallback);
                    return;
                }
                facebookLoginCallback.onLoginFail(LoginErrorCode.aw, "facebook绑定失败", new HashMap());
            }
        }, intentFilter);
        LoginRouter.b(this.c, loginByThirdPartAccessTokenError.b);
    }

    /* access modifiers changed from: private */
    public void a(final String str, final String str2, final FacebookLoginCallback facebookLoginCallback) {
        BaseLoginApi.a(this.e, str, str2, this.h, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
            public void a(Pair<AccountInfo, Long> pair) {
                AuthenticatorUtil.addOrUpdateAccountManager(FbLoginManager.this.c, (AccountInfo) pair.first);
                BaseAccount baseAccount = new BaseAccount();
                baseAccount.e = str2;
                baseAccount.f23409a = str;
                baseAccount.b = ((AccountInfo) pair.first).getEncryptedUserId();
                baseAccount.c = ((AccountInfo) pair.first).getServiceToken();
                baseAccount.d = ((AccountInfo) pair.first).getSecurity();
                baseAccount.f = ((Long) pair.second).longValue();
                FbLoginManager.this.a(baseAccount, (BaseLoginCallback) facebookLoginCallback);
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
                hashMap.put("userId", str);
                hashMap.put("passToken", str2);
                facebookLoginCallback.onLoginFail(exceptionError.a(), exceptionError.b(), hashMap);
            }
        });
    }
}
