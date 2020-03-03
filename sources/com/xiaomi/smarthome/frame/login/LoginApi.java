package com.xiaomi.smarthome.frame.login;

import android.accounts.Account;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.constants.LoginConstants;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.core.entity.account.OAuthAccount;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.util.LoginEventUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.library.common.util.MijiaWrapper;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.youpin.login.api.manager.LoginManager;
import com.xiaomi.youpin.login.api.manager.callback.MiuiSystemLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.OAuthLoginCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.oauth.LoginMiByOAuthResult;
import com.xiaomi.youpin.login.okhttpApi.api.AccessAccountCallback;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import com.yanzhenjie.permission.Action;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;

public class LoginApi implements LoginConstants {
    private static final Object k = new Object();
    private static volatile LoginApi l;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoginType {
    }

    public static class LoginCallback {
        public void a() {
        }
    }

    private LoginApi() {
    }

    public static LoginApi a() {
        if (l == null) {
            synchronized (k) {
                if (l == null) {
                    l = new LoginApi();
                }
            }
        }
        return l;
    }

    public void a(Context context, Activity activity, final MiuiSystemLoginCallback miuiSystemLoginCallback) {
        new MijiaLoginManager(context).a(activity, (MiuiSystemLoginCallback) new MiuiSystemLoginCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                FrameManager.b().g().a(4);
                if (miuiSystemLoginCallback != null) {
                    miuiSystemLoginCallback.onLoginSuccess(loginMiAccount);
                }
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                FrameManager.b().g().b();
                if (miuiSystemLoginCallback != null) {
                    miuiSystemLoginCallback.onLoginFail(i, str, map);
                }
            }
        });
    }

    public void a(final Context context, final int i, @Nullable final LoginCallback loginCallback) {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(FrameManager.b().c());
        instance.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                instance.unregisterReceiver(this);
                if (loginCallback != null) {
                    loginCallback.a();
                }
            }
        }, new IntentFilter(LoginEventUtil.f16346a));
        if (!SystemApi.c()) {
            LogUtilGrey.a("LoginApi", "startLogin not miui", true);
            MjLoginRouter.a(context, i);
        } else if (Build.VERSION.SDK_INT >= 26) {
            AccountManagerUtil.a(context, true, new AccessAccountCallback() {
                public void a(Account account) {
                    MjLoginRouter.a(context, i);
                }

                public void a(int i, String str) {
                    LogUtilGrey.a("LoginApi", "startLogin canAccessAccount onFail:" + i + "," + str, true);
                    MjLoginRouter.a(context);
                }
            });
        } else if (PermissionHelper.a(context, "android.permission.GET_ACCOUNTS")) {
            LogUtilGrey.a("LoginApi", "startLogin isPermsGranted true", true);
            MjLoginRouter.a(context, i);
        } else if (context instanceof Activity) {
            PermissionHelper.m((Activity) context, true, new Action() {
                public void onAction(List<String> list) {
                    MjLoginRouter.a(context, i);
                }
            });
        } else {
            MjLoginRouter.a(context);
        }
    }

    public void a(Context context, String str, @Nullable final LoginCallback loginCallback) {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(FrameManager.b().c());
        instance.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                instance.unregisterReceiver(this);
                if (loginCallback != null) {
                    loginCallback.a();
                }
            }
        }, new IntentFilter(LoginEventUtil.f16346a));
        MjLoginRouter.a(context, str);
    }

    public AsyncHandle a(final boolean z, final AsyncCallback<Void, Error> asyncCallback) {
        final boolean z2 = !CoreApi.a().v();
        return CoreApi.a().a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                Log.e("PluginManager", "Logout");
                if (z2) {
                    AccountManagerUtil.e(FrameManager.b().c());
                }
                if (z) {
                    CoreApi.a().a((IClientCallback) new IClientCallback.Stub() {
                        public void onSuccess(Bundle bundle) throws RemoteException {
                            CoreApi.a().S();
                        }

                        public void onFailure(Bundle bundle) throws RemoteException {
                            CoreApi.a().S();
                        }
                    });
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(null);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public void a(Activity activity, int[] iArr) {
        new LoginManager(activity).a(activity, iArr, HostSetting.n, HostSetting.p, (OAuthLoginCallback) new OAuthLoginCallback() {
            public void a(LoginMiByOAuthResult loginMiByOAuthResult) {
                OAuthAccount oAuthAccount = new OAuthAccount();
                oAuthAccount.a(loginMiByOAuthResult.f23519a, loginMiByOAuthResult.b);
                CoreApi.a().a(oAuthAccount, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        FrameManager.b().g().a(8);
                    }

                    public void onFailure(Error error) {
                        FrameManager.b().g().b();
                    }
                });
            }

            public void a(int i, String str) {
                FrameManager.b().g().b();
            }
        });
    }

    public void a(com.xiaomi.youpin.login.AsyncCallback asyncCallback) {
        if (asyncCallback != null) {
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    final MijiaWrapper mijiaWrapper = new MijiaWrapper(asyncCallback);
                    AccountManagerUtil.a(SHApplication.getAppContext(), false, new AccessAccountCallback() {
                        public void a(Account account) {
                            ((com.xiaomi.youpin.login.AsyncCallback) mijiaWrapper.f18690a).a(account);
                            mijiaWrapper.f18690a = null;
                        }

                        public void a(int i, String str) {
                            LogUtilGrey.a("login", "startLoginMiSystem on fail " + i + "," + str);
                            ((com.xiaomi.youpin.login.AsyncCallback) mijiaWrapper.f18690a).a(new com.xiaomi.youpin.login.entity.Error(i, str));
                            mijiaWrapper.f18690a = null;
                        }
                    });
                    return;
                }
                asyncCallback.a(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
