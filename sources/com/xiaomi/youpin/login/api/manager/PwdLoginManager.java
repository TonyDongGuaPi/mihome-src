package com.xiaomi.youpin.login.api.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.data.Step2LoginParams;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.LoginRouter;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.PwdLoginCallback;
import com.xiaomi.youpin.login.api.phone.PhoneQueryManager;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.AsyncServiceTimeDiffUtil;
import com.xiaomi.youpin.login.okhttpApi.api.PwdLoginApi;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;
import com.xiaomi.youpin.login.ui.LoginMiByDynamicTokenActivity;
import java.util.HashMap;

class PwdLoginManager extends BaseLoginManager {
    private static final String j = "PwdLoginManager";
    private PhoneQueryManager k;

    public PwdLoginManager(Context context) {
        super(context);
        this.k = new PhoneQueryManager(context);
    }

    @SuppressLint({"StaticFieldLeak"})
    public void a(String str, String str2, String str3, String str4, PwdLoginCallback pwdLoginCallback) {
        if (this.b == null) {
            final String str5 = str;
            final String str6 = str2;
            final String str7 = str3;
            final String str8 = str4;
            final PwdLoginCallback pwdLoginCallback2 = pwdLoginCallback;
            AsyncTaskUtils.a(new AsyncTask<Object, Object, Pair<MetaLoginData, Exception>>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Pair<MetaLoginData, Exception> doInBackground(Object... objArr) {
                    MetaLoginData metaLoginData = null;
                    try {
                        e = null;
                        metaLoginData = XMPassport.getMetaLoginData(str5, PwdLoginManager.this.e);
                    } catch (Exception e2) {
                        e = e2;
                    }
                    return new Pair<>(metaLoginData, e);
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(Pair<MetaLoginData, Exception> pair) {
                    MetaLoginData metaLoginData = (MetaLoginData) pair.first;
                    Exception exc = (Exception) pair.second;
                    if (exc != null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("username", str5);
                        hashMap.put("password", str6);
                        hashMap.put("captchaCode", str7);
                        hashMap.put("captchaIck", str8);
                        PwdLoginCallback pwdLoginCallback = pwdLoginCallback2;
                        pwdLoginCallback.onLoginFail(LoginErrorCode.y, "获取MiServerSign失败 " + exc.getMessage(), hashMap);
                    } else if (metaLoginData == null) {
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("username", str5);
                        hashMap2.put("password", str6);
                        hashMap2.put("captchaCode", str7);
                        hashMap2.put("captchaIck", str8);
                        pwdLoginCallback2.onLoginFail(LoginErrorCode.y, "获取MiServerSign失败  metaLoginData is null", hashMap2);
                    } else {
                        PwdLoginManager.this.b = metaLoginData;
                        PwdLoginManager.this.b(str5, str6, str7, str8, pwdLoginCallback2);
                    }
                }
            }, new Object[0]);
            return;
        }
        b(str, str2, str3, str4, pwdLoginCallback);
    }

