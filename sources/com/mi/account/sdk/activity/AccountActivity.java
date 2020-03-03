package com.mi.account.sdk.activity;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import com.mi.account.sdk.LoginManager;
import com.mi.account.sdk.util.Constants;
import com.mi.activity.BaseActivity;
import com.mi.global.bbs.R;
import com.mi.global.bbs.manager.MiCommunitySdkManager;
import com.mi.global.bbs.manager.SdkListener;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import com.mi.util.ThreadPool;
import com.mi.util.Utils;
import com.mi.widget.BaseAlertDialog;
import com.xiaomi.accountsdk.futureservice.MiAccountManagerFuture;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.servicetoken.data.XmAccountVisibility;
import com.xiaomi.stat.c.c;

public class AccountActivity extends BaseActivity {
    private static final int CODE_NEW_CHOOSE_ACCOUNT = 1010;
    /* access modifiers changed from: private */
    public static final String TAG = "AccountActivity";

    /* access modifiers changed from: protected */
    public void onLocalLoginFailed() {
    }

    public void gotoAccount() {
        if (!MiCommunitySdkManager.getInstance().isUseSdkLogin()) {
            SdkListener listener = MiCommunitySdkManager.getInstance().getListener();
            if (listener != null) {
                listener.onNeedLogin(Constants.Account.accountSid);
                return;
            }
            throw new IllegalStateException("you should call MiCommunitySdkManager.getInstance().setListener() first.");
        } else if (!isMiui(getApplicationContext()) || Build.VERSION.SDK_INT < 26) {
            showLogin();
        } else {
            canAccessAccount();
        }
    }

    public static boolean isMiui(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo(c.f23036a, 0) != null) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void showLogin() {
        LoginManager instance = LoginManager.getInstance();
        instance.getMiAccountManager().setUseSystem();
        if (!instance.hasSystemAccount() || !Utils.Preference.getBooleanPref(this, "pref_miui_account_available", false)) {
            gotoLogin();
        } else {
            showSystemLoginDialog();
        }
    }

    private void showSystemLoginDialog() {
        BaseAlertDialog baseAlertDialog = new BaseAlertDialog(this);
        baseAlertDialog.setTitle(R.string.autologin_title);
        baseAlertDialog.a((CharSequence) getResources().getString(R.string.autologin_summary, new Object[]{LoginManager.getInstance().getSystemAccountId()}));
        baseAlertDialog.a(R.string.autologin_ask_ok, new View.OnClickListener() {
            public void onClick(View view) {
                ThreadPool.a(new Runnable() {
                    public void run() {
                        String systemAccountAuthToken = LoginManager.getInstance().getSystemAccountAuthToken(Constants.Account.getInstance().getServiceID());
                        if (!TextUtils.isEmpty(systemAccountAuthToken)) {
                            LoginManager.getInstance().loginSystem(systemAccountAuthToken);
                        } else {
                            MiToast.a((Context) AccountActivity.this, R.string.login_system_failed, 0);
                        }
                    }
                });
            }
        });
        baseAlertDialog.b(R.string.autologin_ask_cancel, new View.OnClickListener() {
            public void onClick(View view) {
                LoginManager.getInstance().setSystemLogin(false);
                AccountActivity.this.gotoLogin();
            }
        });
        baseAlertDialog.show();
    }

