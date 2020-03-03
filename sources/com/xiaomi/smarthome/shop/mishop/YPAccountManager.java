package com.xiaomi.smarthome.shop.mishop;

import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.miot.store.api.YouPinAccountManager;
import com.xiaomi.mishopsdk.account.adapter.AppAccountManager;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.youpin.log.LogUtils;

public class YPAccountManager extends AppAccountManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1570a = "YPAccountManager";
    /* access modifiers changed from: private */
    public Context b;

    public YPAccountManager(Application application) {
        super(application);
        this.b = application.getApplicationContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    String action = intent.getAction();
                    if (!TextUtils.isEmpty(action)) {
                        char c = 65535;
                        try {
                            int hashCode = action.hashCode();
                            if (hashCode != -1984077386) {
                                if (hashCode == -1470224095) {
                                    if (action.equals("action_on_logout")) {
                                        c = 1;
                                    }
                                }
                            } else if (action.equals("action_on_login_success")) {
                                c = 0;
                            }
                            switch (c) {
                                case 0:
                                    LogUtils.d(YPAccountManager.f1570a, "login");
                                    if (!ServerCompact.e(YPAccountManager.this.b)) {
                                        LoginManager.getInstance().login();
                                        YouPinAccountManager.a();
                                        return;
                                    }
                                    return;
                                case 1:
                                    LogUtils.d(YPAccountManager.f1570a, "logout");
                                    LoginManager.getInstance().logout();
                                    YouPinAccountManager.logout();
                                    return;
                                default:
                                    return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, intentFilter);
    }

    public boolean hostAccountHasLogin() {
        LogUtils.d(f1570a, "hostAccountHasLogin " + CoreApi.a().q());
        return CoreApi.a().q();
    }

    public void gotoLocalLoginUI(Activity activity, AccountManagerCallback<Bundle> accountManagerCallback) {
        LogUtils.d(f1570a, "gotoLocalLoginUI");
        LoginApi.a().a((Context) activity, 5, (LoginApi.LoginCallback) null);
    }

    public boolean isUseSystem() {
        LogUtils.d(f1570a, "isUseSystem");
        return CoreApi.a().v();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0084 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0085  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAccountAuthToken(java.lang.String r9) {
        /*
            r8 = this;
            boolean r0 = r8.isUseSystem()
            r1 = 0
            if (r0 == 0) goto L_0x0040
            android.content.Context r0 = r8.b
            android.accounts.Account r3 = com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil.c(r0)
            android.content.Context r0 = r8.b     // Catch:{ OperationCanceledException -> 0x0031, IOException -> 0x002c, AuthenticatorException -> 0x0027, Exception -> 0x0022 }
            android.accounts.AccountManager r2 = android.accounts.AccountManager.get(r0)     // Catch:{ OperationCanceledException -> 0x0031, IOException -> 0x002c, AuthenticatorException -> 0x0027, Exception -> 0x0022 }
            r5 = 1
            r6 = 0
            r7 = 0
            r4 = r9
            android.accounts.AccountManagerFuture r9 = r2.getAuthToken(r3, r4, r5, r6, r7)     // Catch:{ OperationCanceledException -> 0x0031, IOException -> 0x002c, AuthenticatorException -> 0x0027, Exception -> 0x0022 }
            java.lang.Object r9 = r9.getResult()     // Catch:{ OperationCanceledException -> 0x0031, IOException -> 0x002c, AuthenticatorException -> 0x0027, Exception -> 0x0022 }
            android.os.Bundle r9 = (android.os.Bundle) r9     // Catch:{ OperationCanceledException -> 0x0031, IOException -> 0x002c, AuthenticatorException -> 0x0027, Exception -> 0x0022 }
            goto L_0x0036
        L_0x0022:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0035
        L_0x0027:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0035
        L_0x002c:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0035
        L_0x0031:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0035:
            r9 = r1
        L_0x0036:
            if (r9 != 0) goto L_0x0039
            return r1
        L_0x0039:
            java.lang.String r0 = "authtoken"
            java.lang.String r9 = r9.getString(r0)
            return r9
        L_0x0040:
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r0 = r0.s()
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r2 = r2.w()
            com.xiaomi.accountsdk.account.data.AccountInfo r9 = com.xiaomi.passport.utils.AccountHelper.getServiceTokenByPassToken(r0, r2, r9)     // Catch:{ IOException -> 0x007d, InvalidResponseException -> 0x0078, InvalidCredentialException -> 0x0073, AccessDeniedException -> 0x006e, AuthenticationFailureException -> 0x0069, InvalidUserNameException -> 0x0064, IllegalDeviceException -> 0x005f, NeedNotificationException -> 0x005a, Exception -> 0x0055 }
            goto L_0x0082
        L_0x0055:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0081
        L_0x005a:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0081
        L_0x005f:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0081
        L_0x0064:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0081
        L_0x0069:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0081
        L_0x006e:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0081
        L_0x0073:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0081
        L_0x0078:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0081
        L_0x007d:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0081:
            r9 = r1
        L_0x0082:
            if (r9 != 0) goto L_0x0085
            return r1
        L_0x0085:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r9.serviceToken
            r0.append(r1)
            java.lang.String r1 = ","
            r0.append(r1)
            java.lang.String r9 = r9.security
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.mishop.YPAccountManager.getAccountAuthToken(java.lang.String):java.lang.String");
    }
}
