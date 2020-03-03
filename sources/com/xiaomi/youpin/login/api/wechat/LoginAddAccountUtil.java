package com.xiaomi.youpin.login.api.wechat;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.snscorelib.internal.entity.PassportSnsConstant;
import com.xiaomi.passport.snscorelib.internal.entity.SNSLoginParameter;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.entity.wx.GetWXAccessTokenByAuthCodeResult;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;

public class LoginAddAccountUtil {
    public static void a(Activity activity, GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult, String str, final AsyncCallback<Boolean, ExceptionError> asyncCallback) {
        Bundle bundle = new Bundle();
        bundle.putString("login_type", PassportSnsConstant.SNS_LOGIN);
        bundle.putParcelable(PassportSnsConstant.SNS_LOGIN_PARAMETER, new SNSLoginParameter.Builder().sid(str).enToken(getWXAccessTokenByAuthCodeResult.f23524a).openId(getWXAccessTokenByAuthCodeResult.b).expires_in(String.valueOf(getWXAccessTokenByAuthCodeResult.c)).appid(MiLoginApi.a().l()).build());
        MiAccountManager miAccountManager = MiAccountManager.get(activity.getApplicationContext());
        miAccountManager.setUseLocal();
        miAccountManager.addAccount("com.xiaomi", str, (String[]) null, bundle, activity, new AccountManagerCallback<Bundle>() {
            @SuppressLint({"StaticFieldLeak"})
            public void run(final AccountManagerFuture<Bundle> accountManagerFuture) {
                AsyncTaskUtils.a(new AsyncTask<Object, Object, Pair<Boolean, ExceptionError>>() {
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.accounts.OperationCanceledException} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.io.IOException} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: android.accounts.AuthenticatorException} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: android.accounts.OperationCanceledException} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: android.accounts.OperationCanceledException} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: android.accounts.OperationCanceledException} */
                    /* access modifiers changed from: protected */
                    /* JADX WARNING: Multi-variable type inference failed */
                    /* JADX WARNING: Removed duplicated region for block: B:12:0x001d  */
                    /* JADX WARNING: Removed duplicated region for block: B:14:0x002d  */
                    /* renamed from: a */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public android.util.Pair<java.lang.Boolean, com.xiaomi.youpin.login.entity.error.ExceptionError> doInBackground(java.lang.Object... r5) {
                        /*
                            r4 = this;
                            r5 = 0
                            android.accounts.AccountManagerFuture r0 = r2     // Catch:{ OperationCanceledException -> 0x0016, IOException -> 0x0011, AuthenticatorException -> 0x000c }
                            java.lang.Object r0 = r0.getResult()     // Catch:{ OperationCanceledException -> 0x0016, IOException -> 0x0011, AuthenticatorException -> 0x000c }
                            android.os.Bundle r0 = (android.os.Bundle) r0     // Catch:{ OperationCanceledException -> 0x0016, IOException -> 0x0011, AuthenticatorException -> 0x000c }
                            r1 = r0
                            r0 = r5
                            goto L_0x001b
                        L_0x000c:
                            r0 = move-exception
                            r0.printStackTrace()
                            goto L_0x001a
                        L_0x0011:
                            r0 = move-exception
                            r0.printStackTrace()
                            goto L_0x001a
                        L_0x0016:
                            r0 = move-exception
                            r0.printStackTrace()
                        L_0x001a:
                            r1 = r5
                        L_0x001b:
                            if (r0 == 0) goto L_0x002d
                            com.xiaomi.youpin.login.entity.error.ExceptionError r1 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                            r2 = -1
                            java.lang.String r3 = ""
                            r1.<init>(r2, r3)
                            r1.f23518a = r0
                            android.util.Pair r0 = new android.util.Pair
                            r0.<init>(r5, r1)
                            return r0
                        L_0x002d:
                            if (r1 == 0) goto L_0x003f
                            java.lang.String r0 = "booleanResult"
                            boolean r0 = r1.getBoolean(r0)
                            android.util.Pair r1 = new android.util.Pair
                            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                            r1.<init>(r0, r5)
                            return r1
                        L_0x003f:
                            android.util.Pair r0 = new android.util.Pair
                            r1 = 0
                            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                            r0.<init>(r1, r5)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.api.wechat.LoginAddAccountUtil.AnonymousClass1.AnonymousClass1.doInBackground(java.lang.Object[]):android.util.Pair");
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Pair<Boolean, ExceptionError> pair) {
                        Boolean bool = (Boolean) pair.first;
                        ExceptionError exceptionError = (ExceptionError) pair.second;
                        if (asyncCallback == null) {
                            return;
                        }
                        if (exceptionError == null) {
                            asyncCallback.a(bool);
                        } else {
                            asyncCallback.a(exceptionError);
                        }
                    }
                }, new Object[0]);
            }
        }, (Handler) null);
    }
}