    public void gotoLogin() {
        MiAccountManager miAccountManager = LoginManager.getInstance().getMiAccountManager();
        miAccountManager.setUseLocal();
        doLocalLogin(miAccountManager);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1010 && i2 == -1) {
            intent.getStringExtra("authAccount");
            String stringExtra = intent.getStringExtra("accountType");
            String str = TAG;
            AccountLog.i(str, "accountType: " + stringExtra);
            if (TextUtils.equals(stringExtra, "com.xiaomi")) {
                Utils.Preference.setBooleanPref(getApplicationContext(), "pref_miui_account_available", true);
                showLogin();
                return;
            }
            return;
        }
        AccountLog.i(TAG, "newChooseAccount cancelled");
    }

    private void doLocalLogin(MiAccountManager miAccountManager) {
        miAccountManager.addAccount("com.xiaomi", Constants.Account.getInstance().getServiceID(), (String[]) null, new Bundle(), this, new AccountManagerCallback<Bundle>() {
            public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                try {
                    Bundle result = accountManagerFuture.getResult();
                    if (result != null) {
                        String access$000 = AccountActivity.TAG;
                        LogUtil.b(access$000, "get bundle:" + result);
                    }
                    if (result.getBoolean("booleanResult")) {
                        LogUtil.b(AccountActivity.TAG, "Add account succeed!");
                        ThreadPool.a(new Runnable() {
                            public void run() {
                                LoginManager.getInstance().loginLocal();
                            }
                        });
                        return;
                    }
                    LogUtil.b(AccountActivity.TAG, "Add account failed or not finished!");
                    AccountActivity.this.onLocalLoginFailed();
                } catch (Exception e) {
                    String access$0002 = AccountActivity.TAG;
                    LogUtil.b(access$0002, "get Exception:" + e.toString());
                    e.printStackTrace();
                }
            }
        }, (Handler) null);
    }

    public boolean checkLogin() {
        return loginPassport();
    }

    private boolean loginPassport() {
        if (LoginManager.getInstance().hasLogin()) {
            return true;
        }
        gotoAccount();
        return false;
    }

    private void canAccessAccount() {
        final MiAccountManager miAccountManager = LoginManager.getInstance().getMiAccountManager();
        miAccountManager.setUseSystem();
        final MiAccountManagerFuture<XmAccountVisibility> canAccessAccount = miAccountManager.canAccessAccount(getApplicationContext());
        new AsyncTask<Void, Void, XmAccountVisibility>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public XmAccountVisibility doInBackground(Void... voidArr) {
                try {
                    return (XmAccountVisibility) canAccessAccount.get();
                } catch (Exception e) {
                    AccountLog.w(AccountActivity.TAG, (Throwable) e);
                    return null;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(XmAccountVisibility xmAccountVisibility) {
                super.onPostExecute(xmAccountVisibility);
                if (xmAccountVisibility == null) {
                    AccountActivity.this.gotoLogin();
                    return;
                }
                switch (AnonymousClass5.f6724a[xmAccountVisibility.errorCode.ordinal()]) {
                    case 1:
                        if (xmAccountVisibility.visible) {
                            Utils.Preference.setBooleanPref(AccountActivity.this.getApplicationContext(), "pref_miui_account_available", true);
                            AccountActivity.this.showLogin();
                            return;
                        }
                        return;
                    case 2:
                        miAccountManager.addAccount("com.xiaomi", Constants.Account.getInstance().getServiceID(), (String[]) null, (Bundle) null, AccountActivity.this, new AccountManagerCallback<Bundle>() {
                            public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                            }
                        }, (Handler) null);
                        return;
                    case 3:
                        Intent intent = xmAccountVisibility.newChooseAccountIntent;
                        if (intent != null) {
                            intent.putExtra("descriptionTextOverride", AccountActivity.this.getString(R.string.tips_mi_account_login));
                            AccountActivity.this.startActivityForResult(intent, 1010);
                            return;
                        }
                        return;
                    default:
                        AccountActivity.this.gotoLogin();
                        return;
                }
            }
        }.execute(new Void[0]);
    }

    /* renamed from: com.mi.account.sdk.activity.AccountActivity$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f6724a = new int[XmAccountVisibility.ErrorCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode[] r0 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f6724a = r0
                int[] r0 = f6724a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode r1 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.ERROR_NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f6724a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode r1 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.ERROR_NO_ACCOUNT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f6724a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode r1 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.ERROR_NOT_SUPPORT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f6724a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode r1 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.ERROR_NO_PERMISSION     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.account.sdk.activity.AccountActivity.AnonymousClass5.<clinit>():void");
        }
    }
}