    /* access modifiers changed from: private */
    public void b(String str, String str2, String str3, String str4, PwdLoginCallback pwdLoginCallback) {
        final PwdLoginCallback pwdLoginCallback2 = pwdLoginCallback;
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final String str8 = str4;
        this.k.a(this.e, str, str2, str3, str4, (PhoneLoginController.PasswordLoginCallback) new PhoneLoginController.PasswordLoginCallback() {
            public void onLoginSuccess(final AccountInfo accountInfo) {
                if (PwdLoginManager.this.h) {
                    AsyncServiceTimeDiffUtil.a(new AsyncCallback<Long, Error>() {
                        public void a(Long l) {
                            AuthenticatorUtil.addOrUpdateAccountManager(PwdLoginManager.this.c, accountInfo);
                            BaseAccount baseAccount = new BaseAccount();
                            baseAccount.a(accountInfo, l.longValue());
                            PwdLoginManager.this.a(baseAccount, (BaseLoginCallback) pwdLoginCallback2);
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
                            hashMap.put("username", str5);
                            hashMap.put("password", str6);
                            hashMap.put("captchaCode", str7);
                            hashMap.put("captchaIck", str8);
                            pwdLoginCallback2.onLoginFail(LoginErrorCode.i, "获取timeDiff失败", hashMap);
                        }
                    });
                    return;
                }
                AuthenticatorUtil.addOrUpdateAccountManager(PwdLoginManager.this.c, accountInfo);
                BaseAccount baseAccount = new BaseAccount();
                baseAccount.a(accountInfo, 0);
                PwdLoginManager.this.a(baseAccount, (BaseLoginCallback) pwdLoginCallback2);
            }

            public void onLoginByStep2(Step2LoginParams step2LoginParams) {
                PwdLoginManager.this.a(step2LoginParams.userId, step2LoginParams.step1Token, step2LoginParams.metaLoginData, pwdLoginCallback2);
            }

            public void onNeedCaptchaCode(boolean z, String str) {
                if (!TextUtils.isEmpty(str)) {
                    pwdLoginCallback2.a(str, z);
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("username", str5);
                hashMap.put("password", str6);
                hashMap.put("captchaCode", str7);
                hashMap.put("captchaIck", str8);
                pwdLoginCallback2.onLoginFail(LoginErrorCode.A, "Passtoken获取失败, 图形验证码url为空", hashMap);
            }

            public void onNeedNotification(String str, String str2) {
                if (!TextUtils.isEmpty(str2)) {
                    PwdLoginManager.this.a(str2, (BaseLoginCallback) pwdLoginCallback2);
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("username", str5);
                hashMap.put("password", str6);
                hashMap.put("captchaCode", str7);
                hashMap.put("captchaIck", str8);
                pwdLoginCallback2.onLoginFail(LoginErrorCode.z, "Passtoken获取失败, 安全验证Data不合法", hashMap);
            }

            public void onLoginFailed(PhoneLoginController.ErrorCode errorCode, String str, boolean z) {
                String str2;
                HashMap hashMap = new HashMap();
                if (errorCode == null) {
                    str2 = null;
                } else {
                    str2 = errorCode.toString();
                }
                hashMap.put("errorCode", str2);
                hashMap.put("username", str5);
                hashMap.put("password", str6);
                hashMap.put("captchaCode", str7);
                hashMap.put("captchaIck", str8);
                switch (AnonymousClass5.f23455a[errorCode.ordinal()]) {
                    case 1:
                        pwdLoginCallback2.onLoginFail(LoginErrorCode.I, str, hashMap);
                        return;
                    case 2:
                        pwdLoginCallback2.onLoginFail(-5001, str, hashMap);
                        return;
                    case 3:
                        PwdLoginCallback pwdLoginCallback = pwdLoginCallback2;
                        pwdLoginCallback.onLoginFail(LoginErrorCode.K, "身份验证失败 " + str, hashMap);
                        return;
                    case 4:
                        PwdLoginCallback pwdLoginCallback2 = pwdLoginCallback2;
                        pwdLoginCallback2.onLoginFail(LoginErrorCode.L, "网络错误 " + str, hashMap);
                        return;
                    case 5:
                        PwdLoginCallback pwdLoginCallback3 = pwdLoginCallback2;
                        pwdLoginCallback3.onLoginFail(LoginErrorCode.M, "服务器出错 " + str, hashMap);
                        return;
                    case 6:
                        PwdLoginCallback pwdLoginCallback4 = pwdLoginCallback2;
                        pwdLoginCallback4.onLoginFail(LoginErrorCode.N, "禁止访问 " + str, hashMap);
                        return;
                    case 7:
                        PwdLoginCallback pwdLoginCallback5 = pwdLoginCallback2;
                        pwdLoginCallback5.onLoginFail(LoginErrorCode.O, "参数错误 " + str, hashMap);
                        return;
                    case 8:
                        PwdLoginCallback pwdLoginCallback6 = pwdLoginCallback2;
                        pwdLoginCallback6.onLoginFail(LoginErrorCode.P, "用户操作次数达到上限 " + str, hashMap);
                        return;
                    case 9:
                        PwdLoginCallback pwdLoginCallback7 = pwdLoginCallback2;
                        pwdLoginCallback7.onLoginFail(LoginErrorCode.Q, "密码错误 " + str, hashMap);
                        return;
                    case 10:
                        PwdLoginCallback pwdLoginCallback8 = pwdLoginCallback2;
                        pwdLoginCallback8.onLoginFail(LoginErrorCode.S, "不存在该手机号 " + str, hashMap);
                        return;
                    case 11:
                        PwdLoginCallback pwdLoginCallback9 = pwdLoginCallback2;
                        pwdLoginCallback9.onLoginFail(LoginErrorCode.R, "不存在当前用户 " + str, hashMap);
                        return;
                    default:
                        pwdLoginCallback2.onLoginFail(-5001, str, hashMap);
                        return;
                }
            }
        });
    }

    /* renamed from: com.xiaomi.youpin.login.api.manager.PwdLoginManager$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f23455a = new int[PhoneLoginController.ErrorCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode[] r0 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f23455a = r0
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_UNKNOWN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_AUTH_FAIL     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_NETWORK     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_SERVER     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x004b }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_ACCESS_DENIED     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_INVALID_PARAM     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_USER_ACTION_OVER_LIMIT     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x006e }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_PASSWORD     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x007a }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_NO_PHONE     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                int[] r0 = f23455a     // Catch:{ NoSuchFieldError -> 0x0086 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_NON_EXIST_USER     // Catch:{ NoSuchFieldError -> 0x0086 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0086 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0086 }
            L_0x0086:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.api.manager.PwdLoginManager.AnonymousClass5.<clinit>():void");
        }
    }

    @Deprecated
    private void c(String str, String str2, String str3, String str4, final PwdLoginCallback pwdLoginCallback) {
        PwdLoginApi.a(str, str2, str3, str4, this.e, this.h, this.b, true, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
            public void a(ExceptionError exceptionError) {
            }

            public void a(Pair<AccountInfo, Long> pair) {
                AccountInfo accountInfo = (AccountInfo) pair.first;
                AuthenticatorUtil.addOrUpdateAccountManager(PwdLoginManager.this.c, accountInfo);
                BaseAccount baseAccount = new BaseAccount();
                baseAccount.a(accountInfo, ((Long) pair.second).longValue());
                PwdLoginManager.this.a(baseAccount, (BaseLoginCallback) pwdLoginCallback);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final String str, final String str2, MetaLoginData metaLoginData, final PwdLoginCallback pwdLoginCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || metaLoginData == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("userId", str);
            hashMap.put("step1Token", str2);
            pwdLoginCallback.onLoginFail(LoginErrorCode.H, "动态Token参数错误", hashMap);
            return;
        }
        LoginRouter.a(this.c, str, str2, metaLoginData);
        IntentFilter intentFilter = new IntentFilter(LoginMiByDynamicTokenActivity.ACTION_TOKEN_COMPLETE);
        LocalBroadcastManager.getInstance(this.c.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (LoginMiByDynamicTokenActivity.ACTION_TOKEN_COMPLETE.equals(intent.getAction())) {
                    LocalBroadcastManager.getInstance(PwdLoginManager.this.c.getApplicationContext()).unregisterReceiver(this);
                    if (intent.getBooleanExtra(LoginMiByDynamicTokenActivity.PARMA_STATE, false)) {
                        pwdLoginCallback.onLoginSuccess((LoginMiAccount) intent.getParcelableExtra(LoginMiByDynamicTokenActivity.PARMA_MIACCOUNT));
                        return;
                    }
                    int intExtra = intent.getIntExtra(LoginMiByDynamicTokenActivity.PARMA_ERRORCODE, 0);
                    String stringExtra = intent.getStringExtra(LoginMiByDynamicTokenActivity.PARMA_ERRORCMSG);
                    HashMap hashMap = new HashMap();
                    hashMap.put("userId", str);
                    hashMap.put("step1Token", str2);
                    pwdLoginCallback.onLoginFail(intExtra, stringExtra, hashMap);
                }
            }
        }, intentFilter);
    }
}
