package com.xiaomi.youpin.login.api.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.data.Step2LoginParams;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.LoginRouter;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.PhonePwdLoginCallback;
import com.xiaomi.youpin.login.api.phone.PhoneQueryManager;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.okhttpApi.AsyncServiceTimeDiffUtil;
import com.xiaomi.youpin.login.ui.LoginMiByDynamicTokenActivity;
import java.util.HashMap;

public class PhonePwdLoginManager extends BaseLoginManager {
    private PhoneQueryManager j;

    public PhonePwdLoginManager(Context context) {
        super(context);
        this.j = new PhoneQueryManager(context);
    }

    public void a(ActivatorPhoneInfo activatorPhoneInfo, String str, String str2, String str3, PhonePwdLoginCallback phonePwdLoginCallback) {
        LocalPhoneDataCache.a().c();
        final PhonePwdLoginCallback phonePwdLoginCallback2 = phonePwdLoginCallback;
        final ActivatorPhoneInfo activatorPhoneInfo2 = activatorPhoneInfo;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        this.j.a(this.e, activatorPhoneInfo, str, str2, str3, (PhoneLoginController.PasswordLoginCallback) new PhoneLoginController.PasswordLoginCallback() {
            public void onLoginSuccess(final AccountInfo accountInfo) {
                if (PhonePwdLoginManager.this.h) {
                    AsyncServiceTimeDiffUtil.a(new AsyncCallback<Long, Error>() {
                        public void a(Long l) {
                            PhonePwdLoginManager.this.a(accountInfo, l, phonePwdLoginCallback2);
                        }

                        public void a(Error error) {
                            String str;
                            HashMap hashMap = new HashMap();
                            String str2 = null;
                            if (error == null) {
                                str = null;
                            } else {
                                str = error.a() + ":" + error.b();
                            }
                            hashMap.put("error", str);
                            if (activatorPhoneInfo2 != null) {
                                str2 = activatorPhoneInfo2.phone + ":" + activatorPhoneInfo2.operatorLink + ":" + activatorPhoneInfo2.activatorToken;
                            }
                            hashMap.put("activatorPhoneInfo", str2);
                            hashMap.put("pwd", str4);
                            hashMap.put("captchaCode", str5);
                            hashMap.put("captchaIck", str6);
                            phonePwdLoginCallback2.onLoginFail(LoginErrorCode.i, "获取timeDiff失败", hashMap);
                        }
                    });
                } else {
                    PhonePwdLoginManager.this.a(accountInfo, 0L, phonePwdLoginCallback2);
                }
            }

            public void onLoginByStep2(Step2LoginParams step2LoginParams) {
                PhonePwdLoginManager.this.a(step2LoginParams.userId, step2LoginParams.step1Token, step2LoginParams.metaLoginData, phonePwdLoginCallback2);
            }

            public void onNeedCaptchaCode(boolean z, String str) {
                phonePwdLoginCallback2.a(str, z);
            }

            public void onNeedNotification(String str, String str2) {
                PhonePwdLoginManager.this.a(str2, (BaseLoginCallback) phonePwdLoginCallback2);
            }

            public void onLoginFailed(PhoneLoginController.ErrorCode errorCode, String str, boolean z) {
                String str2;
                HashMap hashMap = new HashMap();
                if (errorCode == null) {
                    str2 = null;
                } else {
                    str2 = errorCode.toString();
                }
                hashMap.put("ErrorCode", str2);
                hashMap.put("b", "" + z);
                switch (AnonymousClass3.f23443a[errorCode.ordinal()]) {
                    case 1:
                        phonePwdLoginCallback2.onLoginFail(LoginErrorCode.I, str, hashMap);
                        return;
                    case 2:
                        phonePwdLoginCallback2.onLoginFail(-5001, str, hashMap);
                        return;
                    case 3:
                        PhonePwdLoginCallback phonePwdLoginCallback = phonePwdLoginCallback2;
                        phonePwdLoginCallback.onLoginFail(LoginErrorCode.K, "身份验证失败 " + str, hashMap);
                        return;
                    case 4:
                        PhonePwdLoginCallback phonePwdLoginCallback2 = phonePwdLoginCallback2;
                        phonePwdLoginCallback2.onLoginFail(LoginErrorCode.L, "网络错误 " + str, hashMap);
                        return;
                    case 5:
                        PhonePwdLoginCallback phonePwdLoginCallback3 = phonePwdLoginCallback2;
                        phonePwdLoginCallback3.onLoginFail(LoginErrorCode.M, "服务器出错 " + str, hashMap);
                        return;
                    case 6:
                        PhonePwdLoginCallback phonePwdLoginCallback4 = phonePwdLoginCallback2;
                        phonePwdLoginCallback4.onLoginFail(LoginErrorCode.N, "禁止访问 " + str, hashMap);
                        return;
                    case 7:
                        PhonePwdLoginCallback phonePwdLoginCallback5 = phonePwdLoginCallback2;
                        phonePwdLoginCallback5.onLoginFail(LoginErrorCode.O, "参数错误 " + str, hashMap);
                        return;
                    case 8:
                        PhonePwdLoginCallback phonePwdLoginCallback6 = phonePwdLoginCallback2;
                        phonePwdLoginCallback6.onLoginFail(LoginErrorCode.P, "用户操作次数达到上限 " + str, hashMap);
                        return;
                    case 9:
                        PhonePwdLoginCallback phonePwdLoginCallback7 = phonePwdLoginCallback2;
                        phonePwdLoginCallback7.onLoginFail(LoginErrorCode.Q, "密码错误 " + str, hashMap);
                        return;
                    case 10:
                        PhonePwdLoginCallback phonePwdLoginCallback8 = phonePwdLoginCallback2;
                        phonePwdLoginCallback8.onLoginFail(LoginErrorCode.S, "不存在该手机号 " + str, hashMap);
                        return;
                    case 11:
                        PhonePwdLoginCallback phonePwdLoginCallback9 = phonePwdLoginCallback2;
                        phonePwdLoginCallback9.onLoginFail(LoginErrorCode.R, "不存在当前用户 " + str, hashMap);
                        return;
                    default:
                        phonePwdLoginCallback2.onLoginFail(-5001, str, hashMap);
                        return;
                }
            }
        });
    }

