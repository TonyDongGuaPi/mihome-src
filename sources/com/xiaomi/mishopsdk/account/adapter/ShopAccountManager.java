package com.xiaomi.mishopsdk.account.adapter;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import com.xiaomi.mishopsdk.Listener.IShopAccountManager;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.util.ToastUtil;
import com.xiaomi.mishopsdk.widget.CommonAlertDialog;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.servicetoken.ServiceTokenFuture;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.shop2.util.PermissionUtil;
import java.lang.ref.WeakReference;

public abstract class ShopAccountManager implements IShopAccountManager {
    private static final String TAG = "ShopAccountManager";
    protected Application mApplication;

    public void onLoginResult(boolean z) {
    }

    public boolean useAuthTokenType() {
        return true;
    }

    public boolean isUseSystem() {
        return MiAccountManager.get(ShopApp.instance).isUseSystem();
    }

    public final void login() {
        LoginManager.getInstance().login();
    }

    public final void logout() {
        LoginManager.getInstance().logout();
    }

    public ShopAccountManager(Application application) {
        this.mApplication = application;
    }

    public final Account[] getAccounts() {
        return MiAccountManager.get(ShopApp.instance).getAccounts();
    }

    public final void gotoAccount(Activity activity) {
        if (hostAccountHasLogin()) {
            showLoginDialog(activity);
        } else if (Constants.TARGET_SDK_VERSION >= 26 || ShopApp.getInstance().isSystemApp()) {
            openLocalLoginUI(activity);
        } else if (PermissionUtil.checkPermission(activity, "android.permission.GET_ACCOUNTS", 103)) {
            openLocalLoginUI(activity);
        }
    }

    /* access modifiers changed from: protected */
    public void openLocalLoginUI(Activity activity) {
        final WeakReference weakReference = new WeakReference(activity);
        gotoLocalLoginUI(activity, new AccountManagerCallback<Bundle>() {
            public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                Activity activity = (Activity) weakReference.get();
                if (activity != null) {
                    Activity parent = activity.getParent();
                    if (parent != null) {
                        activity = parent;
                    }
                    try {
                        Intent intent = (Intent) accountManagerFuture.getResult().getParcelable("intent");
                        if (intent != null) {
                            activity.startActivityForResult(intent, IShopAccountManager.ADD_ACOUNT_REQUEST_CODE);
                        }
                    } catch (Exception e) {
                        Log.e(ShopAccountManager.TAG, "AccountManagerCallback.run failed.", (Object) e);
                    }
                }
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 10223) {
            boolean z = false;
            if (i2 == -1) {
                z = true;
                loginThroughHostAccount();
            }
            onLoginResult(z);
        }
    }

    public String getAccountAuthToken(String str) {
        ServiceTokenResult serviceTokenResult;
        MiAccountManager miAccountManager = MiAccountManager.get(this.mApplication);
        Account xiaomiAccount = miAccountManager.getXiaomiAccount();
        if (xiaomiAccount == null) {
            return null;
        }
        if (useAuthTokenType()) {
            try {
                return miAccountManager.getAuthToken(xiaomiAccount, str, (Bundle) null, true, (AccountManagerCallback<Bundle>) null, (Handler) null).getResult().getString("authtoken");
            } catch (Throwable unused) {
                Log.e(TAG, "get AuthTokenFailed");
                return null;
            }
        } else {
            ServiceTokenFuture serviceToken = miAccountManager.getServiceToken(ShopApp.instance, str);
            if (serviceToken == null || (serviceTokenResult = serviceToken.get()) == null) {
                return null;
            }
            return serviceTokenResult.serviceToken + "," + serviceTokenResult.security;
        }
    }

    public String getEncryptedUserId() {
        Account[] accountsByType = MiAccountManager.get(ShopApp.instance).getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return MiAccountManager.get(ShopApp.instance).getUserData(accountsByType[0], "encrypted_user_id");
        }
        return null;
    }

    public final boolean canUseSystem() {
        return MiAccountManager.get(ShopApp.instance).canUseSystem();
    }

    public final void setUseSystem() {
        MiAccountManager.get(ShopApp.instance).setUseSystem();
    }

    public final void setUseLocal() {
        MiAccountManager.get(ShopApp.instance).setUseLocal();
    }

    public final Account[] getAccountsByType(String str) {
        return MiAccountManager.get(ShopApp.instance).getAccountsByType(str);
    }

    public String getUserId() {
        Account[] accountsByType = MiAccountManager.get(ShopApp.instance).getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return accountsByType[0].name;
        }
        return null;
    }

    public void invalidateAuthToken(String str, String str2) {
        MiAccountManager.get(ShopApp.instance).invalidateAuthToken(str, str2);
    }

    /* access modifiers changed from: protected */
    public void showLoginDialog(Activity activity) {
        final CommonAlertDialog commonAlertDialog = new CommonAlertDialog(activity);
        commonAlertDialog.setTitle(R.string.mishopsdk_autologin_title);
        commonAlertDialog.setMessage((CharSequence) ShopApp.instance.getResources().getString(R.string.mishopsdk_autologin_summary, new Object[]{LoginManager.getInstance().getAccountId()}));
        commonAlertDialog.setPositiveButton(R.string.mishopsdk_autologin_ask_ok, new View.OnClickListener() {
            public void onClick(View view) {
                commonAlertDialog.setOnDismissListener((DialogInterface.OnDismissListener) null);
                ShopAccountManager.this.loginThroughHostAccount();
            }
        });
        commonAlertDialog.setNegativeButton(R.string.mishopsdk_autologin_no_login, new View.OnClickListener() {
            public void onClick(View view) {
                commonAlertDialog.dismiss();
            }
        });
        commonAlertDialog.setOnDismissListener((DialogInterface.OnDismissListener) null);
        if (!activity.isFinishing() && !commonAlertDialog.isShowing()) {
            commonAlertDialog.show();
        }
    }

    public void loginThroughHostAccount() {
        AndroidUtil.sStageQueue.postRunnable(new Runnable() {
            public void run() {
                final String accountAuthToken = LoginManager.getInstance().getAccountAuthToken("eshopmobile");
                AndroidUtil.runOnUIThread(new Runnable() {
                    public void run() {
                        if (TextUtils.isEmpty(accountAuthToken)) {
                            ToastUtil.show(ShopApp.instance.getString(R.string.mishopsdk_login_system_failed));
                        } else if (ShopAccountManager.this.isUseSystem()) {
                            LoginManager.getInstance().loginSystem(accountAuthToken);
                            LoginManager.getInstance().setSystemLogin(true);
                        } else {
                            LoginManager.getInstance().login();
                        }
                    }
                });
            }
        });
    }

    public final AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return MiAccountManager.get(ShopApp.instance).addAccount(str, str2, strArr, bundle, activity, accountManagerCallback, handler);
    }

    @Deprecated
    public final AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return MiAccountManager.get(ShopApp.instance).getAuthToken(account, str, bundle, activity, accountManagerCallback, handler);
    }

    @Deprecated
    public final AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return MiAccountManager.get(ShopApp.instance).getAuthToken(account, str, bundle, z, accountManagerCallback, handler);
    }
}
