package com.xiaomi.youpin.login.api.manager;

import android.content.Context;
import android.util.Pair;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.RegisterCallback;
import com.xiaomi.youpin.login.api.phone.PhoneQueryManager;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.api.BaseLoginApi;
import java.util.HashMap;

class PhoneRegisterManager extends BaseLoginManager {
    private PhoneQueryManager j;

    public PhoneRegisterManager(Context context) {
        super(context);
        this.j = new PhoneQueryManager(context);
    }

    public void a(String str, String str2, RegisterCallback registerCallback) {
        this.j.a(str, str2, a(registerCallback, true));
    }

    public void a(ActivatorPhoneInfo activatorPhoneInfo, RegisterCallback registerCallback) {
        LocalPhoneDataCache.a().c();
        this.j.a(activatorPhoneInfo, a(registerCallback, false));
    }

    private PhoneLoginController.PhoneRegisterCallback a(final RegisterCallback registerCallback, final boolean z) {
        return new PhoneLoginController.PhoneRegisterCallback() {
            public void onRegisterSuccess(AccountInfo accountInfo) {
                PhoneRegisterManager.this.a(accountInfo, registerCallback);
            }

            public void onRegisterReachLimit() {
                registerCallback.onLoginFail(LoginErrorCode.W, "该手机号码半年内绑定超过3个小米账号", new HashMap());
            }

            public void onTokenExpired() {
                if (z) {
                    registerCallback.onLoginFail(LoginErrorCode.X, "短信验证码错误", new HashMap());
                } else {
                    registerCallback.onLoginFail(LoginErrorCode.Y, "网络取号的Token无效", new HashMap());
                }
            }

            public void onRegisterFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                String str2;
                HashMap hashMap = new HashMap();
                if (errorCode == null) {
                    str2 = null;
                } else {
                    str2 = errorCode.toString();
                }
                hashMap.put("ErrorCode", str2);
                switch (AnonymousClass3.f23446a[errorCode.ordinal()]) {
                    case 1:
                        registerCallback.onLoginFail(LoginErrorCode.I, str, hashMap);
                        return;
                    case 2:
                        registerCallback.onLoginFail(-5001, str, hashMap);
                        return;
                    case 3:
                        RegisterCallback registerCallback = registerCallback;
                        registerCallback.onLoginFail(LoginErrorCode.K, "身份验证失败 " + str, hashMap);
                        return;
                    case 4:
                        RegisterCallback registerCallback2 = registerCallback;
                        registerCallback2.onLoginFail(LoginErrorCode.L, "网络错误 " + str, hashMap);
                        return;
                    case 5:
                        RegisterCallback registerCallback3 = registerCallback;
                        registerCallback3.onLoginFail(LoginErrorCode.M, "服务器出错 " + str, hashMap);
                        return;
                    case 6:
                        RegisterCallback registerCallback4 = registerCallback;
                        registerCallback4.onLoginFail(LoginErrorCode.N, "禁止访问 " + str, hashMap);
                        return;
                    case 7:
                        RegisterCallback registerCallback5 = registerCallback;
                        registerCallback5.onLoginFail(LoginErrorCode.O, "参数错误 " + str, hashMap);
                        return;
                    case 8:
                        RegisterCallback registerCallback6 = registerCallback;
                        registerCallback6.onLoginFail(LoginErrorCode.P, "用户操作次数达到上限 " + str, hashMap);
                        return;
                    default:
                        registerCallback.onLoginFail(-5001, str, hashMap);
                        return;
                }
            }
        };
    }

    /* renamed from: com.xiaomi.youpin.login.api.manager.PhoneRegisterManager$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f23446a = new int[PhoneLoginController.ErrorCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode[] r0 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f23446a = r0
                int[] r0 = f23446a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f23446a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_UNKNOWN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f23446a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_AUTH_FAIL     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f23446a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_NETWORK     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f23446a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_SERVER     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f23446a     // Catch:{ NoSuchFieldError -> 0x004b }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_ACCESS_DENIED     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f23446a     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_INVALID_PARAM     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f23446a     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.xiaomi.passport.uicontroller.PhoneLoginController$ErrorCode r1 = com.xiaomi.passport.uicontroller.PhoneLoginController.ErrorCode.ERROR_USER_ACTION_OVER_LIMIT     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.api.manager.PhoneRegisterManager.AnonymousClass3.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void a(final AccountInfo accountInfo, final RegisterCallback registerCallback) {
        BaseLoginApi.a(this.e, accountInfo.getUserId(), accountInfo.getPassToken(), true, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
            public void a(Pair<AccountInfo, Long> pair) {
                AccountInfo accountInfo = (AccountInfo) pair.first;
                AuthenticatorUtil.addOrUpdateAccountManager(PhoneRegisterManager.this.c, accountInfo);
                BaseAccount baseAccount = new BaseAccount();
                baseAccount.e = accountInfo.getPassToken();
                baseAccount.f23409a = accountInfo.getUserId();
                baseAccount.b = accountInfo.getEncryptedUserId();
                baseAccount.c = accountInfo.getServiceToken();
                baseAccount.d = accountInfo.getSecurity();
                baseAccount.f = ((Long) pair.second).longValue();
                PhoneRegisterManager.this.a(baseAccount, (BaseLoginCallback) registerCallback);
            }

            public void a(ExceptionError exceptionError) {
                String str;
                HashMap hashMap = new HashMap();
                if (accountInfo == null) {
                    str = null;
                } else {
                    str = accountInfo.userId + ":" + accountInfo.encryptedUserId + ":" + accountInfo.autoLoginUrl + ":" + accountInfo.passToken;
                }
                hashMap.put("accountInfo", str);
                registerCallback.onLoginFail(exceptionError.a(), exceptionError.b(), hashMap);
            }
        });
    }
}