    /* renamed from: com.xiaomi.youpin.login.api.manager.PhonePwdLoginManager$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f23443a = new int[PhoneLoginController.ErrorCode.values().length];

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
                f23443a = r0
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_UNKNOWN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_AUTH_FAIL     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_NETWORK     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_SERVER     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x004b }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_ACCESS_DENIED     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_INVALID_PARAM     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_USER_ACTION_OVER_LIMIT     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x006e }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_PASSWORD     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x007a }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_NO_PHONE     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                int[] r0 = f23443a     // Catch:{ NoSuchFieldError -> 0x0086 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_NON_EXIST_USER     // Catch:{ NoSuchFieldError -> 0x0086 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0086 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0086 }
            L_0x0086:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.api.manager.PhonePwdLoginManager.AnonymousClass3.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str, String str2, MetaLoginData metaLoginData, final PhonePwdLoginCallback phonePwdLoginCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || metaLoginData == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("userId", str);
            hashMap.put("step1Token", str2);
            phonePwdLoginCallback.onLoginFail(LoginErrorCode.H, "动态Token参数错误", hashMap);
            return;
        }
        LoginRouter.a(this.c, str, str2, metaLoginData);
        IntentFilter intentFilter = new IntentFilter(LoginMiByDynamicTokenActivity.ACTION_TOKEN_COMPLETE);
        LocalBroadcastManager.getInstance(this.c.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (LoginMiByDynamicTokenActivity.ACTION_TOKEN_COMPLETE.equals(intent.getAction())) {
                    LocalBroadcastManager.getInstance(PhonePwdLoginManager.this.c.getApplicationContext()).unregisterReceiver(this);
                    if (intent.getBooleanExtra(LoginMiByDynamicTokenActivity.PARMA_STATE, false)) {
                        phonePwdLoginCallback.onLoginSuccess((LoginMiAccount) intent.getParcelableExtra(LoginMiByDynamicTokenActivity.PARMA_MIACCOUNT));
                        return;
                    }
                    int intExtra = intent.getIntExtra(LoginMiByDynamicTokenActivity.PARMA_ERRORCODE, 0);
                    String stringExtra = intent.getStringExtra(LoginMiByDynamicTokenActivity.PARMA_ERRORCMSG);
                    HashMap hashMap = new HashMap();
                    hashMap.put("userId", str);
                    phonePwdLoginCallback.onLoginFail(intExtra, stringExtra, hashMap);
                }
            }
        }, intentFilter);
    }

    /* access modifiers changed from: private */
    public void a(AccountInfo accountInfo, Long l, PhonePwdLoginCallback phonePwdLoginCallback) {
        AuthenticatorUtil.addOrUpdateAccountManager(this.c, accountInfo);
        BaseAccount baseAccount = new BaseAccount();
        baseAccount.e = accountInfo.getPassToken();
        baseAccount.f23409a = accountInfo.getUserId();
        baseAccount.b = accountInfo.getEncryptedUserId();
        baseAccount.c = accountInfo.getServiceToken();
        baseAccount.d = accountInfo.getSecurity();
        baseAccount.f = l.longValue();
        a(baseAccount, (BaseLoginCallback) phonePwdLoginCallback);
    }
}
