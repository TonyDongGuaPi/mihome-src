package com.xiaomi.youpin.login.api.manager;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.LoginDependencyApi;
import com.xiaomi.youpin.login.LoginRouter;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.WxLoginCallback;
import com.xiaomi.youpin.login.api.wechat.LoginAddAccountUtil;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.entity.third_part.LoginByThirdPartAccessTokenError;
import com.xiaomi.youpin.login.entity.wx.GetWXAccessTokenByAuthCodeResult;
import com.xiaomi.youpin.login.okhttpApi.LoginApiInternal;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import com.xiaomi.youpin.login.okhttpApi.api.BaseLoginApi;
import com.xiaomi.youpin.login.setting.LoginConstant;
import com.xiaomi.youpin.login.ui.web.LoginWXBindMiWebActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WxLoginManager extends BaseLoginManager {
    /* access modifiers changed from: private */
    public boolean j = false;
    private BroadcastReceiver k;

    public WxLoginManager(Context context) {
        super(context);
    }

    public boolean a() {
        return this.j;
    }

    public void a(final Activity activity, final WxLoginCallback wxLoginCallback) {
        final String str;
        this.j = false;
        if (this.k != null) {
            LocalBroadcastManager.getInstance(this.c.getApplicationContext()).unregisterReceiver(this.k);
        }
        if (!MiLoginApi.a().b()) {
            wxLoginCallback.onLoginFail(LoginErrorCode.Z, "WxAppId或IWXAPI为空", new HashMap());
        } else if (MiLoginApi.a().n().isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            if (this.d == 0) {
                str = "smarthome_" + System.currentTimeMillis();
            } else {
                str = "youpin_" + System.currentTimeMillis();
            }
            req.state = str;
            IntentFilter intentFilter = new IntentFilter("action.wx.login.complete");
            this.k = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null && intent.getAction() != null) {
                        String action = intent.getAction();
                        if (((action.hashCode() == 624821011 && action.equals("action.wx.login.complete")) ? (char) 0 : 65535) == 0) {
                            boolean unused = WxLoginManager.this.j = true;
                            boolean booleanExtra = intent.getBooleanExtra("login_success", false);
                            int intExtra = intent.getIntExtra("error_code", -1);
                            String stringExtra = intent.getStringExtra("auth_code");
                            String stringExtra2 = intent.getStringExtra("state");
                            if (TextUtils.isEmpty(stringExtra2) || TextUtils.isEmpty(str) || str.equalsIgnoreCase(stringExtra2)) {
                                LocalBroadcastManager.getInstance(WxLoginManager.this.c.getApplicationContext()).unregisterReceiver(this);
                                if (booleanExtra) {
                                    wxLoginCallback.a();
                                    WxLoginManager.this.a(activity, stringExtra, wxLoginCallback);
                                    return;
                                }
                                switch (intExtra) {
                                    case -6:
                                        wxLoginCallback.a((int) LoginErrorCode.ag);
                                        return;
                                    case -5:
                                        wxLoginCallback.a((int) LoginErrorCode.af);
                                        return;
                                    case -4:
                                        wxLoginCallback.a((int) LoginErrorCode.ae);
                                        return;
                                    case -3:
                                        wxLoginCallback.a((int) LoginErrorCode.ad);
                                        return;
                                    case -2:
                                        wxLoginCallback.b();
                                        return;
                                    case -1:
                                        wxLoginCallback.a(-7001);
                                        return;
                                    default:
                                        wxLoginCallback.a(-7001);
                                        return;
                                }
                            }
                        }
                    }
                }
            };
            LocalBroadcastManager.getInstance(this.c.getApplicationContext()).registerReceiver(this.k, intentFilter);
            if (!MiLoginApi.a().n().sendReq(req)) {
                LocalBroadcastManager.getInstance(this.c.getApplicationContext()).unregisterReceiver(this.k);
                wxLoginCallback.a(-7001);
            }
        } else if (wxLoginCallback != null) {
            wxLoginCallback.a(-7001);
        }
    }

    /* access modifiers changed from: private */
    public void a(final Activity activity, String str, final WxLoginCallback wxLoginCallback) {
        if (TextUtils.isEmpty(str)) {
            wxLoginCallback.onLoginFail(LoginErrorCode.ai, "AuthCode为空", new HashMap());
        } else {
            LoginApiInternal.a(this.d, str, new AsyncCallback<GetWXAccessTokenByAuthCodeResult, Error>() {
                public void a(GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult) {
                    WxLoginManager.this.a(activity, getWXAccessTokenByAuthCodeResult, wxLoginCallback);
                }

                public void a(Error error) {
                    wxLoginCallback.onLoginFail(LoginErrorCode.aj, error.b(), new HashMap());
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(Activity activity, GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult, WxLoginCallback wxLoginCallback) {
        if (MiLoginApi.a().p()) {
            b(activity, getWXAccessTokenByAuthCodeResult, wxLoginCallback);
        } else {
            a(getWXAccessTokenByAuthCodeResult, wxLoginCallback);
        }
    }

    public void a(final GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult, final WxLoginCallback wxLoginCallback) {
        LoginApiInternal.a(this.d, this.e, getWXAccessTokenByAuthCodeResult.f23524a, getWXAccessTokenByAuthCodeResult.b, getWXAccessTokenByAuthCodeResult.c, new AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError>() {
            public void a(BaseAccount baseAccount) {
                AuthenticatorUtil.addOrUpdateAccountManager(WxLoginManager.this.c, WxLoginManager.this.a(baseAccount));
                WxLoginManager.this.a(baseAccount, (BaseLoginCallback) wxLoginCallback);
            }

            public void a(LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError) {
                WxLoginManager.this.a(loginByThirdPartAccessTokenError, getWXAccessTokenByAuthCodeResult.b, getWXAccessTokenByAuthCodeResult.f23524a, wxLoginCallback);
            }
        });
    }

    private void b(Activity activity, final GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult, final WxLoginCallback wxLoginCallback) {
        LoginAddAccountUtil.a(activity, getWXAccessTokenByAuthCodeResult, this.e, new AsyncCallback<Boolean, ExceptionError>() {
            public void a(Boolean bool) {
                if (bool.booleanValue()) {
                    LoginDependencyApi h = MiLoginApi.a().h();
                    if (h == null) {
                        return;
                    }
                    if (h.a()) {
                        WxLoginManager.this.a(getWXAccessTokenByAuthCodeResult, (AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
                            public void a(final Boolean bool) {
                                WxLoginManager.this.b(getWXAccessTokenByAuthCodeResult, new AsyncCallback<Boolean, Error>() {
                                    public void a(Boolean bool) {
                                        wxLoginCallback.a(bool.booleanValue(), bool.booleanValue());
                                    }

                                    public void a(Error error) {
                                        wxLoginCallback.a(bool.booleanValue(), false);
                                    }
                                });
                            }

                            public void a(Error error) {
                                WxLoginManager.this.b(getWXAccessTokenByAuthCodeResult, new AsyncCallback<Boolean, Error>() {
                                    public void a(Boolean bool) {
                                        wxLoginCallback.a(false, bool.booleanValue());
                                    }

                                    public void a(Error error) {
                                        wxLoginCallback.a(false, false);
                                    }
                                });
                            }
                        });
                    } else {
                        WxLoginManager.this.a(wxLoginCallback);
                    }
                } else {
                    wxLoginCallback.onLoginFail(LoginErrorCode.aE, "", new HashMap());
                }
            }

            public void a(ExceptionError exceptionError) {
                wxLoginCallback.onLoginFail(LoginErrorCode.aE, exceptionError.toString(), new HashMap());
            }
        });
    }

    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public void a(final WxLoginCallback wxLoginCallback) {
        Account d = AccountManagerUtil.d(this.c);
        if (d == null) {
            wxLoginCallback.onLoginFail(LoginErrorCode.aF, "", new HashMap());
            return;
        }
        MiAccountManager miAccountManager = MiAccountManager.get(this.c);
        miAccountManager.setUseLocal();
        final String str = d.name;
        final String password = miAccountManager.getPassword(d);
        BaseLoginApi.a(this.c, this.e, true, (AsyncCallback<MiServiceTokenInfo, ExceptionError>) new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                BaseAccount baseAccount = new BaseAccount();
                baseAccount.f23409a = str;
                baseAccount.e = password;
                baseAccount.b = miServiceTokenInfo.b;
                baseAccount.f = miServiceTokenInfo.e;
                baseAccount.c = miServiceTokenInfo.c;
                baseAccount.d = miServiceTokenInfo.d;
                WxLoginManager.this.a(baseAccount, wxLoginCallback);
            }

            public void a(ExceptionError exceptionError) {
                wxLoginCallback.onLoginFail(LoginErrorCode.aG, exceptionError.toString(), new HashMap());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final BaseAccount baseAccount, final WxLoginCallback wxLoginCallback) {
        BaseLoginApi.a(this.c, this.f, new AsyncCallback<List<MiServiceTokenInfo>, ExceptionError>() {
            public void a(List<MiServiceTokenInfo> list) {
                ArrayList<MiServiceTokenInfo> arrayList = new ArrayList<>();
                arrayList.add(new MiServiceTokenInfo(WxLoginManager.this.e, baseAccount.b, baseAccount.c, baseAccount.d, LoginConstant.a(WxLoginManager.this.e), baseAccount.f));
                LoginMiAccount loginMiAccount = new LoginMiAccount();
                loginMiAccount.a(baseAccount.f23409a, baseAccount.e, false);
                arrayList.addAll(list);
                for (MiServiceTokenInfo a2 : arrayList) {
                    loginMiAccount.a(a2);
                }
                WxLoginManager.this.a(loginMiAccount, wxLoginCallback);
            }

            public void a(ExceptionError exceptionError) {
                wxLoginCallback.onLoginFail(LoginErrorCode.aH, exceptionError.toString(), new HashMap());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError, String str, String str2, WxLoginCallback wxLoginCallback) {
        if (loginByThirdPartAccessTokenError.a() != -7010) {
            wxLoginCallback.onLoginFail(LoginErrorCode.al, "获取Passtoken失败 " + loginByThirdPartAccessTokenError.b(), new HashMap());
            return;
        }
        a(loginByThirdPartAccessTokenError, str, wxLoginCallback);
    }

    private void a(LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError, String str, final WxLoginCallback wxLoginCallback) {
        LoginRouter.a(this.c, loginByThirdPartAccessTokenError.b, str);
        IntentFilter intentFilter = new IntentFilter(LoginWXBindMiWebActivity.ACTION_COMPLETE);
        LocalBroadcastManager.getInstance(this.c.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(WxLoginManager.this.c.getApplicationContext()).unregisterReceiver(this);
                if (intent.getBooleanExtra("bind_success", false)) {
                    WxLoginManager.this.a(intent.getStringExtra("userId"), intent.getStringExtra("passToken"), wxLoginCallback);
                    return;
                }
                wxLoginCallback.onLoginFail(LoginErrorCode.am, "微信绑定失败", new HashMap());
            }
        }, intentFilter);
    }

    /* access modifiers changed from: private */
    public void a(final String str, final String str2, final WxLoginCallback wxLoginCallback) {
        BaseLoginApi.a(this.e, str, str2, this.h, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
            public void a(Pair<AccountInfo, Long> pair) {
                AccountInfo accountInfo = (AccountInfo) pair.first;
                AuthenticatorUtil.addOrUpdateAccountManager(WxLoginManager.this.c, accountInfo);
                BaseAccount baseAccount = new BaseAccount();
                baseAccount.e = str2;
                baseAccount.f23409a = str;
                baseAccount.b = accountInfo.getEncryptedUserId();
                baseAccount.c = accountInfo.getServiceToken();
                baseAccount.d = accountInfo.getSecurity();
                baseAccount.f = ((Long) pair.second).longValue();
                WxLoginManager.this.a(baseAccount, (BaseLoginCallback) wxLoginCallback);
            }

            public void a(ExceptionError exceptionError) {
                wxLoginCallback.onLoginFail(exceptionError.a(), exceptionError.b(), new HashMap());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult, final AsyncCallback<Boolean, Error> asyncCallback) {
        LoginApiInternal.a(this.d, this.e, getWXAccessTokenByAuthCodeResult.f23524a, getWXAccessTokenByAuthCodeResult.b, getWXAccessTokenByAuthCodeResult.c, new AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError>() {
            public void a(BaseAccount baseAccount) {
                asyncCallback.a(true);
            }

            public void a(LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError) {
                if (loginByThirdPartAccessTokenError.a() == -7010) {
                    asyncCallback.a(false);
                } else {
                    asyncCallback.a(loginByThirdPartAccessTokenError);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult, final AsyncCallback<Boolean, Error> asyncCallback) {
        LoginApiInternal.a(MiLoginApi.a().l(), this.e, getWXAccessTokenByAuthCodeResult.f23524a, getWXAccessTokenByAuthCodeResult.b, (AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
            public void a(Boolean bool) {
                asyncCallback.a(bool);
            }

            public void a(Error error) {
                asyncCallback.a(error);
            }
        });
    }
}
